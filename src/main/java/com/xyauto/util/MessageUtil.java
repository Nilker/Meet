package com.xyauto.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.http.ParseException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xusy 作者 E-mail:
 * @version 创建时间：2017年10月10日 下午1:29:23 
 * 类说明 会议通知
 */
@Slf4j
@Component
public class MessageUtil {
	@Value("${com.xyauto.MESSAGE_API_URL}")
	private String MESSAGE_API_URL;
	public void meetingInvitation(MeetingMessage meetingMessage)
			throws ParseException, IOException, InterruptedException, ExecutionException {
		meetingMessage.setType(Constants.MEETTING_INVITATION);
		meetingMessage.getData().setEntityType(Constants.MEETTING_INVITATION);
		meetingMessage.getData().setAction(Constants.MEETTING_INV_CN);
		meetingMessage.getData().setActionDate(DateUtils.now());
		meetingMessage.getData().setRemindUserId(Constants.REMIND_USERID);
		String secretkey = Md5Utils.getMD5(DateTime.now().toString("MM|yyyy|dd|HH") + Constants.KEY_STR, "UTF-8");
		Map<String, String> map = new HashMap<>();
		map.put("secretkey", secretkey);
		map.put("content", meetingMessage.toString());
		log.debug("send meetingInvitation-->" + HttpUtil.httpForm(MESSAGE_API_URL,map) + " obj {}-->" + map);
	}

	public void meetingCancel(MeetingMessage meetingMessage)
			throws ParseException, IOException, InterruptedException, ExecutionException {
		meetingMessage.setType(Constants.MEETTING_CANCEL);
		meetingMessage.getData().setEntityType(Constants.MEETTING_CANCEL);
		meetingMessage.getData().setAction(Constants.MEETTING_CAL_CN);
		meetingMessage.getData().setActionDate(DateUtils.now());
		meetingMessage.getData().setRemindUserId(Constants.REMIND_USERID);
		String secretkey = Md5Utils.getMD5(DateTime.now().toString("MM|yyyy|dd|HH") + Constants.KEY_STR, "UTF-8");
		Map<String, String> map = new HashMap<>();
		map.put("secretkey", secretkey);
		map.put("content", meetingMessage.toString());
		log.debug("send meetingCancel-->" + HttpUtil.httpForm(MESSAGE_API_URL,map) + " obj {}-->" + map);
	}

	public synchronized void meetingRemind(MeetingMessage meetingMessage)
			throws ParseException, IOException, InterruptedException, ExecutionException {
		meetingMessage.setType(Constants.MEETTING_REMIND);
		meetingMessage.getData().setEntityType(Constants.MEETTING_REMIND);
		meetingMessage.getData().setAction(Constants.MEETTING_REM_CN);
		meetingMessage.getData().setActionDate(DateUtils.now());
		meetingMessage.getData().setRemindUserId(Constants.REMIND_USERID);
		String secretkey = Md5Utils.getMD5(DateTime.now().toString("MM|yyyy|dd|HH") + Constants.KEY_STR, "UTF-8");
		Map<String, String> map = new HashMap<>();
		map.put("secretkey", secretkey);
		map.put("content", meetingMessage.toString());
		log.debug("send meetingRemind-->" + HttpUtil.httpForm(MESSAGE_API_URL,map) + " obj {}-->" + map);
	}

}
