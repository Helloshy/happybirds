<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String thisPath = basePath + "/web/product/manage/";
%>
<jsp:include page="../../include.jsp"></jsp:include> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script src="<%=basePath%>/js/uploadPreview/uploadPreview.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=basePath%>/pictures/picture.js"></script>
	<script type="text/javascript">
		/** 编辑 */
		var EditFormatter = function(value,row,index){
			return '<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 5px;width:50px;" onClick="gridEdit('+index+')">修改</a>'+
			'<a href="javascript:void(0)" class="easyui-linkbutton cu-lbtn" style="margin-left: 20px;width:70px;" onClick="totalCount('+index+')">添加库存</a>';
		}

		/*上下架标识*/
		var stateFormatter = function (value,row,index) {
			if (row.totalStockQty){
				return '上架'
			}
			return '下架'
		}
		//库存量
		var searchFormatters = function(value,row,index){
			var _temp ='';
			if(!value){
				value = _temp
			}
			return '<a href="javascript:void(0)" style="margin-left: 10px;color: blue;" onClick="totalCount('+index+')">'+(value == '' ? 0 : value)+'</a>';
		};

		//库存量
		var totalCount = function(rowIndex){
			mygrid.datagrid("unselectAll");
			mygrid.datagrid('selectRow', rowIndex);
			var rowData = mygrid.datagrid('getSelected');// 取到选择的数据
			var _id =rowData.id;
			var _categoryName = rowData.category_name;
			var _itemRemark = rowData.item_remark;
			var _price = rowData.price;

			var url = dataOptions.basePath+'/views/jsp/shop/productInventory.jsp?productId='+_id+'&categoryName='+_categoryName+'&itemRemark='+_itemRemark+'&itemName='+rowData.item_name;
			parent.$('#tabs').tabs('add',{
				title:"库存列表",
				content:'<iframe  style="width:100%;height:90%;" scrolling="auto" frameborder="0" src="'+url+'"></iframe>',
				closable: true
			});
		} ;

		 /*var yzundefined = function(value){
			return undefined == value ? "null" : value;
		} ; */

	</script>
</head>
<body>
	<!-- 查询条件panel 一般使用只需修改 标题:title -->
	<div id="gridTools" data-options="border:false" style="padding-top: 10px;">
		<form id="search-form" style="margin-top: 10px;text-align: center;">
			<span style="margin-left: 40px;">商品名称：<input class="easyui-textbox" type="text" name="itemName" style="width: 150px"/></span>
			<a href="javascript:void(0)" title="查询数据" style="margin-left: 30px;"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" onClick="gridSearch()">查询</a>
			<a href="javascript:void(0)" title="重置当前查询条件重新查询数据" style="margin-left: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onClick="gridReload()">重置</a>
			<a href="javascript:void(0)" title="添加一条数据" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="gridCustomAdd()">添加</a>
			<a href="javascript:void(0)" title="批量删除数据" style="margin-left: 60px;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="gridDel()">删除</a>
			<a href="javascript:void(0)" title="批量删除数据" style="margin-left: 60px;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="offShelves()">下架</a>
		</form>
	</div>

	<!--  数据列表datagrid    一般使用只需要配置 标题:title 数据加载url地址:url  data-options第二行配置是datagrid的默认配置 -->
	<table id="mygrid" style="margin-top: 10px;display: none;width: 100%" class="myResize"
		data-options="url:'<%=thisPath %>searchList.htm?',loadFilter:loadFilter,nowrap:false,pageNumber:1,
        pageSize:10,pageList:[10,20],fitColumns:true,animate: true,showFooter: false,onLoadSuccess:gridLoadSuccess,pagination:true,loading:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'item_name',width:10,align:'center'">商品名称</th>
				<th data-options="field:'category_name',width:8,align:'center'">商品分类</th>
				<th data-options="field:'item_remark',width:10,align:'center'">商品描述</th>
				<th data-options="field:'logo_path',width:10,align:'center',formatter:imageFormatter">封面详情</th>
				<th data-options="field:'price',width:10,align:'center'">价格/元</th>
				<th data-options="field:'row_seq',width:10,align:'center'">优先级</th>
				<th data-options="field:'item_comment',width:10,align:'center',formatter:htmlFormatter">商品详情</th>
				<th data-options="field:'create_time',width:10,align:'center'">添加时间</th>
				<th data-options="field:'totalStockQty',width:10,align:'center',formatter:searchFormatters">库存</th>
				<th data-options="field:'state',width:10,align:'center',formatter:stateFormatter">状态</th>
				<th data-options="field:'clicknum2',width:16,formatter:EditFormatter,align:'center'">操作</th>
			</tr>
		</thead>
	</table>

<!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
<div style="display: none;">
<div id="add-dialog" style="width: 700px;height: 650px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改',buttons:[{text:'保存', iconCls:'icon-ok', handler:save},{text:'取消',iconCls:'icon-remove',handler:function(){$('#add-dialog').dialog('close');}}]">
	<form id="add-data-form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
	<input type="hidden" name="id" />
	<input type="hidden" name="logoPath" id="logoPath" />
	<input type="hidden" name="photoId1" />
	<input type="hidden" name="photoId2" />
	<input type="hidden" name="photoId3" />
	<input type="hidden" name="photoId4" />
	<input type="hidden" name="photoId5" />
	<table class="mf-table">
		<tr class="mf-line">
			<td class="mf-left">封面图片</td>
			<td class="mf-right">
				 <div id="imgdiv"><img id="imgShow" width="100" height="100" /></div>
   				 <input type="file" id="up_img" name="file"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">轮播图片1</td>
			<td >
				<div id="imgdiv1"><img id="imgShow1" width="100" height="100" /></div>
				<input type="file" id="up_img1" name="photo1"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">轮播图片2</td>
			<td class="mf-right">
				<div id="imgdiv2"><img id="imgShow2" width="100" height="100" /></div>
				<input type="file" id="up_img2" name="photo2"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">轮播图片3</td>
			<td class="mf-right">
				<div id="imgdiv3"><img id="imgShow3" width="100" height="100" /></div>
				<input type="file" id="up_img3" name="photo3"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">轮播图片4</td>
			<td class="mf-right">
				<div id="imgdiv4"><img id="imgShow4" width="100" height="100" /></div>
				<input type="file" id="up_img4" name="photo4"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">轮播图片5</td>
			<td class="mf-right">
				<div id="imgdiv5"><img id="imgShow5" width="100" height="100" /></div>
				<input type="file" id="up_img5" name="photo5"/>
			</td>
		</tr>
		<%--<tr class="mf-line">
			<td class="mf-left">图片</td>
			<td class="mf-right">
				<div id="imgs"></div>
			</td>
		</tr>--%>

		<tr class="mf-line">
			<td class="mf-left">商品分类</td>
			<td class="mf-right">
				<input type="text" id="categoryId" name="categoryId" class="easyui-combobox" data-options="editable:false,multiple:false,valueField:'id',textField:'text',
				url:'<%=basePath %>/web/product/category/getCategoryList.htm'"  style="width: 180px"/>
				优先级<input type="text" id="rowSeq" name="rowSeq" class="easyui-textbox" data-options="required:true" style="width: 180px"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">商品名称</td>
			<td class="mf-right">
				<input type="text" id="itemName1" name="itemName" class="easyui-textbox" data-options="required:true"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">商品描述</td>
			<td class="mf-right">
				<input type="text" id="itemRemark" name="itemRemark" class="easyui-textbox" data-options="required:true"/>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">价格</td>
			<td class="mf-right">
				<input type="text" id="price" name="price" class="easyui-textbox" data-options="required:true"/>元
				<%--<a href="javascript:void(0)" title="添加一条数据" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="gridAddProductInventory()">添加库存</a>--%>
			</td>
		</tr>
		<tr class="mf-line">
			<td class="mf-left">商品详情</td>
			<td class="mf-right">
				<textarea style="width:98%;height:200px;" id="itemContent" name="itemComment"></textarea>
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
					<td class="mf-left">轮播图片：</td>
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
<script type="text/javascript" src="<%=basePath%>/views/js/shop/productManage.js"></script>
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
