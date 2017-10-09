package com.xyauto.test;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xyauto.pojo.OfficeLocation;
import com.xyauto.service.OfficeLocationService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DBTest {
	
	@Autowired
	OfficeLocationService officeLocationService;
	
	@Before
	public void before() {
		System.out.println(">>  测试开始  <<");
	}

	@Test
	public void test() {
		List<OfficeLocation> list = officeLocationService.selectAll();
		for (OfficeLocation officeLocation : list) {
			System.out.println(officeLocation);
		}
	}

	@After
	public void after() {
		System.out.println(">>  测试结束  <<");
	}
}
