$(function() {
	var officeId = 1;
	var startDay = '2017-10-18';
	var boardroomList = [];
	var scheduleList = [];
	loadBoardList(officeId, startDay);
});
function loadBoardList(officeId, startTime) {
	$.ajax({
		url : urlObj.scheduleOffice,
		type : 'get',
		dataType : 'json',
		data : {
			officeId : officeId,
			startTime : startTime
		},
		success : function(data) {
			if (data.msg == 'success') {
				var boardroomList = data.data;
				getList(boardroomList);
				$('.table_box .table_item ul .booked').on(
						'click',
						function(e) {
							e.stopPropagation();
							var startTimes = $(this).children().text();
							startTimes = startTime + " "
									+ schedTimeArr[startTimes] + ":59";
							var biId = $(this).next().text();
							clickNotSchedule(biId, startTimes);
							$(this).parents('ul').find('li').removeClass(
									'active');
							$(this).parent().parent().siblings('.table_item')
									.children('ul').find('li').removeClass(
											'active');
						});
				$('.table_item ul').on(
						'click',
						function(e) {
							e.stopPropagation();
							$(this).parents('.table_item').siblings(
									'.table_item').find('li').removeClass(
									'active');
							$(this).find('li').addClass('active');
							var biId = $(this).prev().children().text();
							$.ajax({
								url : urlObj.findBoardByPrimaryKey,
								type : 'get',
								dataType : 'json',
								data : {
									biId : biId
								},
								success : function(data) {
									if(data.msg == 'success'){
										$(".layer_toBook").empty();
										var boardHead = "<p class='info fl'>"+data.data.biFloor+"-"+data.data.biName+"  &nbsp;&nbsp;（"+data.data.biCapacity+"座）<br><br>";
										var boardEnd = "</p><div class='button'>预定</div>";
										var boardBody = "";
										var equipArr = XY.dec2bin(data.data.equipment).split("");
										for (var i = 0; i < equipArr.length; i++) {
											if (equipArr[i] == 1) {
												boardBody+=equipmentArr[i];
											}
										}
										$(".layer_toBook").append(boardHead+boardBody+boardEnd);
										$(".button").on('click',function(){
											alert(data.data.biId);//立即预定
										});
									}
								},
								error : function(err) {
									console.log(err)
								}
							});
							$('.layer_booked').css('height', '0');
							$('.layer_toBook').css('height', '1.5rem');
						});
			}
		},
		error : function(err) {
			console.log(err)
		}
	});
}
// 不可预定
function clickNotSchedule(biId, startTime) {
	// $(this).find('li').removeClass('active');
	// $(this).parent().parent().siblings().find('li').removeClass('active');
	$.ajax({
		url : urlObj.findInfoByBiId,
		type : 'get',
		dataType : 'json',
		data : {
			biId : biId,
			startTime : startTime
		},
		success : function(data) {
			if (data.msg == 'success') {
				$('.layer_booked').empty();
				$(".layer_booked").append(
						"<p>"
								+ data.data.scheduleList.meetingTheme
								+ "&nbsp;&nbsp;（"
								+ new Date(data.data.scheduleList.startTime)
										.Format('hh:mm')
								+ " --"
								+ new Date(data.data.scheduleList.endTime)
										.Format('hh:mm') + "）</p><p>"
								+ data.data.scheduleList.employeeName
								+ "&nbsp;&nbsp;&nbsp;&nbsp;"
								+ data.data.scheduleList.phone
								+ " <span class='phone'><img src='" + urlPrefix
								+ "phone.png'></span></p>");
				$('.layer_booked').css('height', '1.5rem');
				$('.layer_toBook').css('height', '0');
			}
		},
		error : function(err) {
			console.log(err)
		}
	});
}
function getList(boardroomList) {
	console.log(boardroomList);
	var scheduleArr = [];
	var schedIndex = [];
	for (var i = 0; i < boardroomList.length; i++) {
		$(".table_box").append(
				"<div class='table_item'><div class='table_title'>"
						+ boardroomList[i].biFloor + "-"
						+ boardroomList[i].biName + "("
						+ boardroomList[i].biCapacity + "座)<span>"
						+ boardroomList[i].biId
						+ "</span></div><ul class='ul' id='id" + i
						+ "'></ul></div>");
		for (var x = 0; x < 18; x++) {
			$("#id" + i).append(
					"<li id=liId" + i + "x" + x
							+ ">&nbsp;</li><span style='display:none;'>"
							+ boardroomList[i].biId + "</span>");
		}
		newArr3 = searchIndex(schedTimeArr, boardroomList[i].startEndTimeArr);
		for (var a = 0; a < newArr3.length; a++) {
			for (var b = newArr3[a][0]; b < newArr3[a][newArr3[a].length - 1]; b++) {
				// console.log(newArr3[a][0] + "==newArr3[" + a + "][0]==="
				// + newArr3[a][newArr3[a].length - 1] + "==newArr3[" + a
				// + "][newArr3[" + a + "].length - 1]");
				if (b == newArr3[a][newArr3[a].length - 1] - 1) {
					$('#liId' + i + "x" + b).addClass('booked');
					$('#liId' + i + "x" + b).css('border-color', '#fff');
					// console.log("我加边框了" + '#liId' + i + "x" + b);
				} else {
					$('#liId' + i + "x" + b).addClass('booked');
					// console.log("我没加边框" + '#liId' + i + "x" + b);
				}
				$("#liId" + i + "x" + b).append(
						"<span style='display:none;'>" + newArr3[a][0]
								+ "</span>");
			}
		}
	}
}
function intStrToAarry(intStr) {
	var nanArray = [];

	if (intStr == null) {
		return nanArray;
	}
	for (var i = intStr.split(",")[0]; i <= intStr.split(",")[1]; i++) {
		nanArray[i] = i;
	}
	return nanArray;
}

function searchIndex(origArr, newArr) {
	// console.info("origArr{}"+origArr+"newArr{}"+newArr);
	var resultArr = '';
	var newArr1 = [];
	var newArr2 = [];
	var newArr3 = [];
	if (newArr == null || newArr.length == 0) {
		return resultArr;
	}
	for (var i = 0; i < newArr.length; i++) {
		var newArr2 = [];
		newArr1 = newArr[i].split("-");
		// console.info("====newArr1===="+[i]+"==");
		// console.info(newArr1);
		// console.info("newArr2{}"+newArr2);
		for (var j = 0; j < newArr1.length; j++) {
			// console.info("===newArr2===["+j+"]");
			// console.info(newArr1[j]);
			// alert(schedTimeArr.indexOf(newArr2[j])+"==j=="+j);
			newArr2.push(schedTimeArr.indexOf(newArr1[j]));
		}
		// resultArr=resultArr.substring(1);
		// resultArr='';
		newArr3.push(newArr2);
	}
	// console.info("======newArr3");
	// console.info(newArr3);
	// console.info("======end+====newArr3");
	// for (var i = 0; i < newArr3.length; i++) {
	// for (var j = newArr3[i][0]; j <= newArr3[i][newArr3[i].length - 1]; j++)
	// {
	// console.log(j);
	// }
	// console.log('--');
	// }
	return newArr3;
}
function test(temp_1, temp_2) {
	var temp_1 = [];
	var temp_2 = [];
	temp_2.push(1);
	temp_2.push(5);
	temp_1.push(temp_2);
	temp_2 = [];
	temp_2.push(8);
	temp_2.push(11);
	temp_1.push(temp_2);
	console.log(temp_1);
	console.log(temp_2);
	for (var i = 0; i < temp_1.length; i++) {
		for (var j = temp_1[i][0]; j <= temp_1[i][temp_1[i].length - 1]; j++) {
			console.log(j);
		}
		console.log('--');
	}
}