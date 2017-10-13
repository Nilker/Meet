$(function(){
    $('.box_left .menu li').hover(function(){
        $(this).toggleClass('hover');
    });
});
//页面跳转
function skipPages(obj) {
    var index = $(obj).index();
    $(obj).addClass("current").siblings().removeClass("current");
    switch (index)
    {
        case 0:
            $(".box_right .content").load('template/meeting_manager.html?r='+Math.random(),function(){
            });
            break;
        case 1:
            $(".box_right .content").load('template/authorize_manager.html?r='+Math.random(),function(){
                // $(".box_right .content").append('<script language="javascript" src="../js/documents.js" type="text/javascript"></script>');
            });
            break;
        case 2:
            $(".box_right .content").load('template/scheduled_preview.html?r'+Math.random(),function(){
                // $(".box_right .content").append('<script language="javascript" src="../js/pendings.js" type="text/javascript"></script>');
            });
            break;
    }
}