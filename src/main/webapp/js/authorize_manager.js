/**
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月10日 下午2:40:49
 */
var AM_LIST_ID = [];
var PAGE_SIZE_INIT = 10;//初始化条数
var PAGE_SIZE_TEMP = PAGE_SIZE_INIT;//当前显示条数
var STEP_VAlUE = 10;//分页步进值
var TOTAL = 0;//查询总量
var OFFICE_INFO_LIST = [];
//
$(function(){
    dropDdownList();
});

// 办公区下拉框列表
function dropDdownList(){
    $.get("getOfficeInfoAll",function(rec){
        if(rec.code == 0){
            OFFICE_INFO_LIST = rec.data;
            $('#office_list').empty();
            $('#office_list').append('<li data-value="-2">全部</li>');
            $.each(rec.data,function(i,item){
                $('#office_list').append('<li data-value="'+ item.officeId +'">'+ item.officeName +'</li>');
            });
            $('#office_input').val("全部");
            $('#office_input').attr("key",-2);
            select(true);
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
	var employeeName = $('#emp_input').val();
	var parameter = {};
    parameter.pageNo = 0;
    // 当上次结果小于初始条数的时候修正查询条件
	parameter.pageSize = PAGE_SIZE_TEMP < PAGE_SIZE_INIT ? PAGE_SIZE_TEMP = PAGE_SIZE_INIT : PAGE_SIZE_TEMP;
	parameter.officeId = officeId;
	parameter.employeeName = employeeName;

	$.get("am/select",parameter, function(rec){ 
        if(rec.code == 0){
            AM_LIST_ID = [];
            $('#am_list').empty();
            $('#am_list').append('<tr>'+
                                    '<th width="15%" style="padding-left: 30px;">员工姓名</th>'+
                                    '<th width="25%">所属部门</th>'+
                                    '<th width="35%">授权办公区</th>'+
                                    '<th width="10%">添加日期</th>'+
                                    '<th width="10%">创建人</th>'+
                                    '<th width="5%">操作</th>'+
                                '</tr>');
            $.each(rec.data.data,function(i,item){
                AM_LIST_ID.push(item.oaaId);
                $('#am_list').append('<tr>'+
                    '<td style="padding-left: 30px;">'+ item.employeeName +'</td>'+
                    '<td>'+ item.departmentName +'</td>'+
                    '<td class="td_con" title="'+ item.officeIds +'">'+ item.officeIds +'</td>'+
                    '<td class="td_con">'+ dateConvert(item.adddate,'yyyyMMddHHmmss') +'</td>'+
                    '<td>'+ item.founderName +'</td>'+
                    '<td class="pr" onmouseover="tdIn('+ "'" + item.oaaId + "'" +')" onmouseout="tdOut('+ "'" + item.oaaId + "'" +')">'+
                        '<div class="type-ellipse" >&nbsp;</div>'+
                        '<ul id="'+ item.oaaId +'" class="drop-down" style="display: none;" >'+
                            '<li onclick="openLayer('+ "'" +'update'+ "'" +','+ "'" + item.oaaId + "'" +')">编辑</li>'+
                            '<li onclick="deleteById('+ "'" + item.oaaId + "'" +')">删除</li>'+
                        '</ul>'+
                    '</td>'+
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

function deleteById(oaaId){
	$.get("am/delete",{ "oaaId": oaaId }, function(rec){
        if(rec.code == 0){
            layer.msg(UPDATE_OK);
            select(false);
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
// 显示操作弹层
function tdIn(oaaId){
	$.each(AM_LIST_ID,function(i,item){
		$('#' + item).hide();
	});
	$('#' + oaaId).show();
}
// 隐藏操作弹层
function tdOut(oaaId){
	$('#' + oaaId).hide();
}

function openLayer(flag,oaaId){
    // 判断办公区列表是否加载完毕
    if(0 == OFFICE_INFO_LIST.length)
        dropDdownList();

    var offcieHTML = '';
    $.each(OFFICE_INFO_LIST,function(index, el) {
        // offcieHTML += '<div class="check_con">';
        // offcieHTML += '<input class="magic-checkbox" type="checkbox" name="layout" id="'+ el.officeId +'">';
        // offcieHTML += '<label for="'+ el.officeId +'"><span>'+ el.officeName +'</span></label></div>';
        offcieHTML += '<li><div class="check_con">';
        offcieHTML += '<input id="'+ el.officeId +'" class="magic-checkbox" type="checkbox"><label for="'+ el.officeId +'" title="'+ el.officeName +'">'+ el.officeName +'</label>';
        offcieHTML += '</div></li>';
    });
    offcieHTML += '<div class="clear"></div>';
    if("insert" == flag){
        $("#layer_title").empty();
        $("#layer_title").html('添加权限'+
            '<div class="close" onclick="closeLayer()">&nbsp;</div>'+
            '<div class="clear"></div>');
        $("#layer_office_list").html(offcieHTML);

        $("#layer_emp_num").val('');
        $('#layer_emp_num').attr("readonly",false);

        $("#submit").unbind("click");
        $("#submit").click(function(event) {
            
            var checkedList = $(".areaList input:checked");
            var tempId ='';
            $.each(checkedList,function (i,item) {
                if(i != 0)
                    tempId += ',';
                tempId += item.id;
            })

            var parameter = {};
            parameter.employeeId = $("#layer_emp_num").val();
            parameter.officeIds = tempId;

            if('' == parameter.employeeId){
                // layer.msg("请输入员工编号");
                layer.tips("请输入员工编号", '#layer_emp_num',{
                    tips: [2, '#3399ff']
                });
                return;
            }

            if(parameter.employeeId.length < 4){
                // layer.msg("员工编号格式不正确");
                layer.tips("员工编号格式不正确", '#layer_emp_num',{
                    tips: [2, '#3399ff']
                });
                return;
            }

            if('' == parameter.officeIds){
                // layer.msg("请至少选择一个授权办公区");
                layer.tips("请至少选择一个授权办公区", '#layer_office_list',{
                    tips: [2, '#3399ff']
                });
                return;
            }
            
            $.get("am/insert", parameter, function(rec){
                if(rec.code == 0){
                    closeLayer();
                    layer.msg(INSTER_OK);
                    select(false);
                }else{
                    if(null == rec.msg || undefined == rec.msg)
                        layer.msg(ERROR_MSG);
                    else
                        layer.msg(rec.msg);
                }
            });
        });
    }else if("update" == flag){
        $.get("am/one",{ "oaaId": oaaId }, function(rec){
            if(rec.code == 0){
                var oaa = rec.data;
                $("#layer_title").empty();
                $("#layer_title").html('编辑权限'+
                    '<div class="close" onclick="closeLayer()">&nbsp;</div>'+
                    '<div class="clear"></div>');
                $("#layer_office_list").html(offcieHTML);

                $("#layer_emp_num").val(oaa.employeeId);
                $('#layer_emp_num').attr("readonly",true);

                var oidList = oaa.officeIds.split(",");
                $.each(oidList,function (i,item) {
                    $("#" + item).prop("checked",true);
                })
                
                $("#submit").unbind("click");
                $("#submit").click(function(event) {
                    
                    var checkedList = $(".areaList input:checked");
                    var tempId ='';
                    $.each(checkedList,function (i,item) {
                        if(i != 0)
                            tempId += ',';
                        tempId += item.id;
                    })
        
                    var parameter = {};
                    parameter.oaaId = oaaId;
                    parameter.officeIds = tempId;

                    if('' == parameter.officeIds){
                        // layer.msg("请至少选择一个授权办公区");
                        layer.tips("请至少选择一个授权办公区", '#layer_office_list',{
                            tips: [2, '#3399ff']
                        });
                        return;
                    }
                    
                    $.get("am/update", parameter, function(rec){
                        if(rec.code == 0){
                            closeLayer();
                            layer.msg(UPDATE_OK);
                            select(false);
                        }else{
                            if(null == rec.msg || undefined == rec.msg)
                                layer.msg(ERROR_MSG);
                            else
                                layer.msg(rec.msg);
                        }
                    });
                });
            }else{
                if(null == rec.msg || undefined == rec.msg)
                    layer.msg(ERROR_MSG);
                else
                    layer.msg(rec.msg);
            }
        });
    }else{
        return;
    }
    $("#layer").fadeToggle();
}

function closeLayer(){
 	$("#layer").fadeToggle();
}