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
	 
	 $("#companyHallId").change(function(){
		 var value = $(this).val();
		 if(value==''){
			$("#sid option").remove();
		    $("#sid").append("<option value=''>--请选择--</option>");	
		 }else{
			 $.ajax({
				    url:webRoot+'/community/getstaff.htm',
				    type:'GET', 
				    async:true,   
				    data:{hallId:value},
				    timeout:5000,   
				    dataType:'json',   
				    success:function(data){
				    	if(data.status == 10001){
					    	var province ='<option value="">--请选择--</option>';
					    	for(var i = 0 ;i< data.data.length;i++){
					    		province += '<option value="'+data.data[i].id+'">'+data.data[i].realName+'</option>';
					    	}
					    	$("#sid option").remove();
					    	$("#sid").append(province);	
				    	}
				    },
				    error:function(){
				    },
				});
		 }
	 })
	
})
       
/**
 * 表单校验
 */
function valid(){
	
	$("#dataFrom").validate({
		 rules:{ 
			 sid:{
	    		  af:true
	    	  },
	    	  companyHallId:{
	    		  af:true
	    	  },province:{
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