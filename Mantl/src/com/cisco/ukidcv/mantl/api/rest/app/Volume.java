
package com.cisco.ukidcv.mantl.api.rest.app;

/**
 * Java implementation of the Marathon JSON API.
 * <p>
 * Please see the Marathon documentation for detail on implementation.
 *
 * @author Matt Day
 *
 */
public class Volume {

	private String containerPath;
	private String hostPath;
	private String mode;

	/**
	 * 
	 * @return The containerPath
	 */
	public String getContainerPath() {
		return this.containerPath;
	}

	/**
	 * 
	 * @param containerPath
	 *            The containerPath
	 */
	public void setContainerPath(String containerPath) {
		this.containerPath = containerPath;
	}

	/**
	 * 
	 * @return The hostPath
	 */
	public String getHostPath() {
		return this.hostPath;
	}

	/**
	 * 
	 * @param hostPath
	 *            The hostPath
	 */
	public void setHostPath(String hostPath) {
		this.hostPath = hostPath;
	}

	/**
	 * 
	 * @return The mode
	 */
	public String getMode() {
		return this.mode;
	}

	/**
	 * 
	 * @param mode
	 *            The mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

}
