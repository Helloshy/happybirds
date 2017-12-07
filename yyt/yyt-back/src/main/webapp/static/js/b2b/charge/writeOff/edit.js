//加载日期控件
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
})



$(":submit").click(function () {
        	valid();
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
    
/**
 * 表单校验
 */
function valid(){
	
	$("#dataFrom").validate({
	   	 rules:{ 
	   		 id:{
		    	  required:true, 
		    	  maxlength:50
	    	  },valueFrom:{
			  	  required:true, 
			  	  number:true
			  },valueTo:{
		    	  required:true, 
		    	  number:true
			  }
	   	 }
   });
}