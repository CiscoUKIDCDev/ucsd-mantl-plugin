/*******************************************************************************
 * Copyright (c) 2016 Cisco and/or its affiliates
 * @author Matt Day
 *
 * Unless explicitly stated otherwise all files in this repository are licensed
 * under the Apache Software License 2.0
 *******************************************************************************/
package com.cisco.ukidcv.mantl.account.inventory;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.cisco.ukidcv.mantl.account.MantlAccount;
import com.cisco.ukidcv.mantl.api.MantlApi;
import com.cisco.ukidcv.mantl.constants.MantlConstants;
import com.cisco.ukidcv.mantl.exceptions.MantlAccountException;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.lib.connector.account.AccountUtil;
import com.cloupia.lib.connector.account.PhysicalConnectivityStatus;
import com.cloupia.lib.connector.account.PhysicalInfraAccount;

/**
 * Runs every 60 minutes or after a user action and collects information to
 * store locally
 * <p>
 * This is handled in two parts - this inventory class providing static methods
 * and a database store
 * <p>
 * This is a complex class and the heart of the plugin's communication with
 * Mantl
 *
 * @author Matt Day
 * @see com.cisco.ukidcv.mantl.account.inventory.MantlInventoryDB
 *
 */
public class MantlInventory {
	static Logger logger = Logger.getLogger(MantlInventory.class);

	/**
	 * Attempt to update the inventory.
	 * <p>
	 * UCS Director's internal inventory collection happens every 60 minutes.
	 * This is a long time. Whenever this is called it will check if a
	 * determined polling period has passed and if so, refresh the cached
	 * inventory
	 *
	 * @param account
	 *            Account to update
	 * @param reason
	 *            String reason for logging purposes
	 * @param force
	 *            set to true to force an update even if it's not due
	 * @throws Exception
	 */
	public static void update(MantlAccount account, String reason, boolean force) throws Exception {
		Date d = new Date();
		long c = d.getTime();
		// MantlInventoryDB invStore = getInventoryStore(account);
		final String accountName = account.getAccountName();
		final String queryString = "accountName == '" + accountName + "'";

		PhysicalInfraAccount infraAccount = AccountUtil.getAccountByName(accountName);
		PhysicalConnectivityStatus status = new PhysicalConnectivityStatus(infraAccount);

		try {

			ObjStore<MantlInventoryDB> invStoreCollection = ObjStoreHelper.getStore(MantlInventoryDB.class);
			MantlInventoryDB store = null;
			try {
				store = invStoreCollection.query(queryString).iterator().next();
			}
			catch (Exception e) {
				logger.warn("Possibly stale entry from older API - deleting & re-creating. " + e.getMessage());
				invStoreCollection.delete(queryString);
				store = create(account);
			}

			if (store == null) {
				logger.warn("Cannot find " + accountName + " in inventory! Rolling back and creating new");
				// Attempt to create it:
				store = create(account);
			}

			// Get user configured inventory lifespan (TODO fixme)
			final long inventoryLife = MantlConstants.MAX_POLLING_TIME;

			// If we're not forcing an update and the set time hasn't expired,
			// don't update
			if ((!force) && ((d.getTime() - store.getUpdated()) < inventoryLife)) {
				return;
			}
			logger.info("Updating inventory for account " + accountName);

			store.setUpdated(c);

			// Do things like
			// store.setBlahblahblah()

			d = new Date();
			final String update = c + "@" + d.getTime() + "@" + force + "@" + reason;

			log(store, update);

			invStoreCollection.modifySingleObject(queryString, store);

			status.setConnectionOK(true);

		}
		catch (Exception e) {
			logger.warn("Exception updating database! " + e.getMessage());

			status.setConnectionOK(false);
		}
	}

	/**
	 * Add a log entry
	 *
	 * @param store
	 *            Inventory store to log to
	 * @param message
	 *            Message to include
	 */
	private static void log(MantlInventoryDB store, String message) {
		store.getPolling().add(message);

		// Remove oldest entry if longer than the allowed log length
		if (store.getPolling().size() > MantlConstants.MAX_POLLING_LOG_ENTRIES) {
			store.getPolling().remove(0);
		}
	}

	/**
	 * Get the inventory database store
	 *
	 * @param account
	 *            Account to obtain
	 * @return Inventory DB
	 * @throws Exception
	 *             if there's a problem getting it
	 */
	private static MantlInventoryDB getInventoryStore(MantlAccount account) throws Exception {
		final String accountName = account.getAccountName();
		final String queryString = "accountName == '" + accountName + "'";
		try {

			ObjStore<MantlInventoryDB> invStoreCollection = ObjStoreHelper.getStore(MantlInventoryDB.class);

			for (MantlInventoryDB store : invStoreCollection.query(queryString)) {
				if (accountName.equals(store.getAccountName())) {
					return store;
				}
			}
		}
		catch (Exception e) {
			logger.warn("Exeption when doing this! " + e.getMessage());
		}
		// Account was deleted most likely during init
		return create(account);
	}

	/**
	 * Create a new inventory store
	 *
	 * @param account
	 *            Account to create it for
	 * @return New inventory store
	 * @throws Exception
	 *             if there's a problem creating it
	 */
	private static MantlInventoryDB create(MantlAccount account) throws Exception {
		PhysicalInfraAccount infraAccount = AccountUtil.getAccountByName(account.getAccountName());
		PhysicalConnectivityStatus status = new PhysicalConnectivityStatus(infraAccount);

		final String accountName = account.getAccountName();
		try {
			// Check we can reach the Mantl API:
			if (!MantlApi.testConnection(account)) {
				status.setConnectionOK(false);
				logger.warn("Could not get valid token - marking connection as invalid");
				throw new MantlAccountException("Could not get valid token");
			}
			ObjStore<MantlInventoryDB> invStoreCollection = ObjStoreHelper.getStore(MantlInventoryDB.class);
			MantlInventoryDB invStore = new MantlInventoryDB(accountName);
			invStoreCollection.insert(invStore);
			status.setConnectionOK(true);
			return invStore;
		}
		catch (Exception e) {
			status.setConnectionOK(false);
			logger.warn("Exeption when doing this! " + e.getMessage());
		}
		throw new MantlAccountException("Could not create inventory store!");
	}

	/**
	 * Returns the polling log (when things have been updated and why)
	 *
	 * @param account
	 *            Account from which to obtain the log
	 * @return the log file
	 * @throws Exception
	 */
	public static List<String> getLog(MantlAccount account) throws Exception {
		return getInventoryStore(account).getPolling();
	}

}
