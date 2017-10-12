package com.xyauto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyauto.pojo.BoardroomInfo;
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
	ResultUtil insert(BoardroomInfo bi) {
		
		//TODO session role
		
		//TODO office role
		
		if(null == bi.getBiName() || StringUtil.isEmpty(bi.getBiName()))
			return ResultUtil.error("会议室名称不能为空");
		if(null == bi.getBiFloor() || StringUtil.isEmpty(bi.getBiFloor()))
			return ResultUtil.error("所在楼层不能为空");
		if(null == bi.getBiCapacity())
			return ResultUtil.error("最大人数不能为空");
		if(bi.getBiName().length() > 11)
			return ResultUtil.error("会议室名称超出最大限制");
		if(bi.getBiFloor().length() > 11)
			return ResultUtil.error("所在楼层超出最大限制");
		if(bi.getBiCapacity() > 9999 || bi.getBiCapacity() < 1)
			return ResultUtil.error("人数过小或者过大");
		
		bi.setBiId(IdUtil.getUUID());
		bi.setStatus((byte) 1);
		bi.setIsDelete(false);
		// TODO
		bi.setCreateUser("测试");
		// TODO
		bi.setUpdateUser("测试");

		Integer result = boardroomInfoService.insert(bi);
		if (1 == result)
			return ResultUtil.success();
		if (-1 == result)
			return ResultUtil.error("同一办公区内不能有重复名称的办公室");
		return ResultUtil.error(Constants.INSTER_ERROR);
	}

	@GetMapping("/update")
	@ApiOperation(value = "修改", notes = "通过ID修改一条会议室记录")
	ResultUtil update(BoardroomInfo bi) {
		
		//TODO session role
		
		if (StringUtil.isEmpty(bi.getBiId()))
			return ResultUtil.error("数据错误");
		if(null == bi.getBiName() || StringUtil.isEmpty(bi.getBiName()))
			return ResultUtil.error("会议室名称不能为空");
		if(null == bi.getBiFloor() || StringUtil.isEmpty(bi.getBiFloor()))
			return ResultUtil.error("所在楼层不能为空");
		if(null == bi.getBiCapacity())
			return ResultUtil.error("最大人数不能为空");
		if(bi.getBiName().length() > 11)
			return ResultUtil.error("会议室名称超出最大限制");
		if(bi.getBiFloor().length() > 11)
			return ResultUtil.error("所在楼层超出最大限制");
		if(bi.getBiCapacity() > 9999 || bi.getBiCapacity() < 1)
			return ResultUtil.error("人数过小或者过大");
		
		BoardroomInfo update = new BoardroomInfo();
		update.setBiId(bi.getBiId());
		update.setOfficeId(bi.getOfficeId());
		update.setBiName(bi.getBiName());
		update.setBiFloor(bi.getBiFloor());
		update.setBiCapacity(bi.getBiCapacity());
		// TODO byte 测试默认值是否为0
		update.setEquipment(bi.getEquipment());
		// TODO
		update.setUpdateUser("测试");
		
		Integer result = boardroomInfoService.update(update);
		if (1 == result ) 
			return ResultUtil.success();
		if (-1 == result) 
			return ResultUtil.success();
		return ResultUtil.error(Constants.UPDATE_ERROR);
	}

	
	@GetMapping("/status")
	@ApiOperation(value = "状态", notes = "通过ID变更一条会议室记录")
	ResultUtil status(String biId, Integer status) {
		
		//TODO session role
		
		Byte b = 0;
		if(1 == status)
			b = 1;
		
		BoardroomInfo statusUpdate = new BoardroomInfo();
		statusUpdate.setBiId(biId);
		statusUpdate.setStatus(b);
		// TODO
		statusUpdate.setUpdateUser("测试");
		Integer result = boardroomInfoService.status(statusUpdate);
		if (1 == result) 
			return ResultUtil.success();
		return ResultUtil.error(Constants.UPDATE_ERROR);
	}
	
	@GetMapping("/delete")
	@ApiOperation(value = "删除", notes = "通过ID删除一条会议室记录")
	ResultUtil delete(String biId) {
		
		//TODO session role
		
		BoardroomInfo delete = new BoardroomInfo();
		delete.setBiId(biId);
		delete.setIsDelete(true);
		// TODO
		delete.setUpdateUser("测试");
		Integer result = boardroomInfoService.delete(delete);
		if (1 == result)
			return ResultUtil.success();
		if(-1 == result)
			return ResultUtil.error("非停用状态不能删除");
		if(-2 == result)
			return ResultUtil.error("有未完成的会议，不能删除会议室");
		return ResultUtil.error(Constants.DELETE_ERROR);
	}

	@GetMapping("/select")
	@ApiOperation(value = "查询", notes = "通过条件查询会议室记录")
	ResultUtil select(Integer pageNo, Integer pageSize) {
		
		//TODO session role
		
		return ResultUtil.success(boardroomInfoService.selectByCondition(pageNo, pageSize, null, null));
	}
}
