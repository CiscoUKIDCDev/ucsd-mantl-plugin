/*******************************************************************************
 * Copyright (c) 2016 Cisco and/or its affiliates
 * @author Matt Day
 *
 * Unless explicitly stated otherwise all files in this repository are licensed
 * under the Apache Software License 2.0
 *******************************************************************************/
package com.cisco.ukidcv.mantl.account.handler;

import java.util.Map;

import com.cisco.ukidcv.mantl.account.MantlAccount;
import com.cisco.ukidcv.mantl.account.inventory.MantlInventory;
import com.cisco.ukidcv.mantl.constants.MantlConstants;
import com.cloupia.lib.connector.AbstractInventoryItemHandler;
import com.cloupia.lib.connector.InventoryContext;
import com.cloupia.service.cIM.inframgr.collector.controller.PersistenceListener;
import com.cloupia.service.cIM.inframgr.collector.model.ItemResponse;

/**
 * Implements the inventory collector. Not sure about all of this as it's
 * boilerplate from the SDK...
 *
 * @author Matt Day
 *
 */
public class MantlInventoryItemHandler extends AbstractInventoryItemHandler {

	@Override
	public void cleanup(String accountName) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * This method used for do Inventory of Account
	 *
	 * @Override method of IInventoryItemHandlerIf interface
	 * @param accountName
	 *            ,InventoryContext
	 * @exception Exception
	 */
	@Override
	public void doInventory(String accountName, InventoryContext inventoryCtxt) throws Exception {
		this.doInventory(accountName);

	}

	/**
	 * This method used for do Inventory of Account
	 *
	 * @Override method of IInventoryItemHandlerIf interface
	 * @param accountName
	 *            ,Object
	 * @exception Exception
	 */
	@Override
	public void doInventory(String accountName, Object obj) throws Exception {
		this.doInventory(accountName);

	}

	/**
	 * private Method used for doing Inventory of Account
	 *
	 * @param accountName
	 * @exception Exception
	 */
	private void doInventory(String accountName) throws Exception {

		// MantlAccountAPI api = MantlAccountAPI.getMantlAccountAPI(acc);

		/**
		 * To provide the real implemntation , depends on the respond data
		 * binder for the requested item. To ensure the data collecting for the
		 * inventory via HTTP / SSH connection. Response is converted as JSON
		 * Data, Json Data is binded with the
		 */

		// String jsonData = api.getInventoryData(getUrl());

		MantlInventory.update(new MantlAccount(accountName), MantlConstants.INVENTORY_REASON_PERIODIC, true);

		final String jsonData = null;
		final ItemResponse bindableResponse = new ItemResponse();
		bindableResponse.setContext(this.getContext(accountName));
		bindableResponse.setCollectedData(jsonData);
		ItemResponse bindedResponse = null;

		final MantlJSONBinder binder = this.getBinder();
		if (binder != null) {
			bindedResponse = binder.bind(bindableResponse);
		}

		final PersistenceListener listener = this.getListener();
		if (listener != null) {
			listener.persistItem(bindedResponse);
		}

	}

	/**
	 * Method used for get Url
	 *
	 * @return String
	 */
	@SuppressWarnings("static-method")
	public String getUrl() {
		// TODO Auto-generated method stub
		return "platform/1/protocols/smb/shares";
	}

	/**
	 * Method used to get object of MantlAccountJSONBinder Binder will bind the
	 * respective object as JSON.
	 *
	 * @return MantlAccountJSONBinder
	 */
	@SuppressWarnings("static-method")
	public MantlAccountJSONBinder getBinder() {
		// TODO Auto-generated method stub
		return new MantlAccountJSONBinder();
	}

	/**
	 * Private Method used to get Map of Context
	 *
	 * @param accountName
	 * @return Map<String, Object>
	 * @exception No
	 */
	@SuppressWarnings("static-method")
	private Map<String, Object> getContext(String accountName) {
		return null;
	}

	/**
	 * Private Method used to get Object of PersistenceListener
	 *
	 * @param No
	 * @return PersistenceListener
	 * @exception No
	 */
	@SuppressWarnings("static-method")
	private PersistenceListener getListener() {
		// TODO Auto-generated method stub
		return new MantlCollectorInventoryPersistenceListener();
	}

}
