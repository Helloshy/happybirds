<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/user/user/";
%>
<jsp:include page="../../include.jsp"></jsp:include> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script src="<%=basePath%>/js/uploadPreview/uploadPreview.js" type="text/javascript"></script>
	<script type="text/javascript">
		/** 编辑 */
		var EditFormatter = function(value,row,index){
			return '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 5px;width:80px;" onClick="khVisit('+index+')">回访记录</a>'
			 + '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 20px;width:80px;" onClick="detail('+index+')">详情信息</a>';
		}
	</script>
</head>
<body>
	<!-- 查询条件panel 一般使用只需修改 标题:title -->
	<div id="gridTools" data-options="border:false" style="padding-top: 10px;">
		<form id="search-form" style="margin-top: 10px;text-align: center;">
		<span>省：<input type="text" id="province"  name="province" style="width: 100px"/></span>
		<span>市：<input type="text" id="city" name="city" class="easyui-combobox" style="width: 100px"/></span>
		<span style="margin-left: 20px;">注册账号：<input class="easyui-textBox" type="text" name="userName" style="width: 120px"/></span>
		<span style="margin-left: 20px;">真实姓名：<input class="easyui-textBox" type="text" name="realName" style="width: 120px"/></span>
		<a href="javascript:void(0)" title="查询数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
		<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
		<a href="javascript:void(0)" title="导入" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onClick="exportService()">导出Excel</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 100%" class="myResize"
		data-options="url:'<%=thisPath %>searchServiceList.htm',loadFilter:loadFilter,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'userName',width:7,align:'center'">客服账号</th>
				<th data-options="field:'realName',width:6,align:'center'">客服姓名</th>
				<th data-options="field:'position',width:6,align:'center'">客服身份</th>
				<th data-options="field:'kuserName',width:7,align:'center'">客户账号</th>
				<th data-options="field:'krealName',width:6,align:'center'">客户姓名</th>
				<th data-options="field:'pcd',width:10,align:'center'">省市区</th>
				<th data-options="field:'itemName',width:6,align:'center'">最新购买课程</th>
				<th data-options="field:'isUse',width:6,align:'center'">课程状态</th>
				<th data-options="field:'registerDate',width:6,align:'center'">报到时间</th>
				<th data-options="field:'createTime',width:12,align:'center'">下单时间</th>
				<th data-options="field:'clicknum2',width:12,formatter:EditFormatter,align:'center'">操作</th>
			</tr>
		</thead>
	</table>


<div style="display: none;">
<div id="add-detail" style="width: 850px;height: 550px;" class="easyui-dialog" data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'详情信息',buttons:[{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-detail').dialog('close');}}]">
	<form id="add-detail-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">注册账号</td>
			<td class="mf-right" id="userName1"></td>
			<td class="mf-left">昵称</td>
			<td class="mf-right" id="nickName1"></td>
			<td class="mf-left">头像</td>
			<td class="mf-right"><img id="logoPath1" height="100px" width="150px;"/></td>
		</tr>
		<tr class="mf-line" style="height: 45px;">
			<td class="mf-left">姓名</td>
			<td class="mf-right" id="realName1"></td>
			<td class="mf-left">性别</td>
			<td class="mf-right" id="sex1"></td>
			<td class="mf-left">身份证号</td>
			<td class="mf-right" id="idCard1"></td>
		</tr>
		<tr class="mf-line" style="height: 45px;">
			<td class="mf-left">微信</td>
			<td class="mf-right" id="wechat1"></td>
			<td class="mf-left">动能身份</td>
			<td class="mf-right" id="dnPosition1"></td>
			<td class="mf-left">个人身份</td>
			<td class="mf-right" id="grPosition1"></td>
		</tr>
		<tr class="mf-line" style="height: 45px;">
			<td class="mf-left">邀请码</td>
			<td class="mf-right" id="inviteCode1"></td>
			<td class="mf-left">邀请人</td>
			<td class="mf-right" id="uidFrom1"></td>
			<td class="mf-left">省市区</td>
			<td class="mf-right" id="pcd1"></td>
		</tr>
		<tr class="mf-line" style="height: 45px;">
			<td class="mf-left">蓝币</td>
			<td class="mf-right" id="blueCurrency1"></td>
			<td class="mf-left">红币</td>
			<td class="mf-right" id="redCurrency1"></td>
			<td class="mf-left">邮箱</td>
			<td class="mf-right" id="mail1"></td>
		</tr>
		<tr class="mf-line" style="height: 45px;">
			<td class="mf-left">状态</td>
			<td class="mf-right" id="state1"></td>
			<td class="mf-left">截止期限</td>
			<td class="mf-right" id="dueDate1"></td>
			<td class="mf-left">注册时间</td>
			<td class="mf-right" id="createTime1"></td>
		</tr>
	</table>
	</form>
</div>
</div>	

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/user/user.js"></script>
<script type="text/javascript">
	$(function(){
		$('#province').combobox({
	            valueField:'id', //值字段
	            textField:'name', //显示的字段
	            url:'<%=thisPath%>'+'pcd.htm?type=0&status=1',
	            panelHeight:300,
	            editable:false,//不可编辑，只能选择
	            multiple:false,
	            onChange:function(provinceId){
            			$('#city').combobox({
			                valueField:'id', //值字段
			                textField:'name', //显示的字段
			                url:'<%=thisPath%>'+'pcd.htm?type=1&status=1&id='+provinceId,
			                panelHeight:300,
			                editable:false,
			                multiple:false
		                });
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
