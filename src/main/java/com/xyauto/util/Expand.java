package com.xyauto.util;

import lombok.Data;

/** 
 * @author xusy 作者 E-mail: 
 * @version1.0 创建时间：2017年10月19日 下午5:54:07 
 * 类说明 
 */
@Data
public class Expand {
	private String Title;
	private String Date;
	private String StartTime;
	private String EndTime;
	private String SubTitle;
	@Override
	public String toString() {
		return "{\"Title\":" +"\""+Title+"\"" + ", \"Date\":" + "\""+ Date + "\""+ ", \"StartTime\":"
				+ "\""+ StartTime + "\""+ ", \"EndTime\":" + "\""+ EndTime+ "\"" + ", \"SubTitle\":" + "\""+ SubTitle
				+ "\""+ "}";
	}
	
}
