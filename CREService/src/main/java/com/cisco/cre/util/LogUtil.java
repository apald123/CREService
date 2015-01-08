package com.cisco.cre.util;

public class LogUtil {
	
	enum LOG_LEVEL_TYPE {DEBUG, INFO, WARN, ERROR};

	private static LOG_LEVEL_TYPE LOG_LEVEL = LOG_LEVEL_TYPE.DEBUG;
	
	public static void print(Object obj, String s1) {
		StringBuilder str = new StringBuilder();
		str.append(obj.getClass().getName()).append(": ").append(s1);
		System.out.println(str.toString());
	}
	
	public static void debug(Object obj, String s1) {
		
		if (LOG_LEVEL == LOG_LEVEL_TYPE.DEBUG)
			print(obj, s1);
	}

	public static void info(Object obj, String s1) {
		if (LOG_LEVEL == LOG_LEVEL_TYPE.INFO || LOG_LEVEL == LOG_LEVEL_TYPE.DEBUG)
			print(obj, s1);
	}

}
