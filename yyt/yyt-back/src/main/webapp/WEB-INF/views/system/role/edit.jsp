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
	<form id="dataFrom" action="${pageContext.request.contextPath}/system/role/save.htm">
		<input type="hidden" id="status" name="status" value="${role.status}"  />
		<input type="hidden" name="id" value="${role.id}"  />
	    <div class="dialog-1">
	        <div class="layout-1">
	            <span class="layout-left">角色名</span>
	            <input name="roleName" value="${role.roleName}" type="text" class="layout-right"/>
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">roleKey</span>
	            <input name="roleKey" value="${role.roleKey}" type="text" class="layout-right"/>
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">是否可用</span>
	            <input id="id-button-borders" ${role.status == 1?"checked='checked'":""} type="checkbox" class="ace ace-switch ace-switch-5" />
				<span class="lbl middle"></span>
	
	        </div>
	        <div class="btn-box">
	            <button type="submit" class="btn btn-primary save">保存</button>
	            <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
	        </div>
	    </div>
	</form>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/role/add.js"></script>
</body>



</html>
