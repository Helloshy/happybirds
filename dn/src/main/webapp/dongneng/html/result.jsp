<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String result = request.getParameter("result") ;
String uid = request.getParameter("uid") ;
String msg = request.getParameter("msg") ;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cmn-Hans" class="bg-1">

<head>
    <!-- 声明文档使用的字符编码 -->
    <meta charset="UTF-8">
    <!-- 优先使用 IE 最新版本和 Chrome -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <!-- 页面描述 -->
    <meta name="description" content="不超过150个字符" />
    <!-- 禁用uc浏览器字体放大 -->
    <meta name="wap-font-scale" content="no">
    <!-- 页面关键词 -->
    <meta name="keywords" content="" />
    <!-- 网页作者 -->
    <meta name="author" content="name, email@gmail.com" />
    <!-- 添加到主屏后的标题（iOS 6 新增） -->
    <meta name="apple-mobile-web-app-title" content="标题">
    <!-- 添加 favicon icon -->
    <!-- <link rel="shortcut icon" type="image/ico" href="/favicon.ico"/> -->
    <!-- 标题 -->
    <meta name="flexible" content="initial-dpr=1" />
    <title>评测</title>
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.min.js"></script>
    <script src="<%=basePath %>dongneng/js/flexible_css.debug.js"></script>
    <link rel="stylesheet" href="<%=basePath %>dongneng/css/share.css" media="screen" title="no title">
    <script src="<%=basePath %>dongneng/js/flexible.debug.js"></script>
    <script type="text/javascript" src="<%=basePath %>dongneng/js/fastclick.js"></script>
    <!-- 提示 -->
    <link rel="stylesheet" href="<%=basePath %>dongneng/sweet/css/sweet-alert.css" >
    <script src="<%=basePath %>dongneng/sweet/js/sweet-alert.min.js"></script>
</head>

<body>
<!--     <section class="mui-header mui-flex"> -->
<!--         <a href="#" class="fixed cell"><i class="icon"></i></a> -->
<!--         <span class="cell title">测试结果</span> -->
<!--         <a href="#" class="fixed cell"></a> -->
<!--     </section> -->
    <section class="mui-title-1">
        <h2 class="title"><%=msg %></h2>
    </section>
    <section class="">
        <div id="main" style="width: 100%;height:400px;"></div>
    </section>
    <section class="mui-title-1">
        <h2 class="title">根据您的测试结果，我们给您推荐如下课程</h2>
    </section>
    <section class="list-layout-2">
        <ul class="list-box">
        </ul>
    </section>
    <script type="text/javascript" src="<%=basePath %>dongneng/js/echarts.min.js"></script>
    
    <script type="text/javascript">
	    $(function(){
	    	var result = '<%=result%>' ;
	    	var url = '<%=basePath%>user/getCourse.do' ;
	       	var param = {result:result} ;
	       	$.post(url,param,function(json){
	       		console.info(json) ;
	       		if(json.status == 10001){
	       			var html = '' ;
	       			if(json.data.info != null){
	       				$.each(json.data.info,function(i,domEle){
		       				html += '<li class="list mui-flex" >'+
						       	                '<a href="#" class="cell img-box" onclick="webview('+domEle.id+',\''+domEle.typeName+'\')">'+
						                    '<div class="img" style="background-image: url('+domEle.logoPath+');"></div>'+
						                '</a>'+
						                '<div class="cell content-box">'+
						                    '<a href="#" class="title mui-flex">'+
						                        '<span class="text">'+domEle.itemName+'</span>'+
						                        '<span class="price"￥'+domEle.amount+'</span>'+
						                    '</a>'+
						                    '<a href="#" class="text-box-1">'+
						                        '<span class="text">'+domEle.teacherName+'</span>'+
						                        '<span class="label" style="margin-left:20px;">'+domEle.typeName+'</span>'+
						                    '</a>'+
						                    '<div class="text-box-2">'+
						                        '<a href="#" class="left">'+
						                           '<span class="text text-overflow">报到地点：'+domEle.province+domEle.city+domEle.district+domEle.address+'</span>'+
						                            '<span class="text text-overflow">报到时间：'+domEle.registerDate+'</span>'+
						                        '</a>'+
						                        '<a href="tel:'+domEle.phone+'" class="icon-phone"></a>'+
						                    '</div>'+
						                '</div>'+
						            '</li>' ;
		       			}) ;
		       			$('.list-box').empty() ;
		       			$('.list-box').append(html) ;
	       			}
	       		}else{
	       			swal('亲，出现问题了!') ;
	       		}
	       	},'json') ;
	    }) ;
	    
	    var webview = function(courseId,typeName){
	    	window.location.href = 'dn://webview?action=course||courseId='+courseId+'&typeName='+typeName ;
	   		var param = {courseId:courseId,typeName:typeName} ;
	    	window.webkit.messageHandlers.courseInfo.postMessage(param);
	    } ;
    </script>
    
    <script type="text/javascript">
        if ('addEventListener' in document) {
            document.addEventListener('DOMContentLoaded', function() {
                FastClick.attach(document.body);
            }, false);
        }

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        option = {
            title: {
                // text: '基础雷达图'
            },
            tooltip: {},
            backgroundColor: '#fff',
            radar: {
                // shape: 'circle',
                name: {
                       textStyle: {
                           color:'#666'
                       }
                },
                center: ['50%', '50%'],
                radius: 75,
                indicator: [{
                    name: '孩子学习动力指数',
                    max: 20,
                }, {
                    name: '亲子关系',
                    max: 20
                }, {
                    name: '婚姻关系',
                    max: 20
                }, {
                    name: '家庭环境',
                    max: 20
                }, {
                    name: '家校关系',
                    max: 20
                }, {
                    name: '家庭价值观',
                    max: 20
                }, {
                    name: '家风家训',
                    max: 20
                }, {
                    name: '个人修炼与成长',
                    max: 20
                }]
            },
            series: [{
                // name: '预算 vs 开销（Budget vs spending）',
                type: 'radar',
                // areaStyle: {normal: {}},
                data: [{
                    value: [<%=result%>],
                    name: '综合结果'
                }]
            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
</body>


</html>