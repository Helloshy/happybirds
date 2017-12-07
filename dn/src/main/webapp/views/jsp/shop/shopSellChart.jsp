<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/shop/sellChart/";
%>
<jsp:include page="../../include.jsp"></jsp:include> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script type="text/javascript">
		$(function(){
			dataOptions.baseUrl = '<%=thisPath%>';
			dataOptions.basePath = '<%=basePath%>';
			dataOptions.addInit = function(){};
			dataOptions.editInit = function(data){};
			url='<%=thisPath%>';
		});
		/**价格**/
		var priceFormatter = function (value,row,index) {
			var price = Math.round(row.estimatePayAmount/row.totalProductQty,2)
			console.log(price)
			return price
		}
	</script>
</head>
<body>
	<!-- 查询条件panel 一般使用只需修改 标题:title -->
	<div id="gridTools" data-options="border:false" style="padding-top: 10px;">
		<form id="search-form" style="margin-top: 10px;text-align: center;">
			查询时间  <span ><input type="text" name="startTime" class="easyui-datetimebox" id="startTimeQuery" style="width: 120px"/></span>
			&nbsp;至 <span style="margin-left: 20px;"><input type="text" name="endTime" id="endTimeQuery" class="easyui-datetimebox" style="width: 120px"/></span>
			<span style="margin-left: 30px;">商品描述：<input class="easyui-textbox" type="text" id="itemRemarkQuery" name="itemRemark" style="width: 120px"/></span>
			<a href="javascript:void(0)" title="查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
			<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
			<a href="javascript:void(0)" title="导出" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onClick="exportShopSellExcel()">导出Excel</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 100%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm',loadFilter:loadFilter,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'itemRemark',width:8,align:'center'">商品描述</th>
				<th data-options="field:'totalProductQty',width:12,align:'center'">销售数量</th>
				<th data-options="field:'price',width:8,align:'center',formatter:priceFormatter">商品平均销售价格</th>
				<th data-options="field:'estimatePayAmount',width:8,align:'center'">理论销售金额</th>
				<th data-options="field:'totalDiscountRed',width:8,align:'center'">使用红币数</th>
				<th data-options="field:'totalDiscountBlue',width:8,align:'center'">使用蓝币数</th>
				<th data-options="field:'totalPayAmount',width:6,align:'center'">实际收入现金</th>
				<th data-options="field:'create_time',width:12,align:'center'">更新时间</th>
			</tr>
		</thead>
	</table>


<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/shop/shopSellChart.js"></script>

</body>
</html>
