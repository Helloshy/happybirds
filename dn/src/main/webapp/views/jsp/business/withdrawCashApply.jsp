<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/user/usercashapply/";
%>
<jsp:include page="../../include.jsp"></jsp:include> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>
	<!-- 查询条件panel 一般使用只需修改 标题:title -->
	<div id="gridTools" data-options="border:false" style="padding-top: 10px;">
		<form id="search-form" style="margin-top: 10px;text-align: center;">
		<span>手机号：<input class="easyui-textBox" type="text" name="userName" style="width: 100px"/></span>
		<span style="margin-left: 20px;">真实姓名：<input class="easyui-textBox" type="text" name="realName" style="width: 100px"/></span>
		<span style="margin-left: 20px;">申请时间：<input type="text" name="startTime" class="easyui-datetimebox" style="width: 100px"/></span> 
		&nbsp;至&nbsp; <span style="margin-left: 20px;"><input type="text" name="endTime" class="easyui-datetimebox" style="width: 100px"/></span>
		<a href="javascript:void(0)" title="查询数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
		<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
		<a href="javascript:void(0)" style="margin-left: 20px;width: 50px;" class="easyui-linkbutton"  onClick="upstatus(1)">已处理</a>
		<a href="javascript:void(0)" style="margin-left: 20px;width: 50px;" class="easyui-linkbutton"  onClick="upstatus(2)">已到账</a>
		<a href="javascript:void(0)" title="导入" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onClick="exportExcel()">导出Excel</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 100%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm',loadFilter:loadFilter,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'userName',width:10,align:'center'">手机号</th>
				<th data-options="field:'realName',width:10,align:'center'">真实姓名</th>
				<th data-options="field:'bankName',width:10,align:'center'">开户银行</th>
				<th data-options="field:'bankInfo',width:10,align:'center'">支行信息</th>
				<th data-options="field:'cardId',width:14,align:'center'">银行卡账号</th>
				<th data-options="field:'cardSignature',width:10,align:'center'">开户人姓名</th>
				<th data-options="field:'amount',width:10,align:'center'">提现金额</th>
				<th data-options="field:'state',width:10,align:'center',formatter:function(value,row,index){
								if(value == 0){
									return '待处理'
								}else if(value == 1){
									return '已处理'
								}else{
									return '已到账'
								}
						
				}">处理状态</th>
				<th data-options="field:'createTime',width:10,align:'center'">申请时间</th>
				<!-- <th data-options="field:'clicknum2',width:10,formatter:EditFormatter,align:'center'">操作</th> -->
			</tr>
		</thead>
	</table>

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/business/withdrawCashApply.js"></script>
<script type="text/javascript">
	$(function(){
		dataOptions.baseUrl = '<%=thisPath%>';
		dataOptions.basePath = '<%=basePath%>';
		dataOptions.addInit = function(){};
		dataOptions.editInit = function(data){};
	});
</script>
</body>
</html>
