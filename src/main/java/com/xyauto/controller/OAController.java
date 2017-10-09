package com.xyauto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyauto.service.OAService;
import com.xyauto.util.ResultUtil;

/**
 * OA接口
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年3月25日 下午1:08:22
 */
@RestController
@RequestMapping("/oa")
public class OAController {

	@Autowired
	private OAService oas;

	@GetMapping("/department")
	public ResultUtil queryDepartment() {
		return ResultUtil.success(oas.queryDepartment());
	}

	@GetMapping("/employeeByDept/{id}")
	public ResultUtil queryEmployeeByDept(@PathVariable Integer id) {
		return ResultUtil.success(oas.queryEmployeeByDept(id));
	}
	
	@GetMapping("/queryEmployeeByDeptIncludeChildren/{id}")
	public ResultUtil queryEmployeeByDeptIncludeChildren(@PathVariable Integer id) {
		return ResultUtil.success(oas.queryEmployeeByDeptIncludeChildren(id));
	}
}
