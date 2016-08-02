/*******************************************************************************
 * Copyright (c) 2016 Cisco and/or its affiliates
 * @author Matt Day
 *
 * Unless explicitly stated otherwise all files in this repository are licensed
 * under the Apache Software License 2.0
 *******************************************************************************/
package com.cisco.ukidcv.mantl.account;

/**
 * UCS Director stores account information in JSON format.
 * <p>
 * This class is used to represent that back as Java. Each item you store in
 * your account needs to be represented here.
 * <p>
 * This occurs in MantlAccount but can also be extracted by using
 * acc.getCredential();
 * <p>
 * Not javadoc'd as the values are documented in the Database
 *
 * @author Matt Day
 * @see com.cisco.ukidcv.mantl.account.MantlReportDB
 *
 */
@SuppressWarnings("javadoc")
public class MantlAccountJsonObject {
	private String account;
	private String serverAddress;
	private String username;
	private String password;
	private int tcpPort;
	private boolean proxy;
	private String proxyServer;
	private int proxyPort;
	private boolean proxyAuth;
	private String proxyUser;
	private String proxyPass;

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public boolean isProxy() {
		return this.proxy;
	}

	public void setProxy(boolean proxy) {
		this.proxy = proxy;
	}

	public String getProxyServer() {
		return this.proxyServer;
	}

	public void setProxyServer(String proxyServer) {
		this.proxyServer = proxyServer;
	}

	public int getProxyPort() {
		return this.proxyPort;
	}

	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}

	public boolean isProxyAuth() {
		return this.proxyAuth;
	}

	public void setProxyAuth(boolean proxyAuth) {
		this.proxyAuth = proxyAuth;
	}

	public String getProxyUser() {
		return this.proxyUser;
	}

	public void setProxyUser(String proxyUser) {
		this.proxyUser = proxyUser;
	}

	public String getProxyPass() {
		return this.proxyPass;
	}

	public void setProxyPass(String proxyPass) {
		this.proxyPass = proxyPass;
	}

	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTcpPort() {
		return tcpPort;
	}

	public void setTcpPort(int tcpPort) {
		this.tcpPort = tcpPort;
	}
	
	

}
