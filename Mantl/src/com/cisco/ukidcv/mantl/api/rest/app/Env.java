
package com.cisco.ukidcv.mantl.api.rest.app;

/**
 * Java implementation of the Marathon JSON API.
 * <p>
 * Please see the Marathon documentation for detail on implementation.
 *
 * @author Matt Day
 *
 */
public class Env {

	private String lDLIBRARYPATH;

	/**
	 * 
	 * @return The lDLIBRARYPATH
	 */
	public String getLDLIBRARYPATH() {
		return this.lDLIBRARYPATH;
	}

	/**
	 * 
	 * @param lDLIBRARYPATH
	 *            The LD_LIBRARY_PATH
	 */
	public void setLDLIBRARYPATH(String lDLIBRARYPATH) {
		this.lDLIBRARYPATH = lDLIBRARYPATH;
	}

}
