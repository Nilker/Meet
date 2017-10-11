package com.xyauto.mapper;

import com.xyauto.pojo.ScheduledRecord;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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
}