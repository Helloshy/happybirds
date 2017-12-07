<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/course/usercoupon/";
	String uid = request.getParameter("uid");
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
		<a href="javascript:void(0)" title="导入" style="margin-left: 40px;" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onClick="exportExcel(<%=uid %>)">导出Excel</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 100%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm?uid=<%=uid %>',loadFilter:loadFilter,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'itemName',width:10,align:'center'">课程系列名称</th>
				<th data-options="field:'couponName',width:10,align:'center'">抵扣卷名称</th>
				<th data-options="field:'couponContent',width:15,align:'center'">抵扣卷说明</th>
				<th data-options="field:'dateFromdateTo',width:12,align:'center'">使用时间</th>
				<th data-options="field:'state',width:10,align:'center',formatter:function(value,row,indrx){
									if(value == 0){
										return '可使用'
									}else if(value == 1){
										return '已使用'
									}else if(value == 2){
										return '已过期'
									}
				}">状态</th>
				<th data-options="field:'createTime',width:10,align:'center'">更新时间</th>
				<th data-options="field:'clicknum2',width:10,formatter:EditFormatter,align:'center'">操作</th>
			</tr>
		</thead>
	</table>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" style="width: 450px;height: 350px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改',buttons:[{text:'保存', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="id" id="id"/>
	<input type="hidden" name="uid" id="uid"/>
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">课程系列名称</td>
			<td class="mf-right">
				<input type="text" id="courseId" name="courseId" style="width: 170px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">抵扣卷名称</td>
			<td class="mf-right" id="couponName">
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">抵扣卷说明</td>
			<td class="mf-right" id="couponContent">
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">使用期限</td>
			<td class="mf-right">
				<input type="text" id="dateFrom" name="dateFrom" class="easyui-datebox" data-options="required:true,editable:false" style="width: 100px"/>
				 至 <input type="text" id="dateTo" name="dateTo" class="easyui-datebox" data-options="required:true,editable:false" style="width: 100px"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/course/coupon.js"></script>
<script type="text/javascript">
	$(function(){
		$('#courseId').combobox({
	            valueField:'id', //值字段
	            textField:'text', //显示的字段
	            url:'<%=basePath%>'+'/web/course/coursetype/searchCourseTypeList.htm',
	            panelHeight:300,
	            multiple:false,
				editable:false,
				onChange:function(id){
					$.post('<%=basePath%>'+'/web/course/coursetype/data.htm?id='+id,null,function(ret){
            			$("#couponName").text(ret.data.data.itemName+"抵扣卷");	
            			$("#couponContent").text("可以使用此抵扣卷全额购买"+ret.data.data.itemName);
					},"json");
	            }
	   	});
		
		dataOptions.baseUrl = '<%=thisPath%>';
		dataOptions.basePath = '<%=basePath%>';
		dataOptions.uid = '<%=uid%>';
		dataOptions.addInit = function(){};
		dataOptions.editInit = function(data){};
	});
</script>
</body>
</html>
