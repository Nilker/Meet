package com.xyauto.pojo;

import java.util.Date;

import lombok.Data;

/**
 * 预定记录类
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月9日 下午1:58:32
 */
@Data
public class ScheduledRecord {

	private String srId;
	private String biId;
	private Date startTime;
	private Date endTime;
	private String meetingTheme;
	private String employeeId;
	private String employeeName;
	private String departmentId;
	private String departmentName;
	private String phone;
	private Integer officeId;
	private Boolean isDelete;
	private Date createTime;
	private String createUser;
	private Date updateTime;
	private String updateUser;
	/*-1：未开始
	   0：会议中
	   1：已结束
	   2:
	* */
	private Integer status;
}