package com.xyauto.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PropertiesUtil {

	private final static String WEB_SERVICE = "com.xyauto.WEB_SERVICE";
	private final static String GET_OA_CHECK = "com.xyauto.GET_OA_CHECK";
	private final static String GET_OA_USER = "com.xyauto.GET_OA_USER";
	private static ConcurrentMap<String, String> oaProperties = null;
	
	static {
		InputStream in = ClassLoader.getSystemResourceAsStream("application.properties"); 
		Properties prop = new Properties();  
		try {
			prop.load(in);
			oaProperties = new ConcurrentHashMap<>();
			oaProperties.put("WEB_SERVICE", prop.getProperty(WEB_SERVICE));
			oaProperties.put("GET_OA_CHECK", prop.getProperty(GET_OA_CHECK));
			oaProperties.put("GET_OA_USER", prop.getProperty(GET_OA_USER));
		} catch (IOException e) {
			System.err.println(e.toString());
		} 
	}
	
	private PropertiesUtil(){};
	
//	public static synchronized String getPropertiesByKey(String key){
//		return PropertiesUtil.oaProperties.get(key);
//	}
}
