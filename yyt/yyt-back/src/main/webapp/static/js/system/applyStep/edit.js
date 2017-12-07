$(function(){
    $('.date-picker').datetimepicker({  
        language:  'zh-CN',  
        format:'yyyy-mm-dd',
        weekStart: 1,  
        todayBtn:  1,  
        autoclose: 1,  
        todayHighlight: 1,  
        startView: 2,  
        forceParse: 0,  
        minView: "month"
    });
    $("#price").blur(function(){
    	calculateRise();
    });
    $("#transactionPrice").blur(function(){
    	calculateRise();
    });
    $(":submit").click(function () {
        var options = {
       		type : "post",
			dataType:"json",
            success: function (data) {
                if(data.status == 10001){
                	layer.msg("修改成功");
                	$(grid_selector).trigger("reloadGrid");
                	layer.close(laybox);
                }else {
                	layer.alert("修改失败！", {
						icon : 2
					});
                }
            }
        };
        $("#dataFrom").ajaxForm(options);
    })
});

/**
 * 计算涨幅
 */
function calculateRise(){
	var price = $.trim($("#price").val());
	var transactionPrice = $.trim($("#transactionPrice").val());
	if(price !=''&&transactionPrice != ''){
		var rise = price - transactionPrice;
		$("#rise").html(toDecimal(rise));
	}
	
}
