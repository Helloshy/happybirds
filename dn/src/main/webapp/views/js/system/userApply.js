
var dataOptions = {
	baseUrl:"",
	basePath:"",
	addInit:function(){},// 添加弹窗初始化
	editInit:function(data){},// 修改数据加载完成初始化
	saveInit:function(data){}// 保存前执行操作
};

$(function(){
	$('#recordType').combobox({
		editable:false,
		multiple:false,
		panelHeight:200,
		valueField:'id',
		textField:'text',
		data:[{
			"id":"",
			"text":"全部"
		},{
			"id":"1",
			"text":"动能留学-提交申请"
		},{
			"id":"2",
			"text":"财商课程-合作加盟"
		},{
			"id":"3",
			"text":"动能社区-社区申请"
		},{
			"id":"4",
			"text":"集团介绍-合作加盟"
		},{
			"id":"5",
			"text":"集团介绍-招聘信息"
		}]
	});
	
});


//详情信息
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
			var html = "";
			var recordType = ret.data.data.recordType;
			if(recordType == 1){
				html = "动能留学-提交申请";
			}else if(recordType == 2){
				html = "财商课程-合作加盟";
			}else if(recordType == 3){
				html = "动能社区-社区申请";
			}else if(recordType == 4){
				html = "集团介绍-合作加盟";
			}else if(recordType == 5){
				html = "集团介绍-招聘信息";
			}
			
			$("input[textboxName=recordType]").textbox("setValue",html);
			$('#add-dialog').dialog('open');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);
};

