//package com.xyauto.service;
//
//import com.google.common.collect.Lists;
//import com.xyauto.domain.DeptTreeNode;
//import com.xyauto.oa.*;
//import com.xyauto.util.Constants;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import javax.xml.ws.Holder;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
///**
// * @author qiaom@xingyuanauto.com
// * @version 创建时间：2017年3月25日 下午1:40:04
// */
//@Slf4j
//@Service
//public class OAService {
//
//    @Resource
//    private CacheManager cacheManager;
//
//    @Cacheable(value = Constants.CACHE_OA, key = "'department_list'")
//    public List<Department> queryDepartment() {
//        EmployeeService employeeService = new EmployeeService();
//        EmployeeServiceSoap employeeServiceSoap = employeeService.getEmployeeServiceSoap();
//        ArrayOfDepartment querySubDept = employeeServiceSoap.querySubDept(1, true);
//        List<Department> departmentList = querySubDept.getDepartment();
//        log.debug(">> 部门列表_未命中缓存 <<");
//        //根据 上级ID 分组 除了顶级部门
//        Map<Integer, List<Department>> parentIdList = departmentList.stream().filter(o -> o.getParentId() != 1).collect(Collectors.groupingBy(Department::getParentId));
//        List<DeptTreeNode> listNodes = Lists.newArrayList();//构造 tree
//
//        //所有顶级部门
//        List<Department> topDepts = departmentList.stream().filter(o -> o.getParentId() == 1).collect(Collectors.toList());
//
//        topDepts.forEach(o -> {//循环顶级部门
//            DeptTreeNode node = new DeptTreeNode();
//            node.setDepartmentId(o.getDepartmentId());
//            node.setName(o.getName());
//            listNodes.add(node);
//            LoadTreeNode(parentIdList, parentIdList.get(o.getDepartmentId()), node);
//        });//构造 tree完毕
//
//        List<Department> data = Lists.newArrayList();
//        //展开 tree 制表符分割层级
//        listNodes.forEach(o -> {
//            Department department = new Department();
//            department.setDepartmentId(o.getDepartmentId());
//            department.setName(o.getName());
//            data.add(department);
//            spreadTreeNode(data, o.getNode(), 1);//v 层级
//        });
//
//        return data;
//    }
//
//    //展开tree
//    private static void spreadTreeNode(List<Department> data, List<DeptTreeNode> nodes, int v) {
//        nodes.forEach(o -> {
//            Department department = new Department();
//            department.setDepartmentId(o.getDepartmentId());
//            StringBuilder blank = new StringBuilder();
//            for (int i = 0; i < v; i++) {
//                blank.append("&nbsp;&nbsp;&nbsp;&nbsp;");
//            }
//            department.setName(blank.toString() + o.getName());
//            data.add(department);
//            spreadTreeNode(data, o.getNode(), v + 1);
//        });
//    }
//
//    //组织tree
//    private static void LoadTreeNode(Map<Integer, List<Department>> group, List<Department> departments, DeptTreeNode node) {
//        if (departments != null) {
//            departments.forEach(o -> {
//                DeptTreeNode n = new DeptTreeNode();
//                n.setDepartmentId(o.getDepartmentId());
//                n.setName(o.getName());
//                node.getNode().add(n);
//                LoadTreeNode(group, group.get(o.getDepartmentId()), n);
//            });
//        }
//    }
//
//    @Cacheable(value = Constants.CACHE_OA, key = "'department_'+#id")
//    public List<Employee> queryEmployeeByDept(Integer id) {
////        Holder<Integer> employeeCount = new Holder<>();
////		Holder<ArrayOfEmployee> queryEmployeeByDeptResult = new Holder<>();
////		EmployeeService employeeService = new EmployeeService();
////		EmployeeServiceSoap employeeServiceSoap = employeeService.getEmployeeServiceSoap();
////		employeeServiceSoap.queryEmployeeByDept(id, false, false, 999, 1, queryEmployeeByDeptResult, employeeCount);
////		log.debug(">> 人员列表_未命中缓存 <<");
//        return getList(id, false, false);
//    }
//
//    @Cacheable(value = Constants.CACHE_OA, key = "'department_all_'+#id")
//    public List<Employee> queryEmployeeByDeptIncludeChildren(Integer id) {
////		Holder<Integer> employeeCount = new Holder<>();
////		Holder<ArrayOfEmployee> queryEmployeeByDeptResult = new Holder<>();
////		EmployeeService employeeService = new EmployeeService();
////		EmployeeServiceSoap employeeServiceSoap = employeeService.getEmployeeServiceSoap();
////		employeeServiceSoap.queryEmployeeByDept(id, true, true, 999, 1, queryEmployeeByDeptResult, employeeCount);
////		log.debug(">> 人员列表_未命中缓存 <<");
//        return getList(id, true, false);
//    }
//
//    private List<Employee> getList(Integer id, boolean includeChildren, boolean filterPartTime) {
//        Holder<Integer> employeeCount = new Holder<>();
//        Holder<ArrayOfEmployee> queryEmployeeByDeptResult = new Holder<>();
//        EmployeeService employeeService = new EmployeeService();
//        EmployeeServiceSoap employeeServiceSoap = employeeService.getEmployeeServiceSoap();
//        employeeServiceSoap.queryEmployeeByDept(id, includeChildren, filterPartTime, 999, 1, queryEmployeeByDeptResult, employeeCount);
//        log.debug(">> 人员列表_未命中缓存 <<");
//        return queryEmployeeByDeptResult.value.getEmployee();
//    }
//}
