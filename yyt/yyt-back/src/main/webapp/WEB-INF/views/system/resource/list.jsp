<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<%@ include file="/common/include.jsp" %>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>菜单管理</title>

		<meta name="description" content="Dynamic tables and grids using jqGrid plugin" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />



		
	</head>

	<body class="no-skin">
		<!-- /section:basics/navbar.layout -->
		<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>

			<div class="main-content">
				<div class="main-content-inner">
					<div class="page-content">
						<div class="row">
							<div class="doc-buttons" style="padding: 10px 0;">
								<c:forEach items="${res}" var="key">
									 ${key.btn }
								</c:forEach>
							</div>
							<div class="col-xs-12">

								<table id="grid-table"></table>

								<div id="grid-pager"></div>

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div>


		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='../components/_mod/jquery.mobile.custom/jquery.mobile.custom.js'>"+"<"+"/script>");
		</script>

	<script type="text/javascript">
		$(function(){
			var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
			$(window).on('resize.jqGrid', function() {
			    $(grid_selector).jqGrid('setGridWidth', $(".page-content").width());
			})
			var parent_column = $(grid_selector).closest('[class*="col-"]');
			$(document).on('settings.ace.jqGrid', function(ev, event_name, collapsed) {
			    if (event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed') {
			        setTimeout(function() {
			            $(grid_selector).jqGrid('setGridWidth', parent_column.width());
			        }, 0);
			    }
			})
			$(grid_selector).jqGrid({
			    url : webRoot + '/system/resource/list.htm',
			    mtype : "GET",
			    datatype : "json",
			    colModel : [{
			                label : '菜单名',
			                name : 'resourceName',
			                width : 100,
			                sortable : false
			            }, {
			                label : 'url',
			                name : 'url',
			                width : 200,
			                sortable : false
			            }, {
			                label : '权限标志',
			                name : 'permission',
			                width : 150,
			                sortable : false
			            }, {
			                label : '图标',
			                name : 'icon',
			                width : 150,
			                sortable : false
			            }, {
			                label : '备注',
			                name : 'remark',
			                width : 150,
			                sortable : false
			            }, {
			                label : '操作',
			                width : 100,
			                align : "center",
			                fixed : true,
			                sortable : false,
			                resize : false,
			                formatter : function(cellvalue, options, rowObject) {
			                    var array = [];
			                    array.push(' <a href="javascript:void(0);" onclick="updateAdmin(\'' + rowObject.id
			                            + '\');" title="修改"><i class="ace-icon fa fa-pencil-square-o bigger-130"></i></a> ');
			                    array.push(' <a href="javascript:void(0);" onclick="delAdmin(\'' + rowObject.id
			                            + '\');" title="删除"><i class="ace-icon fa fa-trash-o bigger-150"></i></a> ');
			                    return array.join('');
			                }
			            }],
			        caption: "数据列表",
			        hidegrid:false,
			        multiselect: true,
				    rownumbers : true,
				    viewrecords : true,
				    height : 500,
				    rowNum : 20,
				    rowList : [10, 20, 30],
				    pager : pager_selector,
				    altRows : true,
			        loadComplete : function() {
			            var table = this;
			            setTimeout(function() {
			                updatePagerIcons(table);
			            }, 0);
			        }
				}
				).navGrid(pager_selector, {
				    edit : false,
				    add : false,
				    del : false,
				    search : false,
				    refresh : true,
				    refreshicon : 'ace-icon fa fa-refresh green'
				});
				$(window).triggerHandler('resize.jqGrid');
			
				
				
				
				var grid_selector = "#grid-table";
				var pager_selector = "#grid-pager";
				$(window).on('resize.jqGrid', function() {
				    $(grid_selector).jqGrid('setGridWidth', $(".page-content").width());
				})
				var parent_column = $(grid_selector).closest('[class*="col-"]');
				$(document).on('settings.ace.jqGrid', function(ev, event_name, collapsed) {
				    if (event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed') {
				        setTimeout(function() {
				            $(grid_selector).jqGrid('setGridWidth', parent_column.width());
				        }, 0);
				    }
				})
				$(grid_selector).jqGrid({
				    url : webRoot + '/system/role/list.htm',
				    mtype : "GET",
				    datatype : "json",
				    colModel : [{
				                label : '角色名',
				                name : 'roleName',
				                width : 100,
				                sortable : false
				            }, {
				                label : '状态',
				                name : 'status',
				                width : 100,
				                sortable : false,
				                formatter : function(cellvalue, options, rowObject){
				                	if(cellvalue == 1){
				                		return "可用";
				                	}else{
				                		return "禁用";
				                	}
				                }
				            }, {
				                label : 'roleKey',
				                name : 'roleKey',
				                width : 150,
				                sortable : false
				            }, {
				                label : '创建时间',
				                name : 'createTime',
				                width : 150,
				                sortable : false
				            }, {
				                label : '操作',
				                width : 100,
				                align : "center",
				                fixed : true,
				                sortable : false,
				                resize : false,
				                formatter : function(cellvalue, options, rowObject) {
				                    var array = [];
				                    array.push(' <a href="javascript:void(0);" onclick="updateAdmin(\'' + rowObject.id
				                            + '\');" title="修改"><i class="ace-icon fa fa-pencil-square-o bigger-130"></i></a> ');
				                    array.push(' <a href="javascript:void(0);" onclick="delAdmin(\'' + rowObject.id
				                            + '\');" title="删除"><i class="ace-icon fa fa-trash-o bigger-150"></i></a> ');
				                    return array.join('');
				                }
				            }],
				        caption: "数据列表",
				        hidegrid:false,
				        multiselect: true,
					    rownumbers : true,
					    viewrecords : true,
					    height : 500,
					    rowNum : 20,
					    rowList : [10, 20, 30],
					    pager : pager_selector,
					    altRows : true,
				        loadComplete : function() {
				            var table = this;
				            setTimeout(function() {
				                updatePagerIcons(table);
				            }, 0);
				        }
					}
					).navGrid(pager_selector, {
					    edit : false,
					    add : false,
					    del : false,
					    search : false,
					    refresh : true,
					    refreshicon : 'ace-icon fa fa-refresh green'
					});
					$(window).triggerHandler('resize.jqGrid');
				
					
					
					
			        //删除
			        $("#delFun").click(function(){
			        	var cbox = $(grid_selector).jqGrid('getGridParam','selarrrow');
			        	if(cbox == ""){
			        		layer.alert("请选择删除项！", {
								icon : 0
							});
			        		return;
			        	}
			        	layer.confirm("是否删除？",function(index){
			        		var url = webRoot + "/system/resource/delete.htm";
			        		var s = CommonUtil.ajax(url, 
			        								"post",
			        								{ids: cbox.join(",")},
			        								"json");
			        		if(s.status == 10001){
			        			layer.msg("删除成功！");
			        			var reccount = $(grid_selector).getGridParam("reccount");//当前页行数
			        			if(reccount == cbox.length){
			        				var prevPage = $(grid_selector).getGridParam("page") - 1;
			        				$(grid_selector).jqGrid("setGridParam",{
			        					page:prevPage
			        				}).trigger("reloadGrid");
			        			}else{
			        				$(grid_selector).trigger("reloadGrid");
			        			}
			        		}else{
			        			layer.alert("删除失败！", {
									icon : 2
								});
			        		}
			        	});
			        });
			});
	</script>
	</body>
</html>
