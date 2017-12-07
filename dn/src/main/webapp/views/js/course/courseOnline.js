var editor ;
var dataOptions = {
	baseUrl:"",
	basePath:"",
	status:0,
	addInit:function(){},// 添加弹窗初始化
	editInit:function(data){},// 修改数据加载完成初始化
	saveInit:function(data){}// 保存前执行操作
};

$(function(){
	$('#courseGroup-g').combobox({
		multiple:false,
		editable:false,
		panelHeight:150,
		valueField:'id',
		textField:'text',
		data:[{
			"id":"",
			"text":"全部"
		},{
			"id":"孩子课程",
			"text":"孩子课程"
		},{
			"id":"父母课程",
			"text":"父母课程"
		},{
			"id":"精英课程",
			"text":"精英课程"
		}]
	});
	
	
});

/**添加*/
function gridAdd(){
	clearTextBox();
	$('#add-data-form').form('clear');// 清除form表单数据
	new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
	$("#imgShow").attr("src","");
	new uploadPreview({ UpBtn: "up_img1", DivShow: "imgdiv1", ImgShow: "imgShow1" });
	$("#imgShow1").attr("src","");
	$("input[name=courseGroup][value=孩子课程]").attr('checked', 'true');
	$("input[name=linkType][value=1]").attr('checked', 'true');
	$("input[name=courseType]")[0].checked="checked";
	$("input[name=isPublic]")[1].checked="checked";
	$("input[name=isFinancial]")[0].checked="checked";
	editor.html("");
	$( "#add-dialog" ).dialog("open").dialog('setTitle','添加');
};

//保存
var save = function(){
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
			dataOptions.status = -1;
			$('#add-data-form').form('clear');// 清除form表单数据
			// 初始化
			var data = ret.data.data;
			$("#teacherId").combobox("setValue",data.teacherId+"");
			$('#add-data-form').find("input[name=id]").val(data.id);
			$("input[name=courseGroup][value="+data.courseGroup+"]").attr('checked', 'true');
			$("input[name=courseType][value="+data.courseType+"]").attr('checked', 'true');
			$("input[name=isPublic][value="+data.isPublic+"]").attr('checked', 'true');
			$("input[name=isFinancial][value="+data.isFinancial+"]").attr('checked', 'true');
			$("input[name=linkType][value="+data.linkType+"]").attr('checked', 'true');
			$("#itemName").textbox("setValue",data.itemName);
			$("#itemRemark").val(data.itemRemark);
			$("#amount").textbox("setValue",data.amount);
			$("#vedioLink").val(data.vedioLink);
			new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
			$("#imgShow").attr("src",data.logoPath);
			new uploadPreview({ UpBtn: "up_img1", DivShow: "imgdiv1", ImgShow: "imgShow1" });
			$("#teacherName").textbox("setValue",data.teacherName);
			$("#sort").textbox("setValue",data.sort);
			$("#teacherRemark").val(data.teacherRemark);
			$("#imgShow1").attr("src",data.teacherLogoPath);
			$("#rate").textbox("setValue",data.rate);
			$("#ct").combobox("setValues", data.ct == '' ? '' : (data.ct+"").split(","));
			editor.html(data.courseContent);
			$('#add-dialog').dialog('setTitle','修改');
			$('#add-dialog').dialog('open');
			$('#add-data-form').form("validate");
			dataOptions.status = 0;
		}else{
			parent.$.messager.alert("错误",ret.msg,"error");
			dataOptions.status = 0;
		}
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);
};

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
	parent.$.messager.confirm('确认','确认要删除所选数据?有可能关联的系统课程也会删除',function(r){  
       if (r) asynPAjaxMin(delUrl,postData,success,error);
    });
};

/**
 * 编辑器创建
 */
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

/** 导出 */
var exportExcel = function(teacherId){
	window.location.href = dataOptions.baseUrl+'exportExcel.htm?teacherId='+teacherId+"&"+$('#search-form').serialize();;
};

