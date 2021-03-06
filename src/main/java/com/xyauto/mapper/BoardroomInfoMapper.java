package com.xyauto.mapper;

import com.xyauto.pojo.BoardroomInfo;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author qiaom@xingyuanauto.com
 * @version	创建时间：2017年10月9日 下午1:58:32
 */
@Mapper
public interface BoardroomInfoMapper {

    int insert(BoardroomInfo record);
    int updateByPrimaryKey(BoardroomInfo record);
    BoardroomInfo selectByPrimaryKey(String biId);
    Integer countByPage(
    		@Param("officeId") String officeId,
    		@Param("status") String status,
    		@Param("employeeId") String employeeId);
    Integer existsByOfficeIdAndbiName(
    		@Param("officeId") Integer officeId,
    		@Param("biName") String biName);
    List<BoardroomInfo> selectByOfficeId(
    		@Param("officeId") Integer officeId,
    		@Param("employeeId") String employeeId);
    List<BoardroomInfo> selectByPage(
    		@Param("pageNo") Integer pageNo, 
    		@Param("pageSize") Integer pageSize,
    		@Param("officeId") String officeId,
    		@Param("status") String status,
    		@Param("employeeId") String employeeId);
}