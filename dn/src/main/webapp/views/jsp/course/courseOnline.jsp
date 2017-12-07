<%@page import="com.kapphk.web.utils.ValidateUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/course/courseconline/";
	String teacherId = ValidateUtils.isBlank(request.getParameter("teacherId")) ? "" : request.getParameter("teacherId");
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
		<span>课程分类：<input type="text" name="courseGroup" id="courseGroup-g" style="width: 80px"/></span>
		<span style="margin-left: 30px;">价格：<input class="easyui-textbox" type="text" name="amount" style="width: 80px"/></span>
		<span style="margin-left: 30px;">课程标题：<input class="easyui-textbox" type="text" name="itemName" style="width: 120px"/></span>
		<span style="margin-left: 30px;">教师名称：<input class="easyui-textbox" type="text" name="teacherName" style="width: 120px"/></span>
		<a href="javascript:void(0)" title="查询数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
		<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
		<a href="javascript:void(0)" title="添加一条数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="gridAdd()">添加</a>
		<a href="javascript:void(0)" title="批量删除数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="gridDel()">删除</a>
		<a href="javascript:void(0)" title="导出" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onClick="exportExcel('<%=teacherId %>')">导出Excel</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 115%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm?teacherId=<%=teacherId %>',loadFilter:loadFilter,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'teacherName',width:6,align:'center'">主讲老师</th>
				<th data-options="field:'itemName',width:10,align:'center'">网络课程名称</th>
				<th data-options="field:'itemRemark',width:10,align:'center'">课程简介</th>
				<th data-options="field:'courseContent',width:10,align:'center',formatter:htmlFormatter">课程介绍</th>
				<th data-options="field:'courseGroup',width:6,align:'center'">课程分类</th>
				<th data-options="field:'isFinancial',width:4,align:'center',formatter:function(value,row,index){
										if(value == 0){
										 	return '否'
										}else{
											return '是'
										}
				}">财商课程</th>
				<th data-options="field:'courseType',width:4,align:'center',formatter:function(value,row,index){
										if(value == 1){
										 	return '视频'
										}else{
											return '音频'
										}
				}">课程类型</th>
				<th data-options="field:'linkType',width:4,align:'center',formatter:function(value,row,index){
										if(value == 1){
										 	return '阿里云'
										}else{
											return '优酷'
										}
				}">播放类型</th>
				<th data-options="field:'logoPath',width:10,align:'center',formatter:imageFormatter">课程封面</th>
				<th data-options="field:'amount',width:6,align:'center'">费用</th>
				<th data-options="field:'rate',width:6,align:'center'">销售提成比例</th>
				<th data-options="field:'sort',width:6,align:'center'">课程排序</th>
				<th data-options="field:'vedioLink',width:10,align:'center'">视频网址</th>
				<th data-options="field:'vedioPalys',width:4,align:'center'">播放次数</th>
				<th data-options="field:'ct',width:10,align:'center'">展示对象</th>
				<th data-options="field:'createTime',width:10,align:'center'">更新时间</th>
				<th data-options="field:'clicknum2',width:8,formatter:EditFormatter,align:'center'">操作</th>
			</tr>
		</thead>
	</table>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" style="width: 650px;height: 750px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改',buttons:[{text:'保存', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="id" />
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">课程封面</td>
			<td class="mf-right">
				<div id="imgdiv"><img id="imgShow" width="300" height="150" /></div>
   				 <input type="file" id="up_img" name="file"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">网络课程名称</td>
			<td class="mf-right">
				<input type="text" id="itemName" name="itemName" class="easyui-textbox" data-options="required:true" style="width: 200px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">课程简介</td>
			<td class="mf-right">
				<textarea name="itemRemark" id="itemRemark" cols="46" rows="5" class="textarea easyui-validatebox" data-options="required:true"></textarea>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">财商课程</td>
			<td class="mf-right">
				<input type="radio" name="isFinancial" value="0"/>否
				&nbsp;<input type="radio" name="isFinancial" value="1"/>是
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">课程分类</td>
			<td class="mf-right">
				<input type="radio" name="courseGroup" value="孩子课程"/>孩子课程
				&nbsp;<input type="radio" name="courseGroup" value="父母课程"/>父母课程
				&nbsp;<input type="radio" name="courseGroup" value="精英课程"/>精英课程
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">课程类型</td>
			<td class="mf-right">
				<input type="radio" name="courseType" value="1"/>视频
				&nbsp;<input type="radio" name="courseType" value="2"/>音频
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">课程排序</td>
			<td class="mf-right">
				<input type="text" id="sort" name="sort" class="easyui-textbox" style="width: 200px" />
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">费用</td>
			<td class="mf-right">
				<input type="text" id="amount" name="amount" class="easyui-textbox" data-options="required:true" style="width: 200px"/>
				&nbsp;公益课&nbsp;<input type="radio" name="isPublic" value="1"/>是
				&nbsp;<input type="radio" name="isPublic" value="0"/>否
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">视频网址</td>
			<td class="mf-right">
				<textarea name="vedioLink" id="vedioLink" cols="46" rows="5" class="textarea easyui-validatebox" data-options="required:true"></textarea>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">播放类型</td>
			<td class="mf-right">
				&nbsp;<input type="radio" name="linkType" value="1"/>阿里云
				&nbsp;<input type="radio" name="linkType" value="2"/>优酷
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">主讲老师</td>
			<td class="mf-right">
				<input type="text" id="teacherId" name="teacherId" class="easyui-combobox"  style="width: 200px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">讲师名称</td>
			<td class="mf-right">
				<input type="text" id="teacherName" name="teacherName" class="easyui-textbox" style="width: 200px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">讲师头像</td>
			<td class="mf-right">
				<div id="imgdiv1"><img id="imgShow1" width="300" height="150" /></div>
   				 <input type="file" id="up_img1" name="file1"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">简介</td>
			<td class="mf-right">
				<textarea name="teacherRemark" id="teacherRemark" cols="46" rows="5" class="textarea easyui-validatebox"></textarea>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">销售提成比例</td>
			<td class="mf-right">
				<input type="text" id="rate" name="rate" class="easyui-textbox" data-options="required:true" style="width: 200px"/>%
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">展示对象</td>
			<td class="mf-right">
				<input type="text" id="ct" name="ct" class="easyui-combobox" data-options="required:true,editable:false,multiple:true,valueField:'id',textField:'text',
				url:'<%=basePath %>/web/tag/usertag/searchTagTypeList.htm?tagType=身份'" style="width: 160px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">课程介绍</td>
			<td class="mf-right">
				<textarea style="width:98%;height:250px;" id="courseContent" name="courseContent"></textarea>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/course/courseOnline.js"></script>
<script type="text/javascript">
	$(function(){
		$('#teacherId').combobox({
	            valueField:'id', //值字段
	            textField:'text', //显示的字段
	            url:'<%=basePath%>'+'/web/teacher/teacher/searchTeacherList.htm?recordType=1',
	            panelHeight:300,
	            required:true,
	            editable:false,
	            multiple:false,
	            onChange:function(id){
		            if(dataOptions.status == 0){
		            	$.post('<%=basePath%>/web/teacher/teacher/data.htm?id='+id,null,function(data){
		            		if(data != null && data.status == 10001){
		            			var data = data.data.data;
		            			new uploadPreview({ UpBtn: "up_img1", DivShow: "imgdiv1", ImgShow: "imgShow1" });
								$("#imgShow1").attr("src",data.logoPath);
								$("#teacherRemark").val(data.itemRemark);
		            			$("#teacherName").textbox("setValue",data.itemName);
		            		}else{
		            			$.messager.alert("错误",ret.msg,"error");
		            		}
		            	},'json');
		            }
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
