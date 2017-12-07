<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String username = (String)request.getSession().getAttribute("username") ;
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<style text="text/css">
body { font: normal 100% Helvetica, Arial, sans-serif; }
p {text-indent: 0em;}
small { font-size: 0.875em; } 
.main { float: left; width: 98%; } 
.leftBar { float: left; width: 25%; }
img { max-width: 100%; }
object { max-width: 100%;}
img { -ms-interpolation-mode: bicubic; }
</style>

<script type="text/javascript">
function enter(index){
	if(index == 1){//安卓用户
		window.location.href = "http://fir.im/3mdt" ;
	}else if(index == 2){//ios用户 
		window.location.href = "http://fir.im/jqxk" ;
	}else if(index == 3){//安卓商家
		window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.phonestreet2" ;
	}else if(index == 4){//ios商家
		//window.location.href = "http://www.pgyer.com/TPWg" ;
	}
}
</script>
<head>
<title>分享页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<div id="small" class="main">
		<div align="center">
			<img alt="#" src="<%=path%>/images/title.png">
		</div>
		<div style="margin-top: 20px;margin-bottom: 30px;">
			<div style="height: 50px;" align="center">
<!-- 				<input type="button" value="Android" style="height:50px;width: 120px;border-radius:5px;background-color: #FB767B;border: 1px solid #FB767B;" onclick="enter(1)"/> -->
<!-- 				<input type="button" value="IPhone" style="height:50px;width: 120px;border-radius:5px;background-color: #FB767B;border: 1px solid #FB767B;margin-left: 30px;" onclick="enter(2)"/> -->
				<img alt="#" src="<%=path%>/images/android.png" width="25%" height="35px;" onclick="enter(1)">
				<img alt="#" src="<%=path%>/images/ios.png" width="25%" height="35px;" style="margin-left: 30px;" onclick="enter(2)">
			</div>
		</div>
	</div>
</body>
</html>
