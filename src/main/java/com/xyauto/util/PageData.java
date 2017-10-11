package com.xyauto.util;

import lombok.Data;

/**
 * 分页数据类
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月10日 下午5:14:24
 */
@Data
public class PageData {

	private Integer pageNo; // 页号
	private Integer pageSize; // 每页数据数量
	private Integer total; // 总数据数量
	private Integer totalPages; // 总页数
	private Object data; // 查询结果项
}
