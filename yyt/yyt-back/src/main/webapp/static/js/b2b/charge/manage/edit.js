//加载日期控件
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
})


$(":submit").click(function () {
	valid();
	var id = $("input[name='id']").val();
	var address = $("input[name='address']").val();
	var realName = $("input[name='realName']").val();
	var amount = $("input[name='amount']").val();
	var payMethod = $("input[name='payMethod']").val();
	var createTime = $("input[name='createTime']").val();

	$.jBox("iframe:" + webRoot + "/charge/manage/payment?id="+id+"&address="+address+"&realName="+realName+"&amount="+amount+
		"&payMethod="+payMethod+"&createTime="+createTime+"" , {
		title: "确认缴费",
		width: 800,
		height: 400,
		buttons: { '关闭': false }
	});

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