currentDay=new Date().Format('yyyy-MM-dd');
ymdDate=new Date().Format('yyyy-MM-dd');
$(function() {
	$.ajax({
		url : urlObj.findAllMeetOfSelf,
		type : 'get',
		dataType : 'json',
		success : function(data) {
			if(data.msg == 'loginError'){
				loginauthorizefailed();return;
			}
			if (data.msg == 'success') {
				var flag=0;
				var boardroomList=[];
				boardroomList=data.data;
				var dayHead="<div class='head'><h2>";
				var dayEnd="</h2></div>";
				if(boardroomList.length == 0 || boardroomList == ''){
					$(".list").append("<div class='info'>暂无预定会议</div>");
					$(".info").show();
					return;
				}

				for (var i = 0; i < boardroomList.length; i++) {
					var beginTime=new Date(boardroomList[i].startTime).Format('yyyyMMddhhmm');
					var endTime=new Date(boardroomList[i].endTime).Format('yyyyMMddhhmm');
					var currentTime=new Date().Format('yyyyMMddhhmm');
//					alert(XY.dateCompare(boardroomList[i].startTime,boardroomList[i].endTime));
					var scheBeginTime=new Date(boardroomList[i].startTime).Format('hh:mm');
					var scheEndTime=new Date(boardroomList[i].endTime).Format('hh:mm');
					var meetingTheme=boardroomList[i].meetingTheme;
					var employeeName=boardroomList[i].employeeName;
					var biFloor=boardroomList[i].biFloor;
					var biName=boardroomList[i].biName;
					var listObjHead="<div class='item";
					var listObjBody="'><i></i><p><span class='fl'>"
								+scheBeginTime+" -"
								+scheEndTime+"</span><span class='fr'>"
								+employeeName+"</span><div class='clear'></div></p><p>"
								+meetingTheme+"</p><p class='place'>"+biFloor+"层-"+biName+"</p><div class='state'>";
					var listObjEnd="</div></div>";
//					console.info("dayTime"+dayTime);
//					console.info(dayHead+dayTime+dayEnd);
					str="";
					var dayTime=new Date(boardroomList[i].startTime).Format('yyyy-MM-dd');
					var weekDay = "星期" + "日一二三四五六".charAt(new Date(boardroomList[i].startTime).getDay());
					
					if(dayTime == ymdDate){
						weekDay = '今天';
					}
					if(beginTime>currentTime){
						if(currentDay!=dayTime||i==0){
							str=dayHead+dayTime+" "+weekDay+dayEnd;
						}
						$(".list").append(str+listObjHead+" notbegin"+listObjBody+"未开始"+"<span style='display:none;'>"+boardroomList[i].srId+"</span>"+listObjEnd);
					}
					if(beginTime<=currentTime&&endTime>=currentTime){
						if(currentDay!=dayTime||i==0){
							str=dayHead+dayTime+" "+weekDay+dayEnd;
						}
						$(".list").append(str+listObjHead+" ongoing"+listObjBody+"会议中"+"<span style='display:none;'>"+boardroomList[i].srId+"</span>"+listObjEnd);
					}
					if(endTime<currentTime){
						if(currentDay!=dayTime||i==0){
							str=dayHead+dayTime+" "+weekDay+dayEnd;
						}
						$(".list").append(str+listObjHead+" over"+listObjBody+"已结束"+"<span style='display:none;'>"+boardroomList[i].srId+"</span>"+listObjEnd);
					}
					currentDay=dayTime;
//					alert(currentDay);
				}
//				$(".list .state").on('click',function(e){	
//					e.stopPropagation();
//					var meetStatus=$(this).text().substring(0,3);
//					var srId=$(this).text().substring(3);
//					detialMeet(meetStatus,srId);
//				});
				$(".over").on('click',function(e){	
					e.stopPropagation();
					var meetStatus=$(this).children().last().text().substring(0,3);
					var srId=$(this).children().last().text().substring(3);
//					alert($(this).children().last().text());
					detialMeet(meetStatus,srId);
				});
				$(".notbegin").on('click',function(e){	
					e.stopPropagation();
					var meetStatus=$(this).children().last().text().substring(0,3);
					var srId=$(this).children().last().text().substring(3);
//					alert($(this).children().last().text());
					detialMeet(meetStatus,srId);
				});
				$(".ongoing").on('click',function(e){	
					e.stopPropagation();
					var meetStatus=$(this).children().last().text().substring(0,3);
					var srId=$(this).children().last().text().substring(3);
//					alert($(this).children().last().text());
					detialMeet(meetStatus,srId);
				});
				console.info(data.data);
			}
		},
		error : function(err) {
			console.log(err)
		}
	});
});
function detialMeet(meetStatus,srId){
//	alert(meetStatus + "===meetStatus " + "srId===" + srId);
	$.ajax({
		url : urlObj.findSingleMeetBySrId,
		type : 'get',
		data :{
			srId:srId
		},
		dataType : 'json',
		success : function(data) {
			console.info(data);
			if(data.msg == 'loginError'){
				loginauthorizefailed();return;
			}
			if(data.msg=='success'){
				var dataList=data.data;
				appendStr="";
				console.info(dataList[0]);
				var startTime=new Date(dataList[0].startTime).Format("yyyy-MM-dd hh:mm");
				var endTime=new Date(dataList[0].endTime).Format("hh:mm");
				var title="<div class='title'>"+dataList[0].meetingTheme+"</div>";
				var boardBody="<ul><li><div class='list_name'>时间 :</div><div class='list_text'> "+startTime+" - "+endTime+"</div><div class='clear'></div></li><li><div class='list_name'>地点:</div><div class='list_text'> "+dataList[0].biFloor+"-"+dataList[0].biName+"</div><div class='clear'></div></li>";
				var attenBodyHead="<li><div class='list_name'>";
				var attenBodyEnd=":</div>";
				var attenNameHead="<div class='list_text'>";
				var spanLabelBeg="<span>";
				var spanLabelEnd="</span>";
				var attenNameEnd="</div>";
				var attenEnd="</li>";
				var strEnd="<div class='clear'></div></ul><div class='clear'></div>";
				var mark="<p class='mark'>会议时间已过，不能取消</p>";
				var cancel="<div class='btn_con'><div class='btn_layer'>取消会议</div><p><span class='but_ok'>确定</span><span class='but_cancel'>取消</span></p></div>";
				var markNest = "<div style='height:0.2rem;background: transparent;'></div>";
				var end="<div class='clear'></div></div>";
				var clear = "<div class='clear'></div>";
				var attenStr=attenBodyHead+"与会人"+attenBodyEnd+attenNameHead;
				var faqiAttenStr=attenBodyHead+"发起人"+attenBodyEnd+attenNameHead;
				var attenssBody = "";
				for (var i = 0; i < dataList.length; i++) {
					if(dataList[i].type=='与会人'){
						var attenPerson = dataList[i].employeeName;
						if(attenPerson == null){
							attenPerson = "";
						}
						attenssBody += spanLabelBeg+attenPerson+"  "+spanLabelEnd;
					}
				}
				
				for (var i = 0; i < dataList.length; i++) {
					if(dataList[i].type=='发起人'){
						faqiAttenStr += attenNameHead+dataList[i].employeeName + attenNameEnd ;
					}
				}
				appendStr=title+boardBody+attenStr+attenssBody+attenNameEnd+clear + "</li>" + faqiAttenStr +"</div>"+clear+attenEnd+strEnd
				if(meetStatus=='已结束'){
					appendStr+=mark+markNest;
				}
				if(meetStatus=='会议中'||meetStatus=='未开始'){
					for (var i = 0; i < dataList.length; i++) {
						if(dataList[i].type=='发起人'&&dataList[i].employeeId==dataList[0].myEmpId){
							appendStr+=cancel+markNest;
						}
					}
				}
				appendStr+=end;
				console.info("append"+appendStr);
				$(".con").append(appendStr);
				$(".model").show();
				$(".btn_layer").on("click",function(e){//取消会议
					e.stopPropagation();
				//	$(".btn_layer").hide();
					$('.btn_con').css('-webkit-transform','translateX(-50%)');
					cancelMeet(srId);
				});
				$(".close").on('click',function(e){
					e.stopPropagation();
					$(".model").css('display','none');
					$(".con").empty();
				});
			}
		}
	});
}
function cancelMeet(srId){
//	e.stopPropagation();
//	$('.btn_con').css('transform','translateX(-50%)');
	$(".but_ok").on("click",function(e){
		 $.ajax({
		        type:"GET",
		        url:urlObj.delScheduleBySrId,
		        data:{
		        	srId : srId
		        },
		        dataType : "json",
				contentType : 'application/json',
		        success:function(data){
		        	if(data.msg == 'loginError'){
						loginauthorizefailed();return;
					}
		        	location.reload();
		        },
		        error : function(err){
		        	console.log(err);
		        }
			})
	});
	$(".but_cancel").on("click",function(e){
		e.stopPropagation();
		$(".model").css('display','none');
		$(".con").empty();
	});
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

