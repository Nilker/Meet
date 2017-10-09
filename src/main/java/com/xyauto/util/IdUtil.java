package com.xyauto.util;

import java.util.UUID;

/**
 * ID 工具类
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月9日 下午4:13:25
 */
public class IdUtil {

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
