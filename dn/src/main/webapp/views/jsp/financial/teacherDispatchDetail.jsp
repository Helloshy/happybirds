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
			<span>讲师名称：<input class="easyui-textbox" type="text" name="itemName" style="width: 120px"/></span>
			<span style="margin-left: 30px;">邀约者：<input class="easyui-textbox" type="text" name="realName" style="width: 120px"/></span>
			<span style="margin-left: 30px;">时间 <input type="text" name="startTime" class="easyui-datetimebox" style="width: 120px"/></span>
			&nbsp;至 <span style="margin-left: 10px;"><input type="text" name="endTime" class="easyui-datetimebox" style="width: 120px"/></span>
			<a href="javascript:void(0)" title="查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
			<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
			<a href="javascript:void(0)" title="导出" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onClick="exportExcel(13)">导出Excel</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 100%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm?type=13',loadFilter:loadFilter,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'itemName',width:8,align:'center'">预约讲师</th>
				<th data-options="field:'teachDate',width:12,align:'center'">讲课日期</th>
				<th data-options="field:'address',width:8,align:'center'">讲课地点</th>
				<th data-options="field:'companyName',width:6,align:'center'">主办单位</th>
				<th data-options="field:'nickName',width:6,align:'center'">主办单位邀约者</th>
				<th data-options="field:'phone',width:8,align:'center'">联系电话</th>
				<th data-options="field:'teachTimes',width:6,align:'center'">讲课场次</th>
				<th data-options="field:'freeTimes',width:6,align:'center'">赠送场次</th>
				<th data-options="field:'actualAmount',width:6,align:'center'">讲课费</th>
				<th data-options="field:'personnel',width:6,align:'center'">陪同人员</th>
				<th data-options="field:'transportation',width:6,align:'center'">交通费</th>
				<th data-options="field:'reason',width:10,align:'center'">赠送原因</th>
				<th data-options="field:'createTime',width:12,align:'center'">更新时间</th>
				<th data-options="field:'ct',width:10,formatter:EditFormatter,align:'center'">操作</th>
			</tr>
		</thead>
	</table>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" style="width: 500px;height: 450px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改',buttons:[{text:'保存', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="id" />
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">赠送场次</td>
			<td class="mf-right">
				<input type="text" id="freeTimes" name="freeTimes" class="easyui-textbox" data-options="required:true" style="width: 180px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">陪同人员</td>
			<td class="mf-right">
				<input type="text" id="personnel" name="personnel" class="easyui-textbox" data-options="required:true" style="width: 180px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">交通费</td>
			<td class="mf-right">
				&nbsp;<input type="radio" name="transportation" value="0"/>我方付
				&nbsp;&nbsp;<input type="radio" name="transportation" value="1"/>对方付
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">赠送原因</td>
			<td class="mf-right">
				<input type="text" id="reason" name="reason" class="easyui-textbox" data-options="required:true" style="width: 180px"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

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
