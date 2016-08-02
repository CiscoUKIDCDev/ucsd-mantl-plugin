
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
public class Docker {

	private String image;
	private String network;
	private List<PortMapping> portMappings = new ArrayList<>();
	private boolean privileged;
	private boolean forcePullImage;

	/**
	 *
	 * @return The image
	 */
	public String getImage() {
		return this.image;
	}

	/**
	 *
	 * @param image
	 *            The image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 *
	 * @return The network
	 */
	public String getNetwork() {
		return this.network;
	}

	/**
	 *
	 * @param network
	 *            The network
	 */
	public void setNetwork(String network) {
		this.network = network;
	}

	/**
	 *
	 * @return The portMappings
	 */
	public List<PortMapping> getPortMappings() {
		return this.portMappings;
	}

	/**
	 *
	 * @param portMappings
	 *            The portMappings
	 */
	public void setPortMappings(List<PortMapping> portMappings) {
		this.portMappings = portMappings;
	}

	/**
	 *
	 * @return The privileged
	 */
	public boolean isPrivileged() {
		return this.privileged;
	}

	/**
	 *
	 * @param privileged
	 *            The privileged
	 */
	public void setPrivileged(boolean privileged) {
		this.privileged = privileged;
	}

	/**
	 *
	 * @return The forcePullImage
	 */
	public boolean isForcePullImage() {
		return this.forcePullImage;
	}

	/**
	 *
	 * @param forcePullImage
	 *            The forcePullImage
	 */
	public void setForcePullImage(boolean forcePullImage) {
		this.forcePullImage = forcePullImage;
	}

}
