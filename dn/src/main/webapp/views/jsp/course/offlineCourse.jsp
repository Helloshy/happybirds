<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/course/offline/";
%>
<jsp:include page="../../include.jsp"></jsp:include> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script src="<%=basePath%>/js/uploadPreview/uploadPreview.js" type="text/javascript"></script>
	<script type="text/javascript">
	//编辑
	var EditFormatter = function(value,row,index){
		return '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" data-options="iconCls:\'icon-edit\'" style="margin-left: 10px;width:60px;" onClick="gridEdit('+index+')">修 改</a>'
				+ '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 10px;width:70px;" onClick="teacher('+index+')">主讲老师</a>'
				+ '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 10px;width:70px;" onClick="contact('+index+')">报名联系人</a>';
	}
	
	//首页推荐
	var irFormatter = function(value,row,index){
		var html = "是";
		var ind = 0;
			if(row.isRecommend == 0){
				html = '否';
				ind = 1;
			}
			return '<a href="javascript:void(0)" onClick="isRecommend('+index+','+ind+')">'+html+'</a>';
	}
	
	//现场成交费用
	var oaFormatter = function(value,row,index){
		return '<a href="javascript:void(0)" onClick="offlineAmount('+index+')">'+(row.offlineAmount == "" ? 0 : row.offlineAmount)+'</a>';
	}
</script>
</head>
<body>
	<!-- 查询条件panel 一般使用只需修改 标题:title -->
	<div id="gridTools" data-options="border:false" style="padding-top: 10px;">
		<form id="search-form" style="margin-top: 10px;text-align: center;">
			<span>课程系列：<input type="text" id="courseTypeId-g" name="courseTypeId" style="width: 100px"/></span>
			<span style="margin-left: 15px;">状态：<input class="easyui-combobox" type="text" id="state-g" name="state" style="width: 60px"/></span>
			<span style="margin-left: 15px;">首页推荐：<input class="easyui-combobox" type="text" id="isRecommend-g" name="isRecommend" style="width: 60px"/></span>
			<span style="margin-left: 15px;">价格：<input class="easyui-textbox" type="text" name="amount" style="width: 80px"/></span>
			<span style="margin-left: 15px;">课程标题：<input class="easyui-textbox" type="text" name="itemName" style="width: 120px"/></span>
			<a href="javascript:void(0)" title="查询数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
			<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
			<a href="javascript:void(0)" title="添加一条数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="gridAdd()">添加</a>
			<a href="javascript:void(0)" title="批量删除数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="gridDel()">删除</a>
			<a href="javascript:void(0)" title="导出" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onClick="gridExport()">导出Excel</a>
			<a href="javascript:void(0)" title="批量删除数据" style="margin-left: 30px;width:65px;" class="easyui-linkbutton" onclick="upState()">课程状态</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 115%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm',loadFilter:loadFilter,
		fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'courseTypeId',width:8,align:'center'">所属课程系列</th>
				<th data-options="field:'itemName',width:8,align:'center'">课程标题</th>
				<th data-options="field:'logoPath',width:10,align:'center',formatter:imageFormatter">课程封面</th>
				<th data-options="field:'registerDate',width:6,align:'center'">报到时间</th>
				<th data-options="field:'pcda',width:10,align:'center'">报到地点</th>
				<th data-options="field:'teacher',width:6,align:'center'">主讲老师</th>
				<th data-options="field:'contact',width:6,align:'center'">报名联系人</th>
				<th data-options="field:'amount',width:6,align:'center'">报名费用</th>
				<th data-options="field:'offlineAmount',width:6,align:'center',formatter:oaFormatter">现场成交费用</th>
				<th data-options="field:'state',width:6,align:'center',formatter:function(value,row,index){
										if(value != null){
											if(value == 1){
												return '报名中'
											}else if(value == 2){
												return '报名结束'
											}else if(value == 3){
												return '已开课'
											}
										}
				}">课程状态</th>
				<th data-options="field:'isRecommend',width:6,align:'center',formatter:irFormatter">首页推荐</th>
				<th data-options="field:'sort',width:6,align:'center'">课程排序</th>
				<th data-options="field:'createTime',width:10,align:'center'">更新时间</th>
				<th data-options="field:'clicknum2',width:18,formatter:EditFormatter,align:'center'">操作</th>
			</tr>
		</thead>
	</table>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" style="width: 750px;height: 750px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改',buttons:[{text:'保存', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="id" />
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">所属课程系列</td>
			<td class="mf-right">
				<input type="text" id="courseTypeId" name="courseTypeId" class="easyui-combobox" data-options="required:true" style="width: 200px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">课程标题</td>
			<td class="mf-right">
				<input type="text" id="itemName" name="itemName" class="easyui-textbox" data-options="required:true" style="width: 200px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">课程封面</td>
			<td class="mf-right">
				 <div id="imgdiv"><img id="imgShow" width="300" height="200" /></div>
   				 <input type="file" id="up_img" name="file"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">报到时间</td>
			<td class="mf-right">
				<input type="text" id="registerDate" name="registerDate" class="easyui-textbox" data-options="required:true" style="width: 200px"/>
				<span style="color: gray;font-size: 15px;">&nbsp;如2016-06-16</span>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">报到地点</td>
			<td class="mf-right">
				<input type="text" name="province" id="province" style="width:100px;" class="easyui-combobox" />省
				<input type="text" name="city" id="city" style="width:100px;" class="easyui-combobox" />市
				<input type="text" name="district" id="district" style="width:100px;" class="easyui-combobox" />区
				<input type="text" name="address" id="address" style="width:150px;" class="easyui-textbox" />
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">报名费用</td>
			<td class="mf-right">
				<input type="text" id="amount" name="amount" class="easyui-textbox" data-options="required:true" style="width: 200px"/>
				&nbsp;公益课&nbsp;<input type="radio" name="isPublic" value="1"/>是
				&nbsp;<input type="radio" name="isPublic" value="0"/>否
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">状态</td>
			<td class="mf-right">
				&nbsp;<input type="radio" name="state" value="1"/>报名中
				&nbsp;<input type="radio" name="state" value="2"/>报名结束
				&nbsp;<input type="radio" name="state" value="3"/>已开课
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">课程排序</td>
			<td class="mf-right">
				<input type="text" id="sort" name="sort" class="easyui-textbox" style="width: 200px" />
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">首页推荐</td>
			<td class="mf-right">
				&nbsp;<input type="radio" name="isRecommend" value="1"/>是
				<input type="radio" name="isRecommend" value="0"/>否
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">推荐课程1</td>
			<td class="mf-right">
				<input type="text" id="courseId" name="courseId" class="easyui-combobox" data-options="editable:false,multiple:false,valueField:'id',textField:'text',
				url:'<%=basePath %>/web/course/course/searchCourseList.htm?recordType=1'"  style="width: 250px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">推荐课程2</td>
			<td class="mf-right">
				<input type="text" id="courseId2" name="courseId2" class="easyui-combobox" data-options="editable:false,multiple:false,valueField:'id',textField:'text',
				url:'<%=basePath %>/web/course/course/searchCourseList.htm?recordType=1'"  style="width: 250px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">教务补贴费</td>
			<td class="mf-right">
				<input type="text" id="subsidy" name="subsidy" class="easyui-textbox" data-options="required:true" style="width: 200px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">摘要</td>
			<td class="mf-right">
				<textarea cols="40" rows="6" id="remark" name="remark"></textarea>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">课程介绍</td>
			<td class="mf-right">
				<textarea style="width:98%;height:200px;" id="courseContent" name="courseContent"></textarea>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">报到须知</td>
			<td class="mf-right">
				<textarea style="width:98%;height:200px;" id="courseRemark" name="courseRemark"></textarea>
			</td>
		</tr>
		<tr>
			<th colspan="2" style="color: red;">添加完课程基本信息之后,请去添加 主讲老师 和 报名联系人信息</th>
		</tr>
	</table>
	</form>
</div>
</div>

<!-- 线下成交费 -->
<div style="display: none;">
<div id="add-oa" style="width: 400px;height: 300px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改线下成交费',buttons:[{text:'保存', iconCls:'icon-ok', handler:saveOA},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-oa').dialog('close');}}]">
	<form id="add-oa-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="id" />
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">现场成交费</td>
			<td class="mf-right">
				<input type="text" id="offlineAmount" name="offlineAmount" class="easyui-textbox" style="width: 150px"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<!-- 线下成交费 -->
<div style="display: none;">
<div id="add-state" style="width: 400px;height: 300px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改线下成交费',buttons:[{text:'保存', iconCls:'icon-ok', handler:saveState},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-state').dialog('close');}}]">
	<form id="add-state-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="ids" />
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">课程状态</td>
			<td class="mf-right">
				<input type="text" id="state" name="state" data-options="required:true"  style="width: 150px"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/course/offlineCourse.js"></script>
<script type="text/javascript">
	$(function(){
		$('#province').combobox({
	            valueField:'id', //值字段
	            textField:'name', //显示的字段
	            url:'<%=thisPath%>'+'searchPcd.htm?type=0',
	            panelHeight:300,
	            editable:true,//不可编辑，只能选择
	            onChange:function(provinceId){
            			$('#city').combobox({
			                valueField:'id', //值字段
			                textField:'name', //显示的字段
			                url:'<%=thisPath%>'+'searchPcd.htm?type=1&id='+provinceId,
			                panelHeight:300,
			                editable:true,
			                onChange:function(cityId){
			            			$('#district').combobox({
						                valueField:'id', //值字段
						                textField:'name', //显示的字段
						                url:'<%=thisPath%>'+'searchPcd.htm?type=2&id='+cityId,
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
				url:'<%=thisPath%>'+'searchPcd.htm?type=2&id='+"",
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
