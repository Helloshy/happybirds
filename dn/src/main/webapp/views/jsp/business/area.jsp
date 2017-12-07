<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/tag/apparea/";
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
		<a href="javascript:void(0)" title="添加一条数据" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="gridAdd()">添加</a>
		<a href="javascript:void(0)" title="批量删除数据" style="margin-left: 60px;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="gridDel()">删除</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 100%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm',loadFilter:loadFilter,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id',width:10,align:'center'">区域名称</th>
				<th data-options="field:'areaDetail',width:14,align:'center'">省份</th>
				<th data-options="field:'createTime',width:10,align:'center'">修改时间</th>
				<th data-options="field:'clicknum2',width:10,formatter:EditFormatter,align:'center'">操作</th>
			</tr>
		</thead>
	</table>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" style="width: 400px;height: 350px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改',buttons:[{text:'保存', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="oldId" />
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">区域名称</td>
			<td class="mf-right" >
				<input type="text" name="id" class="easyui-textbox" data-options="required:true" style="width: 180px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">省份</td>
			<td class="mf-right" >
				<input type="text" name="areaDetail" class="easyui-combobox" data-options="required:true,editable:false,multiple:true,valueField:'id',textField:'name',
				url:'<%=basePath %>/web/user/user/pcd.htm?type=0'" style="width: 180px"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/business/area.js"></script>
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