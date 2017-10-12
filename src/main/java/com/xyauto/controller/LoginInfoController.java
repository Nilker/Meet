package com.xyauto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyauto.util.ResultUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户登录信息接口
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月10日 下午2:40:49
 */
@RestController
@Api(description = "登录信息")
public class LoginInfoController {

	@GetMapping("/getLoginInfo")
	@ApiOperation(value = "查询", notes = "获取用户登录信息")
	ResultUtil getLoginInfo() {
		// TODO return session user
		return ResultUtil.success(null);
	}
}
