var editor ;
var dataOptions = {
	baseUrl:"",
	basePath:"",
	recordType:"",
	addInit:function(){},// 添加弹窗初始化
	editInit:function(data){},// 修改数据加载完成初始化
	saveInit:function(data){}// 保存前执行操作
};

/**添加*/
function gridAdd(){
	var url = dataOptions.baseUrl+'findCount.htm?recordType='+dataOptions.recordType;
	$.post(url,null,function(ret){
		if(ret && ret.status == 10001){
			if(ret.data.count == 5){
				$.messager.alert("警告","数据最多添加5条");
				return;
			}else{
				$( "#add-dialog" ).dialog("open").dialog('setTitle','添加');
			}
		}else {
			$.messager.alert("错误",ret.msg,"error");
		}
	},"json");
	clearTextBox();
	$('#add-data-form').form('clear');// 清除form表单数据
	new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
	$("#imgShow").attr("src","");
	editor.html("");
	
};

//保存
var save = function(){
	if(!$("#add-data-form").form('validate'))return;
	if(dataOptions.recordType == 2){
		if(editor.html() == '' && $("#teacherId").combobox("getValue") == ''){
			$.messager.alert("错误","图文详情与讲师必填一项");
			return;
		}
	}else if(editor.html() == '' && $("#itemLink").textbox("getValue") == ''){
		$.messager.alert("错误","连接与图文详情必填一项");
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
			var data = ret.data.data;
			$("input[name=id]").val(data.id);
			$("input[textboxName=itemLink]").textbox('setValue',data.itemLink);
			$("input[textboxName=sort]").textbox('setValue',data.sort);
			new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
			$("#imgShow").attr("src",data.logoPath);
			editor.html(data.content);
			//$("input[name=state]").eq([ret.data.data.state]).attr('checked', 'true');
			if(data.teacherId != '') $("#teacherId").combobox("setValue",data.teacherId+"");
			$('#add-dialog').dialog('setTitle','修改');
			$('#add-dialog').dialog('open');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);
};

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

