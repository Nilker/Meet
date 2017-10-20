//3秒定时器
$(function() {
	var mesTimeOut;
	var username = document.cookie.split(";")[0].split("=")[1];
	// alert(username);
	var biId = "3";
	startDay = "2017-10-20";// 用户选择
	scheduleInitial(biId, startDay);
});
//预定成功2秒跳转
function clearMes(){
	$(".model").remove();
	window.clearTimeout(mesTimeOut); 
}
// 添加与会人
function addAttendee(employeeIds) {
	// 获取与会人Id
	employeeIds = [ "0214", "0216", "0213" ];
	employeeIdName = [ "李梦茹", "孟然", "许松玉" ,"孙尚香" ];
	var attendStr = "";
	attendStr += "<ul class='add_people'><li class='list_name'>与会人</li>"
	for (var i = 0; i < employeeIds.length; i++) {//绑定人员ID和人员名称
		attendStr += "<li class='peoples'><span value='"+employeeIds[i]+"'>" + employeeIdName[i]
				+ "</span>、</li>"
	}
	attendStr += "<div class='clear'></div>"
			+ "<li class='add'><span id='empIds' style='display:none;'>"
			+ employeeIds + "</span></li></ul>"
	$(".list").append(attendStr);
	$(".peoples").on("click", function(e) {
		e.stopPropagation();
		$(this).siblings().removeClass("active");
		$(this).addClass("active");
//		alert($(this).text().substring(0,4));
		var empName=$(this).text();
		empName=empName.substr(0,empName.length-1);
		var delAddentee="<div class='model'>"
			+"<div class='layer' style='width:4.7rem;height:2.5rem;background: #fff;top:0;margin:auto;padding-top: 0.2rem;'>"
			+"<p style='text-align: center;'>确定要删除 <span style='color:#1f97f4'>"+empName+"</span> 吗？</p>"
			+"<p><span class='but_ok'>确定</span><span class='but_cancel'>取消</span></p>"
			+"</div>"
			+"</div>";
		$("body").append(delAddentee);
		$(".but_ok").on("click",function(e){
			delArry(empName,employeeIdName);//应该删除人员ID数组======
			$(".active").text("");
			$(".model").remove();
		});
		$(".but_cancel").on("click",function(e){
			$(".model").remove();
		});
	});
}
function delArry(delElement,arr){
	var index=$.inArray(delElement,arr);
	if(index>=0){
		//arrayObject.splice(index,howmany,item1,.....,itemX)
		//参数  描述
		//index 必需。整数，规定添加/删除项目的位置，使用负数可从数组结尾处规定位置。
		//howmany 必需。要删除的项目数量。如果设置为 0，则不会删除项目。
		//item1, ..., itemX 可选。向数组添加的新项目。
		 arr.splice(index,1);
		}else{
		 alert("error");
		 return false;
		}
}
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
	ids = $("#empIds").text().split(",");
	scheduleExt.employeeIds = ids;
	scheduleExt.biId = $("#biId").text();
	scheduleExt.startTime = new Date(beginDay + " " + startEndTime.split("-")[0] + ":59");
	scheduleExt.endTime = new Date(beginDay + " " + startEndTime.split("-")[1] + ":00");
	scheduleExt.meetingTheme = $("input").val();
	scheduleExt.officeId = $("#officeId").text();
	console.info(scheduleExt);
	$.ajax({
        type:"POST",
        url:urlObj.insertSchedule,
        data:JSON.stringify(scheduleExt),
        dataType : "json",
		contentType : 'application/json',
        success:function(data){
        	if(data.msg == 'success' && data.data == 0){
        		var toast="";
                toast+=""
                +"<div class='model'><div class='layer' style='width:4.7rem;height:1.4rem;background: #fff;top:0;margin:auto;padding-top: 0.2rem;'>"
                +"  <p style='text-align: center;'>预定成功</p>"
                +" </div><div>"
                $("body").append(toast);
                   mesTimeOut=setTimeout(function() {
        				clearMes()
        			},2000);
        	}else{
        		var toast="";
                toast+=""
                +"<div class='model'><div class='layer' style='width:4.7rem;height:1.4rem;background: #fff;top:0;margin:auto;padding-top: 0.2rem;'>"
                +"  <p style='text-align: center;'>预定失败</p>"
                +" </div><div>"
                $("body").append(toast);
                   mesTimeOut=setTimeout(function() {
        				clearMes()
        		},2000);
        	}
        },
        async: false,
//        beforeSend : function(xhr) {  
//            var cookie = credentials["COOKIE"];//此处设置cookie
//            console.info( "adding cookie: "+ cookie );          
//            xhr.setRequestHeader('Cookie', cookie);
//         },
        error: function(err){
            console.log(err);
        }      
     });
}
// 初始化
function scheduleInitial(biId, startDay) {
	$
			.ajax({
				url : urlObj.findSingleInfoByBiId,
				type : 'get',
				dataType : 'json',
				data : {
					biId : biId,
					startTime : startDay
				},
				success : function(data) {
					console.info(data.data);
					if (data.msg == 'success') {
						var beforeStr = "";
						var boardroomList = data.data.boardroomList;
						var scheduleList = data.data.scheduleList;
						var strartDay = startDay;
						var headStr = "<div class='title'><h2> "
								+ boardroomList.biFloor + "层-"
								+ boardroomList.biName + " </h2><span>"
								+ boardroomList.biCapacity
								+ "人</span><span id='officeId' style='display:none;'>"+boardroomList.officeId+"</span><span id='biId' style='display:none;'>"+boardroomList.biId+"</span><div class='clear'></div><ul>";
						var equipmentInfo1 = "<li id='eq0' style='display:none;'><img src='"
								+ urlPrefix
								+ "icon2.png' style='width:0.4rem;'>"+equipmentArr[0]+"</li>";
						var equipmentInfo2 = "<li id='eq1' style='display:none;'><img src='"
								+ urlPrefix
								+ "icon5.png' style='width:0.34rem;'>"+equipmentArr[1]+"</li>";
						var equipmentInfo3 = "<li id='eq2' style='display:none;'><img src='"
								+ urlPrefix
								+ "icon1.png' style='width:0.35rem;'>"+equipmentArr[2]+"</li>";
						var equipmentInfo4 = "<li id='eq3' style='display:none;'><img src='"
								+ urlPrefix
								+ "icon3.png' style='width:0.25rem;'>"+equipmentArr[3]+"</li>";
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
						//
						var body = "<ul><li class='list_name'>会议主题</li><li class='list_text'><input type='text' style='border: none;'></li></ul><ul><li class='list_name'>会议日期</li><li class='list_text'>"
								+ strartDay
								+ "</li><div class='clear'></div><li class='table'><ol><li id='0' value='0900'>09</li><li id='1' value='0930'></li><li id='2' value='1000'>10</li><li id='3' value='1030'></li>"
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
								+ "</ul><ul><li class='list_name'>会议时间</li><li class='list_text'><span id='start'></span></li></ul>";
						$(".list").append(body);
						$(".table ol li")
								.on(
										'click',
										function(e) {
											e.stopPropagation();
											$(this).addClass("active");
											$(this).siblings().removeClass(
													"active");
											$(this).parent().siblings()
													.children().removeClass(
															"active");
											
											var str = $(this).val() + "";// 点击获取值
											var nextStr = $(this).next().val() + "";
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
											var start = "";
											var staTimeStr = $("#start").text();// 目前值
											if ($("#start").text().length > 5) {
												$("#start").text("");
												staTimeStr = "";
											}
											if (staTimeStr == null
													|| staTimeStr == '') {
												start = str.substring(0, 2)
														+ ":"
														+ str.substring(2, 4);
												$("#start").text(start);
											} else {
												// 获取点击值
												var t = staTimeStr.substring(0,
														2)
														+ staTimeStr.substring(
																3, 5);
												var currentClick = str
														.substring(0, 2)
														+ ":"
														+ str.substring(2, 4);
												var exsitTime = t.substring(0,
														2)
														+ ":"
														+ t.substring(2, 4);
												if (str == t) {
													return;
												}
												if (str < t) {
													$("#start")
															.text(
																	currentClick
																			+ "-"
																			+ exsitTime);
												} else {
													$("#start")
															.text(
																	exsitTime
																			+ "-"
																			+ currentClick);
												}
											}
										});
						for (var i = 0; i < scheduleList.length; i++) {
							var startIdx = '0';
							var endIdx = '0';
							var startTime = new Date(scheduleList[i].startTime)
									.Format('hh:mm');
							var endTime = new Date(scheduleList[i].endTime)
									.Format('hh:mm');
							startIdx = schedTimeArr.indexOf(startTime);
							endIdx = schedTimeArr.indexOf(endTime);
							for (var j = startIdx; j <= endIdx; j++) {
								$("#" + j).addClass("booked");
								$("#" + j).unbind("click");
							}
						}
						addAttendee(1);
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
