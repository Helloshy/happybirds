<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/community/usercommunity/";
%>
<jsp:include page="../../include.jsp"></jsp:include> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<script src="<%=basePath%>/js/uploadPreview/uploadPreview.js" type="text/javascript"></script>
<body>
	<!-- 查询条件panel 一般使用只需修改 标题:title -->
	<div id="gridTools" data-options="border:false" style="padding-top: 10px;">
		<form id="search-form" style="margin-top: 10px;text-align: center;">
		<span>小组名称：<input class="easyui-textBox" type="text" name="itemName" style="width: 100px"/></span>
		<span style="margin-left: 20px;">区长手机号：<input class="easyui-textBox" type="text" name="userName" style="width: 100px"/></span>
		<span style="margin-left: 20px;">成员姓名：<input class="easyui-textBox" type="text" name="realName" style="width: 100px"/></span>
		<a href="javascript:void(0)" title="查询数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
		<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
		<a href="javascript:void(0)" title="批量删除数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="gridDel()">删除</a>
		<a href="javascript:void(0)" title="导出" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onClick="exportExcel()">导出Excel</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 100%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm',loadFilter:loadFilter,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'itemName',width:8,align:'center'">小组名称</th>
				<th data-options="field:'managerPhone',width:10,align:'center'">区长手机号</th>
				<th data-options="field:'managerName',width:8,align:'center'">区长姓名</th>
				<th data-options="field:'tagValue',width:8,align:'center'">社区身份</th>
				<th data-options="field:'logoPath',width:10,align:'center',formatter:imageFormatter">头像</th>
				<th data-options="field:'realName',width:8,align:'center'">真实姓名</th>
				<th data-options="field:'userName',width:10,align:'center'">手机号</th>
				<th data-options="field:'sjRealName',width:8,align:'center'">上级姓名</th>
				<th data-options="field:'sjUserName',width:10,align:'center'">上级手机号</th>
				<th data-options="field:'createTime',width:10,align:'center'">添加时间</th>
			</tr>
		</thead>
	</table>

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
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
