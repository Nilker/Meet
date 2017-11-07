package com.xyauto.util;

/**
 * 系统常量工具类
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月9日 下午2:28:02
 */
public interface Constants {
	/*
	 * 一.会议室可用设备顺序列表（equipment） 投影仪、投影幕、电视、八爪鱼 
	 * 二.是否删除（is_delete） 已删除：1、未删除：0
	 * 三.会议室状态（status） 启用：1、停用：0 
	 * 四.接口返回code 正常：0、异常：99
	 */

	// Login
	public static final String COOKIE_KEY = "mmp-3244xx32234sdf";
	public static final String SESSION_USER = "user";
	public static final String SESSION_APPUSER = "appUser";

	// Cache
	public static final String CACHE_OA = "OACache";
	// Error Message
	public static final String INSTER_ERROR = "添加失败";
	public static final String UPDATE_ERROR = "更新失败";
	public static final String DELETE_ERROR = "删除失败";
	public static final String SELECT_ERROR = "查询失败";
	public static final String ROLE_ERROR = "没有访问权限";
	public static final String EXCEPTION = "发生异常";
	/**
	 * Login Error
	 */
	public static final String LOGIN_ERROR = "loginError";

	/**
	 * 会议提醒人ID 多人为0
	 */
	public static final Integer REMIND_USERID = 0;
	/**
	 * message key
	 */
	public static final String KEY_STR = "com.xyauto.sys";
	/**
	 * message invitation
	 */
	public static final String MEETTING_INVITATION = "MeetingInvitation";
	public static final String MEETTING_INV_CN = "会议邀请";

	/**
	 * message cancel
	 */
	public static final String MEETTING_CANCEL = "MeetingCancel";
	public static final String MEETTING_CAL_CN = "会议取消";
	/*
	 * 15Min message remind
	 */
	public static final String MEETTING_REMIND = "MeetingRemind";
	public static final String MEETTING_REM_CN = "会议提醒";
	/**
	 * boardroom unit : 层-
	 */
	public static final String SUB_TITLE = "层-";
}
