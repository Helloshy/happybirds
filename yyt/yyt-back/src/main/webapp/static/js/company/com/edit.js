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
        

function ajaxFileUpload() {
	
		//设置上传文件参数
	    $.ajaxFileUpload({
	            url: webRoot+'/company/upload.htm', //用于文件上传的服务器端请求地址
	            secureuri: false, //是否需要安全协议，一般设置为false
	            fileElementId: 'file', //文件上传域的ID
	            dataType: 'text', 
	            success:function(data, status){//服务器响应成功时的处理函数
	    			var jsonData = $.parseJSON(data);
	    			if(jsonData.status == 0){
	    				//设置文件服务器文件保存路径
	    				$("#filePath").val(jsonData.url);
	    			}else{
	    				
	    				$("#fileError").html("文件上传失败");
	    			}
	    		},
	    		error:function(data, status, e){ 
	    			$("#fileError").html("文件上传失败");
	    		}
	        }
	    )
}

/**
 * 检验上传图片类型
 */
function checkFileType(filePath){
	var uploadFileType = filePath.substring(filePath.lastIndexOf(".")+1,filePath.length);
	var checkStatus = false;
	//校验文件格式
	for(var i= 0;i<FILE_TYPE.length;i++){
		if(uploadFileType==FILE_TYPE[i]){
			checkStatus = true;
			break;
		}
	}
	//检验失败提示信息
	if(!checkStatus){
		layer.alert("不支持此类型文件！", {
			icon :5
		});
	}
	return checkStatus;
}

/**
 * 选择文件后执行的函数
 * @param obj
 */
function preview(obj){
	
	$("#fileError").html("");
	$("#fileName").html($(obj).val());
	//校验文件类型
	//var status = checkFileType($(obj).val());
	//if(status){
		ajaxFileUpload();
	//}
}
