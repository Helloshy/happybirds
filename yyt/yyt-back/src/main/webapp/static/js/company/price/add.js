$(function(){
	
	 $('.date-picker').datetimepicker({  
	        language:  'zh-CN',  
	        format:'yyyy-mm-dd HH:mm',
	        weekStart: 1,  
	        todayBtn:  1,  
	        autoclose: 1,  
	        todayHighlight: 1,  
	        startView: 2,  
	        forceParse: 0,  
	        minView: "day"
	    });
	
    $(":submit").click(function () {
    	 valid();
        var options = {
       		type : "post",
			dataType:"json",
            success: function (data) {
                if(data.status == 10001){
                	layer.msg("新增成功");
                	$(grid_selector).trigger("reloadGrid");
                	layer.close(laybox);
                }else {
                	layer.alert("新增失败！", {
						icon : 2
					});
                }
            }
        };
        $("#dataFrom").ajaxForm(options);
    })
    
});


/**
 * 表单校验
 */
function valid(){
	
	$("#dataFrom").validate({
		 rules:{ 
		   	 },
		   errorClass: "error",
	       success: 'valid',
	       unhighlight: function (element, errorClass, validClass) { //验证通过
	          $(element).tooltip('destroy').removeClass(errorClass);
	       },
	       highlight: function (element, errorClass, validClass) { //未通过验证
	       }
	       ,
	       errorPlacement: function (label, element) {
	           $(element).attr('title', $(label).text()).tooltip('show'); 
	       },
	       
	   	});
	}

