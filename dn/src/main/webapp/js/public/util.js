
var dataOptions = {
	baseUrl:"",
	basePath:"",
	addInit:function(){},// 添加弹窗初始化
	editInit:function(data){},// 修改数据加载完成初始化
	saveInit:function(data){}// 保存前执行操作
};
/**定义一个全局的表格对象*/
var mygrid = $('#mygrid').datagrid();

/**取到选择的数据*/
var getData = function(rowIndex){
	mygrid.datagrid("unselectAll");
	mygrid.datagrid('selectRow', rowIndex); 
	return mygrid.datagrid('getSelected');
};

/** 导出 */
var exportExcel = function(){
	window.location.href = dataOptions.baseUrl+'exportExcel.htm?'+$('#search-form').serialize();;
} ;

/** 重置并刷新列表 */
var gridReload = function() {
	$('#search-form').form('clear');
	// 清理所有的easyui-textbox的值
	var textbox = $('#search-form .easyui-textbox');
	$.each(textbox, function(i, domEle) {
		$(domEle).textbox('setValue', '');
	});
	mygrid.datagrid('options').queryParams = new Object();
	mygrid.datagrid('reload');
};

/** 清空tableID为data-form的textbox的值 */
var clearTextBox = function() {
	var doms = $('#add-dialog .easyui-textbox');
	$.each(doms, function(i, domEle) {
		$(domEle).textbox('setValue', '');
	});
};

/**添加*/
function gridAdd(){
	clearTextBox();
	$('#add-data-form').form('clear');// 清除form表单数据
	$( "#add-dialog" ).dialog("open").dialog('setTitle','添加');
};


/** 查询*/
var gridSearch = function(){
	var searchData = $('#search-form').form('getData');
	mygrid.datagrid('options').queryParams = searchData;
	mygrid.datagrid('reload');
};

/** 保存*/
var save = function(){
	if(!$("#add-data-form").form('validate'))return;
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

/**删除单行*/
var gridDel = function(rowIndex){
	var rowData = getData(rowIndex);
	var delUrl = dataOptions.baseUrl + 'delete.htm';/* 配置删除的地址 */
	var postData = {'ids': rowData.id};/* 请求到服务器的数据内容 */
	parent.$.messager.confirm('确认','确认要删除所选数据?',function(r){  
	   if (r) asynPAjaxMin(delUrl,postData,success,error);
	});
}

/**批量删除*/
var gridDel = function(){
	
	var sele = mygrid.datagrid("getSelections");
	if(sele.length==0){
		$.messager.alert("确认","至少选择一条数据?")
		return 
	}
	var ids = [];
	for(var i in sele){
		ids.push(sele[i].id);
	}
	ids = ids.join(",");
	
	var delUrl = dataOptions.baseUrl + 'delete.htm';/* 配置删除的地址 */
	var postData = {'ids': ids};/* 请求到服务器的数据内容 */
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
};

/**提交form表单*/
var formSubmit = function(form,url,submit,success){
	$.messager.progress();
	$(form).form('submit',{
		url: url,
		onSubmit:submit,
	    success:function(data){
	    	$.messager.progress('close');
	    	data = $.parseJSON(data);
	    	if(data){
	    		if(data == -1){}// 正确
	    		else errorData(data);
	    	}
	    	if(success) success(data);
	    }
	});
}

/** 通用异步请求简单封装 */
function asynPAjaxMin(url,data,success,error,type,getType){
	if(type){}else type = 'Post';
	if(getType){}else getType = 'json';
	asynAjax(type,url,data,getType,function(data){
		if(success) success(data);
	},function(){
		if(error) error();
		else $.messager.alert('错误','请求服务器失败!','error');
	});
}

function asynAjax(type,url,data,dataType,success,error){
	$.messager.progress();
	$.ajax({type:type,url:url,data:data,dataType:dataType,success:function(data){
			$.messager.progress('close');
			if(success) success(data);
		},error:function(){
			$.messager.progress('close');
			if(error) error();
		}
	});
}

/** 初始化数据 */
function myLoad (form,param){
	var name;
	var value;
	doms = $(form).find("input[name],select[name],textarea[name]");
	$.each(doms, function(i, domEle) {
		name = $(domEle).attr("name");
		value = jQuery.proxy(function(){try{return eval('this.'+name);}catch(e){return "";}},param)();
		$(domEle).val(value);
	});
	var doms = $(form+' .easyui-textbox');
	$.each(doms, function(i, domEle) {
		name = $(domEle).attr("textboxName");
		value = jQuery.proxy(function(){try{return eval('this.'+name);}catch(e){return "";}},param)();
		$(domEle).textbox('setValue', value);
	});
	doms = $(form+' .easyui-combobox');
	$.each(doms, function(i, domEle) {
		name = $(domEle).attr("textboxName");
		value = jQuery.proxy(function(){try{return eval('this.'+name);}catch(e){return "";}},param)()+"";
		$(domEle).combobox('setValues', value != "" ? value.split(",") : "");
	});
	doms = $(form+' .easyui-validatebox');
	$.each(doms, function(i, domEle) {
		name = $(domEle).attr("name");
		value = jQuery.proxy(function(){try{return eval('this.'+name);}catch(e){return "";}},param)();
		$(domEle).val(value);
	});
	doms = $(form+' .easyui-datetimebox');
	$.each(doms, function(i, domEle) {
		name = $(domEle).attr("textboxName");
		value = jQuery.proxy(function(){try{return eval('this.'+name);}catch(e){return "";}},param)();
		$(domEle).datetimebox('setValue', value);
	});
	doms = $(form+' .easyui-datebox');
	$.each(doms, function(i, domEle) {
		name = $(domEle).attr("textboxName");
		value = jQuery.proxy(function(){try{return eval('this.'+name);}catch(e){return "";}},param)();
		$(domEle).datebox('setValue', value);
	});
	doms = $(form+' .easyui-combotree');
	$.each(doms, function(i, domEle) {
		name = $(domEle).attr("textboxName");
		value = jQuery.proxy(function(){try{return eval('this.'+name);}catch(e){return "";}},param)()+"";
		$(domEle).combotree('setValues', value != "" ? value.split(",") : "");
	});
	$(form).form("validate");
}
