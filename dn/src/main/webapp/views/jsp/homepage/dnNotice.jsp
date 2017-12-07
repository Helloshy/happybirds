<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/homepage/appnews/";
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
		<span>分类名称：<input type="text" name="newsTag" class="easyui-combobox" data-options="editable:false,multiple:false,valueField:'id',textField:'text',
				url:'<%=basePath %>/web/tag/usertag/searchTagTypeList.htm?tagType=公告分类&type=1'" style="width: 100px"/></span>
		<span style="margin-left: 30px;">标题：<input type="text" name="title" id="title-g" class="easyui-textbox" style="width: 130px"/></span>
		<a href="javascript:void(0)" title="查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
		<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
		<a href="javascript:void(0)" title="添加一条数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="gridAdd()">添加</a>
		<a href="javascript:void(0)" title="批量删除数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="gridDel()">删除</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 100%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm?recordType=5',loadFilter:loadFilter,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'newsTag',width:10,align:'center'">分类名称</th>
				<th data-options="field:'logoPath',width:10,align:'center',formatter:imageFormatter">图片</th>
				<th data-options="field:'title',width:10,align:'center'">标题</th>
				<th data-options="field:'content',width:10,align:'center',formatter:htmlFormatter">图文详情</th>
				<th data-options="field:'createTime',width:10,align:'center'">更新时间</th>
				<th data-options="field:'clicknum2',width:10,formatter:EditFormatter,align:'center'">操作</th>
			</tr>
		</thead>
	</table>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" style="width: 600px;height: 670px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改',buttons:[{text:'保存', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="id" />
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">分类名称</td>
			<td class="mf-right">
				<input type="text" id="newsTag" name="newsTag" class="easyui-combobox" data-options="required:true,editable:false,multiple:false,valueField:'id',textField:'text',
				url:'<%=basePath %>/web/tag/usertag/searchTagTypeList.htm?tagType=公告分类'" style="width: 200px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">图片</td>
			<td class="mf-right">
				 <div id="imgdiv"><img id="imgShow" width="250" height="200" /></div>
   				 <input type="file" id="up_img" name="file"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">标题</td>
			<td class="mf-right">
				<input type="text" id="title" name="title" class="easyui-textbox" data-options="required:true" style="width: 250px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">图文详情</td>
			<td class="mf-right">
				<textarea style="width:98%;height:250px;" id="content" name="content"></textarea>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/homepage/news.js"></script>
<script type="text/javascript">
	$(function(){
		dataOptions.recordType = '5';
		dataOptions.baseUrl = '<%=thisPath%>';
		dataOptions.basePath = '<%=basePath%>';
		dataOptions.addInit = function(){};
		dataOptions.editInit = function(data){};
	});
</script>
</body>
</html>
