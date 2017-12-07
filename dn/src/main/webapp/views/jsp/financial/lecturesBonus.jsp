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
			更新时间  <span ><input type="text" name="startTime" class="easyui-datetimebox" style="width: 100px"/></span>
			&nbsp;至 <span style="margin-left: 10px;"><input type="text" name="endTime" class="easyui-datetimebox" style="width: 100px"/></span>
			<span style="margin-left: 30px;">讲师名称：<input class="easyui-textbox" type="text" name="itemName" style="width: 120px"/></span>
			<span style="margin-left: 30px;">邀约者：<input class="easyui-textbox" type="text" name="realName" style="width: 120px"/></span>
			<a href="javascript:void(0)" title="查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
			<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
			<a href="javascript:void(0)" title="导出" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onClick="exportExcel(6)">导出Excel</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 100%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm?type=6',loadFilter:loadFilter,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id',width:8,align:'center'">预约订单号</th>
				<th data-options="field:'itemName',width:12,align:'center'">讲师名称</th>
				<th data-options="field:'level',width:8,align:'center'">讲师级别</th>
				<th data-options="field:'rate',width:8,align:'center'">提成比例</th>
				<th data-options="field:'date',width:12,align:'center'">讲课时间</th>
				<th data-options="field:'address',width:8,align:'center'">讲课地点</th>
				<th data-options="field:'companyName',width:6,align:'center'">主办单位</th>
				<th data-options="field:'nickName',width:6,align:'center'">主办单位邀约者</th>
				<th data-options="field:'phone',width:8,align:'center'">联系电话</th>
				<th data-options="field:'teachTimes',width:6,align:'center'">讲课场次</th>
				<th data-options="field:'actualAmount',width:6,align:'center'">实收讲课费</th>
				<th data-options="field:'amount',width:6,align:'center'">提成金额</th>
				<th data-options="field:'createTime',width:12,align:'center'">更新时间</th>
			</tr>
		</thead>
	</table>


<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/order/offlineOrder.js"></script>
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
