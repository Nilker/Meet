package com.xyauto.test;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xyauto.pojo.OfficeAreaAuthority;
//import com.xyauto.service.OfficeAreaAuthorityService;
import com.xyauto.util.DateUtils;

@SpringBootTest
// @RunWith(SpringRunner.class)
public class DateTest {

	@Before
	public void before() {
		System.out.println(">>  测试开始  <<");
	}

	/**
	 * 
	 */
	/**
	 * 
	 */
	@Test
	public void timeTest() {
		 DateUtils.str2Date("2017-10-11 11:30:59");
		 System.out.println(DateUtils.date2Str(DateUtils.str2Date("2017-10-11 11:30:00"), DateUtils.YMDHM)+":00");
	}
	@After
	public void after() {
		System.out.println(">>  测试结束  <<");
	}
}
