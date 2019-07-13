package com.ssa.state.util;

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
	public static final String BASE_PACKAGE = "com.ssa.state";
	/**
	 * Resource Package name containing RestController
	 */
	public static final String RESOURCE_PACKAGE = "com.ssa.state.resource";

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
	 * Email exists value
	 */
	public static final String EMAIL_EXISTS = "Email Already Exists";
	/**
	 * Email ok value
	 */
	public static final String EMAIL_OK = "OK";

	/**
	 * Model Attributes Keys
	 */
	/**
	 * Model attribute key for Genders
	 */
	public static final String GENDERS_MODEL_KEY = "genders";
	/**
	 * Model attribute key for roles
	 */
	public static final String ROLES_MODEL_KEY = "roles";
	
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
	 * Registration success redirect attribute key
	 */
	public static final String ACC_REG_REDIRECT_KEY = "msg";

	

	/**
	 * Request Attribute flash values
	 */
	/**
	 * Registration success redirect attribute value
	 */
	public static final String ACC_REG_SUCCESS_REDIRECT_VALUE = "Account Registration Successful...";

	/**
	 * Registration fail redirect attribute value
	 */
	public static final String ACC_REG_FAIL_REDIRECT_VALUE = "Account Registration Fail...";

	/**
	 * Request Mapping urls
	 */
	/**
	 * Show account add form url
	 */
	public static final String SHOW_ACC_FORM_GET_URL = "/show_account";
	/**
	 * Account user in DB url
	 */
	public static final String SAVE_ACC_POST_URL = "/save_account";
	/**
	 * Show all account form 
	 */
	public static final String SHOW_ALL_ACC_GET_URL = "/showall";
	/**
	 * Send all user form DB url
	 */
	public static final String SEND_ALL_ACCOUNTS_GET_URL="/getlist";
	
	/**
	 * Show email Page
	 */
	public static final String SHOW_EMAIL_GET_URL="/show_email";
	
	/**
	 * Check email availability Url
	 */
	public static final String CHECK_EMAIL_GET_URL="/check_email";
	
	/**
	 * Request redirect Urls
	 */

	/**
	 * Redirects to Show user enroll form
	 */
	public static final String REDIRECT_SHOW_ACC_FORM_GET_URL = "redirect:/show_account";

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
	 * Register user form view name
	 */
	public static final String ACC_VIEW = "account";
	/**
	 * Email form view name
	 */
	public static final String EMAIL_VIEW = "email";
	/**
	 * Show all user form view name
	 */
	public static final String ALL_ACC_VIEW = "show_accounts";
	/**
	 * Error view name
	 */
	public static final String ERROR_VIEW = "error";

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
