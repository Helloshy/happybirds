    	if($("#id-button-borders").prop("checked") == true){
    		$("#status").val(1);
    	}else{
    		$("#status").val(0);
    	}
    	
    	$("#id-button-borders").click(function(){
    		if($(this).prop("checked") == true){
    			$("#status").val(1);
    		}else{
        		$("#status").val(0);
        	}
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