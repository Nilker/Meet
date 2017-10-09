package com.xyauto.mapper;

import com.xyauto.pojo.Attendees;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author qiaom@xingyuanauto.com
 * @version	创建时间：2017年10月9日 下午1:58:32
 */

@Mapper
public interface AttendeesMapper {
	
    int insert(Attendees record);
    List<Attendees> selectAll();
}