package com.xyauto.util;

/**
 * 系统常量工具类
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月9日 下午2:28:02
 */
public interface Constants {
	/*
	 * 一.会议室可用设备顺序列表（equipment）
	 * 		投影仪、投影幕、电视、八爪鱼
	 * 二.是否删除（is_delete）
	 * 		已删除：1、未删除：0
	 * 三.会议室状态（status）
	 * 		启用：1、停用：0
	 */

	//Login
	public static final String COOKIE_KEY = "mmp-3244xx32234sdf";
	public static final String GET_OA_USER = "http://oa.xingyuanauto.com/hrwebapi/EmployeeForPortal/GetEmpForPortal";
	public static final String OA_LOGIN_URL = "http://oa.xingyuanauto.com/login.aspx";
	public static final String SESSION_USER = "user";
	//Cache
	public static final String CACHE_OA = "OACache";
}
