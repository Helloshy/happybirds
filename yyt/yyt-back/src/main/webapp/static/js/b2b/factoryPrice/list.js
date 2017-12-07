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
		    url : webRoot + '/product/factoryPrice/list.htm',
		    mtype : "GET",
		    datatype : "json",
		    colModel : [{
							label : '描述',
							name : 'id',
							width : 100,
							sortable : false,
		            	},
						{
							label : '判断值(大卡/立方米)',
							name : 'valueFrom',
							width : 100,
							sortable : false,
							formatter : function(cellvalue, options, rowObject){
								cellvalue =cellvalue>0?cellvalue:0;
								var _valueTo = rowObject.valueTo;
								if(cellvalue>0&&_valueTo<=0){
									return cellvalue+"以上";
								}
								return cellvalue + "~" +_valueTo;
							}
						},
						{
							label : '创建时间',
							name : 'createTime',
							width : 150,
							sortable : false,
							formatter : function(cellvalue, options, rowObject) {
								return new Date(cellvalue).format('yyyy-MM-dd hh:mm:ss');
							}
		            	}
		            ],
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
			var url = webRoot + "/product/factoryPrice/addPage.htm";
        	laybox = layer.open({
        		title: "新增",
        		type: 1,
        		area:["800px","40%"],
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
			var url = webRoot + "/product/factoryPrice/editPage.htm?id=" + cbox;
			laybox = layer.open({
        		title: "编辑",
        		type: 1,
        		area:["600px","80%"],
        		content:CommonUtil.ajax(url,"get")
        	});
		});

	});
		
		