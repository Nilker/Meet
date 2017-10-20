package com.xyauto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyauto.mapper.ScheduledRecordMapper;
import com.xyauto.pojo.ScheduledRecord;

/**
 * 会议室预览服务类
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月9日 下午2:26:34
 */
@Service
public class ScheduledPreviewService {

	@Autowired
	private ScheduledRecordMapper scheduledRecordMapper;

	public List<ScheduledRecord> selectByPage(Integer pageSize, Integer officeId, String biId, Integer conferenceStatus,
			String departmentName) {
		return scheduledRecordMapper.selectByPage(0, pageSize, officeId, biId, conferenceStatus, departmentName);
	}
}
