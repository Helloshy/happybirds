<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/community/community/";
%>
<jsp:include page="../../include.jsp"></jsp:include> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<script src="<%=basePath%>/js/uploadPreview/uploadPreview.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>/pictures/picture.js"></script>
<body>
	<!-- 查询条件panel 一般使用只需修改 标题:title -->
	<div id="gridTools" data-options="border:false" style="padding-top: 10px;">
		<form id="search-form" style="margin-top: 10px;text-align: center;">
		<span>省：<input type="text"  name="province" class="easyui-combobox" data-options="editable:false,multiple:false,valueField:'id',textField:'name',
				url:'<%=basePath %>/web/user/user/pcd.htm?type=0&status=1'" style="width: 120px"/></span>
		<span style="margin-left: 10px;">小组名称：<input class="easyui-textBox" type="text" name="itemName" style="width: 100px"/></span>
		<span style="margin-left: 10px;">区长手机号：<input class="easyui-textBox" type="text" name="userName" style="width: 100px"/></span>
		<span style="margin-left: 10px;">区长姓名：<input class="easyui-textBox" type="text" name="realName" style="width: 100px"/></span>
		<a href="javascript:void(0)" title="查询数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
		<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
		<a href="javascript:void(0)" title="添加一条数据" style="margin-left: 20px;"  class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="gridAdd()">添加</a>
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
				<th data-options="field:'recordType',width:8,align:'center'">小组类别</th>
				<th data-options="field:'district',width:10,align:'center'">服务地区</th>
				<th data-options="field:'logoPath',width:10,align:'center',formatter:imageFormatter">小组图片</th>
				<th data-options="field:'itemName',width:10,align:'center'">小组名称</th>
				<th data-options="field:'userName',width:0,align:'center'">区长手机号</th>
				<th data-options="field:'realName',width:8,align:'center'">区长姓名</th>
				<th data-options="field:'contents',width:8,align:'center'">帖子数</th>
				<th data-options="field:'members',width:8,align:'center'">成员数</th>
				<th data-options="field:'remark',width:10,align:'center',formatter:htmlFormatter">社区介绍</th>
				<th data-options="field:'createTime',width:10,align:'center'">添加时间</th>
				<th data-options="field:'clicknum2',width:10,formatter:EditFormatter,align:'center'">操作</th>
			</tr>
		</thead>
	</table>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" style="width: 630px;height: 670px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改',buttons:[{text:'保存', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="id" />
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">小组类别</td>
			<td class="mf-right">
				<input type="text" id="recordType" name="recordType" style="width: 180px"/>
			</td>
			<td class="mf-left">小组名称</td>
			<td class="mf-right">
				<input type="text" id="itemName" name="itemName" class="easyui-textbox" data-options="required:true" style="width: 180px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">服务地区</td>
			<td class="mf-right" colspan="3">
				<input type="text" name="province" id="province" style="width:100px;" class="easyui-combobox" data-options="required:true"/>省
				<span id="shi"><input type="text" name="city" id="city" style="width:100px;" class="easyui-combobox" data-options="required:true"/>市</span>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">图片：</td>
			<td class="mf-right" colspan="3">
				 <div id="imgdiv"><img id="imgShow" width="200" height="150" /></div>
   				 <input type="file" id="up_img" name="file"/>
			</td>
		</tr>
		<tr class="mf-line" id="sjh">
			<td class="mf-left">区长手机号</td>
			<td class="mf-right">
				<input type="text" id="userName" name="userName" class="easyui-textbox" style="width: 180px"/>
			</td>
			<td class="mf-left">区长姓名</td>
			<td class="mf-right" id="realName"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left" >社区介绍</td>
			<td class="mf-right" colspan="3">
				<textarea cols="55" rows="6" id="remark" name="remark"></textarea>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">社区图片</td>
			<td class="mf-right" colspan="3">
				<div id="imgs" style="width: 400px;"></div>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<div style="display: none;">
<div id="add-picture" style="width: 400px;height: 300px;" class="easyui-dialog" data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,title:'修改',
	buttons:[{text:'保存', iconCls:'icon-ok', handler:savePicture},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-picture').dialog('close');}}]">
	<form id="add-form-picture" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">图片：</td>
			<td class="mf-right" >
				<div style="margin-left: 50px;"><p style="color: red;">Ctrl + 多选</p>
   				<input style="margin-top: 20px;" type="file" name="pictures" multiple="multiple" id="multiple_picture"/></div>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/communitys/community.js"></script>
<script type="text/javascript">
	$(function(){
		$('#userName').textbox({
	            onChange:function(value){
	            	$.post( '<%=thisPath%>'+'getUserDetail.htm?userName='+value,null,function(ret){
	            			if(ret && ret.status == 10001){
	            				var data = ret.data.data;
	            				$("#realName").text(data.realName);
	            			}else{
	            				$.messager.alert("错误",ret.msg,"error");
	            				$("#realName").text("");
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
