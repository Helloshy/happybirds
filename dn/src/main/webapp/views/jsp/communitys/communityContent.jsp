<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/community/communitycontent/";
%>
<jsp:include page="../../include.jsp"></jsp:include> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
	<script src="<%=basePath%>/js/uploadPreview/uploadPreview.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=basePath%>/pictures/picture.js"></script>
	<script type="text/javascript">
		/** 编辑 */
		var EditFormatter = function(value,row,index){
			return '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 20px;width:80px;" onClick="detail('+index+')">详情信息</a>';
		}
	</script>
	<style type="text/css">
     .img{ border-radius:50%;width:100px;height:100px;} 
	</style>
<body>
	<!-- 查询条件panel 一般使用只需修改 标题:title -->
	<div id="gridTools" data-options="border:false" style="padding-top: 10px;">
		<form id="search-form" style="margin-top: 10px;text-align: center;">
		<span>内容类型：<input type="text"  name="recordType" id="g-recordType" style="width: 80px"/></span>
		<span style="margin-left: 20px;">是否被举报：<input type="text" name="isReport" id="g-isReport" style="width: 80px"/></span>
		<span style="margin-left: 20px;">小组名称：<input class="easyui-textBox" type="text" name="itemName" style="width: 100px"/></span>
		<span style="margin-left: 20px;">发表人昵称：<input class="easyui-textBox" type="text" name="nickName" style="width: 100px"/></span>
		<a href="javascript:void(0)" title="查询数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
		<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
		<a href="javascript:void(0)" title="添加一条数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="gridAdd()">添加</a>
		<a href="javascript:void(0)" title="批量删除数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="gridDel()">删除</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 100%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm',loadFilter:loadFilter,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'itemName',width:10,align:'center'">小组名称</th>
				<th data-options="field:'recordType',width:8,align:'center'">内容类型</th>
				<th data-options="field:'logoPath',width:10,align:'center',formatter:imageFormatter">头像</th>
				<th data-options="field:'nickName',width:10,align:'center'">昵称</th>
				<th data-options="field:'userName',width:10,align:'center'">手机号</th>
				<th data-options="field:'iconValue',width:8,align:'center',formatter:littleImageFormatter">身份图标</th>
				<th data-options="field:'content',width:10,align:'center'">发布内容</th>
				<th data-options="field:'likes',width:6,align:'center'">点赞数</th>
				<th data-options="field:'comments',width:6,align:'center'">评论数</th>
				<th data-options="field:'isReport',width:6,align:'center'">是否被举报</th>
				<th data-options="field:'createTime',width:10,align:'center'">添加时间</th>
				<th data-options="field:'clicknum2',width:10,formatter:EditFormatter,align:'center'">操作</th>
			</tr>
		</thead>
	</table>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" style="width: 550px;height: 450px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改',buttons:[{text:'保存', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="id" />
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">发布区域</td>
			<td class="mf-right">
				<input type="text" id="aid" name="aid" class="easyui-combobox" data-options="editable:false,multiple:true,valueField:'id',textField:'text',
				url:'<%=basePath %>/web/tag/apparea/getAreaList.htm'" style="width: 180px;"/>
				&nbsp;<input type="checkbox" name="aid_checkbox"/>全部
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">发布省份</td>
			<td class="mf-right">
				<input type="text" id="pid" name="pid" class="easyui-combobox" data-options="editable:false,multiple:true,valueField:'id',textField:'name',
				url:'<%=basePath %>/web/user/user/pcd.htm?type=0'" style="width: 180px;"/>
				&nbsp;<input type="checkbox" name="pid_checkbox"/>全部
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">发布小组</td>
			<td class="mf-right">
				<input type="text" id="cid" name="cid" class="easyui-combobox" data-options="editable:false,multiple:true,valueField:'id',textField:'text',
				url:'<%=basePath %>/web/community/community/getCommunityList.htm'"  style="width: 180px"/>
				&nbsp;<input type="checkbox" name="cid_checkbox"/>全部小组
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">内容类型</td>
			<td class="mf-right">
				&nbsp;<input type="radio" name="recordType" value="1"/>公告
				&nbsp;&nbsp;<input type="radio" name="recordType" value="2"/>动态
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">内容</td>
			<td class="mf-right">
				<textarea cols="60" rows="4" id="content" name="content"></textarea>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">图片</td>
			<td class="mf-right">
				<div id="imgs" style="width: 400px;"></div>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<!-- 用户详情 -->
<div style="display: none;">
<div id="add-dialog1" style="width: 750px;height: 650px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'详情',buttons:[{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog1').dialog('close');}}]">
	<form id="add-data-form1" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">小组名称</td>
			<td class="mf-right" id="itemName1"></td>
			<td class="mf-left">区长</td>
			<td class="mf-right" id="realName1"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">发表人昵称</td>
			<td class="mf-right" id="nickName1"></td>
			<td class="mf-left">手机号</td>
			<td class="mf-right" id="userName1"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">头像</td>
			<td class="mf-right"><div id="logoPath1"></div></td>
			<td class="mf-left">身份图标</td>
			<td class="mf-right"><div id="iconValue1"></div></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">内容类型</td>
			<td class="mf-right" id="recordType1"></td>
			<td class="mf-left">是否被举报</td>
			<td class="mf-right" id="isReport1"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">点赞数</td>
			<td class="mf-right" id="likes1"></td>
			<td class="mf-left">评论数</td>
			<td class="mf-right" id="comments1"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">添加时间</td>
			<td class="mf-right" id="createTime1" colspan="3"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">内容</td>
			<td class="mf-right" colspan="3">
				<textarea cols="60" rows="4" id="content1" readonly="readonly"></textarea>
			</td>
		</tr>
		<tr class="mf-line" style="height: 80px;">
			<td class="mf-left">图片</td>
			<td class="mf-right" colspan="3">
				<div id="logoPaths1"></div>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left" colspan="5">评论列表</td>
		</tr>
		<tr class="mf-line">
			<td colspan="5">
				<table cellpadding="4px;" id="comment">
				</table>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<!-- 多图片上传 -->
<div style="display: none;">
<div id="add-picture" style="width: 400px;height: 300px;" class="easyui-dialog" data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,title:'修改',
	buttons:[{text:'保存', iconCls:'icon-ok', handler:savePicture},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-picture').dialog('close');}}]">
	<form id="add-form-picture" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">图片：</td>
			<td class="mf-right" >
				<div style="margin-left: 50px;"><p style="color: red;">Ctrl+ 多选</p>
   				<input style="margin-top: 20px;" type="file" name="pictures" multiple="multiple" id="multiple_picture"/></div>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/communitys/communityContent.js"></script>
<script type="text/javascript">
	$(function(){
		dataOptions.baseUrl = '<%=thisPath%>';
		dataOptions.basePath = '<%=basePath%>';
		dataOptions.addInit = function(){};
		dataOptions.editInit = function(data){};
	});
</script>
</body>
</html>
