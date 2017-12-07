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
			return '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 5px;width:50px;" onClick="gridEdit('+index+')">修改</a>'
			 + '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 20px;width:80px;" onClick="detail('+index+')">详情信息</a>';
		}
		
		/** 更改蓝币 */
		var currencyFormatter = function(value,row,index){
			return '<a href="javascript:void(0)" onClick="currency('+row.id+')">'+row.blueCurrency+'</a>';
		}
		
	</script>
</head>
<body>
	<!-- 查询条件panel 一般使用只需修改 标题:title -->
	<div id="gridTools" data-options="border:false" style="padding-top: 10px;">
		<form id="search-form" style="margin-top: 10px;text-align: center;">
		<span style="margin-left: 30px;">省：<input type="text" id="province1"  name="province" style="width: 100px"/></span>
		<span style="margin-left: 30px;">市：<input type="text" class="easyui-combobox" id="city1" name="city" style="width: 100px"/></span>
		<span style="margin-left: 30px;">邀请码：<input class="easyui-textbox" type="text" name="inviteCode" style="width: 120px"/></span>
		<span style="margin-left: 30px;">邀请人姓名：<input class="easyui-textbox" type="text" name="yqRealName" style="width: 120px"/></span>
		<span style="margin-left: 30px;">时间: <input type="text" name="startTime" class="easyui-datebox" style="width: 100px"/></span>
		&nbsp;至 <span style="margin-left: 10px;"><input type="text" name="endTime" class="easyui-datebox" style="width: 100px"/></span>
		<br /><br />
		<span>账号状态：<input id="state" name="state" style="width: 70px"/></span>
		<span style="margin-left: 30px;">注册账号：<input class="easyui-textBox" type="text" name="userName" style="width: 120px"/></span>
		<span style="margin-left: 30px;">真实姓名：<input class="easyui-textBox" type="text" name="realName" style="width: 120px"/></span>
		<a href="javascript:void(0)" title="查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
		<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
		<a href="javascript:void(0)" title="使用" style="margin-left: 40px;width: 50px;" class="easyui-linkbutton"  onClick="upstatus(1)">使  用</a>
		<a href="javascript:void(0)" title="暂停" style="margin-left: 40px;width: 50px;" class="easyui-linkbutton"  onClick="upstatus(0)">暂  停</a>
		<a href="javascript:void(0)" style="margin-left: 40px;" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onClick="exportStudent(1)">导出Excel</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 120%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm?type=1',loadFilter:loadFilter,
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
				<th data-options="field:'blueCurrency',width:5,align:'center',formatter:currencyFormatter">蓝币</th>
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
				<th data-options="field:'clicknum2',width:12,formatter:EditFormatter,align:'center'">操作</th>
			</tr>
		</thead>
	</table>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" style="width: 660px;height: 550px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改',buttons:[{text:'保存', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="id" />
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">头像：</td>
			<td class="mf-right" colspan="3">
				 <div id="imgdiv"><img id="imgShow" width="260" height="180" /></div>
   				 <input type="file" id="up_img" name="file"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">昵称</td>
			<td class="mf-right">
				<input type="text" id="nickName" name="nickName" class="easyui-textbox" data-options="required:true" style="width: 180px"/>
			</td>
			<td class="mf-left">姓名</td>
			<td class="mf-right">
				<input type="text" id="realName" name="realName" class="easyui-textbox" data-options="required:true" style="width: 180px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">性别</td>
			<td class="mf-right" colspan="3">
				&nbsp;<input type="radio" name="sex" value="1"/>男
				&nbsp;&nbsp;<input type="radio" name="sex" value="2"/>女
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">个人身份</td>
			<td class="mf-right">
				<input type="text" id="grPosition" name="grPosition" class="easyui-combobox" data-options="editable:false,multiple:true,valueField:'id',textField:'text',
				url:'<%=basePath %>/web/tag/usertag/searchTagTypeList.htm?tagType=个人身份'" style="width: 180px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">省市区</td>
			<td class="mf-right" colspan="3">
				<input type="text" name="province" id="province" style="width:100px;" class="easyui-combobox"/>省
				<input type="text" name="city" id="city" style="width:100px;" class="easyui-combobox"/>市
				<input type="text" name="district" id="district" style="width:100px;" class="easyui-combobox"/>区
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">上级邀请码</td>
			<td class="mf-right" colspan="3">
				<input type="text" id="sjInviteCode" name="sjInviteCode" class="easyui-textbox" style="width: 180px" data-options="required:true"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">邮箱</td>
			<td class="mf-right">
				<input type="text" id="mail" name="mail" class="easyui-textbox" style="width: 180px"/>
			</td>
			<td class="mf-left">微信</td>
			<td class="mf-right">
				<input type="text" id="wechat" name="wechat" class="easyui-textbox" style="width: 180px"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<!-- 升级身份 -->
<div style="display: none;">
<div id="add-identity" style="width: 500px;height: 400px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'升级身份',buttons:[{text:'保存', iconCls:'icon-ok', handler:saveIdentity},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-identity').dialog('close');}}]">
	<form id="add-identity-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">动能身份</td>
			<td class="mf-right">
				<input type="text" name="identity" class="easyui-combobox" data-options="required:true,editable:false,multiple:true,valueField:'id',textField:'text',
				url:'<%=basePath %>/web/tag/usertag/searchTagTypeList.htm?tagType=动能身份'" style="width: 150px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">缴纳费用</td>
			<td class="mf-right">
				<input type="text" name="amount" class="easyui-textbox" data-options="required:true" style="width: 150px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">截止期限</td>
			<td class="mf-right">
				<input type="text" name="dueDate1" class="easyui-datebox" data-options="required:true" style="width: 150px"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<!-- 修改蓝币数量 -->
<div style="display: none;">
<div id="add-currency" style="width: 500px;height: 400px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改蓝币',buttons:[{text:'保存', iconCls:'icon-ok', handler:saveCurrency},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-currency').dialog('close');}}]">
	<form id="add-currency-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="id" />
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">蓝币数量</td>
			<td class="mf-right">
				<input type="text" name="amount" class="easyui-textbox" data-options="required:true" style="width: 150px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">类型</td>
			<td class="mf-right">
				&nbsp;<input type="radio" name="type" value="1"/>增加
				&nbsp;&nbsp;<input type="radio" name="type" value="2"/>减少
		</tr>
		<tr class="mf-line">
			<td class="mf-left">更改原因</td>
			<td class="mf-right">
				<input type="text" name="content" class="easyui-textbox" data-options="required:true" style="width: 150px"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<!-- 详情 -->
<div style="display: none;">
<div id="add-detail" style="width: 850px;height: 570px;" class="easyui-dialog" data-options="iconCls:'icon-save',resizable:true,modal:true,
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
		<tr class="mf-line" style="height: 2px;"><td colspan="6"><hr></td></tr>
		<tr class="mf-line" style="height: 45px;">
			<td colspan="6">
				<a href="javascript:void(0)" style="margin-left: 70px;width: 80px;height: 30px;" class="easyui-linkbutton" onClick="identity()">升级身份</a>
				<a href="javascript:void(0)" style="margin-left: 70px;width: 80px;height: 30px;" class="easyui-linkbutton" onClick="coupon()">课程抵扣卷</a>
				<a href="javascript:void(0)" style="margin-left: 70px;width: 80px;height: 30px;" class="easyui-linkbutton" onClick="userRelation()">用户关系</a>
				<a href="javascript:void(0)" style="margin-left: 70px;width: 80px;height: 30px;" class="easyui-linkbutton" onClick="brokerage()">分佣明细</a>
			</td>
		</tr>
		<tr class="mf-line" style="height: 45px;">
			<td colspan="6">
				<a href="javascript:void(0)" style="margin-left: 70px;width: 80px;height: 30px;" class="easyui-linkbutton" onClick="offlineOrder()">课程订单</a>
				<a href="javascript:void(0)" style="margin-left: 70px;width: 80px;height: 30px;" class="easyui-linkbutton" onClick="storeOrder()">商城订单</a>
				<a href="javascript:void(0)" style="margin-left: 70px;width: 80px;height: 30px;" class="easyui-linkbutton" onClick="teacherOrder(2)">陪伴订单</a>
				<a href="javascript:void(0)" style="margin-left: 70px;width: 80px;height: 30px;" class="easyui-linkbutton" onClick="teacherOrder(1)">讲学申请</a>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>	

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/user/user.js"></script>
<script type="text/javascript">
	$(function(){
		$('#province1').combobox({
	            valueField:'id', //值字段
	            textField:'name', //显示的字段
	            url:'<%=thisPath%>'+'pcd.htm?type=0&status=1&',
	            panelHeight:300,
	            editable:false,//不可编辑，只能选择
	            multiple:false,
	            onChange:function(provinceId){
            			$('#city1').combobox({
			                valueField:'id', //值字段
			                textField:'name', //显示的字段
			                url:'<%=thisPath%>'+'pcd.htm?type=1&status=1&id='+provinceId,
			                panelHeight:300,
			                editable:false,
			                multiple:false
		                });
	            }
	   	});
	
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
