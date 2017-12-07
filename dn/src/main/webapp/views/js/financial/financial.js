var dataOptions = {
	baseUrl:"",
	basePath:"",
	addInit:function(){},// 添加弹窗初始化
	editInit:function(data){},// 修改数据加载完成初始化
	saveInit:function(data){}// 保存前执行操作
};

/**添加*/
function gridAdd(){
	clearTextBox();
	$('#add-data-form').form('clear');// 清除form表单数据
	$( "#add-dialog" ).dialog("open").dialog('setTitle','添加');
};

//保存
var save = function(){
	if(!$("#add-data-form").form('validate'))return;
	var url = dataOptions.basePath+'/web/teacher/teacherorder/save.htm';/* 配置保存数据地址 */
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

//修改
var gridEdit = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.basePath + '/web/teacher/teacherorder/data.htm';/* 配置查询要修改的数据查询地址 */
	var data = {id:rowData.id}; /* 请求的参数内容 */
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			var data = ret.data.data;
			$("input[name=id]").val(data.id);
			$("#freeTimes").textbox("setValue",data.freeTimes);
			$("#personnel").textbox("setValue",data.personnel);
			$("#reason").textbox("setValue",data.reason);
			$("input[name=transportation][value="+data.transportation+"]").attr('checked', 'true');
			$('#add-dialog').dialog('setTitle','修改');
			$('#add-dialog').dialog('open');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);
};

//课程订单
var offlineOrder = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.basePath+'/views/jsp/order/offlineOrder.jsp?uid='+rowData.uid+'&couponId='+(rowData.couponId == "" ? 0 : rowData.couponId);
	parent.$('#tabs').tabs('add',{
		title:"线下课程订单",
		content:'<iframe  style="width:100%;height:90%;" scrolling="auto" frameborder="0" src="'+url+'"></iframe>',
		closable: true
	});
}


//课程购买人数
var counts = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.basePath+'/views/jsp/financial/purchaseCount.jsp?cid='+rowData.id+'&itemName='+rowData.itemName+'&teacherName='+rowData.teacherName;
	parent.$('#tabs').tabs('add',{
		title:"课程购买人数",
		content:'<iframe  style="width:100%;height:90%;" scrolling="auto" frameborder="0" src="'+url+'"></iframe>',
		closable: true
	});
}
//导出课程购买人数
var purchaseExcel = function(id){
	window.location.href = dataOptions.baseUrl+'exportExcel.htm?courseId='+id+'&type=4';
};

/** 导出 */
var exportExcel = function(type){
	window.location.href = dataOptions.baseUrl+'exportExcel.htm?type='+type+"&"+$('#search-form').serialize();
};
