package com.warehouse.common;

/**
 * This is utility class to read configuration properties
 * 
 */
public class MyFileProperty {

	private static String WS_SERVICES_URL;
	private static String APPLCATION_URL;

	private static String USER_ADMIN_ROLE;
	private static String USER_GUEST_ROLE;
	
	private MyFileProperty() {
		/*private constructor*/
	}
	
	/**
	 * in login controller this method is called to prepare all properties ready
	 * 
	 * @param string
	 * @param property
	 */
	public static void prepareProperty(String string, String property) {
		if ("WS_SERVICES_URL".equals(string) && WS_SERVICES_URL == null) {
			WS_SERVICES_URL = property;
		} else if ("APPLCATION_URL".equals(string) && APPLCATION_URL == null) {
			APPLCATION_URL = property;
		} else if ("USER_ADMIN_ROLE".equals(string) && USER_ADMIN_ROLE == null) {
			USER_ADMIN_ROLE = property;
		} else if ("USER_GUEST_ROLE".equals(string) && USER_GUEST_ROLE == null) {
			USER_GUEST_ROLE = property;
		}
	}

	/**
	 * @return
	 */
	public static String getWS_SERVICES_URL() {
		return WS_SERVICES_URL;
	}

	public static void setWS_SERVICES_URL(String wS_SERVICES_URL) {
		WS_SERVICES_URL = wS_SERVICES_URL;
	}

	public static String getAPPLCATION_URL() {
		return APPLCATION_URL;
	}

	public static void setAPPLCATION_URL(String aPPLCATION_URL) {
		APPLCATION_URL = aPPLCATION_URL;
	}

	public static String getUSER_ADMIN_ROLE() {
		return USER_ADMIN_ROLE;
	}

	public static void setUSER_ADMIN_ROLE(String uSER_ADMIN_ROLE) {
		USER_ADMIN_ROLE = uSER_ADMIN_ROLE;
	}

	public static String getUSER_GUEST_ROLE() {
		return USER_GUEST_ROLE;
	}

	public static void setUSER_GUEST_ROLE(String uSER_GUEST_ROLE) {
		USER_GUEST_ROLE = uSER_GUEST_ROLE;
	}
}
