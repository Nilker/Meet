package com.xyauto.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyauto.pojo.OfficeAreaAuthority;
import com.xyauto.service.OfficeAreaAuthorityService;
import com.xyauto.util.Constants;
import com.xyauto.util.IdUtil;
import com.xyauto.util.ResultUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 权限管理接口
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月10日 下午2:40:49
 */
@RestController
@RequestMapping("am")
@Api(description="权限管理")
public class AuthorizeManagerController {

	@Autowired
	private OfficeAreaAuthorityService officeAreaAuthorityService;

	@GetMapping("/insert")
	@ApiOperation(value = "新增", notes = "新增一条权限记录")
	ResultUtil insert(OfficeAreaAuthority oaa) {

		oaa.setOaaId(IdUtil.getUUID());
		// TODO
		oaa.setEmployeeName("测试");
		// TODO
		oaa.setDepartmentId("test");
		// TODO
		oaa.setDepartmentName("测试");
		// TODO
		oaa.setFounderId("test");
		// TODO
		oaa.setFounderName("测试");
		oaa.setAdddate(new Date());
		// TODO
		oaa.setIsDelete(false);
		// TODO
		oaa.setCreateUser("测试");
		oaa.setUpdateTime(new Date());
		// TODO
		oaa.setUpdateUser("测试");

		Integer result = officeAreaAuthorityService.insert(oaa);
		if (result == 1) {
			return ResultUtil.success();
		}
		return ResultUtil.error(Constants.INSTER_Error);
	}

	@GetMapping("/update")
	@ApiOperation(value = "修改", notes = "通过ID修改一条权限记录")
	ResultUtil update(OfficeAreaAuthority oaa) {
		oaa.setUpdateTime(new Date());
		// TODO
		oaa.setUpdateUser("测试");
		Integer result = officeAreaAuthorityService.update(oaa);
		if (result == 1) {
			return ResultUtil.success();
		}
		return ResultUtil.error(Constants.UPDATE_Error);
	}

	@GetMapping("/delete")
	@ApiOperation(value = "删除", notes = "通过ID删除一条权限记录")
	ResultUtil delete(String oaaId) {
		OfficeAreaAuthority oaa = new OfficeAreaAuthority();
		oaa.setOaaId(oaaId);
		oaa.setIsDelete(true);
		oaa.setUpdateTime(new Date());
		// TODO
		oaa.setUpdateUser("测试");
		Integer result = officeAreaAuthorityService.update(oaa);
		if (result == 1) {
			return ResultUtil.success();
		}
		return ResultUtil.error(Constants.DELETE_Error);
	}

	@GetMapping("/all")
	@ApiOperation(value = "查询", notes = "查询全部权限记录")
	ResultUtil all() {
		return ResultUtil.success(officeAreaAuthorityService.all());
	}
}
