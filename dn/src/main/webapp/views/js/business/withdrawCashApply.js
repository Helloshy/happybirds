var dataOptions = {
	baseUrl:"",
	basePath:"",
	addInit:function(){},// 添加弹窗初始化
	editInit:function(data){},// 修改数据加载完成初始化
	saveInit:function(data){}// 保存前执行操作
};

//处理与到账
var upstatus = function(index){
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
	parent.$.messager.confirm('确认','确认要更新所选数据?',function(r){  
       if (r) asynPAjaxMin(delUrl,postData,success,error);
    });
}

//导出
var exportExcel = function(){
	window.location.href = dataOptions.baseUrl+'exportExcel.htm?'+$('#search-form').serialize();
};

