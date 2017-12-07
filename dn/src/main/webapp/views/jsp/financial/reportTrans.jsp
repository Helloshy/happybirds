<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/user/reporttrans/";
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
			上传时间  <span ><input type="text" name="startTime" class="easyui-datetimebox" style="width: 120px"/></span>
			&nbsp;至 <span style="margin-left: 20px;"><input type="text" name="endTime" class="easyui-datetimebox" style="width: 120px"/></span>
			<span style="margin-left: 30px;">真实姓名：<input class="easyui-textbox" type="text" name="realName" style="width: 120px"/></span>
			<a href="javascript:void(0)" title="查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
			<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
			<a href="javascript:void(0)" title="导出" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onClick="exportExcel()">导出Excel</a>
			<a href="javascript:void(0)" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onClick="importExcel()">导入Excel</a>
		</form>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 100%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm',loadFilter:loadFilter,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'ym',width:8,align:'center'">月份</th>
				<th data-options="field:'fromPhone',width:8,align:'center'">转出手机号</th>
				<th data-options="field:'fromName',width:12,align:'center'">转出真实姓名</th>
				<th data-options="field:'fromCourse',width:8,align:'center'">转出课程名称</th>
				<th data-options="field:'fromAmount',width:6,align:'center'">转出金额</th>
				<th data-options="field:'toPhone',width:8,align:'center'">转入手机号</th>
				<th data-options="field:'toName',width:12,align:'center'">转入真实姓名</th>
				<th data-options="field:'toCourse',width:8,align:'center'">转入课程名称</th>
				<th data-options="field:'toAmount',width:6,align:'center'">转入金额</th>
				<th data-options="field:'fromOperator',width:6,align:'center'">转出部门经办人</th>
				<th data-options="field:'toOperator',width:6,align:'center'">转入部门经办人</th>
				<th data-options="field:'approveWho',width:6,align:'center'">财务审核人</th>
				<th data-options="field:'createTime',width:12,align:'center'">上传时间</th>
			</tr>
		</thead>
	</table>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" class="easyui-dialog" style="width:400px;height: 350px;" 
	data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,title:'导入',maximizable:true,
	buttons:[{text:'确定', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" target="uploadImgFrame" method="post" enctype="multipart/form-data" style="margin: 0px;padding: 0px;">
		<table class="mf-table">
			<tr class="mf-line"><td class="mf-left">文件：</td>
				<td class="mf-right">
					<input type="file" id="file" name="file"/>
				</td>
			</tr>
		</table>
	</form>
</div>
</div>

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript">
	$(function(){
		dataOptions.baseUrl = '<%=thisPath%>';
		dataOptions.basePath = '<%=basePath%>';
		dataOptions.addInit = function(){};
		dataOptions.editInit = function(data){};
	});
	
	/**导入*/
	var importExcel = function(){
		$('#add-data-form').form('clear');// 清除form表单数据
		$( "#add-dialog" ).dialog("open");
	}
	
	/** 保存*/
	var save = function(){
	var fileName = $("#file").val();
	fileName = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
	if(".xls"!=fileName && ".xlsx"!=fileName){
		$.messager.alert("错误","文件有误!","error");
		return;
	}	
	var check = function(){};
	var url = '<%=thisPath%>'+'importExcel.htm';/* 配置保存数据地址 */
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			$('#add-dialog').dialog('close');
			mygrid.datagrid('reload');
			$('#add-data-form').form('clear');
			$.messager.show({
				title:'提示',
				msg:'上传成功',
				timeout:5000,
				showType:'slide'
			});

		}else {
			parent.$.messager.alert("错误",ret.msg,"error");
		}
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	formSubmit($('#add-data-form'),url,check,success);
};
</script>
</body>
</html>
