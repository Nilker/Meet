package com.xyauto.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * @author xusy 作者 E-mail: 
 * @version1.0 创建时间：2017年10月19日 下午5:54:07 
 * 类说明 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetingMessage{
	private String UserCode;
	private	String Type;
	private MainData Data;
	@Override
	public String toString() {
		return "[{\"UserCode\""+":" +"[\""+ UserCode +"\"]"+ ", \"Type\":" + "\""+Type
				+"\""+ ",\"Data\":" + Data + "}]";
	}
	
}
