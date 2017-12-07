var dataOptions = {
	recordType:"",
	baseUrl:"",
	basePath:"",
	addInit:function(){},// 添加弹窗初始化
	editInit:function(data){},// 修改数据加载完成初始化
	saveInit:function(data){}// 保存前执行操作
};

$(function(){
	$('#state-1').combobox({
		editable:false,
		multiple:false,
		panelHeight:120,
		valueField:'id',
		textField:'text',
		data:[{
			"id":"",
			"text":"全部"
		},{
			"id":"0",
			"text":"待审核"
		},{
			"id":"2",
			"text":"审核不通过"
		}]
	});
	$('#state-2').combobox({
		editable:false,
		multiple:false,
		panelHeight:120,
		valueField:'id',
		textField:'text',
		data:[{
			"id":"",
			"text":"全部"
		},{
			"id":"1",
			"text":"待付费"
		},{
			"id":"3",
			"text":"已取消"
		},{
			"id":"4",
			"text":"付费成功"
		}]
	});
	$('#state-3').combobox({
		editable:false,
		multiple:false,
		panelHeight:120,
		valueField:'id',
		textField:'text',
		data:[{
			"id":"",
			"text":"全部"
		},{
			"id":"4",
			"text":"未开始"
		},{
			"id":"5",
			"text":"进行中"
		}]
	});
})

//保存
var save = function(){
	if(!$("#add-data-form").form('validate'))return;
	var state = $("input[name=state]:checked").val();
	if(state == 1 && $("#orderAmount").textbox("getValue") == '' || $("#teachEnd").datebox("getValue") == 0){
		$.messager.alert("警告","费用与结束时间必填");
		return;
	}
	var url = dataOptions.baseUrl+'save.htm?recordType='+dataOptions.recordType;/* 配置保存数据地址 */
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
	var url = dataOptions.baseUrl + 'data.htm';/* 配置查询要修改的数据查询地址 */
	var data = {id:rowData.id}; /* 请求的参数内容 */
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){

			$('#add-data-form').form('clear');// 清除form表单数据
			// 初始化
			var data = ret.data.data;
			$("input[name=id]").val(data.id);
			$("#realName").text(data.realName);
			$("#userName").text(data.userName);
			$("#createTime").text(data.createTime);
			$("#nickName").textbox("setValue",data.nickName);
			$("#phone").textbox("setValue",data.phone);
			$("#orderNo").text(data.id);
			$("#address").textbox("setValue",data.address);
			$("#companyName").textbox("setValue",data.companyName);
			$("#companyRemark").textbox("setValue",data.companyRemark);
			$("#teachTheme").textbox("setValue",data.teachTheme);
			$("#orderAmount").textbox("setValue",data.orderAmount);
			$("#teachStart").datebox("setValue",data.teachStart);
			$("#teachEnd").datebox("setValue",data.teachEnd);
			$("#itemRemark").val(data.itemRemark);
			$("input[name=state][value="+data.state+"]").attr('checked', 'true');
			$('#add-dialog').dialog('setTitle','修改').dialog('open');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);
};

/**批量*/
var state = function(state){
	var sele = mygrid.datagrid("getSelections");
	if(sele.length != 1){
		$.messager.alert("确认","只能选择一条数据?")
		return ;
	}
	var ids = [];
	for(var i in sele){
		ids.push(sele[i].id);
	}
	ids = ids.join(",");
	if(state == 1){
		var url = dataOptions.baseUrl + 'data.htm?id='+ids;/* 配置查询要修改的数据查询地址 */
		$.post(url,null,function(ret){
			if(ret && ret.status == 10001){
			// 初始化
			var data = ret.data.data;
			$('#add-data-oa').form('clear');// 清除form表单数据
			$("#teachStart1").datebox("setValue",data.teachStart);
			$("#teachEnd1").datebox("setValue",data.teachEnd);
			$("#orderAmount1").textbox("setValue",data.orderAmount);
			$("#ids-oa").val(ids);
			$("#state-oa").val(state);
			$('#add-oa').dialog('setTitle','审核通过').dialog('open');
		}else parent.$.messager.alert("错误",ret.msg,"error");
		},'json');
	}else{
		var delUrl = dataOptions.baseUrl + 'upstate.htm';/* 配置删除的地址 */
		var postData = {ids: ids,state:state};/* 请求到服务器的数据内容 */
		/* 请求成功后回调函数success  data为请求服务器返回的参数*/
		var success = function(ret){
			if(ret && ret.status == 10001){
				mygrid.datagrid('reload');
			}else parent.$.messager.alert("错误",ret.msg,"error");
		};
		var error = null;
		parent.$.messager.confirm('确认','确认要更新所选数据?',function(r){  
	       if (r) asynPAjaxMin(delUrl,postData,success,error);
	    });
	}
};

//保存
var saveoa = function(){
	if(!$("#add-data-oa").form('validate'))return;
	var url = dataOptions.baseUrl+'upstate.htm';/* 配置保存数据地址 */
	var check = function(){};

	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			$('#add-oa').dialog('close');
			mygrid.datagrid('reload');
			$('#add-data-oa').form('clear');
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
	formSubmit($('#add-data-oa'),url,check,success);
};

//付费
var payEdit = function(rowIndex){
	$('#add-data-oa').form('clear');// 清除form表单数据
	var rowData = getData(rowIndex);// 取到选择的数据
	$("#ids-oa").val(rowData.id);
	$("#state-oa").val(4);
	$("#orderAmount").text(rowData.orderAmount);
	$("#actualAmount").textbox("setValue",rowData.actualAmount);
	$("#discountRemark").text(rowData.discountRemark);
	$('#add-oa').dialog('setTitle','付费成功').dialog('open');
}

//评价
var comment = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	if(rowData.comment == 0){
		$.messager.alert("提示","该订单还未评价");
		return;
	}
	var url = dataOptions.baseUrl + 'commentDetail.htm';/* 配置查询要修改的数据查询地址 */
	var data = {id:rowData.id}; /* 请求的参数内容 */
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			var data = ret.data.data;
			$('#add-data-oa').form('clear');// 清除form表单数据
			var html = "";
			if(data.star > 0){
				for ( var int = 0; int < data.star; int++) {
					html += ' <img src="'+dataOptions.basePath+'/attached/evaluate1.png" alt="" height="30px;" width="30px;"/>';
				}
				for ( var int2 = 0; int2 < (5 - data.star); int2++) {
					html += ' <img src="'+dataOptions.basePath+'/attached/evaluate.png" alt="" height="30px;" width="30px;"/>';
				}
			}else{
				for ( var int = 0; int < 5; int++) {
					html += ' <img src="'+dataOptions.basePath+'/attached/evaluate.png" alt="" height="30px;" width="30px;"/>';
				}
			}
			$("#star").html(html);
			$("#content").val(data.content);
			$("#createTime").text(data.createTime);
			$('#add-oa').dialog('open');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);
}

/** 导出 */
var exportExcel = function(type){
	window.location.href = dataOptions.baseUrl+'exportExcel.htm?recordType=2&type='+type+"&"+$('#search-form').serialize();
} ;

