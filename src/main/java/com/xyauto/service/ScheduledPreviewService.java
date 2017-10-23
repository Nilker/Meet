package com.xyauto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyauto.mapper.ScheduledRecordMapper;
import com.xyauto.pojo.User;
import com.xyauto.util.PageData;

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

	public PageData selectByPage(Integer pageNo, Integer pageSize, String officeId, String biId, String conferenceStatus,
			String employeeName, User user) {
		PageData data = new PageData();
		data.setPageNo(pageNo);
		data.setPageSize(pageSize);
		data.setTotal(scheduledRecordMapper.countByPage(officeId, biId, conferenceStatus, employeeName, user.getEmployeeId()));
		data.setData(scheduledRecordMapper.selectByPage(pageNo, pageSize, officeId, biId, conferenceStatus, employeeName, user.getEmployeeId()));
		return data;
	}
}
