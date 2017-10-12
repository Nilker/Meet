package com.xyauto.extend;

import com.xyauto.pojo.ScheduledRecord;

import lombok.Data;

@Data
public class ScheduledRecordExt extends ScheduledRecord {
	private String[] employee_ids;
	private String type;
	private String biFloor;
	private String biName;
}
