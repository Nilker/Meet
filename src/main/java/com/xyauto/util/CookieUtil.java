package com.xyauto.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie工具类
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月9日 下午2:28:11
 */
public class CookieUtil {
	private final static String DOMAIN = "oa.xingyuanauto.com";
	private final static String PATH = "/";

	public static boolean setCookieValue(HttpServletResponse response, String key, String value) {
		return setCookieValue(response, key, value, PATH, DOMAIN);
	}

	public static boolean setCookieValue(HttpServletResponse response, String key, String value, String path,
			String domain) {
		try {
			Cookie cookie = new Cookie(key, value);
			cookie.setPath(path);// very important
			cookie.setDomain(domain);
			cookie.setMaxAge(-1);
			response.addCookie(cookie);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String getCookieValue(HttpServletRequest request, String cookieName) {

		Cookie[] cookies = request.getCookies();
		if (cookies != null)
			for (Cookie cookie : cookies)
				if (cookieName.equals(cookie.getName()))
					return cookie.getValue();
		return null;
	}
}
