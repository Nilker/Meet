package com.xyauto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyauto.mapper.OfficeAreaAuthorityMapper;
import com.xyauto.pojo.OfficeAreaAuthority;
import com.xyauto.pojo.OfficeLocation;

/**
 * 办公区权限服务类
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月9日 下午2:26:34
 */
@Service
public class OfficeAreaAuthorityService {

	@Autowired
	private OfficeAreaAuthorityMapper officeAreaAuthorityMapper;
	
	public Integer insert(OfficeAreaAuthority oaa) {
		return officeAreaAuthorityMapper.insert(oaa);
	}

	public Integer update(OfficeAreaAuthority oaa) {
		return officeAreaAuthorityMapper.updateByPrimaryKey(oaa);
	}

	public List<OfficeAreaAuthority> all() {
		return officeAreaAuthorityMapper.selectAll();
	}
}
