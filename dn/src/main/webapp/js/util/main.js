var judgeData = [{id:1,text:'正常'},{id:2,text:'禁用'}];// 状态
var voucherJudgeData = [{id:1,text:'未兑换'},{id:2,text:'已兑换'},{id:3,text:'已使用'},{id:4,text:'已销毁'}];
var judgeDataProduct = [{id:1,text:'上架中'},{id:2,text:'已下架'}];// 状态
var judgeDataUse = [{id:1,text:'使用中'},{id:2,text:'未使用'}];// 状态
var advTypeData = [{id:1,text:'Banner'},{id:2,text:'商品推荐'}];// 广告位置
var advLinkData = [{id:1,text:'无链接'},{id:2,text:'商城商品'},{id:3,text:'Url链接'}];// 链接类型
var pTypeData = [{id:1,text:'主分类'},{id:2,text:'二级分类'}];// 商城产品分类级别
var canDelData = [{id:1,text:'是'},{id:2,text:'否'}];// 能否删除
var sexData = [{id:1,text:'男'},{id:2,text:'女'}];
var clientTypeData = [{id:1,text:'安卓'},{id:2,text:'苹果'}];
var voucherTypeData = [{id:1,text:'未兑换'},{id:2,text:'已兑换'},{id:3,text:'已使用'},{id:4,text:'已销毁'}];

var statusTypeData = [{id:0,text:'有效'},{id:1,text:'无效'}];

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
/*广告链接类型格式化*/
var advLinkFormatter = function(value,row,index){
	return baseFormatter(value,row,index,advLinkData);
}
/*广告类型格式化*/
var advFormatter = function(value,row,index){
	return baseFormatter(value,row,index,advTypeData);
}
/*广告列表连接内容格式化*/
var advContextFormatter = function(value,row,index){
	if(row.url) return '<a href="http://'+row.url+'" target="_blank">'+row.url+'</a>';
	else if(row.recordId) '<a href="'+base.basePath+'AdvsC/aa.do">'+row.recordId+'</a>';
}
/*商城产品分类级别*/
var ptypeFormatter = function(value,row,index){
	var plab = '<lable class="icon-tag"></lable>';
	if(value == 2 || value.id == 2) plab = '<lable class="icon-tags"></lable>'
	return plab+baseFormatter(value,row,index,pTypeData);
}

/*主格式化*/
var baseFormatter = function(value,row,index, data){
	var id = value;
	if(value.id) id = value.id;
	return data[id-1].text;
}

//------------------- zoneyu -----------------------------------------------------------------------------------
//图片格式化并点击查看大图
var htmlFormatter = function(value,row,index){
	var html ;
	if(value != null && value != "" && value != "null"){
		html = '<div style="width:120px;height:60px;">'+value+'</div>';
	}else{
		html = "--" ;
	}
	return html;
}

//图片格式化
var imageFormatter = function(value,row,index){
	var html ;
	if(value != null && value != "" && value != "null"){
		html = '<img style="width:120px;height:60px;" title="无数据" src="'+value+'"></img>';
	}else{
		html = "--" ;
	}
	return html;
}
//图片格式化并点击查看大图
var imageFormatterWithClick = function(value,row,index){
	var html ;
	if(value != null && value != "" && value != "null"){
		var path = root+"/"+value ;
		html = '<img style="width:120px;height:60px;" title="点击查看大图" src="'+path+'" onclick="visiteLarge(this)"></img>';
	}else{
		html = "--" ;
	}
	return html;
}

//状态
var statusFormatter = function(value,row,index){
	if(value == 0){
		html = '<font color="blue">'+statusTypeData[0].text+'</font>' ;
	}else if(value == 1){
		html = '<font color="red">'+statusTypeData[1].text+'</font>' ;
	}else{
		html = '<font color="red">数据错误</font>' ;
	}
	return html;
}

/**判断是否为空*/
var isBlank = function(str){
	if(str != null && str != '' && str != 'null' && str != 'NULL'){
		return false ;
	}else{
		return true ;
	}
}
//--------------------- zoneyu --------------------------------------------------------------------------------------

// 表格加载成功后执行事件
var gridLoadSuccess = function(data){
	$(".cu-lbtn").linkbutton({});
    $(this).datagrid("fixRownumber");
}
//去除html中的标签
function myextractor(data) {
	var bodyRegEx = /<body[^>]*>((.|[\n\r])*)<\/body>/im;
	var found = bodyRegEx.exec(data);
	if (found) {
		return found[1];
	} else {
		return data;
	}
}
var controlFormatter = function(value,row,index){
	return '<a id="" href="javascript:void(0)" style="margin-left: 10px;" class="easyui-linkbutton" onClick="gridEdit('+index+')"> 修 改 </a>'
	+'<a id="" href="javascript:void(0)" style="margin-left: 10px;" class="easyui-linkbutton" onClick="gridDel('+index+')"> 删 除 </a>';
}
var getData = function(rowIndex){
	mygrid.datagrid('selectRow', rowIndex); 
	return mygrid.datagrid('getSelected');// 取到选择的数据
}
var timeFormatter = function(value,row,index){
	if(value) return $.cus.datelongToStr(value);
	else return '';
}
var cmbSelect = function(record){
	if(record.id == 0) $(this).next().find('.combo-text').css('color','green');
	else $(this).next().find('.combo-text').css('color','red');
}
var derror = function(){
	$.messager.alert('错误','请求服务器失败!','error');
}
shipStatusData=[{id:0,text:'待发货'},{id:1,text:'已发货'}];
var shipStatusFormatter = function(value,row,index){
	if(value == 0 || (value && value.id == 0 )) return '<font color="green">'+shipStatusData[0].text+'</font>';
    else if(value == 1 || (value && value.id == 1 )) return '<font color="red">'+shipStatusData[1].text+'</font>';
    else return value.text;
}

var payStatusData=[{id:0,text:'未付款'},{id:1,text:'已付款'},{id:2,text:'支付失败'}];

var payStatusFormatter = function(value,row,index){
if(value == 0 || (value && value.id == 0 )) return '<font color="blue">'+payStatusData[0].text+'</font>';
    else if(value == 1 || (value && value.id == 1 )) return '<font color="green">'+payStatusData[1].text+'</font>';
    else if(value == 2 || (value && value.id == 2 )) return '<font color="red">'+payStatusData[2].text+'</font>';
    else return value.text;
}
var frameSize = 0;
var getContents = function(tempUrl){
	frameSize ++;
	var html = '<iframe src="{0}" id="itemFrames'+frameSize+'" name="itemFrames'+frameSize+'" allowTransparency="true" style="border:0;width:100%;height:86%" frameBorder="0"></iframe>';
	return $.cus.formatString(html,tempUrl);
}
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
/* 导入模版
   1:学校
*/ 
var exportExcel = function(type){
	if(type) window.location = base.basePath + 'ExcelC/exportExcel.do?type='+type;
}
// 导入数据
var excelType = 1;
function dlgUploadExcel(loadcb, type){
	if(type) excelType = type;
	if($('#dlgUploadExcel').length < 1)$('body').append('<div id="dlgUploadExcel"></div>');
	$('#dlgUploadExcel').dialog({  
    	title: '上传文件',  
    	width: 380,  
    	height: 200,  
    	cache: false,  
    	href: base.basePath+'views/dialog/ExcelDialog.jsp',  
    	modal: true,
    	onLoad: function(){
    		if(loadcb) loadcb();
    	},
		buttons:[
			{
				text:'关闭窗口',
				iconCls:'icon-cancel',
				handler:function(){
					$('#dlgUploadExcel').dialog('close');
				}
			}/* ,{
				text:'放弃',
				iconCls:'icon-cancel',
				handler:function(){
					$('#dlgUploadExcel').dialog('close');
				}
			} */]
	});
	$('#dlgUploadExcel').dialog('open');
}