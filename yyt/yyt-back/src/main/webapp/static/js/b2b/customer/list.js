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
	                label : '所属公司',
	                name : 'company_name',
	                width : 160,
	                align :'center',
	                sortable : false
	            },{
	                label : '用户编号',
	                name : 'id',
	                width : 150,
	                align :'center',
	                sortable : false,
	            }, {
	                label : '用户类型',
	                name : 'record_tag',
	                width : 130,
	                align :'center',
	                sortable : false
	            },  {
	                label : '用户姓名',
	                name : 'real_name',
	                width : 150,
	                align :'center',
	                sortable : false,
	            }, {
	                label : '手机号码',
	                name : 'tel',
	                width : 150,
	                align :'center',
	                sortable : false,
	            }, {
	                label : '用气地址',
	                name : 'address',
	                width : 150,
	                align :'center',
	                sortable : false
	            },{
	                label : '开栓状态',
	                name : 'status',
	                width : 100,
	                align :'center',
	                sortable : false,
					formatter: function(cellvalue, options, rowObject){
						return cellvalue==0?'闭栓':'开栓';
					}
	            },{
	                label : '气表类型',
	                name : 'meter_type',
	                width : 130,
	                align :'center',
	                sortable : false,
					formatter: function(cellvalue, options, rowObject){
						if(rowObject.status==1){
							if(cellvalue==1){
								return '机械表';
							}
							if(cellvalue==2){
								return 'IC卡表';
							}
						}else {
							return '';
						}
					}
	            },{
	                label : '气表编号',
	                name : 'meter_no',
	                width : 150,
	                sortable : false
	            },{
	                label : '当前余额',
	                name : 'balance_amount',
	                width : 150,
	                sortable : false
	            },{
	                label : '更新时间',
	                name : 'update_time',
	                width : 160,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return (new Date(cellvalue)).format('yyyy-MM-dd hh:mm:ss')
	                }
	            },{
	                label : '操作账号',
	                name : 'user_name',
	                width : 160,
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
		    postData:{'queryParam':$.trim($("#queryParam").val())},//发送查询参数
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
        		var url = webRoot + "/customer/list/delete.htm";
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
			var url = webRoot + "/customer/list/editPage.htm?id=" + cbox;
			laybox = layer.open({
				title: "编辑",
				type: 1,
				area:["700px","90%"],
				content:CommonUtil.ajax(url,"get")
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
			