<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/system/role/";
%>
<jsp:include page="../../include.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript">
	//编辑
	var EditFormatter = function(value,row,index){
		if("ROLE_SUPER_ADMIN" == row.roleValue)return;
		return '<a id="" href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" data-options="iconCls:\'icon-edit\'" style="margin-left: 10px;width:80px;" onClick="gridEdit('+index+')">修改</a>';
	}
</script>
</head>
<body>

	<!-- 查询条件panel 一般使用只需修改 标题:title -->
	<div id="gridTools" data-options="border:false" style="padding-top: 10px;">
		<form id="search-form" style="margin-top: 10px;text-align: center;">
		    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onClick="gridAdd()">添加</a>
	        <a href="javascript:void(0)" style="margin-left: 50px;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onClick="gridDel()">删除</a> 
		</form>
	</div>
	
	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<div style="margin-top: 10px;">
	<table id="mygrid" style="display: none;" class="myResize"
	      data-options="url:'<%=thisPath %>searchList.htm',loadFilter:loadFilter,
	      fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
	    <thead>
	        <tr>
	        	<th data-options="field:'idx',width:5,align:'center',checkbox:true"></th>
        	    <th data-options="field:'id',width:3,align:'center'">序号</th>
        	    <th data-options="field:'roleName',width:5,align:'center'">角色名称</th>
        	    <th data-options="field:'roleValue',width:5,align:'center'">角色对象</th>
	        	<th data-options="field:'createTime',width:5,align:'center'">创建时间</th>
	        	<th data-options="field:'clicknum',width:10,formatter:EditFormatter,align:'center'">操作</th>
	        </tr>
	    </thead>
	</table>
	</div>

<div style="display: none;">
<div id="add-dialog" style="width: 500px;height: 500px;" class="easyui-dialog" 
	data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,title:'角色',buttons:[{text:'确定', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="id" id="id"/>
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">授权：</td>
			<td class="mf-right">
				<input type="text" id="resourceId" name="resourceId" class="easyui-combotree" style="width: 280px;"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">角色名称：</td>
			<td class="mf-right">
				<input type="text" class="easyui-textbox" name="roleName" data-options="required:true" style="width: 200px;"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>
<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/system/role.js"></script>
<script type="text/javascript">
	$(function(){
	
		dataOptions.baseUrl = '<%=thisPath%>';
		
		dataOptions.basePath = '<%=basePath%>';
		
		dataOptions.addInit = function(){
			
		};
		dataOptions.editInit = function(data){
		};
	});
</script>
</body>
</html>
