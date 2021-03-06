/*******************************************************************************
 * Copyright (c) 2016 Cisco and/or its affiliates
 * @author Matt Day
 *
 * Unless explicitly stated otherwise all files in this repository are licensed
 * under the Apache Software License 2.0
 *******************************************************************************/
package com.cisco.ukidcv.mantl.account.handler;

import org.apache.log4j.Logger;

import com.cisco.ukidcv.mantl.account.MantlAccount;
import com.cisco.ukidcv.mantl.api.MantlApi;
import com.cisco.ukidcv.mantl.constants.MantlConstants;
import com.cisco.ukidcv.mantl.exceptions.MantlAccountException;
import com.cloupia.lib.connector.account.AccountUtil;
import com.cloupia.lib.connector.account.PhysicalConnectivityStatus;
import com.cloupia.lib.connector.account.PhysicalConnectivityTestHandler;
import com.cloupia.lib.connector.account.PhysicalInfraAccount;

/**
 * Used to test if the account is available and if the credentials work when
 * it's being created
 *
 * @author Matt Day
 *
 */
public class MantlConnectionHandler extends PhysicalConnectivityTestHandler {
	static Logger logger = Logger.getLogger(PhysicalConnectivityTestHandler.class);

	@Override
	public PhysicalConnectivityStatus testConnection(String accountName) throws Exception {

		PhysicalInfraAccount infraAccount = AccountUtil.getAccountByName(accountName);
		PhysicalConnectivityStatus status = new PhysicalConnectivityStatus(infraAccount);

		status.setConnectionOK(false);
		if (infraAccount != null) {
			if (infraAccount.getAccountType() != null) {
				if (infraAccount.getAccountType().equals(MantlConstants.INFRA_ACCOUNT_TYPE)) {
					logger.debug("Testing connectivity for account " + accountName);

					MantlAccount account = new MantlAccount(accountName);
					try {
						// Check we can reach the Mantl API:
						if (MantlApi.testConnection(account)) {
							status.setConnectionOK(true);
						}
						logger.debug("Token acquired - connection verified");

						MantlAccountStatusSummary.accountSummary(accountName);

					}
					catch (@SuppressWarnings("unused") MantlAccountException e) {
						logger.warn("Couldn't get token from system - probably invalid credentials");
						status.setConnectionOK(false);
						status.setErrorMsg("Could not obtain mantl report (check credentials)");
						return status;
					}
					catch (@SuppressWarnings("unused") Exception e) {
						logger.warn("Exception raised testing connection - probably wrong IP address or unreachable");
						// Didn't get a token
						status.setConnectionOK(false);
						status.setErrorMsg("Could not connect to mantl service (check proxy settings)");
						return status;
					}

				}
			}

		}
		return status;
	}

}
