package com.xyauto.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户角色模块类
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年9月30日 上午9:55:52
 */
@Getter
@Setter
public class UserRole {

	private String moduleId;
	private String url;

	public UserRole() {
		super();
	}

	public UserRole(String moduleId) {
		super();
		this.moduleId = moduleId;
	}

	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (null == object)
			return false;
		if (getClass() != object.getClass())
			return false;
		final UserRole userRole = (UserRole) object;
		if (this.getModuleId().equals(userRole.getModuleId()))
			return true;
		return false;
	}

	public String toString() {
		return this.moduleId;
	}
}
