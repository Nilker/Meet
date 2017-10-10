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
import com.xyauto.service.OfficeAreaAuthorityService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DBTest {

	@Autowired
	OfficeAreaAuthorityService officeAreaAuthorityService;

	@Before
	public void before() {
		System.out.println(">>  测试开始  <<");
	}

	@Test
	public void test() {
		List<OfficeAreaAuthority> list = officeAreaAuthorityService.all();
		for (OfficeAreaAuthority officeAreaAuthority : list) {
			System.out.println(officeAreaAuthority);
		}
	}

	@After
	public void after() {
		System.out.println(">>  测试结束  <<");
	}
}
