package com.xyauto.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xyauto.oa.Employee;
import com.xyauto.oa.EmployeeService;
import com.xyauto.oa.EmployeeServiceSoap;
import com.xyauto.util.Constants;

import lombok.extern.slf4j.Slf4j;

/**
 * OA服务类
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年3月25日 下午1:40:04
 */
@Slf4j
@Service
public class OAService {

	@Value("${com.xyauto.WEB_SERVICE}")
	private String WEB_SERVICE;
	// @Resource
	// private CacheManager cacheManager;

//	@Cacheable(value = Constants.CACHE_OA, key = "'department_list'")
//	public List<Department> queryDepartment() {
//		EmployeeService employeeService = new EmployeeService();
//		EmployeeServiceSoap employeeServiceSoap = employeeService.getEmployeeServiceSoap();
//		ArrayOfDepartment querySubDept = employeeServiceSoap.querySubDept(1, true);
//		List<Department> departmentList = querySubDept.getDepartment();
//		log.debug(">> 部门列表_未命中缓存 <<");
//		return departmentList;
//		// 根据 上级ID 分组 除了顶级部门
//		// Map<Integer, List<Department>> parentIdList =
//		// departmentList.stream().filter(o -> o.getParentId() != 1)
//		// .collect(Collectors.groupingBy(Department::getParentId));
//		// List<DeptTreeNode> listNodes = Lists.newArrayList();// 构造 tree
//		//
//		// // 所有顶级部门
//		// List<Department> topDepts = departmentList.stream().filter(o ->
//		// o.getParentId() == 1)
//		// .collect(Collectors.toList());
//		//
//		// topDepts.forEach(o -> {// 循环顶级部门
//		// DeptTreeNode node = new DeptTreeNode();
//		// node.setDepartmentId(o.getDepartmentId());
//		// node.setName(o.getName());
//		// listNodes.add(node);
//		// LoadTreeNode(parentIdList, parentIdList.get(o.getDepartmentId()),
//		// node);
//		// });// 构造 tree完毕
//
//		// List<Department> data = Lists.newArrayList();
//		// 展开 tree 制表符分割层级
//		// listNodes.forEach(o -> {
//		// Department department = new Department();
//		// department.setDepartmentId(o.getDepartmentId());
//		// department.setName(o.getName());
//		// data.add(department);
//		// spreadTreeNode(data, o.getNode(), 1);//v 层级
//		// });
//
//		// return data;
//	}

	// 组织tree
	// private void LoadTreeNode(Map<Integer, List<Department>> group,
	// List<Department> departments,
	// DeptTreeNode node) {
	// if (departments != null) {
	// departments.forEach(o -> {
	// DeptTreeNode n = new DeptTreeNode();
	// n.setDepartmentId(o.getDepartmentId());
	// n.setName(o.getName());
	// node.getNode().add(n);
	// LoadTreeNode(group, group.get(o.getDepartmentId()), n);
	// });
	// }
	// }

	// 展开tree
	// private static void spreadTreeNode(List<Department> data,
	// List<DeptTreeNode> nodes, int v) {
	// nodes.forEach(o -> {
	// Department department = new Department();
	// department.setDepartmentId(o.getDepartmentId());
	// StringBuilder blank = new StringBuilder();
	// for (int i = 0; i < v; i++) {
	// blank.append("&nbsp;&nbsp;&nbsp;&nbsp;");
	// }
	// department.setName(blank.toString() + o.getName());
	// data.add(department);
	// spreadTreeNode(data, o.getNode(), v + 1);
	// });
	// }

//	@Cacheable(value = Constants.CACHE_OA, key = "'department_'+#id")
//	public List<Employee> queryEmployeeByDept(Integer id) {
//		return getList(id, false, false);
//	}
//
//	@Cacheable(value = Constants.CACHE_OA, key = "'department_all_'+#id")
//	public List<Employee> queryEmployeeByDeptIncludeChildren(Integer id) {
//		return getList(id, true, false);
//	}
//
//	private List<Employee> getList(Integer id, boolean includeChildren, boolean filterPartTime) {
//		Holder<Integer> employeeCount = new Holder<>();
//		Holder<ArrayOfEmployee> queryEmployeeByDeptResult = new Holder<>();
//		EmployeeService employeeService = new EmployeeService();
//		EmployeeServiceSoap employeeServiceSoap = employeeService.getEmployeeServiceSoap();
//		employeeServiceSoap.queryEmployeeByDept(id, includeChildren, filterPartTime, 999, 1, queryEmployeeByDeptResult,
//				employeeCount);
//		log.debug(">> 人员列表_未命中缓存 <<");
//		return queryEmployeeByDeptResult.value.getEmployee();
//	}

	@Cacheable(value = Constants.CACHE_OA, key = "'employee_'+#id")
	public Employee queryEmployeeById(String id) {
		URL url = null;
		Employee employee = null;
		try {
			url = new URL(WEB_SERVICE);
			EmployeeService employeeService = new EmployeeService(url);
			EmployeeServiceSoap employeeServiceSoap = employeeService.getEmployeeServiceSoap();
			employee = employeeServiceSoap.getEmployeeByEmployeeNumber(id);
			log.debug(">> 人员信息 未命中缓存 <<");
		} catch (MalformedURLException e) {
			log.debug(">> web service url error:" + e.toString());
			log.debug(">> 人员信息调取失败 <<");
		}
		return employee;
	}
	
	@SuppressWarnings("unchecked")
	@Cacheable(value = Constants.CACHE_OA, key = "'employee_list'")
	public List<HashMap<String, String>> queryAllEmployee() throws JsonParseException, JsonMappingException, IOException {
		URL url = new URL(WEB_SERVICE);
		EmployeeService employeeService = new EmployeeService(url);
		EmployeeServiceSoap employeeServiceSoap = employeeService.getEmployeeServiceSoap();
		String res = employeeServiceSoap.getAllEmployeeInfo(false);
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap<String, List<HashMap<String, String>>> map = objectMapper.readValue(res, HashMap.class);
		List<HashMap<String, String>> employeeList = map.get("EmployeeList");
		log.debug(">> 人员信息列表 未命中缓存 <<");
		return employeeList;
	}
}
