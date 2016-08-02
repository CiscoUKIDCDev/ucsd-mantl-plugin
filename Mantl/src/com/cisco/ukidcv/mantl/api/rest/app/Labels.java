
package com.cisco.ukidcv.mantl.api.rest.app;

/**
 * Java implementation of the Marathon JSON API.
 * <p>
 * Please see the Marathon documentation for detail on implementation.
 *
 * @author Matt Day
 *
 */
public class Labels {

	private String _new;
	private String label;

	/**
	 * 
	 * @return The _new
	 */
	public String getNew() {
		return this._new;
	}

	/**
	 * 
	 * @param _new
	 *            The New
	 */
	public void setNew(String _new) {
		this._new = _new;
	}

	/**
	 * 
	 * @return The label
	 */
	public String getLabel() {
		return this.label;
	}

	/**
	 * 
	 * @param label
	 *            The Label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

}
