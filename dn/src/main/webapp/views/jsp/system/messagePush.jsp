<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/system/messagepush/";	
%>
<jsp:include page="../../include.jsp"></jsp:include> 
<script src="<%=basePath%>/js/uploadPreview/uploadPreview.js" type="text/javascript"></script>

<!-- 查询条件panel 一般使用只需修改 标题:title -->
<div id="gridTools" data-options="border:false" style="padding-top: 10px;">
	<form id="search-form" style="margin-top: 10px;text-align: center;">
		<!-- 浏览身份：<input type="text" name="position" id="position2"/>
		<span style="margin-left: 40px;">标题：<input type="text" name="title" id="title" class="easyui-textbox"/></span> 
		<a href="javascript:void(0)" title="查询数据" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
		<a href="javascript:void(0)" title="查询数据" style="margin-left: 40px;" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onClick="rest()">重置</a>-->
		<a href="javascript:void(0)" title="添加数据" style="margin-left: 40px;" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onClick="gridAdd()">添加</a>
		<a href="javascript:void(0)" title="批量删除数据" style="margin-left: 40px;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="gridDel()">删除</a>
	</form>
</div>

<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
<div style="margin-top: 10px;">
<table id="mygrid" style="display: none;" class="myResize"
      data-options="url:'<%=thisPath %>searchList.htm',loadFilter:loadFilter,
	      fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
    <thead>
        <tr>
        	<th data-options="field:'id',width:10,checkbox:true"></th>
        	<th data-options="field:'remark',width:10,align:'center'">浏览身份</th>
        	<th data-options="field:'title',width:10,align:'center'">标题</th>
        	<th data-options="field:'content',width:10,align:'center',formatter:htmlFormatter">内容</th>
        	<th data-options="field:'createTime',width:10,align:'center'">创建时间</th>
            <th data-options="field:'ct',width:10,formatter:EditFormatter,align:'center'">操作</th>
        </tr>
    </thead>
</table>
</div>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" class="easyui-dialog" style="width: 600px;height: 450px;" 
	data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,title:'推送消息',maximizable:true,
	buttons:[{text:'确定', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" target="uploadImgFrame" method="post" enctype="multipart/form-data" style="margin: 0px;padding: 0px;">
		<input type="hidden" name="id" id="id"/>
		<table class="mf-table">
			<tr class="mf-line"><td class="mf-left">浏览身份：</td>
				<td class="mf-right">
					<div style="float: left;margin-top: -2px;">&nbsp;<input type="checkbox" name="type" value="全部" style="width:17px; height:17px;"/></div><div style="float: left;">全部</div>
					<div style="float: left;margin-top: -2px;">&nbsp;<input type="checkbox" name="type" value="动能集团员工" style="width:17px; height:17px;"/></div><div style="float: left;">员工</div>
					<div style="float: left;margin-top: -2px;">&nbsp;<input type="checkbox" name="type" value="动能集团合作伙伴" style="width:17px; height:17px;"/></div><div style="float: left;">合作伙伴</div>
					<div style="float: left;margin-top: -2px;">&nbsp;<input type="checkbox" name="type" value="动能集团学员,其它" style="width:17px; height:17px;"/></div><div style="float: left;">集团学员/其它</div>
				</td>
			</tr>
			<tr class="mf-line"><td class="mf-left">标题：</td>
				<td class="mf-right">
					<input type="text" name="title" class="easyui-textbox" required="required" data-options="required:true" style="width: 250px;"/>
				</td>
			</tr>
			<tr class="mf-line"><td class="mf-left">内容：</td>
				<td class="mf-right">
					<textarea style="width:98%;height:220px;" name="content" id="content"></textarea>
				</td>
			</tr>
		</table>
	</form>
</div>

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/system/messagePush.js"></script>
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