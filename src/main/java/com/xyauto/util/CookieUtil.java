package com.xyauto.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * cookie工具类
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月9日 下午2:28:11
 */
public class CookieUtil {

	public static String getCookieValue(HttpServletRequest request, String cookieName) {

		Cookie[] cookies = request.getCookies();
		if (cookies != null)
			for (Cookie cookie : cookies)
				if (cookieName.equals(cookie.getName()))
					return cookie.getValue();
		return null;
	}
}
