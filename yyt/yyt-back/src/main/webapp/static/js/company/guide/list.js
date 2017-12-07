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
			    url : webRoot + '/guide/list.htm',
			    mtype : "GET",
			    datatype : "json",
			    colModel : [{
			                label : '图片',
			                name : 'logoPath',
			                width : 200,
			                sortable : false,
			                formatter:function(cellvalue, options, rowObject){
			                	
			                	var url = (cellvalue.indexOf("http") == -1)? fileServer + cellvalue:cellvalue;
			                	
			                	return "<a href='" + url+ "'target='_blank' title='查看图片'>"
			                	+"<img src='" +url + "' width='100%' height='100px'/></a>"
			                }
			            }, {
			                label : '内容',
			                name : 'content',
			                width : 200,
			                sortable : false,
			                formatter: function(cellvalue, options, rowObject){
			                	var dd = cellvalue.replace(/<[^>]+>/g,"");
			                	if(dd.length>100){
			                		return dd.substring(0,	100)+"..."
			                	}
			                	return dd;
			                }
			            }, {
			                label : '是否可用',
			                name : 'status',
			                width : 100,
			                sortable : false,
			                formatter : function(cellvalue, options, rowObject){
			                	if(cellvalue == 1){
			                		return "可用";
			                	}else{
			                		return "不可用";
			                	}
			                }
			            },{
			                label : '创建时间',
			                name : 'createTime',
			                width : 100,
			                sortable : false,
			                formatter: function(cellvalue, options, rowObject){
			                	return (new Date(cellvalue)).format('yyyy-MM-dd hh:mm:ss')
			                }
			            }],
			        postData:{recordType:$("#recordType").val()},
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
					var recordType = $("#recordType").val();
					var url = webRoot + "/guide/addPage.htm?recordType="+recordType;
					laybox = layer.open({
		        		title: "新增",
		        		type: 1,
		        		area:["800px","95%"],
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
					var url = webRoot + "/guide/editPage.htm?id=" + cbox;
					laybox = layer.open({
		        		title: "编辑",
		        		type: 1,
		        		area:["800px","95%"],
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
		        		var url = webRoot + "/guide/delete.htm";
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
		})