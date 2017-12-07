<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>新增</title>
</head>

<body>
	<form id="dataFrom" action="${pageContext.request.contextPath}/system/systemUser/save.htm">
		<input type="hidden" name="id" value="${sysUser.id}" />
		<input type="hidden" id="status" name="status" value="${sysUser.status}"  />
	    <div class="dialog-1">
	        <div class="layout-1">
	            <span class="layout-left">账号</span>
	            <input name="userName" type="text" value="${sysUser.userName}" class="layout-right" required/>
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">名称</span>
	            <input name="name" type="text" value="${sysUser.name}" class="layout-right" required/>
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">所属角色</span>
	            <select class="layout-right selectRole" name="roleId">
	            </select>
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">是否可用</span>
	            <input id="id-button-borders" ${sysUser.status == 1?"checked='checked'":""} type="checkbox" class="ace ace-switch ace-switch-5" />
				<span class="lbl middle"></span>
	
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">备注</span>
	            <input name="remark" value="${sysUser.remark}" type="text" class="layout-right"/>
	        </div>
	        <div class="btn-box">
	            <button type="submit" class="btn btn-primary save">保存</button>
	            <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
	        </div>
	    </div>
	</form>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/systemUser/edit.js"></script>
    <script type="text/javascript">
    	$.ajax({
    		type: "get",
    		url: webRoot + "/system/role/selectRole.htm",
    		dataType: "json",
    		success: function(data){
    			var html = "";
    			$(data).each(function(index,item){
    				if(item.id == "${systemUserRole.roleId}"){
    					html += "<option selected='selected' value='" + item.id + "'>" + item.roleName + "</option>";
    				}else{
    					html += "<option value='" + item.id + "'>" + item.roleName + "</option>";
    				}
    				
    			});
    			$(".selectRole").html(html);
    		}
    	});

    </script>
</body>



</html>
