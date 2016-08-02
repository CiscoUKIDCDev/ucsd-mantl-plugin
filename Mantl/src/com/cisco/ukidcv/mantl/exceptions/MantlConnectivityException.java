/*******************************************************************************
 * Copyright (c) 2016 Cisco and/or its affiliates
 * @author Matt Day
 *
 * Unless explicitly stated otherwise all files in this repository are licensed
 * under the Apache Software License 2.0
 *******************************************************************************/
package com.cisco.ukidcv.mantl.exceptions;

/**
 * Thrown if there are any connectivity related problems.
 * <p>
 * Designed to be catchable if the server cannot be connected to for whatever
 * reason
 *
 * @author Matt Day
 *
 */
public class MantlConnectivityException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Throw new account exception
	 *
	 * @param args
	 */
	public MantlConnectivityException(String args) {
		super(args);
	}
}
