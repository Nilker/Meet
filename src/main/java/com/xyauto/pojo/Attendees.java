package com.xyauto.pojo;

import java.util.Date;

import lombok.Data;

/**
 * @author qiaom@xingyuanauto.com
 * @version	创建时间：2017年10月9日 下午1:58:32
 */

@Data
public class Attendees {
	
    private String srId;
    private String employeeId;
    private String employeeName;
    private Boolean isDelete;
    private Date createTime;
    private String createUser;
    private Date updateTime;
    private String updateUser;
}