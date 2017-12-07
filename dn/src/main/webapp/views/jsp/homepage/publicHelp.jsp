<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/system/public/";
%>
<jsp:include page="../../include.jsp"></jsp:include> 
 <script src="<%=basePath%>/js/uploadPreview/uploadPreview.js" type="text/javascript"></script>

<!-- 查询条件panel 一般使用只需修改 标题:title -->
<div id="gridTools" data-options="border:false" style="padding-top: 10px;">
	<form id="search-form" style="margin-left: 250px;">
	</form>
</div>

<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
<table id="mygrid" style="margin-top: 10px;display: none;" class="myResize"
      data-options="url:'<%=thisPath %>searchList.htm?msgType=1',singleSelect:true,loadFilter:loadFilter,pageNumber:1,
      pageSize:10,pageList:[10,10],fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,
      pagination:true,loading:true,nowrap:true">
    <thead>
        <tr>
        	<th data-options="field:'id',width:10,align:'center'">编号</th>
        	<th data-options="field:'title',width:10,align:'center'">标题</th>
        	<th data-options="field:'memo',width:10,align:'center',formatter:htmlFormatter">详细内容</th>
            <th data-options="field:'ct',width:10,formatter:EditFormatter,align:'center'">操作</th><!-- 默认行 操作行 -->
        </tr>
    </thead>
</table>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" class="easyui-dialog" style="width: 950px;height: 500px;" 
	data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,title:'详情',maximizable:true,
	buttons:[{text:'确定', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" target="uploadImgFrame" method="post" enctype="multipart/form-data" style="margin: 0px;padding: 0px;">
		<input type="hidden" name="id"/>
		<table class="mf-table">
			<tr class="mf-line"><td class="mf-left">标题：</td>
				<td class="mf-right" id="title"></td>
			</tr>
			<tr class="mf-line"><td class="mf-left">内容：</td>
				<td class="mf-right">
					<textarea id="memo" name="memo" style="width: 100%;height: 300px" ></textarea>
					<a href="javascript:void(0)" title="添加视频" style="float: left;margin-top: 10px;" class="easyui-linkbutton" onclick="gridAddVideo()">添加视频</a>
				</td>
			</tr>
		</table>
	</form>
</div>
</div>
<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/system/publicHelp.js"></script>
<script type="text/javascript">

	$(function(){
	
		dataOptions.msgType=1;
		dataOptions.baseUrl = '<%=thisPath%>';
		dataOptions.basePath = '<%=basePath%>';
		dataOptions.addInit = function(){
		};
		dataOptions.editInit = function(data){
		};
	});
  
</script>
