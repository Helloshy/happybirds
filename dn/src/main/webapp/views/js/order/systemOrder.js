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
		},{
			"id":"线下支付",
			"text":"线下支付"
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

//保存线下实收交易金额
var saveAmount = function(){
	publicsave("#add-amount-form",'#add-amount');
};

/**添加*/
function gridAdd(){
	clearTextBox();
	$('#add-data-form').form('clear');// 清除form表单数据
	$("#realName").text("");
    $("#community").text("");
    $("#sjUserName").text("");
    $("#sjRealName").text("");
    $("input[name=recordType]").val(3);
	$( "#add-dialog" ).dialog("open").dialog('setTitle','添加');
};

//详情
var detail = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.baseUrl + 'courseOrderDetail.htm';/* 配置查询要修改的数据查询地址 */
	var data = {id:rowData.id}; /* 请求的参数内容 */
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			var data = ret.data.data;
			$("#courseId2").text(data.itemName);
			$("#userName2").text(data.userName);
			$("#offlineName2").text(data.offlineName);
			$("#onlineName2").text(data.onlineName);
			$("#realName2").text(data.realName);
			$("#community2").text(data.community);
			$("#sjUserName2").text(data.sjUserName);
			$("#sjRealName2").text(data.sjRealName);
			$("#originAmount2").text(data.originAmount);
			$("#payAmount2").text(data.payAmount);
			$("#payMethod2").text(data.payMethod);
			$("#payTime2").text(data.payTime);
			$("#discountBlue2").text(data.discountBlue);
			$("#discountRed2").text(data.discountRed);
			$('#add-dialog1').dialog('open');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);
}
//线下实收交易金额
var amount = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.baseUrl + 'getCourseDetail.htm';/* 配置查询要修改的数据查询地址 */
	var data = {id:rowData.courseId}; /* 请求的参数内容 */
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			$('#add-amount-form').form('clear');// 清除form表单数据
			// 初始化
			var data = ret.data.data;
			$('#add-amount-form').find("input[name=id]").val(rowData.id);
			$("#itemName1").text(data.itemName);
			$("#amount").text(data.amount1+data.amount2);
			$("#offlineName1").text(data.offlineName);
			$("#amount1").text(data.amount1);
			$("#onlineName1").text(data.onlineName);
			$("#amount2").text(data.amount2);
			$("#discountBlue1").text(rowData.discountBlue);
			$("#discountRed1").text(rowData.discountRed);
			$("#payAmount1").textbox("setValue",(rowData.payAmount > 0) ? rowData.payAmount : (data.amount1+data.amount2));
			$('#add-amount').dialog('open');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);
};

/** 导出 */
var exportExcel = function(type){
	window.location.href = dataOptions.baseUrl+'exportExcel.htm?type='+type+"&"+$('#search-form').serialize();
};
