package com.xyauto.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xyauto.extend.ScheduledRecordExt;
import com.xyauto.mapper.AppMapper;
import com.xyauto.mapper.AttendeesMapper;
import com.xyauto.mapper.BoardroomInfoMapper;
import com.xyauto.mapper.ScheduledRecordMapper;
import com.xyauto.oa.Employee;
import com.xyauto.pojo.Attendees;
import com.xyauto.pojo.BoardroomInfo;
import com.xyauto.pojo.ScheduledRecord;
import com.xyauto.util.IdUtil;
import com.xyauto.util.StringUtil;

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
	@Autowired
	private OAService oAService;

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

	// 加事务
	@Transactional
	public Integer insertSchedule(ScheduledRecordExt record) {
		// 验证
		if (appMapper.checkMeetTime(record.getBiId(), record.getStartTime(), record.getEndTime()) > 0)
			return appMapper.checkMeetTime(record.getBiId(), record.getStartTime(), record.getEndTime());
		String srId = IdUtil.getUUID();
		// 调用oa接口
		record.setSrId(srId);
		Employee e = oAService.queryEmployeeById(record.getEmployeeId());
		record.setEmployeeId(record.getEmployeeId());
		record.setEmployeeName(e.getCnName());
		record.setDepartmentId(Integer.toString(e.getDepartment().getDepartmentId()));
		record.setDepartmentName(e.getDepartment().getDescription());
		record.setPhone(e.getMobile());
		record.setIsDelete(false);
		record.setCreateUser(record.getEmployeeId());
		record.setUpdateUser(record.getEmployeeId());
		scheduledRecordMapper.insert(record);
		// 插入与会人
		List<Attendees> attendeesList=new ArrayList<>();
		for (String eid : record.getEmployee_ids()) {
			Attendees attendees = new Attendees();
			attendees.setSrId(srId);
			attendees.setEmployeeId(eid);
			attendees.setEmployeeName(oAService.queryEmployeeById(eid).getCnName());
			attendees.setIsDelete(false);
			attendees.setCreateUser(record.getEmployeeId());
			attendees.setUpdateUser(record.getEmployeeId());
			attendeesList.add(attendees);
		}
		appMapper.insertByBatch(attendeesList);
		//通知与会人
		
		return 0;
	}
	
}
