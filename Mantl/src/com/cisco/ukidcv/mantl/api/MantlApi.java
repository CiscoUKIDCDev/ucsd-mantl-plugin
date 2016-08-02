/*******************************************************************************
 * Copyright (c) 2016 Cisco and/or its affiliates
 *
 * Unless explicitly stated otherwise all files in this repository are licensed
 * under the Apache Software License 2.0
 * @author Matt Day
 *******************************************************************************/
package com.cisco.ukidcv.mantl.api;

import com.cisco.ukidcv.mantl.account.MantlAccount;

public class MantlApi {

	// FIXME - always returns true!
	public static boolean testConnection(MantlAccount account) {
		return true;
	}
}
