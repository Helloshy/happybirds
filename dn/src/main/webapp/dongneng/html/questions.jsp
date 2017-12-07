<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String uid = request.getParameter("uid") ;
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <meta name="flexible" content="maximum-dpr=1" />
    <!-- 添加 favicon icon -->
    <!-- <link rel="shortcut icon" type="image/ico" href="/favicon.ico"/> -->
    <!-- 标题 -->
    <title>评测</title>
    <script type="text/javascript" src="<%=basePath%>/js/jquery/jquery-1.11.1.min.js"></script>
    <script src="<%=basePath %>/dongneng/js/flexible.debug.js"></script>
    <link rel="stylesheet" href="<%=basePath %>/dongneng/css/share.css" media="screen" title="no title">
    <script src="<%=basePath %>/dongneng/js/sweetalert.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>/dongneng/js/fastclick.js"></script>
</head>

<body>
<!--     <section class="mui-header mui-flex"> -->
<!--         <a href="#" class="fixed cell"><i class="icon"></i></a> -->
<!--         <span class="cell title">评测</span> -->
<!--         <a href="#" class="fixed cell"></a> -->
<!--     </section> -->
    <section class="list-layout-1">
        <div class="title">
            <p class="text">1.如果你不督促，孩子极少主动学习。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．从不</li>
            <li class="list text-overflow">B．有时</li>
            <li class="list text-overflow">C．总是</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">2.孩子觉得读书没意思，想去找个工作做。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．从不</li>
            <li class="list text-overflow">B．有时</li>
            <li class="list text-overflow">C．总是</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">3.孩子给自己定下的学习目标，多数因做不到而不得不放弃。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．从不</li>
            <li class="list text-overflow">B．有时</li>
            <li class="list text-overflow">C．总是</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">4.孩子花在课外读物上的时间比花在教科书上的时间要多的多。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．从不</li>
            <li class="list text-overflow">B．有时</li>
            <li class="list text-overflow">C．总是</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">5.和孩子对话时，我很少使用“你应该…”、“你最好…否则…”．“你再不…我就…”的语气和孩子交谈。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．非常不符合</li>
            <li class="list text-overflow">B．一般</li>
            <li class="list text-overflow">C．非常符合</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">6.我觉得孩子犯错和惹麻烦是成长必经的过程。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．非常不符合</li>
            <li class="list text-overflow">B．一般</li>
            <li class="list text-overflow">C．非常符合</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">7.即使孩子犯了错，我也不会因此就认为他（她）是个坏孩子。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．非常不符合</li>
            <li class="list text-overflow">B．一般</li>
            <li class="list text-overflow">C．非常符合</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">8.我与孩子谈话时，我能了解孩子内心真正的感受。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．非常不符合</li>
            <li class="list text-overflow">B．一般</li>
            <li class="list text-overflow">C．非常符合</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    
    <section class="list-layout-1">
        <div class="title">
            <p class="text">9.夫妻之间相互关心、注意、询问对方的身体状况。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．总是</li>
            <li class="list text-overflow">B. 偶尔</li>
            <li class="list text-overflow">C．从不</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">10.夫妻之间个人的事情互相沟通交流。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．总是</li>
            <li class="list text-overflow">B. 偶尔</li>
            <li class="list text-overflow">C．从不</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">11.因为配偶的不良习惯或嗜好而影响夫妻关系。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．总是</li>
            <li class="list text-overflow">B. 偶尔</li>
            <li class="list text-overflow">C．从不</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">12.在教育子女的问题上你与配偶存在分歧。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．总是</li>
            <li class="list text-overflow">B. 偶尔</li>
            <li class="list text-overflow">C．从不</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">13.孩子在家中有固定的学习空间。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．完全不符合</li>
            <li class="list text-overflow">B．一般</li>
            <li class="list text-overflow">C．完全符合</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">14.孩子在家中的习场所的照明状况、桌凳配置完善。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．完全不符合</li>
            <li class="list text-overflow">B．一般</li>
            <li class="list text-overflow">C．完全符合</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">15.在家庭的决策中，您是否会让孩子参与决策？</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．总是</li>
            <li class="list text-overflow">B. 偶尔</li>
            <li class="list text-overflow">C．从不</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">16.我的家庭中经常充满民主、和谐的气氛。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．完全不符合</li>
            <li class="list text-overflow">B．一般</li>
            <li class="list text-overflow">C．完全符合</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">17.在与老师交流的过程中孩子总是很拘谨和紧张。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．符合</li>
            <li class="list text-overflow">B．不确定</li>
            <li class="list text-overflow">C．不符合</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">18.孩子喜欢和同学在一起，从中能体会到交流的快乐。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．符合</li>
            <li class="list text-overflow">B．不确定</li>
            <li class="list text-overflow">C．不符合</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">19.孩子很讨厌某位老师。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A.符合</li>
            <li class="list text-overflow">B.不确定</li>
            <li class="list text-overflow">C.不符合</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">20.老师很少与我交流看法。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A.符合</li>
            <li class="list text-overflow">B.不确定</li>
            <li class="list text-overflow">C.不符合</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">21.我经常给孩子提供必要的教育性参考资料、书报杂志、音像制品。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．完全不符合</li>
            <li class="list text-overflow">B．一般</li>
            <li class="list text-overflow">C．完全符合</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">22.您会在日常生活中策划一些有助于家庭幸福的活动。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A.总是</li>
            <li class="list text-overflow">B.偶尔</li>
            <li class="list text-overflow">C.从不</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">23.孩子与同学单独出去游玩，您是否会隔段时间打电话询问？</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A.总是</li>
            <li class="list text-overflow">B.偶尔</li>
            <li class="list text-overflow">C.从不</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">24.你是否会偷看孩子的日记等隐私作品。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．总是</li>
            <li class="list text-overflow">B. 偶尔</li>
            <li class="list text-overflow">C．从不</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">25.对于老人跌倒扶不扶的问题，您是如何教导孩子的？</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．赶紧上前扶起老人。</li>
            <li class="list text-overflow">B. 在证明自己的清白下，扶起老人。</li>
            <li class="list text-overflow">C．小心被讹，不要多管闲事。</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">26.您认同“你孝敬老人，你的子女也会走这条路，将来就会孝敬你”这句话吗？</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．非常认同</li>
            <li class="list text-overflow">B. 不认同，也不反对</li>
            <li class="list text-overflow">C．不认同</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">27.亲子间有冲突时，我不认为一定是孩子的错。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．非常不符合</li>
            <li class="list text-overflow">B．一般</li>
            <li class="list text-overflow">C．非常符合</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">28.您在公共场合演讲是否会觉得紧张？</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．总是</li>
            <li class="list text-overflow">B. 偶尔</li>
            <li class="list text-overflow">C．从不</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">29.对家庭外问题的认识和看法能保持默契。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．总是</li>
            <li class="list text-overflow">B. 偶尔</li>
            <li class="list text-overflow">C．从不</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">30.在我的家庭中，大家经常通过多样的学习方式去学习各种知识。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．完全不符合</li>
            <li class="list text-overflow">B．一般</li>
            <li class="list text-overflow">C．完全符合</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">31.不管我的工作或生活再忙碌，每一天我都会留一些时间给子女。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．非常不符合</li>
            <li class="list text-overflow">B．一般</li>
            <li class="list text-overflow">C．非常符合</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <section class="list-layout-1">
        <div class="title">
            <p class="text">32.我能经常和孩子有亲密的接触（如摸头、拍肩、拍手、相互拥抱……）。</p>
        </div>
        <ul class="content">
            <li class="list text-overflow">A．非常不符合</li>
            <li class="list text-overflow">B．一般</li>
            <li class="list text-overflow">C．非常符合</li>
        </ul>
        <input type="hidden" name="" class="input" />
    </section>
    <div class="submit-1">
        <button type="button" class="button" name="button">提交</button>
    </div>
    <div class="overlay"></div>
    <div class="dialog">
        <p class="text">第2,3,4未选择</p>
        <a href="#" class="btn">确定</a>
    </div>
    <script type="text/javascript" src="../js/zepto.js"></script>
    <script type="text/javascript">
        if ('addEventListener' in document) {
            document.addEventListener('DOMContentLoaded', function() {
                FastClick.attach(document.body);
            }, false);
        }
        var $content = $('.list-layout-1').find('.content');

        $content.on('click', '.list', function() {
            var $this = $(this);
            var $value = $this.text();

            $this.siblings('.list').removeClass('active');

            if ($this.hasClass('active')) {
                $this.removeClass('active');
                $this.parent('.content').siblings('.input').val('');
                $this.parents('.list-layout-1').removeAttr('data-checked');
            } else {
                $this.addClass('active');
                $this.parents('.list-layout-1').data('checked', 'true');
                $this.parent('.content').siblings('.input').val($value);
            }

        });

        $('.submit-1').find('.button').on('click', function() {
            var noSelect = [],
                showText = '';
            $('.list-layout-1').each(function(index, elem) {

                if (!$(elem).data('checked')) {
                    noSelect.push(index + 1);
                }
            });

            if (noSelect.length <= 0) {
//                 showText = "谢谢您的填写！";
//                 swal({
//                     title: "成功",
//                     text: showText,
//                     type: "success",
//                     confirmButtonText: "确定",
//                     confirmButtonColor: "rgb(174, 222, 244)",
//                     closeOnConfirm: false
//                 });
				var answers = '' ;
	       		var lth = $('.input').length ;
	           	$.each($('.input'),function(i,domEle){
	           		var val = $(domEle).val() ;
           			val = val.substring(0,1) ;
           			answers += val +',' ;
	           	}) ;
            	var url = '<%=basePath%>user/getResult.do' ;
	           	var uid = '<%=uid %>' ;
	           	var param = {answers:answers} ;
	           	$.post(url,param,function(json){
	           		if(json.status == 10001){
	           		  window.location.href = '<%=basePath%>dongneng/html/result.jsp?result='+json.data.info.result+'&msg='+json.data.info.msg+'&uid='+uid ;
	           		}else{
	           		  showText = '亲，出现问题了!';
	                  swal({
	                	  title: "错误",
	                      text: showText,
	                      type: "error",
	                      confirmButtonText: "确定",
	                      confirmButtonColor: "#F27474",
	                      closeOnConfirm: false
	                  });
	           		}
	           	},'json') ;
            } else {
                showText = "第 " + noSelect.slice(0, 1).join() + " 题未选择";
                var noSelectFirst = noSelect[0];
                swal({
                    title: "错误",
                    text: showText,
                    type: "error",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#F27474",
                    closeOnConfirm: false
                });
            }

            $('.confirm').click(function() {
                if (noSelect.length >= 0) {
                    var noSelectDom = $content[noSelectFirst - 1];
                    var noSelectFirstHeight = parseInt($(noSelectDom).offset().top);
                    $('html, body').scrollTop(noSelectFirstHeight - 50);
                }
            });
        });
    </script>
</body>

</html>