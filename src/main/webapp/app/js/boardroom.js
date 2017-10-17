$(function() {
	schedTimeArr = [ "09:00", "09:30", "10:00", "10:30",
			"11:00","11:30", "12:00","12:30", "13:00", "13:30", "14:00",
			"14:30", "15:00", "15:30", "16:00", "16:30",
			"17:00", "17:30", "18:00" ];
	var officeId = 1;
	var startTime = '2017-10-11';
	var boardroomList = [];
	var scheduleList = [];
	loadBoardList(officeId,startTime);
});
function loadBoardList (officeId,startTime){
	$.ajax({
		url : urlObj.scheduleOffice,
		type : 'get',
		dataType : 'json',
		data : {
			officeId : officeId,
			startTime : startTime
		},
		success : function(data) {
			// alert(scheduleStatus('09:30:59'));
//			 alert(schedTimeArr.indexOf('11:30:00'));
			//
			console.info(data);
			if (data.msg == 'success') {
				boardroomList = data.data;
//				var i=["09:30:00-11:30:00","09:00:59-10:30:00"];
//				getList(boardroomList);
				console.info(intStrToAarry("2,7"));
//				console.log($('.table_box .table_item .booked'))
				 $('.table_box .table_item ul .booked').on('click',function(e){
			         e.stopPropagation();
//			         alert(startTime+" "+$(this).next().text().split("-")[1]+":59");
			         var startTimes=startTime+" "+$(this).next().text().split("-")[1]+":59";
			         var biId=$(this).next().text().split("-")[0];
			         clickNotSchedule(biId,startTimes);
			         $('.layer_booked').css('height','1.5rem');
			         $('.layer_toBook').css('height','0');
			         $(this).parents('ul').find('li').removeClass('active');
			     });
				 $('.table_item ul').on('click',function(e){
			            e.stopPropagation();
			            $(this).parents('.table_item').siblings('.table_item').find('li').removeClass('active');
			            $(this).find('li').addClass('active');
			            $('.layer_booked').css('height','0');
			            $('.layer_toBook').css('height','1.5rem');
			     });
			}

		},
		error : function(err) {
			console.log(err)
		}
	});
}
//不可预定
function clickNotSchedule(biId,startTime){
	alert(startTime);
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
				console.info(data.data);
//				$(".layer.layer_booked").append("<p>"++"&nbsp;&nbsp;（14：00 -- 18：00）</p>");
//				 <p>产品技术委员会例会&nbsp;&nbsp;（14：00 -- 18：00）</p>
//			        <p>常钰&nbsp;&nbsp;&nbsp;&nbsp;13699213531    <span class='phone'><img src="../app/images/phone.png"></span></p>
			}
		},
		error : function(err) {
			console.log(err)
		}
	});
}
function getList(boardroomList) {
	var scheduleArr=[];
	var schedIndex=[];
	for (var i = 0; i < boardroomList.length; i++) {
		$(".table_box").append(
				"<div class='table_item'><div class='table_title'>"
						+ boardroomList[i].biFloor + "-"
						+ boardroomList[i].biName + "("
						+ boardroomList[i].biCapacity + "座)</div><ul class='ul' id='id"
						+ i + "'></ul></div>");
		for (var x = 0; x < 18; x++) {
			$("#id" + i).append("<li id=liId"+i + x + ">&nbsp;</li>");
		}
		var scheduleLen=0;
		var indexList = new Array();
		scheduleArr = searchIndex(schedTimeArr,boardroomList[i].startEndTimeArr);
		console.info("scheduleArr" + scheduleArr);
		console.info("scheduleArr.length" + scheduleLen);
		scheduleLen = scheduleArr.length;
		alert(scheduleArr.length);
		var startScheIdx = 0 ;
		var endScheIdx = 0;
		for (var n = 0; n < scheduleLen; n++){
			startScheIdx = scheduleArr[n].split(",")[0];
			endScheIdx = scheduleArr[n].split(",")[1];
			alert(startScheIdx + "====startScheIdx ");
			alert(endScheIdx + "====endScheIdx"); 
			for (var j = startScheIdx ; j <= endScheIdx; j++) {
				alert(j +" ===== j");
				console.info("j:"+j+"schedIndex[0]"+startScheIdx+"schedIndex[1]"+endScheIdx);
				if(j==endScheIdx){
					$('#liId' + i + j).addClass('booked');
					$('#liId' + i + j).css('border-color','#fff');
//					$('#liId' + i + j).append("<span style='display:none;'>"+boardroomList[i].biId+"-"+schedTimeArr[schedIndex[0]]+"</span>");
				}else{
					$('#liId'+i+j).addClass('booked');
				}
				$('#liId'+i+j).append("<span style='display:none;'>"+boardroomList[i].biId+"-"+schedTimeArr[startScheIdx]+"</span>");
				
			}
			
		}
	}
}
function intStrToAarry(intStr){
	var nanArray=[];
	
	if(intStr==null){
		return nanArray;
	}
	for(var i = intStr.split(",")[0];i<=intStr.split(",")[1];i++){
		nanArray[i]=i;
	}
	return nanArray;
}

function searchIndex(origArr, newArr) {
//	console.info("origArr{}"+origArr+"newArr{}"+newArr);
	var resultArr='';
	var newArr2=[];
	var newArr3=[];
	if (newArr == null || newArr.length == 0) {
		return resultArr;
	}
	for (var i = 0; i < newArr.length; i++) {
		newArr2=newArr[i].split("-");
//		console.info("newArr2{}"+newArr2);
		for (var j = 0; j < newArr2.length; j++) {
//			console.info("newArr2["+j+"]"+newArr2[j]);
			resultArr +=','+schedTimeArr.indexOf(newArr2[j]);
		}
		resultArr=resultArr.substring(1);
		newArr3[i]=resultArr;
		resultArr='';
	}
//	console.info("newArr3{}"+newArr3);
	return newArr3;
}




