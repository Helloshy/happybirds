<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/product/inventory/";
	String productId = request.getParameter("productId") == null ? "" : request.getParameter("productId") ;
	String categoryName = request.getParameter("categoryName");
	String itemRemark = request.getParameter("itemRemark") ;
	String itemName = request.getParameter("itemName") ;
%>
<jsp:include page="../../include.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript">
		/** 删除 */
		var EditFormatter = function(value,row,index){
			return '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn"  style="margin-left: 20px;width:50px;" onClick="gridEdit('+index+')">修 改</a>'+
			       '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 20px;width:50px;" onClick="gridDel('+index+')">删 除</a>';
		}
	</script>
</head>
<body>
	<!-- 查询条件panel 一般使用只需修改 标题:title -->
	<div id="gridTools" data-options="border:false" style="padding-top: 10px;">
		<form id="search-form" style="margin-top: 10px;text-align: center;">
			<a href="javascript:void(0)" title="添加一条数据" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="gridAdd()">添加</a>
			<a href="javascript:void(0)" title="批量删除数据" style="margin-left: 60px;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="exportProInvertoryExcel(''+<%=productId%>)">导出表格</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 100%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm?productId=<%=productId%>',loadFilter:loadFilter,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'itemName',width:10,align:'center'">商品分类</th>
				<th data-options="field:'itemRemark',width:20,align:'center'">商品描述</th>
				<th data-options="field:'colorSize',width:10,align:'center'">属性搭配</th>
				<th data-options="field:'price',width:8,align:'center'">单价</th>
				<th data-options="field:'stockQty',width:8,align:'center'">库存</th>
				<th data-options="field:'createTime',width:15,align:'center'">添加时间</th>
				<th data-options="field:'clicknum2',width:12,formatter:EditFormatter,align:'center'">操作</th>
			</tr>
		</thead>
	</table>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" style="width: 600px;height: 550px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改',buttons:[{text:'保存', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">商品名称</td>
			<td class="mf-right" id="itemName"><%=itemName%></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">商品分类</td>
			<td class="mf-right" id="categoryName"><%=categoryName%></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">商品描述</td>
			<td class="mf-right" id="itemRemark"><%=itemRemark%></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">颜色值</td>
			<td class="mf-right">
				<input type="text" name="color" id="color" class="easyui-textbox" style="width: 180px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">尺码值</td>
			<td class="mf-right">
				<input type="text" name="size" id="size" class="easyui-textbox" style="width: 180px"/>
				&nbsp;<span style="color: gray;">当尺码值输入时颜色值必填</span>
			</td>
		</tr>

		<tr class="mf-line">
			<td class="mf-left">单价</td>
			<td class="mf-right" >
				<input type="text" name="price" id="price"  class="easyui-textbox" data-options="required:true" style="width: 180px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">库存量</td>
			<td class="mf-right" >
				<input type="text" name="stockQty" id="stockQty" class="easyui-textbox" data-options="required:true" style="width: 180px"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<div style="display: none;">
<div id="edit-dialog" style="width: 600px;height: 550px;" class="easyui-dialog" data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改',buttons:[{text:'保存', iconCls:'icon-ok', handler:saveEdit},{text:'取消',iconCls:'icon-remove',handler:function(){$('#edit-dialog').dialog('close');}}]">
	<form id="edit-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="id" />
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">商品名称</td>
			<td class="mf-right" id="itemName"><%=itemName%></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">商品分类</td>
			<td class="mf-right" id="categoryName"><%=categoryName%></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">商品描述</td>
			<td class="mf-right" id="itemRemark"><%=itemRemark%></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">颜色值</td>
			<td class="mf-right" id="color1"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">尺码值</td>
			<td class="mf-right" id="size1"></td>
		</tr>

		<tr class="mf-line">
			<td class="mf-left">单价</td>
			<td class="mf-right" >
				<input type="text" name="price" id="price1"  class="easyui-textbox" data-options="required:true" style="width: 180px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">库存量</td>
			<td class="mf-right" >
				<input type="text" name="stockQty" id="stockQty1" class="easyui-textbox" data-options="required:true" style="width: 180px"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/shop/productInventory.js"></script>
<script type="text/javascript">
	$(function(){
		dataOptions.productId = '<%=productId%>';
		dataOptions.baseUrl = '<%=thisPath%>';
		dataOptions.basePath = '<%=basePath%>';
		dataOptions.addInit = function(){};
		dataOptions.editInit = function(data){};
	});
</script>
</body>
</html>
