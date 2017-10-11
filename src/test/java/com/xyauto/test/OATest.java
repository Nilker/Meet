package com.xyauto.test;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xyauto.oa.Department;
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
	public void test() {
		Employee e = oas.queryEmployeeById("0287");
		e = oas.queryEmployeeById("0287");
		System.out.println(e.getCnName());
	}

	@After
	public void after() {
		System.out.println(">>  测试结束  <<");
	}
}
