/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2016-12-14 13:29:49 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.system.systemUser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.kapphk.web.utils.PropertiesUtil;

public final class list_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/common/include.jsp", Long.valueOf(1481718369855L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("\t<head>\r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	//获取文件服务器地址
	request.setAttribute("fileServer", PropertiesUtil.getFileServer());

      out.write('\r');
      out.write('\n');
      if (_jspx_meth_c_005fset_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<meta http-equiv=\"pragma\" content=\"no-cache\"> \r\n");
      out.write("<meta http-equiv=\"cache-control\" content=\"no-cache\"> \r\n");
      out.write("<meta http-equiv=\"expires\" content=\"0\">   \r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/bootstrap.css\" />\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/jquery-ui.min.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/ui.jqgrid.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/ace-fonts.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/ace.css\" class=\"ace-main-stylesheet\" id=\"main-ace-style\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/ace-skins.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/ace-rtl.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/font-awesome/css/font-awesome.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/plugins/bootstrap-datepicker/css/bootstrap-datetimepicker.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/plugins/kindeditor-4.1.10/themes/default/default.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/plugins/JBox/jBox/Skins2/Metro/jbox.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/plugins/bootstrap-fileinput/css/fileinput.min.css\" />\r\n");
      out.write("\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/js/jquery-1.9.1.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/js/jquery-migrate-1.2.1.js\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/js/jquery-form.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/js/jquery.validate.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/js/jquery.validate.messages_zh.js\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/js/common.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/js/bootstrap.js\"></script>\r\n");
      out.write("<!--<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/plugins/bootstrap-datepicker/js/moment.js\"></script>-->\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/plugins/bootstrap-datepicker/js/bootstrap-datetimepicker.js\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/plugins/bootstrap-datepicker/js/locales/bootstrap-datetimepicker.zh-CN.js\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/js/jquery.jqGrid.js\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/js/grid.locale-cn.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/plugins/JBox/jBox/jquery.jBox-2.3.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/plugins/layer/layer.js\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/plugins/kindeditor-4.1.10/kindeditor-all.js\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/plugins/bootstrap-fileinput/js/fileinput.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/plugins/bootstrap-fileinput/js/locales/zh.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tvar webRoot = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" ;\r\n");
      out.write("\tvar fileServer = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileServer}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("\tfunction updatePagerIcons(table) {\r\n");
      out.write("\t\tvar replacement = \r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\t'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',\r\n");
      out.write("\t\t\t'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',\r\n");
      out.write("\t\t\t'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',\r\n");
      out.write("\t\t\t'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'\r\n");
      out.write("\t\t};\r\n");
      out.write("\t\t$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){\r\n");
      out.write("\t\t\tvar icon = $(this);\r\n");
      out.write("\t\t\tvar $class = $.trim(icon.attr('class').replace('ui-icon', ''));\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tif($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);\r\n");
      out.write("\t\t})\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction cancel(){\r\n");
      out.write("\t\tlayer.close(laybox);\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("    function toDecimal(x) {    \r\n");
      out.write("        var f = parseFloat(x);    \r\n");
      out.write("        if (isNaN(f)) {    \r\n");
      out.write("            return 0;    \r\n");
      out.write("        }    \r\n");
      out.write("        var f = Math.round(x*100)/100;    \r\n");
      out.write("        var s = f.toString();   \r\n");
      out.write("        \r\n");
      out.write("         var rs = s.indexOf('.');    \r\n");
      out.write("        if (rs < 0) {    \r\n");
      out.write("            rs = s.length;    \r\n");
      out.write("            s += '.';    \r\n");
      out.write("        }    \r\n");
      out.write("        while (s.length <= rs + 2) {    \r\n");
      out.write("            s += '0';    \r\n");
      out.write("        }   \r\n");
      out.write("        return s;\r\n");
      out.write("        //return (Math.round(parseFloat(s)*100)/100);    */ \r\n");
      out.write("    }   \r\n");
      out.write("    \r\n");
      out.write("    \r\n");
      out.write("    Date.prototype.format = function(format) {\r\n");
      out.write("        var date = {\r\n");
      out.write("               \"M+\": this.getMonth() + 1,\r\n");
      out.write("               \"d+\": this.getDate(),\r\n");
      out.write("               \"h+\": this.getHours(),\r\n");
      out.write("               \"m+\": this.getMinutes(),\r\n");
      out.write("               \"s+\": this.getSeconds(),\r\n");
      out.write("               \"q+\": Math.floor((this.getMonth() + 3) / 3),\r\n");
      out.write("               \"S+\": this.getMilliseconds()\r\n");
      out.write("        };\r\n");
      out.write("        if (/(y+)/i.test(format)) {\r\n");
      out.write("               format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));\r\n");
      out.write("        }\r\n");
      out.write("        for (var k in date) {\r\n");
      out.write("               if (new RegExp(\"(\" + k + \")\").test(format)) {\r\n");
      out.write("                      format = format.replace(RegExp.$1, RegExp.$1.length == 1\r\n");
      out.write("                             ? date[k] : (\"00\" + date[k]).substr((\"\" + date[k]).length));\r\n");
      out.write("               }\r\n");
      out.write("        }\r\n");
      out.write("        return format;\r\n");
      out.write(" }\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<style>\r\n");
      out.write(".doc-buttons {\r\n");
      out.write("  padding-left: 12px!important;\r\n");
      out.write("}\r\n");
      out.write(" .layout-1 {\r\n");
      out.write("\tmargin: 5px auto;!important;\r\n");
      out.write("}\r\n");
      out.write(".ui-jqgrid tr.jqgrow td { \r\n");
      out.write("\ttext-overflow : ellipsis;\r\n");
      out.write("\twhite-space: normal;\r\n");
      out.write(" }\r\n");
      out.write(".ui-jqgrid-view > .ui-jqgrid-titlebar {\r\n");
      out.write("\r\n");
      out.write("  background: #6FB3E0;\r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write(".ke-menu-default{\r\n");
      out.write("\tz-index: 99999999!important;\r\n");
      out.write("}\r\n");
      out.write(".ke-colorpicker-default{\r\n");
      out.write(" z-index: 99999999!important;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".dialog-1 .modal-header {\r\n");
      out.write("  background-color: #307ECC;\r\n");
      out.write("  color: #fff; }\r\n");
      out.write("  .dialog-1 .modal-header .close {\r\n");
      out.write("    color: #fff; }\r\n");
      out.write(".dialog-1 .layout-1 {\r\n");
      out.write("  margin: 10px auto;\r\n");
      out.write("  font-size: 14px;\r\n");
      out.write("  margin-left: 20px; }\r\n");
      out.write("  .dialog-1 .layout-1 .layout-left {\r\n");
      out.write("    height: 36px;\r\n");
      out.write("    width: 15%;\r\n");
      out.write("    display: inline-block; }\r\n");
      out.write("  .dialog-1 .layout-1 .layout-right {\r\n");
      out.write("    height: 36px;\r\n");
      out.write("    width: 80%;\r\n");
      out.write("    display: inline-block; }\r\n");
      out.write(".dialog-1 .btn-box {\r\n");
      out.write("  text-align: center; }\r\n");
      out.write("\r\n");
      out.write("/*# sourceMappingURL=admin-style.css.map */\r\n");
      out.write("\r\n");
      out.write("/*定义滚动条高宽及背景 高宽分别对应横竖滚动条的尺寸*/  \r\n");
      out.write("::-webkit-scrollbar  \r\n");
      out.write("{  \r\n");
      out.write("    width: 1px;  \r\n");
      out.write("    height: 1px;  \r\n");
      out.write("    background-color: #F5F5F5;  \r\n");
      out.write("}  \r\n");
      out.write("  \r\n");
      out.write("/*定义滚动条轨道 内阴影+圆角*/  \r\n");
      out.write("::-webkit-scrollbar-track  \r\n");
      out.write("{  \r\n");
      out.write("    -webkit-box-shadow: inset 0 0 1px rgba(0,0,0,0.3);  \r\n");
      out.write("    border-radius: 10px;  \r\n");
      out.write("    background-color: #F5F5F5;  \r\n");
      out.write("}  \r\n");
      out.write("  \r\n");
      out.write("/*定义滑块 内阴影+圆角*/  \r\n");
      out.write("::-webkit-scrollbar-thumb  \r\n");
      out.write("{  \r\n");
      out.write("    border-radius: 10px;  \r\n");
      out.write("    -webkit-box-shadow: inset 0 0 1px rgba(0,0,0,.3);  \r\n");
      out.write("    background-color: #555;  \r\n");
      out.write("}  \r\n");
      out.write("\r\n");
      out.write("</style>");
      out.write("\r\n");
      out.write("\t\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" />\r\n");
      out.write("\t\t<meta charset=\"utf-8\" />\r\n");
      out.write("\t\t<title>用户管理</title>\r\n");
      out.write("\r\n");
      out.write("\t\t<meta name=\"description\" content=\"Dynamic tables and grids using jqGrid plugin\" />\r\n");
      out.write("\t\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0\" />\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\r\n");
      out.write("\t</head>\r\n");
      out.write("\r\n");
      out.write("\t<body class=\"no-skin\">\r\n");
      out.write("\t\t<!-- /section:basics/navbar.layout -->\r\n");
      out.write("\t\t<div class=\"main-container ace-save-state\" id=\"main-container\">\r\n");
      out.write("\t\t\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\t\ttry{ace.settings.loadState('main-container')}catch(e){}\r\n");
      out.write("\t\t\t</script>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<div class=\"main-content\">\r\n");
      out.write("\t\t\t\t<div class=\"main-content-inner\">\r\n");
      out.write("\t\t\t\t\t<div class=\"page-content\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"doc-buttons\" style=\"padding: 10px 0;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-xs-12\">\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t<table id=\"grid-table\"></table>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t<div id=\"grid-pager\"></div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t<!-- PAGE CONTENT ENDS -->\r\n");
      out.write("\t\t\t\t\t\t\t</div><!-- /.col -->\r\n");
      out.write("\t\t\t\t\t\t</div><!-- /.row -->\r\n");
      out.write("\t\t\t\t\t</div><!-- /.page-content -->\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div><!-- /.main-content -->\r\n");
      out.write("\r\n");
      out.write("\t\t\t<a href=\"#\" id=\"btn-scroll-up\" class=\"btn-scroll-up btn btn-sm btn-inverse\">\r\n");
      out.write("\t\t\t\t<i class=\"ace-icon fa fa-angle-double-up icon-only bigger-110\"></i>\r\n");
      out.write("\t\t\t</a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/js/system/systemUser/list.js\"></script>\r\n");
      out.write("\t</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fset_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f0.setParent(null);
    // /common/include.jsp(10,0) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setValue(new org.apache.jasper.el.JspValueExpression("/common/include.jsp(10,0) '${pageContext.request.contextPath}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${pageContext.request.contextPath}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /common/include.jsp(10,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setVar("ctx");
    int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
    if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /WEB-INF/views/system/systemUser/list.jsp(31,8) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/views/system/systemUser/list.jsp(31,8) '${res}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${res}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /WEB-INF/views/system/systemUser/list.jsp(31,8) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("key");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t\t ");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${key.btn }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }
}
