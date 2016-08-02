
package com.cisco.ukidcv.mantl.api.rest.app;

/**
 * Java implementation of the Marathon JSON API.
 * <p>
 * Please see the Marathon documentation for detail on implementation.
 *
 * @author Matt Day
 *
 */
public class HealthCheck {

	private String path;
	private String protocol;
	private int portIndex;
	private int gracePeriodSeconds;
	private int intervalSeconds;
	private int timeoutSeconds;
	private int maxConsecutiveFailures;
	private boolean ignoreHttp1xx;

	/**
	 * 
	 * @return The path
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * 
	 * @param path
	 *            The path
	 */
	public void setPath(String path) {
		this.path = path;
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
	 * @return The portIndex
	 */
	public int getPortIndex() {
		return this.portIndex;
	}

	/**
	 * 
	 * @param portIndex
	 *            The portIndex
	 */
	public void setPortIndex(int portIndex) {
		this.portIndex = portIndex;
	}

	/**
	 * 
	 * @return The gracePeriodSeconds
	 */
	public int getGracePeriodSeconds() {
		return this.gracePeriodSeconds;
	}

	/**
	 * 
	 * @param gracePeriodSeconds
	 *            The gracePeriodSeconds
	 */
	public void setGracePeriodSeconds(int gracePeriodSeconds) {
		this.gracePeriodSeconds = gracePeriodSeconds;
	}

	/**
	 * 
	 * @return The intervalSeconds
	 */
	public int getIntervalSeconds() {
		return this.intervalSeconds;
	}

	/**
	 * 
	 * @param intervalSeconds
	 *            The intervalSeconds
	 */
	public void setIntervalSeconds(int intervalSeconds) {
		this.intervalSeconds = intervalSeconds;
	}

	/**
	 * 
	 * @return The timeoutSeconds
	 */
	public int getTimeoutSeconds() {
		return this.timeoutSeconds;
	}

	/**
	 * 
	 * @param timeoutSeconds
	 *            The timeoutSeconds
	 */
	public void setTimeoutSeconds(int timeoutSeconds) {
		this.timeoutSeconds = timeoutSeconds;
	}

	/**
	 * 
	 * @return The maxConsecutiveFailures
	 */
	public int getMaxConsecutiveFailures() {
		return this.maxConsecutiveFailures;
	}

	/**
	 * 
	 * @param maxConsecutiveFailures
	 *            The maxConsecutiveFailures
	 */
	public void setMaxConsecutiveFailures(int maxConsecutiveFailures) {
		this.maxConsecutiveFailures = maxConsecutiveFailures;
	}

	/**
	 * 
	 * @return The ignoreHttp1xx
	 */
	public boolean isIgnoreHttp1xx() {
		return this.ignoreHttp1xx;
	}

	/**
	 * 
	 * @param ignoreHttp1xx
	 *            The ignoreHttp1xx
	 */
	public void setIgnoreHttp1xx(boolean ignoreHttp1xx) {
		this.ignoreHttp1xx = ignoreHttp1xx;
	}

}
