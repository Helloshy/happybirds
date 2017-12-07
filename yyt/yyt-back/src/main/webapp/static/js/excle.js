(function($) {
    var defaults = {
        submitUrl : '',
        templateName : '',
        params : {},
        titles : {},
        notNullTitles : {},
        success : function(result) {
        },
        error : function() {
        }
    };
    var importExcel = {
        opts : {},
        dialog : null,
        open : function(o) {
            opts = o;
            var $uploadForm = null;
            var $file = null;
            var $info = null;
            dialog = ygDialog({
                isFrame : true,
                cache : false,
                title : '导入',
                modal : true,
                showMask : false,
                width : '380',
                height : '17',
                href : opts.importPageUrl,
                buttons : [
                    {
                        text : '上传',
                        iconCls : 'icon-upload',
                        handler : function(self) {
                            $.messager.progress({
                                msg : '处理中',
                                interval : 1000
                            });

                            $uploadForm
                                .form({
                                    onSubmit : function(param) {
                                        var uploadFile = $file.val();
                                        var fileListType = "xls|xlsx";
                                        if (!uploadFile) {
                                            $info
                                                .html("请选择{0}文件"
                                                    .format(fileListType));
                                            $.importExcel.expand(380,
                                                250);
                                            return false;
                                        }
                                        var destStr = uploadFile
                                            .substring(
                                                uploadFile
                                                    .lastIndexOf(".") + 1,
                                                uploadFile.length);
                                        if ('xls' != destStr
                                            && 'xlsx' != destStr) {
                                            $info
                                                .html("只允许上传{0}文件"
                                                    .format(fileListType));
                                            $.importExcel.expand(380,
                                                250);
                                            return false;
                                        }
                                        param["titles[]"] = opts.titles;
                                        param["notNullTitles[]"] = opts.notNullTitles;
                                    },
                                    success : function(result) {
                                        if (!result) {
                                            $uploadForm.form('clear');
                                            result = '上传成功!';
                                        }
                                        $info.html(result);
                                        $.importExcel.expand(380, 250);
                                    }
                                });
                            $uploadForm.submit();
                        }
                    },
                    {
                        text : '下载模板',
                        iconCls : 'icon-download',
                        handler : function() {
                            if(o.templateName!=''){
                                window.location.href = BasePath
                                    + '/resources/template/'
                                    + o.templateName;
                            }
                        }
                    } ],
                onLoad : function(win, dialog) {
                    $info = dialog.$('#info');
                    $file = dialog.$('#uploadFile');
                    $uploadForm = dialog.$('#uploadForm');
                    $uploadForm.attr('action', o.submitUrl);

                }
            });
        },

        shrink : function(_width, _height) {
            $.messager.progress('close');
            $(dialog).dialog('resize', {
                width : _width,
                height : _height
            });
        },

        expand : function(_width, _height) {
            $.messager.progress('close');
            $(dialog).dialog('resize', {
                width : _width,
                height : _height
            });
        },

        close : function() {
            $.messager.progress('close');
            dialog.panel('close');
        },
        success : function(result) {
            $.messager.progress('close');
            opts.success.call(this, result);
        },
        error : function(e) {
            $.messager.progress('close');
            opts.error.call(this, e);
        }
    };

    $.importExcel = function(options) {
        $.fn.importExcel.open(options);
    };

    $.importExcel.open = function(options) {
        var opts = $.extend({}, defaults, options);
        importExcel.open(opts);
    };

    $.importExcel.shrink = function(_width, _height) {
        return importExcel.shrink(_width, _height);
    };

    $.importExcel.expand = function(_width, _height) {
        return importExcel.expand(_width, _height);
    };

    $.importExcel.success = function(result) {
        return importExcel.success(result);
    };

    $.importExcel.error = function(e) {
        return importExcel.error(e);
    };

    $.importExcel.colse = function() {
        return importExcel.close();
    };
})(jQuery);