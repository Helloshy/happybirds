<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/course/coursecontact/";
	String courseId = request.getParameter("courseId");
	String courseTypeId = request.getParameter("courseTypeId");
	String itemName = request.getParameter("itemName");
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
		<a href="javascript:void(0)" title="添加一条数据" style="margin-left: 40px;" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="gridAdd()">添加</a>
		<a href="javascript:void(0)" title="批量删除数据" style="margin-left: 40px;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="gridDel()">删除</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 100%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm?courseId=<%=courseId %>',loadFilter:loadFilter,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'courseTypeId',width:10,align:'center'">所属课程系列</th>
				<th data-options="field:'itemName',width:10,align:'center'">课程标题</th>
				<th data-options="field:'userName',width:10,align:'center'">报名联系人</th>
				<th data-options="field:'tel',width:10,align:'center'">联系电话</th>
				<th data-options="field:'createTime',width:10,align:'center'">更新时间</th>
				<th data-options="field:'clicknum2',width:10,formatter:EditFormatter,align:'center'">操作</th>
			</tr>
		</thead>
	</table>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" style="width: 450px;height: 450px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改',buttons:[{text:'保存', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="id" />
	<table class="mf-table">
		<%-- <tr class="mf-line" id="courseTypeId">
			<td class="mf-left">所属课程系列</td>
			<td class="mf-right">
				<%=courseTypeId %>
			</td>
		</tr> --%>
		<tr class="mf-line">
			<td class="mf-left">课程标题</td>
			<td class="mf-right">
				<%=itemName %>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">报名联系人</td>
			<td class="mf-right">
				<input type="text" id="uid" name="uid" class="easyui-combobox" data-options="required:true" style="width: 200px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">联系人名称</td>
			<td class="mf-right">
				<input type="text" id="userName" name="userName" class="easyui-textbox" style="width: 200px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">联系人头像</td>
			<td class="mf-right">
				<div id="imgdiv"><img id="imgShow" width="300" height="170" /></div>
   				<input type="file" id="up_img" name="file"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">联系电话</td>
			<td class="mf-right">
				<input type="text" id="tel" name="tel" class="easyui-textbox" data-options="required:true" style="width: 250px"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/course/courseContact.js"></script>
<script type="text/javascript">
	$(function(){
		$('#uid').combobox({
	            valueField:'id', //值字段
	            textField:'text', //显示的字段
	            url:'<%=thisPath%>'+'searchContactList.htm',
	            panelHeight:300,
	            required:true,
	            editable:false,
	            multiple:false,
	            onChange:function(id){
	              if(dataOptions.status == 0){
	            	$.post('<%=basePath%>/web/user/user/data.htm?id='+id,null,function(data){
	            		if(data != null && data.status == 10001){
	            			var data = data.data.data;
	            			new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
							$("#imgShow").attr("src",data.logoPath);
							$("#userName").textbox("setValue",data.nickName);
	            			$("#tel").textbox("setValue",data.userName);
	            			$('#add-dialog').dialog('open');
	            		}else{
	            			$.messager.alert("错误",ret.msg,"error");
	            		}
	            	},'json');
	             }
	            }
	     });       
	     
		dataOptions.courseId = '<%=courseId%>';
		dataOptions.baseUrl = '<%=thisPath%>';
		dataOptions.basePath = '<%=basePath%>';
		dataOptions.addInit = function(){};
		dataOptions.editInit = function(data){};
	});
</script>
</body>
</html>
