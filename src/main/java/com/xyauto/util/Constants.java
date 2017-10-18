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
	 * 四.接口返回code
	 * 		正常：0、异常：99
	 */

	//Login
	public static final String COOKIE_KEY = "mmp-3244xx32234sdf";
	public static final String GET_OA_CHECK = "http://oa1.xingyuanauto.com/hrwebapi/Authorize/Check";
	public static final String GET_OA_USER = "http://oa1.xingyuanauto.com/hrwebapi/EmployeeForPortal/GetEmpForPortal";
	public static final String OA_LOGIN_URL = "http://oa1.xingyuanauto.com/login.aspx";
	public static final String SESSION_USER = "user";
	public static final String SESSION_APPUSER = "appUser";
	public static final String APP_COOKIE_KEY="";
	//Role
	public static final String SYSTEM_ID = "SYS023";
	public static final String MEETING_MANAGER = "SYS023MOD0001";
	public static final String SCHEDULED_PREVIEW = "SYS023MOD0002";
	public static final String AUTHORIZE_MANAGER = "SYS023MOD0003";
	//Cache
	public static final String CACHE_OA = "OACache";
	//Error Message 
	public static final String INSTER_ERROR = "添加失败";
	public static final String UPDATE_ERROR = "更新失败";
	public static final String DELETE_ERROR = "删除失败";
	public static final String SELECT_ERROR = "查询失败";
	public static final String ROLE_ERROR = "没有访问权限";
	public static final String EXCEPTION = "发生异常";
}
