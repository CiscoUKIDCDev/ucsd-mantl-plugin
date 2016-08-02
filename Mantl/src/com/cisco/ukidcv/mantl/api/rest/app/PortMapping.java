
package com.cisco.ukidcv.mantl.api.rest.app;

/**
 * Java implementation of the Marathon JSON API.
 * <p>
 * Please see the Marathon documentation for detail on implementation.
 *
 * @author Matt Day
 *
 */
public class PortMapping {

	private int containerPort;
	private int hostPort;
	private int servicePort;
	private String protocol;
	private Labels labels;

	/**
	 * 
	 * @return The containerPort
	 */
	public int getContainerPort() {
		return this.containerPort;
	}

	/**
	 * 
	 * @param containerPort
	 *            The containerPort
	 */
	public void setContainerPort(int containerPort) {
		this.containerPort = containerPort;
	}

	/**
	 * 
	 * @return The hostPort
	 */
	public int getHostPort() {
		return this.hostPort;
	}

	/**
	 * 
	 * @param hostPort
	 *            The hostPort
	 */
	public void setHostPort(int hostPort) {
		this.hostPort = hostPort;
	}

	/**
	 * 
	 * @return The servicePort
	 */
	public int getServicePort() {
		return this.servicePort;
	}

	/**
	 * 
	 * @param servicePort
	 *            The servicePort
	 */
	public void setServicePort(int servicePort) {
		this.servicePort = servicePort;
	}

	/**
	 * 
	 * @return The protocol
	 */
	public String getProtocol() {
		return this.protocol;
	}

	/**
	 * 
	 * @param protocol
	 *            The protocol
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	/**
	 * 
	 * @return The labels
	 */
	public Labels getLabels() {
		return this.labels;
	}

	/**
	 * 
	 * @param labels
	 *            The labels
	 */
	public void setLabels(Labels labels) {
		this.labels = labels;
	}

}
