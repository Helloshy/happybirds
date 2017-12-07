<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/user/servicelog/";
	String uid = request.getParameter("uid");
	String userName = request.getParameter("userName");
	String realName = request.getParameter("realName");
%>
<jsp:include page="../../include.jsp"></jsp:include> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script src="<%=basePath%>/js/uploadPreview/uploadPreview.js" type="text/javascript"></script>
</head>
<body>
	<!-- 查询条件panel 一般使用只需修改 标题:title -->
	<div id="gridTools" data-options="border:false" style="padding-top: 10px;">
		<form id="search-form" style="margin-top: 10px;text-align: center;">
		<span>客户注册账号：<%=userName %></span>
		<span style="margin-left: 40px;">真实姓名：<%=realName %></span>
		<br /><br />
		<span>员工账号：<input class="easyui-textBox" type="text" name="userName" style="width: 120px"/></span>
		<span style="margin-left: 30px;">员工姓名：<input class="easyui-textBox" type="text" name="realName" style="width: 120px"/></span>
		<a href="javascript:void(0)" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
		<a href="javascript:void(0)" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
		<a href="javascript:void(0)" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onClick="gridAdd()">添加</a>
		<a href="javascript:void(0)" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onClick="exportExcel(<%=uid %>)">导出Excel</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 100%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm?uid=<%=uid %>',loadFilter:loadFilter,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'logContent',width:14,align:'center',formatter:htmlFormatter">回访内容</th>
				<th data-options="field:'logDate',width:6,align:'center'">回访时间</th>
				<th data-options="field:'userName',width:6,align:'center'">回访员工账号</th>
				<th data-options="field:'realName',width:10,align:'center'">回访员工姓名</th>
			</tr>
		</thead>
	</table>


<div style="display: none;">
<div id="add-dialog" style="width: 450px;height: 350px;" class="easyui-dialog" data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'添加',buttons:[{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">回访内容</td>
			<td class="mf-right">
				<textarea name="logContent" cols="30" rows="5" class="textarea easyui-validatebox" data-options="required:true"></textarea>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">回访时间</td>
			<td class="mf-right">
				<input type="text" name="logDate" class="easyui-datetimebox" data-options="required:true" style="width: 180px"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>	

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/user/serviceLog.js"></script>
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
