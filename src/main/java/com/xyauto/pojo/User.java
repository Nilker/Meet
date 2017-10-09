package com.xyauto.pojo;

import lombok.Data;

/**
 * 用户类
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年9月30日 上午9:55:52
 */
@Data
public class User {

	private Integer userId;
	private String userName;
	private String employeeId;
	private String employeeName;
	private String departmentId;
	private String departmentName;
	private String icon;
	private Boolean isLogin;
}
