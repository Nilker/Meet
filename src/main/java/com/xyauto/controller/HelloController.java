package com.xyauto.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/")
	String home() {
		return "Hello World! SBoot！！！";
	}

	@RequestMapping("/hello")
	String hello() {
		return "hello";
	}

	@RequestMapping("/hello/{myName}")
	String index(@PathVariable String myName) {
		return "Hello " + myName + "!!!Sboot";
	}
	
//	@RequestMapping("/office/{id}")
//	OfficeLocation office(@PathVariable Integer id) {
//		return officeLocationService.selectOneById(id);
//	}
}
