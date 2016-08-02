
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
public class Container {

	private String type;
	private List<Volume> volumes = new ArrayList<>();
	private Docker docker;

	/**
	 *
	 * @return The type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 *
	 * @param type
	 *            The type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 *
	 * @return The volumes
	 */
	public List<Volume> getVolumes() {
		return this.volumes;
	}

	/**
	 *
	 * @param volumes
	 *            The volumes
	 */
	public void setVolumes(List<Volume> volumes) {
		this.volumes = volumes;
	}

	/**
	 *
	 * @return The docker
	 */
	public Docker getDocker() {
		return this.docker;
	}

	/**
	 *
	 * @param docker
	 *            The docker
	 */
	public void setDocker(Docker docker) {
		this.docker = docker;
	}

}
