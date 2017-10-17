//titleObj="<div class='title'><h2> 7层-会议室4 </h2><span>12人</span><div class='clear'></div><ul><li><img src='icon1.png' style='width:0.35rem;'>电视</li><li><img src='../app/images/icon2.png' style='width: 0.4rem;'>投影仪</li><li><img src='../app/images/icon3.png' style='width: 0.25rem;'>八爪鱼</li><div class='clear'></div></ul></div>";
$(function() {
	var username = document.cookie.split(";")[0].split("=")[1];
	// alert(username);
	var biId = "7fe2c0686999418da098cf89b6e327c7";
	var startTime = "2017-10-18";// 用户选择
	scheduleInitial(biId, startTime);
});

// 添加与会人
function addAttendee(employeeIds) {
}
// 立即预定
function scheduleBoard(scheduleObj) {
}
// 初始化
function scheduleInitial(biId, startTime) {
	$
			.ajax({
				url : urlObj.findSingleInfoByBiId,
				type : 'get',
				dataType : 'json',
				data : {
					biId : biId,
					startTime : startTime
				},
				success : function(data) {
					console.info(data.data);
					if (data.msg == 'success') {
						var boardroomList = data.data.boardroomList;
						var scheduleList = data.data.scheduleList;
						var strartDay = new Date(scheduleList[0].startTime)
								.Format('yyyy-MM-dd');
						var headStr = "<div class='title'><h2> "
								+ boardroomList.biFloor + "-"
								+ boardroomList.biName + " </h2><span>"
								+ boardroomList.biCapacity
								+ "人</span><div class='clear'></div><ul>";
						var equipmentInfo1 = "<li><img src='"
								+ urlObj.urlPrefix
								+ "icon2.png' style='width:0.4rem;'>投影仪</li>";
						var equipmentInfo2 = "<li><img src='"
								+ urlObj.urlPrefix
								+ "icon5.png' style='width:0.34rem;'>投影幕</li>";
						var equipmentInfo3 = "<li><img src='"
								+ urlObj.urlPrefix
								+ "icon1.png' style='width:0.35rem;'>电视</li>";
						var equipmentInfo4 = "<li><img src='"
								+ urlObj.urlPrefix
								+ "icon3.png' style='width:0.25rem;'>八爪鱼</li>";
						var headEndStr = "<div class='clear'></div></ul></div>";

						var beforeStr = headStr;
						//
						var body = "<ul><li class='list_name'>会议日期</li><li class='list_text'>"
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
								+ "	</li>" + "</ul>";
						for (var i = 0; i < scheduleList.length; i++) {
							var startTime = new Date(scheduleList[i].startTime)
									.Format('hh:mm');
							var endTime = new Date(scheduleList[i].endTime)
									.Format('hh:mm');
							// alert(new
							// Date(scheduleList[i].startTime).Format('hhmm')+"===scheduleList["+i+"].startTime"+new
							// Date(scheduleList[i].endTime).Format('hhmm'));
						}

						$(".list").before(beforeStr);

						$(".active").on('click', function(e) {
							alert($(this).val());
						});

					}
				},
				error : function(err) {
					console.log(err)
				}
			});
	console.info(convert(2));
	// alert(PrefixInteger(11,4));
}
