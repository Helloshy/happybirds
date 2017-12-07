<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/product/order/";
%>
<jsp:include page="../../include.jsp"></jsp:include> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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

		/** 编辑 */
		var EditFormatter = function(value,row,index){
			var html='';
			if($.trim(row.state)=='待发货'){
				html+='<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 5px;width:50px;" onClick="gridDelivery('+index+')">发货</a>'
			}
			if(row.commentTime){
				html+='<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 5px;width:50px;" onClick="detail('+index+')">详情</a>'
						+'<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 5px;width:50px;" onClick="comment('+index+')">评价</a>';
                console.log(row.commentTime)
				return html
			}
			html+= '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 5px;width:50px;" onClick="detail('+index+')">详情</a>'
            return html

		}
	</script>
</head>
<body>
	<!-- 查询条件panel 一般使用只需修改 标题:title -->
	<div id="gridTools" data-options="border:false" style="padding-top: 10px;">
		<form id="search-form" style="margin-top: 10px;text-align: center;">
			<span style="margin-left: 40px;">订单号：<input class="easyui-textbox" id="orderNoQuery" type="text" name="orderNo" style="width: 150px"/></span>
			<span style="margin-left: 40px;">商品描述：<input class="easyui-textbox" id="itemRemarkQuery" type="text" name="itemRemark" style="width: 150px"/></span>
			<span style="margin-left: 40px;">收货人：<input class="easyui-textbox" id="deliveryNameQuery" type="text" name="deliveryName" style="width: 150px"/></span>
			<span style="margin-left: 40px;">订单状态：<input   type="text" id="oderStateQuery" name="state" style="width: 80px"/></span>
			<a href="javascript:void(0)" title="查询数据" style="margin-left: 100px;"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
			<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
			<%--<a href="javascript:void(0)" title="添加一条数据" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="gridDelivery()">发货</a>--%>
			<a href="javascript:void(0)" title="批量删除数据" style="margin-left: 20px;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="exportOrder()">导出表格</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 120%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm?',loadFilter:loadFilter,nowrap:false,pageNumber:1,
        pageSize:10,pageList:[10,20],fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'orderNo',width:70,align:'center'">订单号</th>
				<th data-options="field:'state',width:25,align:'center'">订单状态</th>
				<th data-options="field:'itemRemark',width:100,align:'center'">商品描述</th>
				<th data-options="field:'price',width:30,align:'center'">商品单价/元</th>
				<th data-options="field:'color',width:20,align:'center'">颜色</th>
				<th data-options="field:'size',width:15,align:'center'">尺寸</th>
				<th data-options="field:'qty',width:25,align:'center'">购买数量</th>
				<th data-options="field:'discount',width:60,align:'center'">欧币抵扣</th>
				<th data-options="field:'payAmount',width:30,align:'center'">支付金额/元</th>
				<th data-options="field:'payMethod',width:30,align:'center'">支付方式</th>
				<th data-options="field:'payTime',width:40,align:'center'">支付时间</th>
				<th data-options="field:'deliveryMethod',width:40,align:'center'">配送方式</th>
				<th data-options="field:'userName',width:35,align:'center'">购买账号</th>
				<th data-options="field:'deliveryName',width:35,align:'center'">收货人</th>
				<th data-options="field:'deliveryNo',width:50,align:'center'">物流单号</th>
				<th data-options="field:'clicknum2',width:40,formatter:EditFormatter,align:'center'">操作</th>
			</tr>
		</thead>
	</table>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" style="width: 700px;height: 650px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改',buttons:[{text:'保存', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="id" />
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">封面图片</td>
			<td class="mf-right">
				 <div id="imgdiv"><img id="imgShow" width="300" height="180" /></div>
   				 <input type="file" id="up_img" name="file"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">标题</td>
			<td class="mf-right">
				<input type="text" id="title" name="title" class="easyui-textbox" data-options="required:true" style="width: 180px"/>
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
				<textarea cols="60" rows="5" id="remark" name="remark"></textarea>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">图文详情</td>
			<td class="mf-right">
				<textarea style="width:98%;height:200px;" id="content" name="content"></textarea>
			</td>
		</tr>
	</table>
	</form>
</div>
</div>

<!-- 发货dialog buttons配置按钮默认是添加与取消按钮 -->
	<div style="display: none;">
		<div id="delivery-dialog" style="width: 400px;height: 300px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改',buttons:[{text:'保存', iconCls:'icon-ok', handler:saveDelivery},{text:'取消',iconCls:'icon-remove',handler:function(){$('#delivery-dialog').dialog('close');}}]">
			<form id="delivery-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
				<input type="hidden" name="orderNo" id="orderNo1"/>
				<table class="mf-table">
					<tr class="mf-line">
						<td class="mf-left">物流名称</td>
						<td class="mf-right">
							<input type="text" id="deliveryCompany" name="deliveryCompany" class="easyui-textbox" data-options="required:true" style="width: 180px"/>
						</td>
					</tr>
					<tr class="mf-line">
						<td class="mf-left">物流单号</td>
						<td class="mf-right">
							<input type="text" id="deliveryNo" name="deliveryNo" class="easyui-textbox" data-options="required:true" style="width: 180px"/>
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
                    <td class="mf-left">购买用户信息</td>
                </tr>
                <tr class="mf-line">
                    <td class="mf-left">用户真实姓名</td>
                    <td class="mf-right" id="realName1"></td>
                    <td class="mf-left">注册账号</td>
                    <td class="mf-right" id="userName1"></td>
                    <td class="mf-left">支付时间</td>
                    <td class="mf-right" id="payTime1"></td>
                </tr>
                <tr class="mf-line" style="height: 45px;">
                    <td class="mf-left">收货人</td>
                    <td class="mf-right" id="deliveryName1"></td>
                    <td class="mf-left">手机号码</td>
                    <td class="mf-right" id="userName2"></td>
                    <td class="mf-left">收货地址</td>
                    <td class="mf-right" id="deliveryAddress1"></td>
                </tr>
                <tr class="mf-line">
                    <td class="mf-left">商品信息</td>
                </tr>
                <tr class="mf-line">
                    <td class="mf-left">商品描述</td>
                    <td class="mf-right" id="itemRemark1"></td>
                    <td class="mf-left">商品单价</td>
                    <td class="mf-right" id="price1"></td>
                    <td class="mf-left">配送方式</td>
                    <td class="mf-right" id="deliveryAmount1"> </td>
                </tr>
                <tr class="mf-line" style="height: 45px;">
                    <td class="mf-left">颜色</td>
                    <td class="mf-right" id="color1"></td>
                    <td class="mf-left">尺寸</td>
                    <td class="mf-right" id="size1"></td>
                    <td class="mf-left">数量</td>
                    <td class="mf-right" id="qty1"></td>
                </tr>
                <tr class="mf-line">
                    <td class="mf-left">订单信息</td>
                </tr>

				<tr class="mf-line">
					<td class="mf-left">订单号</td>
					<td class="mf-right" id="orderNo1"></td>
					<td class="mf-left">订单状态</td>
					<td class="mf-right" id="state1"></td>
					<td class="mf-left">物流单号</td>
					<td class="mf-right" id="deliveryNo1"></td>
				</tr>
				<tr class="mf-line" style="height: 45px;">
					<td class="mf-left">欧币抵扣</td>
					<td class="mf-right" id="discount1"></td>
					<td class="mf-left">支付方式</td>
					<td class="mf-right" id="payMethod1"></td>
					<td class="mf-left">支付金额</td>
					<td class="mf-right" id="payAmount1"></td>
                    <td class="mf-left">支付时间</td>
                    <td class="mf-right" id="payTime2"></td>
				</tr>
			</table>
		</form>
	</div>
</div>

<!-- 商品评价 -->
<div style="display: none;">
	<div id="add-comment" style="width: 300px;height: 400px;" class="easyui-dialog" data-options="iconCls:'icon-save',resizable:true,modal:true,
closed:true,title:'详情信息',buttons:[{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-comment').dialog('close');}}]">
		<form id="add-comment-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
			<table class="mf-table">
				<tr class="mf-line">
					<td class="mf-left">评价等级</td>
                    <td class="mf-right"style="height: 30px">
                        <div id="starDiv">
                        </div>
                    </td>
				</tr>
				<tr class="mf-line" style="height: 75px;">
					<td class="mf-left">评价内容</td>
					<td class="mf-right" id="commentRemark1"></td>
				</tr>
				<tr class="mf-line" style="height: 20px;">
					<td class="mf-left">评价时间</td>
					<td class="mf-right" id="commentTime1"></td>
				</tr>
			</table>
		</form>
	</div>
</div>

<%--<script type="text/javascript" src="<%=basePath%>/raty-master/lib/jquery.raty.css"></script>--%>
	<script type="text/javascript" src="<%=basePath%>/js/public/util.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/raty-master/lib/jquery.raty.js"></script>
<script type="text/javascript" src="<%=basePath%>/views/js/shop/productOrder.js"></script>
<script type="text/javascript">
	$(function(){
		dataOptions.recordType = '6';
		dataOptions.baseUrl = '<%=thisPath%>';
		dataOptions.basePath = '<%=basePath%>';
		dataOptions.addInit = function(){};
		dataOptions.editInit = function(data){};
	});
</script>
</body>
</html>
