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
			    url : webRoot + '/employee/list.htm',
			    mtype : "GET",
			    datatype : "json",
			    colModel : [{
			                label : '所属燃气公司',
			                name : 'companyName',
			                width : 100,
			                sortable : false
			            }, {
			                label : '员工账号',
			                name : 'userName',
			                width : 100,
			                sortable : false
			            }, {
			                label : '员工姓名',
			                name : 'realName',
			                width : 100,
			                sortable : false
			            }, {
			                label : '所在部门',
			                name : 'departmentTag',
			                width : 100,
			                sortable : false
			            }, {
			                label : '权限组别',
			                name : 'role',
			                width : 100,
			                sortable : false
			            }, {
			                label : '所属营业网点',
			                name : 'hallName',
			                width : 100,
			                sortable : false
			            }, {
			                label : '职位',
			                name : 'position',
			                width : 100,
			                sortable : false
			            }, {
			                label : '联系方式',
			                name : 'tel',
			                width : 100,
			                sortable : false
			            },  {
			                label : '更新时间',
			                name : 'createTime',
			                width : 100,
			                sortable : false, formatter : function(cellvalue, options, rowObject) {
			                    return (new Date(cellvalue)).format('yyyy-MM-dd hh:mm:ss')
			                }
			            }, {
			                label : '操作账号',
			                name : 'operator',
			                width : 100,
			                sortable : false
			            }],
			        caption: "数据列表",
			        hidegrid:false,
			        postData:{'userName':$.trim($("#userName").val())},
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
					var url = webRoot + "/employee/addPage.htm";
		        	laybox = layer.open({
		        		title: "新增",
		        		type: 1,
		        		area:["600px","80%"],
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
					var url = webRoot + "/employee/editPage.htm?id=" + cbox;
					laybox = layer.open({
		        		title: "编辑",
		        		type: 1,
		        		area:["600px","80%"],
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
		        		var url = webRoot + "/employee/delete.htm";
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
		        
		        /**
		    	 * 列表查询函数
		    	 */
		    	$("#queryFun").click(function(){
		    		var userName = $.trim($('#userName').val());
		            $("#grid-table").jqGrid('setGridParam',{ 
		                url:webRoot + '/employee/list.htm',
		                postData:{'userName':userName}, //发送查询数据
		                page:1 
		            }).trigger("reloadGrid"); //重新载入 
		    	});
		})
	        