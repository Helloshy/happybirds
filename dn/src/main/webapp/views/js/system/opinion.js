
$(function(){
	$('#state').combobox({
		required:true,
		multiple:false,
		editable:false,
		panelHeight:80,
		valueField: 'label',
		textField: 'value',
		data:[{
				label: '',
				value: '全部'
			},{
				label: '1',
				value: '已回复'
			},{
				label: '0',
				value: '未回复'
			}]
	});
});

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
			myLoad("#add-data-form",ret.data.data);
			$("#userName").text(ret.data.data.userName);
			$("#nickName").text(ret.data.data.nickName);
			$("#content").val(ret.data.data.content);
			$("#createTime").text(ret.data.data.createTime);
			$("#state").text(ret.data.data.state == 0 ? '未回复' : '已回复');
			$('#add-dialog').dialog('setTitle','回复');
			$('#add-dialog').dialog('open');
			
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);	
};

/** 保存*/
var save = function(){
	if(!$("#add-data-form").form('validate'))return;
	var check = function(){};
	var url = dataOptions.baseUrl+'save.htm?state=1&recordType=2';/* 配置保存数据地址 */
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			$('#add-dialog').dialog('close');
			mygrid.datagrid('reload');
			$('#add-data-form').form('clear');
			$.messager.show({
				title:'提示',
				msg:'回复成功',
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

//详情
var info = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.baseUrl + 'data.htm';/* 配置查询要修改的数据查询地址 */
	var data = {id:rowData.id}; /* 请求的参数内容 */
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			$("#userName1").text(ret.data.data.userName);
			$("#nickName1").text(ret.data.data.nickName);
			$("#content1").text(ret.data.data.content);
			$("#createTime1").text(ret.data.data.createTime);
			$("#state1").text(ret.data.data.state == 0 ? '未回复' : '已回复');
			$("#feedback1").text(ret.data.data.feedback);
			$('#add-dialog1').dialog('setTitle','详情');
			$('#add-dialog1').dialog('open');
			
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);
}

