package com.xyauto.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xyauto.service.AppService;

import lombok.extern.slf4j.Slf4j;

/**
 * app
 * 
 * @author xusy@xingyuanauto.com
 * @version 创建时间：2017年10月17日
 */
@Controller
@RequestMapping("app")
@Slf4j
public class AppController {
	@Autowired
	private AppService appService;

	@RequestMapping(value = "scheduled/getInfoByOfficeId/{officeId}/{startTime}", method = RequestMethod.GET)
	public ResponseEntity<Map> findInfoByOfficeId(@PathVariable int officeId, @PathVariable String startTime) {
		Map<String, Object> scheduledInfoMap = appService.findInfoByOfficeId(officeId, startTime);
		return ResponseEntity.ok(scheduledInfoMap);
	}

	@RequestMapping(value = "scheduled/test")
	public String findInfoByOfficeId() {
		// Map<String, Object> scheduledInfoMap = appService.getInfoByOfficeId(officeId,
		// startTime);
		System.out.println("test");
		return "app/boardroom";
	}

}
