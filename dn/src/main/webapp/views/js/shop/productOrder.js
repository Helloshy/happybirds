var dataOptions = {
	baseUrl:"",
	basePath:"",
	recordType:"",
	tagType:"",
	addInit:function(){},// 添加弹窗初始化
	editInit:function(data){},// 修改数据加载完成初始化
	saveInit:function(data){}// 保存前执行操作
};


$(function(){
	$('#oderStateQuery').combobox({
		valueField:'state', //值字段
		textField:'text', //显示的字段
		panelHeight:120,
		editable:false,//不可编辑，只能选择
		multiple:false,
		data:[{
			"state":"",
			"text":""
		},{
			"state":"0",
			"text":"待支付"
		},{
			"state":"1",
			"text":"待发货"
		},{
			"state":"2",
			"text":"待收货"
		},{
			"state":"3",
			"text":"已完成"
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
			var data = ret.data.data;
			editor.html("");
			$("input[name=id]").val(data.id);
			if(dataOptions.recordType != 4)new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
			$("#imgShow").attr("src",data.logoPath);
			$("#title").textbox("setValue",data.title);
			$("#link").textbox("setValue",data.link);
			$("#dn-title").text(data.title);
			$("#recordType").combobox("setValue",data.recordType+"");
			$("#newsTag").combobox("setValue",data.newsTag+"");
			if(dataOptions.recordType != 5){
				$("input[name=isTop][value="+data.isTop+"]").attr('checked', 'true');
				$("input[name=isQuality][value="+data.isQuality+"]").attr('checked', 'true');
			}
			$("#remark").val(data.remark);
			editor.html(data.content);
			$('#add-dialog').dialog('setTitle','修改');
			$('#add-dialog').dialog('open');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);
};

//详情
var detail = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.baseUrl + 'data.htm';/* 配置查询要修改的数据查询地址 */
	var data = {orderNo:rowData.orderNo}; /* 请求的参数内容 */
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			// 初始化
			var data = ret.data.data[0];
			$("#realName1").text(data.realName);
			$("#userName1").text(data.userName);
			$("#payTime1").text(data.payTime);
			$("#deliveryName1").text(data.deliveryName);
			$("#userName2").text(data.userName);
			$("#deliveryAddress1").text(data.deliveryAddress);

			$("#itemRemark1").text(data.itemRemark);
			$("#price1").text(data.price);
			$('#deliveryAmount1').text(data.discount);
			$("#color1").text(data.color);
			$("#size1").text(data.size);
			$("#qty1").text(data.qty);

			$("#orderNo1").text(data.orderNo);
			$("#state1").text(data.state);
			$("#deliveryNo1").text(data.deliveryNo);
			$("#discount1").text(data.discount);
			$("#payMethod1").text(data.payMethod);
			$("#payTime2").text(data.payTime);
			$('#add-detail').dialog('open');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);
};

//查看评价
var comment =function (rowIndex) {
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.baseUrl + 'data.htm';/* 配置查询要修改的数据查询地址 */
	var data = {orderNo:rowData.orderNo}; /* 请求的参数内容 */
	console.log(rowData)
	/* 请求成功后回调函数succe
	ss  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			// 初始化
			var data = ret.data.data[0];
			console.log(data)
			$('#starDiv').raty({ readOnly: true, score: data.commentStar});
			console.log(data.commentStar)
			$("#commentRemark1").text(data.commentRemark);
			$("#commentTime1").text(data.commentTime);
			$('#add-comment').dialog('open');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);


}

//发货
var gridDelivery = function (rowIndex){
	clearTextBox();
	$('#delivery-data-form').form('clear');// 清除form表单数据
	var rowData = getData(rowIndex);// 取到选择的数据
	var orderNo = rowData.orderNo
	console.log(rowData)
	//传入订单编号
	$('#orderNo1').val(orderNo);
	$("#delivery-dialog").dialog("open").dialog('setTitle','发货');
};


 /*function () {
	$('#delivery-data-form').form('clear');// 清除form表单数据
	var sele = mygrid.datagrid("getSelections");
	if(sele.length!=1){
		$.messager.alert("确认","只能选择一条数据")
		return
	}
	var orderNo = sele[0].orderNo;
	console.log(orderNo)

	$("#delivery-dialog").dialog("open").dialog('setTitle','添加');
}*/

//保存发货信息
var saveDelivery= function(){
	if(!$("#delivery-data-form").form('validate'))return;
	var url = dataOptions.baseUrl+'update.htm'/* 配置保存数据地址 */
	var check = function(){};

	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			$('#delivery-dialog').dialog('close');
			mygrid.datagrid('reload');
			$('#delivery-data-form').form('clear');
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
	formSubmit($('#delivery-data-form'),url,check,success);
};

var exportOrder =function () {
	//拿到搜索条件
	var orderNo = $('#orderNoQuery').val();
	var itemRemark = $('#itemRemarkQuery').val();
	var deliveryName = $('#deliveryNameQuery').val();
	var state = $('#oderStateQuery').val();
    window.location.href = dataOptions.baseUrl+'exportOrder.htm?orderNo='+orderNo
		+'&itemRemark='+itemRemark+'&deliveryName='+deliveryName+'&state='+state;
}


