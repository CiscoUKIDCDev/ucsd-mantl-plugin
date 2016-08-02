
package com.cisco.ukidcv.mantl.api.rest.app;

/**
 * Java implementation of the Marathon JSON API.
 * <p>
 * Please see the Marathon documentation for detail on implementation.
 *
 * @author Matt Day
 *
 */
public class VersionInfo {

	private String lastScalingAt;
	private String lastConfigChangeAt;

	/**
	 * 
	 * @return The lastScalingAt
	 */
	public String getLastScalingAt() {
		return this.lastScalingAt;
	}

	/**
	 * 
	 * @param lastScalingAt
	 *            The lastScalingAt
	 */
	public void setLastScalingAt(String lastScalingAt) {
		this.lastScalingAt = lastScalingAt;
	}

	/**
	 * 
	 * @return The lastConfigChangeAt
	 */
	public String getLastConfigChangeAt() {
		return this.lastConfigChangeAt;
	}

	/**
	 * 
	 * @param lastConfigChangeAt
	 *            The lastConfigChangeAt
	 */
	public void setLastConfigChangeAt(String lastConfigChangeAt) {
		this.lastConfigChangeAt = lastConfigChangeAt;
	}

}
