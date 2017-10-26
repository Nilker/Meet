package com.xyauto.extend;

import java.util.Date;

import com.xyauto.pojo.ScheduledRecord;

import lombok.Data;

/**
 * @author xusy
 *
 */
@Data
public class ScheduledRecordExt extends ScheduledRecord {
	private String[] employeeIds;
	private String[] startEndTimeArr;
	private String startEndTime;
	private String type;//发起人、与会人类型
	private String biFloor;
	private String biName;
	private Integer biCapacity;
	private String myEmpId;
	private String beginTimer;//提前十五分钟
}
