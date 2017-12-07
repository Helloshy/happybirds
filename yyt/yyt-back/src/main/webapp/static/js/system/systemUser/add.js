    	$.ajax({
    		type: "get",
    		url: webRoot + "/system/role/selectRole.htm",
    		dataType: "json",
    		success: function(data){
    			var html = "";
    			$(data).each(function(index,item){
    				html += "<option value='" + item.id + "'>" + item.roleName + "</option>";
    			});
    			$(".selectRole").html(html);
    		}
    	});
    	
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
                    	layer.msg("新增成功");
                    	$(grid_selector).trigger("reloadGrid");
                    	layer.close(laybox);
                    }else {
                    	layer.alert(data.msg, {
    						icon : 2
    					});
                    }
                }
            };
            $("#dataFrom").ajaxForm(options);
        })