package com.xyauto.pojo;

import java.util.Date;

import lombok.Data;

/**
 * @author qiaom@xingyuanauto.com
 * @version	创建时间：2017年10月9日 下午1:58:32
 */

@Data
public class OfficeAreaAuthority {
	
    private String oaaId;
    private String officeIds;
    private String employeeId;
    private String employeeName;
    private String departmentId;
    private String departmentName;
    private String founderId;
    private String founderName;
    private Date adddate;
    private Boolean isDelete;
    private Date createTime;
    private String createUser;
    private Date updateTime;
    private String updateUser;
}