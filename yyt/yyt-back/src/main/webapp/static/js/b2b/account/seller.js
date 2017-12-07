var laybox;

$(function(){
	sellProduct();
});

/**
 * 已卖出的商品
 */
function sellProduct(){
	$(".sellerPageContent").hide();
	$("#sellProduct").show();
	var grid_selector = "#sell-product-grid-table";
	var pager_selector = "#sell-product-grid-pager";
	
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
	    url : webRoot + '/system/account/orderList.htm',
	    mtype : "GET",
	    datatype : "json",
	    colModel : [{
	                label : '<center>商品</center>',
	                name : 'districtId',
	                width : 150,
	                sortable : false, 
                	formatter: function(cellvalue, options, rowObject){
                		return rowObject.districtId +rowObject.tagValue+rowObject.gasRate+"Nm³/T";
	                }
	            },{
	                label : '<center>订单号</center>',
	                name : 'id',
	                width : 100,
	                sortable : false, 
	            }, {
	                label : '<center>配送方式</center>',
	                name : 'delivery',
	                width : 100,
	                sortable : false
	            },  {
	                label : '<center>出厂价/挂牌价 <br/>（元/吨）</center>',
	                name : 'unitPrice',
	                width : 100,
	                sortable : false, 
	                formatter: function(cellvalue, options, rowObject){
	                	return toDecimal(cellvalue);
	                }
	            },  {
	                label : '<center>采购数量（吨）</center>',
	                name : 'qty',
	                width : 100,
	                sortable : false, 
	                formatter: function(cellvalue, options, rowObject){
	                	return toDecimal(cellvalue);
	                }
	            },  {
	                label : '<center>采购金额（元）</center>',
	                name : 'unitPrice',
	                width : 100,
	               
	                sortable : false, 
	                formatter: function(cellvalue, options, rowObject){
	                	return toDecimal(rowObject.unitPrice*rowObject.qty);
	                }
	            },  {
	                label : '<center>运费（元）</center>',
	                name : 'deliveryFee',
	                width : 100,
	                sortable : false, 
	                formatter: function(cellvalue, options, rowObject){
	                	return toDecimal(cellvalue);
	                }
	            }, {
	                label : '<center>总计（元）</center>',
	                name : 'deliveryFee',
	                width : 100,
	                sortable : false, 
	                formatter: function(cellvalue, options, rowObject){
	                	return toDecimal(rowObject.unitPrice*rowObject.qty+rowObject.deliveryFee);
	                }
	            },{
	                label : '<center>订单状态</center>',
	                name : 'state',
	                width : 100,
	                sortable : false, 
	                formatter: function(cellvalue, options, rowObject){
	                	switch(cellvalue){
	                	case 1:
	                	  return '待确认';
	                	case 2:
	                	  return '待配送'
	                	case 3:
	                	  return '待收货'
	                	case 4:
	                	  return '待完成'
	                	case 5:
	                	  return '已完成'
	                	}
	                }
	            },  {
	                label : '<center>买方商家</center>',
	                name : 'buyName',
	                width : 150,
	                sortable : false, 
	                formatter: function(cellvalue, options, rowObject){
	                	return "<a href='company.htm?userId="+rowObject.buyerId+"'>"+cellvalue+"</a>"
	                }
	            },  {
	                label : '<center>订单时间</center>',
	                name : 'createTime',
	                width : 100,
	                sortable : false
	            },  {
	                label : '<center>操作</center>',
	                name : 'id',
	                width : 100,
	                sortable : false, 
	                formatter: function(cellvalue, options, rowObject){
	                	return "<a href='javascript:void(0);' onclick='orderDetails(\""+cellvalue+"\")'>详情</a>"
	                }
	            }],
	            postData:{
	            	sellerId:$.trim($("#uid").val()),
	            	buyerId:null,
	            	state:$("#sellProductState").val()=='all'?null:$("#sellProductState").val(),
	            	searchTitle:$.trim($("#sellProductSearchTitle").val())}, //发送查询数据 
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
}
/**
 * 已卖出的商品搜索
 */
function sellProductSearch(){
	var sellProductState = $("#sellProductState").val();
	if(sellProductState == 'all'){
		sellProductState = null ;
	}
	var sellProductSearchTitle =$.trim($("#sellProductSearchTitle").val());
    $("#sell-product-grid-table").jqGrid('setGridParam',{ 
        url:webRoot + '/system/account/orderList.htm',
        postData:{
        	sellerId:$.trim($("#uid").val()),
        	buyerId:null,
        	'state':sellProductState,
        	'searchTitle':sellProductSearchTitle
        	}, //发送查询数据 
        page:1 
    }).trigger("reloadGrid"); //重新载入 
}

/**
 * 订单详情页面
 * @param id
 */
function orderDetails(id){
	var url = webRoot + "/order/detailPage.htm?orderId=" + id;
	detailFun("订单详情",url);
}
/**
 * 我的商品
 */
function product(){
	$(".sellerPageContent").hide();
	$("#product").show();
	var grid_selector = "#product-grid-table";
	var pager_selector = "#product-grid-pager";
	
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
	    url : webRoot + '/system/account/productList.htm?usid='+usid,
	    mtype : "GET",
	    datatype : "json",
	    colModel : [{
	                label : '<center>商品名称</center>',
	                name : 'district_id',
	                width : 150,
	                sortable : false, 
	                formatter: function(cellvalue, options, rowObject){
	                	return cellvalue+rowObject.tag_value;
	                }
	            },{
	                label : '<center>气源地</center>',
	                name : 'district_id',
	                width : 100,
	                sortable : false, 
	            }, {
	                label : '<center>气源类型</center>',
	                name : 'tag_value',
	                width : 100,
	                sortable : false
	            },  {
	                label : '<center>气化率 <br/>（标方/吨）</center>',
	                name : 'gas_rate',
	                width : 100,
	                sortable : false
	            },  {
	                label : '<center>热值<br/>（焦兆/公斤）</center>',
	                name : 'hot_value',
	                width : 100,
	                sortable : false
	            },  {
	                label : '<center>液温<br/>（℃）</center>',
	                name : 'liquid_temp',
	                width : 100,
	                sortable : false
	            },  {
	                label : '<center>密度<br/>（kg/㎥）</center>',
	                name : 'density',
	                width : 100,
	                sortable : false
	            },  {
	                label : '<center>甲烷含量<br/>（%）</center>',
	                name : 'methane_value',
	                width : 100,
	                sortable : false
	            },  {
	                label : '<center>气质报告</center>',
	                name : 'report',
	                width : 150,
	                sortable : false, 
	                formatter: function(cellvalue, options, rowObject){
	                	return "<a href='"+fileServer+rowObject.report_path+"' target='_blank'>"+cellvalue+"</a>"
	                }
	            },  {
	                label : '<center>发布时间</center>',
	                name : 'create_time',
	                width : 100,
	                sortable : false
	                
	            },  {
	                label : '<center>操作</center>',
	                name : 'id',
	                width : 100,
	                sortable : false, 
	                formatter: function(cellvalue, options, rowObject){
	                	return "<a href='javascript:void(0);'>详情</a>"
	                }
	            }],
	            postData:{
	            	uid:$.trim($("#uid").val())
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
}
/**
 * 我的商品搜索
 */
function productSearch(){
	var productName =$.trim($("#productName").val());
    $("#product-grid-table").jqGrid('setGridParam',{ 
        url:webRoot + '/system/account/productList.htm',
        postData:{usid:usid,'productName':productName}, //发送查询数据 
        page:1 
    }).trigger("reloadGrid"); //重新载入 
}



/**
 * 删除我的商品
 */
function deleteProduct(){
	var grid_selector = '#product-grid-table';
	var cbox = $(grid_selector).jqGrid('getGridParam','selarrrow');
	if(cbox == ""){
		layer.alert("请选择删除项！", {
			icon : 0
		});
		return;
	}
	layer.confirm("是否删除？",function(index){
		var url = webRoot + "/system/account/deleteProduct.htm";
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
}

/**
 * 我的商城
 */
function shop(){
	$(".sellerPageContent").hide();
	$("#shop").show();
	var state = $("#shop-state").val();
	var grid_selector = "#shop-grid-table";
	var pager_selector = "#shop-grid-pager";
	
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
	    url : webRoot + '/system/account/supplyList.htm',
	    mtype : "GET",
	    datatype : "json",
	    colModel : [{
	                label : '<center>商品名称</center>',
	                name : 'district_id',
	                width : 150,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return rowObject.district_id+rowObject.gasType;
	                }
	            },{
	                label : '<center>商品编号</center>',
	                name : 'product_id',
	                width : 100,
	                sortable : false, 
	            },   {
	                label : '<center>供货日期</center>',
	                name : 'supply_start',
	                width : 150,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return rowObject.supply_start+"&nbsp;~&nbsp;"+rowObject.supply_end;
	                }
	            },  {
	                label : '<center>配送方式</center>',
	                name : 'trans',
	                width : 100,
	                sortable : false
	            },  {
	                label : '<center>供应量</center>',
	                name : 'supply_qty',
	                width : 100,
	                sortable : false
	            },  {
	                label : '<center>供应类型</center>',
	                name : 'supply_type',
	                width : 100,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return cellvalue == '1'?'每日供应':'总供应量';
	                }
	            },  {
	                label : '<center>出厂价/挂牌价</center>',
	                name : 'unit_price',
	                width : 100,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return toDecimal(cellvalue);
	                }
	            },  {
	                label : '<center>状态</center>',
	                name : 'state',
	                width : 100,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	switch (cellvalue) {
							case 0:
								return '未上架';
							case 1:
								return '出售中';	
							case 2:
								return '已下架';
		                }
	                }
	            },  {
	                label : '<center>发布时间</center>',
	                name : 'create_time',
	                width : 100,
	                sortable : false
	                
	            },  {
	                label : '<center>操作</center>',
	                name : 'id',
	                width : 100,
	                sortable : false, 
	                formatter: function(cellvalue, options, rowObject){
	                	return "<a href='javascript:void(0);' onclick='shopDetail(\""+cellvalue+"\")'>详情</a>"
	                }
	            }],
	            postData:{
	            	usid:$.trim($("#uid").val()),
	            	state: $("#shop-state").val() == 'all' ? null :$("#shop-state").val() ,
	            	productName:$.trim($("#shop-productName").val())
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
}

/**
 * 我的商城搜索
 */
function shopSearch(){
	var state = $("#shop-state").val();
	if(state == 'all'){
		state = null; 
	}
	var productName =$.trim($("#shop-productName").val());
    $("#shop-grid-table").jqGrid('setGridParam',{ 
        url:webRoot + '/system/account/supplyList.htm',
        postData:{buyerId:usid,state:state,productName:productName}, //发送查询数据 
        page:1 
    }).trigger("reloadGrid"); //重新载入 
}

/**
 * 删除我的商城
 */
function deleteShop(){
	
	var cbox = $("#shop-grid-table").jqGrid('getGridParam','selarrrow');
	if(cbox == ""){
		layer.alert("请选择删除项！", {
			icon : 0
		});
		return;
	}
	var count = 0;
	for(var i = 0;i<cbox.length;i++){
		var state = $("#shop-grid-table").jqGrid('getRowData',cbox[i]).state;
		if($.trim(state)=='出售中'){
			count += 1;
		}
	}
	if(count == 0){
		layer.alert("所选项中不包含【出售中】状态的项！", {
			icon : 0
		});
		return;
		
	}else{
		executeDelete("#shop-grid-table",webRoot + "/system/account/deleteSupply.htm");
	}
}

function shopDetail(id){
	
	var url = webRoot + "/system/account/supplyDetails.htm?id=" + id;
	detailFun("商品详情",url);
	
}

/**
 * 我的客户
 */
function customer(){
	$(".sellerPageContent").hide();
	$("#customer").show();
	var grid_selector = "#customer-grid-table";
	var pager_selector = "#customer-grid-pager";
	
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
	    url : webRoot + '/system/account/customerList.htm',
	    mtype : "GET",
	    datatype : "json",
	    colModel : [{
	                label : '<center>用户名</center>',
	                name : 'user_name',
	                width : 100,
	                sortable : false
	            },{
	                label : '<center>账号（手机号）</center>',
	                name : 'register_phone',
	                width : 100,
	                sortable : false, 
	            },   {
	                label : '<center>公司名称</center>',
	                name : 'company_name',
	                width : 150,
	                sortable : false
	            },  {
	                label : '<center>联系人</center>',
	                name : 'contact_person',
	                width : 100,
	                sortable : false
	            },  {
	                label : '<center>联系电话</center>',
	                name : 'phone',
	                width : 100,
	                sortable : false
	            },  {
	                label : '<center>QQ号</center>',
	                name : 'qq',
	                width : 100,
	                sortable : false
	            },  {
	                label : '<center>地址</center>',
	                name : 'address',
	                width : 150,
	                sortable : false
	            },  {
	                label : '<center>添加时间</center>',
	                name : 'settlement_date',
	                width : 100,
	                sortable : false
	            }],
	            postData:{
	            	sellerId:$.trim($("#uid").val()),
	            	userName:$.trim($("#customer-userName").val())
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
}
/**
 * 我的客户搜索
 */
function customerSearch(){
	var userName =$.trim($("#customer-userName").val());
    $("#customer-grid-table").jqGrid('setGridParam',{ 
        url:webRoot + '/system/account/customerList.htm',
        postData:{sellerId:usid,userName:userName}, //发送查询数据 
        page:1 
    }).trigger("reloadGrid"); //重新载入 
	
	
	
}

/**
 * 我的账单
 */
function bill(){
	$(".sellerPageContent").hide();
	$("#bill").show();
	var grid_selector = "#bill-grid-table";
	var pager_selector = "#bill-grid-pager";
	
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
	    url : webRoot + '/system/account/billList.htm',
	    mtype : "GET",
	    datatype : "json",
	    colModel : [{
	                label : '<center>商品</center>',
	                name : 'districtId',
	                width : 150,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	//return rowObject.district_id+rowObject.tag_value+rowObject.gas_rate+"Nm³/T";
	                	return cellvalue + rowObject.tagValue + toDecimal(rowObject.gasRate)+"Nm³/T";
	                }
	            },{
	                label : '<center>订单号</center>',
	                name : 'id',
	                width : 150,
	                sortable : false, 
	            },   {
	                label : '<center>配送方式</center>',
	                name : 'delivery',
	                width : 100,
	                sortable : false
	            },  {
	                label : '<center>出厂价、挂牌价<br/>（元/吨）</center>',
	                name : 'unitPrice',
	                width : 100,
	                sortable : false
	            },  {
	                label : '<center>采购数量<br/>（吨）</center>',
	                name : 'qty',
	                width : 100,
	                sortable : false, 
	                formatter: function(cellvalue, options, rowObject){
	                	return toDecimal(cellvalue);
	                }
	            },  {
	                label : '<center>采购金额<br/>（元）</center>',
	                name : 'unitPrice',
	                width : 100,
	                sortable : false, 
	                formatter: function(cellvalue, options, rowObject){
	                	return toDecimal(rowObject.qty*cellvalue);
	                }
	            },  {
	                label : '<center>运费<br/>（元）</center>',
	                name : 'deliveryFee',
	                width : 100,
	                sortable : false, 
	                formatter: function(cellvalue, options, rowObject){
	                	return toDecimal(cellvalue);
	                }
	            },  {
	                label : '<center>气差费用<br/>（元）</center>',
	                name : 'poorGasFee',
	                width : 100,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	/**计算公式实际气差 = 装车净重 - 卸车净重
	                	合同气差 = ±200
	                	结算气差 = IF(ABS(实际气差)<=200,0,
	                			 IF(实际气差>0, 实际气差 - 200, 实际气差+ 200))
	                	采购数量 = 订单采购数量
	                	实际结算重量 = 采购数量 + 结算气差
	                	气差费用 = 结算气差/1000 * 成交价
	                	*/
//	                	var gasValue = cellvalue-rowObject.unload;
//	                	var gas = 0;
//	                	if(Math.abs(gasValue)<=200){
//	                		gas = 0;
//	                	}else if(gasValue>0){
//	                		gas = gasValue-200;
//	                	}else{
//	                		gas = gasValue+200;
//	                	}
//	                	return toDecimal(gas/1000 * rowObject.qty * rowObject.unit_price);
	                	return toDecimal(cellvalue);
	                }
	            },  {
	                label : '<center>压车费用<br/>（元）</center>',
	                name : 'pressFee',
	                width : 100,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	/**计算公式
	                	合同压车 = 6 小时
						超出合同压车时间 = 装车信息:离厂时间 - 装车信息:到厂时间 
						压车费用 = IF(超出合同压车时间 - 合同压车 > 0 ,
						(超出合同压车时间 - 合同压车)*压车处理费用(栏位:car_process), 0  )
	                	*/
	                	//超出合同压车时间
//	                	var hour = toDecimal((cellvalue - rowObject.date_arrive)/1000/3600);
//	                	return (hour-6) > 0 ? toDecimal((hour-6)* rowObject.car_process) : '0.00'
	                	return toDecimal(cellvalue);
	                		
	                }
	            },  {
	                label : '<center>结算金额</center>',
	                name : 'totalFee',
	                width : 100,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
//	                	var gasValue = rowObject.loading-rowObject.unload;
//	                	var gas = 0;
//	                	if(Math.abs(gasValue)<=200){
//	                		gas = 0;
//	                	}else if(gasValue>0){
//	                		gas = gasValue-200;
//	                	}else{
//	                		gas = gasValue+200;
//	                	}
//	                	//气差费用
//	                	var gasPoor =  gas/1000 * rowObject.qty * rowObject.unit_price;
//	                	//超出合同压车时间
//	                	var hour = (rowObject.date_leave - rowObject.date_arrive)/1000/3600;
//	                	//押车费用
//	                	var delayUnloading =  (hour-6) > 0 ? toDecimal((hour-6)* rowObject.car_process) : 0;
//	                	return toDecimal(rowObject.qty*rowObject.unit_price+rowObject.delivery_fee+parseFloat(gasPoor)+parseFloat(delayUnloading));
	                	return toDecimal(cellvalue);
	                }
	            },  {
	                label : '<center>买方商家</center>',
	                name : 'buyerName',
	                width : 150,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return "<a href='company.htm?userId="+rowObject.buyerId+"'>"+cellvalue+"</a>";
	                }
	            },  {
	                label : '<center>订单时间</center>',
	                name : 'createTime',
	                width : 150,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return new Date(cellvalue).format('yyyy-MM-dd: hh:mm:ss');
	                }
	            },  {
	                label : '<center>操作</center>',
	                name : 'id',
	                width : 70,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return "<a href='javascript:void(0);' onclick='billDetail(\""+cellvalue+"\")'>详情</a>";
	                }
	            }],
	            postData:{
	            	sellerId:$.trim($("#uid").val()),
	            	buyerId:null,
	            	searchTitle:$.trim($("#bill-searchTitle").val())
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
}

/**
 * 我的账单搜索
 * 
 */
function billSearch(){
	
    $("#bill-grid-table").jqGrid('setGridParam',{ 
        url:webRoot + '/system/account/billList.htm',
        postData:{
        	sellerId:$.trim($("#uid").val()),
        	buyerId:null,
        	searchTitle:$.trim($("#bill-searchTitle").val())
    	}, //发送查询数据 
        page:1 
    }).trigger("reloadGrid"); //重新载入 
	
}

/**
 * 我的账单详情
 * @param id 订单id
 */
function billDetail(id){
	
	var url = webRoot + "/system/account/billDetail.htm?type=1&orderId="+id;
	laybox = layer.open({
		title: "账单详情",
		type: 1,
		area:["600px","80%"],
		content:CommonUtil.ajax(url,"get")
	});
}


/**
 * 执行删除操作
 * @param documentId gridId
 * @param url 请求路径
 * @param selectMsg 未选择提示信息
 * @param confirmMsg 确认信息
 * @param successMsg 成功信息
 * @param errorMsg 失败信息
 */
function executeDelete(documentId,url,selectMsg,confirmMsg,successMsg,errorMsg){
	if(!selectMsg) selectMsg ="请选择删除项！";
	var cbox = $(documentId).jqGrid('getGridParam','selarrrow');
	if(cbox == ""){
		layer.alert(selectMsg, {
			icon : 0
		});
		return;
	}
	if(!confirmMsg) confirmMsg ="是否删除?";
	if(!successMsg) successMsg ="删除成功！";
	if(!errorMsg) errorMsg ="删除失败！";
	layer.confirm(confirmMsg,function(index){
		var cbox = $(documentId).jqGrid('getGridParam','selarrrow');
		var s = CommonUtil.ajax(url,"post",{ids: cbox.join(",")},"json");
		if(s.status == 10001){
			layer.msg(successMsg);
			var reccount = $(documentId).getGridParam("reccount");//当前页行数
			if(reccount == cbox.length){
				var prevPage = $(documentId).getGridParam("page") - 1;
				$(documentId).jqGrid("setGridParam",{
					page:prevPage
				}).trigger("reloadGrid");
			}else{
				$(documentId).trigger("reloadGrid");
			}
		}else{
			layer.alert(errorMsg, {
				icon : 2
			});
		}
	});
}



/**
 * 新建一个弹出窗口
 * @param id
 * @returns
 */
function detailFun(title,url){
	laybox = layer.open({
		title: title,
		type: 1,
		area:["100%","100%"],
		content:CommonUtil.ajax(url,"get")
	});
}