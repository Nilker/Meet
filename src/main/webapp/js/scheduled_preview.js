/**
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月10日 下午2:40:49
 */
var PAGE_SIZE_INIT = 10;//初始化条数
var PAGE_SIZE_TEMP = PAGE_SIZE_INIT;//当前显示条数
var STEP_VAlUE = 10;//分页步进值
var TOTAL = 0;//查询总量
var OFFICE_INFO_LIST = [];
var OFFICE_INFO_MAP = {};
var MEETING_INFO_MAP = {};
$(function(){
    dropDdownList1();
});

// 办公区下拉框列表
function dropDdownList1(){
    $.get("getOfficeInfoByRole",function(rec){
        if(rec.code == 0){
            OFFICE_INFO_LIST = rec.data;
            OFFICE_INFO_MAP = {};
            $('#office_list').empty();
            $('#office_list').append('<li data-value="-2" onclick="dropDdownList2(-2)">全部</li>');
            $.each(rec.data,function(i,item){
                OFFICE_INFO_MAP[item.officeId] = item.officeName;
                $('#office_list').append('<li data-value="'+ item.officeId +'" onclick="dropDdownList2('+ item.officeId +')">'+ item.officeName +'</li>');
            });
            $('#office_input').val("全部");
            $('#office_input').attr("key",-2);
            $('#status_input').val("全部");
            $('#status_input').attr("key",-2);
            dropDdownList2(-2);            
        }else{
            if(null == rec.msg || undefined == rec.msg)
                layer.msg(ERROR_MSG);
            else
                layer.msg(rec.msg);
        }
    });
}
// 会议室下拉框列表
function dropDdownList2(oid){
    $.get("getMeetingByOfficeId", { "oid" : oid }, function(rec){
        if(rec.code == 0){
            MEETING_INFO_MAP = {};
            $('#meeting_list').empty();
            $('#meeting_list').append('<li data-value="-2">全部</li>');
            $.each(rec.data,function(i,item){
                MEETING_INFO_MAP[item.biId] = item.biName;
                $('#meeting_list').append('<li data-value="'+ item.biId +'">'+ item.biName +'</li>');
            });
            select(true);
            $('#meeting_input').val("全部");
            $('#meeting_input').attr("key",-2);
        }else{
            if(null == rec.msg || undefined == rec.msg)
                layer.msg(ERROR_MSG);
            else
                layer.msg(rec.msg);
        }
    });
}

function select(pageInti){ 
	if(pageInti)
		PAGE_SIZE_TEMP = PAGE_SIZE_INIT;

    var officeId = $('#office_input').attr("key");
    var biId = $('#meeting_input').attr("key");
    var conferenceStatus = $('#status_input').attr("key");
    var employeeName = $('#emp_input').val();
    
	var parameter = {};
    parameter.pageNo = 0;
    // 当上次结果小于初始条数的时候修正查询条件
	parameter.pageSize = PAGE_SIZE_TEMP < PAGE_SIZE_INIT ? PAGE_SIZE_TEMP = PAGE_SIZE_INIT : PAGE_SIZE_TEMP;
    parameter.officeId = officeId;
    parameter.biId = biId;
    parameter.conferenceStatus = conferenceStatus;
    parameter.employeeName = employeeName;

	$.get("sp/select",parameter, function(rec){ 
        if(rec.code == 0){
            $('#sp_list').empty();
            $('#sp_list').append('<tr>'+
                                    '<th width="12%" style="padding-left: 30px;">会议室</th>'+
                                    '<th width="18%">预定时间</th>'+
                                    '<th width="14%">会议主题</th>'+
                                    '<th width="8%">预订者</th>'+
                                    '<th width="15%">预订者部门</th>'+
                                    '<th width="10%">联系电话</th>'+
                                    '<th width="15%">办公区</th>'+
                                    '<th width="8%">会议状态</th>'+
                                '</tr>');
            $.each(rec.data.data,function(i,item){
                $('#sp_list').append('<tr>'+
                    '<td class="td_con" style="padding-left: 30px;">'+ MEETING_INFO_MAP[item.biId] +'</td>'+
                    '<td>'+ dateConvert(item.startTime,'yyyyMMddHHmm') +'</td>'+
                    '<td class="td_con" title="'+ item.meetingTheme +'">'+ item.meetingTheme +'</td>'+
                    '<td>'+ item.employeeName +'</td>'+
                    '<td>'+ item.departmentName +'</td>'+
                    '<td>'+ item.phone +'</td>'+
                    '<td>'+ OFFICE_INFO_MAP[item.officeId] +'</td>'+
                    statusConvert(item.startTime,item.endTime)+
                '</tr>');
            });
            // 当查询结果小于期望条数时 修正实际查询结果
            
            TOTAL = rec.data.total;
            if(PAGE_SIZE_TEMP >= TOTAL){
                PAGE_SIZE_TEMP = TOTAL;
                $("#load_more").hide();
            }else{
                $("#load_more").show();
            }
            $("#num").text(PAGE_SIZE_TEMP);
            $("#count_num").text(TOTAL);
        }else{
            if(null == rec.msg || undefined == rec.msg)
                layer.msg(ERROR_MSG);
            else
                layer.msg(rec.msg);
        }
    });
}

function load(){
	if(PAGE_SIZE_TEMP == TOTAL)
		return;
	PAGE_SIZE_TEMP += STEP_VAlUE;
	select(false);
}

function statusConvert(startTime, endTime){
    var now = new Date().getTime();
    if(now < startTime){
        return '<td class="color01">未开始</td>';
    }
    if(now > endTime){
        return '<td class="color02">已结束</td>';
    }
    return '<td class="color05">进行中</td>';
}
