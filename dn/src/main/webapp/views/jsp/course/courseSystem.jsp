<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/course/coursesystem/";
%>
<jsp:include page="../../include.jsp"></jsp:include> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script src="<%=basePath%>/js/uploadPreview/uploadPreview.js" type="text/javascript"></script>
	<script type="text/javascript">
		//编辑
		var EditFormatter = function(value,row,index){
			return '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" data-options="iconCls:\'icon-edit\'" style="margin-left: 10px;width:60px;" onClick="gridEdit('+index+')">修 改</a>'
					+ '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 10px;width:80px;" onClick="contact('+index+')">报名联系人</a>';
		}
	</script>
</head>
<body>
	<!-- 查询条件panel 一般使用只需修改 标题:title -->
	<div id="gridTools" data-options="border:false" style="padding-top: 10px;">
		<form id="search-form" style="margin-top: 10px;text-align: center;">
		<span>课程名称：<input class="easyui-textBox" type="text" name="itemName" style="width: 100px"/></span>
		<a href="javascript:void(0)" title="查询数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
		<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
		<a href="javascript:void(0)" title="添加一条数据" style="margin-left: 40px;" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="gridAdd()">添加</a>
		<a href="javascript:void(0)" title="批量删除数据" style="margin-left: 40px;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="gridDel()">删除</a>
		<a href="javascript:void(0)" title="导出" style="margin-left: 40px;" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onClick="exportExcel()">导出Excel</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 100%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm',loadFilter:loadFilter,nowrap:false,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'logoPath',width:10,align:'center',formatter:imageFormatter">课程封面</th>
				<th data-options="field:'itemName',width:10,align:'center'">系统课程名称</th>
				<th data-options="field:'itemRemark',width:10,align:'center'">系统课程简介</th>
				<th data-options="field:'amount',width:6,align:'center'">费用</th>
				<th data-options="field:'sort',width:6,align:'center'">课程排序</th>
				<th data-options="field:'isFinancial',width:4,align:'center',formatter:function(value,row,index){
										if(value == 0){
										 	return '否'
										}else{
											return '是'
										}
				}">财商课程</th>
				<th data-options="field:'courses',width:12,align:'center'">课程套餐内容</th>
				<th data-options="field:'counts',width:10,align:'center'">内容数量</th>
				<th data-options="field:'createTime',width:10,align:'center'">添加时间</th>
				<th data-options="field:'clicknum2',width:12,formatter:EditFormatter,align:'center'">操作</th>
			</tr>
		</thead>
	</table>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" style="width: 600px;height: 650px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改',buttons:[{text:'保存', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="id" />
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">课程封面</td>
			<td class="mf-right">
				<div id="imgdiv"><img id="imgShow" width="300" height="150" /></div>
   				 <input type="file" id="up_img" name="file"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">系统课程名称</td>
			<td class="mf-right">
				<input type="text" id="itemName" name="itemName" class="easyui-textbox" data-options="required:true" style="width: 200px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">财商课程</td>
			<td class="mf-right">
				<input type="radio" name="isFinancial" value="0"/>否
				&nbsp;<input type="radio" name="isFinancial" value="1"/>是
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">系统课程简介</td>
			<td class="mf-right">
				<textarea name="itemRemark" id="itemRemark" cols="40" rows="8" class="textarea easyui-validatebox" data-options="required:true"></textarea>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">费用</td>
			<td class="mf-right">
				<input type="text" id="amount" name="amount" class="easyui-textbox" data-options="required:true" style="width: 200px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">课程排序</td>
			<td class="mf-right">
				<input type="text" id="sort" name="sort" class="easyui-textbox" style="width: 200px" />
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">线下课程名称</td>
			<td class="mf-right">
				<input type="text" id="courseOffline" name="courseOffline" class="easyui-combobox" data-options="editable:false,multiple:true,valueField:'id',textField:'text',
				url:'<%=basePath %>/web/course/course/searchCourseList.htm?recordType=1&type=1'"  style="width: 200px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">网络课程名称</td>
			<td class="mf-right">
				<input type="text" id="courseOnline" name="courseOnline" class="easyui-combobox" data-options="editable:false,multiple:true,valueField:'id',textField:'text',
				url:'<%=basePath %>/web/course/course/searchCourseList.htm?recordType=2&type=1'"  style="width: 200px"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/course/courseSystem.js"></script>
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
