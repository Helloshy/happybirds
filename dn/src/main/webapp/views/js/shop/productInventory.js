var dataOptions = {
	baseUrl:"",
	basePath:"",
	productId:"",
	addInit:function(){},// 添加弹窗初始化
	editInit:function(data){},// 修改数据加载完成初始化
	saveInit:function(data){}// 保存前执行操作
};

var initProperTy = function (productId,categoryName,itemRemark,price) {
	/*$('#color1').combobox({
		url: webRoot+'/web/tag/usertag/searchTagTypeList.htm?tagType=颜色',
		valueField: 'id',
		textField: 'color'
	})

	$('#size1').combobox({
		url: webRoot+'/web/tag/usertag/searchTagTypeList.htm?tagType=尺寸',
		valueField: 'id',
		textField: 'size'
	})*/

	$('#productId1').val(productId)
	$('#categoryName1').textbox('setValue',categoryName)
	$('#itemRemark1').textbox('setValue',itemRemark)
	$('#price1').textbox('setValue',price)
}

/**添加*/
function gridAdd(productId,categoryName,itemRemark,price){
	clearTextBox();
	$('#add-data-form').form('clear');// 清除form表单数据
	$( "#add-dialog" ).dialog("open").dialog('setTitle','添加');
};

//保存
var save = function(){
	if(!$("#add-data-form").form('validate'))return;
	var url = dataOptions.baseUrl+'save.htm?productId='+dataOptions.productId;/* 配置保存数据地址 */
	var check = function(){};
	if($("#size").textbox("getValue") != ''){
		if($("#color").textbox("getValue") == ''){
			parent.$.messager.alert("提示","尺码值输入时颜色值必填","error");
			return;
		}
	}

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
			$('#edit-data-form').form('clear');
			//myLoad("#add-data-form",ret.data.data);
			// 初始化
			var data = ret.data.data;
			$("input[name=id]").val(data.id);
			$("#itemName").text(data.itemName);
			$("#categoryName").text(data.categoryName);
			$("#itemRemark").text(data.itemRemark);
			$("#color1").text(data.color);
			$("#size1").text(data.size);
			$("#colorName1").text(data.colorName);
			$("#sizeName1").text(data.sizeNAme);
			$("#price1").textbox("setValue",data.price);
			$("#stockQty1").textbox("setValue",data.stockQty);
			$('#edit-dialog').dialog('setTitle','修改');
			$('#edit-dialog').dialog('open');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);
};

var saveEdit = function(){
	if(!$("#edit-data-form").form('validate'))return;
	var url = dataOptions.baseUrl+'save.htm?productId='+dataOptions.productId;/* 配置保存数据地址 */
	var check = function(){};

	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			$('#edit-dialog').dialog('close');
			mygrid.datagrid('reload');
			$('#edit-data-form').form('clear');
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
	formSubmit($('#edit-data-form'),url,check,success);
};


var exportProInvertoryExcel =function (productId) {
	//拿到搜索条件
	if(!productId){
		$.messager.alert('提示','请先添加产品');
		return;
	}
    window.location.href = dataOptions.baseUrl+'exportExcel.htm?productId'+productId;
}

var gridDel = function(rowIndex){
	var rowData = getData(rowIndex);
	var delUrl = dataOptions.baseUrl + 'delete.htm';/* 配置删除的地址 */
	var postData = {'ids': rowData.id};/* 请求到服务器的数据内容 */
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			mygrid.datagrid('reload');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null;
	parent.$.messager.confirm('确认','确认要删除所选数据?',function(r){  
       if (r) asynPAjaxMin(delUrl,postData,success,error);
    });
}

