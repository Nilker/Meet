package com.xyauto.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xyauto.extend.ScheduledRecordExt;
import com.xyauto.mapper.AppMapper;
import com.xyauto.service.AppService;
import com.xyauto.util.DateUtils;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AppTest {

	@Autowired
	AppMapper appMapper;
	@Autowired
	AppService appService;

	@Before
	public void before() {
		System.out.println(">>  测试开始  <<");
	}

	/**
	 * 
	 */
//	@Test
//	public void checkMeetTimeTest() {
//		Integer count = appMapper.checkMeetTime("1", DateUtils.str2Date("2017-10-11 07:00:59"),
//				DateUtils.str2Date("2017-10-11 11:30:00"));
//		System.out.println("obj{}" + count);
//	}
	@Test
	public void insertScheduleTest() {
		ScheduledRecordExt schedule=new ScheduledRecordExt();
		String[] eids= {"0218","0212","0289"};
		schedule.setBiId("1");
		schedule.setEmployeeId("0281");
		schedule.setEmployee_ids(eids);
		schedule.setStartTime(DateUtils.str2Date("2017-10-14 12:30:59"));
		schedule.setEndTime(DateUtils.str2Date("2017-10-14 13:30:00"));
		schedule.setMeetingTheme("测试3");
		schedule.setOfficeId(1);
		appService.insertSchedule(schedule);
	}
	@Test
	public void findInfoByOfficeId() {
		System.out.println(appService.findInfoByOfficeId(1, "2017-10-11"));
	}

	@After
	public void after() {
		System.out.println(">>  测试结束  <<");
	}
}
