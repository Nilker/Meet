package com.xyauto.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PropertiesUtil extends Properties {

	private static final long serialVersionUID = 1L;
	private final static String GET_OA_CHECK = "com.xyauto.GET_OA_CHECK";
	private final static String GET_OA_USER = "com.xyauto.GET_OA_USER";
	private final static String MESSAGE_API_URL = "com.xyauto.MESSAGE_API_URL";
	private static ConcurrentMap<String, String> oaProperties = null;
	private static PropertiesUtil instance = null;
	
	private PropertiesUtil(){
		InputStream in = ClassLoader.getSystemResourceAsStream("application.properties");
		try {
			this.load(in);
			oaProperties = new ConcurrentHashMap<>();
			oaProperties.put("GET_OA_CHECK", this.getProperty(GET_OA_CHECK));
			oaProperties.put("GET_OA_USER", this.getProperty(GET_OA_USER));
			oaProperties.put("MESSAGE_API_URL", this.getProperty(MESSAGE_API_URL));
		} catch (IOException e) {
			System.err.println(e.toString());
		}
		
	};
	
	public static synchronized String getPropertiesByKey(String key){
		if(null == instance)
			instance = new PropertiesUtil();
		return PropertiesUtil.oaProperties.get(key);
	}
}
