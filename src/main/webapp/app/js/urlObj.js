//var domain="http://x.xingyuanauto.com/app/scheduled/";
var domain="http://meet.oa.xingyuanauto.com/xyauto_meet/app/scheduled/";
urlPrefix="../app/images/";
var urlObj={
		//会议室区域列表
		scheduleOffice : domain + 'getInfoByOfficeId',
		//已预定会议室列表
		findInfoByBiId : domain + 'findInfoByBiId',
		//我的会议列表
		findAllMeetOfSelf : domain + 'findAllMeetOfSelf',
		//我的会议列表详情
		findSingleMeetBySrId : domain + 'findSingleMeetBySrId',
		//预定会议室回显
		findSingleInfoByBiId : domain + 'findSingleInfoByBiId',
		//预定
		insertSchedule : domain + 'insertSchedule',
		//删除预定
		delScheduleBySrId : domain + 'delScheduleBySrId',
		//会议室信息
		findBoardByPrimaryKey : domain + 'findBoardByPrimaryKey'
}