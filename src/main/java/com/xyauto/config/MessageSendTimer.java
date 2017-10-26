package com.xyauto.config;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xyauto.extend.ScheduledRecordExt;
import com.xyauto.mapper.AppMapper;
import com.xyauto.util.CacheUtil;
import com.xyauto.util.Constants;
import com.xyauto.util.DateUtils;
import com.xyauto.util.Expand;
import com.xyauto.util.MainData;
import com.xyauto.util.MeetingMessage;
import com.xyauto.util.MessageUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2017年10月10日 下午12:01:10 类说明
 */
@Component
@Slf4j
public class MessageSendTimer {
	@Autowired
	private AppMapper mapper;

	/* 每100秒执行一次 */
	@Scheduled(cron = "0/100 * * * * *")
	public void timerRate() throws ParseException, IOException, InterruptedException, ExecutionException {
		// 获取当前时间
		Map<String, ScheduledRecordExt> scheMap = CacheUtil.getScheMap(mapper);
		log.debug(">> scheMap {} " + scheMap);
		for (Map.Entry<String, ScheduledRecordExt> entry : scheMap.entrySet()) {
			// log.debug("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			if (DateUtils.now(DateUtils.HHMM).equals(entry.getValue().getBeginTimer())) {
				String empIdStr = entry.getValue().getMyEmpId();
				MeetingMessage msg = new MeetingMessage();
				MainData data = new MainData();
				Expand expand = new Expand();
				expand.setTitle(entry.getValue().getMeetingTheme());
				expand.setDate(DateUtils.date2Str(entry.getValue().getStartTime(), DateUtils.YMD));
				expand.setStartTime(DateUtils.date2Str(entry.getValue().getStartTime(), DateUtils.HHMMSS));
				expand.setEndTime(DateUtils.date2Str(entry.getValue().getEndTime(), DateUtils.HHMMSS));
				expand.setSubTitle(entry.getValue().getBiFloor() + entry.getValue().getBiName());
				msg.setUserCode(empIdStr);
				data.setRealtionId(entry.getValue().getSrId());
				data.setActionUser(entry.getValue().getEmployeeName());
				data.setRemark(Constants.MEETTING_REM_CN);
				data.setRemindUserCode(empIdStr);
				data.setExpand(expand);
				msg.setData(data);
				// 提醒与会人
				MessageUtil.meetingRemind(msg);
				log.info(">> meetingRemind msg {}" + msg);
			}
			log.info(">> current time : " + DateUtils.now(DateUtils.HHMM) + " remind time : "
					+ entry.getValue().getBeginTimer());
		}
	}

	/**
	 * 清空预定信息
	 */
	@Scheduled(cron = "0 0 1 * * *")
	public void timerRate2() {
		// 获取当前时间
		CacheUtil.clearScheMap(mapper);
		CacheUtil.getScheMap(mapper);
		log.info(">> current time : " + DateUtils.now() + "  clear scheMap success ! ");
	}
}
