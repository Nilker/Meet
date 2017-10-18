package com.xyauto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyauto.mapper.OfficeLocationMapper;
import com.xyauto.mapper.RolePermissionsMapper;
import com.xyauto.pojo.OfficeLocation;
import com.xyauto.pojo.UserRole;

/**
 * 工具服务类
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月9日 下午2:26:34
 */
@Service
public class UtilsService {

	@Autowired
	private RolePermissionsMapper rolePermissionsMapper;
	@Autowired
	private OfficeLocationMapper officeLocationMapper; 

	public List<UserRole> getUserRole(Integer empId) {
		return rolePermissionsMapper.getUserRole(empId);
	}
	
	public List<OfficeLocation> getOfficeInfoAll() {
		return officeLocationMapper.selectAll();
	}
}
