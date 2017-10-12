package com.xyauto.test;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xyauto.mapper.RolePermissionsMapper;
import com.xyauto.service.LoginInfoService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DBTest {

	@Autowired
	private LoginInfoService loginInfoService;

	@Before
	public void before() {
		System.out.println(">>  测试开始  <<");
	}

	@Test
	public void test() {
		List<String> list = loginInfoService.getUserRole(5418);
		for (String string : list) {
			System.out.println(string);
		}
	}

	@After
	public void after() {
		System.out.println(">>  测试结束  <<");
	}
}
