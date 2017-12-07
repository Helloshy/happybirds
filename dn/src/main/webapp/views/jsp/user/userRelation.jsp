<%@page import="com.kapphk.web.utils.ValidateUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/user/user/";
	String id = ValidateUtils.isBlank(request.getParameter("id")) ? "" : request.getParameter("id");
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
		<span>动能身份：<input class="easyui-combobox" type="text" name="position" data-options="editable:false,multiple:false,valueField:'id',textField:'text',
				url:'<%=basePath %>/web/tag/usertag/searchTagTypeList.htm?tagType=身份&type=1'" style="width: 120px"/></span>
		<span style="margin-left: 20px;">注册账号：<input class="easyui-textBox" type="text" name="userName" style="width: 120px"/></span>
		<span style="margin-left: 20px;">真实姓名：<input class="easyui-textBox" type="text" name="realName" style="width: 120px"/></span>
		<span style="margin-left: 20px;">邀请人姓名：<input class="easyui-textBox" type="text" name="yqRealName" style="width: 120px"/></span>
		<span style="margin-left: 20px;">客户姓名：<input class="easyui-textBox" type="text" name="khRealName" style="width: 120px"/></span>
		<a href="javascript:void(0)" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
		<a href="javascript:void(0)" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
		<a href="javascript:void(0)" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onClick="exportUserRelation(<%=id %>)">导出Excel</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 100%" class="myResize"
		data-options="url:'<%=thisPath %>searchUserRelation.htm?id=<%=id %>',loadFilter:loadFilter,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'userName',width:7,align:'center'">注册账号</th>
				<!-- <th data-options="field:'logoPath',width:8,align:'center',formatter:imageFormatter">头像</th> -->
				<th data-options="field:'realName',width:7,align:'center'">真实姓名</th>
				<th data-options="field:'position',width:7,align:'center'">动能身份</th>
				<th data-options="field:'inviteCode',width:7,align:'center'">邀请码</th>
				<th data-options="field:'yqRealName',width:7,align:'center'">邀请人姓名</th>
				<th data-options="field:'yqInviteCode',width:7,align:'center'">邀请人邀请码</th>
				<th data-options="field:'hierarchy',width:7,align:'center'">分佣层级</th>
				<th data-options="field:'khRealName',width:7,align:'center'">客户姓名</th>
				<th data-options="field:'khInviteCode',width:7,align:'center'">客户邀请码</th>
				<th data-options="field:'createTime',width:10,align:'center'">更新时间</th>
			</tr>
		</thead>
	</table>

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/user/user.js"></script>
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
