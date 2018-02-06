package com.xyauto.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.xyauto.oa.Employee;
import com.xyauto.pojo.OfficeAreaAuthority;
import com.xyauto.pojo.User;
import com.xyauto.pojo.UserRole;
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
	@Value("${com.xyauto.SYSTEM_ID}")
	private String SYSTEM_ID;
	@Value("${com.xyauto.AUTHORIZE_MANAGER}")
	private String AUTHORIZE_MANAGER;

	@Autowired
	private OAService oas;
	@Autowired
	private AuthorizeManagerService officeAreaAuthorityService;

	@GetMapping("/insert")
	@ApiOperation(value = "新增", notes = "新增一条权限记录")
	ResultUtil insert(OfficeAreaAuthority oaa, @SessionAttribute(Constants.SESSION_USER) User user) {
		// role 权限管理
		List<UserRole> userRole = user.getRoleList();
		UserRole roleCheck = new UserRole(SYSTEM_ID + AUTHORIZE_MANAGER);
		if (!userRole.contains(roleCheck))
			return ResultUtil.error(Constants.ROLE_ERROR);
		// check
		String employeeId = oaa.getEmployeeId();
		String officeIds = oaa.getOfficeIds();
		if (null == employeeId || StringUtil.isEmpty(employeeId))
			return ResultUtil.error("员工编号不能为空");
		if (!employeeId.matches("^\\d{4,5}$"))
			return ResultUtil.error("员工编号格式不正确");
		if (null == officeIds || StringUtil.isEmpty(officeIds))
			return ResultUtil.error("至少选这一个授权办公区");
		String[] officeIdArray = officeIds.split(",");
		for (String string : officeIdArray) {
			if (!string.matches("^\\d+$"))
				return ResultUtil.error("授权办公区异常");
		}

		Employee e = oas.queryEmployeeById(employeeId);
		if (null == e)
			return ResultUtil.error("没有查到员工信息，请重新输入");
		oaa.setOaaId(IdUtil.getUUID());
		oaa.setEmployeeName(e.getCnName());
		oaa.setDepartmentId(Integer.toString(e.getDepartment().getDepartmentId()));
		oaa.setDepartmentName(e.getDepartment().getFullPath().replaceAll("行圆汽车-", ""));
		oaa.setFounderId(user.getEmployeeId());
		oaa.setFounderName(user.getEmployeeName());
		oaa.setAdddate(new Date());
		oaa.setIsDelete(false);
		oaa.setCreateUser(user.getEmployeeId());
		oaa.setUpdateUser(user.getEmployeeId());

		Integer result = officeAreaAuthorityService.insert(oaa);
		if (1 == result)
			return ResultUtil.success();
		if (-1 == result)
			return ResultUtil.error("员工权限已经存在");
		return ResultUtil.error(Constants.INSTER_ERROR);
	}

	@GetMapping("/update")
	@ApiOperation(value = "修改", notes = "通过ID修改一条权限记录")
	ResultUtil update(OfficeAreaAuthority oaa, @SessionAttribute(Constants.SESSION_USER) User user) {

		// role 权限管理
		List<UserRole> userRole = user.getRoleList();
		UserRole roleCheck = new UserRole(SYSTEM_ID + AUTHORIZE_MANAGER);
		if (!userRole.contains(roleCheck))
			return ResultUtil.error(Constants.ROLE_ERROR);
		// check
		String officeIds = oaa.getOfficeIds();
		if (StringUtil.isEmpty(oaa.getOaaId()))
			return ResultUtil.error(Constants.EXCEPTION);
		if (null == officeIds || StringUtil.isEmpty(officeIds))
			return ResultUtil.error("至少选这一个授权办公区");
		String[] officeIdArray = officeIds.split(",");
		for (String string : officeIdArray) {
			if (!string.matches("^\\d+$"))
				return ResultUtil.error("授权办公区异常");
		}

		OfficeAreaAuthority update = new OfficeAreaAuthority();
		update.setOaaId(oaa.getOaaId());
		update.setOfficeIds(oaa.getOfficeIds());
		update.setUpdateUser(user.getEmployeeId());
		Integer result = officeAreaAuthorityService.update(update);
		if (1 == result)
			return ResultUtil.success();
		return ResultUtil.error(Constants.UPDATE_ERROR);
	}

	@GetMapping("/delete")
	@ApiOperation(value = "删除", notes = "通过ID删除一条权限记录")
	ResultUtil delete(String oaaId, @SessionAttribute(Constants.SESSION_USER) User user) {

		// role 权限管理
		List<UserRole> userRole = user.getRoleList();
		UserRole roleCheck = new UserRole(SYSTEM_ID + AUTHORIZE_MANAGER);
		if (!userRole.contains(roleCheck))
			return ResultUtil.error(Constants.ROLE_ERROR);

		OfficeAreaAuthority delete = new OfficeAreaAuthority();
		delete.setOaaId(oaaId);
		delete.setIsDelete(true);
		delete.setUpdateUser(user.getEmployeeId());
		Integer result = officeAreaAuthorityService.update(delete);
		if (1 == result)
			return ResultUtil.success();
		return ResultUtil.error(Constants.DELETE_ERROR);
	}

	@GetMapping("/one")
	@ApiOperation(value = "查询", notes = "通过ID查询一条权限记录")
	ResultUtil one(String oaaId, @SessionAttribute(Constants.SESSION_USER) User user) {

		// role 权限管理
		List<UserRole> userRole = user.getRoleList();
		UserRole roleCheck = new UserRole(SYSTEM_ID + AUTHORIZE_MANAGER);
		if (!userRole.contains(roleCheck))
			return ResultUtil.error(Constants.ROLE_ERROR);
		// check
		if (null == oaaId || StringUtil.isEmpty(oaaId))
			return ResultUtil.error("请选择一条权限");

		OfficeAreaAuthority one = officeAreaAuthorityService.one(oaaId);
		if (null == one)
			return ResultUtil.error(Constants.SELECT_ERROR);
		return ResultUtil.success(one);
	}

	@GetMapping("/select")
	@ApiOperation(value = "查询", notes = "通过条件查询权限记录")
	ResultUtil select(Integer pageNo, Integer pageSize, String officeId, String employeeName,
			@SessionAttribute(Constants.SESSION_USER) User user) {

		// role 权限管理
		List<UserRole> userRole = user.getRoleList();
		UserRole roleCheck = new UserRole(SYSTEM_ID + AUTHORIZE_MANAGER);
		if (!userRole.contains(roleCheck))
			return ResultUtil.error(Constants.ROLE_ERROR);
		if (null == pageNo)
			return ResultUtil.error(Constants.EXCEPTION);
		// FETCH NEXT = 0 EXCEPTION
		if (null == pageSize || 0 == pageSize)
			return ResultUtil.error(Constants.EXCEPTION);
		// 是否查询所有
		if ("-2".equals(officeId))
			officeId = null;
		return ResultUtil
				.success(officeAreaAuthorityService.selectByCondition(pageNo, pageSize, officeId, employeeName));
	}
}
