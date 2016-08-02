
package com.cisco.ukidcv.mantl.api.rest.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Java implementation of the Marathon JSON API.
 * <p>
 * Please see the Marathon documentation for detail on implementation.
 *
 * @author Matt Day
 *
 */

public class MantlApp {

	private List<App> apps = new ArrayList<>();

	/**
	 *
	 * @return The apps
	 */
	public List<App> getApps() {
		return this.apps;
	}

	/**
	 *
	 * @param apps
	 *            The apps
	 */
	public void setApps(List<App> apps) {
		this.apps = apps;
	}

}
