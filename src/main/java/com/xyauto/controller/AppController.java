package com.xyauto.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.experimental.var;
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
	@Autowired
	private AppLoginInterceptor appLoginInterceptor;
	
	@RequestMapping(value = "scheduled/delScheduleBySrId", method = RequestMethod.GET)
	public ResponseEntity<ResultUtil> delScheduleBySrId(HttpServletRequest request, String srId) throws Exception {
		log.info(">> srId {} " + srId);
		if (appLoginInterceptor.checkLogin(request)) {// 登陆验证
			return ResponseEntity.ok(ResultUtil.success(appService.delScheduleBySrId(srId)));
		} else {

			return ResponseEntity.ok(ResultUtil.error(Constants.LOGIN_ERROR));
		}
	}

	@RequestMapping(value = "scheduled/findBoardByPrimaryKey", method = RequestMethod.GET)
	public ResponseEntity<ResultUtil> findBoardByPrimaryKey(HttpServletRequest request, String biId) throws Exception {
		log.info(">> biId {} " + biId);
		if (appLoginInterceptor.checkLogin(request)) {// 登陆验证
			// System.out.println(request.getSession().getAttribute(Constants.SESSION_APPUSER));
			return ResponseEntity.ok(ResultUtil.success(appService.findBoardByPrimaryKey(biId)));
		} else {
			return ResponseEntity.ok(ResultUtil.error(Constants.LOGIN_ERROR));
		}
	}

	@RequestMapping(value = "scheduled/getInfoByOfficeId", method = RequestMethod.GET)
	public ResponseEntity<ResultUtil> findInfoByOfficeId(HttpServletRequest request, int officeId, String startTime)
			throws Exception {
		log.info(">> officeId {} " + officeId + " startTime {} " + startTime);
		if (appLoginInterceptor.checkLogin(request)) {// 登陆验证
			// System.out.println(request.getSession().getAttribute(Constants.SESSION_APPUSER));
			return ResponseEntity.ok(ResultUtil.success(appService.findInfoByOfficeId(officeId, startTime)));
		} else {
			return ResponseEntity.ok(ResultUtil.error(Constants.LOGIN_ERROR));
		}
	}

	@RequestMapping(value = "scheduled/findInfoByBiId", method = RequestMethod.GET)
	public ResponseEntity<ResultUtil> findInfoByBiId(HttpServletRequest request, String biId, String startTime)
			throws Exception {
		log.info(">> biId {} " + biId + " startTime {} " + startTime);
		if (appLoginInterceptor.checkLogin(request)) {// 登陆验证
			// System.out.println(request.getSession().getAttribute(Constants.SESSION_APPUSER));
			return ResponseEntity.ok(ResultUtil.success(appService.findInfoByBiId(biId, startTime)));
		} else {
			return ResponseEntity.ok(ResultUtil.error(Constants.LOGIN_ERROR));
		}
	}

	/**
	 * 我的会议-列表
	 */
	@RequestMapping(value = "scheduled/findAllMeetOfSelf", method = RequestMethod.GET)
	public ResponseEntity<ResultUtil> findAllMeetOfSelf(HttpServletRequest request) throws Exception {
		if (appLoginInterceptor.checkLogin(request)) {// 登陆验证
			User user = (User) request.getSession().getAttribute(Constants.SESSION_APPUSER);
			log.debug(">> employeeId {} " + user.getEmployeeId());
			return ResponseEntity.ok(ResultUtil.success(appService.findAllMeetOfSelf(user.getEmployeeId())));
		} else {
			return ResponseEntity.ok(ResultUtil.error(Constants.LOGIN_ERROR));
		}
	}

	@RequestMapping(value = "scheduled/findSingleMeetBySrId", method = RequestMethod.GET)
	public ResponseEntity<ResultUtil> findSingleMeetBySrId(HttpServletRequest request, String srId) throws Exception {
		log.info(">> srId {} " + srId);
		if (appLoginInterceptor.checkLogin(request)) {// 登陆验证
			User user = (User) request.getSession().getAttribute(Constants.SESSION_APPUSER);
			List<ScheduledRecordExt> findSingleMeetBySrId = appService.findSingleMeetBySrId(srId);
			findSingleMeetBySrId.get(0).setMyEmpId(user.getEmployeeId());
			return ResponseEntity.ok(ResultUtil.success(findSingleMeetBySrId));
		} else {
			return ResponseEntity.ok(ResultUtil.error(Constants.LOGIN_ERROR));
		}
	}

	@RequestMapping(value = "scheduled/findSingleInfoByBiId", method = RequestMethod.GET)
	public ResponseEntity<ResultUtil> findSingleInfoByBiId(HttpServletRequest request, String biId, String startTime)
			throws Exception {
		log.info(">> biId {} " + biId + " >> startTime {} " + startTime);
		if (appLoginInterceptor.checkLogin(request)) {// 登陆验证
			return ResponseEntity.ok(ResultUtil.success(appService.findSingleInfoByBiId(biId, startTime)));
		} else {
			return ResponseEntity.ok(ResultUtil.error(Constants.LOGIN_ERROR));
		}
	}

	@RequestMapping(value = "scheduled/insertSchedule", method = RequestMethod.POST)
	public ResponseEntity<ResultUtil> insertSchedule(HttpServletRequest request,
			@RequestBody ScheduledRecordExt schedule) throws Exception {
		log.info(">> obj {} " + schedule);
		if (appLoginInterceptor.checkLogin(request)) {// 登陆验证
			User user = (User) request.getSession().getAttribute(Constants.SESSION_APPUSER);
			schedule.setEmployeeId(user.getEmployeeId());
			return ResponseEntity.ok(ResultUtil.success(appService.insertSchedule(schedule)));
		} else {
			return ResponseEntity.ok(ResultUtil.error(Constants.LOGIN_ERROR));
		}
	}

	/**扫码修改会议室状态接口
	 * biId ：会议室Id
	 * employeeId：预订人员工Id
	* */
	@RequestMapping(value = "scheduled/updateStatusByQr", method = RequestMethod.GET)
	public ResponseEntity<ResultUtil> updateStatusByQr(HttpServletRequest request, String biId,String employeeId) throws Exception{
		log.info(">> biId {} " + biId + " >> employeeId {} " + employeeId);
		  String num= appService.updateStatusByQr(biId,employeeId);
		  if (num!=null){
			  return ResponseEntity.ok(ResultUtil.success(num));
		  }
		  else {
			return ResponseEntity.ok(ResultUtil.error("亲，您的会议时间还没有到!!"));
		  }
	}

	/**修改会议室状态
	 * srId: 申请记录Id
	 * status: 状态
	 * */
	@RequestMapping(value = "scheduled/updateStatusBySrId", method = RequestMethod.GET)
	public ResponseEntity<ResultUtil> updateStatusBySrId(HttpServletRequest request, String srId,Integer status ) throws Exception{
		log.info(">> srId {} " + srId +">> status {}"+status );
		if (appLoginInterceptor.checkLogin(request)) {// 登陆验证
			User user = (User) request.getSession().getAttribute(Constants.SESSION_APPUSER);
			Integer num= appService.updateStatusBySrId(srId,user.getEmployeeId(),status);
			if (num>=1){
				return ResponseEntity.ok(ResultUtil.success());
			}
			else {
				return ResponseEntity.ok(ResultUtil.error("操作失败"));
			}
		} else {
			return ResponseEntity.ok(ResultUtil.error(Constants.LOGIN_ERROR));
		}
	}

	/**
	 * 获取当前登录人信息
	 *
	 * */
	@RequestMapping(value = "scheduled/getCurrentUserInfo",method = RequestMethod.GET)
	public ResponseEntity<ResultUtil> getCurrentUserInfo(HttpServletRequest request) throws Exception{
		if (appLoginInterceptor.checkLogin(request)) {// 登陆验证
			User user = (User) request.getSession().getAttribute(Constants.SESSION_APPUSER);
			return ResponseEntity.ok(ResultUtil.success(user));
		}else {
			return ResponseEntity.ok(ResultUtil.error(Constants.LOGIN_ERROR));
		}
	}

}
