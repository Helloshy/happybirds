var editor;
var dataOptions = {
	baseUrl:"",
	basePath:"",
	recordType:"",
	tagType:"",
	addInit:function(){},// 添加弹窗初始化
	editInit:function(data){},// 修改数据加载完成初始化
	saveInit:function(data){}// 保存前执行操作
};

/*$(function(){
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
});*/

/**添加*/
function gridCustomAdd(){
	clearTextBox();
	dataPicture.init();
	dataPicture.imgs = "";
	$('#add-data-form').form('clear');// 清除form表单数据
	/*$(':file[name^="photo"]').each()*/
	new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
	new uploadPreview({ UpBtn: "up_img1", DivShow: "imgdiv1", ImgShow: "imgShow1"});
	new uploadPreview({ UpBtn: "up_img2", DivShow: "imgdiv2", ImgShow: "imgShow2"});
	new uploadPreview({ UpBtn: "up_img3", DivShow: "imgdiv3", ImgShow: "imgShow3"});
	new uploadPreview({ UpBtn: "up_img4", DivShow: "imgdiv4", ImgShow: "imgShow4"});
	new uploadPreview({ UpBtn: "up_img5", DivShow: "imgdiv5", ImgShow: "imgShow5"});
	$("#imgShow").attr("src","");
	$("#imgShow1").attr("src","");
	$("#imgShow2").attr("src","");
	$("#imgShow3").attr("src","");
	$("#imgShow4").attr("src","");
	$("#imgShow5").attr("src","");
	editor.html("");
	$( "#add-dialog" ).dialog("open").dialog('setTitle','添加');
};

//保存
var save = function(){
	if(!$("#add-data-form").form('validate'))return;
	//验证图片数量
	

	/*if(dataPicture.imgs.split(",").length > 5){
		$.messager.alert("提示","图片不能超过5张");
		return;
	}*/
	var url = dataOptions.baseUrl+'save.htm?imgs='+dataPicture.imgs;/* 配置保存数据地址 */
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
			console.log(data)
			var _photos = data.photos;
			console.log(_photos)
			$("input[name=id]").val(data.id);
            new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
            new uploadPreview({ UpBtn: "up_img1", DivShow: "imgdiv1", ImgShow: "imgShow1"});
            new uploadPreview({ UpBtn: "up_img2", DivShow: "imgdiv2", ImgShow: "imgShow2"});
            new uploadPreview({ UpBtn: "up_img3", DivShow: "imgdiv3", ImgShow: "imgShow3"});
            new uploadPreview({ UpBtn: "up_img4", DivShow: "imgdiv4", ImgShow: "imgShow4"});
            new uploadPreview({ UpBtn: "up_img5", DivShow: "imgdiv5", ImgShow: "imgShow5"});
			$("#imgShow").attr("src",data.logo_path);
			$('#logoPath').val(data.logo_path)
			for (var i=1;i<=_photos.length;i++){
				new uploadPreview({ UpBtn: "up_img"+i, DivShow: "imgdiv"+i, ImgShow: "imgShow"+i });
				$("#imgShow"+i).attr("src",_photos[i-1].logoPath);
				$("input[name=photoId"+i+"]").val(_photos[i-1].photoId);
			}
			$("#categoryId").combobox('setValue',data.categoryId+"");
			$('#rowSeq').textbox('setValue',data.row_seq);
			$("#itemName1").textbox("setValue",data.item_name);
			$("#itemRemark").textbox('setValue',data.item_remark);
			$("#price").textbox('setValue',data.price);
			editor.html(data.item_comment);
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

/**批量删除*/
var offShelves = function(){
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
	var delUrl = dataOptions.baseUrl + 'offShelves.htm';/* 配置下架的地址 */
	var postData = {'ids': ids};/* 请求到服务器的数据内容 */
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			mygrid.datagrid('reload');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null;
	parent.$.messager.confirm('确认','确认要下架所选数据?',function(r){
		if (r) asynPAjaxMin(delUrl,postData,success,error);
	});
};

var gridAddProductInventory = function (rowIndex) {
	
}

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


