/*******************************************************************************
 * Copyright (c) 2016 Cisco and/or its affiliates
 * @author Matt Day
 *
 * Unless explicitly stated otherwise all files in this repository are licensed
 * under the Apache Software License 2.0
 *******************************************************************************/
package com.cisco.ukidcv.mantl.account;

import com.cisco.cuic.api.client.JSON;
import com.cisco.ukidcv.mantl.exceptions.MantlAccountException;
import com.cloupia.lib.connector.account.AccountUtil;
import com.cloupia.lib.connector.account.PhysicalInfraAccount;
import com.cloupia.model.cIM.ReportContext;

/**
 * Acts as the interface between the MantlAccountDB class and the rest of the
 * system. It stores the account name and API key for that account.
 * <p>
 * Has multiple constructors allowing it to be used from almost anywhere.
 *
 * @author Matt Day
 *
 */
public class MantlAccount {

	// API key
	private String username;
	private String password;
	private String server;
	private int port;

	private MantlProxySettings proxy;

	// Account name (must be set by constructor)
	private final String accountName;

	/**
	 * Used for testing classes only - not for use in regular code
	 *
	 * @param server
	 *            Server to connect to
	 * @param username
	 *            Username
	 * @param password
	 *            Password
	 */
	@Deprecated
	public MantlAccount(String server, String username, String password) {
		this.server = server;
		this.username = username;
		this.password = password;
		this.port = 8080;
		// Set the account
		this.accountName = "Testing";
		// Set up proxy blank
		this.proxy = new MantlProxySettings(null);
	}

	/**
	 * Obtain credentials from the account name
	 * <p>
	 * For example, if a user creates an account 'Mantl-1' then this will obtain
	 * the credentials for that account
	 *
	 * @param accountName
	 *            Name of the Mantl account
	 * @throws MantlAccountException
	 *             If the account cannot be found
	 */
	public MantlAccount(String accountName) throws MantlAccountException {
		this.accountName = accountName;
		try {
			// Get the account details
			PhysicalInfraAccount acc = AccountUtil.getAccountByName(this.accountName);

			if (acc == null) {
				// Account does not exist:
				throw new MantlAccountException("Unable to find the account:" + this.accountName);
			}
			// Initialise from PhysicalInfraAccount
			this.initFromAccount(acc);
		}
		catch (Exception e) {
			throw new MantlAccountException("Unable to find the account:" + this.accountName + " " + e.getMessage());
		}
	}

	/**
	 * Each report (drop down, list of values etc) has a ReportContext. This can
	 * be used to obtain the account credentials
	 * <p>
	 * For example, if a user has two accounts (Mantl-1 and Mantl-2) and is
	 * currently looking at a table for Mantl-1 this will return those
	 * credentials
	 *
	 * @param context
	 *            Current context
	 * @throws MantlAccountException
	 *             If the account isn't found
	 */
	public MantlAccount(ReportContext context) throws MantlAccountException {
		final String contextId = context.getId();
		// If the context ID isn't null, use it to get the account name:
		this.accountName = (contextId == null) ? null : contextId.split(";")[0];

		if (this.accountName == null) {
			throw new MantlAccountException("Account not found");
		}
		try {
			PhysicalInfraAccount acc = AccountUtil.getAccountByName(this.accountName);
			if (acc == null) {
				throw new MantlAccountException("Unable to find the account:" + this.accountName);
			}
			this.initFromAccount(acc);
		}
		catch (Exception e) {
			throw new MantlAccountException("Unable to find the account:" + this.accountName + " " + e.getMessage());
		}

	}

	// Use the internal account JSON to obtain them as native Java objects:

	private void initFromAccount(PhysicalInfraAccount acc) throws Exception {
		// Get account information as a JSON query:
		String json = acc.getCredential();
		// Parse using MantlAccountJsonObject as a template:
		MantlAccountJsonObject account = (MantlAccountJsonObject) JSON.jsonToJavaObject(json,
				MantlAccountJsonObject.class);

		// Set API key:
		this.server = account.getServerAddress();
		this.username = account.getUsername();
		this.password = account.getPassword();
		// Set proxy
		this.proxy = new MantlProxySettings(account);
	}

	/**
	 * Get the account name for an account
	 *
	 * @return Account Name
	 */
	public String getAccountName() {
		return this.accountName;
	}

	/**
	 * Get Proxy information for this account
	 *
	 * @return Proxy info
	 */
	public MantlProxySettings getProxy() {
		return this.proxy;
	}

	/**
	 * Set Proxy information for this account
	 *
	 * @param proxySettings
	 *            proxy settings to use
	 */
	public void setProxy(MantlProxySettings proxySettings) {
		this.proxy = proxySettings;
	}

	/**
	 * Get Mantl username
	 *
	 * @return mantl username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Get Mantl password
	 *
	 * @return Mantl password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Get Mantl server
	 *
	 * @return Mantl server
	 */
	public String getServer() {
		return this.server;
	}

	/**
	 * Get Mantl port
	 *
	 * @return Mantl TCP port
	 */
	public int getPort() {
		return this.port;
	}

}
