<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>修改</title>
    <style type="text/css">
    .ke-dialog-default.ke-dialog {
	  z-index: 99999999!important;
	}
	.layout-left{
		width:18%!important;
	}
	.layout-right{
		width:50%!important;
	}
    </style>
    <script type="text/javascript">
	  	var province = '${company.province}'
		var city = '${company.city}'
		var district ='${company.district}'
    </script>
</head>

<body>
	<form id="dataFrom" action="${pageContext.request.contextPath}/company/save.htm">
	   	<input type="hidden" name="id" value="${company.id }">
		<div class="dialog-1">
	        <div class="layout-1">
	            <span class="layout-left">公司账号：</span>
	            <%--<input name="userName" value="${user.userName }">
	             <span class="layout-left">登录密码：</span>
	            <input name="pwd" value="${user.userName }"> --%>
	            <label class="layout-right">${user.userName }</label>
	        </div>
	         <div class="layout-1">
	            <span class="layout-left">公司名称：</span>
	            <input type="text" name="companyName" class="layout-right" value="${company.companyName}" required="required">
	        </div>
			<div class="layout-1">
				<span class="layout-left">所在省市：</span>
				<span>
				<select id="province" name="province" style="width:100px;">
				</select> 省
				<select id="city" name="city" style="width:100px;">
					<option value="">--请选择--</option>
				</select> 市
				<select id="district" name="district" style="width:100px;">
					<option value="" >--请选择--</option>
				</select>区
				<input type="text" name="address"  required="required" value="${company.address}">
				</span>
			</div>
			 <div class="layout-1">
	            <span class="layout-left">微信公众号APPID：</span>
	            <input value="${company.appid }" name="appid" required="required">
	            <span class="layout-left">微信公众号秘钥：</span>
	            <input value="${company.apikey }" name="apikey" required="required">
	        </div>
	         <div class="layout-1">
	            <span class="layout-left">微信商户号：</span>
	            <input value="${company.mchid }" name="mchid" required="required">
	            <span class="layout-left">微信商户秘钥：</span>
	            <input value="${company.appsecret }" name="appsecret" required="required">
	        </div>
	         <div class="layout-1">
	            <span class="layout-left">证书：</span>
	            <input type="file" name="file" id="file" onchange="preview(this)" class="layout-right" style="display:none">
	            <input type="hidden" id="filePath" name="filePath" value="${company.filePath}">
	            <a href="javascript:void(0)" onclick="$('input[id=file]').click();">选择文件</a>
	            <span id="fileName">${company.filePath}</span>
	            <span id="fileError" style="color:red"></span>
	        </div>
	        <div class="btn-box">
	            <button type="submit" class="btn btn-primary save">保存</button>
	            <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
	        </div>
	    </div>
	</form>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/loadProvince.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/company/com/edit.js"></script>
</body>
</html>
