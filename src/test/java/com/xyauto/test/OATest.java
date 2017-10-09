//package com.xyauto.test;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.xyauto.test.service.OAService;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class OATest {
//
//	@Autowired
//	OAService oas;
//
//	@Before
//	public void before() {
//		System.out.println(">>  测试开始  <<");
//	}
//
//	@Test
//	public void test() {
//		System.out.println(oas.queryDepartment());
//		System.out.println(oas.queryDepartment());
////		 oas.queryEmployeeByDept(11128);
////		 oas.queryEmployeeByDept(11137);
//	}
//
//	@After
//	public void after() {
//		System.out.println(">>  测试结束  <<");
//	}
//}
