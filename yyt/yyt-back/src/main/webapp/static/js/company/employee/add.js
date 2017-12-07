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
	   		userName:{
	   			checkUser:true
	    	  },
	    	  departmentTag:{
	    		  af:true
	    	  },
	    	  companyHallId:{
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
//        $(element).tooltip('destroy'); 
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

var state = false;
$.validator.addMethod("checkUser",function(value,element,params){
	
	$.ajax({
	    url:webRoot+'/employee/checkUser.htm',
	    type:'GET', 
	    async:false,   
	    data:{userName:$.trim(value)},
	    timeout:5000,   
	    dataType:'json',   
	    success:function(data){
	    	if(data.status == 20001){
	    		state = false;
	    	}else{
	    		state = true;
	    	}
	    },
	    error:function(){
	    	state = false;
	    },
	});
	return state;
	},"账号已存在");
