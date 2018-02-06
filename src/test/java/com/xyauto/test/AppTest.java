package com.xyauto.test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xyauto.extend.ScheduledRecordExt;
import com.xyauto.mapper.AppMapper;
import com.xyauto.pojo.ScheduledRecord;
import com.xyauto.service.AppService;
import com.xyauto.util.DateUtils;
import com.xyauto.util.StringUtil;

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
	@Test
	public void checkMeetTimeTest() {
		Date now = new Date();
		Date now_10 = new Date(now.getTime() - 900000); //10分钟前的时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
		String nowTime_10 = dateFormat.format(now_10);
		System.out.println(nowTime_10);
		// System.out.println(request.getSession().getAttribute("appUser"));
		// Integer count = appMapper.checkMeetTime("1", DateUtils.str2Date("2017-10-11
		// 07:00:59"),
		// DateUtils.str2Date("2017-10-11 11:30:00"));
		// System.out.println("obj{}" + count);
//		System.out.println(Integer.toBinaryString(0000));
	}

	@Test
	public void insertScheduleTest() throws ParseException, IOException, InterruptedException, ExecutionException {
		ScheduledRecordExt schedule = new ScheduledRecordExt();
		String[] eids = { "9806", "1102", "1192" };
		schedule.setBiId("1b2249f4922945e88ff3822d5f14b5de");
		schedule.setEmployeeId("1192");
		schedule.setEmployeeIds(eids);
		schedule.setStartTime(DateUtils.str2Date("2018-2-10 10:30:59"));// 开始时间只能为59秒
		schedule.setEndTime(DateUtils.str2Date("2018-2-10 11:00:00"));// 结束时间只能为00秒
		schedule.setMeetingTheme("主题1");
		schedule.setOfficeId(33);
		schedule.setStatus(-1);
		appService.insertSchedule(schedule);
	}

	@Test
	public void findBoradSchedInfoByReq() {
		List<ScheduledRecordExt> findBoradSchedInfoByReq = appMapper.findBoradSchedInfoByReq(1, "2017-10-11");
		for (ScheduledRecordExt scheduledRecord : findBoradSchedInfoByReq) {
			scheduledRecord.setStartEndTimeArr(StringUtil.convertStrToArray(scheduledRecord.getSrId()));
		}
	}

	@Test
	public void findInfoByOfficeId() {
		System.out.println(appService.findInfoByOfficeId(1, "2017-10-11"));
	}

	@Test
	public void findAllMeetOfSelf() {
		// List<ScheduledRecordExt> findAllMeetOfSelf =
		// appService.findAllMeetOfSelf("0289", DateUtils.str2Date("2017-10-11",
		// DateUtils.YMD));
		// System.out.println(findAllMeetOfSelf.size());
	}

	@Test
	public void findSingleMeetBySrId() {
		List<ScheduledRecordExt> findAllMeetOfSelf = appService
				.findSingleMeetBySrId("7228c33311a045089378210050740e01");
		System.out.println(findAllMeetOfSelf.size());
	}

	@After
	public void after() {
		System.out.println(">>  测试结束  <<");
	}
}
