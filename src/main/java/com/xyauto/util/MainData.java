package com.xyauto.util;
import lombok.Data;

/** 
 * @author xusy 作者 E-mail: 
 * @version1.0 创建时间：2017年10月19日 下午5:54:07 
 * 类说明 
 */
@Data
public class MainData {
	private String EntityType;
	private String RealtionId;
	private String ActionUser;
	private String Action;
	private String ActionDate;
	private String Remark;
	private int RemindUserId;
	private String RemindUserCode;
	private Expand Expand;
	@Override
	public String toString() {
		return "{\"EntityType\":" + "\""+EntityType +"\""+", \"RealtionId\":"
				+ "\""+RealtionId +"\""+ ", \"ActionUser\":" +"\""+ActionUser +"\""+ ",\"Action\":"
				+ "\""+Action +"\""+ ", \"ActionDate\":" + "\""+ActionDate+"\""+", \"Remark\":" +"\""+ Remark
				+"\""+ ", \"RemindUserId\":" + RemindUserId + ", \"RemindUserCode\":"
				+ "\""+RemindUserCode +"\""+ ", \"Expand\":" +""+ Expand +""+ "}";
	}

}
