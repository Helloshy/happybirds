var dataOptions = {
	baseUrl:"",
	basePath:"",
	addInit:function(){},// 添加弹窗初始化
	editInit:function(data){},// 修改数据加载完成初始化
	saveInit:function(data){}// 保存前执行操作
};

$(function(){
	$('#g-recordType').combobox({
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
			"text":"公告"
		},{
			"id":"2",
			"text":"动态"
		}]
	});
	$('#g-isReport').combobox({
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
});

/**添加*/
function gridAdd(){
	clearTextBox();
	dataPicture.init();
	dataPicture.imgs = "";
	$('#add-data-form').form('clear');// 清除form表单数据
	$('#add-data-form').find("input[name=recordType][value=2]").attr('checked', 'true');
	$( "#add-dialog" ).dialog("open").dialog('setTitle','添加');
};

//保存
var save = function(){
	if(!$("#add-data-form").form('validate'))return;
	if(dataPicture.imgs.split(",").length > 3){
		$.messager.alert("提示","图片不能超过3张");
		return;
	}
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
var detail = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.baseUrl + 'data.htm';/* 配置查询要修改的数据查询地址 */
	var data = {id:rowData.id}; /* 请求的参数内容 */
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			var data = ret.data.data;
			$("#itemName1").text(data.itemName);
			$("#realName1").text(data.realName);
			$("#nickName1").text(data.nickName);
			$("#userName1").text(data.userName);
			$("#logoPath1").html('&nbsp;<img src="'+data.logoPath+'" class="img" style="vertical-align: middle;"/>');
			$("#iconValue1").html('&nbsp;<img src="/dn'+data.iconValue+'" class="img" style="vertical-align: middle;"/>');
			$("#recordType1").text(data.recordType);
			$("#isReport1").text(data.isReport);
			$("#likes1").text(data.likes);
			$("#comments1").text(data.comments);
			$("#createTime1").text(data.createTime);
			$("#content1").val(data.content);
			var logoPaths = "";
			if(data.logoPath1 != ''){
				logoPaths += '&nbsp;<img src="'+data.logoPath1+'" width="120px;"/>';
				if(data.logoPath2 != ''){
					logoPaths += '&nbsp;&nbsp;<img src="'+data.logoPath2+'" width="120px;"/>';
					if(data.logoPath3 != ''){
						logoPaths += '&nbsp;&nbsp;<img src="'+data.logoPath3+'" width="120px;"/>';
					}
				}
			}
			var html = "";
			var commentList = data.commentList;
			for ( var i = 0; i < commentList.length; i++) {
				html += '<tr><td width="9%">'+commentList[i].realName+':</td><td width="*">';
				html += commentList[i].content+'</td><th width="15%">'+commentList[i].createTime;
				html += '</th><th width="15%"><a onclick="deleteComment(this,'+commentList[i].id;
				html += ')" href="javascript:void(0)">删除</a></th></tr>';
			}
			$("#comment").html(html);
			$("#logoPaths1").html(logoPaths);
			$('#add-dialog1').dialog('open');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);
};

var deleteComment = function(obj,id){
	var delUrl = dataOptions.baseUrl + 'deleteComment.htm';/* 配置删除的地址 */
	var postData = {'id': id};/* 请求到服务器的数据内容 */
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			$(obj).parent().parent().remove();
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null;
	parent.$.messager.confirm('确认','确认要删除所选评论?',function(r){  
       if (r) asynPAjaxMin(delUrl,postData,success,error);
    });
}

