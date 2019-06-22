package com.ssa.util;

/**
 * ALL String literals
 * 
 * @author VISHAL
 *
 */
public class ConstantUtils {// NOPMD
	/**
	 * Package Constants
	 */

	/**
	 * Base Package name
	 */
	public static final String BASE_PACKAGE = "com.ssa";
	/**
	 * Resource Package name containing RestController
	 */
	public static final String RESOURCE_PACKAGE = "com.ssa.resource";

	/**
	 * Value constants
	 */
	/**
	 * Male value
	 */
	public static final String MALE = "Male";
	/**
	 * Fe-Male value
	 */
	public static final String FEMALE = "Female";

	/**
	 * Model Attributes Keys
	 */
	/**
	 * Model attribute key for Genders
	 */
	public static final String GENDERS_MODEL_KEY = "genders";
	/**
	 * Model attribute key for User
	 */
	public static final String USER_MODEL_KEY = "user";
	/**
	 * Model attribute key for state
	 */
	public static final String STATES_MODEL_KEY = "states";
	/**
	 * Model attribute key for user list
	 */
	public static final String USER_LIST_MODEL_KEY = "userModelList";

	/**
	 * Request Attribute flash keys
	 */
	/**
	 * Enroll success redirect attribute key
	 */
	public static final String ENROLL_SUCCESS_REDIRECT_KEY = "msg";

	/**
	 * Request Attribute flash values
	 */
	/**
	 * Enroll success redirect attribute value
	 */
	public static final String ENROLL_SUCCESS_REDIRECT_VALUE = "SSN Enrollment completed successfully with ";

	/**
	 * Request Mapping urls
	 */
	/**
	 * Show user enroll form url
	 */
	public static final String ENROLL_GET_URL = "/register";
	/**
	 * Enroll user in DB url
	 */
	public static final String ENROLL_POST_URL = "/enroll";
	/**
	 * Show all user form DB url
	 */
	public static final String SHOW_USERS_GET_URL = "/showall";

	/**
	 * Request redirect Urls
	 */
	/**
	 * Redirects to Show user enroll form
	 */
	public static final String REDIRECT_ENROLL_GET_URL = "redirect:/register";

	/**
	 * Rest Controller urls
	 */
	/**
	 * Get state by using ssn as a PathVariable
	 */
	public static final String GET_STATE_GET_URL = "/getState/{ssn}";

	/**
	 * Logical views
	 */
	/**
	 * Enroll user form view name
	 */
	public static final String ENROLL_VIEW = "ssnreg";
	/**
	 * Show all user form view name
	 */
	public static final String SHOW_USERS_VIEW = "showallusers";
	/**
	 * Error view name
	 */
	public static final String ERROR_VIEW = "ssnerror";

	/**
	 * Request param names
	 */
	/**
	 * Photo file request parameter
	 */
	public static final String PHOTO_FILE_REQUEST_PARAM = "photoFile";

	/**
	 * Exception keys
	 */
	/**
	 * Key to store exception
	 */
	public static final String EXC_KEY = "errMsg";

}
