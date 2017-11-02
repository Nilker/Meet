package com.xyauto.pojo;

import java.util.Date;

import lombok.Data;

/**
 * 会议室信息类
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月9日 下午1:58:32
 */
@Data
public class BoardroomInfo {

	private String biId;
	private Integer officeId;
	private String biName;
	private String biFloor;
	private Integer biCapacity;
	private Byte equipment;
	private Byte status;
	private Boolean isDelete;
	private Date createTime;
	private String createUser;
	private Date updateTime;
	private String updateUser;
}