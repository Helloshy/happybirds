<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/financial/financial/";
	String courseId = request.getParameter("cid");
	String itemName = request.getParameter("itemName");
	String teacherName = request.getParameter("teacherName");
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
			<span>网络课程名称：<%=itemName %></span>
			<span style="margin-left: 40px;">讲师名称：<%=teacherName %></span>
			<a href="javascript:void(0)" style="margin-left: 40px;" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onClick="purchaseExcel(<%=courseId %>)">导出Excel</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 100%" class="myResize"
		data-options="url:'<%=thisPath %>searchPurchaseList.htm?courseId=<%=courseId %>',loadFilter:loadFilter,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'userName',width:8,align:'center'">用户手机号</th>
				<th data-options="field:'realName',width:12,align:'center'">真实姓名</th>
				<th data-options="field:'sjRealName',width:8,align:'center'">用户上级</th>
				<th data-options="field:'sjUserName',width:8,align:'center'">上级手机号</th>
				<th data-options="field:'createTime',width:12,align:'center'">购买时间</th>
			</tr>
		</thead>
	</table>


<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/financial/financial.js"></script>
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
