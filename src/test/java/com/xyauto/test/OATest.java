package com.xyauto.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

	/**
	 * 
	 */
	@Test
	public void test() {
		Employee e = oas.queryEmployeeById("0289");
//		e = oas.queryEmployeeById("0289");
		// System.out.println(e.getCnName());
		System.out.println(e.getCnName());
		System.out.println(e.getEmployeeNumber());
		System.out.println(e.getDomainAccount());
		System.out.println(e.getEmail());
		System.out.println(e.getEmployeeID());
		System.out.println(e.getMobile());
		System.out.println(e.getOfficePlaceID());
		System.out.println(e.getWorkCharacter());
		System.out.println(e.getDepartment().getDepartmentId());
		System.out.println(e.getDepartment().getName());
		System.out.println(e.getDepartment().getDescription());
//		System.out.println(oas.queryDepartment());

	}

	@After
	public void after() {
		System.out.println(">>  测试结束  <<");
	}
}
