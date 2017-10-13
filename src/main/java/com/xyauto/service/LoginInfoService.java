package com.xyauto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyauto.mapper.RolePermissionsMapper;
import com.xyauto.pojo.UserRole;

/**
 * 用户登录信息服务类
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月9日 下午2:26:34
 */
@Service
public class LoginInfoService {

	@Autowired
	private RolePermissionsMapper rolePermissionsMapper;

	public List<UserRole> getUserRole(Integer empId) {
		return rolePermissionsMapper.getUserRole(empId);
	}
}
