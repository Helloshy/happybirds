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
		<input type="hidden" id="status" name="status" value="1"  />
	    <div class="dialog-1">
	        <div class="layout-1">
	            <span class="layout-left">账号</span>
	            <input name="userName" type="text" class="layout-right" required/>
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">密码</span>
	            <input name="pwd" type="password" class="layout-right" required/>
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">重复密码</span>
	            <input name="repwd" type="password" class="layout-right" required/>
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">名称</span>
	            <input name="name" type="text" class="layout-right" required/>
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">所属角色</span>
	            <select class="layout-right selectRole" name="roleId">
	            </select>
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">是否可用</span>
	            <input id="id-button-borders" checked="checked" type="checkbox" class="ace ace-switch ace-switch-5" />
				<span class="lbl middle"></span>
	
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">备注</span>
	            <input name="remark" type="text" class="layout-right"/>
	        </div>
	        <div class="btn-box">
	            <button type="submit" class="btn btn-primary save">保存</button>
	            <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
	        </div>
	    </div>
	</form>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/systemUser/add.js"></script>
</body>



</html>
