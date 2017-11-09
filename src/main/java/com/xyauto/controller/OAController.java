package com.xyauto.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.xyauto.service.OAService;
import com.xyauto.util.ResultUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * OA接口
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年3月25日 下午1:08:22
 */
@RestController
@RequestMapping("/oa")
@Api(description = "OA")
public class OAController {

	@Autowired
	private OAService oas;

	@GetMapping("/queryAllEmployee")
	@ApiOperation(value = "查询员工名称", notes = "查询所有员工名称")
	public ResultUtil queryAllEmployee() {
		ResultUtil result = null;
		try {
			result = ResultUtil.success(oas.queryAllEmployee());
		} catch (JsonParseException e) {
			result = ResultUtil.error("员工名称列表 json 转换异常");
			e.printStackTrace();
		} catch (JsonMappingException e) {
			result = ResultUtil.error("员工名称列表 json 转换异常");
			e.printStackTrace();
		} catch (IOException e) {
			result = ResultUtil.error("员工名称列表 json 转换异常");
			e.printStackTrace();
		}
		return result;
	}
}
