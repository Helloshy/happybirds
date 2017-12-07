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
	$('#recordType1').combobox({
		multiple:false,
		editable:false,
		panelHeight:80,
		valueField:'id',
		textField:'text',
		data:[{
			"id":"",
			"text":"全部"
		},{
			"id":"1",
			"text":"欧爸今日说"
		},{
			"id":"2",
			"text":"学术专栏管理"
		}]
	});
	$('#recordType').combobox({
		multiple:false,
		editable:false,
		panelHeight:80,
		valueField:'id',
		textField:'text',
		data:[{
			"id":"1",
			"text":"欧爸今日说"
		},{
			"id":"2",
			"text":"学术专栏管理"
		}]
	});
});

/**添加*/
function gridAdd(){
	clearTextBox();
	$('#add-data-form').form('clear');// 清除form表单数据
	new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
	$("#imgShow").attr("src","");
	editor.html("");
	if(dataOptions.recordType != 5){
		$("input[name=isTop]")[1].checked="checked";
		$("input[name=isQuality]")[0].checked="checked";
	}
	$( "#add-dialog" ).dialog("open").dialog('setTitle','添加');
};

//保存
var save = function(){
	if(!$("#add-data-form").form('validate'))return;
	if(dataOptions.recordType == 4){
		if(editor.html() == '' && $("#link").textbox("getValue") == ''){
			$.messager.alert("错误","连接与图文详情必填一项");
			return;
		}
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
			editor.html("");
			$("input[name=id]").val(data.id);
			if(dataOptions.recordType != 4)new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
			$("#imgShow").attr("src",data.logoPath);
			$("#title").textbox("setValue",data.title);
			$("#link").textbox("setValue",data.link);
			$("#dn-title").text(data.title);
			$("#recordType").combobox("setValue",data.recordType+"");
			$("#sort").textbox("setValue",data.sort);
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
	var data = {id:rowData.id}; /* 请求的参数内容 */
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			// 初始化
			var data = ret.data.data;
			editor.html("");
			$("#imgShow1").attr("src",data.logoPath);
			$("#title1").text(data.title);
			$("#newsTag1").text(data.newsTag);
			$("#isTop1").text(data.isTop == 0 ? "否" : "是");
			$("#isQuality1").text(data.isQuality == 0 ? "否" : "是");
			$("#remark1").text(data.remark);
			editor1.html(data.content);
			editor1.readonly(true);
			$('#add-dialog1').dialog('open');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);
};

//加精
var quality = function(rowIndex,status){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.baseUrl+'saveQuality.htm';/* 配置保存数据地址 */
	var data = {id:rowData.id,isQuality:status}; /* 请求的参数内容 */
	$.post(url,data,function(ret){
		if(ret && ret.status == 10001){
			mygrid.datagrid('reload');
		}else {
			$.messager.alert("错误",ret.msg,"error");
		}
	},'json');
}

//置顶
var istop = function(rowIndex,status){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.baseUrl+'saveTop.htm';/* 配置保存数据地址 */
	var data = {id:rowData.id,isTop:status,recordType:dataOptions.recordType,newsTag:rowData.newsTag}; /* 请求的参数内容 */
	$.post(url,data,function(ret){
		if(ret && ret.status == 10001){
			mygrid.datagrid('reload');
		}else {
			$.messager.alert("错误",ret.msg,"error");
		}
	},'json');
}

var ishome = function(rowIndex,status){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.baseUrl+'saveQuality.htm';/* 配置保存数据地址 */
	var data = {id:rowData.id,isHome:status,recordType:dataOptions.recordType}; /* 请求的参数内容 */
	$.post(url,data,function(ret){
		if(ret && ret.status == 10001){
			mygrid.datagrid('reload');
		}else {
			$.messager.alert("错误",ret.msg,"error");
		}
	},'json');
}



var editor ;
/**
 * 编辑器创建
 */
KindEditor.ready(function(K) {
	editor = K.create('textarea[id="content"]', {
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
var editor1 ;
/**
 * 编辑器创建
 */
KindEditor.ready(function(K) {
	editor1 = K.create('textarea[id="content1"]', {
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

