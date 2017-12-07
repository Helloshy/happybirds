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
	    url : webRoot + '/community/list.htm',
	    mtype : "GET",
	    datatype : "json",
	    colModel : [{
		            label : '所属燃气公司',
		            name : 'companyName',
		            width : 100,
		            sortable : false
		        },{
	                label : '小区名称',
	                name : 'itemName',
	                width : 100,
	                sortable : false
	            },{
	                label : '小区地址',
	                name : 'province',
	                width : 200,
	                sortable : false,
					formatter: function(cellvalue, options, rowObject){
						return rowObject.province + rowObject.city + rowObject.district + rowObject.address;
					}
	            }, {
	                label : '抄表人',
	                name : 'realName',
	                width : 100,
	                sortable : false
	            },  {
	                label : '所属营业网点',
	                name : 'hallName',
	                width : 100,
	                sortable : false,
	            }, {
	                label : '更新时间',
	                name : 'createTime',
	                width : 100,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return (new Date(cellvalue)).format('yyyy-MM-dd hh:mm:ss')
	                }
	            },{
	                label : '操作账号',
	                name : 'user_name',
	                width : 100,
	                sortable : false
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
		
		//新增
		$("#addFun").click(function(){
			
			var url = webRoot + "/community/addPage.htm";
			laybox = layer.open({
				title: "新增",
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
        		var url = webRoot + "/community/delete.htm";
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
			var url = webRoot + "/community/editPage.htm?id=" + cbox;
			laybox = layer.open({
				title: "编辑",
				type: 1,
				area:["700px","80%"],
				content:CommonUtil.ajax(url,"get")
			});
		});
        
        /**
    	 * 列表查询函数
    	 */
    	$("#queryFun").click(function(){
    		var itemName = $('#itemName').val();
            $("#grid-table").jqGrid('setGridParam',{ 
                url:webRoot + '/community/list.htm',
                postData:{'itemName':itemName}, //发送查询数据
                page:1 
            }).trigger("reloadGrid"); //重新载入 
    	});
});
			