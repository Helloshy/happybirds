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
	    url : webRoot + '/system/account/list.htm',
	    mtype : "GET",
	    datatype : "json",
	    colModel : [{
	                label : '用户名',
	                name : 'userName',
	                width : 100,
	                sortable : false
	            },{
	                label : '账号',
	                name : 'registerPhone',
	                width : 100,
	                sortable : false,
	            }, {
	                label : '邮箱地址',
	                name : 'email',
	                width : 100,
	                sortable : false
	            },  {
	                label : '操作',
	                name : 'id',
	                width : 200,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	var operate = "<a href='company.htm?userId="+cellvalue+"' title='企业信息'>企业信息</a>&nbsp;&nbsp;"
	                		+"<a href='company.htm?userId="+cellvalue+"' title='资质认证'>资质认证</a>&nbsp;&nbsp;"	
	                		+"<a href='invoicePage.htm?userId="+cellvalue+"' title='发票管理'>发票管理</a>&nbsp;&nbsp;"	
	                		//+"<a href='javascript:void(0);' title='子账号管理'>子账号管理</a>&nbsp;&nbsp;"
	                		+"<a href='sellerPage.htm?usid="+cellvalue+"' title='卖家中心'>卖家中心</a>&nbsp;&nbsp;"
	                		+"<a href='buyerPage.htm?usid="+cellvalue+"' title='买家中心'>买家中心</a>"
	                	return operate;
	                }
	            }],
	            postData:{userName:$.trim($("#userName").val())}, //发送查询数据 
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
			var url = webRoot + "/system/appChart/editPage.htm?id=" + cbox;
			laybox = layer.open({
        		title: "编辑",
        		type: 1,
        		area:["600px","80%"],
        		content:CommonUtil.ajax(url,"get")
        	});
		});
        /**
    	 * 列表查询函数
    	 */
    	$("#queryFun").click(function(){
    		var userName = $.trim($("#userName").val());
            $("#grid-table").jqGrid('setGridParam',{ 
                url:webRoot + '/system/account/list.htm', 
                postData:{'userName':userName}, //发送查询数据 
                page:1 
            }).trigger("reloadGrid"); //重新载入 
    	});
});
			