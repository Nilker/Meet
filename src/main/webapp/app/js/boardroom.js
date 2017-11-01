$(function() {
	var officeId = getQueryString("officeId");;
	var startDay = getQueryString("startTime");;
	loadBoardList(officeId, startDay);
});
function getQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
//预定页面
function toSchedule(biId){
//	alert(biId);
	 var u = navigator.userAgent;
	 var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
	 var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端is
	 if(isAndroid){
		 console.log('getSchedule'+"?"+biId); 
	 }else if(isiOS){
	        window.location.href='bluebird://getSchedule?bid='+biId;
	 }
}
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
			if (data.msg == 'loginError') {
				loginauthorizefailed();return;
			}
			if (data.msg == 'success') {
				var boardroomList = data.data;
//				boardroomList = [];
				if(boardroomList.length == 0 || boardroomList == '' || boardroomList == null){
//	//				console.info(boardroomList);
					$(".table_box").append("<div class='info'>此办公区暂没有可预订的会议室</div>");
					$(".info").show();
					return;
				}
				getList(boardroomList);
				$('.table_box .table_item ul .booked').on(
						'click',
						function(e) {
							e.stopPropagation();
							$(this).siblings().removeClass("clicked");
//							$(this).parent('ul').parent('table_item').siblings().children('ul').find('li').removeClass('clicked');
//							console.info($(this).parent('ul').parent('table_item').siblings().children('ul'));
							$("[booked-click="+$(this).attr('booked-click')+"]").addClass("clicked");
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
							$(this).parent().parent().siblings('.table_item')
							.children('ul').find('li').removeClass(
									'clicked');
						});
				$('.table_item ul').on(
						'click',
						function(e) {
							e.stopPropagation();
							$(this).parents('.table_item').siblings(
									'.table_item').find('li').removeClass(
									'clicked');
							$(this).find('li').removeClass('clicked');
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
									if (data.msg == 'loginError') {
										loginauthorizefailed();
										return;
									}
									if(data.msg == 'success'){
										$(".layer_toBook").empty();
										var boardHead = "<div class='info fl'><p>"+data.data.biFloor+"层-"+data.data.biName+"  &nbsp;&nbsp;（"+data.data.biCapacity+"座）</p>";
										var boardEnd = "<div  class='button'>预定</div>";
										var boardBody = "";
										var equipArr = XY.dec2bin(data.data.equipment).split("");
										for (var i = 0; i < equipArr.length; i++) {
											if (equipArr[i] == 1) {
												boardBody+="、"+equipmentArr[i];
											}
										}
										$(".layer_toBook").append(boardHead+"<p>"+boardBody.substring(1)+"</p></div>"+boardEnd);
										$(".button").on('click',function(e){
											e.stopPropagation();
//											alert(biId);//立即预定
											toSchedule(biId);
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
	$.ajax({
		url : urlObj.findInfoByBiId,
		type : 'get',
		dataType : 'json',
		data : {
			biId : biId,
			startTime : startTime
		},
		success : function(data) {
			if (data.msg == 'loginError') {
				loginauthorizefailed();
				return;
			}
			if (data.msg == 'success') {
//				console.info(data.data.scheduleList);
				$('.layer_booked').empty();
				var appenstr = "";
				appenstr += "<p>"
					+ data.data.scheduleList.meetingTheme
					+ "&nbsp;&nbsp;（"
					+ new Date(data.data.scheduleList.startTime)
							.Format('hh:mm')
					+ " --"
					+ new Date(data.data.scheduleList.endTime)
							.Format('hh:mm') + "）</p><p>"
					+ data.data.scheduleList.employeeName
					+ "&nbsp;&nbsp;&nbsp;&nbsp;";
				if(Trim(data.data.scheduleList.phone) != null && Trim(data.data.scheduleList.phone) != ''){
					appenstr += "<span>"+data.data.scheduleList.phone +"</span>"
					+ " <span class='phone'><img src='" + urlPrefix
					+ "phone.png'></span></p>";
				}else{
					appenstr += "</p>";
				}
				$(".layer_booked").append(appenstr);
				$('.layer_booked').css('height', '1.5rem');
				$('.layer_toBook').css('height', '0');
				$(".phone").on("click",function(e){
					e.stopPropagation();
//					alert($(this).parent().children().text());
					appFunction('phone?','phoneNumber=',$(this).parent().children().text());
				});
			}
		},
		error : function(err) {
			console.log(err)
		}
	});
}
function Trim(str)
{ 
    return str.replace(/(^\s*)|(\s*$)/g, ""); 
}
function getList(boardroomList) {
	console.log(boardroomList);
	var scheduleArr = [];
	var schedIndex = [];
	for (var i = 0; i < boardroomList.length; i++) {
		$(".table_box").append(
				"<div class='table_item'><div class='table_title'>"
						+ boardroomList[i].biFloor + "层-"
						+ boardroomList[i].biName + "("
						+ boardroomList[i].biCapacity + "座)<span style = 'display:none;'>"
						+ boardroomList[i].biId
						+ "</span></div><ul class='ul' id='id" + i
						+ "'></ul></div>");
		for (var x = 0; x < 18; x++) {
			var classStyle = "";
			if(x == 0){
				classStyle += "class='first'";
			}
			if(x == 17){
				classStyle += "class='last'";
			}
			$("#id" + i).append(
					"<li id=liId" + i + "x" + x
							+ " "+classStyle+">&nbsp;</li><span style='display:none;'>"
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
					$('#liId' + i + "x" + b).attr('booked-click',"click" + a + i);
//					 console.log("我加边框了" + '#liId' + i + "x" + b + "==a==" + a + i);
				} else {
					$('#liId' + i + "x" + b).addClass('booked');
					$('#liId' + i + "x" + b).attr('booked-click',"click" + a + i);
//					 console.log("我没加边框" + '#liId' + i + "x" + b + "==a==" + a+i);
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
function appFunction(functionName,paramName,param){
	var u = navigator.userAgent;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端is
	if(isAndroid){
		console.log(functionName+param); 
//		alert(functionName+param);
	}else if(isiOS){
//		alert('bluebird://'+functionName+paramName+param);
		window.location.href='bluebird://'+functionName+paramName+param;
	}
}
function loginauthorizefailed(){
	var u = navigator.userAgent;
	 var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
	 var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端is
	 if(isAndroid){
		 console.log('loginauthorizefailed'); 
	 }else if(isiOS){
	        window.location.href='bluebird://loginauthorizefailed';
	 }
}
