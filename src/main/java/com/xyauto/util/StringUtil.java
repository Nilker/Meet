package com.xyauto.util;

import java.util.Random;
import java.util.StringTokenizer;

/**
 * 字符串工具类
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月9日 下午2:28:31
 */
public class StringUtil {

	public static boolean isEmpty(String str) {
		return ((str == null) || (str.length() == 0));
	}

	public static boolean isNotEmpty(String str) {
		return ((str != null) && (str.length() > 0));
	}

	public static String getRandomString(int length) {
		// String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		String base = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static String removeTrim(String string) {
		if (string != null) {
			if ("".equals(string.trim())) {
				return "";
			}
			return string.replaceAll("\\s+", "");
		} else {
			return null;
		}
	}

	public static String[] convertStrToArray(String str) {
		String[] strArray = new String[] {};
		if (isEmpty(str))
			return strArray;
		strArray = str.split(",");
		return strArray;
	}
}
