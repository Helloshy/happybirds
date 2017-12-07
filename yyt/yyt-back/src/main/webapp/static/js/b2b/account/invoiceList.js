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
	    url : webRoot + '/system/account/invoiceList.htm',
	    mtype : "GET",
	    datatype : "json",
	    colModel : [{
	                label : '单位名称',
	                name : 'companyName',
	                width : 150,
	                sortable : false
	            },{
	                label : '纳税人识别码',
	                name : 'taxId',
	                width : 150,
	                sortable : false,
	            }, {
	                label : '注册地址',
	                name : 'registerAddress',
	                width : 150,
	                sortable : false
	            },  {
	                label : '注册电话',
	                name : 'registerPhone',
	                width : 100,
	                sortable : false
	                
	            },  {
	                label : '开户银行',
	                name : 'bankName',
	                width : 100,
	                sortable : false
	                
	            },  {
	                label : '银行账户',
	                name : 'bankAccount',
	                width : 100,
	                sortable : false
	                
	            },  {
	                label : '采购标识',
	                name : 'recordType',
	                width : 100,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return cellvalue == '1' ?'自主采购' : '委托采购';
	                }
	            },  {
	                label : '更新时间',
	                name : 'createTime',
	                width : 100,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return (new Date(cellvalue)).format('yyyy-MM-dd hh:mm:ss')
	                }
	                
	            }],
	            postData:{uid:$.trim($("#uid").val())}, //发送查询数据 
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
        		var url = webRoot + "/system/account/deleteInvoice.htm";
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
			