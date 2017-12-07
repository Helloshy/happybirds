<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/community/communitystaff/";
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
		<span>社区身份：<input type="text"  name="tagValue" class="easyui-combobox" data-options="editable:false,multiple:false,valueField:'id',textField:'text',
				url:'<%=basePath %>/web/tag/usertag/searchTagTypeList.htm?tagType=社区身份&type=1'"  style="width: 80px"/></span>
		<span style="margin-left: 20px;">小组名称：<input class="easyui-textBox" type="text" name="itemName" style="width: 100px"/></span>
		<span style="margin-left: 20px;">区长手机号：<input class="easyui-textBox" type="text" name="managerPhone" style="width: 100px"/></span>
		<span style="margin-left: 20px;">管理人员姓名：<input class="easyui-textBox" type="text" name="realName" style="width: 100px"/></span>
		<a href="javascript:void(0)" title="查询数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
		<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
		<a href="javascript:void(0)" title="添加一条数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="gridAdd()">添加</a>
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
				<th data-options="field:'itemName',width:10,align:'center'">小组名称</th>
				<th data-options="field:'managerPhone',width:14,align:'center'">区长手机号</th>
				<th data-options="field:'managerName',width:10,align:'center'">区长姓名</th>
				<th data-options="field:'tagValue',width:10,align:'center'">社区身份</th>
				<th data-options="field:'logoPath',width:10,align:'center',formatter:imageFormatter">头像</th>
				<th data-options="field:'realName',width:10,align:'center'">真实姓名</th>
				<th data-options="field:'userName',width:10,align:'center'">手机号</th>
				<th data-options="field:'createTime',width:10,align:'center'">添加时间</th>
				<th data-options="field:'clicknum2',width:10,formatter:EditFormatter,align:'center'">操作</th>
			</tr>
		</thead>
	</table>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" style="width: 550px;height: 450px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改',buttons:[{text:'保存', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="id" />
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">小组名称</td>
			<td class="mf-right">
				<input type="text" id="communityId" name="communityId" data-options="required:true" style="width: 180px;"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">区长手机号</td>
			<td class="mf-right" id="managerPhone"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">区长姓名</td>
			<td class="mf-right" id="managerName"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">社区身份</td>
			<td class="mf-right">
				<input type="text" id="tagValue" name="tagValue"  class="easyui-combobox" data-options="required:true"  style="width: 180px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">筛选账号</td>
			<td class="mf-right">
				<input type="text" id="userName" name="userName" class="easyui-textbox" style="width: 180px;"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">用户账号</td>
			<td class="mf-right">
				<input type="text" id="uid" name="uid" class="easyui-combobox" data-options="required:true" style="width: 180px;"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>
<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/communitys/communityStaff.js"></script>
<script type="text/javascript">
	$(function(){
	
		$(".combo").click(function(){
			$('#uid').combobox({
	            valueField:'id', //值字段
       			textField:'text', //显示的字段
				url:'<%=thisPath%>'+'getUserList.htm?userName='+$("#userName").textbox("getValue"),
				panelHeight:200,
				multiple:false,
				editable:false
	   		});
		})
		$('#tagValue').combobox({
	            valueField:'id', //值字段
       			textField:'text', //显示的字段
				url:'<%=basePath%>'+'/web/tag/usertag/searchTagTypeList.htm?tagType=社区身份',
				panelHeight:200,
				multiple:false,
				editable:false
	   	});
		$('#communityId').combobox({
				valueField:'id', //值字段
		        textField:'text', //显示的字段
				url:'<%=basePath%>'+'/web/community/community/getCommunityList.htm',
				multiple:false,
				editable:false,
				panelHeight:300,
	            onChange:function(id){
	            	$.post( '<%=basePath%>'+'/web/community/community/data.htm?id='+id,null,function(ret){
	            			if(ret && ret.status == 10001){
	            				var data = ret.data.data;
	            				$("#managerName").text(data.realName);
	            				$("#managerPhone").text(data.userName);
	            			}else{
	            				$.messager.alert("错误",ret.msg,"error");
	            				$("#managerName").text("");
	            				$("#managerPhone").text("");
	            			}
	            	},'json');
	            }
	   	});
		
		dataOptions.baseUrl = '<%=thisPath%>';
		dataOptions.basePath = '<%=basePath%>';
		dataOptions.addInit = function(){};
		dataOptions.editInit = function(data){};
	});
</script>
</body>
</html>
