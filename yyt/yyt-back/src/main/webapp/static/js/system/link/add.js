$(function(){
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
	   		linkName:{
		    	  required:true, 
		    	  rangelength:[1,100]
	    	  },url:{
	    		  url:true,
			  	  required:true, 
			  	  rangelength:[1,200]
			  }
	   	 }
   });
}