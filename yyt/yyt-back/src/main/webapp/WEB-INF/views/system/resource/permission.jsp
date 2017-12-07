<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/plugins/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/plugins/zTree_v3/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript">
	var setting = {
		check: {
			enable: true
		},
		data : {
			simpleData : {
				enable : true,
				idKey: "id",
				pIdKey: "parentId"
			}
		}
	};
	var zNodes = ${permission};
	console.info(zNodes);
	var zTree;
	$(document).ready(function() {
		zTree = $.fn.zTree.init($("#pztree"), setting, zNodes);
		//zTree.expandAll(true);
		zTree.setting.check.chkboxType = { "Y" : "ps", "N" : "s" };
		 $.ajax({
				type : "GET",
				data : {
					"roleId" : "${param.roleId}"
				},
				url : '${pageContext.request.contextPath}/system/resource/findResByRoleId.htm',
				dataType : 'json',
				success : function(json) {
					for (index in json) {
						if(json[index].id!=undefined)
					    zTree.checkNode( zTree.getNodeByParam( "id",json[index].id ), true ); 
					};
				}
			}); 
		 $(".save").bind("click",
           function() {
			 var sNodes = zTree.getCheckedNodes(true);
				var resIds = "";
				if(sNodes != undefined && sNodes != 0){
					for(var i=0;i<sNodes.length;i++){
						resIds += sNodes[i].id+",";
					};
				}
				
				$.ajax({
					async : false, //请勿改成异步，下面有些程序依赖此请数据
					type : "POST",
					data : {
						"roleId" : "${param.roleId}",
						"resourceIds":resIds
					},
					url : '${pageContext.request.contextPath}/system/resource/addRolePermission.htm',
					dataType : 'json',
					success : function(json) {
						if (json.status == 10001) {
							layer.msg('分配成功！');
							layer.close(laybox);
						} else {
							layer.alert('分配失败！', {
								icon : 2
							});
						}
						;
					}
				});
			});
	});
</script>
<ul id="pztree" class="ztree"></ul>
<div class="btn-box" style="padding-top:10%">
    <button type="submit" class="btn btn-primary save">保存</button>
    <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
</div>
