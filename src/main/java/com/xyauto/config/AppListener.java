package com.xyauto.config;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.xyauto.mapper.AppMapper;
import com.xyauto.util.CacheUtil;

import org.springframework.beans.factory.annotation.Autowired;
//import com.xyauto.util.HttpUtil;
import org.springframework.boot.CommandLineRunner;

@Component
@Order(value = 2)
public class AppListener implements CommandLineRunner {
	@Autowired
	private AppMapper mapper;
	@Override
	public void run(String... args) throws Exception {
//		System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作111111111<<<<<<<<<<<<<");
		CacheUtil.getScheMap(mapper);
//		System.out.println(">>>>>>>>>>>>>>>服务启动执行成功<<<<<<<<<<<<<");
	}

}
