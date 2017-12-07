<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/system/opinion/";	
%>
<jsp:include page="../../include.jsp"></jsp:include> 
<body>
<script type="text/javascript">
	//操作
var EditFormatter = function(value,row,index){
	var html="";
	if(value == 0){
		html += '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="width:60px;" onClick="gridEdit('+index+')">回复</a>';
		html += '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 20px;width:60px;" onClick="info('+index+')">详情</a>';
	}else{
		html += '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 20px;width:60px;" onClick="info('+index+')">详情</a>';
	}
	return html;
}
</script>
<!-- 查询条件panel 一般使用只需修改 标题:title -->
<div id="gridTools" data-options="border:false" style="padding-top: 10px;">
	<form id="search-form" style="margin-top: 10px;text-align: center;">
			<span>状态：<input class="easyui-combobox" type="text" id="state" name="state" style="width: 80px"/></span>
			<a href="javascript:void(0)" title="查询数据" style="margin-left: 30px;"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
			<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
	</form>
</div>

<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
<div style="margin-top: 10px;">
<table id="mygrid" style="display: none;" class="myResize"
      data-options="url:'<%=thisPath %>searchList.htm?recordType=2',loadFilter:loadFilter,pageNumber:1,
      pageSize:10,pageList:[10,20],fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,
      pagination:true,loading:true,nowrap:true">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'userName',width:10,align:'center'">注册账号</th>
        	<th data-options="field:'nickName',width:8,align:'center'">昵称</th>
        	<th data-options="field:'content',width:10,align:'center',formatter:htmlFormatter">反馈内容</th>
        	<th data-options="field:'state',width:4,align:'center',formatter:function(value,row,index){
        			if(value == 0){
        				return '未回复'
        			}else if(value == 1){
        				return '已回复'
        			}
        	},align:'center'">状态</th>
        	<th data-options="field:'feedback',width:10,align:'center',formatter:htmlFormatter">回复内容</th>
        	<th data-options="field:'createTime',width:10,align:'center'">反馈时间</th>
            <th data-options="field:'state1',width:10,formatter:EditFormatter,align:'center'">操作</th>
        </tr>
    </thead>
</table>
</div>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" style="width: 550px;height: 500px;" class="easyui-dialog" data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,title:'添加',
	buttons:[{text:'确定', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="id" />
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">注册账号：</td>
			<td class="mf-right">
				<label id="userName"></label>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">昵称：</td>
			<td class="mf-right"">
				<label id="nickName"></label>
			</td>
		</tr>
		<tr class="mf-line" style="height:80px">
			<td class="mf-left">反馈内容：</td>
			<td class="mf-right">
				<textarea id="content" rows="7" cols="40" readonly="readonly"></textarea>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">反馈时间：</td>
			<td class="mf-right">
				<label id="createTime"></label>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">状态：</td>
			<td class="mf-right">
				<label id="state"></label>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">回复内容：</td>
			<td class="mf-right">
				<textarea name="feedback" rows="7" cols="40" class="textarea easyui-validatebox" data-options="required:true"></textarea>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog1" style="width: 550px;height: 500px;" class="easyui-dialog" data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,title:'添加',buttons:[{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog1').dialog('close');}}]">
	<form id="add-data-form1" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">注册账号：</td>
			<td class="mf-right">
				<label id="userName1"></label>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">昵称：</td>
			<td class="mf-right"">
				<label id="nickName1"></label>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">反馈内容：</td>
			<td class="mf-right">
				<textarea id="content1" rows="7" cols="40" readonly="readonly"></textarea>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">反馈时间：</td>
			<td class="mf-right">
				<label id="createTime1"></label>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">状态：</td>
			<td class="mf-right">
				<label id="state1"></label>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">回复内容：</td>
			<td class="mf-right">
				<textarea id="feedback1" rows="7" cols="40" readonly="readonly"></textarea>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>
<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/system/opinion.js"></script>
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