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
	 * Button operation key 
	 */
	public static final String OPERATION_BUTTON_TEXT = "operation";
	/**
	 * Button operation save value 
	 */
	public static final String SAVE_TEXT = "Save";
	/**
	 * Button operation update value 
	 */
	public static final String UPDATE_TEXT = "Update";
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
	 * Model attribute key for all accounts
	 */
	public static final String ALL_ACCOUNTS_MODEL_KEY = "accountModelList";
	/**
	 * Model attribute key for all plans
	 */
	public static final String ALL_PLANS_MODEL_KEY = "planModelList";
	
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
	 * Model attribute key for plan add
	 */
	public static final String PLAN_MODEL_KEY = "plan";
	/**
	 * Model attribute key for Account model
	 */
	public static final String ACC_MODEL_KEY = "account";
	
	/**
	 * Model attribute Operation selected
	 */
	public static final String OP_SELECTED_KEY = "operationSelectedMsg";
	
	/**
	 * Request Attribute flash keys
	 */
	/**
	 * Registration success  attribute key
	 */
	public static final String ACC_REG_KEY = "msg";
	/**
	 * Update success  attribute key
	 */
	public static final String ACC_UPDATE_KEY = "msg";

	/**
	 * Registration success redirect attribute key
	 */
	public static final String PLAN_SAVE_KEY = "msg";


	/**
	 * Request Attribute flash values
	 */
	
	/**
	 * Registration success redirect attribute value
	 */
	
	public static final String ACC_REG_SUCCESS_VALUE = "Account Registration Successful...";

	/**
	 * Registration fail redirect attribute value
	 */
	public static final String ACC_REG_FAIL_VALUE = "Account Registration Fail...";

	/**
	 * Account update success redirect attribute value
	 */
	public static final String ACC_UPDATE_SUCCESS_VALUE = "Account Update Successful...";

	/**
	 * Account update fail redirect attribute value
	 */
	public static final String ACC_UPDATE_FAIL_VALUE = "Account Update Fail...";

	/**
	 * Plan Add success redirect attribute value
	 */
	public static final String PLAN_SAVE_SUCCESS_VALUE = "Plan saved Successfully...";

	/**
	 * Plan Add fail redirect attribute value
	 */
	public static final String PLAN_SAVE_FAIL_VALUE = "Plan save Failed...";

	/**
	 * Account Reg value attribute value
	 */
	public static final String ACCOUNT_REGISTRATION_VALUE = "Account Registration";
	/**
	 * Account Update value attribute value
	 */
	public static final String ACCOUNT_UPDATE_VALUE = "Account Update";
	
	/**
	 * Request Mapping urls
	 */
	/**
	 * Show account add form url
	 */
	public static final String SHOW_ACC_FORM_GET_URL = "/show_account";
	
	/**
	 * Account save user in DB url
	 */
	public static final String SAVE_ACC_POST_URL = "/save_account";
	/**
	 * Account update user in DB url
	 */
	public static final String UPDATE_ACC_POST_URL = "/update_account";
	/**
	 * Show all account form 
	 */
	public static final String SHOW_ALL_ACC_GET_URL = "/showall";
	/**
	 * Send all user form DB url
	 */
	public static final String SEND_ALL_ACCOUNTS_GET_URL="/getlist";
	/**
	 * Send all user form DB url
	 */
	public static final String SEND_ALL_ACCOUNTS_1_GET_URL="/get_account_list";
	/**
	 * Show email Page
	 */
	public static final String SHOW_EMAIL_GET_URL="/show_email";
	
	/**
	 * Check email availability Url
	 */
	public static final String CHECK_EMAIL_GET_URL="/check_email";
	/**
	 * Save plan Url
	 */
	public static final String PLAN_SAVE_POST_URL="/save_plan";
	/**
	 * Show plan add Url
	 */
	public static final String SHOW_PLAN_GET_URL="/show_plan";
	/**
	 * Request redirect Urls
	 */

	/**
	 * Redirects to Show account add form
	 */
	public static final String REDIRECT_SHOW_ACC_FORM_GET_URL = "redirect:/show_account";
	/**
	 * Redirects to Show plan add form
	 */
	public static final String REDIRECT_PLAN_ADD_GET_URL = "redirect:/show_plan";

	/**
	 * Send all plans url
	 */
	public static final String SEND_ALL_PLANS_GET_URL = "get_plan_list";

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
	 * Show all user form view name
	 */
	public static final String ALL_ACC_1_VIEW = "show_accounts_1";
	/**
	 * Show all plans  view name
	 */
	public static final String ALL_PLAN_VIEW = "show_plans";
	/**
	 * Error view name
	 */
	public static final String ERROR_VIEW = "error";
	
	/**
	 * Show plan view name
	 */
	public static final String PLAN_ADD_VIEW = "add_plan";
	
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

	public static class Login{
		/**
		 * Show login form url
		 */
		public static final String SHOW_LOGIN_GET_URL = "/show_login";
		/**
		 * Login view name
		 */
		public static final String LOGIN_VIEW = "login";
		
		/**
		 * Check login form url
		 */
		public static final String CHECK_LOGIN_POST_URL = "/check_login";
		/**
		 * Login model key name
		 */
		public static final String LOGIN_MODEL_KEY = "login";
		/**
		 * Login msg key name
		 */
		public static final String LOGIN_KEY = "msg";
		/**
		 * Login success value
		 */
		public static final String LOGIN_SUCCESS_VALUE = "Login successful...";
		/**
		 * Login fail value
		 */
		public static final String LOGIN_FAILED_VALUE = "Invalid email id or password";
		/**
		 * Account inactive value
		 */
		
		public static final String ACCOUNT_INACTIVE_VALUE = "Account is inactive...";
		
		/**
		 * Login session key
		 */
		public static final String LOGIN_SESSION_KEY = "login";
		/**
		 * Login email key
		 */
		public static final String LOGIN_EMAIL_KEY = "email";
		/**
		 * Login to Home redirect
		 */
		public static final String LOGIN_TO_HOME_REDIRECT_GET_URL = "redirect:/show_home";
		
	}
	public static class Home{
		/**
		 * Show home form url
		 */
		public static final String SHOW_HOME_GET_URL = "/show_home";
		/**
		 * Home view name
		 */
		public static final String HOME_VIEW = "home";
	}
	public static class ForgotPassword{
		/**
		 * Show home form url
		 */
		public static final String SHOW_FORGOT_PASSWORDD_GET_URL = "/show_forgot";
		/**
		 * Home view name
		 */
		public static final String FORGOT_PASSWORD_VIEW = "forgot_password";
		/**
		 * Send password to mail
		 */
		public static final String SEND_PASSWORD_POST_URL = "/send_password";
		/**
		 * Send password key
		 */
		public static final String FORGOT_PASSWORD_KEY = "msg";
		/**
		 * Send password value
		 */
		public static final String PASSWORD_SENT_VALUE = "Password sent to your mail successfully...";
		/**
		 * Send password value
		 */
		public static final String EMAIL_NOT_FOUND_VALUE = "Email id does not exist...";
		
	}
}
