var dataOptions = {
	baseUrl:"",
	basePath:"",
	id:"",
	addInit:function(){},// 添加弹窗初始化
	editInit:function(data){},// 修改数据加载完成初始化
	saveInit:function(data){}// 保存前执行操作
};

$(function(){
	$('#state').combobox({
		editable:false,
		multiple:false,
		panelHeight:130,
		valueField:'id',
		textField:'text',
		data:[{
			"id":"",
			"text":"全部"
		},{
			"id":"1",
			"text":"使用中"
		},{
			"id":"0",
			"text":"暂停使用"
		},{
			"id":"2",
			"text":"待审核"
		},{
			"id":"3",
			"text":"拒绝"
		}]
	});
	$('#isPermissions').combobox({
		editable:false,
		multiple:false,
		panelHeight:100,
		valueField:'id',
		textField:'text',
		data:[{
			"id":"",
			"text":"全部"
		},{
			"id":"1",
			"text":"可以"
		},{
			"id":"0",
			"text":"不可以"
		}]
	});
});

//保存
var save = function(){
	var url = dataOptions.baseUrl + 'save.htm';/* 配置查询要修改的数据查询地址 */
	publicsave(url);
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
			$('#add-data-form').find("input[name=id]").val(data.id);
			new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
			$("#imgShow").attr("src",data.logoPath);
			$("#nickName").textbox("setValue",data.nickName);
			$("#realName").textbox("setValue",data.realName);
			if(data.grPosition != '') $("#grPosition").combobox("setValues",data.grPosition.split(","));
			if(data.dnPosition != '') $("#dnPosition").combobox("setValues",data.dnPosition.split(","));
			if("男" == data.sex){
				$("input[name=sex][value=1]").attr('checked', 'true');
			}else{
				$("input[name=sex][value=2]").attr('checked', 'true');
			}
			$("#sjInviteCode").textbox("setValue",data.sjInviteCode);
			$("#wechat").textbox("setValue",data.wechat);
			$("#mail").textbox("setValue",data.mail);
			$("#dueDate").datebox("setValue",data.dueDate);
			$('#city').combobox({
		        valueField:'id', //值字段
		        textField:'name', //显示的字段
		        url:dataOptions.baseUrl+'pcd.htm?type=1&id='+data.province,
		        panelHeight:300,
		        editable:true
			}); 
			$('#district').combobox({
		        valueField:'id', //值字段
		        textField:'name', //显示的字段
		        url:dataOptions.baseUrl+'pcd.htm?type=2&id='+data.city,
		        panelHeight:300,
		        editable:true
			});
			$('#province').combobox('setValue',data.province);
			$('#city').combobox('setValue',data.city);
			$('#district').combobox('setValue',data.district);
			$('#add-dialog').dialog('setTitle','修改');
			$('#add-dialog').dialog('open');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);
};


//页面跳转
var skip = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.basePath+'/views/jsp/';

	parent.$('#tabs').tabs('add',{
		title:"标题",
		content:'<iframe  style="width:100%;height:90%;" scrolling="auto" frameborder="0" src="'+url+'"></iframe>',
		closable: true
	});
}

//暂停与使用
var upstatus = function(index){
	var title = '停用';
	if(index==1)title = '使用';
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
	
	var delUrl = dataOptions.baseUrl + 'upstatus.htm';/* 配置删除的地址 */
	
	var postData = {'ids': ids,'state':index};/* 请求到服务器的数据内容 */
	/* 请求成功后回调函数success data为请求服务器返回的参数 */
	
	var success = function(ret){
		if(ret && ret.status == 10001){
			mygrid.datagrid('reload');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	
	// 调用此方法进行删除
	parent.$.messager.confirm('确认','确认要'+title+'所选数据?',function(r){  
       if (r) asynPAjaxMin(delUrl,postData,success,error);
    });
}

//详情信息
var detail = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.baseUrl + 'data.htm';/* 配置查询要修改的数据查询地址 */
	var data = {id:rowData.id}; /* 请求的参数内容 */
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			// 初始化
			var data = ret.data.data;
			$('#logoPath1').attr("src",data.logoPath);
			$('#idPhotoFront1').attr("src",data.idPhotoFront);
			$('#idPhotoBack1').attr("src",data.idPhotoBack);
			$('#nickName1').text(data.nickName);
			$('#userName1').text(data.userName);
			$('#realName1').text(data.realName);
			$('#sex1').text(data.sex);
			$('#idCard1').text(data.idCard);
			$('#wechat1').text(data.wechat);
			$('#grPosition1').text(data.grPosition);
			$('#dnPosition1').text(data.dnPosition);
			$('#inviteCode1').text(data.inviteCode);
			$('#uidFrom1').text(data.uidFrom);
			$('#pcd1').text(data.pcd);
			$('#blueCurrency1').text(data.blueCurrency);
			$('#redCurrency1').text(data.redCurrency);
			$('#mail1').text(data.mail);
			var state = '';
				if(data.state == 1){
					state = '使用中' ;
				}else if(data.state == 0){
					state = '暂停使用' ;
				}else if(data.state == 2){
					state = '待审核'
				}else if(data.state == 3){
					state = '拒绝'
				}
			$('#state1').text(state);
			$('#directArea1').text(data.directArea);
			$('#job1').text(data.staffPosition);
			$('#dueDate1').text(data.dueDate);
			$('#createTime1').text(data.createTime);
			$('#add-detail').dialog('open');
			dataOptions.id=rowData.id;
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);
}

//回访记录
var khVisit = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.basePath+'/views/jsp/user/serviceLog.jsp?uid='+rowData.id
	+'&userName='+rowData.kuserName+'&realName='+rowData.krealName;
	parent.$('#tabs').tabs('add',{
		title:"回访记录",
		content:'<iframe  style="width:100%;height:90%;" scrolling="auto" frameborder="0" src="'+url+'"></iframe>',
		closable: true
	});
}

//用户关系跳转
var userRelation = function(){
	var url = dataOptions.basePath+'/views/jsp/user/userRelation.jsp?id='+dataOptions.id;
	parent.$('#tabs').tabs('add',{
		title:"用户关系",
		content:'<iframe  style="width:100%;height:90%;" scrolling="auto" frameborder="0" src="'+url+'"></iframe>',
		closable: true
	});
}

//课程抵扣卷
var coupon = function(){
	var url = dataOptions.basePath+'/views/jsp/course/coupon.jsp?uid='+dataOptions.id;

	parent.$('#tabs').tabs('add',{
		title:"课程抵扣卷",
		content:'<iframe  style="width:100%;height:90%;" scrolling="auto" frameborder="0" src="'+url+'"></iframe>',
		closable: true
	});
}

//分佣明细
var brokerage = function(){
	var url = dataOptions.basePath+'/views/jsp/financial/brokerageDetail.jsp?uid='+dataOptions.id;

	parent.$('#tabs').tabs('add',{
		title:"分佣明细管理",
		content:'<iframe  style="width:100%;height:90%;" scrolling="auto" frameborder="0" src="'+url+'"></iframe>',
		closable: true
	});
}

//课程订单
var offlineOrder = function(){
	var url = dataOptions.basePath+'/views/jsp/order/offlineOrder.jsp?uid='+dataOptions.id;

	parent.$('#tabs').tabs('add',{
		title:"线下订单",
		content:'<iframe  style="width:100%;height:90%;" scrolling="auto" frameborder="0" src="'+url+'"></iframe>',
		closable: true
	});
}

//商城订单
var storeOrder = function(){
	$.messager.alert("提示","该功能未开发");
}

//陪伴与讲学订单
var teacherOrder = function(state){
	var html = "/teachers/teacherOrderCheck.jsp";
	var html2 = "讲学申请订单";
	if(state==2){
		html = "/accompany/teacherOrderCheck.jsp";
		html2 = "陪伴申请订单";
	}
	var url = dataOptions.basePath+'/views/jsp'+html+'?uid='+dataOptions.id;

	parent.$('#tabs').tabs('add',{
		title:html2,
		content:'<iframe  style="width:100%;height:90%;" scrolling="auto" frameborder="0" src="'+url+'"></iframe>',
		closable: true
	});
}

//打开升级身份窗口
var identity = function(){
	$('#add-identity-form').form('clear');// 清除form表单数据
	$("input[textboxName=amount]").textbox("setValue","");
	$( "#add-identity" ).dialog("open");
}

//保存升级身份
var saveIdentity = function(){
	if(!$("#add-identity-form").form('validate'))return;
	var url = dataOptions.baseUrl+'saveIdentity.htm?position=动能集团合作伙伴&id='+dataOptions.id;/* 配置保存数据地址 */
	var check = function(){};

	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			$('#add-identity').dialog('close');
			$('#add-detail').dialog('close');
			mygrid.datagrid('reload');
			$.messager.show({
				title:'提示',
				msg:'升级成功',
				timeout:5000,
				showType:'slide'
			});
		}else {
			parent.$.messager.alert("错误",ret.msg,"error");
		}
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	formSubmit($('#add-identity-form'),url,check,success);
}

//更改蓝币
var currency = function(id){
	$('#add-currency-form').form('clear');// 清除form表单数据
	$('#add-currency-form').find("input[name=id]").val(id);
	$("input[name=type][value=1]").attr('checked', 'true');
	$("#add-currency").dialog("open");
}

//保存更改蓝币
var saveCurrency = function(){
	if(!$("#add-currency-form").form('validate'))return;
	var url = dataOptions.baseUrl+'saveCurrency.htm?id='+dataOptions.id;/* 配置保存数据地址 */
	var check = function(){};

	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			mygrid.datagrid('reload');
			$('#add-currency').dialog('close');
			$.messager.show({
				title:'提示',
				msg:'更改成功',
				timeout:5000,
				showType:'slide'
			});
		}else {
			parent.$.messager.alert("错误",ret.msg,"error");
		}
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	formSubmit($('#add-currency-form'),url,check,success);
}

//拒绝
var check = function(state){
	var sele = mygrid.datagrid("getSelections");
	if(sele.length==0){
		$.messager.alert("错误","至少选择一条数据?")
		return 
	}
	var ids = [];
	for(var i in sele){
		if(sele[i].state != 2){
			$.messager.alert("错误","只能选择待审核用户");
			return;
		}
		ids.push(sele[i].id);
	}
	ids = ids.join(",");
	clearTextBox();
	if(state == 1){
		$("#rejectReason").hide();
		$("#dueDate").show();
	}else if(state == 3){
		$("#dueDate").hide();
		$("#rejectReason").show();
	}
	$('#add-data-form').form('clear');// 清除form表单数据
	$( "#add-dialog" ).dialog("open");
	$( "#add-dialog" ).find("input[name=ids]").val(ids);
	$( "#add-dialog" ).find("input[name=state]").val(state);
}

//保存拒绝  - 通过
var saveState = function(){
	var url = dataOptions.baseUrl+'saveState.htm';/* 配置保存数据地址 */
	if($("#rr").val() == '' && $("input[textboxName=dueDate]").datebox("getValue") == '') {
		$("#add-data-form").form('validate');
		return;
	}
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
}


//添加集团员工
function gridAddStaff(){
	clearTextBox();
	$('#add-data-form').form('clear');// 清除form表单数据
	new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
	$("#imgShow").attr("src","");
	$('#userName1').show();
	$('#userName2').hide();
	$("#ia1").hide();
	$("#ia2").hide();
	$('#add-data-form').find("#inviteCode").text("");
	$("input[name=sex][value=1]").attr('checked', 'true');
	$("input[name=state][value=1]").attr('checked', 'true');
	$("input[name=isPermissions][value=1]").attr('checked', 'true');
	$('input').removeAttr("readonly");//去除input元素的readonly属性
	$( "#add-dialog" ).dialog("open").dialog('setTitle','添加');
};

//修改集团员工
var gridEditStaff = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.baseUrl + 'data.htm';/* 配置查询要修改的数据查询地址 */
	var data = {id:rowData.id}; /* 请求的参数内容 */
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			$('#add-data-form').form('clear');// 清除form表单数据
			// 初始化
			var data = ret.data.data;
			$('#city').combobox({
		        valueField:'id', //值字段
		        textField:'name', //显示的字段
		        url:dataOptions.baseUrl+'pcd.htm?type=1&id='+data.province,
		        panelHeight:300,
		        editable:true
			}); 
			$('#district').combobox({
		        valueField:'id', //值字段
		        textField:'name', //显示的字段
		        url:dataOptions.baseUrl+'pcd.htm?type=2&id='+data.city,
		        panelHeight:300,
		        editable:true
			});
			$('#province').combobox('setValue',data.province);
			$('#add-data-form').find("input[name=id]").val(data.id);
			new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
			$("#imgShow").attr("src",data.logoPath);
			$('#userName1').hide();
			$('#userName2').show().text(data.userName);
			$('#userName').textbox("setValue",data.userName);
			$("input[textboxName=nickName]").textbox("setValue",data.nickName);
			$('#add-data-form').find("input[textboxName=realName]").textbox("setValue",data.realName);
			$('#add-data-form').find("#inviteCode").text(data.inviteCode);
			$("input[textboxName=uidFrom]").combobox("setValue",data.uidFrom);
			if(data.staffPosition.indexOf("区域总经理") == 1){
				$("#ia1").show();
				$("#ia2").show();
			}else{
				$("#ia1").hide();
				$("#ia2").hide();
			}
			$("input[textboxName=idCard]").textbox("setValue",data.idCard);
			if("男" == data.sex)$("input[name=sex][value=1]").attr('checked', 'true');
			else $("input[name=sex][value=2]").attr('checked', 'true');
			$("input[name=state][value="+data.state+"]").attr('checked', 'true');
			$("input[name=isPermissions][value="+data.isPermissions+"]").attr('checked', 'true');
			$("input[textboxName=wechat]").textbox("setValue",data.wechat);
			if(data.directArea != '') $("#directArea").combobox("setValues",data.directArea.split(","));
			if(data.indirectArea != '') $("#indirectArea").combobox("setValues",data.indirectArea.split(","));
			if(data.staffPosition != '') $("#staffPosition").combobox("setValues",data.staffPosition.split(","));
			$('#add-dialog').dialog('setTitle','修改');
			$('#add-dialog').dialog('open');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);
}

//保存集团员工
var saveStaff = function(){
	var url = dataOptions.baseUrl+'saveStaff.htm';/* 配置保存数据地址 */
	publicsave(url);
}

//公用保存
var publicsave = function(url){
	if(!$("#add-data-form").form('validate'))return;
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
}

//课程验证
var isPermissions = function(rowIndex,status){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.baseUrl+'upIsPermissions.htm';/* 配置保存数据地址 */
	var data = {id:rowData.id,isPermissions:status}; /* 请求的参数内容 */
	$.post(url,data,function(ret){
		if(ret && ret.status == 10001){
			mygrid.datagrid('reload');
		}else {
			$.messager.alert("错误",ret.msg,"error");
		}
	},'json');
}



/*****************   导出  *********************************/

//导出学员数据
var exportStudent = function(type){
	window.location.href = dataOptions.baseUrl+'exportStudent.htm?type='+type+"&"+$('#search-form').serialize();;
};

//导出集团员工
var exportStaff = function(){
	window.location.href = dataOptions.baseUrl+'exportStaff.htm?'+$('#search-form').serialize();
};

//导出用户关系
var exportUserRelation = function(id){
	if(id == undefined)id = "";
	window.location.href = dataOptions.baseUrl+'exportUserRelation.htm?id='+ id+"&"+$('#search-form').serialize();
};

//客服管理数据
var exportService = function(){
	window.location.href = dataOptions.baseUrl+'exportService.htm?'+$('#search-form').serialize();
};
