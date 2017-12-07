<%@page import="com.kapphk.web.utils.ValidateUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/teacher/teacherorder/";
	String uid = ValidateUtils.isBlank(request.getParameter("uid")) ? "" : request.getParameter("uid");
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
		<span>状态：<input type="text" id="state-1" name="state" style="width: 100px"/></span>
		<span style="margin-left: 30px;">预约陪伴师：<input type="text" name="itemName" class="easyui-textbox" style="width: 100px"/></span>
		<span style="margin-left: 30px;">真实姓名：<input type="text" name="realName" class="easyui-textbox" style="width: 100px"/></span>
		<a href="javascript:void(0)" title="查询数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
		<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
		<a href="javascript:void(0)" title="批量删除数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="gridDel()">删除</a>
		<a href="javascript:void(0)" title="批量通过" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="state(1)">审核通过</a>
		<a href="javascript:void(0)" title="批量不通过" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-no'" onclick="state(2)">审核不通过</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 115%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm?recordType=2&type=0&uid=<%=uid %>',loadFilter:loadFilter,nowrap:false,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'itemName',width:8,align:'center'">预约陪伴师</th>
				<th data-options="field:'realName',width:8,align:'center'">用户真实姓名</th>
				<th data-options="field:'nickName',width:8,align:'center'">申请人姓名</th>
				<th data-options="field:'phone',width:10,align:'center'">联系电话</th>
				<th data-options="field:'teachTheme',width:10,align:'center'">预约服务</th>
				<th data-options="field:'teachStart_teachEnd',width:15,align:'center'">开始时间</th>
				<th data-options="field:'address',width:10,align:'center'">陪伴地点</th>
				<th data-options="field:'createTime',width:12,align:'center'">申请时间</th>
				<th data-options="field:'state',width:6,align:'center',formatter:function(value,row,index){
									if(value == 0){
										return '待审核'
									}else if(value == 2){
										return '审核不通过'
									}
				}">状态</th>
				<th data-options="field:'clicknum2',width:8,formatter:EditFormatter,align:'center'">操作</th>
			</tr>
		</thead>
	</table>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" style="width: 850px;height: 650px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改',buttons:[{text:'保存', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="id" />
	<table class="mf-table">
		<tr style="height: 30px;background-color: #EEEEEE"><td colspan="6">申请用户信息</td></tr>
		<tr class="mf-line">
			<td class="mf-left">用户真实姓名</td>
			<td class="mf-right" id="realName"></td>
			<td class="mf-left">注册账号</td>
			<td class="mf-right" id="userName"></td>
			<td class="mf-left">提交时间</td>
			<td class="mf-right" id="createTime"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">申请人姓名</td>
			<td class="mf-right">
				<input type="text" id="nickName" name="nickName" class="easyui-textbox" data-options="required:true" style="width: 120px"/>
			</td>
			<td class="mf-left">联系电话</td>
			<td class="mf-right">
				<input type="text" id="phone" name="phone" class="easyui-textbox" data-options="required:true" style="width: 120px"/>
			</td>
			<td class="mf-left">预约订单号</td>
			<td class="mf-right" id="orderNo"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">陪伴地点</td>
			<td class="mf-right" colspan="5">
				<input type="text" id="address" name="address" class="easyui-textbox" data-options="required:true" style="width: 200px"/>
			</td>
		</tr>
		<tr style="height: 30px;background-color: #EEEEEE"><td colspan="6">申请信息</td></tr>
		<tr class="mf-line">
			<td class="mf-left">讲学主题</td>
			<td class="mf-right" colspan="5">
				<input type="text" id="teachTheme" name="teachTheme" class="easyui-textbox" data-options="required:true" style="width: 200px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">开始时间</td>
			<td class="mf-right" colspan="2">
				<input type="text" id="teachStart" name="teachStart" class="easyui-datebox" data-options="required:true,editable:false" style="width: 200px"/>
			</td>
			<td class="mf-left">结束时间</td>
			<td class="mf-right" colspan="2">
				<input type="text" id="teachEnd" name="teachEnd" class="easyui-datebox" data-options="editable:false" style="width: 200px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">费用</td>
			<td class="mf-right" colspan="5">
				<input type="text" id="orderAmount" name="orderAmount" class="easyui-textbox" style="width: 200px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">备注</td>
			<td class="mf-right" colspan="5">
				<textarea id="remark" name="remark" cols="35" rows="5" class="textarea easyui-validatebox"></textarea>
			</td>
		</tr>
		<tr style="height: 30px;background-color: #EEEEEE"><td colspan="6">审核</td></tr>
		<tr class="mf-line">
			<td class="mf-left">状态</td>
			<td class="mf-right" colspan="5">
				<input type="radio" name="state" value="1" size="10"/>审核通过
				&nbsp;<input type="radio" name="state" value="2" size="10"/>审核不通过
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<!-- 通过审核 -->
<div style="display: none;">
<div id="add-oa" style="width: 450px;height: 500px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改',buttons:[{text:'保存', iconCls:'icon-ok', handler:saveoa},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-oa').dialog('close');}}]">
	<form id="add-data-oa" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="ids" id="ids-oa"/>
	<input type="hidden" name="state" id="state-oa"/>
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">开始时间</td>
			<td class="mf-right">
				<input type="text" id="teachStart1" name="teachStart" class="easyui-datebox" data-options="required:true,editable:false" style="width: 150px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">结束时间</td>
			<td class="mf-right">
				<input type="text" id="teachEnd1" name="teachEnd" class="easyui-datebox" data-options="required:true,editable:false" style="width: 150px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">费用</td>
			<td class="mf-right">
				<input type="text" id="orderAmount1" name="orderAmount" class="easyui-textbox" data-options="required:true" style="width: 150px"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/accompany/teacherOrder.js"></script>
<script type="text/javascript">
	$(function(){
		dataOptions.recordType = 2;
		dataOptions.baseUrl = '<%=thisPath%>';
		dataOptions.basePath = '<%=basePath%>';
		dataOptions.addInit = function(){};
		dataOptions.editInit = function(data){};
	});
</script>
</body>
</html>
