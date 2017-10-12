package com.xyauto.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyauto.mapper.BoardroomInfoMapper;
import com.xyauto.mapper.ScheduledRecordMapper;
import com.xyauto.pojo.BoardroomInfo;
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
	private BoardroomInfoMapper boardroomInfoMapper;
	@Autowired
	private ScheduledRecordMapper scheduledRecordMapper;

	// TODO transaction
	public Integer insert(BoardroomInfo bi) {
		if (0 < boardroomInfoMapper.existsByOfficeIdAndbiName(bi.getOfficeId(), bi.getBiName()))
			return -1;
		return boardroomInfoMapper.insert(bi);
	}

	// TODO transaction
	public Integer update(BoardroomInfo bi) {
		BoardroomInfo old = boardroomInfoMapper.selectByPrimaryKey(bi.getBiId());
		if (bi.getOfficeId().equals(old.getOfficeId()) && bi.getBiName().equals(old.getBiName()))
			return boardroomInfoMapper.updateByPrimaryKey(bi);
		if (0 < boardroomInfoMapper.existsByOfficeIdAndbiName(bi.getOfficeId(), bi.getBiName()))
			return -1;
		return boardroomInfoMapper.updateByPrimaryKey(bi);
	}

	// TODO transaction
	public Integer status(BoardroomInfo bi) {
		return boardroomInfoMapper.updateByPrimaryKey(bi);
	}

	// TODO transaction
	public Integer delete(BoardroomInfo bi) {
		BoardroomInfo old = boardroomInfoMapper.selectByPrimaryKey(bi.getBiId());
		if (0 != old.getStatus())
			return -1;
		Date maxTime = scheduledRecordMapper.selectMaxEndTimeBybiId(bi.getBiId());
		Date nowTime = new Date();
		if(null != maxTime && nowTime.getTime() < maxTime.getTime())
			return -2;
		return boardroomInfoMapper.updateByPrimaryKey(bi);
	}

	// TODO transaction
	public PageData selectByCondition(Integer pageNo, Integer pageSize, Integer officeId, Byte status) {
		PageData data = new PageData();
		data.setPageNo(pageNo);
		data.setPageSize(pageSize);
		data.setTotal(boardroomInfoMapper.countByNotDel());
		data.setData(boardroomInfoMapper.selectByPage(pageNo, pageSize, officeId, status));
		return data;
	}
}
