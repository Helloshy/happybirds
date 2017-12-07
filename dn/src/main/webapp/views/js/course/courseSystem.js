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
	new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
	$("#imgShow").attr("src","");
	$("input[name=isFinancial]")[0].checked="checked";
	$( "#add-dialog" ).dialog("open").dialog('setTitle','添加');
};

//保存
var save = function(){
	if(!$("#add-data-form").form('validate'))return;
	if($("#courseOnline").combobox("getValues") == '' && $("#courseOffline").combobox("getValues") == ''){
		$.messager.alert("提示","线下课程或网络课程必选一项");
		return;
	}
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
			var data = ret.data.data;
			if(data.courseOnline != '') $("#courseOnline").combobox("setValues",data.courseOnline.split(","));
			if(data.courseOffline != '') $("#courseOffline").combobox("setValues",data.courseOffline.split(","));
			$("#amount").textbox("setValue",data.amount);
			$("#itemRemark").val(data.itemRemark);
			$("#itemName").textbox("setValue",data.itemName);
			$("#sort").textbox("setValue",data.sort);
			$('#add-data-form').find("input[name=id]").val(data.id);
			$("input[name=isFinancial][value="+ret.data.data.isFinancial+"]").attr('checked', 'true');
			new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
			$("#imgShow").attr("src",ret.data.data.logoPath);
			$('#add-dialog').dialog('setTitle','修改');
			$('#add-dialog').dialog('open');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);
};

//报名联系人
var contact = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.basePath+'/views/jsp/course/courseContact1.jsp?courseId='+rowData.id+'&itemName='+rowData.itemName;
	parent.$('#tabs').tabs('add',{
		title:"系统课程-报名联系人",
		content:'<iframe  style="width:100%;height:90%;" scrolling="auto" frameborder="0" src="'+url+'"></iframe>',
		closable: true
	});
}

