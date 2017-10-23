package com.xyauto.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.xyauto.pojo.OfficeAreaAuthority;

/**
 * @author qiaom@xingyuanauto.com
 * @version	创建时间：2017年10月9日 下午1:58:32
 */
@Mapper
public interface OfficeAreaAuthorityMapper {
    
	Integer insert(OfficeAreaAuthority record);
	Integer updateByPrimaryKey(OfficeAreaAuthority record);
	OfficeAreaAuthority selectByPrimaryKey(String oaaId);
    Integer countByPage(
    		@Param("officeId") String officeId,
    		@Param("employeeName") String employeeName);
    Integer existsByEmployeeId(String employeeId);
    List<OfficeAreaAuthority> selectByPage(
    		@Param("pageNo") Integer pageNo, 
    		@Param("pageSize") Integer pageSize,
    		@Param("officeId") String officeId,
    		@Param("employeeName") String employeeName);
    List<Integer> selectRoleByEmpId(String employeeId);
}