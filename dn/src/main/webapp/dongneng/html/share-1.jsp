<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String url = request.getParameter("url");
String fromUid = request.getParameter("fromUid");
fromUid = fromUid == null ? "" : fromUid ;
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cmn-Hans">

<head>
    <!-- 声明文档使用的字符编码 -->
    <meta charset="UTF-8">
    <!-- 优先使用 IE 最新版本和 Chrome -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <!-- 页面描述 -->
    <meta name="description" content="不超过150个字符"/>
    <!-- 禁用uc浏览器字体放大 -->
    <meta name="wap-font-scale" content="no">
    <!-- 页面关键词 -->
    <meta name="keywords" content=""/>
    <!-- 网页作者 -->
    <meta name="author" content="name, email@gmail.com"/>
    <!-- 添加到主屏后的标题（iOS 6 新增） -->
    <meta name="apple-mobile-web-app-title" content="标题">
    <!-- 添加 favicon icon -->
    <!-- <link rel="shortcut icon" type="image/ico" href="/favicon.ico"/> -->
    <!-- 标题 -->
    <title>分享</title>
    <script src="<%=path%>/dongneng/js/flexible_css.debug.js"></script>
    <link rel="stylesheet" href="<%=path%>/dongneng/css/share.css" media="screen" title="no title">
    <script src="<%=path%>/dongneng/js/flexible.debug.js"></script>
    <script type="text/javascript" src="<%=path%>/dongneng/js/fastclick.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>
    <script src="<%=basePath %>/js/public/md5.js"></script>
</head>

<body class="">
    <section class="mui-header mui-flex">
        <a href="#" class="fixed cell"><i class="icon"></i></a>
        <span class="cell title">动能家人</span>
        <a href="#" class="fixed cell"></a>
    </section>
    <section class="layout-type-box-1">
        <div class="layout-type-1">
            <p class="title">我的邀请码：</p>
            <div class="layout-1 mui-flex">
                <a href="#" class="fixed cell"></a>
                <span class="cell text" id="inviteCode">${requestScope.inviteCode}</span>
                <!-- <a href="#" class="fixed cell"><i class="icon"></i></a> -->
            </div>
            <p class="t-center text-box-1">邀请您加入动能这个大家庭</p>
        </div>
    </section>
    <section class="layout-type-2">
        <label class="input-box mb40">
            <input type="text" id="userName" name="userName" placeholder="请输入手机号" class="input" />
        </label>
        <label class="input-box mui-flex countdown-box mb40">
            <input type="text" id="code" name="code" placeholder="请输入验证码" class="input cell"/>
            <input type="button" value="获取验证码" style="height:40px;border-radius:0.5;-webkit-appearance: none;" onclick="time(this)"/>
        </label>
        <label class="input-box mb30">
            <input type="text" name="pwd" id="pwd" placeholder="请设置密码" class="input" />
        </label>
        <div class="protocol-box">
            <span class="pick active">同意</span>
            <a href="<%=path%>/public/findPublicDetail.do?title=用户注册协议" class="link">《用户注册协议》</a>
        </div>
    </section>
    <div class="submit">
        <button type="button" class="button" onclick="next()">注册</button>
    </div>
    <section class="mui-flex mui-bottom">
        <div class="cell title-box">邀请您加入动能这个大家庭</div>
        <div class="cell mui-flex btn-box">
            <a href="#" class="cell btn">注册</a>
            <a href="#" class="cell btn">下载</a>
        </div>
    </section>
    <script type="text/javascript">
        if ('addEventListener' in document) {
            document.addEventListener('DOMContentLoaded', function() {
                FastClick.attach(document.body);
            }, false);
        }
	
		var count = 60 ;
		var wait = count;  
		function time(o) {
			var userName = $('#userName').val() ;
			var reg= /^1[3|4|5|7|8]\d{9}$/;
			if(!reg.test(userName)){
				layerTips('手机号有误','userName');
				return;
			}
			var url = '<%=path%>/user/insertCodeAndGetCode.do' ;
			var param = {phone:userName,type:1} ;
			$.post(url,param,function(json){
				if(json.status == 10001){
					layer.msg('验证码已下发');
					reback(o) ;
				}else{
					layer.msg(json.msg);
				}
			},'json') ;
		}
		
		function next(){
			var userName = $('#userName').val() ;
			var code = $('#code').val() ;
			var pwd = $('#pwd').val();
			if(userName == ''){
				layerTips('手机号不能为空','userName') ;
				return ;
			}else if(code == ''){
				layerTips('验证码不能为空','code') ;
				return ;
			}else if(pwd == ''){
				layerTips('密码不能为空','pwd') ;
				return ;
			}
			
			
			var url = '<%=path%>/user/register.do' ;
			var param = {userName:userName,pwd:md5(pwd),inviteCode:$("#inviteCode").text(),code:code,position:"其他"} ;
			$.post(url,param,function(json){
				if(json.status == 10001){
					$('#userName').val("");
					$('#code').val("");
					$('#pwd').val("");
					layer.msg("恭喜您注册成功，请下载APP直接登录");
				}else{
					layer.msg(json.msg);
				}
			},'json') ;
		}
		
		function reback(o){
			if (wait == 0) {
		        o.removeAttribute("disabled");
		        o.value="获取验证码";
		        wait = count ;
		    } else {
		        o.setAttribute("disabled", true);  
		        o.value="重新发送(" + wait + ")";  
		        wait--;
		        setTimeout(function() {
		            reback(o) ;
		        },
		        1000) ;
		    }
		  }
		  
		  function layerTips(msg,id){
		  	 layer.tips(msg, '#'+id, {
					  tips: 3
				});
		  }
    </script>
</body>

</html>
