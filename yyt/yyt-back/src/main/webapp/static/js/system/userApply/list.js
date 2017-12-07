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
	    url : webRoot + '/system/userApply/list.htm',
	    mtype : "GET",
	    datatype : "json",
	    colModel : [{
	                label : '昵称',
	                name : 'nickName',
	                width : 100,
	                sortable : false
	            },{
	                label : '注册账号',
	                name : 'userName',
	                width : 100,
	                sortable : false,
	            },{
	                label : '联系方式',
	                name : 'phone',
	                width : 100,
	                sortable : false
	            },{
	                label : '留言',
	                name : 'remark',
	                width : 100,
	                sortable : false
	            },{
	                label : '联系状态',
	                name : 'contactStatus',
	                width : 100,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return cellvalue == 1 ? '已联系':'未联系';
	                }
	            },{
	                label : '更新时间',
	                name : 'createTime',
	                width : 100,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return (new Date(cellvalue)).format('yyyy-MM-dd hh:mm:ss')
	                }
	            },{
	                label : '操作',
	                name : 'id',
	                width : 100,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return '<a href="javascript:void(0);" onclick="showDetail(\''+cellvalue+'\')">详细信息</a>';
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
        		var url = webRoot + "/system/userApply/delete.htm";
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
         * 设置为已联系
         */
        $("#contactFun").click(function(){
        	var cbox = $(grid_selector).jqGrid('getGridParam','selarrrow');
        	if(cbox == ""){
        		layer.alert("请选择需要设置为已联系的项！", {
					icon : 0
				});
        		return;
        	}
        	var ids = new Array();
        	for(var i = 0 ;i<cbox.length;i++){
        		var rowData = $(grid_selector).jqGrid("getRowData",cbox[i]);
                var val= rowData.contactStatus;
                if(val == '未联系'){
                	ids.push(cbox[i]);
                }
        	}
        	
        	if(ids.length == 0){
        		layer.alert("所选项均为已联系状态，无需设置！", {
					icon : 0
				});
        		return;
        	}
            
        	layer.confirm("是否设置为已联系？",function(index){
        		var url = webRoot + "/system/userApply/contact.htm";
        		var s = CommonUtil.ajax(url, 
        								"post",
        								{ids: cbox.join(",")},
        								"json");
        		if(s.status == 10001){
        			layer.msg("设置成功！");
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
        			layer.alert("设置失败！", {
						icon : 2
					});
        		}
        	});
        })
        
        /**
    	 * 列表查询函数
    	 */
    	$("#queryFun").click(function(){
    		var contactStatus = $("#contactStatus").val();
    		if(contactStatus == 'all'){
    			contactStatus = null ;
    		}
    		var userName = $.trim($("#userName").val());
            $("#grid-table").jqGrid('setGridParam',{ 
                url:webRoot + '/system/userApply/list.htm', 
                postData:{'contactStatus':contactStatus,'userName':userName}, //发送查询数据 
                page:1 
            }).trigger("reloadGrid"); //重新载入 
    	});
});

/**
 * 看看详细信息
 * @param id 申请id
 */
function showDetail(id){
	
	var url = webRoot + "/system/userApply/viewPage.htm?id="+id;
	laybox = layer.open({
		title: "<center>详细信息</center>",
		type: 1,
		area:["600px","60%"],
		content:CommonUtil.ajax(url,"get")
	});
	
	
}
			