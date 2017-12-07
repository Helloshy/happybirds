var dataOptions = {
	baseUrl:"",
	basePath:"",
	addInit:function(){},// 添加弹窗初始化
	editInit:function(data){},// 修改数据加载完成初始化
	saveInit:function(data){}// 保存前执行操作
};

$(function(){
	$('#payMethod').combobox({
		multiple:false,
		editable:false,
		panelHeight:200,
		valueField:'id',
		textField:'text',
		data:[{
			"id":"支付宝",
			"text":"支付宝"
		},{
			"id":"微信",
			"text":"微信"
		},{
			"id":"银联",
			"text":"银联"
		}]
	});
	$('#isPay').combobox({
		valueField:'id',
		textField:'text',
		data:[{
			"id":"",
			"text":"全部"
		},{
			"id":"0",
			"text":"未支付"
		},{
			"id":"1",
			"text":"已支付"
		},{
			"id":"2",
			"text":"已取消"
		}]
	});
});

//公用保存
var publicsave = function(form,dialog){
	if(!$(form).form('validate'))return;
	var url = dataOptions.baseUrl+'save.htm';/* 配置保存数据地址 */
	var check = function(){};
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			$(dialog).dialog('close');
			mygrid.datagrid('reload');
			$(form).form('clear');
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
	formSubmit($(form),url,check,success);
}

//保存订单
var save = function(){
	publicsave("#add-data-form",'#add-dialog');
};

/**添加*/
function gridAdd(){
	clearTextBox();
	$('#add-data-form').form('clear');// 清除form表单数据
	$("#realName").text("");
    $("#community").text("");
    $("#sjUserName").text("");
    $("#sjRealName").text("");
    $("input[name=recordType]").val(2);
	$( "#add-dialog" ).dialog("open").dialog('setTitle','添加');
};

/** 导出 */
var exportExcel = function(type){
	window.location.href = dataOptions.baseUrl+'exportExcel.htm?type='+type+"&"+$('#search-form').serialize();
} ;
