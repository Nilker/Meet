package com.xyauto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.xyauto.pojo.User;
import com.xyauto.pojo.UserRole;
import com.xyauto.service.UtilsService;
import com.xyauto.util.Constants;
import com.xyauto.util.ResultUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 工具接口
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月10日 下午2:40:49
 */
@RestController
@Api(description = "登录信息")
public class UtilsController {

	@Value("${com.xyauto.SYSTEM_ID}")
	private String SYSTEM_ID;
	@Value("${com.xyauto.MEETING_MANAGER}")
	private String MEETING_MANAGER;
	@Value("${com.xyauto.SCHEDULED_PREVIEW}")
	private String SCHEDULED_PREVIEW;
	@Value("${com.xyauto.AUTHORIZE_MANAGER}")
	private String AUTHORIZE_MANAGER;

	@Autowired
	private UtilsService utilsService;

	@GetMapping("/getLoginInfo")
	@ApiOperation(value = "查询", notes = "获取用户登录信息")
	ResultUtil getLoginInfo(@SessionAttribute(Constants.SESSION_USER) User user) {
		return ResultUtil.success(user);
	}

	@GetMapping("/getMeetingByOfficeId")
	@ApiOperation(value = "会议室下拉框查询", notes = "通过办公区ID获取会议室信息")
	ResultUtil getMeetingByOfficeId(Integer oid, @SessionAttribute(Constants.SESSION_USER) User user) {
		// role 会议室管理
		List<UserRole> userRole = user.getRoleList();
		UserRole scheduledPreviewRole = new UserRole(SYSTEM_ID + SCHEDULED_PREVIEW);
		if (!userRole.contains(scheduledPreviewRole))
			return ResultUtil.error(Constants.ROLE_ERROR);
		if (-2 == oid)
			oid = null;
		return ResultUtil.success(utilsService.getMeetingByOfficeId(oid, user));
	}

	@GetMapping("/getOfficeInfoByRole")
	@ApiOperation(value = "办公区下拉框查询", notes = "通过角色权限获取办公区信息")
	ResultUtil getOfficeInfoByRole(@SessionAttribute(Constants.SESSION_USER) User user) {
		// role 会议室管理
		List<UserRole> userRole = user.getRoleList();
		UserRole meetingManagerRole = new UserRole(SYSTEM_ID + MEETING_MANAGER);
		UserRole scheduledPreviewRole = new UserRole(SYSTEM_ID + SCHEDULED_PREVIEW);
		if (userRole.contains(meetingManagerRole) || userRole.contains(scheduledPreviewRole))
			return ResultUtil.success(utilsService.getOfficeInfoByRole(user.getEmployeeId()));
		return ResultUtil.error(Constants.ROLE_ERROR);
	}

	@GetMapping("/getOfficeInfoAll")
	@ApiOperation(value = "办公区下拉框查询", notes = "获取办公区信息")
	ResultUtil getOfficeInfoAll(@SessionAttribute(Constants.SESSION_USER) User user) {
		// role 权限管理
		List<UserRole> userRole = user.getRoleList();
		UserRole roleCheck = new UserRole(SYSTEM_ID + AUTHORIZE_MANAGER);
		if (userRole.contains(roleCheck))
			return ResultUtil.success(utilsService.getOfficeInfoAll());
		return ResultUtil.error(Constants.ROLE_ERROR);
	}
}
