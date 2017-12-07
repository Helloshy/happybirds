$(":submit").click(function(){
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
});


/**
 * 表单页面
 */
function valid(){
	$("#dataFrom").validate({
	   	 rules:{ 
	   		 	id:{
		    	  required:true, 
		    	  maxlength:25
	    	  }
	   	 }
  });
}