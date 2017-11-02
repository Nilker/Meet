package com.xyauto.pojo;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * 部门树结构类
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月9日 下午6:07:20
 */
@Data
public class DeptTreeNode {
	private Integer departmentId;
	private String name;
	private List<DeptTreeNode> node = Lists.newArrayList();
}
