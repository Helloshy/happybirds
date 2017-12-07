$(function(){
	loadData();
	$('#user').selectpicker({});
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


/**
 * 加载已有权限
 * @param param
 * @param obj
 * @param callback
 */
function loadData(){
	var id = $("input[name=id]").val();
	$.ajax({
	    url:webRoot+'/permission/getResourceById.htm?id='+id,
	    type:'GET', 
	    async:true,   
	    data:{},
	    timeout:5000,   
	    dataType:'json',   
	    success:function(data){
	    	if(data.status== 10001){
	    		for(var i = 0 ;i< data.data.length;i++){
	    			var resourceId = data.data[i].resourceId;
	    			$(".resource"+resourceId).attr("checked","checked");
	    			
	    		}
	    	}
	    },
	    error:function(){
	        
	    },
	});
}

