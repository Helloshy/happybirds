<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<%@include file="/common/include.jsp" %>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>系统管理后台</title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		
<style type="text/css">
.content-wrapper .content-tabs {
  position: relative;
  height: 41px;
  line-height: 39.5px;
  background: #fafafa;
  border-bottom: solid 2px #222d32;
  font-size: 12px;
}
.content-wrapper .content-tabs .roll-left {
  left: 0;
  border-right: solid 1px #FAFAFA;
}
.content-wrapper .content-tabs .roll-nav, .page-tabs-list {
  position: absolute;
  width: 40px;
  height: 39.5px;
  text-align: center;
  color: #475059;
  z-index: 2;
  top: 0;
}
.content-wrapper .content-tabs button {
  background: #FAFAFA;
  border: 0;
  height: 39px;
  line-height: 39px;
  width: 40px;
  outline: 0;
}
.content-wrapper .content-tabs button i {
  color: #999;
}
.content-wrapper .content-tabs nav.page-tabs {
  margin-left: 40px;

  height: 40px;
  overflow: hidden;
}
.content-wrapper .content-tabs nav.page-tabs .page-tabs-content {
  float: left;
}
.content-wrapper .content-tabs .page-tabs a.active {
  background: #222d32;
  color: #FAFAFA;
  border-right: solid 1px #2c3e50;
}
.content-wrapper .content-tabs .page-tabs a:first-child {
  padding-right: 15px;
}
.content-wrapper .content-tabs .page-tabs a {
  display: block;
  float: left;
  border-right: solid 0px #FAFAFA;
  padding: 0 15px;
  padding-right: 8px;
  text-decoration: none;
  color: #475059;
}
.content-wrapper .content-tabs .roll-right.tabRight {
  right: 120px;
}
.content-wrapper .content-tabs .roll-right {
  right: 0;
  border-left: solid 1px #FAFAFA;
}
.content-wrapper .content-tabs .roll-nav, .page-tabs-list {
  position: absolute;
  width: 40px;
  height: 39.5px;
  text-align: center;
  color: #475059;
  z-index: 2;
  top: 0;
}
.content-wrapper .content-tabs button {
  background: #FAFAFA;
  border: 0;
  height: 39px;
  line-height: 39px;
  width: 40px;
  outline: 0;
}
.content-wrapper .content-tabs button i {
  color: #999;
}
.content-wrapper .content-tabs .roll-right.btn-group {
  right: 40px;
  width: 80px;
  padding: 0;
}
.content-wrapper .content-tabs .roll-right {
  right: 0;
  border-left: solid 1px #FAFAFA;
}
.content-wrapper .content-tabs .roll-nav, .page-tabs-list {
  position: absolute;
  width: 40px;
  height: 39.5px;
  text-align: center;
  color: #475059;
  z-index: 2;
  top: 0;
}
.content-wrapper .content-tabs .roll-right.btn-group button {
  width: 80px;
}
.content-wrapper .content-tabs button {
  background: #FAFAFA;
  border: 0;
  height: 39px;
  line-height: 39px;
  width: 40px;
  outline: 0;
}
.content-wrapper .content-tabs .dropdown-menu {
  border: solid 1px #ccc;
  border-top-left-radius: 0px;
  border-top-right-radius: 0px;
  right: -1px;
}
.dropdown-menu {
  border-color: #eee;
}
.dropdown-menu>li>a {
  color: #777;
  font-size: 12px;
}
.content-wrapper .content-tabs .roll-right {
  right: 0;
  border-left: solid 1px #FAFAFA;
}
.content-wrapper .content-tabs .roll-nav, .page-tabs-list {
  position: absolute;
  width: 40px;
  height: 38.5px;
  text-align: center;
  color: #475059;
  z-index: 2;
  top: 0;
}
.content-wrapper .content-tabs button {
  background: #FAFAFA;
  border: 0;
  height: 39px;
  line-height: 39px;
  width: 40px;
  outline: 0;
}
.content-wrapper .content-tabs button i {
  color: #999;
}

.content-iframe.page-content {
  height: calc(100vh - 163px);
  padding: 0;
}
div#content-main {
  height: 100%;
}
.sidebar.menu-min .sidebar-shortcuts {
  z-index: 3;
}
.ss:hover {
	background-color: #6FB3E0;
	color: #fff;
	cursor:pointer;
	display: inline-block;
}
.ss_color {
	background-color: #6FB3E0;
	color: #fff;
	display: inline-block;
}
</style>
		<script src="${ctx}/assets/js/ace-extra.js"></script>
	</head>

	<body class="no-skin">
		<!-- #section:basics/navbar.layout -->
		<div id="navbar" class="navbar navbar-default          ace-save-state">
			<div class="navbar-container ace-save-state" id="navbar-container">
				<!-- #section:basics/sidebar.mobile.toggle -->
				<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
					<span class="sr-only">Toggle sidebar</span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>
				</button>

				<!-- /section:basics/sidebar.mobile.toggle -->
				<div class="navbar-header pull-left">
					<!-- #section:basics/navbar.layout.brand -->
					<a href="#" class="navbar-brand">
						<small>
							<i class="fa fa-leaf"></i>
							客户服务管理系统
						</small>
					</a>

					<!-- /section:basics/navbar.layout.brand -->

					<!-- #section:basics/navbar.toggle -->

					<!-- /section:basics/navbar.toggle -->
				</div>

				<!-- #section:basics/navbar.dropdown -->
				<div class="navbar-buttons navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<!-- #section:basics/navbar.user_menu -->
						<li class="light-blue dropdown-modal">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="${ctx}/assets/avatars/user.jpg" alt="Jason's Photo" />
								<span class="user-info">
									<small>欢迎,</small>
									${systemUser.userName}
								</span>
								<i class="ace-icon fa fa-caret-down"></i>
							</a>

							<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li class="edit-pwd">
									<a href="javascript:void(0)">
										<i class="ace-icon fa fa-edit"></i>
										修改密码
									</a>
								</li>
								<li class="divider"></li>

								<li>
									<a href="javascript:void(0)" class="logout">
										<i class="ace-icon fa fa-power-off"></i>
										退出
									</a>
								</li>
							</ul>
						</li>

						<!-- /section:basics/navbar.user_menu -->
					</ul>
				</div>
				<!-- /section:basics/navbar.dropdown -->
			</div><!-- /.navbar-container -->
		</div>

		<!-- /section:basics/navbar.layout -->
		<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>

			<!-- #section:basics/sidebar -->
			<div id="sidebar" class="sidebar                  responsive                    ace-save-state">
				<script type="text/javascript">
					try{ace.settings.loadState('sidebar')}catch(e){}
				</script>
				<div class="sidebar-shortcuts" id="sidebar-shortcuts" style="background-color:#fff">
					<!--===== logo ====-->
					<div style="padding-left:16px; text-align: center;">
						<!-- <img alt="" src="${ctx}/static/images/logo.png"> -->
					</div>
				</div>
				<!-- 菜单栏 -->
				<ul class="nav nav-list sidebar-menu" id="sidebar-menu">
				 <c:forEach items="${list}" var="list"> 
				
					<%--<li class="">
						 <a href="javascript:void(0)" class="dropdown-toggle">
							<i class="menu-icon fa fa-${list.icon}"></i>
							<span class="menu-text"> ${list.resourceName} </span>
							<b class="arrow fa fa-angle-down"></b>
						</a>

						<b class="arrow"></b>

						<ul class="submenu"> --%>
						
							<c:forEach items="${list.menus}" var="menu">
								<li class="menuItem menus_${list.id } menuli"  <c:if test="${list.id != parentId }">style="display:none"</c:if>>
									<a class="menuItem" href="${ctx}${menu.url}?id=${menu.id}" style="margin-left: 10px;">
										<!-- <i class="menu-icon fa fa-caret-right"></i>  -->
										
										${menu.resourceName}
									</a>
									<b class="arrow"></b>
								</li>
							</c:forEach>
							
					<%-- 	</ul>
					</li>--%>
				</c:forEach> 
				</ul><!-- /.nav-list -->

				<!-- #section:basics/sidebar.layout.minimize -->
				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>

				<!-- /section:basics/sidebar.layout.minimize -->
			</div>

			<!-- /section:basics/sidebar -->
			<div class="main-content">
				<div class="main-content-inner">
					<!-- #section:basics/content.breadcrumbs -->
					<div class="breadcrumbs ace-save-state content-wrapper" id="breadcrumbs">
						 <div  style="height: 40px;border-bottom: 1px solid #e3e3e3;">
							<div style="float: right;margin-right: 126px;">
								<c:forEach items="${list}" var="list">
									<span class="ss <c:if test="${list.id == parentId }">ss_color</c:if>" style="padding:0 15px;" onclick="changeMenu(this,'${list.id}')">${list.resourceName }</span>
								</c:forEach>
							</div>
						</div> 
						<!-- <div class="content-tabs">
							<button class="roll-nav roll-left tabLeft">
								<i class="fa fa-backward"></i>
							</button>
							<nav class="page-tabs menuTabs">
								<div class="page-tabs-content" style="margin-left: 0px;">
									<a href="javascript:;" class="menuTab active" data-id="/Home/Default">欢迎首页</a>
								</div>
							</nav>
							<button class="roll-nav roll-right tabRight">
								<i class="fa fa-forward" style="margin-left: 3px;"></i>
							</button>
							<div class="btn-group roll-nav roll-right">
								<button class="dropdown tabClose" data-toggle="dropdown">
									页签操作<i class="fa fa-caret-down" style="padding-left: 3px;"></i>
								</button>
								<ul class="dropdown-menu dropdown-menu-right">
									<li><a class="tabReload" href="javascript:void();">刷新当前</a></li>
									<li><a class="tabCloseCurrent" href="javascript:void();">关闭当前</a></li>
									<li><a class="tabCloseAll" href="javascript:void();">全部关闭</a></li>
									<li><a class="tabCloseOther" href="javascript:void();">除此之外全部关闭</a></li>
								</ul>
							</div>
							<button class="roll-nav roll-right fullscreen"><i class="fa fa-arrows-alt"></i></button>
					</div>
					 -->
						<!-- /section:basics/content.searchbox -->
					</div>

					<!-- /section:basics/content.breadcrumbs -->
					
					<div class="content-iframe page-content" style="overflow: hidden;">
						<div class="mainContent" id="content-main" style="margin: 10px; margin-bottom: 0px; padding: 0;">
							<iframe class="LRADMS_iframe" width="100%" height="100%" <%-- src="${ctx}/welcome.jsp" --%> frameborder="0" data-id="default.html"></iframe>
						</div>
					</div>
					
				</div>
			</div><!-- /.main-content -->

			<div class="footer">
				<div class="footer-inner">
					<!-- #section:basics/footer -->
					<div class="footer-content">
						<span class="bigger-120">
							<span class="blue bolder">Ace</span>
							Application &copy; 2013-2014
						</span>
					</div>

					<!-- /section:basics/footer -->
				</div>
			</div>

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div>

		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${ctx}/js/jquery.mobile.custom.js'>"+"<"+"/script>");
		</script>

		<!-- ace scripts -->
		<script src="${ctx}/assets/js/src/ace.js"></script>
		<script src="${ctx}/assets/js/src/ace.basics.js"></script>
		<script src="${ctx}/assets/js/src/ace.scrolltop.js"></script>
		<script src="${ctx}/assets/js/src/ace.ajax-content.js"></script>
		<script src="${ctx}/assets/js/src/ace.touch-drag.js"></script>
		<script src="${ctx}/assets/js/src/ace.sidebar.js"></script>
		<script src="${ctx}/assets/js/src/ace.sidebar-scroll-1.js"></script>
		<script src="${ctx}/assets/js/src/ace.widget-box.js"></script>
		<script src="${ctx}/assets/js/src/ace.settings.js"></script>
		<script src="${ctx}/assets/js/src/ace.settings-rtl.js"></script>
		<script src="${ctx}/assets/js/src/ace.settings-skin.js"></script>
		<script src="${ctx}/assets/js/src/ace.widget-on-reload.js"></script>
		<script src="${ctx}/assets/js/src/ace.searchbox-autocomplete.js"></script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript" src="${ctx}/assets/js/ace-tab.js"></script>
		<script type="text/javascript">
	
		</script>
		<script type="text/javascript">
			var moduleId = '${parentId}';
			$(".logout").click(function(){
				layer.confirm('确定要退出吗？', {
					  btn: ['确定','取消'] //按钮
					}, function(){
					  window.location.href = webRoot + "/system/logout.htm";
					});
			});
			
			$(".edit-pwd").click(function(){
				var url = webRoot + "/system/systemUser/editPwdPage.htm";
	        	laybox = layer.open({
	        		title: "修改密码",
	        		type: 1,
	        		area:["500px","260px"],
	        		content:CommonUtil.ajax(url,"get")
	        	});
			});
			
			function changeMenu(obj,menuId){
				$('.ss_color').removeClass("ss_color");
				$(obj).addClass("ss_color");
				$(".menuli").hide();
				$(".menus_"+menuId).show();
				//$(".fa-remove").trigger("click");
				defaultView(menuId);
			}
			$(function(){
				defaultView(moduleId);
			})
			function defaultView(id){
				$(".menus_"+id +" :first").find("a").trigger("click");
			}

		</script>
	</body>
</html>
