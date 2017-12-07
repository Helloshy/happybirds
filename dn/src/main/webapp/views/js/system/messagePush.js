var editor;
$(function(){
	$('#position2').combobox({
		multiple:false,
		panelHeight:200,
		data:[{
			"id":"全部",
			"text":"全部"
		},{
			"id":"员工",
			"text":"员工"
		},{
			"id":"合作伙伴",
			"text":"合作伙伴"
		},{
			"id":"集团学员、其他",
			"text":"集团学员、其他"
		}]
	});
});

/**添加*/
function gridAdd(){
	clearTextBox();
	$('#add-data-form').form('clear');// 清除form表单数据
	editor.html("");
	$( "#add-dialog" ).dialog("open").dialog('setTitle','添加');
};

//查看详情
var gridEdit  = function(rowIndex){
	var rowData = getData(rowIndex);
	var url = dataOptions.baseUrl + 'data.htm';/* 配置查询要修改的数据查询地址 */
	var data = {id:rowData.id}; /* 请求的参数内容 */
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			$('#add-data-form').form('clear');// 清除form表单数据
			myLoad("#add-data-form",ret.data.data);
			editor.html(ret.data.data.content);
			$('#add-dialog').dialog('setTitle','修改').dialog('open');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);
};

$(function(){
	KindEditor.ready(function(K) {
		editor = K.create('textarea[id="content"]', {
			cssPath : webRoot+'/js/kindeditor-4.1.10/plugins/code/prettify.css',
			uploadJson : webRoot+'/js/kindeditor-4.1.10/jsp/upload_json.jsp',
			extraFileUploadParams:{folder:'siteAccessories'},
			resizeType : 1,
			allowFileManager : true,
			allowPreviewEmoticons : false,
			items : [
				'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
				'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
				'insertunorderedlist', '|', 'emoticons', 'image','link','baidumap'
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
}) ;