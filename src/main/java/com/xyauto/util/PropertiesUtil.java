package com.xyauto.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtil {

	private final String WEB_SERVICE = "com.xyauto.WEB_SERVICE";
	private final String GET_OA_CHECK = "com.xyauto.GET_OA_CHECK";
	private final String GET_OA_USER = "com.xyauto.GET_OA_USER";
	private static PropertiesUtil instance = null;
	private ConcurrentMap<String, String> oaProperties = null;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private PropertiesUtil(){
		InputStream in = ClassLoader.getSystemResourceAsStream("application.properties"); 
		Properties prop = new Properties();  
		try {
			prop.load(in);
			oaProperties = new ConcurrentHashMap<>();
			oaProperties.put("WEB_SERVICE", prop.getProperty(WEB_SERVICE));
			oaProperties.put("GET_OA_CHECK", prop.getProperty(GET_OA_CHECK));
			oaProperties.put("GET_OA_USER", prop.getProperty(GET_OA_USER));
			log.debug(">> WEB_SERVICE_URL:" + oaProperties.get("WEB_SERVICE"));
			log.debug(">> GET_OA_CHECK_URL:" + oaProperties.get("GET_OA_CHECK"));
			log.debug(">> GET_OA_USER_URL:" + oaProperties.get("GET_OA_USER"));
		} catch (IOException e) {
			log.debug(">> OA_PROPERTIES:" + e.toString());
		} 
	};
	
	public static synchronized String getPropertiesByKey(String key){
		if(null == instance){
			instance = new PropertiesUtil();
		}
		return instance.oaProperties.get(key);
	}
}
