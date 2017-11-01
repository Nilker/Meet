package com.xyauto.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.support.logging.Log;
import com.xyauto.extend.ScheduledRecordExt;
import com.xyauto.mapper.AppMapper;
import com.xyauto.mapper.BoardroomInfoMapper;
import com.xyauto.mapper.ScheduledRecordMapper;
import com.xyauto.oa.Employee;
import com.xyauto.pojo.Attendees;
import com.xyauto.pojo.BoardroomInfo;
import com.xyauto.pojo.ScheduledRecord;
import com.xyauto.util.CacheUtil;
import com.xyauto.util.Constants;
import com.xyauto.util.DateUtils;
import com.xyauto.util.Expand;
import com.xyauto.util.IdUtil;
import com.xyauto.util.MainData;
import com.xyauto.util.MeetingMessage;
import com.xyauto.util.MessageUtil;
import com.xyauto.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

@Service
public class AppService {
	@Autowired
	private BoardroomInfoMapper boardroomInfoMapper;
	@Autowired
	private ScheduledRecordMapper scheduledRecordMapper;
	@Autowired
	private AppMapper appMapper;
	@Autowired
	private OAService oAService;

	public List<ScheduledRecordExt> findInfoByOfficeId(Integer officeId, String startTime) {
		List<ScheduledRecordExt> findBoradSchedInfoByReq = appMapper.findBoradSchedInfoByReq(officeId, startTime);
		for (ScheduledRecordExt scheduledRecord : findBoradSchedInfoByReq) {
			scheduledRecord.setStartEndTimeArr(StringUtil.convertStrToArray(scheduledRecord.getStartEndTime()));
		}
		return findBoradSchedInfoByReq;
	}

	public BoardroomInfo findBoardByPrimaryKey(String biId) {
		return boardroomInfoMapper.selectByPrimaryKey(biId);
	}
	@Transactional
	public Integer delScheduleBySrId(String srId) throws ParseException, IOException, InterruptedException, ExecutionException {
		int upSchedCount = appMapper.delScheduleBySrId(srId);
		int upAttenCount = appMapper.delAttendeesBySrId(srId);
		if(upSchedCount > 0 && upAttenCount > 0) {
			List<ScheduledRecordExt> findSingleMeetBySrId = appMapper.findSingleMeetBySrId(srId);
			MeetingMessage msg = new MeetingMessage();
			MainData data = new MainData();
			Expand expand = new Expand();
			StringBuffer empIdStr = new StringBuffer();
			StringBuffer boardInfo = new StringBuffer();
			String actionUser = "";
			for (int i = 0; i < findSingleMeetBySrId.size(); i++) {
				empIdStr.append(findSingleMeetBySrId.get(i).getEmployeeId()+"|");
				if(findSingleMeetBySrId.get(i).getType().equals("发起人")) {
					actionUser=findSingleMeetBySrId.get(i).getEmployeeName();
					boardInfo.append(findSingleMeetBySrId.get(i).getBiFloor());
					boardInfo.append(findSingleMeetBySrId.get(i).getBiName());
				}
				expand.setTitle(findSingleMeetBySrId.get(0).getMeetingTheme());
				expand.setDate(DateUtils.date2Str(findSingleMeetBySrId.get(i).getStartTime(),DateUtils.YMD));
				expand.setStartTime(DateUtils.date2Str(findSingleMeetBySrId.get(i).getStartTime(),DateUtils.HHMMSS));
				expand.setEndTime(DateUtils.date2Str(findSingleMeetBySrId.get(i).getEndTime(),DateUtils.HHMMSS));
			}
			expand.setSubTitle(boardInfo.toString());
			msg.setUserCode(empIdStr.toString());
			data.setRealtionId(srId);
			data.setActionUser(actionUser);
			data.setRemark(Constants.MEETTING_CANCEL);
			data.setRemindUserCode(empIdStr.toString());
			data.setExpand(expand);
			msg.setData(data);
			MessageUtil.meetingCancel(msg);
			CacheUtil.delScheMap(srId, appMapper);
			System.out.println("删除后-->"+CacheUtil.getScheMap(appMapper));
		}
		return 0;
	}

	public ScheduledRecord findSchelByPrimaryKey(String srId) {
		return scheduledRecordMapper.selectByPrimaryKey(srId);
	}

	public Map<String, Object> findInfoByBiId(String biId, String startTime) {
		Map<String, Object> scheduleMap = new HashMap<>();
		scheduleMap.put("boardroomList", boardroomInfoMapper.selectByPrimaryKey(biId));
		scheduleMap.put("scheduleList", appMapper.findInfoByBiId(biId, startTime));
		return scheduleMap;
	}

	/**
	 * 预定会议室-单个会议室详情
	 * 
	 * @param biId
	 * @param startTime
	 *            yyyy-MM-dd
	 * @return
	 */
	public Map<String, Object> findSingleInfoByBiId(String biId, String startTime) {
		Map<String, Object> scheduleMap = new HashMap<>();
		scheduleMap.put("boardroomList", boardroomInfoMapper.selectByPrimaryKey(biId));
		scheduleMap.put("scheduleList", appMapper.findScheInfoByBiId(biId, startTime));
		return scheduleMap;
	}

	public List<ScheduledRecordExt> findAllMeetOfSelf(String employeeId) {
		return appMapper.findAllMeetOfSelf(employeeId,DateUtils.now(DateUtils.YMD));
	}

	public List<ScheduledRecordExt> findSingleMeetBySrId(String srId) {
		return appMapper.findSingleMeetBySrId(srId);
	}

	@Transactional
	public Integer insertSchedule(ScheduledRecordExt record) throws ParseException, IOException, InterruptedException, ExecutionException {
		int addResult = 0;
		// 验证
		if (appMapper.checkMeetTime(record.getBiId(), record.getStartTime(), record.getEndTime()) > 0)
			return 1;
		if(appMapper.checkBoardStatus(record.getBiId()) == 0)
			return 2;
		MeetingMessage msg = new MeetingMessage();
		MainData data = new MainData();
		Expand expand = new Expand();
		StringBuffer empIdStr = new StringBuffer();
//		StringBuffer boardInfo = new StringBuffer();
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
		int insert = scheduledRecordMapper.insert(record);
		// 插入与会人
		if(!record.getEmployeeIds()[0].equals("") && record.getEmployeeIds() != null) {
			List<Attendees> attendeesList = new ArrayList<>();
			for (String eid : record.getEmployeeIds()) {
				Attendees attendees = new Attendees();
				attendees.setSrId(srId);
				attendees.setEmployeeId(eid);
				String name = oAService.queryEmployeeById(eid).getCnName();
				attendees.setEmployeeName(name);
				attendees.setIsDelete(false);
				attendees.setCreateUser(record.getEmployeeId());
				attendees.setUpdateUser(record.getEmployeeId());
				attendeesList.add(attendees);
				empIdStr.append(eid+"|");
			}
			addResult = appMapper.insertByBatch(attendeesList);
		}
		if(insert > 0) {
			BoardroomInfo selectByPrimaryKey = boardroomInfoMapper.selectByPrimaryKey(record.getBiId());
			expand.setTitle(record.getMeetingTheme());
			expand.setDate(DateUtils.date2Str(record.getStartTime(),DateUtils.YMD));
			expand.setStartTime(DateUtils.date2Str(record.getStartTime(),DateUtils.HHMMSS));
			expand.setEndTime(DateUtils.date2Str(record.getEndTime(),DateUtils.HHMMSS));
			expand.setSubTitle(selectByPrimaryKey.getBiFloor()+Constants.SUB_TITLE+selectByPrimaryKey.getBiName());
			msg.setUserCode(empIdStr.toString());
			data.setRealtionId(srId);
			data.setActionUser(e.getCnName());
			data.setRemark(Constants.MEETTING_INVITATION);
			data.setRemindUserCode(empIdStr.toString());
			data.setExpand(expand);
			msg.setData(data);
			if(addResult > 0) {
				MessageUtil.meetingInvitation(msg);
			}
			// 通知与会人
			if(DateUtils.now(DateUtils.YMD).equals(DateUtils.date2Str(record.getStartTime(),DateUtils.YMD))) {
				empIdStr.append(record.getEmployeeId());//会议邀请不邀请发起人
				msg.setUserCode(empIdStr.toString());
				data.setRemindUserCode(empIdStr.toString());
				msg.setData(data);
				if(DateUtils.str2Date(DateUtils.dateCompute(record.getStartTime()), DateUtils.HHMM).getTime() <= DateUtils.str2Date(DateUtils.now(DateUtils.HHMM), DateUtils.HHMM).getTime()) {
						MessageUtil.meetingRemind(msg);
				}else {
					record.setBiFloor(selectByPrimaryKey.getBiFloor());
					record.setBiName(selectByPrimaryKey.getBiName());
					record.setMyEmpId(empIdStr.toString());
					record.setBeginTimer(DateUtils.dateCompute(record.getStartTime()));
					CacheUtil.addScheMap(record, appMapper);
				}
			}
		}
		return 0;
	}

}
