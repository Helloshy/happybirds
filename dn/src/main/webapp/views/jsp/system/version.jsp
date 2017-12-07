<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/system/version/";	
%>
<jsp:include page="../../include.jsp"></jsp:include> 
<script type="text/javascript">
		/** 编辑 */
		var EditFormatter = function(value,row,index){
			return '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 20px;width:80px;" onClick="detail('+index+')">详情信息</a>';
		}
</script>
	
<!-- 查询条件panel 一般使用只需修改 标题:title -->
<div id="gridTools" data-options="border:false" style="padding-top: 10px;text-align: center;">
	<form id="search-form" style="margin-left: 10px;text-align: center;">
		<a href="javascript:void(0)" title="添加数据"  class="easyui-linkbutton" data-options="iconCls:'icon-add'" onClick="gridAdd()">添加</a>
		<a href="javascript:void(0)" title="批量删除" style="margin-left: 40px;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onClick="gridDel()">删除</a>
	</form>
</div>

<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
<table id="mygrid" style="margin-top: 10px;display: none;" class="myResize"
      data-options="url:'<%=thisPath %>searchList.htm',loadFilter:loadFilter,
	      fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:4,align:'center'">编号</th>
        	<th data-options="field:'itemName',width:6,align:'center'">版本名称</th>
        	<th data-options="field:'path',width:12,align:'center'">版本连接</th>
        	<th data-options="field:'remark',width:12,align:'center'">备注</th>
        	<th data-options="field:'createTime',width:12,align:'center'">添加时间</th>
        	<th data-options="field:'ct',width:6,formatter:EditFormatter,align:'center'">操作</th>
        </tr>
    </thead>
</table>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" class="easyui-dialog" style="width:600px;height: 450px;" 
	data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,title:'添加版本',maximizable:true,
	buttons:[{text:'确定', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" target="uploadImgFrame" method="post" enctype="multipart/form-data" style="margin: 0px;padding: 0px;">
		<input type="hidden" name="id"/>
		<table class="mf-table">
			<tr class="mf-line"><td class="mf-left">版本名称：</td>
				<td class="mf-right">
					<input type="text" id="itemName" name="itemName" class="easyui-textbox" data-options="required:true" style="width: 180px;"/>
				</td>
			</tr>
			<tr class="mf-line"><td class="mf-left">文件路径：</td>
				<td class="mf-right">
					<textarea rows="5" cols="60" id="path" name="path" class="textarea easyui-validatebox" data-options="required:true"></textarea>
				</td>
			</tr>
			<tr class="mf-line"><td class="mf-left">备注：</td>
				<td class="mf-right">
					<textarea rows="5" cols="60" id="remark" name="remark"></textarea>
				</td>
			</tr>
		</table>
	</form>
</div>
</div>

<div style="display: none;">
<div id="add-detail" class="easyui-dialog" style="width:600px;height: 450px;" 
	data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,title:'版本详情',maximizable:true,
	buttons:[{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-detail').dialog('close');}}]">
	<form id="add-detail-form" target="uploadImgFrame" method="post" enctype="multipart/form-data" style="margin: 0px;padding: 0px;">
		<table class="mf-table">
			<tr class="mf-line"><td class="mf-left">版本名称：</td>
				<td class="mf-right" id="itemName1">
				</td>
			</tr>
			<tr class="mf-line"><td class="mf-left">文件路径：</td>
				<td class="mf-right" id="path1"></td>
			</tr>
			<tr class="mf-line"><td class="mf-left">备注：</td>
				<td class="mf-right" id="remark1"></td>
			</tr>
			<tr class="mf-line"><td class="mf-left">创建时间：</td>
				<td class="mf-right" id="createTime1"></td>
			</tr>
		</table>
	</form>
</div>
</div>

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/system/version.js"></script>
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