var dataOptions = {
	baseUrl:"",
	basePath:"",
	addInit:function(){},// 添加弹窗初始化
	editInit:function(data){},// 修改数据加载完成初始化
	saveInit:function(data){}// 保存前执行操作
};

$(function(){
	$('#state').combobox({
		required:true,
		multiple:false,
		editable:false,
		panelHeight:80,
		valueField: 'label',
		textField: 'value',
		data:[{
				label: '1',
				value: '有效'
			},{
				label: '0',
				value: '无效'
			}]
	});
});

/**添加*/
function gridAdd(){
	clearTextBox();
	$("#password").parent().parent().hide();
	$('#pwd').textbox({
		required:true
	});
	$('#add-data-form').form('clear');// 清除form表单数据
	$( "#add-dialog" ).dialog("open").dialog('setTitle','添加');
};

/** 保存*/
var save = function(){
	if(!$("#add-data-form").form('validate'))return;
	var data = $('#add-data-form').form('getData');
	if(data.id != ''){
		if(data.pwd != '' && data.password == ''){
			$.messager.alert("错误",'修改密码时、原密码与新密码必填');
			return;
		}else if(data.pwd == '' && data.password != ''){
			$.messager.alert("错误",'修改密码时、原密码与新密码必填');
			return;
		}
	}
	var check = function(){};
	var url = dataOptions.baseUrl+'save.htm';/* 配置保存数据地址 */
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

//修改
var gridEdit = function(rowIndex){
	var url = dataOptions.baseUrl + 'data.htm';/* 配置查询要修改的数据查询地址 */
	var rowData = getData(rowIndex);
	var data = {id:rowData.id}; /* 请求的参数内容 */
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			$('#add-data-form').form('clear');// 清除form表单数据
			
			// 初始化
			myLoad("#add-data-form",ret.data.data)
			$("#password").parent().parent().show();
			$('#pwd').textbox({
				required:false
			});
			$('#add-dialog').dialog('setTitle','修改');
			$('#add-dialog').dialog('open');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);	
};
