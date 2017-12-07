var dataOptions = {
	baseUrl:"",
	basePath:"",
	addInit:function(){},// 添加弹窗初始化
	editInit:function(data){},// 修改数据加载完成初始化
	saveInit:function(data){}// 保存前执行操作
};

$(function(){ 
	$('#state-g').combobox({
		multiple:false,
		editable:false,
		panelHeight:150,
		valueField:'id',
		textField:'text',
		data:[{
			"id":"",
			"text":"全部"
		},{
			"id":"1",
			"text":"报名中"
		},{
			"id":"2",
			"text":"报名结束"
		},{
			"id":"3",
			"text":"已开课"
		}]
	});
	$('#state').combobox({
		multiple:false,
		editable:false,
		panelHeight:150,
		valueField:'id',
		textField:'text',
		data:[{
			"id":"1",
			"text":"报名中"
		},{
			"id":"2",
			"text":"报名结束"
		},{
			"id":"3",
			"text":"已开课"
		}]
	});
	$('#isRecommend-g').combobox({
		multiple:false,
		editable:false,
		panelHeight:150,
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
	$('#courseTypeId-g').combobox({
		multiple:false,
		editable:false,
		panelHeight:200,
		valueField:'id',
		textField:'text',
		url:webRoot+'/web/course/coursetype/searchCourseTypeList.htm?type=1',
	});
	$('#courseTypeId').combobox({
		multiple:false,
		editable:false,
		panelHeight:200,
		valueField:'id',
		textField:'text',
		url:webRoot+'/web/course/coursetype/searchCourseTypeList.htm',
	});
});

/**添加*/
function gridAdd(){
	clearTextBox();
	$('#add-data-form').form('clear');// 清除form表单数据
	new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
	$("#imgShow").attr("src","");
	$('#add-data-form').find("input[name=state]")[0].checked="checked";
	$('#add-data-form').find("input[name=isRecommend]")[0].checked="checked";
	$('#add-data-form').find("input[name=isPublic]")[1].checked="checked";
	editor.html("");
	editor1.html("");
	$( "#add-dialog" ).dialog("open").dialog('setTitle','添加');
};

//保存
var save = function(){
	if($("input[name=id]").val() == "" && $("#up_img").val() == ""){
		$.messager.alert("错误","课程封面必选");
		return;
	}
	if(!$("#add-data-form").form('validate'))return;
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
			var data = ret.data.data;
			$('#add-data-form').form('clear');// 清除form表单数据
			// 初始化
			$("input[name=id]").val(data.id);
			$('#city').combobox({
		        valueField:'id', //值字段
		        textField:'name', //显示的字段
		        url:dataOptions.baseUrl+'searchPcd.htm?type=1&id='+data.province,
		        panelHeight:300,
		        editable:true
			}); 
			$('#district').combobox({
		        valueField:'id', //值字段
		        textField:'name', //显示的字段
		        url:dataOptions.baseUrl+'searchPcd.htm?type=2&id='+data.city,
		        panelHeight:300,
		        editable:true
			});
			$('#province').combobox('setValue',data.province);
			$('#city').combobox('setValue',data.city);
			$('#district').combobox('setValue',data.district);
			$("#address").textbox("setValue",data.address);
			$("#sort").textbox("setValue",data.sort);
			
			new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
			$("#imgShow").attr("src",data.logoPath);
			$("#courseTypeId").combobox("setValue",data.courseTypeId+"");
			$("#itemName").textbox("setValue",data.itemName);
			$("#registerDate").textbox("setValue",data.registerDate);
			$("#amount").textbox("setValue",data.amount);
			$("input[name=state][value="+data.state+"]").attr('checked', 'true');
			$("input[name=isPublic][value="+data.isPublic+"]").attr('checked', 'true');
			$("input[name=isRecommend][value="+data.isRecommend+"]").attr('checked', 'true');
			if(data.courseId != '') $("#courseId").combobox("setValue",data.courseId+"");
			if(data.courseId2 != '') $("#courseId2").combobox("setValue",data.courseId2+"");
			$("#subsidy").textbox("setValue",data.subsidy);
			$("#remark").val(data.remark);
			editor.html(ret.data.data.courseContent);
			editor1.html(ret.data.data.courseRemark);
			$('#add-dialog').dialog('setTitle','修改');
			$('#add-dialog').dialog('open');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);
};

//主讲老师
var teacher = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.basePath+'/views/jsp/course/courseTeacher.jsp?courseId='+rowData.id+'&courseTypeId='
	+rowData.courseTypeId+'&itemName='+rowData.itemName;
	parent.$('#tabs').tabs('add',{
		title:"线下课程-主讲老师",
		content:'<iframe  style="width:100%;height:90%;" scrolling="auto" frameborder="0" src="'+url+'"></iframe>',
		closable: true
	});
}

//报名联系人
var contact = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.basePath+'/views/jsp/course/courseContact.jsp?courseId='+rowData.id+'&courseTypeId='
	+rowData.courseTypeId+'&itemName='+rowData.itemName;
	parent.$('#tabs').tabs('add',{
		title:"线下课程-报名联系人",
		content:'<iframe  style="width:100%;height:90%;" scrolling="auto" frameborder="0" src="'+url+'"></iframe>',
		closable: true
	});
}

//首页推荐
var isRecommend = function(rowIndex,status){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.baseUrl+'saveCO.htm';/* 配置保存数据地址 */
	var data = {id:rowData.id,isRecommend:status}; /* 请求的参数内容 */
	$.post(url,data,function(ret){
		if(ret && ret.status == 10001){
			mygrid.datagrid('reload');
		}else {
			$.messager.alert("错误",ret.msg,"error");
		}
	},'json');
}

//线下成交费
function offlineAmount(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	$('#add-oa-form').form('clear');// 清除form表单数据
	myLoad("#add-oa-form",rowData);
	$( "#add-oa" ).dialog("open");
};

//保存线下成交费
var saveOA = function(){
	if(!$("#add-oa-form").form('validate'))return;
	var url = dataOptions.baseUrl+'saveCO.htm';/* 配置保存数据地址 */
	var check = function(){};

	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			$('#add-oa').dialog('close');
			mygrid.datagrid('reload');
			$('#add-oa-form').form('clear');
		}else {
			parent.$.messager.alert("错误",ret.msg,"error");
		}
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	formSubmit($('#add-oa-form'),url,check,success);
}

//课程状态
var upState = function(){
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
	$('#add-state-form').form('clear');
	$("#add-state").find("input[name=ids]").val(ids);
	$( "#add-state" ).dialog("open");
}

//保存批量更改状态
var saveState = function(){
	if(!$("#add-state-form").form('validate'))return;
	var url = dataOptions.baseUrl+'upState.htm';/* 配置保存数据地址 */
	var check = function(){};

	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			$('#add-state').dialog('close');
			mygrid.datagrid('reload');
			$('#add-state-form').form('clear');
		}else {
			parent.$.messager.alert("错误",ret.msg,"error");
		}
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	formSubmit($('#add-state-form'),url,check,success);
}

/**
 * 编辑器创建
 */
var editor ;
KindEditor.ready(function(K) {
	editor = K.create('textarea[id="courseContent"]', {
		cssPath : dataOptions.basePath+'/css/img.css',
		uploadJson : dataOptions.basePath+'/js/kindeditor-4.1.10/jsp/upload_json.jsp',
		extraFileUploadParams:{folder:'siteAccessories'},
		resizeType : 1,
		allowFileManager : true,
		allowPreviewEmoticons : false,
		urlType:'domain',
		items : [
	 		'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
	 		'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
	 		'insertunorderedlist', '|', 'emoticons', 'image', 'link'
 		],
		afterCreate : function(){ //kindeditor创建后，将编辑器的内容设置到原来的textarea控件里
            this.sync();  
	    },
	    afterChange: function(){ //编辑器内容发生变化后，将编辑器的内容设置到原来的textarea控件里
	            this.sync();  
	    },
	    afterBlur : function(){ //编辑器聚焦后，将编辑器的内容设置到原来的textarea控件里
	         this.sync();
	    }  
	});
});

/**
 * 编辑器创建
 */
var editor1 ;
KindEditor.ready(function(K) {
	editor1 = K.create('textarea[id="courseRemark"]', {
		cssPath : dataOptions.basePath+'/css/img.css',
		uploadJson : dataOptions.basePath+'/js/kindeditor-4.1.10/jsp/upload_json.jsp',
		extraFileUploadParams:{folder:'siteAccessories'},
		resizeType : 1,
		allowFileManager : true,
		allowPreviewEmoticons : false,
		urlType:'domain',
		items : [
	 		'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
	 		'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
	 		'insertunorderedlist', '|', 'emoticons', 'image', 'link'
 		],
		afterCreate : function(){ //kindeditor创建后，将编辑器的内容设置到原来的textarea控件里
            this.sync();  
	    },
	    afterChange: function(){ //编辑器内容发生变化后，将编辑器的内容设置到原来的textarea控件里
	            this.sync();  
	    },
	    afterBlur : function(){ //编辑器聚焦后，将编辑器的内容设置到原来的textarea控件里
	         this.sync();
	    }  
	});
});

//导出
var gridExport = function(){
	window.location.href = dataOptions.baseUrl+'/export.htm?'+$('#search-form').serialize() ;
} ;
