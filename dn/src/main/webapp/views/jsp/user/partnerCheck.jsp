<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/user/user/";
%>
<jsp:include page="../../include.jsp"></jsp:include> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<script type="text/javascript">
		/** 编辑 */
		var EditFormatter = function(value,row,index){
			return '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 20px;width:80px;" onClick="detail('+index+')">详情信息</a>';
		}
	</script>
<body>
	<!-- 查询条件panel 一般使用只需修改 标题:title -->
	<div id="gridTools" data-options="border:false" style="padding-top: 10px;">
		<form id="search-form" style="margin-top: 10px;text-align: center;">
		<span>邀请码：<input class="easyui-textBox" type="text" name="inviteCode" style="width: 120px"/></span>
		<span style="margin-left: 40px;">邀请人姓名：<input class="easyui-textBox" type="text" name="yqRealName" style="width: 120px"/></span>
		<span style="margin-left: 40px;">时间: <input type="text" name="startTime" class="easyui-datebox" style="width: 100px"/></span>
		&nbsp;至 <span style="margin-left: 10px;"><input type="text" name="endTime" class="easyui-datebox" style="width: 100px"/></span>
		<br/><br/>
		<span>审核状态：<input id="state" name="state" style="width: 70px"/></span>
		<span style="margin-left: 40px;">注册账号：<input class="easyui-textBox" type="text" name="userName" style="width: 120px"/></span>
		<span style="margin-left: 40px;">真实姓名：<input class="easyui-textBox" type="text" name="realName" style="width: 120px"/></span>
		<a href="javascript:void(0)" title="查询数据" style="margin-left: 40px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
		<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 40px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
		<a href="javascript:void(0)" title="使用" style="margin-left: 40px;width: 50px;" class="easyui-linkbutton"  onClick="check(1)">通  过</a>
		<a href="javascript:void(0)" title="暂停" style="margin-left: 40px;width: 50px;" class="easyui-linkbutton"  onClick="check(3)">拒  绝</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 120%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm?type=4',loadFilter:loadFilter,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'userName',width:8,align:'center'">用户账号</th>
				<th data-options="field:'nickName',width:8,align:'center'">昵称</th>
				<th data-options="field:'realName',width:8,align:'center'">真实姓名</th>
				<th data-options="field:'grPosition',width:10,align:'center'">个人身份</th>
				<th data-options="field:'dnPosition',width:10,align:'center'">动能身份</th>
				<th data-options="field:'pcd',width:10,align:'center'">省市区</th>
				<th data-options="field:'sex',width:3,align:'center'">性别</th>
				<th data-options="field:'idCard',width:10,align:'center'">身份证号</th>
				<th data-options="field:'inviteCode',width:5,align:'center'">邀请码</th>
				<th data-options="field:'yqRealName',width:6,align:'center'">邀请人姓名</th>
				<th data-options="field:'yqInviteCode',width:6,align:'center'">邀请人邀请码</th>
				<th data-options="field:'blueCurrency',width:5,align:'center'">蓝币</th>
				<th data-options="field:'redCurrency',width:5,align:'center'">红币</th>
				<th data-options="field:'state',width:5,formatter: function(value,row,index){
	        			if(value != null){
		  					if(value == 1){
		  						return '使用中' ;
		  					}else if(value == 0){
		  						return '暂停使用' ;
		  					}else if(value == 2){
		  						return '待审核'
		  					}else if(value == 3){
		  						return '拒绝'
		  					}
		  				}
				},align:'center'">状态</th>
				<th data-options="field:'createTime',width:10,align:'center'">注册时间</th>
				<th data-options="field:'clicknum2',width:10,formatter:EditFormatter,align:'center'">操作</th>
			</tr>
		</thead>
	</table>

<!-- 拒绝 -->
<div style="display: none;">
<div id="add-dialog" style="width: 450px;height: 300px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'审核状态',buttons:[{text:'保存', iconCls:'icon-ok', handler:saveState},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="ids"/>
	<input type="hidden" name="state"/>
	<table class="mf-table">
		<tr class="mf-line" id="rejectReason">
			<td class="mf-left">拒绝原因</td>
			<td class="mf-right">
				<textarea id="rr" name="rejectReason" cols="40" rows="10" class="textarea easyui-validatebox" data-options="required:true"></textarea>
			</td>
		</tr>
		<tr class="mf-line" id="dueDate">
			<td class="mf-left">截止期限</td>
			<td class="mf-right">
				<input type="text" name="dueDate" class="easyui-datebox" data-options="required:true" style="width: 150px"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<!-- 详情 -->
<div style="display: none;">
<div id="add-detail" style="width: 850px;height: 600px;" class="easyui-dialog" data-options="iconCls:'icon-save',resizable:true,modal:true,
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
			<td class="mf-right" id="sex1" colspan="3"></td>
		</tr>
		<tr class="mf-line" style="height: 45px;">
			<td class="mf-left">微信</td>
			<td class="mf-right" id="wechat1"></td>
			<td class="mf-left">动能身份</td>
			<td class="mf-right" id="dnPosition1"></td>
			<td class="mf-left">个人身份</td>
			<td class="mf-right" id="grPosition1"></td>
		</tr>
		<tr>
			<td class="mf-left">身份证号</td>
			<td class="mf-right" colspan="5">
				<span id="idCard1" ></span>
				&nbsp;&nbsp;&nbsp;<img style="vertical-align: middle;" id="idPhotoFront1" height="100px" width="150px;"/>
				&nbsp;&nbsp;&nbsp;<img style="vertical-align: middle;" id="idPhotoBack1" height="100px" width="150px;"/>
			</td>
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
	            url:'<%=thisPath%>'+'pcd.htm?type=0',
	            panelHeight:300,
	            editable:true,//不可编辑，只能选择
	            onChange:function(provinceId){
            			$('#city').combobox({
			                valueField:'id', //值字段
			                textField:'name', //显示的字段
			                url:'<%=thisPath%>'+'pcd.htm?type=1&id='+provinceId,
			                panelHeight:300,
			                editable:true,
			                onChange:function(cityId){
			                		SKU(cityId);
			            			$('#district').combobox({
						                valueField:'id', //值字段
						                textField:'name', //显示的字段
						                url:'<%=thisPath%>'+'pcd.htm?type=2&id='+cityId,
						                panelHeight:300,
						                editable:true
					                });
	           				}
		                });
		            district();
	            }
	   	});
	   	
	   	var  district = function(){
			$('#district').combobox({
				valueField:'id', //值字段
				textField:'name', //显示的字段
				url:'<%=thisPath%>'+'pcd.htm?type=2&id='+"",
				panelHeight:300,
				editable:true
			});
		}
		
		dataOptions.baseUrl = '<%=thisPath%>';
		dataOptions.basePath = '<%=basePath%>';
		dataOptions.addInit = function(){};
		dataOptions.editInit = function(data){};
	});
</script>
</body>
</html>
