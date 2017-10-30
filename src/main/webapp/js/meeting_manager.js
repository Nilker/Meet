/**
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月10日 下午2:40:49
 */
var MM_LIST_ID = [];
var PAGE_SIZE_INIT = 10;//初始化条数
var PAGE_SIZE_TEMP = PAGE_SIZE_INIT;//当前显示条数
var STEP_VAlUE = 10;//分页步进值
var TOTAL = 0;//查询总量
var OFFICE_INFO_LIST = [];
var OFFICE_INFO_MAP = {};
$(function(){
    dropDdownList();
});

// 办公区下拉框列表
function dropDdownList(){
    $.ajax({
        type: "get",
        url: "getOfficeInfoByRole",
        dataType: "json",
        cache: false,
        async: false,
        success:function(rec){
            if(rec.code == 0){
                OFFICE_INFO_LIST = rec.data;
                OFFICE_INFO_MAP = {};
                $('#office_list').empty();
                $('#office_list').append('<li data-value="-2">全部</li>');
                $.each(rec.data,function(i,item){
                    OFFICE_INFO_MAP[item.officeId] = item.officeName;
                    $('#office_list').append('<li data-value="'+ item.officeId +'">'+ item.officeName +'</li>');
                });
                $('#office_input').val("全部");
                $('#office_input').attr("key",-2);
                $('#status_input').val("全部");
                $('#status_input').attr("key",-2);
                select(true);
            }else{
                if(null == rec.msg || undefined == rec.msg)
                    layer.msg(ERROR_MSG);
                else
                    layer.msg(rec.msg);
            }
        }
    });
}

function select(pageInti){
	if(pageInti)
		PAGE_SIZE_TEMP = PAGE_SIZE_INIT;

	var officeId = $('#office_input').attr("key");
	var status = $('#status_input').attr("key");
	var parameter = {};
    parameter.pageNo = 0;
    // 当上次结果小于初始条数的时候修正查询条件
	parameter.pageSize = PAGE_SIZE_TEMP < PAGE_SIZE_INIT ? PAGE_SIZE_TEMP = PAGE_SIZE_INIT : PAGE_SIZE_TEMP;
	parameter.officeId = officeId;
	parameter.status = status;

	$.get("mm/select",parameter, function(rec){ 
        if(rec.code == 0){
            MM_LIST_ID = [];
            $('#mm_list').empty();
            $('#mm_list').append('<tr>'+
                                    '<th width="20%" style="padding-left: 30px;">会议室名称</th>'+
                                    '<th width="13%">所在楼层</th>'+
                                    '<th width="13%">容纳人数</th>'+
                                    '<th width="20%">办公区</th>'+
                                    '<th width="20%">设备</th>'+
                                    '<th width="7%">状态</th>'+
                                    '<th width="7%">操作</th>'+
                                '</tr>');
            $.each(rec.data.data,function(i,item){
                MM_LIST_ID.push(item.biId);
                var tempEquipment = equipmentConvert(item.equipment);
                $('#mm_list').append('<tr>'+
                    '<td style="padding-left: 30px;">'+ item.biName +'</td>'+
                    '<td class="td_con">'+ item.biFloor +'</td>'+
                    '<td>'+ item.biCapacity +'</td>'+
                    '<td>'+ OFFICE_INFO_MAP[item.officeId] +'</td>'+
                    '<td class="td_con" title="'+ tempEquipment +'">'+ tempEquipment +'</td>'+
                    '<td>'+ statusConvert(item.status) +'</td>'+
                    '<td class="pr" onmouseover="tdIn('+ "'" + item.biId + "'" +')" onmouseout="tdOut('+ "'" + item.biId + "'" +')">'+
                        '<div class="type-ellipse" >&nbsp;</div>'+
                        '<ul id="'+ item.biId +'" class="drop-down" style="display: none;" >'+
                            '<li onclick="openLayer('+ "'" +'update'+ "'" +','+ "'" + item.biId + "'" +')">编辑</li>'+
                            '<li onclick="statusById('+ "'" + item.biId + "'" +',' + item.status + ')">'+ statusConvert(item.status == 0 ? 1 : 0) +'</li>'+
                            (item.status == 0 ?'<li onclick="deleteById(' + "'" + item.biId + "'" + ')">删除</li>':'') +
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

function deleteById(biId){
	$.get("mm/delete",{ "biId": biId }, function(rec){
        if(rec.code == 0){
            layer.msg(DELETE_OK);
            select(false);
        }else{
            if(null == rec.msg || undefined == rec.msg)
                layer.msg(ERROR_MSG);
            else
                layer.msg(rec.msg);
        }
    });
}

function statusById(biId, status){
    $.get("mm/status",{ "biId": biId ,"status": status }, function(rec){
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
function tdIn(biId){
	$.each(MM_LIST_ID,function(i,item){
		$('#' + item).hide();
	});
	$('#' + biId).show();
}
// 隐藏操作弹层
function tdOut(biId){
	$('#' + biId).hide();
}

function openLayer(flag,biId){
   
    // 判断办公区列表是否加载完毕
    if(0 == OFFICE_INFO_LIST.length)
        dropDdownList();
    // 获取设备元素
    var checkboxList = $(".fr input:checkbox");

    if("insert" == flag){
        $.each(checkboxList,function (i,item) {
            $(item).prop("checked",false);
        });

        $("#layer_title").empty();
        $("#layer_title").html('添加会议室'+
            '<div class="close" onclick="closeLayer()">&nbsp;</div>'+
            '<div class="clear"></div>');

        $('#layer_office_list').empty();
        $.each(OFFICE_INFO_LIST,function(i,item){
            $('#layer_office_list').append('<li style="width: 296px; margin:0;" data-value="'+ item.officeId +'">'+ item.officeName +'</li>');
        });

        $("#submit").unbind("click");
        $("#submit").click(function(event) {
            
            var equipment ='';
            $.each(checkboxList,function (i,item) {
                if($(item).prop("checked") == true){
                    equipment += 1;
                }else{
                    equipment += 0;
                }
            });

            var parameter = {};
            parameter.officeId = $("#layer_office_input").attr("key");
            parameter.biName = $("#layer_bi_name").val();
            parameter.biFloor = $("#layer_bi_floor").val();
            parameter.biCapacity = $("#layer_bi_capacity").val();
            parameter.equipment = parseInt(equipment,2);

            if(undefined == parameter.officeId || '' == parameter.officeId){
                // layer.msg("请选择一个办公区");
                layer.tips("请选择一个办公区", '#layer_office_input',{
                    tips: [2, '#3399ff']
                });
                return;
            }
            if('' == parameter.biName){
                // layer.msg("请输入会议室名称");
                layer.tips("请输入会议室名称", '#layer_bi_name',{
                    tips: [2, '#3399ff']
                });
                return;
            }
            if('' == parameter.biFloor){
                // layer.msg("请输入会议室所在楼层");
                layer.tips("请输入会议室所在楼层", '#layer_assembly_floor',{
                    tips: [2, '#3399ff']
                });
                return;
            }
            if('' == parameter.biCapacity || 0 == parameter.biCapacity){
                // layer.msg("请输入人数");
                layer.tips("请输入人数", '#layer_assembly_capacity',{
                    tips: [2, '#3399ff']
                });
                return;
            }
            
            $.get("mm/insert", parameter, function(rec){
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
        $("#layer_title").empty();
        $("#layer_title").html('编辑会议室'+
        '<div class="close" onclick="closeLayer()">&nbsp;</div>'+
        '<div class="clear"></div>');
        // 编辑情况readonly删除下拉框样式
        $("#layer_remove").remove();
        $("#layer_office_list").remove();
        // $('#layer_office_list').empty();
        // $.each(OFFICE_INFO_LIST,function(i,item){
        //     $('#layer_office_list').append('<li style="width: 296px; margin:0;" data-value="'+ item.officeId +'">'+ item.officeName +'</li>');
        // });

        $.get("mm/one",{ "biId": biId }, function(rec){
            if(rec.code == 0){
                var bi = rec.data;

                // $("#layer_office_input").attr("key",bi.officeId);
                $("#layer_office_input").val(OFFICE_INFO_MAP[bi.officeId]);
                $("#layer_bi_name").val(bi.biName);
                $("#layer_bi_floor").val(bi.biFloor);
                $("#layer_bi_capacity").val(bi.biCapacity);

                if(0 != bi.equipment){
                    var binaryList = decimalToBinaryArray(bi.equipment);
                    if(binaryList.length != checkboxList.length)
                        layer.msg("发生错误，设备列表数量异常");
                    $.each(checkboxList,function (i,item) {
                        if(binaryList[i] == 0)
                            $(item).prop("checked",false);
                        else
                            $(item).prop("checked",true);
                    })
                }

                $("#submit").unbind("click");
                $("#submit").click(function(event) {
                    
                    var equipment ='';
                    $.each(checkboxList,function (i,item) {
                        if($(item).prop("checked") == true){
                            equipment += 1;
                        }else{
                            equipment += 0;
                        }
                    });
        
                    var parameter = {};
                    parameter.biId = biId;
                    parameter.officeId = $("#layer_office_input").attr("key");
                    parameter.biName = $("#layer_bi_name").val();
                    parameter.biFloor = $("#layer_bi_floor").val();
                    parameter.biCapacity = $("#layer_bi_capacity").val();
                    parameter.equipment = parseInt(equipment,2);
        
                    // if(undefined == parameter.officeId || '' == parameter.officeId){
                    //     // layer.msg("请选择一个办公区");
                    //     layer.tips("请选择一个办公区", '#layer_office_input',{
                    //         tips: [2, '#3399ff']
                    //     });
                    //     return;
                    // }
                    if('' == parameter.biName){
                        // layer.msg("请输入会议室名称");
                        layer.tips("请输入会议室名称", '#layer_bi_name',{
                            tips: [2, '#3399ff']
                        });
                        return;
                    }
                    if('' == parameter.biFloor){
                        // layer.msg("请输入会议室所在楼层");
                        layer.tips("请输入会议室所在楼层", '#layer_assembly_floor',{
                            tips: [2, '#3399ff']
                        });
                        return;
                    }
                    if('' == parameter.biCapacity || 0 == parameter.biCapacity){
                        // layer.msg("请输入人数");
                        layer.tips("请输入人数", '#layer_assembly_capacity',{
                            tips: [2, '#3399ff']
                        });
                        return;
                    }
                    
                    $.get("mm/update", parameter, function(rec){
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
     // 清空上次数据
     $("#layer_office_input").attr("key","");
     $("#layer_office_input").val("");
     $("#layer_bi_name").val("");
     $("#layer_bi_floor").val("");
     $("#layer_bi_capacity").val("");
}

function decimalToBinaryArray(decimal){
    return (Array(4).join('0') + decimal.toString(2)).slice(-4).split("");
}

function equipmentConvert(equipment){
    var resutl = '';
    var EQUIPMENT_CONSTANTS = ["投影仪","投影幕","电视","八爪鱼"];
    var equipmentArray = decimalToBinaryArray(equipment);
    if(4 == EQUIPMENT_CONSTANTS.length && 4 == equipmentArray.length){
        for(var i=0;i<4;i++)
            if(1 == equipmentArray[i]){
                if(resutl != '')
                    resutl += '、';
                resutl += EQUIPMENT_CONSTANTS[i];
            }
        return resutl;
    }else{
        return resutl;
    }
}

function statusConvert(status){
    var resutl = '';
    switch (status) {
        case 0:
            resutl = "停用";
            break;
        case 1:
            resutl = "启用";
            break;
        default:
            resutl = "未知";
            break;
    }
    return resutl;
}