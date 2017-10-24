// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function (fmt) { 
    var o = {
        "M+": this.getMonth() + 1, // 月份
        "d+": this.getDate(), // 日
        "h+": this.getHours(), // 小时
        "m+": this.getMinutes(), // 分
        "s+": this.getSeconds(), // 秒
        "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
        "S": this.getMilliseconds() // 毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

Array.prototype.indexOf=function(v)
{
    	for(var i=0, n=this.length; i<n; i++)
        {
            if(this[i]==v) return i;
        }
    return -1;
};
var XY;
XY = $.extend({}, XY);
$(function () {
    /***************************************************************************
	 * 日期比较
	 * 
	 * @param str_date1
	 * @param str_date2
	 * @returns {boolean}
	 */
    XY.dateCompare = function (str_date1, str_date2) {
        return str_date1 !== "" && str_date2 !== "" && new Date(str_date1.replace(/-/g, "\/")) < new Date(str_date2.replace(/-/g, "\/"));
    };
    XY.getDay=function (date){
    	var week;
    	if(date.getDay()==0) week="周日"
    	if(date.getDay()==1) week="周一"
    	if(date.getDay()==2) week="周二"
    	if(date.getDay()==3) week="周三"
    	if(date.getDay()==4) week="周四"
    	if(date.getDay()==5) week="周五"
    	if(date.getDay()==6) week="周六"
    	return week;
    }
    XY.searchSameIdx =function (origArr, newArr) {
    	var resultArr='';
    	var newArr2=[];
    	var newArr3=[];
    	if (newArr == null || newArr.length == 0) {
    		return resultArr;
    	}
    	for (var i = 0; i < newArr.length; i++) {
    		newArr2=newArr[i].split("-");
    		for (var j = 0; j < newArr2.length; j++) {
    			resultArr +=','+schedTimeArr.indexOf(newArr2[j]);
    		}
    		resultArr=resultArr.substring(1);
    		newArr3[i]=resultArr;
    		resultArr='';
    	}
    	return newArr3;
    }
    //十进制转换二进制
    XY.dec2bin = function (num){
        if(isNaN(num))return;
        return (Array(4).join(0) + parseInt(num,10).toString(2)).slice(-4);
    }
});