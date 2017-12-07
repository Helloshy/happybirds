var dataOptions = {
	baseUrl:"",
	basePath:"",
	addInit:function(){},// 添加弹窗初始化
	editInit:function(data){},// 修改数据加载完成初始化
	saveInit:function(data){}// 保存前执行操作
};

$(function(){
	$('#recordType').combobox({
        valueField:'id', //值字段
        textField:'text', //显示的字段
        panelHeight:120,
        editable:false,//不可编辑，只能选择
        multiple:false,
        data:[{
				"id":"1",
				"text":"个人社区"
			},{
				"id":"2",
				"text":"官方社区"
		}],
        onChange:function(value){
        	if(value == 1){
        		pc();
        		$("#sjh").show();
        		$("#xm").show();
        		$("#realName").text("");
        		$("#userName").textbox('setValue',"");
        	}else{
        		province();
        		$("#shi").hide();
        		$("#sjh").hide();
        		$("#xm").hide();
        	}
        }
	});
});

/**添加*/
function gridAdd(){
	clearTextBox();
	$('#add-data-form').form('clear');// 清除form表单数据
	dataPicture.init();
	dataPicture.imgs = "";
	new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
	$("#imgShow").attr("src","");
	$("#userName").textbox('setValue',"");
	$("#recordType").combobox('setValue',"1");
	$("#recordType").combobox('readonly',false);
	$("#userName").textbox('readonly',false);
	$( "#add-dialog" ).dialog("open").dialog('setTitle','添加');
};

//保存
var save = function(){
	var data = $('#add-data-form').form('getData');
	if(data.recordType == 1 && data.userName == ''){
		$.messager.alert("小组类别为个人社区时，手机号必填","error");
		return;
	}else if(dataPicture.imgs.split(",").length > 3){
		$.messager.alert("提示","社区图片不能超过3张");
		return;
	}else if("" == $("#itemName").textbox("getValue")){
		$.messager.alert("提示","小组名称不能为空");
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
var gridEdit = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.baseUrl + 'data.htm';/* 配置查询要修改的数据查询地址 */
	var data = {id:rowData.id}; /* 请求的参数内容 */
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			$('#add-data-form').form('clear');// 清除form表单数据
			var data = ret.data.data;
			if(data.recordType == 1){
				pc();
				$("#sjh").show();
        		$("#xm").show();
        		$("#realName").text("");
        		//$("#userName").textbox('setValue',"");
			}else{
				province();
        		$("#shi").hide();
        		$("#sjh").hide();
        		$("#xm").hide();
			}
			$("input[name=id]").val(data.id);
			$("#itemName").textbox('setValue',data.itemName);
			new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
			$("#imgShow").attr("src",data.logoPath);
			$("#remark").val(ret.data.data.remark);
			$("#recordType").combobox('setValues',data.recordType+"");
			$("#recordType").combobox('readonly');
			if(data.province != '') $("#province").combobox('setValues',data.province.split(","));
			$("#city").combobox('setValue',data.city);
			if(data.recordType == 1) $("#userName").textbox('setValue',data.userName);
			$("#userName").textbox('readonly');
			dataPicture.editPicture(ret.data.data.logoPaths.split(","));
			$('#add-dialog').dialog('setTitle','修改');
			$('#add-dialog').dialog('open');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);
};

//初始化省市
var pc = function(){
	city();
	$("#shi").show();
	$('#province').combobox({
        valueField:'id', //值字段
        textField:'name', //显示的字段
        url:dataOptions.basePath+'/web/user/user/pcd.htm?type=0',
        panelHeight:300,
        editable:false,//不可编辑，只能选择
        multiple:false,
        onChange:function(provinceId){
    			$('#city').combobox({
	                valueField:'id', //值字段
	                textField:'name', //显示的字段
	                url:dataOptions.basePath+'/web/user/user/pcd.htm?type=1&id='+provinceId,
	                panelHeight:300,
	                editable:false,
	                multiple:false
                });
			}
 		});
}

//清空城市
var city = function(){
	$('#city').combobox({
		valueField:'id', //值字段
		textField:'name', //显示的字段
		url:dataOptions.basePath+'/web/user/user/pcd.htm?type=1&id='+"",
		panelHeight:300,
		editable:false,
		multiple:false
	});
}

//初始化省份
var province = function(){
	$('#province').combobox({
		valueField:'id', //值字段
		textField:'name', //显示的字段
		url:dataOptions.basePath+'/web/user/user/pcd.htm?type=0',
		panelHeight:300,
		editable:false,
		multiple:true
	});
}
