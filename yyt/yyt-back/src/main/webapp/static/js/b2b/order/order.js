		var productName;
		var id;
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
			    url : webRoot + '/order/list.htm',
			    mtype : "GET",
			    datatype : "json",
			    colModel : [{
			                label : '商品',
			                name : 'productName',
			                width : 180,
			                align : "center",
			                sortable : false,
			                formatter:function(cellvalue, options, rowObject){
			                	return rowObject.product.districtId + rowObject.product.tagValue + "\n" + rowObject.product.gasRate + "Nm³/T";
			                }
			            }, {
			                label : '订单号',
			                name : 'id',
			                width : 150,
			                sortable : false
			            }, {
			                label : '配送方式',
			                name : 'delivery',
			                width : 100,
			                sortable : false
			            },{
			                label : '出厂价/挂牌价(元/吨)',
			                name : 'userProductSupply.unitPrice',
			                width : 130,
			                sortable : false
			            },{
			                label : '采购数量(吨)',
			                name : 'qty',
			                width : 100,
			                sortable : false
			            },{
			                label : '采购金额(元)',
			                name : 'unitPrice',
			                width : 100,
			                sortable : false
			            },{
			                label : '运费(元)',
			                name : 'deliveryFee',
			                width : 100,
			                sortable : false,
			                formatter:function(cellvalue, options, rowObject){
			                	if(cellvalue == "" || cellvalue == null){
			                		return "/";
			                	}else{
			                		return cellvalue;
			                	}
			                }
			            },{
			                label : '总计',
			                width : 100,
			                sortable : false,
			                formatter:function(cellvalue, options, rowObject){
			                	var deliveryFee;
			                	if(rowObject.deliveryFee == "" || rowObject.deliveryFee == null){
			                		deliveryFee = 0;
			                	}
			                	return rowObject.unitPrice + rowObject.deliveryFee;
			                }
			            },{
			                label : '订单状态',
			                name : 'state',
			                width : 100,
			                sortable : false,
			                formatter:function(cellvalue, options, rowObject){
			                	if(cellvalue == 1){
			                		return "待确认";
			                	}else if(cellvalue == 2){
			                		return "待配送";
			                	}else if(cellvalue == 3){
			                		return "待收货";
			                	}else if(cellvalue == 4){
			                		return "待完成";
			                	}else if(cellvalue == 5){
			                		return "已完成";
			                	}
			                }
			            },{
			                label : '卖方商家',
			                name : 'sellerName',
			                width : 200,
			                sortable : false
			            },{
			                label : '买方商家',
			                name : 'buyerName',
			                width : 200,
			                sortable : false
			            },
			            {
			                label : '订单时间',
			                name : 'createTime',
			                width : 150,
			                sortable : false,
			                formatter : function(cellvalue, options, rowObject) {
			                   
			                    return new Date(cellvalue).format("yyyy-MM-dd hh:mm:ss");
			                }
			            }, {
			                label : '操作',
			                width : 90,
			                align : "center",
			                fixed : true,
			                sortable : false,
			                resize : false,
			                formatter : function(cellvalue, options, rowObject) {
			                    var array = [];
			                    array.push(' <a href="javascript:void(0);" onclick="detailFun(\'' + rowObject.id
			                            + '\');" title="详情">详情</a> ');
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
			        },
			        postData:{
			        	state : $.trim($("#state").val()),
	                    productName : $.trim(productName),
	            		id : $.trim(id)
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
			
				
				$("#search").click(function(){
					var txt = $("#input-txt").val();
					
					if(isNaN(txt)){
						productName = txt;
						id = null;
					}else{
						id = txt;
						productName = null;
					}
	            	$(grid_selector).jqGrid("setGridParam",{
	            		page:1,
	                	postData:{
	                		state : $.trim($("#state").val()),
		                    productName : $.trim(productName),
		            		id : $.trim(id)
	                	},
	                	datatype: "json"
	                }).trigger("reloadGrid");
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
		        		var url = webRoot + "/order/delete.htm";
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
	        
		function detailFun(id){
			var url = webRoot + "/order/detailPage.htm?orderId=" + id;
        	laybox = layer.open({
        		title: "订单详情",
        		type: 1,
        		area:["100%","100%"],
        		content:CommonUtil.ajax(url,"get")
        	});
		}