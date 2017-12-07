var dataOptions = {
	baseUrl:"",
	basePath:"",
	addInit:function(){},// 添加弹窗初始化
	editInit:function(data){},// 修改数据加载完成初始化
	saveInit:function(data){}// 保存前执行操作
};

$(function(){
	$('#payStatus').combobox({
		multiple:false,
		editable:false,
		panelHeight:200,
		valueField:'id',
		textField:'text',
		data:[{
			"id":"",
			"text":"全部"
		},{
			"id":"1",
			"text":"新训"
		},{
			"id":"2",
			"text":"复训"
		},{
			"id":"3",
			"text":"赞赏"
		}]
	});
	$('#isUse').combobox({
		multiple:false,
		editable:false,
		panelHeight:200,
		valueField:'id',
		textField:'text',
		data:[{
			"id":"",
			"text":"全部"
		},{
			"id":"1",
			"text":"是"
		},{
			"id":"0",
			"text":"否"
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

//保存学员信息
var saveStudent = function(){
	publicsave("#add-student-form",'#add-student');
};

var saveStudent1 = function(){
	if(!$("#add-student-form1").form('validate'))return;
	var url = dataOptions.baseUrl+'saveCourseStudent.htm';/* 配置保存数据地址 */
	var check = function(){};
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			$("#add-student1").dialog('close');
			mygrid.datagrid('reload');
			$("#add-student-form1").form('clear');
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
	formSubmit($("#add-student-form1"),url,check,success);
};

//取消订单
var cancel = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	$.post(dataOptions.baseUrl+'save.htm?id='+rowData.id+"&isPay=2",null,function(ret){
		if(ret && ret.status == 10001){
			mygrid.datagrid('reload');
			$.messager.show({
				title:'提示',
				msg:'取消成功',
				timeout:5000,
				showType:'slide'
			});
		}else {
			parent.$.messager.alert("错误",ret.msg,"error");
		}
	},"json");
};

/**添加*/
function gridAdd(){
	clearTextBox();
	$('#add-data-form').form('clear');// 清除form表单数据
	$("#registerDate").text("");
	$("#pcda").text("");
	$("#realName").text("");
	$("input[name=recordType]").val(1);
	$("input[name=isUse][value=0]").attr('checked', 'true');
	$("input[name=payStatus][value=1]").attr('checked', 'true');
	$("input[name=isCoupon][value=0]").attr('checked', 'true');
	$( "#add-dialog" ).dialog("open").dialog('setTitle','添加');
};

//学员信息
var student = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	if(rowData.courseGroup != '孩子课程'){
		$('#add-student-form').form('clear');// 清除form表单数据
		$('#add-student-form').find("input[name=id]").val(rowData.id);
		$('#add-student-form').find("input[textboxName=studentName]").textbox("setValue",rowData.studentName);
		$('#add-student-form').find("input[textboxName=studentIdCard]").textbox("setValue",rowData.studentIdCard);
		$('#add-student-form').find("input[textboxName=studentPhone]").textbox("setValue",rowData.studentPhone);
		$('#add-student').dialog('open');
	}else{
		$('#add-student-form1').form('clear');// 清除form表单数据
		$('#add-student-form1').find("input[name=id]").val(rowData.id);
		$('#add-student-form1').find("input[textboxName=parentName]").textbox("setValue",rowData.parentName);
		$('#add-student-form1').find("input[textboxName=parentRelation]").textbox("setValue",rowData.parentRelation);
		$('#add-student-form1').find("input[textboxName=parentPhone]").textbox("setValue",rowData.parentPhone);
		$('#add-student-form1').find("input[textboxName=parentName2]").textbox("setValue",rowData.parentName2);
		$('#add-student-form1').find("input[textboxName=parentRelation2]").textbox("setValue",rowData.parentRelation2);
		$('#add-student-form1').find("input[textboxName=parentPhone2]").textbox("setValue",rowData.parentPhone2);
		$('#add-student-form1').find("input[textboxName=studentName]").textbox("setValue",rowData.studentName1);
		$('#add-student-form1').find("input[textboxName=studentIdCard]").textbox("setValue",rowData.studentIdCard1);
		$('#add-student-form1').find("input[textboxName=bodyStatus]").textbox("setValue",rowData.bodyStatus);
		$('#add-student-form1').find("input[textboxName=caseHistory]").textbox("setValue",rowData.caseHistory);
		$('#add-student-form1').find("input[textboxName=remark]").textbox("setValue",rowData.remark);
		$('#add-student-form1').find("input[name=sex][value="+(rowData.sex == '' ? 1 : rowData.sex)+"]").attr('checked', 'true');
		$('#add-student1').dialog('open');
	}
};

//线下实收交易金额
var amount = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	$('#add-amount-form').form('clear');// 清除form表单数据
	$('#add-amount-form').find("input[name=id]").val(rowData.id);
	$('#add-amount-form').find("input[name=isPay]").val(1);
	$('#add-amount-form').find("input[name=recordType]").val(1);
	$('#add-amount-form').find("input[textboxName=payAmount]").textbox("setValue",
			rowData.payAmount == '' ? rowData.originAmount-(rowData.discountBlue+rowData.discountRed) : rowData.payAmount);
	$('#add-amount').dialog('open');
};

/** 导出 */
var exportExcel = function(type,uid,couponId){
	window.location.href = dataOptions.baseUrl+'exportExcel.htm?type='+type+'&uid='+uid+'&couponId'+couponId+
	"&"+$('#search-form').serialize();
};
