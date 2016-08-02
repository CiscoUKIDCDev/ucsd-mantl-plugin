/*******************************************************************************
 * Copyright (c) 2016 Cisco and/or its affiliates
 * @author Matt Day
 *
 * Unless explicitly stated otherwise all files in this repository are licensed
 * under the Apache Software License 2.0
 *******************************************************************************/
package com.cisco.ukidcv.mantl.account;

import java.util.ArrayList;
import java.util.List;

import com.cisco.ukidcv.mantl.api.MantlApi;
import com.cisco.ukidcv.mantl.constants.MantlConstants;
import com.cisco.ukidcv.mantl.exceptions.MantlAccountException;
import com.cloupia.model.cIM.ConvergedStackComponentDetail;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reports.contextresolve.ConvergedStackComponentBuilderIf;

/**
 * Implements the converged stack view - all the information on the right hand
 * side when you single-click the icon
 *
 * @author Matt Day
 *
 */
public class MantlConvergedStackBuilder implements ConvergedStackComponentBuilderIf {
	/**
	 * Overridden method from SDK
	 *
	 * @param contextId
	 *            account context Id
	 *
	 * @return returns ConvergedStackComponentDetail instance
	 */
	@Override
	public ConvergedStackComponentDetail buildConvergedStackComponent(String contextId)
			throws MantlAccountException, Exception {
		String accountName = null;
		if (contextId != null) {
			// As the contextId returns as: "account Name;POD Name"
			accountName = contextId.split(";")[0];
		}
		if (accountName == null) {
			throw new MantlAccountException("Unable to find the account name");
		}

		MantlAccount account = new MantlAccount(accountName);

		// Test connectivity to cloud
		boolean ok = false;
		try {
			// Check we can reach the Mantl API:
			if (MantlApi.testConnection(account)) {
				ok = true;
			}
		}
		catch (@SuppressWarnings("unused") Exception e) {
			// Do nothing; status is already 'false'
		}

		// This builds the detail view on the right side of the converged view
		final ConvergedStackComponentDetail detail = new ConvergedStackComponentDetail();

		// Set some attributes there:
		detail.setModel(MantlConstants.INFRA_ACCOUNT_LABEL);
		detail.setOsVersion(MantlConstants.API_VERSION);
		detail.setVendorLogoUrl("/app/uploads/openauto/cloud.png");
		detail.setMgmtIPAddr(account.getServer());
		detail.setStatus(ok ? "OK" : "Down");
		detail.setVendorName("Mantl");
		detail.setIconUrl("/app/uploads/openauto/cloud.png");

		// setting account context type (required by UCSD...)
		detail.setContextType(
				ReportContextRegistry.getInstance().getContextByName(MantlConstants.INFRA_ACCOUNT_TYPE).getType());

		// Pass conext type to all reports:
		detail.setContextValue(contextId);
		// Not sure what '3' is here, guessing it's storage
		detail.setLayerType(3);

		// You can add arbitrary fields to this view like this:
		@SuppressWarnings("unused")
		List<String> detailList = new ArrayList<>(2);

		// detailList.add("User, " +
		// MantlInventory.getMe(account).getDisplayName());

		// detailList.add("Created, " +
		// MantlInventory.getMe(account).getCreated());

		// detail.setComponentSummaryList(detailList);

		return detail;
	}

}
