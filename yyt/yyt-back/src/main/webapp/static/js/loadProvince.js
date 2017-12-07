/**  加载省市区    */

$(function(){
	loadData({},"#province",designateProvince);
	$("#province").change(function(){
		var value = $(this).val();
		if(value!=''){
			loadData({parentId:value,level:2},"#city",designateCity)
		}else{
			removeOption("#city");
			removeOption("#district");
		}
	})
	$("#city").change(function(){
		var value = $(this).val();
		if(value!=''){
			loadData({parentId:value,level:3},"#district",designateDistrict)
		}else{
			removeOption("#district");
		}
	})
	
})

function loadData(param,obj,callback){
	$.ajax({
	    url:webRoot+'/addr/list.htm',
	    type:'GET', 
	    async:true,   
	    data:param,
	    timeout:5000,   
	    dataType:'json',   
	    success:function(data){
	    	initSelect(data.data,obj,callback)
	    },
	    error:function(){
	        
	    },
	});
}

function initSelect(data,obj,callback){
	var province ='<option value="">--请选择--</option>';
	for(var i = 0 ;i< data.length;i++){
		province += '<option value="'+data[i].id+'">'+data[i].id+'</option>';
	}
	$(obj+" option").remove();
	$(obj).append(province);
	if(callback != 'undefined'){
		callback();
	}
	
}

function removeOption(obj){
	$(obj+" option").remove();
	$(obj).append('<option value="">--请选择--</option>');	
	
}

/**
 * 选定省
 * @returns
 */
function designateProvince(){
	if(province != 'undefined'&& province !=''){
		var option = $("#province option");
		for(var i = 0 ;i<option.length;i++){
			if($(option[i]).val() == province){
				$(option[i]).attr("selected","selected");
				$("#province").trigger("change");
				return;
			}
		}
	}
}
/**
 * 选定市
 * @returns
 */
function designateCity(){
	if(city != 'undefined'&& city !=''){
		var option = $("#city option");
		for(var i = 0 ;i<option.length;i++){
			if($(option[i]).val() == city){
				$(option[i]).attr("selected","selected");
				$("#city").trigger("change");
				return;
			}
		}
	}
}
/**
 * 选定省市区
 * @returns
 */
function designateDistrict(){
	if(district != 'undefined'&& district !=''){
		var option = $("#district option");
		for(var i = 0 ;i<option.length;i++){
			if($(option[i]).val() == district){
				$(option[i]).attr("selected","selected");
				return;
			}
		}
	}
	
}
