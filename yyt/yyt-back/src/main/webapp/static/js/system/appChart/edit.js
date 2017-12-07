var provinces = {};
$(function(){
	$("#issueDate").val(new Date().format("yyyy-MM-dd"));
	
    $('.date-picker').datetimepicker({  
        language:  'zh-CN',  
        format:'yyyy-mm-dd',
        weekStart: 1,  
        todayBtn:  1,  
        autoclose: 1,  
        todayHighlight: 1,  
        startView: 2,  
        forceParse: 0,  
        minView: "month"
    });
    /*$("#price").blur(function(){
    	calculateRise();
    });
    $("#transactionPrice").blur(function(){
    	calculateRise();
    });*/
    try{
    	provinces = $.parseJSON(provinceStr);
    }catch(e){
    }
    $(":submit").click(function () {
    	valid();
    	if(!valid2())return false;
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
    $("#districtId").trigger("change");
    
});

/**
 * 表单校验
 */
function valid(){
	$.validator.addMethod("checkProvince",function(value,element,params){
		if($("#province").val()=='s'){
			return false;
		}
		return true;
		},'请选择省份');
	
	$.validator.addMethod("checkDistrictId",function(value,element,params){
		if(value=='s'){
			return false;
		}
		return true;
		},"请选择地区");
	$("#dataFrom").validate({
	   	 rules:{ 
	   		districtId:{
	   			checkDistrictId:true
	   		},
	   		province:{
	   			checkProvince:true
		   	},
	   		 issueDate:{
		    	  required:true, 
		    	  dateISO:true
	    	  },price:{
			  	  required:true, 
			  	  number:true
			  },transactionPrice:{
		    	  required:true, 
		    	  number:true
			  }
	  }
   });
	
}
/**
 * 
 */
function valid2(){
	var issueDate = $("#issueDate").val();
 	var province= $("#province").val();
 	var id= $("#id").val();
 	var state = false;
 	if(issueDate!= 's' && province!= 's'){
		$.ajax({
		    url:contextPath+'/system/appChart/validChart.htm',
		    type:'POST', 
		    async:false,    
		    data: {
	    	   	issueDate:issueDate
	    	  	 ,province:province,
	    	  	 id:id
	        },
		    timeout:5000,   
		    dataType:'json',  
		    success:function(data){
		    	if(data.checkResult){
		    		state = true;
		    		$("#issueDate-errorMsg").html("");
		    	}else{
		    		$("#issueDate-errorMsg").html(province+"&nbsp;"+issueDate+"&nbsp;数据已存在");
		    		state = false;
		    	}
		    },
		    error:function(){
		    	state = false;
		    }
		});
		return state;
 	}else{
 		return true;
 	}
}

function changeDistrict(obj){
	var districtId = $(obj).val();
	if(districtId !='s'){
		var province ='<option value="s">--请选择--</option>';
		for(var i = 0 ;i< provinces.length;i++){
			if(provinces[i].districtId == districtId||districtId=='全国'){
				province += '<option value="'+provinces[i].id+'">'+provinces[i].id+'</option>';
			}
		}
		$("#province option").remove();
    	$("#province").append(province);	
	}
}


/**
 * 计算涨幅
 */
function calculateRise(){
	var price = $.trim($("#price").val());
	var transactionPrice = $.trim($("#transactionPrice").val());
	if(price !=''&&transactionPrice != ''&&!isNaN(price)&&!isNaN(transactionPrice)){
		var rise = price - transactionPrice;
		$("#rise").html(toDecimal(rise));
	}
	
}
