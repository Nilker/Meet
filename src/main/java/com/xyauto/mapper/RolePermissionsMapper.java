package com.xyauto.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.xyauto.util.Constants;

@Mapper
public interface RolePermissionsMapper {
	
	@Select("	select distinct "
			+ "		t2.ModuleID "
			+ "	from "
			+ "		Organization.dbo.UserRole t1 "
			+ "	inner join "
			+ "		Organization.dbo.RoleModule t2 "
			+ "	on "
			+ "		t1.RoleID = t2.RoleID "
			+ "	where "
			+ "		t1.SysID = '" + Constants.SYSTEM_ID + "' "
			+ "	and "
			+ "		t1.UserID = #{empId}")
	public List<String> getUserRole(Integer empId);
}
