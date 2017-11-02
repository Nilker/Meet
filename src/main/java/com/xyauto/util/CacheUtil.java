package com.xyauto.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.xyauto.extend.ScheduledRecordExt;
import com.xyauto.mapper.AppMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheUtil {

	private static final Object scheLock = new Object();

	private static Map<String, ScheduledRecordExt> scheMap;

	public static Map<String, ScheduledRecordExt> getScheMap(AppMapper mapper) {
		if (scheMap == null) {
			synchronized (scheLock) {
				if (scheMap == null) {
					log.debug(">> 未读取缓存  <<");
					List<ScheduledRecordExt> scheInfoList = mapper.findMeetByStartTime(DateUtils.now(DateUtils.YMD));
					scheMap = new HashMap<>();
					for (ScheduledRecordExt scheInfo : scheInfoList) {
						scheInfo.setBeginTimer(DateUtils.dateCompute(scheInfo.getStartTime()));
						scheMap.put(scheInfo.getSrId(), scheInfo);
					}
					log.debug(">> scheMap {} " + scheMap);
				}
			}
		}
		return scheMap;
	}

	public static ScheduledRecordExt getScheduleBySrId(String srId, AppMapper mapper) {
		Map<String, ScheduledRecordExt> map = getScheMap(mapper);
		log.debug(">> getScheduleBySrId {} " + srId);
		return map.get(srId);
	}

	public static void addScheMap(ScheduledRecordExt scheObj, AppMapper mapper) {
		Map<String, ScheduledRecordExt> map = getScheMap(mapper);
		map.put(scheObj.getSrId(), scheObj);
		log.debug(">> addScheMap {} " + scheObj);
	}

	public static void delScheMap(String srId, AppMapper mapper) {
		Map<String, ScheduledRecordExt> map = getScheMap(mapper);
		map.remove(srId);
		log.debug(">> delScheMap {} " + srId);
	}

	public synchronized static void clearScheMap(AppMapper mapper) {
		Map<String, ScheduledRecordExt> scheMap = getScheMap(mapper);
		Iterator<Map.Entry<String, ScheduledRecordExt>> it = scheMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, ScheduledRecordExt> entry = (Map.Entry<String, ScheduledRecordExt>) it.next();
			it.remove();
		}
		log.debug(">> clear scheMap after {} " + scheMap);
		List<ScheduledRecordExt> scheInfoList = mapper.findMeetByStartTime(DateUtils.now(DateUtils.YMD));
		for (ScheduledRecordExt scheInfo : scheInfoList) {
			scheInfo.setBeginTimer(DateUtils.dateCompute(scheInfo.getStartTime()));
			scheMap.put(scheInfo.getSrId(), scheInfo);
		}
		log.debug(">> clear scheMap success {} " + scheMap);
	}
}
