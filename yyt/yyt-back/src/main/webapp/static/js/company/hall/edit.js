
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
        
        
        
        /**
         * 表单校验
         */
        function valid(){
        	
        	$("#dataFrom").validate({
        	   	 rules:{ 
        	   		 province:{
        	   			af:true
        	    	  },
        	    	  city:{
        	    		  af:true
        	    	  },
        	    	  district:{
        	    		  af:true
        	    	  }
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
        	$.validator.addMethod("af",function(value,element,params){
        		if(value ==""){
        			return false;
        		}
        		return true;
        		},"请选择");    