var editor ;
var dataOptions = {
	recordType:"",
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
	$("#userName").text("");
	if(dataOptions.recordType == 1){
		new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
		$("#imgShow").attr("src","");
	}
	editor.html("");
	$( "#add-dialog" ).dialog("open").dialog('setTitle','添加');
};

//保存
var save = function(){
	if(!$("#add-data-form").form('validate'))return;
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
			myLoad("#add-data-form",ret.data.data);
			$("#userName").text(ret.data.data.userName);
			if(dataOptions.recordType == 1){
				new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
				$("#imgShow").attr("src",ret.data.data.logoPath);
			}
			editor.html(ret.data.data.itemContent);
			$('#add-dialog').dialog('setTitle','修改');
			$('#add-dialog').dialog('open');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);
};

//网络课程
var onlines = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.basePath+'/views/jsp/course/courseOnline.jsp?teacherId='+rowData.id;
	parent.$('#tabs').tabs('add',{
		title:"网络课程",
		content:'<iframe  style="width:100%;height:90%;" scrolling="auto" frameborder="0" src="'+url+'"></iframe>',
		closable: true
	});
}

//评价
var comment = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	var html = 'teachers';
	var title = '名师管理-评价';
	if(dataOptions.recordType == 2){
		html = 'accompany';
		title = '陪伴师管理-评价';
	}
	var url = dataOptions.basePath+'/views/jsp/'+html+'/comment.jsp?teacherId='+rowData.id;

	parent.$('#tabs').tabs('add',{
		title:title,
		content:'<iframe  style="width:100%;height:90%;" scrolling="auto" frameborder="0" src="'+url+'"></iframe>',
		closable: true
	});
}

//预约时间
var arrange = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.baseUrl + 'arrangeDate.htm';/* 配置查询要修改的数据查询地址 */
	var data = {teacherId:rowData.id}; /* 请求的参数内容 */
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			var data = ret.data.data;
			$('#add-ta-form').form('clear');// 清除form表单数据
			if(null != data){
				$("#add-ta-form").find("input[name=id]").val(data.id);
				$("#arrangeDate").datebox("setValue",data.arrangeDate);
				$("#endDate").datebox("setValue",data.endDate);
				$("input[name=state][value="+data.state+"]").attr('checked', 'true');
			}else{
				$("#add-ta-form").find("input[name=teacherId]").val(rowData.id);
				$("input[name=state]")[0].checked="checked";
			}
			
			$('#add-ta').dialog('open');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);
}

//保存预约时间
var saveTa = function(){
	if(!$("#add-ta-form").form('validate'))return;
	var url = dataOptions.baseUrl+'saveTa.htm';/* 配置保存数据地址 */
	var check = function(){};

	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			$('#add-ta').dialog('close');
			mygrid.datagrid('reload');
			$('#add-ta-form').form('clear');
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
	formSubmit($('#add-ta-form'),url,check,success);
};

/**
 * 编辑器创建
 */
KindEditor.ready(function(K) {
	editor = K.create('textarea[id="itemContent"]', {
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

//保存首页推荐
var ishome = function(rowIndex,status){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.baseUrl+'saveIsHome.htm';/* 配置保存数据地址 */
	var data = {id:rowData.id,isHome:status}; /* 请求的参数内容 */
	$.post(url,data,function(ret){
		if(ret && ret.status == 10001){
			mygrid.datagrid('reload');
		}else {
			$.messager.alert("错误",ret.msg,"error");
		}
	},'json');
}

