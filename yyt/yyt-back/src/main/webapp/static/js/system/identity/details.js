
/**
 * 审核 （1：通过 2，不通过）
 * @param type
 */
function approve(type){
	var msg = type == 1? '通过':'不通过';
	layer.confirm("是否"+msg+"此次申请？",function(index){
		var url = webRoot + "/system/identity/approve.htm";
		var id = $("")
		var s = CommonUtil.ajax(url, 
								"post",
								{id: $("#id").val(),usid:$("#usid").val(),type:type},
								"json");
		if(s.status == 10001){
			layer.msg("审批成功！");
			window.location = webRoot+'/system/identity/listPage.htm?id=125';
		}else{
			layer.alert("审批失败！", {
				icon : 2
			});
		}
	});
}