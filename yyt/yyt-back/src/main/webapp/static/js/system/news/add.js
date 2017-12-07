var FILE_TYPE= ["jpg","jepg","gif","png"];
//设置默认文件上传
$(":submit").click(function(){
	valid();
	if($("#logoPath").val()==''){
		$("#imgError").html("请上传图片");
		return false;
	}
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

function ajaxFileUpload() {
		$("#uploadImg").hide();
		$("#loadding").show();
		$("#localImag").hide();
		//设置上传文件参数
	    $.ajaxFileUpload({
	            url: contextPath+'/system/news/uploadImg.htm', //用于文件上传的服务器端请求地址
	            secureuri: false, //是否需要安全协议，一般设置为false
	            fileElementId: 'file', //文件上传域的ID
	            dataType: 'text', 
	            success:function(data, status){//服务器响应成功时的处理函数
	    			var jsonData = $.parseJSON(data);
	    			if(jsonData.status == 0){
	    				//设置文件上传状态
	    				//设置文件服务器文件保存路径
	    				$("#loadding").hide();
	    				$("#logoPath").val(jsonData.url);
	    				$("#localImag").attr("src",fileServer+jsonData.url);
	    				$("#localImag").show();
	    				
	    			}
	    		},
	    		error:function(data, status, e){ //服务器响应失败时的处理函数
	    			//设置文件上传状态
	    			$("#loadding").hide();
	    		}
	        })
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
		layer.alert("不能上传此类型的文件！", {
			icon :5
		});
	}
	return checkStatus;
}

/**
* 选择文件后执行的函数
* @param obj
*/
function afterSelectFile(obj){
	$("#imgError").html("");
	var filePath = $(obj).val();
	//校验文件类型
	var status = checkFileType(filePath);
	if(status){
		$("#fileText").val(filePath);
		//执行文件上传
		ajaxFileUpload();
	}
}



/**
 * 表单校验
 */
function valid(){
	
	$("#dataFrom").validate({
	   	 rules:{ 
	   		link:{
		    	  required:true,
		    	  rangelength:[1,50]	
	    	  },title:{
			  	  required:true, 
			  	  rangelength:[1,50]	
			  },content:{
				  required:true 
			  }
	   	 }
   });
}



