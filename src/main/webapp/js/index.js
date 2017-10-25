/**
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月10日 下午2:40:49
 */
var OA_URL = 'http://oa1.xingyuanauto.com';
var SUB_URL_MAP = {};
var ALL_EMPLOYEE = [];
var ERROR_MSG = "你的登录信息可能失效，请尝试重新登录后再操作";
var INSTER_OK = "添加成功";
var UPDATE_OK = "更新成功";
var DELETE_OK = "删除成功";
// 加载
$(function(){
    // 菜单图表效果
    $('.box_left .menu li').hover(function(){
        $(this).toggleClass('hover');
    });
    // 下拉框效果
    $('.content').on('click','.type-select',function(e){
        e.stopPropagation();
        $('.type-select ul').hide();
        $(this).find('ul').show();
    });
    $('.content').on('mouseenter','.type-select ul li',function(e){
        e.stopPropagation();
        $(this).addClass('on');
    }).on('mouseleave','.type-select ul li',function(e){
        e.stopPropagation();
        $(this).removeClass('on');
    });
    $('.content').on('click','.type-select ul li',function(e){
        e.stopPropagation();
        $(this).parent('ul').siblings('input').val($(this).text());
        $(this).parent('ul').siblings('input').attr("key",$(this).attr("data-value"));
        $(this).parent('ul').hide();
    });
    $(document).on('click',function(){
        $('.type-select ul').hide();
    });
    // 操作效果
    $('.content').on('mouseenter','.subject-table .drop-down li',function(e){
        e.stopPropagation();
        $(this).addClass('li-hover');
    }).on('mouseleave','.subject-table .drop-down li',function(e){
        e.stopPropagation();
        $(this).removeClass('li-hover');
    });

    // 请求登录信息
    $.get("getLoginInfo", function(rec){
        if(rec.code == 0){
            var data = rec.data;
            // 用户头像与名称
            $("#user_info").html('<a href="javascript:void(0)"><span class="portrait"><img src="' + 
                OA_URL + 
                data.icon + '"></span>' + 
                data.employeeName + '</a>');
            // 菜单权限
            $.each(data.roleList,function(i,item){
                SUB_URL_MAP[item.moduleId] = item.url;
                if(i == 0){
                    $("#" + item.moduleId).attr("class","current");
                    skipPages(item.moduleId);
                }
    　　　　    $("#" + item.moduleId).show();
            });

            // 缓存所有员工信息
            $.get("oa/queryAllEmployee", function(rec){
                if(rec.code == 0){
                    ALL_EMPLOYEE = rec.data;
                }else{
                    if(null == rec.msg || undefined == rec.msg){
                        layer.msg(ERROR_MSG);
                    }else
                        layer.msg(rec.msg);
                }
            });
        }else{
            if(null == rec.msg || undefined == rec.msg){
                window.location.href=OA_URL;
            }else
                layer.msg(rec.msg);
        }
    });
});

//菜单加载
function skipPages(id) {
    $('#' + id).addClass("current").siblings().removeClass("current");
    switch (id)
    {
        case "SYS023MOD0001":
            $(".box_right .content").load('template/' + SUB_URL_MAP['SYS023MOD0001'] + '.html?r='+Math.random(),function(){
                $(".box_right .content").append('<script language="javascript" src="js/' + SUB_URL_MAP['SYS023MOD0001'] + '.js" type="text/javascript"></script>');
            });
            break;
        case "SYS023MOD0002":
            $(".box_right .content").load('template/' + SUB_URL_MAP['SYS023MOD0002'] + '.html?r='+Math.random(),function(){
                $(".box_right .content").append('<script language="javascript" src="js/' + SUB_URL_MAP['SYS023MOD0002'] + '.js" type="text/javascript"></script>');
            });
            break;
        case "SYS023MOD0003":
            $(".box_right .content").load('template/' + SUB_URL_MAP['SYS023MOD0003'] + '.html?r'+Math.random(),function(){
                $(".box_right .content").append('<script language="javascript" src="js/' + SUB_URL_MAP['SYS023MOD0003'] + '.js" type="text/javascript"></script>');
            });
            break;
    }
}


function fuzzyMatching(){
    var key = $('#emp_input').val();
    if(key != ''){
        var reg = new RegExp(key,'gi');
        var str = '';
        $.each(ALL_EMPLOYEE,function(i,item){
            if(reg.test(item.CnName)){
                str += '<li>' + item.CnName + '</li>';
            }
        })
        $("#emp_list").html(str);
    }else{
        $("#emp_list").empty();
    }
}

function dateConvert(timestamp,format){
    var time = new Date(timestamp);
    var y = time.getFullYear();//年
    var m = time.getMonth() + 1;//月
    m = m < 10 ? '0'+m : m;
    var d = time.getDate();//日
    d = d < 10 ? '0'+d : d;
    var h = time.getHours();//时
    h = h < 10 ? '0'+h : h;
    var mm = time.getMinutes();//分
    mm = mm < 10 ? '0'+mm : mm;
    var s = time.getSeconds();//秒
    s = s < 10 ? '0'+s : s;
    switch (format) {
        case "yyyyMMddHHmm":
            return y+"-"+m+"-"+d+" "+h+":"+mm;
            break;
        case "yyyyMMddHHmmss":
            return y+"-"+m+"-"+d+" "+h+":"+mm+":"+s;
            break;
        default:
            return "undefined time";
            break;
    }
    
}
