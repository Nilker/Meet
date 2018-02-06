package com.xyauto.mapper;

import com.xyauto.pojo.ScheduledRecord;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author qiaom@xingyuanauto.com
 * @version	创建时间：2017年10月9日 下午1:58:32
 */
@Mapper
public interface ScheduledRecordMapper {
	
	int insert(ScheduledRecord record);
    int deleteByPrimaryKey(String srId);
    int updateByPrimaryKey(ScheduledRecord record);
    ScheduledRecord selectByPrimaryKey(String srId);
    List<ScheduledRecord> selectAll();
    Date selectMaxStartTimeBybiId(String biId);
    Date selectMaxEndTimeBybiId(String biId);
    Integer countByPage(
    		@Param("officeId") String officeId,
    		@Param("biId") String biId,
    		@Param("conferenceStatus") String conferenceStatus,
    		@Param("employeeName") String employeeName,
    		@Param("employeeId") String employeeId);
    List<ScheduledRecord> selectByPage(
    		@Param("pageNo") Integer pageNo, 
    		@Param("pageSize") Integer pageSize,
    		@Param("officeId") String officeId,
    		@Param("biId") String biId,
    		@Param("conferenceStatus") String conferenceStatus,
    		@Param("employeeName") String employeeName,
    		@Param("employeeId") String employeeId);
    int updateStatusByQr(@Param("biId") String biId,@Param("employeeId") String employeeId);
}