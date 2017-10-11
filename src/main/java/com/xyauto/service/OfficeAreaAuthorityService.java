package com.xyauto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyauto.mapper.OfficeAreaAuthorityMapper;
import com.xyauto.pojo.OfficeAreaAuthority;
import com.xyauto.util.PageData;

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

	// TODO transaction
	public Integer insert(OfficeAreaAuthority oaa) {
		if (0 < officeAreaAuthorityMapper.existsByEmployeeId(oaa.getEmployeeId()))
			return -1;
		return officeAreaAuthorityMapper.insert(oaa);
	}

	public Integer update(OfficeAreaAuthority oaa) {
		return officeAreaAuthorityMapper.updateByPrimaryKey(oaa);
	}

	// TODO transaction
	public PageData selectByCondition(Integer pageNo, Integer pageSize, String officeId, String employeeName) {
		PageData data = new PageData();
		data.setPageNo(pageNo);
		data.setPageSize(pageSize);
		data.setTotal(officeAreaAuthorityMapper.countByNotDel());
		data.setData(officeAreaAuthorityMapper.selectByCondition(pageNo, pageSize, officeId, employeeName));
		return data;
	}
}
