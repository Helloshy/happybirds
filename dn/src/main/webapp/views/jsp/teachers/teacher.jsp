<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/teacher/teacher/";
%>
<jsp:include page="../../include.jsp"></jsp:include> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script src="<%=basePath%>/js/uploadPreview/uploadPreview.js" type="text/javascript"></script>
	<script type="text/javascript">
		//编辑
		var EditFormatter = function(value,row,index){
			return '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 5px;width:50px;" onClick="gridEdit('+index+')">修 改</a>'
			 + '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 20px;width:50px;" onClick="comment('+index+')">评 价</a>';
		}
	
		//网络课程
		var onlineFormatter = function(value,row,index){
			return '<a href="javascript:void(0)" onClick="onlines('+index+')">'+(value == '' ? 0 : value)+'</a>';
		}
	</script>
</head>
<body>
	<!-- 查询条件panel 一般使用只需修改 标题:title -->
	<div id="gridTools" data-options="border:false" style="padding-top: 10px;">
		<form id="search-form" style="margin-top: 10px;text-align: center;">
		<span>级别：<input type="text" name="teacherLevel" class="easyui-combobox" data-options="editable:false,multiple:false,valueField:'id',textField:'text',
				url:'<%=basePath %>/web/teacher/teacherlevel/searchLevelList.htm?recordType=1&type=1'" style="width: 110px"/></span>
		<span style="margin-left: 30px;">区域：<input type="text" name="district" class="easyui-combobox" data-options="editable:false,multiple:false,valueField:'id',textField:'text',
				url:'<%=basePath %>/web/tag/usertag/searchTagTypeList.htm?tagType=区域管理&type=1'" style="width: 110px"/></span>
		<span style="margin-left: 30px;">讲师名称：<input type="text" name="itemName" class="easyui-textbox" style="width: 150px"/></span>
		<a href="javascript:void(0)" title="查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
		<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
		<a href="javascript:void(0)" title="添加一条数据" style="margin-left: 40px;" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="gridAdd()">添加</a>
		<a href="javascript:void(0)" title="批量删除数据" style="margin-left: 40px;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="gridDel()">删除</a>
		<a href="javascript:void(0)" title="导出" style="margin-left: 40px;" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onClick="exportExcel()">导出Excel</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 115%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm?recordType=1',loadFilter:loadFilter,nowrap:false,pageNumber:1,
        pageSize:10,pageList:[10,20],fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'district',width:10,align:'center'">区域</th>
				<th data-options="field:'teacherLevel',width:10,align:'center'">级别</th>
				<th data-options="field:'itemName',width:10,align:'center'">讲师名称</th>
				<th data-options="field:'logoPath',width:10,align:'center',formatter:imageFormatter">头像</th>
				<th data-options="field:'itemRemark',width:10,align:'center',formatter:htmlFormatter">讲师简介</th>
				<th data-options="field:'itemTheme',width:10,align:'center'">擅长主题</th>
				<th data-options="field:'itemContent',width:10,align:'center',formatter:htmlFormatter">课程介绍</th>
				<th data-options="field:'onlines',width:6,align:'center',formatter:onlineFormatter">网络课程</th>
				<th data-options="field:'sort',width:6,align:'center'">排序</th>
				<th data-options="field:'createTime',width:10,align:'center'">更新时间</th>
				<th data-options="field:'clicknum2',width:13,formatter:EditFormatter,align:'center'">操作</th>
			</tr>
		</thead>
	</table>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" style="width: 900px;height: 600px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改',buttons:[{text:'保存', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="id" />
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">区域</td>
			<td class="mf-right">
				<input type="text" id="district" name="district" class="easyui-combobox" data-options="required:true,editable:false,multiple:true,valueField:'id',textField:'text',
				url:'<%=basePath %>/web/tag/usertag/searchTagTypeList.htm?tagType=区域管理'" style="width: 200px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">级别</td>
			<td class="mf-right">
				<input type="text" id="teacherLevel" name="teacherLevel" class="easyui-combobox" data-options="required:true,editable:false,multiple:false,valueField:'id',textField:'text',
				url:'<%=basePath %>/web/teacher/teacherlevel/searchLevelList.htm?recordType=1'" style="width: 200px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">讲师名称</td>
			<td class="mf-right">
				<input type="text" id="itemName" name="itemName" class="easyui-textbox" data-options="required:true" style="width: 200px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">头像</td>
			<td class="mf-right">
				<div id="imgdiv"><img id="imgShow" width="200" height="130" /></div>
   				 <input type="file" id="up_img" name="file"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">讲师简介</td>
			<td class="mf-right">
				<textarea name="itemRemark" cols="30" rows="5" class="textarea easyui-validatebox" data-options="required:true"></textarea>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">擅长主题</td>
			<td class="mf-right">
				<input type="text" id="itemTheme" name="itemTheme" class="easyui-textbox" data-options="required:true" style="width: 200px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">排序</td>
			<td class="mf-right">
				<input type="text" id="sort" name="sort" class="easyui-textbox" style="width: 200px" />
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">课程介绍</td>
			<td class="mf-right">
				<textarea style="width:100%;height:250px;" id="itemContent" name="itemContent"></textarea>
				<a href="javascript:void(0)" title="添加视频" style="float: left;margin-top: 10px;" class="easyui-linkbutton" onclick="gridAddVideo()">添加视频</a>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/teacher/teacher.js"></script>
<script type="text/javascript">
	$(function(){
		dataOptions.recordType = 1;
		dataOptions.baseUrl = '<%=thisPath%>';
		dataOptions.basePath = '<%=basePath%>';
		dataOptions.addInit = function(){};
		dataOptions.editInit = function(data){};
	});
</script>
</body>
</html>
