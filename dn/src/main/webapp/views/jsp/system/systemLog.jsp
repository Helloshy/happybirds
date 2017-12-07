<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/system/log/";	
%>
<jsp:include page="../../include.jsp"></jsp:include> 

<!-- 查询条件panel 一般使用只需修改 标题:title -->
<div id="gridTools" data-options="border:false" style="padding-top: 10px;">
	<form id="search-form" style="margin-left: 250px;">
		用户名：<input type="text" name="userName" class="easyui-textbox"/>
		<a href="javascript:void(0)" title="查询数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
		<a href="javascript:void(0)" title="查询数据" style="margin-left: 40px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
		<a href="javascript:void(0)" title="批量删除" style="margin-left: 40px;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onClick="gridDel()">删除</a>
	</form>
</div>
<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
<div style="margin-top: 10px;">
<table id="mygrid" style="display: none;" class="myResize"
      data-options="url:'<%=thisPath %>searchList.htm',loadFilter:loadFilter,
      fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,nowrap:true,rownumbers:true">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'userName',width:10,align:'center'">用户名</th>
        	<th data-options="field:'operationType',width:15,align:'center'">操作类型</th>
            <th data-options="field:'createTime',width:15,align:'center'">操作时间</th>
        </tr>
    </thead>
</table>
</div>

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript">

	$(function(){
	
		dataOptions.baseUrl = '<%=thisPath%>';
		
		dataOptions.basePath = '<%=basePath%>';
		
		dataOptions.addInit = function(){
			
		};
		dataOptions.editInit = function(data){
		};
	});
  
</script>
    
</body>
</html>