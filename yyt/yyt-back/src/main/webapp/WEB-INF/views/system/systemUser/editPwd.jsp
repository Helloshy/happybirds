<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>修改密码</title>
</head>

<body>
	<form id="dataFrom" action="${pageContext.request.contextPath}/system/systemUser/editPwd.htm">
	    <div class="dialog-1">
	        <div class="layout-1">
	            <span class="layout-left">原密码</span>
	            <input name="pwd" type="password" class="layout-right"/>
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">新密码</span>
	            <input name="newPwd" type="password" class="layout-right"/>
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">确认新密码</span>
	            <input name="reNewPwd" type="password" class="layout-right"/>
	        </div>
	        <div class="btn-box">
	            <button type="submit" class="btn btn-primary save">保存</button>
	            <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
	        </div>
	    </div>
	</form>
</body>

<script type="text/javascript">
$(":submit").click(function () {
    var options = {
   		type : "post",
			dataType:"json",
        success: function (data) {
            if(data.status == 10001){
            	layer.msg("修改成功！");
            	layer.close(laybox);
            }else if(data.status == 20005){
            	layer.alert(data.msg, {
					icon : 2
				});
            }else{
            	layer.alert("修改失败！", {
					icon : 2
				});
            }
        }
    };
    $("#dataFrom").ajaxForm(options);
})
</script>

</html>
