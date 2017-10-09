package com.xyauto.mapper;

import com.xyauto.pojo.OfficeAreaAuthority;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author qiaom@xingyuanauto.com
 * @version	创建时间：2017年10月9日 下午1:58:32
 */

@Mapper
public interface OfficeAreaAuthorityMapper {
    
    int insert(OfficeAreaAuthority record);
    int deleteByPrimaryKey(String oaaId);
    int updateByPrimaryKey(OfficeAreaAuthority record);
    OfficeAreaAuthority selectByPrimaryKey(String oaaId);
    List<OfficeAreaAuthority> selectAll();
}