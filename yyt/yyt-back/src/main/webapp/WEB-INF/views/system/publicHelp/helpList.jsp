<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<%@ include file="/common/include.jsp" %>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>帮助中心</title>

		<meta name="description" content="Dynamic tables and grids using jqGrid plugin" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<style type="text/css">
		    .widget-box{
				padding-bottom: 25px;
			}
			.widget-header{
				background: #6FB3E0;
			}
			.widget-title{
				color:#fff;
				font-size: 15px;
			}
	    </style>
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
					<!-- 搜索框开始 -->
						<div class="widget-box">
							<div class="widget-header">
								<span class="widget-title">查询</span>
							</div>
							<div class="widget-body" >
								<div class="widget-main">
									<div class="form-group">
										<div class="col-sm-9">
											<span>显示位置：</span>
						           			<select id="msgType" style="width:200px;">
						           				<option value="all">全部</option>
							            		<option value="1">常见问题</option>
								            	<option value="2">我要买气</option>
								            	<option value="3">我要卖气</option>
								            	<option value="4">交易指南</option>
								            	<option value="5">交易辅助</option>
						           			</select>
								            &nbsp;&nbsp;&nbsp;
								             <button type="button" onclick="search()" class="btn btn-sm btn-info">
												<span class="glyphicon glyphicon-search" aria-hidden="true">查询</span>
											 </button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- 搜索框结束-->
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
		var K = window.KindEditor;
		var laybox;
		var grid_selector = "#grid-table";
		var pager_selector = "#grid-pager";
		$(function(){
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
			    url : webRoot + '/system/publicHelp/helpList.htm',
			    mtype : "GET",
			    datatype : "json",
			    colModel : [{
			                label : '标题',
			                name : 'title',
			                width : 100,
			                sortable : false
			            }, {
			                label : '文案内容',
			                name : 'memo',
			                width : 200,
			                sortable : false,
			                formatter: function(cellvalue, options, rowObject){
			                	var dd = cellvalue.replace(/<[^>]+>/g,"");
			                	if(dd.length>100){
			                		return dd.substring(0,	100)+"..."
			                	}
			                	return dd;
			                }
			            },{
			                label : '显示位置',
			                name : 'msgType',
			                width : 100,
			                sortable : false,
			                formatter: function(cellvalue, options, rowObject){
			                	switch(cellvalue){
		                			case 1:
			                			return "常见问题";
		                			case 2:
			                			return "我要买气";
		                			case 3:
			                			return "我要卖气";
		                			case 4:
			                			return "交易指南";
		                			case 5:
			                			return "交易辅助";
			                	}
			                }
			            },  {
			                label : '创建时间',
			                name : 'createTime',
			                width : 150,
			                sortable : false,
			                formatter: function(cellvalue, options, rowObject){
			                	return (new Date(cellvalue)).format('yyyy-MM-dd hh:mm:ss');
			                }
			                
			            }],
			        postData:{
			        	msgType:$("#msgType").val()=='all'?null:$("#msgType").val()
	               	}, //发送查询
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
				
				//新增
				$("#addFun").click(function(){
					var url = webRoot + "/system/publicHelp/addPage.htm";
		        	laybox = layer.open({
		        		title: "新增",
		        		type: 1,
		        		area:["800px","80%"],
		        		content:CommonUtil.ajax(url,"get")
		        	});
		        	
				});
				
				//编辑
				$("#editFun").click(function(){
					var cbox = $(grid_selector).jqGrid('getGridParam','selarrrow');
		        	if(cbox == ""){
		        		layer.alert("请选择编辑项！", {
							icon : 0
						});
		        		return;
		        	}else if(cbox.length > 1){
		        		layer.alert("只能选择一项！", {
							icon : 0
						});
		        		return;
		        	}
					var url = webRoot + "/system/publicHelp/editPage.htm?type=1&id=" + cbox;
					laybox = layer.open({
		        		title: "编辑",
		        		type: 1,
		        		area:["800px","80%"],
		        		content:CommonUtil.ajax(url,"get")
		        	});
				});
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
		        		var url = webRoot + "/system/publicHelp/delete.htm";
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
		/**
         * 搜索
         */
        function search(){
            $(grid_selector).jqGrid('setGridParam',{ 
                url:webRoot + '/system/publicHelp/helpList.htm',
                postData:{
                	 msgType:$("#msgType").val()=='all'?null:$("#msgType").val()
                	}, //发送查询数据 
                page:1 
            }).trigger("reloadGrid"); //重新载入 
        }
		</script>
	</body>
</html>
