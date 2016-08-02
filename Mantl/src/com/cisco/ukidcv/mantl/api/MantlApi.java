/*******************************************************************************
 * Copyright (c) 2016 Cisco and/or its affiliates
 *
 * Unless explicitly stated otherwise all files in this repository are licensed
 * under the Apache Software License 2.0
 * @author Matt Day
 *******************************************************************************/
package com.cisco.ukidcv.mantl.api;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.cisco.ukidcv.mantl.account.MantlAccount;
import com.cisco.ukidcv.mantl.api.rest.app.MantlApp;
import com.cisco.ukidcv.mantl.exceptions.MantlConnectivityException;
import com.cisco.ukidcv.ucsd.http.UcsdHttpConnection.httpMethod;
import com.google.gson.Gson;

/**
 * Handles all API requests to and from the Mantl server
 *
 * @author Matt Day
 *
 */
public class MantlApi {

	// FIXME - always returns true!
	/**
	 * Test if an account can be reached and authenticated with
	 *
	 * @param account
	 * @return true if the account is accessible
	 * @throws MantlConnectivityException
	 *             Thrown if there is a problem connecting to the server
	 */
	public static boolean testConnection(MantlAccount account) throws MantlConnectivityException {
		MantlHttpConnection connection = new MantlHttpConnection(account, "/ping", httpMethod.GET);
		try {
			connection.execute();

			// If we get a 200 response, all is dandy
			if (connection.getResponseCode() == 200) {
				return true;
			}
			// Try and show better errors
			if (connection.getResponseCode() == 401) {
				throw new MantlConnectivityException("Authorization failed - check username/password");
			}
			throw new MantlConnectivityException("HTTP error connecting to server: " + connection.getResponse());
		}
		catch (@SuppressWarnings("unused") Exception e) {
			throw new MantlConnectivityException("Connection failed: Check host name and port");
		}
	}

	/**
	 * Get a list of applications deployed in Mantl
	 * <p>
	 * This method is done live from the server and not cached
	 *
	 * @param account
	 *            Mantl account
	 * @return list of applications
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static MantlApp getApps(MantlAccount account) throws ClientProtocolException, IOException {
		MantlHttpConnection connection = new MantlHttpConnection(account, "/v2/apps", httpMethod.GET);
		connection.execute();
		Gson gson = new Gson();
		return gson.fromJson(connection.getResponse(), MantlApp.class);
	}
}
