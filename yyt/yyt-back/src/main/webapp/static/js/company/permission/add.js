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
    });
	
	$('#user').selectpicker({
	   });
	
	$(".child").change(function(){
		var checked = $(this).prop("checked");
		if(checked){
			var resource =".resource"+$(this).data("parent");
			$(resource).prop("checked","checked");
		}
	})
	
	
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
