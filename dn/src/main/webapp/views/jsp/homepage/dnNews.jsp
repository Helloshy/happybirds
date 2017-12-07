<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/homepage/appnews/";
%>
<jsp:include page="../../include.jsp"></jsp:include> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script src="<%=basePath%>/js/uploadPreview/uploadPreview.js" type="text/javascript"></script>
	<script type="text/javascript">
		var qFormatter = function(value,row,index){
			var html = "是";
			var ind = 0;
			if(row.isQuality == 0){
				html = '否';
				ind = 1;
			}
			return '<a href="javascript:void(0)" onClick="quality('+index+','+ind+')">'+html+'</a>';
		}
		
		var topFormatter = function(value,row,index){
			var html = "是";
			var ind = 0;
			if(row.isTop == 0){
				html = '否';
				ind = 1;
			}
			return '<a href="javascript:void(0)" onClick="istop('+index+','+ind+')">'+html+'</a>';
		}
		
		var hFormatter = function(value,row,index){
			var html = "是";
			var ind = 0;
			if(row.isHome == 0){
				html = '否';
				ind = 1;
			}
			return '<a href="javascript:void(0)" onClick="ishome('+index+','+ind+')">'+html+'</a>';
		}
		
		//编辑
		var EditFormatter = function(value,row,index){
				return '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 5px;width:50px;" onClick="gridEdit('+index+')">修 改</a>'
					+ '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 20px;width:70px;" onClick="detail('+index+')">详情信息</a>';
		}
	</script>
</head>
<body>
	<!-- 查询条件panel 一般使用只需修改 标题:title -->
	<div id="gridTools" data-options="border:false">
		<form id="search-form" style="margin-top: 10px;text-align: center;">
		<span>分类名称：<input type="text" name="newsTag" class="easyui-combobox" data-options="editable:false,multiple:false,valueField:'id',textField:'text',
				url:'<%=basePath %>/web/tag/usertag/searchTagTypeList.htm?tagType=资讯分类&type=1'" style="width: 100px"/></span>
		<span style="margin-left: 30px;">标题：<input type="text" name="title" id="title-g" class="easyui-textbox" style="width: 120px"/></span>
		<a href="javascript:void(0)" title="查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
		<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
		<a href="javascript:void(0)" title="添加一条数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="gridAdd()">添加</a>
		<a href="javascript:void(0)" title="批量删除数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="gridDel()">删除</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm?recordType=3',loadFilter:loadFilter,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'newsTag',width:8,align:'center'">分类名称</th>
				<th data-options="field:'logoPath',width:10,align:'center',formatter:imageFormatter">图片</th>
				<th data-options="field:'title',width:8,align:'center'">标题</th>
				<th data-options="field:'content',width:10,align:'center',formatter:htmlFormatter">图文详情</th>
				<th data-options="field:'counts',width:6,align:'center'">浏览数</th>
				<th data-options="field:'likes',width:6,align:'center'">点赞数</th>
				<th data-options="field:'isTop',width:6,align:'center',formatter:topFormatter">置顶</th>
				<th data-options="field:'isQuality',width:6,align:'center',formatter:qFormatter">加精</th>
				<th data-options="field:'isHome',width:6,align:'center',formatter:hFormatter">是否首页推荐</th>
				<th data-options="field:'createTime',width:12,align:'center'">更新时间</th>
				<th data-options="field:'clicknum2',width:10,formatter:EditFormatter,align:'center'">操作</th>
			</tr>
		</thead>
	</table>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" style="width: 700px;height: 700px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改',buttons:[{text:'保存', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="id" />
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">分类名称</td>
			<td class="mf-right">
				<input type="text" id="newsTag" name="newsTag" class="easyui-combobox"  data-options="required:true,editable:false,multiple:false,valueField:'id',textField:'text',
				url:'<%=basePath %>/web/tag/usertag/searchTagTypeList.htm?tagType=资讯分类'" style="width: 200px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">图片</td>
			<td class="mf-right">
				 <div id="imgdiv"><img id="imgShow" width="300" height="200" /></div>
   				 <input type="file" id="up_img" name="file"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">标题</td>
			<td class="mf-right">
				<input type="text" id="title" name="title" class="easyui-textbox" data-options="required:true" style="width: 200px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">置顶</td>
			<td class="mf-right">
				&nbsp;<input type="radio" name="isTop" value="1"/>是
				&nbsp;&nbsp;<input type="radio" name="isTop" value="0"/>否
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">加精</td>
			<td class="mf-right">
				&nbsp;<input type="radio" name="isQuality" value="1"/>是
				&nbsp;&nbsp;<input type="radio" name="isQuality" value="0"/>否
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">摘要</td>
			<td class="mf-right">
				<textarea cols="40" rows="6" id="remark" name="remark"></textarea>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">图文详情：</td>
			<td class="mf-right">
				<textarea style="width:100%;height:250px;" id="content" name="content"></textarea>
				<a href="javascript:void(0)" title="添加视频" style="float: left;margin-top: 10px;" class="easyui-linkbutton" onclick="gridAddVideo()">添加视频</a>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<div style="display: none;">
<div id="add-dialog1" style="width: 600px;height: 680px;" class="easyui-dialog" data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'详情',buttons:[{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog1').dialog('close');}}]">
	<form id="add-data-form1" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">分类名称</td>
			<td class="mf-right" id="newsTag1" colspan="3"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">图片</td>
			<td class="mf-right" colspan="3">
				 <img src="" id="imgShow1" width="300" height="180" />
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">标题</td>
			<td class="mf-right" id="title1" colspan="3"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">置顶</td>
			<td class="mf-right" id="isTop1"></td>
			<td class="mf-left">加精</td>
			<td class="mf-right" id="isQuality1"></td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">摘要</td>
			<td class="mf-right" id="remark1" colspan="3">
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">图文详情</td>
			<td class="mf-right" colspan="3">
				<textarea style="width:100%;height:200px;" id="content1"></textarea>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/homepage/news.js"></script>
<script type="text/javascript">
	$(function(){
		dataOptions.recordType = '3';
		dataOptions.baseUrl = '<%=thisPath%>';
		dataOptions.basePath = '<%=basePath%>';
		dataOptions.addInit = function(){};
		dataOptions.editInit = function(data){};
	});
</script>
</body>
</html>
