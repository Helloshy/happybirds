<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/system/userapply/";
%>
<jsp:include page="../../include.jsp"></jsp:include> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script src="<%=basePath%>/js/uploadPreview/uploadPreview.js" type="text/javascript"></script>
	<script type="text/javascript">
		/** 编辑 */
		var infoFormatter = function(value,row,index){
			return '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 10px;width:80px;" onClick="gridEdit('+index+')">详情信息</a>';
		}
	</script>
</head>
<body>
	<!-- 查询条件panel 一般使用只需修改 标题:title -->
	<div id="gridTools" data-options="border:false" style="padding-top: 10px;">
		<form id="search-form" style="text-align: center;">
		<span>申请来源：<input id="recordType" name="recordType" style="width: 150px"/></span>
		<span style="margin-left: 40px;">姓名：<input class="easyui-textbox" type="text" name="userName" style="width: 150px"/></span>
		<a href="javascript:void(0)" title="查询数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
		<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
		<a href="javascript:void(0)" title="批量删除数据" style="margin-left: 40px;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="gridDel()">删除</a>
		<a href="javascript:void(0)" title="导出" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onClick="exportExcel()">导出Excel</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<div style="margin-top: 10px;">
	<table id="mygrid" style="display: none;width: 100%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm',loadFilter:loadFilter,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'recordType',width:10,align:'center'">申请来源</th>
				<th data-options="field:'userName',width:10,align:'center'">姓名</th>
				<th data-options="field:'userTel',width:10,align:'center'">联系电话</th>
				<th data-options="field:'pcd',width:10,align:'center'">省市区</th>
				<th data-options="field:'content',width:10,align:'center'">申请内容</th>
				<th data-options="field:'createTime',width:10,align:'center'">申请时间</th>
				<th data-options="field:'clicknum2',width:10,formatter:infoFormatter,align:'center'">操作</th>
			</tr>
		</thead>
	</table>
	</div>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" style="width: 500px;height: 500px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'详情信息',buttons:[{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="id" />
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">申请来源</td>
			<td class="mf-right">
				<input type="text" name="recordType" class="easyui-textbox" readonly="readonly" style="width: 250px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">姓名</td>
			<td class="mf-right">
				<input type="text" name="userName" class="easyui-textbox" readonly="readonly" style="width: 250px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">联系电话</td>
			<td class="mf-right">
				<input type="text" name="userTel" class="easyui-textbox" readonly="readonly" style="width: 250px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">省市区</td>
			<td class="mf-right">
				<input type="text" name="pcd" class="easyui-textbox" readonly="readonly"  style="width: 250px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">申请内容</td>
			<td class="mf-right">
				<textarea rows="13" cols="40" name="content" readonly="readonly"></textarea>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">申请时间</td>
			<td class="mf-right">
				<input type="text" name="createTime" class="easyui-textbox" readonly="readonly" style="width: 250px"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/system/userApply.js"></script>
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
