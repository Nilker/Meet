package com.xyauto.extend;

import com.xyauto.pojo.ScheduledRecord;

import lombok.Data;

/**
 * @author xusy
 *
 */
@Data
public class ScheduledRecordExt extends ScheduledRecord {
	private String[] employee_ids;
	private String[] startEndTimeArr;
	private String startEndTime;
	private String type;
	private String biFloor;
	private String biName;
	private Integer biCapacity;
	private String myEmpId;
}
