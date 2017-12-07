<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/teacher/teachercomment/";
	String teacherId = request.getParameter("teacherId");
%>
<jsp:include page="../../include.jsp"></jsp:include> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>
	<div id="gridTools" data-options="border:false" style="padding-top: 10px;"></div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 100%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm?teacherId=<%=teacherId %>',loadFilter:loadFilter,nowrap:false,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'orderId',width:15,align:'center'">预约订单号</th>
				<th data-options="field:'content',width:10,align:'center'">评价内容</th>
				<th data-options="field:'star',width:6,align:'center'">评价星级</th>
				<th data-options="field:'userName',width:10,align:'center'">评价账号</th>
				<th data-options="field:'realName',width:6,align:'center'">真实姓名</th>
				<th data-options="field:'createTime',width:10,align:'center'">评价时间</th>
			</tr>
		</thead>
	</table>
	
	
<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
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