package com.xyauto.test;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.xyauto.oa.Employee;
import com.xyauto.service.OAService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OATest {

	@Autowired
	OAService oas;

	@Before
	public void before() {
		System.out.println(">>  测试开始  <<");
	}

	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException {
		oas.queryAllEmployee();
		oas.queryAllEmployee();
	}

	@After
	public void after() {
		System.out.println(">>  测试结束  <<");
	}
}
