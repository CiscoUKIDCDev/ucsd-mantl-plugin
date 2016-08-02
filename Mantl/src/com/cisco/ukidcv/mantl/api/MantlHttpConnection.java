/*******************************************************************************
 * Copyright (c) 2016 Cisco and/or its affiliates
 * @author Matt Day
 *
 * Unless explicitly stated otherwise all files in this repository are licensed
 * under the Apache Software License 2.0
 *******************************************************************************/
package com.cisco.ukidcv.mantl.api;

import com.cisco.ukidcv.mantl.account.MantlAccount;
import com.cisco.ukidcv.mantl.account.MantlProxySettings;
import com.cisco.ukidcv.ucsd.http.UcsdHttpConnection;

/**
 * Handles communication to the Mantl servers.
 * <p>
 * Extends tue UcsdHttpConnection library to handle most tasks
 *
 * @author Matt Day
 * @see UcsdHttpConnection
 *
 */
public class MantlHttpConnection extends UcsdHttpConnection {

	/**
	 * Create a connection to the Mantl API using the specified account.
	 * <p>
	 * Automatically configures the token and proxy settings.
	 *
	 * @param account
	 *            Account from which to connect
	 * @param path
	 *            path to request to (e.g. /v1/rooms)
	 * @param method
	 *            Method to use (i.e. GET, POST, DELETE, PUT)
	 */
	public MantlHttpConnection(MantlAccount account, String path, httpMethod method) {
		super();

		this.setServer(account.getServer());

		// Set the URI and method to the Mantl Server
		this.setUri(path, method);

		this.setProtocol(httpProtocol.HTTPS, account.getPort());

		// Add Mantl token
		// this.setHeader("Authorization", account.getApiKey());

		this.setHeader("Content-type", "application/json; charset=utf-8");

		// Do we need a proxy?
		this.setProxy(account.getProxy());
	}

	/**
	 * Add mantl account proxy settings
	 *
	 * @param proxy
	 *            Proxy settings
	 */
	public void setProxy(MantlProxySettings proxy) {
		// If the proxy is set:
		if (proxy.isEnabled()) {
			// Proxy auth is handled like http authentication
			if (proxy.isProxyAuth()) {
				this.setProxy(proxy.getProxyServer(), proxy.getProxyPort(), proxy.getProxyUser(), proxy.getProxyPass());
			}
			else {
				this.setProxy(proxy.getProxyServer(), proxy.getProxyPort());
			}
		}
	}
}
