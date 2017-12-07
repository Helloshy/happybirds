<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/course/order/";
%>
<jsp:include page="../../include.jsp"></jsp:include> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script src="<%=basePath%>/js/uploadPreview/uploadPreview.js" type="text/javascript"></script>
	<script type="text/javascript">
		//线下实收交易金额
		var amountFormatter = function(value,row,index){
			if(0==row.isPay && row.payMethod == '线下支付')return '<a href="javascript:void(0)" onClick="amount('+index+')">'+row.payAmount+'</a>';
			else return row.payAmount;
		}
		//详情
		var EditFormatter = function(value,row,index){
			return '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn"  style="margin-left: 10px;width:70px;" onClick="detail('+index+')">详情信息</a>'
		}
		//支付状态
		var isPayFormatter = function(value,row,index){
			var state = '已取消';
			if(1==row.isPay){
				state = '已支付';
			}else if(0==row.isPay){
				state = '未支付';
			}
			return state;
		}
</script>
</head>
<body>
	<!-- 查询条件panel 一般使用只需修改 标题:title -->
	<div id="gridTools" data-options="border:false" style="padding-top: 10px;">
		<form id="search-form" style="margin-top: 10px;text-align: center;">
			支付时间  <span><input type="text" name="startTime" class="easyui-datetimebox" style="width: 100px"/></span>
			&nbsp;至 <span style="margin-left: 10px;"><input type="text" name="endTime" class="easyui-datetimebox" style="width: 100px"/></span>
			<span>订单号：<input class="easyui-textbox" type="text" name="orderNo" style="width: 120px"/></span>
			<span style="margin-left: 20px;">课程名称：<input class="easyui-textbox" type="text" name="itemName" style="width: 100px"/></span>
			<span style="margin-left: 20px;">用户姓名：<input class="easyui-textbox" type="text" name="realName" style="width: 100px"/></span>
			<span style="margin-left: 20px;">支付状态 ：<input type="text" name="isPay" id="isPay" class="easyui-textbox" style="width: 80px"/></span>
			<a href="javascript:void(0)" title="查询数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
			<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
			<a href="javascript:void(0)" title="添加一条数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="gridAdd()">添加</a>
			<a href="javascript:void(0)" title="导出" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onClick="exportExcel(3)">导出Excel</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 115%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm?type=3',loadFilter:loadFilter,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'orderNo',width:14,align:'center'">订单号</th>
				<th data-options="field:'itemName',width:8,align:'center'">课程名称</th>
				<th data-options="field:'meal',width:12,align:'center'">套餐内容</th>
				<th data-options="field:'counts',width:5,align:'center'">内容数量</th>
				<th data-options="field:'userName',width:8,align:'center'">购买账号</th>
				<th data-options="field:'realName',width:8,align:'center'">真实姓名</th>
				<th data-options="field:'community',width:8,align:'center'">所在社区</th>
				<th data-options="field:'sjUserName',width:8,align:'center'">上级账号</th>
				<th data-options="field:'sjRealName',width:8,align:'center'">上级姓名</th>
				<th data-options="field:'originAmount',width:6,align:'center'">课程费用</th>
				<th data-options="field:'payAmount',width:6,align:'center',formatter:amountFormatter">实收费用</th>
				<th data-options="field:'payMethod',width:6,align:'center'">支付方式</th>
				<th data-options="field:'discountBlue',width:5,align:'center'">抵扣蓝币</th>
				<th data-options="field:'discountRed',width:5,align:'center'">抵扣红币</th>
				<th data-options="field:'payTime',width:12,align:'center'">支付时间</th>
				<th data-options="field:'isPay',width:5,align:'center',formatter:isPayFormatter">支付状态</th>
				<th data-options="field:'clicknum2',width:7,formatter:EditFormatter,align:'center'">操作</th>
			</tr>
		</thead>
	</table>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" style="width: 650px;height: 600px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改',buttons:[{text:'保存', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="recordType" />
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">课程名称</td>
			<td class="mf-right">
				<input type="text" id="courseId" name="courseId" style="width: 180px"/>
			</td>
			<td class="mf-left">购买账号</td>
			<td class="mf-right">
				<input type="text" id="userName" name="userName" class="easyui-textbox" data-options="required:true" style="width: 180px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">线下课程名称</td>
			<td class="mf-right" colspan="3" id="offlineName"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">网络课程名称</td>
			<td class="mf-right" colspan="3" id="onlineName"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">真实姓名</td>
			<td class="mf-right" id="realName"></td>
			<td class="mf-left">所在社区</td>
			<td class="mf-right" id="community"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">上级账号</td>
			<td class="mf-right" id="sjUserName"></td>
			<td class="mf-left">上级姓名</td>
			<td class="mf-right" id="sjRealName"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">课程费用</td>
			<td class="mf-right">
				<input type="text" id="originAmount" name="originAmount" class="easyui-textbox" data-options="required:true" style="width: 180px"/>
			</td>
			<td class="mf-left">实收费用</td>
			<td class="mf-right">
				<input type="text" id="payAmount" name="payAmount" class="easyui-textbox" data-options="required:true" style="width: 180px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">支付方式</td>
			<td class="mf-right">
				<input type="text" id="payMethod" name="payMethod" class="easyui-combobox" data-options="required:true" style="width: 180px"/>
			</td>
			<td class="mf-left">支付时间</td>
			<td class="mf-right">
				<input type="text" id="payTime" name="payTime" class="easyui-datetimebox" data-options="required:true" style="width: 180px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">抵扣蓝币</td>
			<td class="mf-right">
				<input type="text" id="discountBlue" name="discountBlue" class="easyui-textbox" data-options="required:true" style="width: 180px"/>
			</td>
			<td class="mf-left">抵扣红币</td>
			<td class="mf-right">
				<input type="text" id="discountRed" name="discountRed" class="easyui-textbox" data-options="required:true" style="width: 180px"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<!-- 线下成交费 -->
<div style="display: none;">
<div id="add-amount" style="width: 600px;height: 400px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改线下成交费',buttons:[{text:'保存', iconCls:'icon-ok', handler:saveAmount},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-amount').dialog('close');}}]">
	<form id="add-amount-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="id" />
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">系统课程名称</td>
			<td class="mf-right" id="itemName1"></td>
			<td class="mf-left">费用</td>
			<td class="mf-right" id="amount"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">线下课程名称</td>
			<td class="mf-right" id="offlineName1"></td>
			<td class="mf-left">价值</td>
			<td class="mf-right" id="amount1"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">网络课程名称</td>
			<td class="mf-right" id="onlineName1"></td>
			<td class="mf-left">价值</td>
			<td class="mf-right" id="amount2"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">蓝币抵扣</td>
			<td class="mf-right" id="discountBlue1"></td>
			<td class="mf-left">红币抵扣</td>
			<td class="mf-right" id="discountRed1"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">线下实收费用</td>
			<td class="mf-right" colspan="3">
				<input type="text" id="payAmount1" name="payAmount" class="easyui-textbox" data-options="required:true"  style="width: 180px"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<!-- 详情 -->
<div style="display: none;">
<div id="add-dialog1" style="width: 650px;height: 600px;" class="easyui-dialog" data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'详情',buttons:[{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog1').dialog('close');}}]">
	<form id="add-data-form1" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">课程名称</td>
			<td class="mf-right" id="courseId2"></td>
			<td class="mf-left">购买账号</td>
			<td class="mf-right" id="userName2"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">线下课程名称</td>
			<td class="mf-right" colspan="3" id="offlineName2"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">网络课程名称</td>
			<td class="mf-right" colspan="3" id="onlineName2"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">真实姓名</td>
			<td class="mf-right" id="realName2"></td>
			<td class="mf-left">所在社区</td>
			<td class="mf-right" id="community2"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">上级账号</td>
			<td class="mf-right" id="sjUserName2"></td>
			<td class="mf-left">上级姓名</td>
			<td class="mf-right" id="sjRealName2"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">课程费用</td>
			<td class="mf-right" id="originAmount2"></td>
			<td class="mf-left">实收费用</td>
			<td class="mf-right" id="payAmount2"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">支付方式</td>
			<td class="mf-right" id="payMethod2"></td>
			<td class="mf-left">支付时间</td>
			<td class="mf-right" id="payTime2"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">抵扣蓝币</td>
			<td class="mf-right" id="discountBlue2"></td>
			<td class="mf-left">抵扣红币</td>
			<td class="mf-right" id="discountRed2"></td>
		</tr>
	</table>
	</form>
</div>
</div>

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/order/systemOrder.js"></script>
<script type="text/javascript">
	$(function(){
		 $("#userName").textbox({
			 onChange:function(userName){
				$.post( '<%=thisPath%>getUserDetail.htm?userName='+userName,null,function(ret){
			       if(ret && ret.status == 10001){
			         var data = ret.data.data;
			         $("#realName").text(data.realName);
			         $("#community").text(data.community);
			         $("#sjUserName").text(data.sjUserName);
			         $("#sjRealName").text(data.sjRealName);
			       }else{
			         $.messager.alert("错误",ret.msg,"error");
			         $("#realName").text("");
			         $("#community").text("");
			         $("#sjUserName").text("");
			         $("#sjRealName").text("");
			       }
			    },'json');
			 }
		});
	
		$('#courseId').combobox({
	            valueField:'id', //值字段
	            textField:'text', //显示的字段
	            url:'<%=thisPath%>'+'getCourseList.htm?type=3',
	            panelHeight:300,
	            editable:false,//不可编辑，只能选择
	            multiple:false,
	            onChange:function(id){
	            	$.post( '<%=thisPath%>'+'/getCourseDetail.htm?type=1&id='+id,null,function(ret){
	            			if(ret && ret.status == 10001){
	            				var data = ret.data.data;
	            				$("#offlineName").text(data.offlineName);
	            				$("#onlineName").text(data.onlineName);
	            				var discountBlue = data.discountBlue+"";
	            				$("#discountBlue").textbox("setValue",discountBlue.substring(0, discountBlue.indexOf(".")));
	            				$("#originAmount").textbox("setValue",data.originAmount);
	            			}else{
	            				$.messager.alert("错误",ret.msg,"error");
	            				$("#onlineName").text("");
	            				$("#onlineName").text("");
	            				$("#discountBlue").textbox("setValue","");
	            				$("#originAmount").textbox("setValue","");
	            			}
	            	},'json');
	            }
	   	});
		dataOptions.baseUrl = '<%=thisPath%>';
		dataOptions.basePath = '<%=basePath%>';
		dataOptions.addInit = function(){};
		dataOptions.editInit = function(data){};
	});
</script>
</body>
</html>
