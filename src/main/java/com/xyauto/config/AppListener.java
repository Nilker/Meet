package com.xyauto.config;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.xyauto.mapper.AppMapper;
import com.xyauto.util.CacheUtil;
import com.xyauto.util.PropertiesUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
//import com.xyauto.util.HttpUtil;
import org.springframework.boot.CommandLineRunner;

@Component
@Order(value = 2)
@Slf4j
public class AppListener implements CommandLineRunner {
	@Autowired
	private AppMapper mapper;
	@Override
	public void run(String... args) throws Exception {
		log.info(">> Initializing loading start <<");
		CacheUtil.getScheMap(mapper);
		log.info(">> Initializing loading end <<");
	}

}
