package com.xyauto.test;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xyauto.util.PropertiesUtil;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OATest {

	@Before
	public void before() {
		System.out.println(">>  测试开始  <<");
	}

	@Test
	public void test() throws IOException {
		System.out.println(PropertiesUtil.getPropertiesByKey("MESSAGE_API_URL"));
	}

	@After
	public void after() {
		System.out.println(">>  测试结束  <<");
	}
}
