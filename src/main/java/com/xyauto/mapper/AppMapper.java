package com.xyauto.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.xyauto.extend.ScheduledRecordExt;
import com.xyauto.pojo.Attendees;
import com.xyauto.pojo.BoardroomInfo;
import com.xyauto.pojo.ScheduledRecord;

/**
 * @author xusy@xingyuanauto.com
 * @version 1.0 创建时间：2017年10月11日 下午1:58:32
 */
@Mapper
public interface AppMapper {

	List<ScheduledRecordExt> findBoradSchedInfoByReq(@Param("officeId") Integer officeId, @Param("startTime") String startTime);

	List<ScheduledRecord> findScheInfoByBiId(@Param("biId") String biId, @Param("startTime") String startTime);

	Integer checkMeetTime(@Param("biId") String biId, @Param("startTime") Date startTime,
			@Param("endTime") Date endTime);

	void insertByBatch(List<Attendees> attendees);

	List<ScheduledRecordExt> findAllMeetOfSelf(@Param("employeeId") String employeeId);

	List<ScheduledRecordExt> findSingleMeetBySrId(String srId);

	Object findInfoByBiId(@Param("biId") String biId, @Param("startTime") String startTime);
}