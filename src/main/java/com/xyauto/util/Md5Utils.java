package com.xyauto.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import lombok.extern.slf4j.Slf4j;

/**
 * 使用java.security.MessageDigest类写的一个工具类用来获取MD5码
 * 
 * @author Talen
 * @see java.security.MessageDigest
 */
@Slf4j
public class Md5Utils {
	/**
	 * 默认的密码字符串组合，apache校验下载的文件的正确性用的就是默认的这个组合
	 */
	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };
	protected static MessageDigest messagedigest = null;
	static {
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsaex) {
			log.debug(Md5Utils.class.getName() + "初始化失败，MessageDigest不支持MD5Util。");
			nsaex.printStackTrace();
		}
	}

	/**
	 * 
	 * 向getMD5方法传入一个你需要转换的原始字符串，将返回字符串的MD5码
	 * 
	 * @param code原始字符串
	 * @return 返回字符串的MD5码
	 */
	public static String getMD5(String code, String charset) {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			byte[] bytes = code.getBytes(charset);
			byte[] results = messageDigest.digest(bytes);
			StringBuilder stringBuilder = new StringBuilder();
			for (byte result : results) {
				// 将byte数组转化为16进制字符存入stringbuilder中
				stringBuilder.append(String.format("%02x", result));
			}
			return stringBuilder.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			log.error("Error Occur " + e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			log.error("Error Occur " + e);
		}
		return null;
	}

}
