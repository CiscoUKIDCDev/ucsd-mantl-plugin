/*******************************************************************************
 * Copyright (c) 2016 Cisco and/or its affiliates
 * @author Matt Day
 *
 * Unless explicitly stated otherwise all files in this repository are licensed
 * under the Apache Software License 2.0
 *******************************************************************************/
package com.cisco.ukidcv.mantl.exceptions;

/**
 * Thrown if there are any mantl report related problems.
 * <p>
 * Designed to be catchable if a mantl report fails to be returned from the
 * server
 *
 * @author Matt Day
 *
 */
public class MantlReportException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Throw new account exception
	 *
	 * @param args
	 */
	public MantlReportException(String args) {
		super(args);
	}
}
