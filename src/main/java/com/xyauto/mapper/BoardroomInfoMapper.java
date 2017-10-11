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
    Integer countByNotDel();
    Integer existsByOfficeIdAndbiName(
    		@Param("officeId") Integer officeId,
    		@Param("biName") String biName);
    List<BoardroomInfo> selectByCondition(
    		@Param("pageNo") Integer pageNo, 
    		@Param("pageSize") Integer pageSize,
    		@Param("officeId") Integer officeId,
    		@Param("status") Byte status);
}