/*******************************************************************************
 * Copyright (c) 2016 Cisco and/or its affiliates
 * @author Matt Day
 *
 * Unless explicitly stated otherwise all files in this repository are licensed
 * under the Apache Software License 2.0
 *******************************************************************************/
/**
 *
 */
package com.cisco.ukidcv.mantl.account;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.lib.connector.account.AbstractInfraAccount;
import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.model.cIM.InfraAccount;
import com.cloupia.service.cIM.inframgr.collector.view2.ConnectorCredential;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;

/**
 * This class stores account details in the UCS Director database.
 * <p>
 *
 * It also indirectly provides the form to create the account with the JDO
 * notation below. The form fields are shown when the user creates a new
 * physical account
 * <p>
 * <b>Note:</b> You should ensure there's a jdo.files entry present and that it
 * contains this class name
 *
 * @author Matt Day
 *
 */
@PersistenceCapable(detachable = "true", table = "Mantl_account_v2")
public class MantlAccountDB extends AbstractInfraAccount implements ConnectorCredential {

	// Log entries for debugging purposes
	static Logger logger = Logger.getLogger(MantlAccountDB.class);

	@Persistent
	private boolean isCredentialPolicy = false;

	@Persistent
	@FormField(label = "Server Address", help = "Mantl Server Address", mandatory = true)
	private String serverAddress;

	@Persistent
	@FormField(label = "TCP Port", help = "TCP Port (default is 8080)", mandatory = true)
	private int tcpPort;

	@Persistent
	@FormField(label = "Username", help = "Username", mandatory = true)
	private String username;

	@Persistent
	@FormField(label = "Password", help = "Password", mandatory = true, type = FormFieldDefinition.FIELD_TYPE_PASSWORD)
	private String password;

	// Input field for proxy:
	@Persistent
	@FormField(label = "Use a proxy", help = "Check if you need to use a proxy to access the internet", type = FormFieldDefinition.FIELD_TYPE_BOOLEAN)
	private boolean proxy;

	// Input field for proxy server:
	@Persistent
	@FormField(label = "Proxy Server", help = "Proxy server to use (leave blank if none)", mandatory = false)
	private String proxyServer;

	// Input field for proxy port:
	@Persistent
	@FormField(label = "Proxy Port", help = "Proxy port to use (leave blank if none)", mandatory = false)
	private int proxyPort;

	// Input field for proxy auth:
	@Persistent
	@FormField(label = "Proxy Authentication", help = "Check if your proxy server requires authentication", mandatory = false, type = FormFieldDefinition.FIELD_TYPE_BOOLEAN)
	private boolean proxyAuth;

	// Input field for proxy username:
	@Persistent
	@FormField(label = "Proxy Username", help = "Proxy username (leave blank if none)", mandatory = false)
	private String proxyUser;

	// Input field for proxy password:
	@Persistent
	@FormField(label = "Proxy Password", help = "Proxy password (leave blank if none)", type = FormFieldDefinition.FIELD_TYPE_PASSWORD, mandatory = false)
	private String proxyPass;

	/**
	 * Initialises the account DB. You should use this to set any defaults
	 * (don't set them above as it will break persistency).
	 */
	public MantlAccountDB() {
		super();
		// Set the default user to admin
		this.username = "admin";
		// Set the https port to 443 by default in the GUI
		this.tcpPort = 8080;
		// Set the proxy port to 80 by default in the GUI
		this.proxyPort = 80;
	}

	/*
	 * Treat the API key as a password
	 */
	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * No policy for this plugin, so return null:
	 */
	@Override
	public String getPolicy() {
		return null;
	}

	@Override
	public void setPolicy(String arg0) {
		// Do nothing - not implemented

	}

	/*
	 * Credential policies are out of the scope of this plugin, so they are not
	 * implemented
	 */
	@Override
	public boolean isCredentialPolicy() {
		return false;
	}

	@Override
	public void setCredentialPolicy(boolean arg0) {
		// Do nothing - not implemented
	}

	/*
	 * Plugins let you set the TCP port you wish to connect to - not needed for
	 * this either
	 */
	@Override
	public void setPort(int tcpPort) {
		this.tcpPort = tcpPort;

	}

	/*
	 * We're not using usernames - just a simple API key
	 */
	@Override
	public void setUsername(String username) {
		this.username = username;

	}

	/**
	 * Set the following to avoid UCSD showing 'null' under server/filer
	 * (cosmetic)
	 *
	 * @return Mantl API IP Address
	 */
	@Override
	public String getServerAddress() {
		return this.serverAddress;
	}

	@Override
	public String getServer() {
		return this.serverAddress;
	}

	@Override
	public String getServerIp() {
		return this.serverAddress;
	}

	/*
	 * Stores the account in the database
	 */
	@Override
	public InfraAccount toInfraAccount() {
		try {
			// Create an object store
			ObjStore<InfraAccount> store = ObjStoreHelper.getStore(InfraAccount.class);
			// Generate a simple query to store:
			String cquery = "serverAddress == '" + this.serverAddress + "' && tcpPort == " + this.tcpPort
					+ " && username == '" + this.username + "' && password == '" + this.password + "' && proxy == "
					+ this.proxy + " && proxyPass == '" + this.proxyPass + "' && proxyUser == '" + this.proxyUser
					+ "' && proxyPort == " + this.proxyPort + " && proxyServer == '" + this.proxyServer
					+ "' && proxyAuth == " + this.proxyAuth;
			List<InfraAccount> accList = store.query(cquery);
			if ((accList != null) && (accList.size() > 0)) {
				return accList.get(0);
			}
			return null;

		}
		catch (Exception e) {
			logger.error("Exception while mapping DeviceCredential to InfraAccount for server: " + this.serverAddress
					+ ": " + e.getMessage());
		}

		return null;
	}

}
