package com.xyauto.util;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.xyauto.extend.ScheduledRecordExt;
import com.xyauto.mapper.AppMapper;

@Slf4j
public class CacheUtil {

	private static final Object scheLock = new Object();
	private static Map<String, ScheduledRecordExt> scheMap;

	public static Map<String, ScheduledRecordExt> getScheMap(AppMapper mapper) {
		if (scheMap == null) {
			synchronized (scheLock) {
				if (scheMap == null) {
					List<ScheduledRecordExt> scheInfoList = mapper.findMeetByStartTime(DateUtils.now(DateUtils.YMD));
					scheMap = new HashMap<>();
					for (ScheduledRecordExt scheInfo : scheInfoList) {
						scheInfo.setBeginTimer(DateUtils.dateCompute(scheInfo.getStartTime()));
						scheMap.put(scheInfo.getSrId(), scheInfo);
					}
					log.debug("scheMap {} " + scheMap);
				}
			}
		}
		return scheMap;
	}

	public static ScheduledRecordExt getScheduleBySrId(String srId, AppMapper mapper) {
		Map<String, ScheduledRecordExt> map = getScheMap(mapper);
		 log.debug("getScheduleBySrId {} "+srId);
		return map.get(srId);
	}

	public static void addScheMap(ScheduledRecordExt scheObj, AppMapper mapper) {
		Map<String, ScheduledRecordExt> map = getScheMap(mapper);
		map.put(scheObj.getSrId(), scheObj);
		 log.debug("addScheMap {} " + scheObj);
	}

	public static void delScheMap(String srId, AppMapper mapper) {
		Map<String, ScheduledRecordExt> map = getScheMap(mapper);
		map.remove(srId);
		 log.debug("delScheMap {} "+srId);
	}

	public static void clearScheMap() {
		scheMap = null;
		log.debug("clear scheMap !" + scheMap);
	}
}