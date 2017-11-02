package com.xyauto.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xyauto.mapper.ScheduledRecordMapper;
import com.xyauto.pojo.ScheduledRecord;
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

	@Transactional
	public PageData selectByPage(Integer pageNo, Integer pageSize, String officeId, String biId, String conferenceStatus,
			String employeeName, User user) {
		
		Integer total = scheduledRecordMapper.countByPage(officeId, biId, conferenceStatus, employeeName, user.getEmployeeId());
		List<ScheduledRecord> srList = scheduledRecordMapper.selectByPage(pageNo, pageSize, officeId, biId, conferenceStatus, employeeName, user.getEmployeeId());
		for (int i = 0,j = 0; i < srList.size() && j < srList.size(); i++,j++) {
			ScheduledRecord sr = srList.get(i);
			if(sr.getStartTime().getTime() < new Date().getTime()){
				srList.remove(i);
				srList.add(sr);
				i--;
			}else{
				break;
			}
		}
		
		PageData data = new PageData();
		data.setPageNo(pageNo);
		data.setPageSize(pageSize);
		data.setTotal(total);
		data.setData(srList);
		return data;
	}
}
