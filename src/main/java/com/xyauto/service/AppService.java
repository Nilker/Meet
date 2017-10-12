package com.xyauto.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyauto.mapper.AppMapper;
import com.xyauto.mapper.AttendeesMapper;
import com.xyauto.mapper.BoardroomInfoMapper;
import com.xyauto.mapper.ScheduledRecordMapper;
import com.xyauto.pojo.Attendees;
import com.xyauto.pojo.BoardroomInfo;
import com.xyauto.pojo.ScheduledRecord;

@Service
public class AppService {
	@Autowired
	private BoardroomInfoMapper boardroomInfoMapper;
	@Autowired
	private ScheduledRecordMapper scheduledRecordMapper;
	@Autowired
	private AppMapper appMapper;
	@Autowired
	private AttendeesMapper attendeesMapper;

	public Map<String, Object> findInfoByOfficeId(Integer officeId, String startTime) {
		Map<String, Object> scheduleMap = new HashMap<>();
		scheduleMap.put("boardroomList", appMapper.findBoardInfoByOfficeId(officeId));
		scheduleMap.put("scheduleList", appMapper.findScheInfoByReq(officeId, startTime));
		return scheduleMap;
	}

	public BoardroomInfo findBoardByPrimaryKey(String biId) {
		return boardroomInfoMapper.selectByPrimaryKey(biId);
	}

	public ScheduledRecord findSchelByPrimaryKey(String srId) {
		return scheduledRecordMapper.selectByPrimaryKey(srId);
	}

	public Map<String, Object> findInfoByBiId(String biId, String startTime) {
		Map<String, Object> scheduleMap = new HashMap<>();
		scheduleMap.put("boardroomList", boardroomInfoMapper.selectByPrimaryKey(biId));
		scheduleMap.put("scheduleList", appMapper.findScheInfoByBiId(biId, startTime));
		return scheduleMap;
	}
	public int insertSchedule(ScheduledRecord record) {
		//验证
		//调用oa接口
//		record.setEmployeeId(employeeId);
//		record.setEmployeeName(employeeName);
//		record.setDepartmentId(departmentId);
//		record.setDepartmentName(departmentName);
//		record.setPhone(phone);
//		record.setIsDelete(false);
//		scheduledRecordMapper.insert(record);
		//插入与会人
//		Attendees attendees=new Attendees();
//		attendees.setSrId(record.getSrId());
//		attendees.setEmployeeId(employeeId);
//		attendees.setEmployeeName(employeeName);
//		attendees.setIsDelete(false);
//		attendeesMapper.insert(attendees);
		return 1;
	}
	/**
	 * 验证会议室是否被预定
	 * @return
	 */
//	public boolean checkMeetTime() {
//		return false; 
//	}
	
}
