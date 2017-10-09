package com.xyauto.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	
	public static String getCookieValue(HttpServletRequest request, String cookieName){

		Cookie[] cookies = request.getCookies();
		if (cookies != null)
			for (Cookie cookie : cookies)
				if(cookieName.equals(cookie.getName()))
					return cookie.getValue();
		return null;
	}
}
