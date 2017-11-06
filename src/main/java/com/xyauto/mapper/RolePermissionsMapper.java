package com.xyauto.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xyauto.pojo.UserRole;

@Mapper
public interface RolePermissionsMapper {
	
	@Select("	select distinct "
			+ "		t2.ModuleID,"
			+ "		t3.Url"
			+ "	from "
			+ "		Organization.dbo.UserRole t1 "
			+ "	inner join "
			+ "		Organization.dbo.RoleModule t2 "
			+ "	on "
			+ "		t1.RoleID = t2.RoleID "
			+ "	inner join "
			+ "		Organization.dbo.ModuleInfo t3 "
			+ "	on "
			+ "		t2.ModuleID = t3.ModuleID "
			+ "	where "
			+ "		t1.SysID = #{systemId}"
			+ "	and "
			+ "		t1.UserID = #{empId}"
			+ " order by 1")
	public List<UserRole> getUserRole(@Param("systemId") String systemId, @Param("empId") Integer empId);
}
