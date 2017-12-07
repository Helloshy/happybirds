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
	    url : webRoot + '/system/applyStep/list.htm',
	    mtype : "GET",
	    datatype : "json",
	    colModel : [{
	                label : '文案用途',
	                name : 'districtId',
	                width : 150,
	                sortable : false
	            },{
	                label : '文案内容',
	                name : 'province',
	                width : 100,
	                sortable : false,
	            },{
	                label : '修改时间',
	                name : 'createTime',
	                width : 150,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return (new Date(cellvalue)).format('yyyy-MM-dd hh:mm:ss')
	                }
	            }],
	            postData:{
	            	'districtId':$.trim($("#districtId").val())== 'all' ? null:$.trim($("#districtId").val()),
	            	'beginDate':$.trim($("#beginDate").val()),
	            	'endDate':$.trim($("#endDate").val())
	            }, //发送查询数据 
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
			var url = webRoot + "/system/appChart/addPage.htm";
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
			var url = webRoot + "/system/appChart/editPage.htm?id=" + cbox;
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
        		var url = webRoot + "/system/appChart/delete.htm";
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
    		var districtId = $("#districtId").val();
    		if(districtId == 'all'){
    			districtId = null ;
    		}
    		var beginDate =$.trim($("#beginDate").val());
    		var endDate =$.trim($("#endDate").val());
            $("#grid-table").jqGrid('setGridParam',{ 
                url:webRoot + '/system/appChart/list.htm', 
                postData:{'districtId':districtId,'beginDate':beginDate,'endDate':endDate}, //发送查询数据 
                page:1 
            }).trigger("reloadGrid"); //重新载入 
    	});
});
			