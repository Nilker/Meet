package com.xyauto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyauto.mapper.OfficeLocationMapper;
import com.xyauto.pojo.OfficeLocation;

/**
 * 办公区服务类
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月9日 下午2:26:34
 */
@Service
public class OfficeLocationService {

	@Autowired
	private OfficeLocationMapper officeLocationMapper;

	public OfficeLocation selectOneById(Integer id) {
		return officeLocationMapper.selectByPrimaryKey(id);
	}

	public List<OfficeLocation> selectAll() {
		return officeLocationMapper.selectAll();
	}
}
