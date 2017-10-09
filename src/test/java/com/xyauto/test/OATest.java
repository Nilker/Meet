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
//		List<Department> queryDepartment = oas.queryDepartment();
//		queryDepartment = oas.queryDepartment();
//		for (Department department : queryDepartment) {
//			System.out.println(department.getName());
//		}
//		System.out.println("~~~~~~~~~~~~~~~~");
		List<Employee> queryEmployeeByDept = oas.queryEmployeeByDept(11137);
		queryEmployeeByDept = oas.queryEmployeeByDeptIncludeChildren(11128);
		queryEmployeeByDept = oas.queryEmployeeByDept(11137);
		queryEmployeeByDept = oas.queryEmployeeByDeptIncludeChildren(11128);
//		for (Employee employee : queryEmployeeByDept) {
//			System.out.println(employee.getCnName());
//		}
//		 oas.queryEmployeeByDept(11137)
	}

	@After
	public void after() {
		System.out.println(">>  测试结束  <<");
	}
}
