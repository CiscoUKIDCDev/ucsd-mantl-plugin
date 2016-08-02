
package com.cisco.ukidcv.mantl.api.rest.app;

/**
 * Java implementation of the Marathon JSON API.
 * <p>
 * Please see the Marathon documentation for detail on implementation.
 *
 * @author Matt Day
 *
 */
public class UpgradeStrategy {

	private int minimumHealthCapacity;
	private int maximumOverCapacity;

	/**
	 * 
	 * @return The minimumHealthCapacity
	 */
	public int getMinimumHealthCapacity() {
		return this.minimumHealthCapacity;
	}

	/**
	 * 
	 * @param minimumHealthCapacity
	 *            The minimumHealthCapacity
	 */
	public void setMinimumHealthCapacity(int minimumHealthCapacity) {
		this.minimumHealthCapacity = minimumHealthCapacity;
	}

	/**
	 * 
	 * @return The maximumOverCapacity
	 */
	public int getMaximumOverCapacity() {
		return this.maximumOverCapacity;
	}

	/**
	 * 
	 * @param maximumOverCapacity
	 *            The maximumOverCapacity
	 */
	public void setMaximumOverCapacity(int maximumOverCapacity) {
		this.maximumOverCapacity = maximumOverCapacity;
	}

}
