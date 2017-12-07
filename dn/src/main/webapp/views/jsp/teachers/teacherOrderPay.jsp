<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/teacher/teacherorder/";
%>
<jsp:include page="../../include.jsp"></jsp:include> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script src="<%=basePath%>/js/uploadPreview/uploadPreview.js" type="text/javascript"></script>
	<script type="text/javascript">
		//编辑
		var EditFormatter = function(value,row,index){
			if(row.state == 1)return '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 5px;width:50px;" onClick="payEdit('+index+')">付 费</a>';
			else return '';
		}
	</script>
</head>
<body>
	<!-- 查询条件panel 一般使用只需修改 标题:title -->
	<div id="gridTools" data-options="border:false" style="padding-top: 10px;">
		<form id="search-form" style="margin-top: 10px;text-align: center;">
		<span>状态：<input type="text" id="state-2" name="state" style="width: 100px"/></span>
		<span style="margin-left: 30px;">预约讲师：<input type="text" name="itemName" class="easyui-textbox" style="width: 120px"/></span>
		<span style="margin-left: 30px;">真实姓名：<input type="text" name="realName" class="easyui-textbox" style="width: 120px"/></span>
		<a href="javascript:void(0)" title="查询数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
		<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
		<a href="javascript:void(0)" title="批量取消数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="state(3)">取 消</a>
		<!-- <a href="javascript:void(0)" title="批量通过" style="margin-left: 40px;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="state(4)">付费成功</a> -->
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 115%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm?recordType=1&type=1',loadFilter:loadFilter,nowrap:false,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'itemName',width:6,align:'center'">预约讲师</th>
				<th data-options="field:'realName',width:6,align:'center'">用户真实姓名</th>
				<th data-options="field:'nickName',width:6,align:'center'">申请人姓名</th>
				<th data-options="field:'phone',width:10,align:'center'">联系电话</th>
				<th data-options="field:'address',width:10,align:'center'">讲学地点</th>
				<th data-options="field:'teachTheme',width:10,align:'center'">讲学主题</th>
				<th data-options="field:'teachStart_teachEnd',width:12,align:'center'">开始时间-结束时间</th>
				<th data-options="field:'teachTimes',width:4,align:'center'">申请讲学场数</th>
				<th data-options="field:'orderAmount',width:6,align:'center'">费用</th>
				<th data-options="field:'state',width:6,align:'center',formatter:function(value,row,index){
									if(value == 1){
										return '待付费'
									}else if(value == 4){
										return '付费成功'
									}
				}">状态</th>
				<th data-options="field:'discountRemark',width:10,align:'center'">欧币抵扣</th>
				<th data-options="field:'actualAmount',width:6,align:'center'">实收金额</th>
				<th data-options="field:'createTime',width:10,align:'center'">申请时间</th>
				<th data-options="field:'clicknum2',width:6,formatter:EditFormatter,align:'center'">操作</th>
			</tr>
		</thead>
	</table>


<!-- 付费成功 -->
<div style="display: none;">
<div id="add-oa" style="width: 500px;height: 450px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改',buttons:[{text:'保存', iconCls:'icon-ok', handler:saveoa},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-oa').dialog('close');}}]">
	<form id="add-data-oa" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="ids" id="ids-oa"/>
	<input type="hidden" name="state" id="state-oa"/>
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">费用</td>
			<td class="mf-right" id="orderAmount">
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">欧币抵扣</td>
			<td class="mf-right" id="discountRemark">
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">实收金额</td>
			<td class="mf-right">
				<input type="text" id="actualAmount" name="actualAmount" class="easyui-textbox" data-options="required:true" style="width: 200px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">收款时间</td>
			<td class="mf-right">
				<input type="text" id="payDate" name="payDate" class="easyui-datebox" data-options="required:true,editable:false" style="width: 200px"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/teacher/teacherOrder.js"></script>
<script type="text/javascript">
	$(function(){
		dataOptions.recordType = '1';
		dataOptions.baseUrl = '<%=thisPath%>';
		dataOptions.basePath = '<%=basePath%>';
		dataOptions.addInit = function(){};
		dataOptions.editInit = function(data){};
	});
</script>
</body>
</html>
