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
<script type="text/javascript">
	//编辑
	var EditFormatter = function(value,row,index){
		return '<a href="javascript:void(0)" onClick="counts('+index+')">'+row.counts+' 购买人数</a>'
	}
</script>
<body>
	<!-- 查询条件panel 一般使用只需修改 标题:title -->
	<div id="gridTools" data-options="border:false" style="padding-top: 10px;">
		<form id="search-form" style="margin-top: 10px;text-align: center;">
			<span>讲师名称：<input class="easyui-textbox" type="text" name="teacherName" style="width: 120px"/></span>
			<span style="margin-left: 60px;">课程名称：<input class="easyui-textbox" type="text" name="itemName" style="width: 120px"/></span>
			<a href="javascript:void(0)" title="查询数据" style="margin-left: 60px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
			<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 60px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
			<a href="javascript:void(0)" title="导出" style="margin-left: 60px;" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onClick="exportExcel(4)">导出Excel</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 100%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm?type=4',loadFilter:loadFilter,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'orderNo',width:8,align:'center'">课程编号</th>
				<th data-options="field:'itemName',width:12,align:'center'">网络课程名称</th>
				<th data-options="field:'teacherName',width:10,align:'center'">讲师名称</th>
				<th data-options="field:'amount',width:8,align:'center'">课程单价</th>
				<th data-options="field:'totalAmount',width:8,align:'center'">销售总额</th>
				<th data-options="field:'rate',width:6,align:'center'">提成比例</th>
				<th data-options="field:'amountSum',width:6,align:'center'">累计提成金额</th>
				<th data-options="field:'createTime',width:12,align:'center'">更新时间</th>
				<th data-options="field:'counts',width:10,formatter:EditFormatter,align:'center'">操作</th>
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
