package com.xyauto.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyauto.oa.Employee;
import com.xyauto.pojo.OfficeAreaAuthority;
import com.xyauto.service.AuthorizeManagerService;
import com.xyauto.service.OAService;
import com.xyauto.util.Constants;
import com.xyauto.util.IdUtil;
import com.xyauto.util.ResultUtil;
import com.xyauto.util.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 办公区权限管理接口
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月10日 下午2:40:49
 */
@RestController
@RequestMapping("am")
@Api(description = "权限管理")
public class AuthorizeManagerController {

	@Autowired
	private OAService oas;
	@Autowired
	private AuthorizeManagerService officeAreaAuthorityService;

	@GetMapping("/insert")
	@ApiOperation(value = "新增", notes = "新增一条权限记录")
	ResultUtil insert(OfficeAreaAuthority oaa) {

		// TODO session role

		String employeeId = oaa.getEmployeeId();
		if (null == employeeId || StringUtil.isEmpty(employeeId))
			return ResultUtil.error("员工ID不能为空");
		if (!employeeId.matches("^\\d{4}$"))
			return ResultUtil.error("员工ID格式不正确");

		Employee e = oas.queryEmployeeById(employeeId);
		oaa.setOaaId(IdUtil.getUUID());
		oaa.setEmployeeName(e.getCnName());
		oaa.setDepartmentId(Integer.toString(e.getDepartment().getDepartmentId()));
		oaa.setDepartmentName(e.getDepartment().getFullPath().replaceAll("行圆汽车-", ""));
		// TODO
		oaa.setFounderId("test");
		// TODO
		oaa.setFounderName("测试");
		oaa.setAdddate(new Date());
		oaa.setIsDelete(false);
		// TODO
		oaa.setCreateUser("测试");
		// TODO
		oaa.setUpdateUser("测试");

		Integer result = officeAreaAuthorityService.insert(oaa);
		if (1 == result)
			return ResultUtil.success();
		if (-1 == result)
			return ResultUtil.error("员工权限已经存在");
		return ResultUtil.error(Constants.INSTER_ERROR);
	}

	@GetMapping("/update")
	@ApiOperation(value = "修改", notes = "通过ID修改一条权限记录")
	ResultUtil update(OfficeAreaAuthority oaa) {

		// TODO session role

		if (StringUtil.isEmpty(oaa.getOaaId()))
			return ResultUtil.error("数据错误");
		if (StringUtil.isEmpty(oaa.getOfficeIds())) 
			return ResultUtil.error("请至少选择一个办公区");

		OfficeAreaAuthority update = new OfficeAreaAuthority();
		update.setOaaId(oaa.getOaaId());
		update.setOfficeIds(oaa.getOfficeIds());
		// TODO
		update.setUpdateUser("测试");
		Integer result = officeAreaAuthorityService.update(update);
		if (result == 1) {
			return ResultUtil.success();
		}
		return ResultUtil.error(Constants.UPDATE_ERROR);
	}

	@GetMapping("/delete")
	@ApiOperation(value = "删除", notes = "通过ID删除一条权限记录")
	ResultUtil delete(String oaaId) {

		// TODO session role

		OfficeAreaAuthority delete = new OfficeAreaAuthority();
		delete.setOaaId(oaaId);
		delete.setIsDelete(true);
		// TODO
		delete.setUpdateUser("测试");
		Integer result = officeAreaAuthorityService.update(delete);
		if (result == 1) {
			return ResultUtil.success();
		}
		return ResultUtil.error(Constants.DELETE_ERROR);
	}

	@GetMapping("/select")
	@ApiOperation(value = "查询", notes = "通过条件查询权限记录")
	ResultUtil select(Integer pageNo, Integer pageSize) {

		// TODO session role

		return ResultUtil.success(officeAreaAuthorityService.selectByCondition(pageNo, pageSize, null, null));
	}
}
