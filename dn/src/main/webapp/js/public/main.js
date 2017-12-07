
var webRoot;
var root;
$(function() {
	var pathName = window.document.location.pathname;
	webRoot = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	root = "http://" + window.location.host + "/";

});

//图片格式化
var imageFormatter = function(value,row,index){
	var html ;
	if(value != null && value != "" && value != "null"){
		html = '<img style="width:270px;height:70px;" title="无数据" src="'+value+'"></img>';
	}else{
		html = "--" ;
	}
	return html;
}

//小图片格式化
var littleImageFormatter = function(value,row,index){
	var html ;
	if(value != null && value != "" && value != "null"){
		html = '<img style="width:70px;height:70px;" title="无数据" src="'+value+'"></img>';
	}else{
		html = "--" ;
	}
	return html;
}

var frameSize = 0;
var getContents = function(tempUrl){
	frameSize ++;
	var html = '<iframe src="{0}" id="itemFrames'+frameSize+'" name="itemFrames'+frameSize+'" allowTransparency="true" style="border:0;width:100%;height:86%" frameBorder="0"></iframe>';
	return $.cus.formatString(html,tempUrl);
}

function refreshThis(){
	var id = $('#main_layout').data('myopenid');
	$('#tt').tree('select',$('#tt').tree('find',id).target);
}

/** 编辑 */
var EditFormatter = function(value,row,index){
	return '<a id="" href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" data-options="iconCls:\'icon-edit\'" style="margin-left: 10px;width:80px;" onClick="gridEdit('+index+')">修改</a>';
}

var htmlFormatter = function(value,row,index){
	var html ;
	if(value != null && value != "" && value != "null"){
		html = '<div style="height:35px;">'+value+'</div>';
	}else{
		html = "" ;
	}
	return html;
}

/** 表格加载成功后执行事件 */
var gridLoadSuccess = function(data){
	$(".cu-lbtn").linkbutton({});
    $(this).datagrid("fixRownumber");
}

var errorData = function(data){
	if(data == -1){}
	else if(data == -2) $.messager.alert('错误！','操作失败！','error');
	else if(data == -3) $.messager.alert('错误！','上传图片失败,请选择正确的图片再试！','error');
	else if(data == -4) $.messager.alert('错误！','上传文件失败,请选择正确的文件再试！','error');
	else if(data == -6) $.messager.alert('提示！','连接超时,请重新登录！','error',function(){
		if(parent.window) parent.window.location = base.basePath;
	});
	else if(data < -10){}// 自定义错误处理
	else if(data < 0) $.messager.alert('错误！', data,'error');
}

function loadFilter(data){
  	if(data && data.status == 10001) return data.data;
  	else{
  		$.messager.alert('错误', data.msg,'error');
  		if(data.status == 21001) parent.window.location=base.basePath+'login/login.jsp';
  		var v = {total:0,rows:[]};
  		return v;
  	}
}

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

var numberId = 1;
var judgeFormatter = function(value,row,index){
	if(value == 1 || (value && value.id == 1 )) return '<font color="green">'+judgeData[0].text+'</font>';
    else if(value == 2 || (value && value.id == 2 )) return '<font color="red">'+judgeData[1].text+'</font>';
    else return value.text;
}
var voucherJudgeFormatter = function(value,row,index){
	return value.text;
}
var judgeFormatterProduct = function(value,row,index){
	if(value == 1 || (value && value.id == 1 )) return '<font color="green">'+judgeDataProduct[0].text+'</font>';
    else if(value == 2 || (value && value.id == 2 )) return '<font color="red">'+judgeDataProduct[1].text+'</font>';
    else return value.text;
}
var voucherTypeFormatter = function(value,row,index){
	if(value == 1 || (value && value.id == 1 )) return '<font color="green">'+voucherTypeData[0].text+'</font>';
    else if(value == 2 || (value && value.id == 2 )) return '<font color="red">'+voucherTypeData[1].text+'</font>';
    else if(value == 3 || (value && value.id == 3 )) return voucherTypeData[2].text;
    else if(value == 4 || (value && value.id == 4 )) return '<font color="gray">'+voucherTypeData[3].text+'</font>';
    else return value.text;
}
var redFormatter = function(value,row,index){
	return '<font color="red">'+value+'</font>';
}
var greenFormatter = function(value,row,index){
	return '<font color="green">'+value+'</font>';
}
var blueFormatter = function(value,row,index){
	return '<font color="blue">'+value+'</font>';
}
var judgeFormatterUse = function(value,row,index){
	if(value == 1 || (value && value.id == 1 )) return '<font color="green">'+judgeDataUse[0].text+'</font>';
    else if(value == 2 || (value && value.id == 2 )) return '<font color="red">'+judgeDataUse[1].text+'</font>';
    else return value.text;
}
var clientTypeFormatter = function(value,row,index){
	if(value == 1 || (value && value.id == 1 )) return '<font color="green">'+clientTypeData[0].text+'</font>';
    else if(value == 2 || (value && value.id == 2 )) return '<font color="red">'+clientTypeData[1].text+'</font>';
    else return value.text;
}
var canDelFormatter = function(value,row,index){
	if(value == 1 || (value && value.id == 1 )) return '<font color="green">'+canDelData[0].text+'</font>';
    else if(value == 2 || (value && value.id == 2 )) return '<font color="red">'+canDelData[1].text+'</font>';
    else return value.text;
}

/**
 * 用于调节左边菜单大小时 中间部分控件可以自适应
 * 一.dagagrid自适应
 * 1.需要自动调节的datagrid加上class="myResize"
 * 2.如果dagagrid可以收缩，为处理收缩后调节左边菜单大小后 展开datagrid变形 可以在data-options加上 onBeforeExpand: myResize
 * 二.panel自适应
 * 1.需要自动调节的panel加上myResizePanel
 * @param w
 * @param h
 * @returns
 */
var myResize = function(w, h){
	var mylayouts = $('.myResizelayout');
	$.each(mylayouts,function(i,layout){
		var id = $(layout).attr('id');
		$('#'+id+'').layout('resize');
	});
	
	var mypanels = $('.myResizePanel');
	$.each(mypanels,function(i,panel){
		var id = $(panel).attr('id');
		$('#'+id+'.panel-body').panel('resize');
	});
	
	var mygrids = $('.myResize');
	$.each(mygrids,function(i,grid){
		var id = $(grid).attr('id');
		$('#'+id+'').datagrid('resize');
	});
};
var myResize1 = function(w, h){
};
 var  onIMove = function(left,top){
	 if(left < 0){  
       $(this).dialog('move',{left:0,top:top});  
	 }if(top < 0){  
       $(this).dialog('move',{left:left,top:5});  
 	 } 
 };
 $.fn.dialog.defaults.onMove = onIMove;
 $.fn.window.defaults.onMove = onIMove;