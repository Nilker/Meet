package com.xyauto.mapper;

import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.xyauto.pojo.BoardroomInfo;
import com.xyauto.pojo.ScheduledRecord;

/**
 * @author xusy@xingyuanauto.com
 * @version 1.0 创建时间：2017年10月11日 下午1:58:32
 */
@Mapper
public interface AppMapper {

	List<BoardroomInfo> findBoardInfoByOfficeId(int officeId);

	List<ScheduledRecord> findScheInfoByReq(@Param("officeId") Integer officeId, @Param("startTime") String startTime);

	List<ScheduledRecord> findScheInfoByBiId(@Param("biId") String biId, @Param("startTime") String startTime);

}