package com.xyauto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.xyauto.pojo.BoardroomInfo;
import com.xyauto.pojo.User;
import com.xyauto.pojo.UserRole;
import com.xyauto.service.MeetingManagerService;
import com.xyauto.util.Constants;
import com.xyauto.util.IdUtil;
import com.xyauto.util.ResultUtil;
import com.xyauto.util.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会议室管理接口
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月10日 下午2:40:49
 */
@RestController
@RequestMapping("mm")
@Api(description = "会议室管理")
public class MeetingManagerController {

	@Autowired
	private MeetingManagerService boardroomInfoService;

	@GetMapping("/insert")
	@ApiOperation(value = "新增", notes = "新增一条会议室记录")
	ResultUtil insert(BoardroomInfo bi, @SessionAttribute(Constants.SESSION_USER) User user) {

		// role 会议室管理
		List<UserRole> userRole = user.getRoleList();
		UserRole roleCheck = new UserRole(Constants.MEETING_MANAGER);
		if (!userRole.contains(roleCheck))
			return ResultUtil.error(Constants.ROLE_ERROR);

		if (null == bi.getBiName() || StringUtil.isEmpty(bi.getBiName()))
			return ResultUtil.error("会议室名称不能为空");
		if (null == bi.getBiFloor() || StringUtil.isEmpty(bi.getBiFloor()))
			return ResultUtil.error("所在楼层不能为空");
		if (null == bi.getBiCapacity())
			return ResultUtil.error("最大人数不能为空");
		if (bi.getBiName().length() > 11)
			return ResultUtil.error("会议室名称超出最大限制");
		if (bi.getBiFloor().length() > 11)
			return ResultUtil.error("所在楼层超出最大限制");
		if (bi.getBiCapacity() > 9999 || bi.getBiCapacity() < 1)
			return ResultUtil.error("人数过小或者过大");
		if (null == bi.getEquipment() && (bi.getEquipment() < 0 || bi.getEquipment() > 15))
			return ResultUtil.error("设备选择异常");

		bi.setBiId(IdUtil.getUUID());
		bi.setStatus((byte) 1);
		bi.setIsDelete(false);
		bi.setCreateUser(user.getEmployeeId());
		bi.setUpdateUser(user.getEmployeeId());

		Integer result = boardroomInfoService.insert(bi, user);
		if (1 == result)
			return ResultUtil.success();
		if (-1 == result)
			return ResultUtil.error("没有权限添加此办公区的会议室");
		if (-2 == result)
			return ResultUtil.error("同一办公区内不能有重复名称的办公室");
		return ResultUtil.error(Constants.INSTER_ERROR);
	}

	@GetMapping("/update")
	@ApiOperation(value = "修改", notes = "通过ID修改一条会议室记录")
	ResultUtil update(BoardroomInfo bi, @SessionAttribute(Constants.SESSION_USER) User user) {

		// role 会议室管理
		List<UserRole> userRole = user.getRoleList();
		UserRole roleCheck = new UserRole(Constants.MEETING_MANAGER);
		if (!userRole.contains(roleCheck))
			return ResultUtil.error(Constants.ROLE_ERROR);

		if (StringUtil.isEmpty(bi.getBiId()))
			return ResultUtil.error("数据错误");
		if (null == bi.getBiName() || StringUtil.isEmpty(bi.getBiName()))
			return ResultUtil.error("会议室名称不能为空");
		if (null == bi.getBiFloor() || StringUtil.isEmpty(bi.getBiFloor()))
			return ResultUtil.error("所在楼层不能为空");
		if (null == bi.getBiCapacity())
			return ResultUtil.error("最大人数不能为空");
		if (bi.getBiName().length() > 11)
			return ResultUtil.error("会议室名称超出最大限制");
		if (bi.getBiFloor().length() > 11)
			return ResultUtil.error("所在楼层超出最大限制");
		if (bi.getBiCapacity() > 9999 || bi.getBiCapacity() < 1)
			return ResultUtil.error("人数过小或者过大");
		if (null == bi.getEquipment() && (bi.getEquipment() < 0 || bi.getEquipment() > 15))
			return ResultUtil.error("设备选择异常");

		BoardroomInfo update = new BoardroomInfo();
		update.setBiId(bi.getBiId());
		update.setOfficeId(bi.getOfficeId());
		update.setBiName(bi.getBiName());
		update.setBiFloor(bi.getBiFloor());
		update.setBiCapacity(bi.getBiCapacity());
		update.setEquipment(bi.getEquipment());
		update.setUpdateUser(user.getEmployeeId());

		Integer result = boardroomInfoService.update(update, user);
		if (1 == result)
			return ResultUtil.success();
		if (-1 == result)
			return ResultUtil.error("没有权限修改此办公区的会议室");
		if (-2 == result)
			return ResultUtil.error("同一办公区内不能有重复名称的办公室");
		return ResultUtil.error(Constants.UPDATE_ERROR);
	}

	@GetMapping("/status")
	@ApiOperation(value = "状态", notes = "通过ID变更一条会议室记录")
	ResultUtil status(String biId, Integer status, @SessionAttribute(Constants.SESSION_USER) User user) {

		// role 会议室管理
		List<UserRole> userRole = user.getRoleList();
		UserRole roleCheck = new UserRole(Constants.MEETING_MANAGER);
		if (!userRole.contains(roleCheck))
			return ResultUtil.error(Constants.ROLE_ERROR);

		Byte b = 0;
		if (0 == status)
			b = 1;

		BoardroomInfo statusUpdate = new BoardroomInfo();
		statusUpdate.setBiId(biId);
		statusUpdate.setStatus(b);
		// TODO
		statusUpdate.setUpdateUser(user.getEmployeeId());
		Integer result = boardroomInfoService.status(statusUpdate, user);
		if (1 == result)
			return ResultUtil.success();
		if (-1 == result)
			return ResultUtil.error("没有权限修改此办公区的会议室");
		return ResultUtil.error(Constants.UPDATE_ERROR);
	}

	@GetMapping("/delete")
	@ApiOperation(value = "删除", notes = "通过ID删除一条会议室记录")
	ResultUtil delete(String biId, @SessionAttribute(Constants.SESSION_USER) User user) {

		// role 会议室管理
		List<UserRole> userRole = user.getRoleList();
		UserRole roleCheck = new UserRole(Constants.MEETING_MANAGER);
		if (!userRole.contains(roleCheck))
			return ResultUtil.error(Constants.ROLE_ERROR);

		BoardroomInfo delete = new BoardroomInfo();
		delete.setBiId(biId);
		delete.setIsDelete(true);
		delete.setUpdateUser(user.getEmployeeId());

		Integer result = boardroomInfoService.delete(delete);
		if (1 == result)
			return ResultUtil.success();
		if (-1 == result)
			return ResultUtil.error("非停用状态不能删除");
		if (-2 == result)
			return ResultUtil.error("有未完成的会议，不能删除会议室");
		return ResultUtil.error(Constants.DELETE_ERROR);
	}

	@GetMapping("/one")
	@ApiOperation(value = "查询", notes = "通过ID查询一条会议室记录")
	ResultUtil one(String biId, @SessionAttribute(Constants.SESSION_USER) User user) {

		// role 权限管理
		List<UserRole> userRole = user.getRoleList();
		UserRole roleCheck = new UserRole(Constants.MEETING_MANAGER);
		if (!userRole.contains(roleCheck))
			return ResultUtil.error(Constants.ROLE_ERROR);
		// check
		if (null == biId || StringUtil.isEmpty(biId))
			return ResultUtil.error("请选择一个会议室");

		BoardroomInfo one = boardroomInfoService.one(biId, user);
		if (null == one)
			return ResultUtil.error(Constants.SELECT_ERROR);
		return ResultUtil.success(one);
	}

	@GetMapping("/select")
	@ApiOperation(value = "查询", notes = "通过条件查询会议室记录")
	ResultUtil select(Integer pageNo, Integer pageSize, String officeId, String status,
			@SessionAttribute(Constants.SESSION_USER) User user) {

		// role 会议室管理
		List<UserRole> userRole = user.getRoleList();
		UserRole roleCheck = new UserRole(Constants.MEETING_MANAGER);
		if (!userRole.contains(roleCheck))
			return ResultUtil.error(Constants.ROLE_ERROR);
		// FETCH NEXT = 0 EXCEPTION
		if (0 == pageSize)
			return ResultUtil.error(Constants.EXCEPTION);
		// 是否查询所有
		if ("-2".equals(officeId))
			officeId = null;
		if ("-2".equals(status))
			status = null;

		return ResultUtil.success(
				boardroomInfoService.selectByCondition(pageNo, pageSize, officeId, status, user.getEmployeeId()));
	}
}
