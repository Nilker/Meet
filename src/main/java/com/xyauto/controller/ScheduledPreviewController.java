package com.xyauto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyauto.service.ScheduledPreviewService;
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

	@Autowired
	private ScheduledPreviewService scheduledPreviewService;

	@GetMapping("/selectOfficeAll")
	@ApiOperation(value = "查询条件列表", notes = "查询办公区信息")
	ResultUtil selectOfficeAll() {

		// TODO session role

		return ResultUtil.success(scheduledPreviewService.selectOfficeAll());
	}

	@GetMapping("/selectBoardroomInfoByOfficeId")
	@ApiOperation(value = "查询条件列表", notes = "通过办公区ID查询会议室信息")
	ResultUtil selectBoardroomInfoByOfficeId(Integer id) {

		// TODO session role
		
		// TODO oa role

		return ResultUtil.success(scheduledPreviewService.selectBoardroomInfoByOfficeId(id == -2 ? null : id));
	}
	
	@GetMapping("/selectByPage")
	@ApiOperation(value = "查询", notes = "通过条件查询会议室预定信息")
	ResultUtil selectByPage(Integer pageSize, Integer officeId, String biId, Integer conferenceStatus,
			String departmentName) {

		// TODO session role
		
		// TODO oa role
		return ResultUtil.success(scheduledPreviewService.selectByPage(pageSize, officeId, biId, conferenceStatus, departmentName));
	}
}
