<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/financial/financial/";
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
			交易时间  <span ><input type="text" name="startTime" class="easyui-datetimebox" style="width: 120px"/></span>
			&nbsp;至 <span style="margin-left: 20px;"><input type="text" name="endTime" class="easyui-datetimebox" style="width: 120px"/></span>
			<span style="margin-left: 30px;">订单号：<input class="easyui-textbox" type="text" name="orderNo" style="width: 120px"/></span>
			<span style="margin-left: 30px;">用户姓名：<input class="easyui-textbox" type="text" name="realName" style="width: 120px"/></span>
			<a href="javascript:void(0)" title="查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
			<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
			<a href="javascript:void(0)" title="导出" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onClick="exportExcel(1)">导出Excel</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 100%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm?type=1',loadFilter:loadFilter,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'itemName',width:8,align:'center'">交易类型</th>
				<th data-options="field:'orderNo',width:12,align:'center'">交易订单号</th>
				<th data-options="field:'realName',width:8,align:'center'">交易对象姓名</th>
				<th data-options="field:'userName',width:8,align:'center'">手机号</th>
				<th data-options="field:'payAmount',width:8,align:'center'">实收交易金额</th>
				<th data-options="field:'payMethod',width:8,align:'center'">支付方式</th>
				<th data-options="field:'discountBlue',width:6,align:'center'">抵扣蓝币</th>
				<th data-options="field:'discountRed',width:6,align:'center'">抵扣红币</th>
				<th data-options="field:'createTime',width:12,align:'center'">支付时间</th>
			</tr>
		</thead>
	</table>


<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/financial/financial.js"></script>
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
