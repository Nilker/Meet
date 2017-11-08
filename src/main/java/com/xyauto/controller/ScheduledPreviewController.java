package com.xyauto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.xyauto.pojo.User;
import com.xyauto.pojo.UserRole;
import com.xyauto.service.ScheduledPreviewService;
import com.xyauto.util.Constants;
import com.xyauto.util.ResultUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 预定预览接口
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月10日 下午2:40:49
 */
@RestController
@RequestMapping("sp")
@Api(description = "预定预览")
public class ScheduledPreviewController {

	@Value("${com.xyauto.SYSTEM_ID}")
	private String SYSTEM_ID;
	@Value("${com.xyauto.SCHEDULED_PREVIEW}")
	private String SCHEDULED_PREVIEW;

	@Autowired
	private ScheduledPreviewService scheduledPreviewService;

	@GetMapping("/select")
	@ApiOperation(value = "查询", notes = "通过条件查询会议室预定信息")
	ResultUtil select(Integer pageNo, Integer pageSize, String officeId, String biId, String conferenceStatus,
			String employeeName, @SessionAttribute(Constants.SESSION_USER) User user) {

		// role 会议室管理
		List<UserRole> userRole = user.getRoleList();
		UserRole roleCheck = new UserRole(SYSTEM_ID + SCHEDULED_PREVIEW);
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
		if ("-2".equals(biId))
			biId = null;
		if ("-2".equals(conferenceStatus))
			conferenceStatus = null;
		if ("-2".equals(employeeName))
			employeeName = null;

		return ResultUtil.success(scheduledPreviewService.selectByPage(pageNo, pageSize, officeId, biId,
				conferenceStatus, employeeName, user));
	}
}
