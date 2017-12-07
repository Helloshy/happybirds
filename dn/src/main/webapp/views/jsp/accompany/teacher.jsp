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
			 + '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 20px;width:70px;" onClick="arrange('+index+')">预约时间</a>'
			 + '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 20px;width:50px;" onClick="comment('+index+')">评 价</a>';
		}
		var hFormatter = function(value,row,index){
			var html = "是";
			var ind = 0;
			if(row.isHome == 0){
				html = '否';
				ind = 1;
			}
			return '<a href="javascript:void(0)" onClick="ishome('+index+','+ind+')">'+html+'</a>';
		}
	</script>
</head>
<body>
	<!-- 查询条件panel 一般使用只需修改 标题:title -->
	<div id="gridTools" data-options="border:false" style="padding-top: 10px;">
		<form id="search-form" style="margin-top: 10px;text-align: center;">
		<span>星级：<input type="text" name="teacherLevel" class="easyui-combobox" data-options="editable:false,multiple:false,valueField:'id',textField:'text',
				url:'<%=basePath %>/web/teacher/teacherlevel/searchLevelList.htm?recordType=2&type=1'" style="width: 100px"/></span>
		<span style="margin-left: 30px;">学科：<input type="text" name="tagValue" class="easyui-combobox" data-options="editable:false,multiple:false,valueField:'id',textField:'text',
				url:'<%=basePath %>/web/tag/usertag/searchTagTypeList.htm?tagType=学科特长&type=1'" style="width: 110px"/></span>
		<span style="margin-left: 30px;">陪伴师名称：<input type="text" name="itemName" class="easyui-textbox" style="width: 100px"/></span>
		<a href="javascript:void(0)" title="查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
		<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
		<a href="javascript:void(0)" title="添加一条数据" style="margin-left: 40px;" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="gridAdd()">添加</a>
		<a href="javascript:void(0)" title="批量删除数据" style="margin-left: 40px;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="gridDel()">删除</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 115%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm?recordType=2',loadFilter:loadFilter,nowrap:false,pageNumber:1,
        pageSize:10,pageList:[10,20],fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'teacherLevel',width:10,align:'center'">星级</th>
				<th data-options="field:'realName',width:10,align:'center'">陪伴师名称</th>
				<th data-options="field:'userName',width:10,align:'center',formatter:htmlFormatter">陪伴师账号</th>
				<th data-options="field:'logoPath1',width:10,align:'center',formatter:imageFormatter">陪伴师头像</th>
				<th data-options="field:'tagValue',width:10,align:'center'">擅长学科</th>
				<th data-options="field:'isHome',width:6,align:'center',formatter:hFormatter">首页推荐</th>
				<th data-options="field:'sort',width:6,align:'center'">排序</th>
				<th data-options="field:'itemContent',width:10,align:'center',formatter:htmlFormatter">服务介绍</th>
				<th data-options="field:'createTime',width:10,align:'center'">更新时间</th>
				<th data-options="field:'clicknum2',width:16,formatter:EditFormatter,align:'center'">操作</th>
			</tr>
		</thead>
	</table>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" style="width: 800px;height: 600px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改',buttons:[{text:'保存', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="id" />
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">陪伴师名称</td>
			<td class="mf-right">
				<input type="text" name="uid" id="uid" style="width: 200px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">手机号</td>
			<td class="mf-right" id="userName"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">星级</td>
			<td class="mf-right">
				<input type="text" name="teacherLevel" class="easyui-combobox" data-options="required:true,editable:false,multiple:false,valueField:'id',textField:'text',
				url:'<%=basePath %>/web/teacher/teacherlevel/searchLevelList.htm?recordType=2'" style="width: 200px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">学科</td>
			<td class="mf-right">
				<input type="text" name="tagValue" class="easyui-combobox" data-options="separator:'、',required:true,editable:false,multiple:true,valueField:'id',textField:'text',
				url:'<%=basePath %>/web/tag/usertag/searchTagTypeList.htm?tagType=学科特长'" style="width: 200px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">排序</td>
			<td class="mf-right">
				<input type="text" id="sort" name="sort" class="easyui-textbox" style="width: 200px" />
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">服务介绍</td>
			<td class="mf-right">
				<textarea style="width:100%;height:250px;" id="itemContent" name="itemContent"></textarea>
				<a href="javascript:void(0)" title="添加视频" style="float: left;margin-top: 10px;" class="easyui-linkbutton" onclick="gridAddVideo()">添加视频</a>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-ta" style="width: 400px;height: 350px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'预约时间',buttons:[{text:'保存', iconCls:'icon-ok', handler:saveTa},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-ta').dialog('close');}}]">
	<form id="add-ta-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="id" />
	<input type="hidden" name="teacherId" />
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">开始时间</td>
			<td class="mf-right">
				<input type="text" id="arrangeDate" name="arrangeDate" class="easyui-datebox" data-options="required:true" style="width: 160px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">结束时间</td>
			<td class="mf-right">
				<input type="text" id="endDate" name="endDate" class="easyui-datebox" data-options="required:true" style="width: 160px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">状态</td>
			<td class="mf-right">
				<input type="radio" name="state" value="0" size="30"/>不可预约
				&nbsp;<input type="radio" name="state" value="1" size="30"/>可预约
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
		$('#uid').combobox({
			required:true,
			editable:false,
			multiple:false,
			valueField:'id',
			textField:'text',
			url:'<%=basePath %>/web/user/user/searchUserList.htm?staffPosition=动能陪伴师',
	        onChange:function(id){
	            	$.post( '<%=basePath%>'+'/web/user/user/data.htm?id='+id,null,function(ret){
	            			if(ret && ret.status == 10001){
	            				var data = ret.data.data;
	            				$("#userName").text(data.userName);
	            			}
	            	},'json');
	       }
	   	});
		dataOptions.recordType = 2;
		dataOptions.baseUrl = '<%=thisPath%>';
		dataOptions.basePath = '<%=basePath%>';
		dataOptions.addInit = function(){};
		dataOptions.editInit = function(data){};
	});
</script>
</body>
</html>
