package com.ssa.util;

public class Constants {
	 //Package Constants
	 public static final String BASE_PACKAGE="com.ssa";
	 public static final String RESOURCE_PACKAGE="com.ssa.resource";
	 
	 //Value constants
	 public static final String MALE="Male";
	 public static final String FEMALE="Female";
	 
	 //Model Attributes Keys
	 public static final String MK_GENDERS="genders";
	 public static final String MK_USER="user";
	 public static final String MK_STATES="states";
	 public static final String MK_USER_LIST="userModelList";
	 
	 //Request Attribute flash keys
	 public static final String RA_KEY_ENROLL_SUCCESS="msg";
	 
	//Request Attribute flash values
	 public static final String RA_VALUE_ENROLL_SUCCESS="SSN Enrollment completed successfully with ";
	 
	 //Request Mapping urls
	 public static final String ENROLL_GET_URL="/register";
	 public static final String ENROLL_POST_URL="/enroll";
	 public static final String SHOW_USERS_GET_URL="/showall";
	 
	 //Request redirect Urls
	 public static final String REDIRECT_ENROLL_GET_URL="redirect:/register";
	 
	 //Rest Controller urls
	 public static final String GET_STATE_GET_URL="/getState/{ssn}";
	 
	 //Logical views
	 public static final String VW_ENROLL="ssnreg";
	 public static final String VW_SHOW_USERS="showallusers";
	 //Request param names
	 public static final String PHOTO_FILE_PARAM="photoFile";
	 
	 
	 
	 
	 
	 
	 
}
