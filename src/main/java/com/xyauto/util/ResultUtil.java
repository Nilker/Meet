package com.xyauto.util;

import lombok.Data;

/**
 * 结果集工具类
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月9日 下午3:21:47
 */
@Data
public class ResultUtil {
	private int code;
	private String msg;
	private Object data;

	private ResultUtil() {
	}

	private ResultUtil(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	/**
	 * 默认成功样式
	 *
	 * @return ResultUtils
	 */
	public static ResultUtil success() {
		return new ResultUtil(0, "success", null);
	}

	/**
	 * 默认成功样式
	 *
	 * @return ResultUtils
	 */
	public static ResultUtil success(Object data) {
		return new ResultUtil(0, "success", data);
	}

	/**
	 * 默认失败样式
	 *
	 * @param msg
	 *            失败信息
	 * @return ResultUtils
	 */
	public static ResultUtil error(String msg) {
		return new ResultUtil(99, msg, null);
	}
}
