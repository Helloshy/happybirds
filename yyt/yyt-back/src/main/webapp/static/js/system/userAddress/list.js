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
	    url : webRoot + '/customer/list/list.htm',
	    mtype : "GET",
	    datatype : "json",
	    colModel : [{
	                label : '商家名称',
	                name : 'companyName',
	                width : 160,
	                align :'center',
	                sortable : false
	            },{
	                label : '注册账号',
	                name : 'registerPhone',
	                width : 130,
	                align :'center',
	                sortable : false,
	            }, {
	                label : '昵称',
	                name : 'nickName',
	                width : 130,
	                align :'center',
	                sortable : false
	            },  {
	                label : '地点名称',
	                name : 'itemName',
	                width : 150,
	                align :'center',
	                sortable : false,
	            }, {
	                label : '详细地址',
	                name : 'address',
	                width : 150,
	                align :'center',
	                sortable : false,
	            }, {
	                label : '存储能力（m³）',
	                name : 'store',
	                width : 150,
	                align :'center',
	                sortable : false,
	                
	            },{
	                label : '工作时间',
	                name : 'workType',
	                width : 100,
	                align :'center',
	                sortable : false,
	                edittype:'select', formatter:'select', editoptions:{value:"1:全天;"}
	            },{
	                label : '联系人',
	                name : 'contactPerson',
	                width : 130,
	                align :'center',
	                sortable : false
	            },{
	                label : '联系电话',
	                name : 'contactPhone',
	                width : 150,
	                sortable : false
	            },{
	                label : '修改时间',
	                name : 'create_time',
	                width : 160,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return (new Date(cellvalue)).format('yyyy-MM-dd hh:mm:ss')
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
		    postData:{'itemName':$.trim($("#itemName").val())},//发送查询参数
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
        		var url = webRoot + "/system/userAddress/delete.htm";
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
    		   var queryParam = $('#queryParam').val();
            $("#grid-table").jqGrid('setGridParam',{ 
                url:webRoot + '/customer/list/list.htm',
                postData:{'queryParam':queryParam}, //发送查询数据
                page:1 
            }).trigger("reloadGrid"); //重新载入 
    	});
});
			