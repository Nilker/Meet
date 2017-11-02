package com.xyauto.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xyauto.mapper.BoardroomInfoMapper;
import com.xyauto.mapper.OfficeAreaAuthorityMapper;
import com.xyauto.mapper.ScheduledRecordMapper;
import com.xyauto.pojo.BoardroomInfo;
import com.xyauto.pojo.User;
import com.xyauto.util.PageData;

/**
 * 会议室管理服务类
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月9日 下午2:26:34
 */
@Service
public class MeetingManagerService {

	@Autowired
	private OfficeAreaAuthorityMapper officeAreaAuthorityMapper;
	@Autowired
	private BoardroomInfoMapper boardroomInfoMapper;
	@Autowired
	private ScheduledRecordMapper scheduledRecordMapper;

	@Transactional
	public Integer insert(BoardroomInfo bi, User user) {
		List<Integer> roleList = officeAreaAuthorityMapper.selectRoleByEmpId(user.getEmployeeId());
		if(!roleList.contains(bi.getOfficeId())){
			return -1;
		}
		if (0 < boardroomInfoMapper.existsByOfficeIdAndbiName(bi.getOfficeId(), bi.getBiName()))
			return -2;
		return boardroomInfoMapper.insert(bi);
		
	}

	@Transactional
	public Integer update(BoardroomInfo bi, User user) {
		BoardroomInfo old = boardroomInfoMapper.selectByPrimaryKey(bi.getBiId());
		List<Integer> roleList = officeAreaAuthorityMapper.selectRoleByEmpId(user.getEmployeeId());
		if(!roleList.contains(old.getOfficeId())){
			return -1;
		}
		
		if (bi.getBiName().equals(old.getBiName()))
			return boardroomInfoMapper.updateByPrimaryKey(bi);
		if (0 < boardroomInfoMapper.existsByOfficeIdAndbiName(old.getOfficeId(), bi.getBiName()))
			return -2;
		return boardroomInfoMapper.updateByPrimaryKey(bi);
	}

	@Transactional
	public Integer status(BoardroomInfo bi, User user) {
		BoardroomInfo old = boardroomInfoMapper.selectByPrimaryKey(bi.getBiId());
		List<Integer> roleList = officeAreaAuthorityMapper.selectRoleByEmpId(user.getEmployeeId());
		if(!roleList.contains(old.getOfficeId())){
			return -1;
		}
		return boardroomInfoMapper.updateByPrimaryKey(bi);
	}

	@Transactional
	public Integer delete(BoardroomInfo bi) {
		BoardroomInfo old = boardroomInfoMapper.selectByPrimaryKey(bi.getBiId());
		if (0 != old.getStatus())
			return -1;
		Date maxTime = scheduledRecordMapper.selectMaxEndTimeBybiId(bi.getBiId());
		if(null != maxTime){
			Date nowTime = new Date();
			if(null != maxTime && nowTime.getTime() < maxTime.getTime())
				return -2;
		}
		return boardroomInfoMapper.updateByPrimaryKey(bi);
	}
	
	@Transactional
	public BoardroomInfo one(String oaaId, User user) {
		BoardroomInfo bi = boardroomInfoMapper.selectByPrimaryKey(oaaId);
		List<Integer> roleList = officeAreaAuthorityMapper.selectRoleByEmpId(user.getEmployeeId());
		if(!roleList.contains(bi.getOfficeId())){
			return null;
		}
		return boardroomInfoMapper.selectByPrimaryKey(oaaId);
	}

	@Transactional
	public PageData selectByCondition(Integer pageNo, Integer pageSize, String officeId, String status, String employeeId) {
		PageData data = new PageData();
		data.setPageNo(pageNo);
		data.setPageSize(pageSize);
		data.setTotal(boardroomInfoMapper.countByPage(officeId, status, employeeId));
		data.setData(boardroomInfoMapper.selectByPage(pageNo, pageSize, officeId, status, employeeId));
		return data;
	}
}
