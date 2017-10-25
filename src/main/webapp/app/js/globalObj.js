schedTimeArr = [ "09:00", "09:30", "10:00", "10:30",
			"11:00","11:30", "12:00","12:30", "13:00", "13:30", "14:00",
			"14:30", "15:00", "15:30", "16:00", "16:30",
			"17:00", "17:30", "18:00" , "18:30"];
equipmentArr = [ "投影仪", "投影幕" , "电视" ,"八爪鱼" ] ;
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
