(function( factory ) {
	if ( typeof define === "function" && define.amd ) {
		define( ["jquery", "../jquery.validate"], factory );
	} else {
		factory( jQuery );
	}
}(function( $ ) {


$.extend($.validator.messages, {
	required: "<span style='color:red'>这是必填字段</span>",
	remote: "<span style='color:red'>请修正此字段</span>",
	email: "<span style='color:red'>请输入有效的电子邮件地址</span>",
	url: "<span style='color:red'>请输入有效的网址</span>",
	date: "<span style='color:red'>请输入有效的日期</span>",
	dateISO: "<span style='color:red'>请输入有效的日期 (YYYY-MM-DD)</span>",
	number: "<span style='color:red'>请输入有效的数字</span>",
	digits: "<span style='color:red'>只能输入数字</span>",
	creditcard: "<span style='color:red'>请输入有效的信用卡号码</span>",
	equalTo: "<span style='color:red'>你的输入不相同</span>",
	extension: "<span style='color:red'>请输入有效的后缀</span>",
	maxlength: $.validator.format("<span style='color:red'>最多可以输入 {0} 个字符</span>"),
	minlength: $.validator.format("<span style='color:red'>最少要输入 {0} 个字符</span>"),
	rangelength: $.validator.format("<span style='color:red'>请输入长度在 {0} 到 {1} 之间的字符串</span>"),
	range: $.validator.format("<span style='color:red'>请输入范围在 {0} 到 {1} 之间的数值</span>"),
	max: $.validator.format("<span style='color:red'>请输入不大于 {0} 的数值</span>"),
	min: $.validator.format("<span style='color:red'>请输入不小于 {0} 的数值</span>")
});

}));