package com.xyauto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyauto.mapper.OfficeLocationMapper;
import com.xyauto.pojo.OfficeLocation;

@Service
public class OfficeLocationService {
	
	@Autowired
	OfficeLocationMapper officeLocationMapper;
	
	public OfficeLocation selectOneById(Integer id){
		return officeLocationMapper.selectByPrimaryKey(id);
	}
	
	public List<OfficeLocation> selectAll(){
		return officeLocationMapper.selectAll();
	}
	
}
