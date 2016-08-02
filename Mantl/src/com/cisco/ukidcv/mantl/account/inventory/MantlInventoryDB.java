/*******************************************************************************
 * Copyright (c) 2016 Cisco and/or its affiliates
 * @author Matt Day
 *
 * Unless explicitly stated otherwise all files in this repository are licensed
 * under the Apache Software License 2.0
 *******************************************************************************/
package com.cisco.ukidcv.mantl.account.inventory;

import java.util.LinkedList;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

/**
 * Provides the JDO notation to cache entries to the UCS Director database.
 * <p>
 * The JSON format here is exactly the same as the Mantl API - we're just using
 * it to cache entries
 *
 * @author Matt Day
 *
 */
@PersistenceCapable(detachable = "true", table = "Mantl_inventory_db")
public class MantlInventoryDB {

	private static Logger logger = Logger.getLogger(MantlInventoryDB.class);

	// Account name this pertains to
	@Persistent
	private String accountName;

	// When it was last updated as an epoch value
	@Persistent
	private long updated;

	// Log file of historical polling
	@Persistent(defaultFetchGroup = "true")
	@Column(jdbcType = "CLOB")
	private LinkedList<String> polling;

	// CLOB is an SQL data type of unlimited length strings; perfect for JSON
	// To do add inventory values here

	/**
	 * Initialise inventory with an account name
	 *
	 * @param accountName
	 *            Name of the account to persist
	 */
	public MantlInventoryDB(String accountName) {
		this.accountName = accountName;
		logger.info("Created persistent entry for account " + accountName);
		try {
			logger.info("Setting up polling history");
			this.polling = new LinkedList<>();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the account name
	 *
	 * @return Account name
	 */
	public String getAccountName() {
		return this.accountName;
	}

	/**
	 * Set the account name
	 *
	 * @param accountName
	 *            Account name
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * Get the last updated time for this account as an epoch number in
	 * miliseconds
	 *
	 * @return Plugin update time in miliseconds
	 */
	public long getUpdated() {
		return this.updated;
	}

	/**
	 * Set the time this account was last polled
	 *
	 * @param updated
	 */
	public void setUpdated(long updated) {
		this.updated = updated;
	}

	/**
	 * Get the polling log
	 *
	 * @return Historical polling log
	 */
	public LinkedList<String> getPolling() {
		return this.polling;
	}

	/**
	 * Create a new polling log
	 *
	 * @param polling
	 *            Linked list of entries
	 */
	public void setPolling(LinkedList<String> polling) {
		this.polling = polling;
	}

}
