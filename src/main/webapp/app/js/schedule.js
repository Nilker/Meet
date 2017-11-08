//3秒定时器
$(function() {
	var mesTimeOut;
	scheduleInitial(getQueryString("biId"), getQueryString("startTime"));
});

function getQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
// 预定成功2秒跳转
function clearMes() {
	$(".model").remove();
	window.clearTimeout(mesTimeOut);
	
}
//已预约
function noClick(param){
//	console.info(param);
	for (var i = 0; i < param.length; i++) {
		var startTime = schedTimeArr.indexOf(new Date(param[i].startTime).Format("hh:mm"));
		var endTime = schedTimeArr.indexOf(new Date(param[i].endTime).Format("hh:mm"));
		for(var j = startTime; j < endTime; j++){
			$('#' + j).addClass('booked');
		}
	}
}
// 添加与会人
function addAttendee(employeeIds) {
//	alert("员工ID串 ：" + employeeIds);
//	employeeIds = "1192|李晓寒";
//	10003, 10009, 10012, 10011
	if(employeeIds.length == 0 || employeeIds == null){
		return;
	}
	var employeeIdAttr = [];
	employeeIdAttr = employeeIds.split(",");
	console.log(employeeIdAttr);
	// 获取与会人Id
	$(".peoples").empty();
	var attendStr = "";
	for (var i = 0; i < employeeIdAttr.length; i++) {// 绑定人员ID和人员名称
		empId = employeeIdAttr[i].split("|")[0];
		empName = employeeIdAttr[i].split("|")[1];
		console.log(empId+empName);
		attendStr += "<li class='peoples'><span class = 'empId' style = 'display:none;'>" + empId
			+ "|</span><span>"+ empName + "</span>";
		if(i == employeeIdAttr.length-1){
			attendStr += "</li>";
		}else{
			attendStr +="、</li>";
		}
	}
	$(".add_people .list_name").after(attendStr);
	$(".peoples").on("click",function(e) {
						e.stopPropagation();
						$(this).siblings().removeClass("active");
						$(this).addClass("active");
						$(this).addClass("empId");
						// alert($(this).text().substring(0,4));
						var empName = $(this).find("span:last-child").text();
						var empId = $(this).find("span:first-child").text().substring(0,$(this).find("span:first-child").text().length-1);
//						empName = empName.substr(0, empName.length - 1);
//						alert(empName+empId);
						var delAddentee = "<div class='model'>"
								+ "<div class='layer' style='width:4.7rem;height:2.5rem;background: #fff;top:0;margin:auto;padding-top: 0.2rem;'>"
								+ "<p style='text-align: center;'>确定要删除 <span style='color:#1f97f4'>"
								+ empName
								+ "</span> 吗？</p>"
								+ "<p><span class='but_ok'>确定</span><span class='but_cancel'>取消</span></p>"
								+ "</div>" + "</div>";
						$("body").append(delAddentee);
						$(".but_ok").on("click", function(e) {
							$(this).parent().parent().parent().siblings(".list").children(".add_people").children(".active").text("");
							$(".model").remove();
						});
						$(".but_cancel").on("click", function(e) {
							$(".model").remove();
						});
					});
}
themeMsg = "请填写会议主题";
timeMsg = "请选择会议时间";
//employeeIdsMsg = "请选择与会人";
function checkSche(scheduleExt) {
	var startTime = $("#start").text();
	if (scheduleExt.meetingTheme == null || scheduleExt.meetingTheme == '') {
		return themeMsg;
	}
	if (startTime == null || startTime == '' || startTime.length < 3) {
		return timeMsg;
	}
//	if (scheduleExt.employeeIds == null || scheduleExt.employeeIds == '') {
//		return employeeIdsMsg;
//	}
	return "";
};
// 立即预定
function scheduleBoard() {
	var scheduleExt = {};
	var ids = [];
	var startTime = "";
	var startEndTime = "";
	var beginDay = "";
	startTime = $(".list_text").text();
	beginDay = startTime.substring(0, 10);
	startEndTime = startTime.substring(10);
	scheduleExt.employeeIds = $(".empId").text().substring(0,$(".empId").text().length-1).split("|");
	scheduleExt.biId = $("#biId").text();
	
	dateStart = beginDay + " "
			+ startEndTime.split("-")[0] + ":59";
	dateEnd = beginDay + " "
			+ startEndTime.split("-")[1] + ":00";
	scheduleExt.startTime = new Date(dateStart.replace(new RegExp(/-/gm) ,"/"));
	scheduleExt.endTime = new Date(dateEnd.replace(new RegExp(/-/gm) ,"/"));
	scheduleExt.meetingTheme = $("input").val();
	scheduleExt.officeId = $("#officeId").text();
	console.log("======"+scheduleExt);
	if (checkSche(scheduleExt) != "") {
		layer.msg(checkSche(scheduleExt));
		return;
	}
$.ajax({
				type : "POST",
				url : urlObj.insertSchedule,
				data : JSON.stringify(scheduleExt),
				dataType : "json",
				contentType : 'application/json',
				success : function(data) {
					if (data.msg == 'loginError') {
//						loginauthorizefailed();
						appFunction('loginauthorizefailed','','');
						return;
					}
					
					if (data.msg == 'success' && data.data == 0) {
						var toast = "";
						toast += ""
								+ "<div class='model'><div class='layer' style='width:4.7rem;height:1.4rem;background: #fff;top:0;margin:auto;padding-top: 0.2rem;'>"
								+ "  <p style='text-align: center;'>预定成功</p>"
								+ " </div><div>"
						$("body").append(toast);
						mesTimeOut = setTimeout(function() {
							clearMes();
							appFunction('scheduleSuccess','','');
						}, 2000);
					}
					if(data.data == 2){
						var toast = "";
						toast += ""
								+ "<div class='model'><div class='layer' style='width:4.7rem;height:1.4rem;background: #fff;top:0;margin:auto;padding-top: 0.2rem;'>"
								+ "  <p style='text-align: center;'>会议室已停用</p>"
								+ " </div><div>"
						$("body").append(toast);
						mesTimeOut = setTimeout(function() {
							clearMes();
						}, 2000);
					}
					if (data.data == 1){
							var toast = "";
							toast += ""
									+ "<div class='model'><div class='layer' style='width:4.7rem;height:1.4rem;background: #fff;top:0;margin:auto;padding-top: 0.2rem;'>"
									+ "  <p style='text-align: center;'>会议时间已经被占用，请重新选择</p>"
									+ " </div><div>"
							$("body").append(toast);
							mesTimeOut = setTimeout(function() {
								clearMes();
								location.reload();
							}, 2000);
					}
				},
				async : false,
				error : function(err) {
					console.log(err);
				}
			});
}
// 初始化
function scheduleInitial(biId, startDay) {
	$.ajax({
				url : urlObj.findSingleInfoByBiId,
				type : 'get',
				dataType : 'json',
				data : {
					biId : biId,
					startTime : startDay
				},
				success : function(data) {
//					alert(data);
					if (data.msg == 'loginError') {
//						loginauthorizefailed();
						appFunction('loginauthorizefailed','','');
						return;
					}
					if (data.msg == 'success') {
						var beforeStr = "";
						var boardroomList = data.data.boardroomList;
						var scheduleList = data.data.scheduleList;
						var headStr = "<div class='title'><h2> "
								+ boardroomList.biFloor
								+ "层-"
								+ boardroomList.biName
								+ " </h2><span>"
								+ boardroomList.biCapacity
								+ "人</span><span id='officeId' style='display:none;'>"
								+ boardroomList.officeId
								+ "</span><span id='biId' style='display:none;'>"
								+ biId
								+ "</span><div class='clear'></div><ul>";
						var equipmentInfo1 = "<li id='eq0' style='display:none;'><img src='"
								+ urlPrefix
								+ "icon2.png' style='width:0.4rem;'>"
								+ equipmentArr[0] + "</li>";
						var equipmentInfo2 = "<li id='eq1' style='display:none;'><img src='"
								+ urlPrefix
								+ "icon5.png' style='width:0.34rem;'>"
								+ equipmentArr[1] + "</li>";
						var equipmentInfo3 = "<li id='eq2' style='display:none;'><img src='"
								+ urlPrefix
								+ "icon1.png' style='width:0.35rem;'>"
								+ equipmentArr[2] + "</li>";
						var equipmentInfo4 = "<li id='eq3' style='display:none;'><img src='"
								+ urlPrefix
								+ "icon3.png' style='width:0.25rem;'>"
								+ equipmentArr[3] + "</li>";
						var headEndStr = "<div class='clear'></div></ul></div>";
						var schedStr = "<div class='but_bottom'>立即预定</div>";
						beforeStr = headStr + equipmentInfo1 + equipmentInfo2
								+ equipmentInfo3 + equipmentInfo4 + headEndStr;
						$(".list").before(beforeStr);
						var equipArr = XY.dec2bin(boardroomList.equipment)
								.split("");
						for (var i = 0; i < equipArr.length; i++) {
							if (equipArr[i] == 1) {
								$("#eq" + i).show();
							}
						}
						var body = "<ul><li class='list_name'>会议主题</li><li class='list_text'><input type='text' maxlength='10' placeholder='必填'></li></ul><ul><li class='list_name'>会议日期</li><li class='list_text'>"
								+ startDay
								+ "</li></ul>" 
								+ "<ul><li class='list_name'>会议时间</li><li class='list_text'><span id='start' style='color:#aaa;'>必填</span></li>"
								+"<div class='clear'></div><li class='table'><ol><li id='0' value='0900'>09</li><li id='1' value='0930'></li><li id='2' value='1000'>10</li><li id='3' value='1030'></li>"
								+ "<li id='4' value='1100'>11</li>"
								+ "<li id='5' value='1130'></li>"
								+ "<li id='6' value='1200'>12</li>"
								+ "<li id='7' value='1230'></li>"
								+ "<li id='8' value='1300'>13</li>"
								+ "<li id='9' value='1330' style='border-right: 0 none;'></li>"
								+ "<div class='clear'></div>"
								+ "</ol>"
								+ "<ol>"
								+ "<li id='10' value='1400'>14</li>"
								+ "<li id='11' value='1430'></li>"
								+ "<li id='12' value='1500'>15</li>"
								+ "<li id='13' value='1530'></li>"
								+ "<li id='14' value='1600'>16</li>"
								+ "<li id='15' value='1630'></li>"
								+ "<li id='16' value='1700'>17</li>"
								+ "<li id='17' value='1730'></li>"
								+ "<li id='18' value='1800'>18</li>"
								+ "<li id='19' value='1830' style='border-right: 0 none;'></li>"
								+ "<div class='clear'></div>"
								+ "</ol>"
								+ "	</li>"
								+ "</ul>";
						var attendStr = "";
						attendStr += "<ul class='add_people'><li class='list_name'>与会人</li><div class='clear'></div><li class='add'></li></ul>";
						$(".list").append(body+attendStr);
//						addAttendee(1);
						noClick(scheduleList);
						$(".add").on('click',function(){
							var userid = $(".peoples").text().replace(/、/g,',').substring(0,$(".peoples").text().length);
							appFunction('getUserID?','userid=',userid);
						});
						$(".table ol li").on('click',function(e) {
											e.stopPropagation();
						if($(this).attr("class") == 'active'){
								var currentClick = $(this).val() + "";
								var nextClick = $(this).next().val() + "";
								if (currentClick == "900") {
									currentClick = "0900";
								}
								if (currentClick == "930") {
									currentClick = "0930";
								}
								if (nextClick == "900") {
									nextClick = "0900";
								}
								if (nextClick == "930") {
									nextClick = "0930";
								}
								if (currentClick == "1330") {
									nextClick = "1400";
								}

								if (currentClick == "1830") {
									nextClick = "1900";
								}

								currentClick = currentClick.substring(0, 2)
								+ ":"
								+ currentClick.substring(2, 4);
								nextClick = nextClick.substring(0, 2)
								+ ":"
								+ nextClick.substring(2, 4);
//								console.log("click===" + currentClick);
//								console.log("nextClick===" + nextClick);
//								console.log("showMin===" + $("#start").text().split("-")[0]);
//								console.log("showMax===" + $("#start").text().split("-")[1]);
								$(this).nextAll().removeClass("active");
								$(this).parent().next().children().removeClass("active");
								$(this).removeClass("active");
								if(currentClick == $("#start").text().split("-")[0]){
									$("#start").text("必填");
								}else{
									$("#start").text($("#start").text().split("-")[0] + "-" + currentClick);
								}
							return;
						}
						if($(this).attr("class") == null || $(this).attr("class") == undefined || $(this).attr("class") == ''){
											$(this).addClass("active");
												var str = $(this).val() + "";// 点击获取值
												var prevStr = $(this).prev().val()
														+ "";
												var nextStr = $(this).next().val()
														+ "";

												if (str == "900") {
													str = "0900";
												}
												if (str == "930") {
													str = "0930";
												}
												if (nextStr == "900") {
													nextStr = "0900";
												}
												if (nextStr == "930") {
													nextStr = "0930";
												}
												if (str == "1330") {
													nextStr = "1400";
												}

												if (str == "1830") {
													nextStr = "1900";
												}

												var start = "";
												var staTimeStr = $("#start").text();// 目前值
												if(staTimeStr == '必填'){
													staTimeStr = "";
												}
												if (staTimeStr == null
														|| staTimeStr == '') {
													start = str.substring(0, 2)
															+ ":"
															+ str.substring(2, 4);
													end = nextStr.substring(0, 2)
															+ ":"
															+ nextStr.substring(2,
																	4);
													$("#start").text(
															start + "-" + end);
												} else {
													var showStrMax = staTimeStr
															.split("-")[1];
													var showStrMin = staTimeStr
															.split("-")[0];
													var currentClick = str
															.substring(0, 2)
															+ ":"
															+ str.substring(2, 4);
													var currentClickNext = nextStr
															.substring(0, 2)
															+ ":"
															+ nextStr.substring(2,
																	4);
													if (showStrMin == currentClickNext) {
														$("#start")
																.text(
																		currentClick
																				+ "-"
																				+ showStrMax);
													}
													if (showStrMax == currentClick) {
														$("#start")
																.text(
																		showStrMin
																				+ "-"
																				+ currentClickNext);
													}
													if (showStrMin != currentClickNext
															&& showStrMax != currentClick && currentClickNext < showStrMin || showStrMax < currentClick) {
														$(this).removeClass("active");
														var toast = "";
														toast += ""
																+ "<div class='model'><div class='layer' style='width:4.7rem;height:1.4rem;background: #fff;top:0;margin:auto;padding-top: 0.1rem;'>"
																+ "  <p style='text-align: center;'>必须选择连续的时间段</p>"
																+ " </div><div>"
														$("body").append(toast);
														mesTimeOut = setTimeout(function() {
															clearMes();
														}, 1000);
													}
												}
											}
										});
						var u = navigator.userAgent;
						$(".list").append(schedStr);
						$(".but_bottom").on("click", function(e) {
							e.stopPropagation();
							scheduleBoard();
						});
					}
				},
				error : function(err) {
					console.log(err)
				}
			});
}
function appFunction(functionName,paramName,param){
	var u = navigator.userAgent;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端is
	if(isAndroid){
		console.log(functionName+param); 
	}else if(isiOS){
		window.location.href='bluebird://'+functionName+paramName+param;
	}
}