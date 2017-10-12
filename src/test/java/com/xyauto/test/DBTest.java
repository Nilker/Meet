package com.xyauto.test;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xyauto.pojo.OfficeAreaAuthority;
import com.xyauto.service.AuthorizeManagerService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DBTest {

	@Autowired
	AuthorizeManagerService officeAreaAuthorityService;

	@Before
	public void before() {
		System.out.println(">>  测试开始  <<");
	}

	@Test
	public void test() {
	}

	@After
	public void after() {
		System.out.println(">>  测试结束  <<");
	}
}
