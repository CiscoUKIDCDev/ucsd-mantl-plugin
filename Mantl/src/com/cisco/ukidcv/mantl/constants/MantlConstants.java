package com.cisco.ukidcv.mantl.constants;

public class MantlConstants {

	// ======== UCSD Account Constants
	/**
	 * UCSD Internal account type. Should NOT be changed between releases or it
	 * breaks all kinds of things
	 */
	public static final String INFRA_ACCOUNT_TYPE = "MANTL";
	/**
	 * User-friendly label for this account type. This can be changed between
	 * releases.
	 */
	public static final String INFRA_ACCOUNT_LABEL = "Mantl";
	/**
	 * Accounts must have a magic number in the converged view. The docs say to
	 * use something "over 1000". Let's hope no one else uses this value!
	 */
	public static final int INFRA_ACCOUNT_MAGIC_NUMBER = 18090212;
	/**
	 * Category to put all the workflows
	 */
	public static final String WORKFLOW_CATEGORY = "Cisco Mantl";
	/**
	 * Folder to put the tasks in
	 */
	public static final String TASK_PREFIX = "Cisco Mantl Tasks";

	// ======== Mantl API Constants

	/**
	 * API Version
	 */
	public static final String API_VERSION = "1";

	// ======== Mantl Inventory constants
	/**
	 * Log message for periodic inventory updates
	 */
	public final static String INVENTORY_REASON_PERIODIC = "Periodic inventory update";
	/**
	 * Log message for initial startup
	 */
	public final static String INVENTORY_REASON_INITIAL = "Initial inventory collection";
	/**
	 * Log message for initial startup
	 */
	public final static String INVENTORY_REASON_USER = "User requested collection";

	/**
	 * Log message for initial startup
	 */
	public final static String INVENTORY_REASON_CRUD = "Performed a Mantl operation";

	/**
	 * Maximum number of log entries
	 */
	public final static int MAX_POLLING_LOG_ENTRIES = 100;

	/**
	 * Time between polling (miliseconds)
	 */
	public final static long MAX_POLLING_TIME = 9000000;
}
