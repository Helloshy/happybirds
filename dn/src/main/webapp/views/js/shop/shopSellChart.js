var dataOptions = {
	baseUrl:"",
	basePath:"",
	recordType:"",
	tagType:"",
	addInit:function(){},// 添加弹窗初始化
	editInit:function(data){},// 修改数据加载完成初始化
	saveInit:function(data){}// 保存前执行操作
};


var exportShopSellExcel =function () {
	//拿到搜索条件
	var itemRemark = $('#itemRemarkQuery').val();
	var startTime = $('#startTimeQuery').val();
	var endTime = $('#endTimeQuery').val();
	if(startTime&&endTime&&startTime>endTime){
		$.messager.alert('提示','开始时间不能大于结束时间');
		return;
	}
    window.location.href = url+'exportExcel.htm?itemRemark='+itemRemark+'&startTime='+startTime+'&endTime='+endTime;
}


