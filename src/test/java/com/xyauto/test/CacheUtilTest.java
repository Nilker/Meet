package com.xyauto.test;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.xyauto.util.CacheUtil;
import com.xyauto.util.DateUtils;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CacheUtilTest {

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
	public void CacheUtil() {
		Map<String, ScheduledRecordExt> scheMap = com.xyauto.util.CacheUtil.getScheMap(appMapper);
		ScheduledRecordExt scheduleBySrId = CacheUtil.getScheduleBySrId("0646c1d7da5346bd9e4ac43377f301da", appMapper);
		System.out.println("当前时间-->"+DateUtils.date2Str(scheduleBySrId.getStartTime()));
		Date now_10 = new Date(scheduleBySrId.getStartTime().getTime() - 900000);
		System.out.println("十五分钟前时间-->"+DateUtils.date2Str(now_10));
//		String srId = "a01e642a15d8463888a28aab248e4b65";
//		List<ScheduledRecordExt> findSingleMeetBySrId = appService.findSingleMeetBySrId(srId);
//		System.out.println("test--->"+scheMap);
//		CacheUtil.clearScheMap();
//		System.out.println("clear--->"+scheMap);
//		CacheUtil.addScheMap(findSingleMeetBySrId.get(0), appMapper);
//		System.out.println("addScheMap--->"+scheMap);
//		Map<String, ScheduledRecordExt> scheMap2 = CacheUtil.getScheMap(appMapper);
//		System.out.println("scheMap2-->"+scheMap2);
//		CacheUtil.getScheduleBySrId(srId, appMapper);
//		System.out.println("getScheduleBySrId--->"+CacheUtil.getScheMap(appMapper));
//		CacheUtil.delScheMap(srId, appMapper);
//		System.out.println("delScheMap--->"+CacheUtil.getScheMap(appMapper));
//		CacheUtil.clearScheMap();
//		System.out.println("clear--->"+CacheUtil.getScheMap(appMapper));
		
	}

	@After
	public void after() {
		System.out.println(">>  测试结束  <<");
	}
}
