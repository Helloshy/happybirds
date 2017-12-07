var dataOptions = {
	baseUrl:"",
	basePath:"",
	uid:"",
	addInit:function(){},// 添加弹窗初始化
	editInit:function(data){},// 修改数据加载完成初始化
	saveInit:function(data){}// 保存前执行操作
};

//保存
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
			$.messager.show({
				title:'提示',
				msg:'保存成功',
				timeout:5000,
				showType:'slide'
			});
		}else {
			parent.$.messager.alert("错误",ret.msg,"error");
		}
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	formSubmit($('#add-data-form'),url,check,success);
};

/**添加*/
function gridAdd(){
	clearTextBox();
	$('#add-data-form').form('clear');// 清除form表单数据
	$('#uid').val(dataOptions.uid);
	$('#couponName').html('');
	$('#couponContent').html('');
	$( "#add-dialog" ).dialog("open").dialog('setTitle','添加');
};

//修改
var gridEdit = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.baseUrl + 'data.htm';/* 配置查询要修改的数据查询地址 */
	var data = {id:rowData.id}; /* 请求的参数内容 */
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){

			$('#add-data-form').form('clear');// 清除form表单数据
			// 初始化
			myLoad("#add-data-form",ret.data.data)
			/*$('#couponName').text('');
			$('#couponContent').text('');
			$('#courseId').combobox('setValues',ret.data.data.courseId+"");
			$('#id').val(ret.data.data.id);
			$('#uid').val(dataOptions.uid);
			$('#dateFrom').datebox('setValue',ret.data.data.dateFrom);
			$('#dateTo').datebox('setValue',ret.data.data.dateTo);*/
			$('#add-dialog').dialog('setTitle','修改');
			$('#add-dialog').dialog('open');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);
};

/** 导出 */
var exportExcel = function(uid){
	window.location.href = dataOptions.baseUrl+'exportExcel.htm?uid='+uid;
} ;
