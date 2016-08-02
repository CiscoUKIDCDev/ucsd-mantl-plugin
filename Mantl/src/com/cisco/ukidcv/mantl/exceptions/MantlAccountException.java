/*******************************************************************************
 * Copyright (c) 2016 Cisco and/or its affiliates
 * @author Matt Day
 *
 * Unless explicitly stated otherwise all files in this repository are licensed
 * under the Apache Software License 2.0
 *******************************************************************************/
package com.cisco.ukidcv.mantl.exceptions;

/**
 * Thrown if there are any account related problems.
 * <p>
 * Designed to be catchable if an account name cannot be found
 *
 * @author Matt Day
 *
 */
public class MantlAccountException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Throw new account exception
	 * 
	 * @param args
	 */
	public MantlAccountException(String args) {
		super(args);
	}
}
