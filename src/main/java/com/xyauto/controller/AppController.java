package com.xyauto.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xyauto.extend.ScheduledRecordExt;
import com.xyauto.pojo.User;
import com.xyauto.service.AppService;
import com.xyauto.util.AppLoginInterceptor;
import com.xyauto.util.Constants;
import com.xyauto.util.ResultUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * app
 * 
 * @author xusy@xingyuanauto.com
 * @version 创建时间：2017年10月17日
 */
@Controller
@RequestMapping("app")
@Slf4j
public class AppController {
	@Autowired
	private AppService appService;

	@RequestMapping(value = "scheduled/delScheduleBySrId", method = RequestMethod.GET)
	public ResponseEntity<ResultUtil> delScheduleBySrId(HttpServletRequest request, String srId)
			throws Exception {
		log.debug("srId{}"+srId);
		if (AppLoginInterceptor.checkLogin(request)) {//登陆验证
			System.out.println(request.getSession().getAttribute(Constants.SESSION_APPUSER));
			return ResponseEntity.ok(ResultUtil.success(appService.delScheduleBySrId(srId)));
		} else {
			return ResponseEntity.ok(ResultUtil.error(Constants.LOGIN_ERROR));
		}
	}

	@RequestMapping(value = "scheduled/findBoardByPrimaryKey", method = RequestMethod.GET)
	public ResponseEntity<ResultUtil> findBoardByPrimaryKey(HttpServletRequest request, String biId) throws Exception {
		log.debug("biId{}" + biId);
		 if (AppLoginInterceptor.checkLogin(request)) {//登陆验证
		 System.out.println(request.getSession().getAttribute(Constants.SESSION_APPUSER));
		return ResponseEntity.ok(ResultUtil.success(appService.findBoardByPrimaryKey(biId)));
		 } else {
		 return ResponseEntity.ok(ResultUtil.error(Constants.LOGIN_ERROR));
		 }
	}

	@RequestMapping(value = "scheduled/getInfoByOfficeId", method = RequestMethod.GET)
	public ResponseEntity<ResultUtil> findInfoByOfficeId(HttpServletRequest request, int officeId, String startTime)
			throws Exception {
		log.debug("officeId{}" + officeId + "startTime{}" + startTime);
		 if (AppLoginInterceptor.checkLogin(request)) {//登陆验证
		 System.out.println(request.getSession().getAttribute(Constants.SESSION_APPUSER));
		return ResponseEntity.ok(ResultUtil.success(appService.findInfoByOfficeId(officeId, startTime)));
		 } else {
		 return ResponseEntity.ok(ResultUtil.error(Constants.LOGIN_ERROR));
		 }
	}
	
	@RequestMapping(value = "scheduled/findInfoByBiId", method = RequestMethod.GET)
	public ResponseEntity<ResultUtil> findInfoByBiId(HttpServletRequest request, String biId, String startTime)
			throws Exception {
		log.debug("biId{}"+biId+"startTime{}"+startTime);
		if (AppLoginInterceptor.checkLogin(request)) {//登陆验证
			System.out.println(request.getSession().getAttribute(Constants.SESSION_APPUSER));
		return ResponseEntity.ok(ResultUtil.success(appService.findInfoByBiId(biId, startTime)));
		} else {
			return ResponseEntity.ok(ResultUtil.error(Constants.LOGIN_ERROR));
		}
	}
/**
 * 我的会议-列表
 */
	@RequestMapping(value = "scheduled/findAllMeetOfSelf", method = RequestMethod.GET)
	public ResponseEntity<ResultUtil> findAllMeetOfSelf(HttpServletRequest request)
			throws Exception {
		if (AppLoginInterceptor.checkLogin(request)) {//登陆验证
			User user=(User)request.getSession().getAttribute(Constants.SESSION_APPUSER);
			return ResponseEntity.ok(ResultUtil.success(appService.findAllMeetOfSelf(user.getEmployeeId())));
		} else {
			return ResponseEntity.ok(ResultUtil.error(Constants.LOGIN_ERROR));
		}
	}
	@RequestMapping(value = "scheduled/findSingleMeetBySrId", method = RequestMethod.GET)
	public ResponseEntity<ResultUtil> findSingleMeetBySrId(HttpServletRequest request,String srId)
			throws Exception {
		if (AppLoginInterceptor.checkLogin(request)) {//登陆验证
			User user=(User)request.getSession().getAttribute(Constants.SESSION_APPUSER);
			List<ScheduledRecordExt> findSingleMeetBySrId = appService.findSingleMeetBySrId(srId);
			findSingleMeetBySrId.get(0).setMyEmpId(user.getEmployeeId());
			return ResponseEntity.ok(ResultUtil.success(findSingleMeetBySrId));
		} else {
			return ResponseEntity.ok(ResultUtil.error(Constants.LOGIN_ERROR));
		}
	}
	@RequestMapping(value = "scheduled/findSingleInfoByBiId", method = RequestMethod.GET)
	public ResponseEntity<ResultUtil> findSingleInfoByBiId(HttpServletRequest request,String biId,String startTime)
			throws Exception {
		if (AppLoginInterceptor.checkLogin(request)) {//登陆验证
			return ResponseEntity.ok(ResultUtil.success(appService.findSingleInfoByBiId(biId,startTime)));
		} else {
			return ResponseEntity.ok(ResultUtil.error(Constants.LOGIN_ERROR));
		}
	}
	@RequestMapping(value = "scheduled/insertSchedule", method = RequestMethod.POST)
	public ResponseEntity<ResultUtil> insertSchedule(HttpServletRequest request,@RequestBody ScheduledRecordExt schedule)
			throws Exception {
		log.info("obj{}"+schedule);
		if (AppLoginInterceptor.checkLogin(request)) {//登陆验证
			User user=(User)request.getSession().getAttribute(Constants.SESSION_APPUSER);
			schedule.setEmployeeId(user.getEmployeeId());
			return ResponseEntity.ok(ResultUtil.success(appService.insertSchedule(schedule)));
		} else {
			return ResponseEntity.ok(ResultUtil.error(Constants.LOGIN_ERROR));
		}
	}
	
}
