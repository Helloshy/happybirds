
var dataOptions = {
	baseUrl:"",
	basePath:"",
	addInit:function(){},// 添加弹窗初始化
	editInit:function(data){},// 修改数据加载完成初始化
	saveInit:function(data){}// 保存前执行操作
};

/**保存数据处理*/
var save = function(){
	if(!$("#add-data-form").form('validate'))return;
	var url = dataOptions.baseUrl+'save.htm';/* 配置保存数据地址 */
	var check = function(){};
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			$('#add-dialog').dialog('close');
			mygrid.datagrid('reload');
			$('#add-data-form').form('clear');
		}else $.messager.alert("错误",ret.msg,"error");
	}
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	formSubmit($('#add-data-form'),url,check,success);
}

//详情
var detail = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	$("#itemName1").text(rowData.itemName);
	$("#path1").text(rowData.path);
	$("#remark1").text(rowData.remark);
	$("#createTime1").text(rowData.createTime);
	$('#add-detail').dialog('open');
}
