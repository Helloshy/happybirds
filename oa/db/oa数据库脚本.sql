# 用户表
insert into `oa_id_user` (`USER_ID`, `ANSWER`, `CHECK_DATE`, `CREATE_DATE`, `EMAIL`, `MODIFY_DATE`, `NAME`, `PASS_WORD`, `PHONE`, `QQ_NUM`, `QUESTION`, `SEX`, `STATUS`, `TEL`, `CHECKER`, `CREATER`, `DEPT_ID`, `JOB_CODE`, `MODIFIER`) values('admin','999','2016-03-19 12:02:54','2016-03-19 09:53:58','lixiaohua7@163.com','2016-03-20 12:14:32','柳岩','e10adc3949ba59abbe56e057f20f883e','18998336981','441510427','0','2','1','020-88798789','admin','admin',NULL,NULL,'admin');


# 部门表
insert into `oa_id_dept` (`ID`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `CREATER`, `MODIFIER`) values('1','2016-03-19 09:54:01','2016-03-19 09:54:01','技术部','技术部','admin','admin');
insert into `oa_id_dept` (`ID`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `CREATER`, `MODIFIER`) values('2','2016-03-19 09:54:01','2016-03-19 09:54:01','运营部','运营部','admin','admin');
insert into `oa_id_dept` (`ID`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `CREATER`, `MODIFIER`) values('3','2016-03-19 09:54:01','2016-03-19 09:54:01','财务部','财务部','admin','admin');
insert into `oa_id_dept` (`ID`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `CREATER`, `MODIFIER`) values('4','2016-03-19 09:54:01','2016-03-19 09:54:01','人事部','人事部','admin','admin');
insert into `oa_id_dept` (`ID`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `CREATER`, `MODIFIER`) values('5','2016-03-19 09:54:01','2016-03-19 09:54:01','总公办','总公办','admin','admin');

# 职位表
insert into `oa_id_job` (`CODE`, `NAME`, `REMARK`) values('0001','职员',NULL);
insert into `oa_id_job` (`CODE`, `NAME`, `REMARK`) values('0002','Java开发工程师',NULL);
insert into `oa_id_job` (`CODE`, `NAME`, `REMARK`) values('0003','Java中级开发工程师',NULL);
insert into `oa_id_job` (`CODE`, `NAME`, `REMARK`) values('0004','Java高级开发工程师',NULL);
insert into `oa_id_job` (`CODE`, `NAME`, `REMARK`) values('0005','系统管理员',NULL);
insert into `oa_id_job` (`CODE`, `NAME`, `REMARK`) values('0006','架构师',NULL);
insert into `oa_id_job` (`CODE`, `NAME`, `REMARK`) values('0007','主管',NULL);
insert into `oa_id_job` (`CODE`, `NAME`, `REMARK`) values('0008','经理',NULL);
insert into `oa_id_job` (`CODE`, `NAME`, `REMARK`) values('0009','总经理',NULL);

# 用户表
insert into `oa_id_user` (`USER_ID`, `ANSWER`, `CHECK_DATE`, `CREATE_DATE`, `EMAIL`, `MODIFY_DATE`, `NAME`, `PASS_WORD`, `PHONE`, `QQ_NUM`, `QUESTION`, `SEX`, `STATUS`, `TEL`, `CHECKER`, `CREATER`, `DEPT_ID`, `JOB_CODE`, `MODIFIER`) values('leesiuwah','湖南','2016-03-19 12:03:02','2016-03-19 09:53:58','lixiaohua7@163.com','2016-03-20 12:15:30','李小华','e10adc3949ba59abbe56e057f20f883e','18998336981','44287934','2','1','1','020-8098763','admin','admin','1','0001','admin');
insert into `oa_id_user` (`USER_ID`, `ANSWER`, `CHECK_DATE`, `CREATE_DATE`, `EMAIL`, `MODIFY_DATE`, `NAME`, `PASS_WORD`, `PHONE`, `QQ_NUM`, `QUESTION`, `SEX`, `STATUS`, `TEL`, `CHECKER`, `CREATER`, `DEPT_ID`, `JOB_CODE`, `MODIFIER`) values('limin','888','2016-03-19 09:54:02','2016-03-19 09:54:02','441510427@qq.com',NULL,'李民','e10adc3949ba59abbe56e057f20f883e','13888888888','88888','0','1','1','02088888888','admin','admin','1','0007',NULL);
insert into `oa_id_user` (`USER_ID`, `ANSWER`, `CHECK_DATE`, `CREATE_DATE`, `EMAIL`, `MODIFY_DATE`, `NAME`, `PASS_WORD`, `PHONE`, `QQ_NUM`, `QUESTION`, `SEX`, `STATUS`, `TEL`, `CHECKER`, `CREATER`, `DEPT_ID`, `JOB_CODE`, `MODIFIER`) values('liqin','888','2016-03-19 09:54:02','2016-03-19 09:54:02','441510427@qq.com',NULL,'李琴','e10adc3949ba59abbe56e057f20f883e','13888888888','88888','0','1','1','02088888888','admin','admin','4','0007',NULL);
insert into `oa_id_user` (`USER_ID`, `ANSWER`, `CHECK_DATE`, `CREATE_DATE`, `EMAIL`, `MODIFY_DATE`, `NAME`, `PASS_WORD`, `PHONE`, `QQ_NUM`, `QUESTION`, `SEX`, `STATUS`, `TEL`, `CHECKER`, `CREATER`, `DEPT_ID`, `JOB_CODE`, `MODIFIER`) values('lixiaohu','888','2016-03-19 09:54:02','2016-03-19 09:54:02','441510427@qq.com',NULL,'李小虎','e10adc3949ba59abbe56e057f20f883e','13888888888','88888','0','1','1','02088888888','admin','admin','5','0009',NULL);
insert into `oa_id_user` (`USER_ID`, `ANSWER`, `CHECK_DATE`, `CREATE_DATE`, `EMAIL`, `MODIFY_DATE`, `NAME`, `PASS_WORD`, `PHONE`, `QQ_NUM`, `QUESTION`, `SEX`, `STATUS`, `TEL`, `CHECKER`, `CREATER`, `DEPT_ID`, `JOB_CODE`, `MODIFIER`) values('liying','888','2016-03-19 09:54:02','2016-03-19 09:54:02','441510427@qq.com',NULL,'李英','e10adc3949ba59abbe56e057f20f883e','13888888888','88888','0','1','1','02088888888','admin','admin','4','0001',NULL);
insert into `oa_id_user` (`USER_ID`, `ANSWER`, `CHECK_DATE`, `CREATE_DATE`, `EMAIL`, `MODIFY_DATE`, `NAME`, `PASS_WORD`, `PHONE`, `QQ_NUM`, `QUESTION`, `SEX`, `STATUS`, `TEL`, `CHECKER`, `CREATER`, `DEPT_ID`, `JOB_CODE`, `MODIFIER`) values('zhangbo','888','2016-03-19 09:54:02','2016-03-19 09:54:02','441510427@qq.com',NULL,'张波','e10adc3949ba59abbe56e057f20f883e','13888888888','88888','0','1','1','02088888888','admin','admin','1','0008',NULL);
insert into `oa_id_user` (`USER_ID`, `ANSWER`, `CHECK_DATE`, `CREATE_DATE`, `EMAIL`, `MODIFY_DATE`, `NAME`, `PASS_WORD`, `PHONE`, `QQ_NUM`, `QUESTION`, `SEX`, `STATUS`, `TEL`, `CHECKER`, `CREATER`, `DEPT_ID`, `JOB_CODE`, `MODIFIER`) values('zhangsan','888','2016-03-19 09:54:02','2016-03-19 09:54:02','441510427@qq.com',NULL,'张三','e10adc3949ba59abbe56e057f20f883e','13888888888','88888','0','1','1','02088888888','admin','admin','4','0008',NULL);


# 模块表
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('0001','2016-03-19 09:53:58','2016-03-19 09:53:58','系统管理','系统管理','#','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('00010001','2016-03-19 09:53:58','2016-03-19 09:53:58','----用户管理','用户','/admin/identity/selectUser.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000100010001','2016-03-19 09:53:58','2016-03-19 09:53:58','--------查询操作','查询操作','/admin/identity/selectUser.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000100010002','2016-03-19 09:53:58','2016-03-19 13:42:08','--------增加操作','增加操作','/admin/identity/addUser.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000100010003','2016-03-19 09:53:58','2016-03-19 09:53:58','--------修改操作',NULL,'/admin/identity/updateUser.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000100010004','2016-03-19 09:53:58','2016-03-19 09:53:58','--------删除操作',NULL,'/admin/identity/deleteUser.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000100010005','2016-03-19 09:53:58','2016-03-19 09:53:58','--------审核操作','审核操作','/admin/identity/checkUser.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('00010002','2016-03-19 09:53:58','2016-03-19 09:53:58','----角色管理',NULL,'/admin/identity/selectRole.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000100020001','2016-03-19 09:53:58','2016-03-19 09:53:58','--------查询操作',NULL,'/admin/identity/selectRole.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000100020002','2016-03-19 09:53:58','2016-03-19 09:53:58','--------增加操作',NULL,'/admin/identity/addRole.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000100020003','2016-03-19 09:53:58','2016-03-19 09:53:58','--------修改操作',NULL,'/admin/identity/updateRole.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000100020004','2016-03-19 09:53:58','2016-03-19 09:53:58','--------删除操作',NULL,'/admin/identity/deleteRole.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000100020005','2016-03-19 09:53:58','2016-03-19 09:53:58','--------查看角色绑定用户',NULL,'/admin/identity/selectRoleUser.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000100020006','2016-03-19 09:53:58','2016-03-19 09:53:58','--------查看角色绑定模块',NULL,'/admin/identity/mgrPopedom.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000100020007','2016-03-19 09:53:58','2016-03-19 09:53:58','--------角色绑定用户',NULL,'/admin/identity/bindUser.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000100020008','2016-03-19 09:53:58','2016-03-19 09:53:58','--------角色解除用户',NULL,'/admin/identity/unbindUser.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000100020009','2016-03-19 09:53:59','2016-03-19 09:53:59','--------查看角色绑定操作',NULL,'/admin/identity/selectOpera.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000100020010','2016-03-19 09:53:59','2016-03-19 09:53:59','--------角色绑定操作',NULL,'/admin/identity/bindModule.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('00010003','2016-03-19 09:53:59','2016-03-19 09:53:59','----操作管理','操作管理','/admin/identity/mgrModule.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000100030001','2016-03-19 09:53:59','2016-03-19 12:09:37','--------管理操作','管理操作','/admin/identity/mgrModule.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000100030002','2016-03-19 09:53:59','2016-03-19 12:09:50','--------查询操作','查询操作','/admin/identity/selectModule.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000100030003','2016-03-19 09:53:59','2016-03-19 09:53:59','--------增加操作',NULL,'/admin/identity/addModule.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000100030004','2016-03-19 09:53:59','2016-03-19 09:53:59','--------修改操作',NULL,'/admin/identity/updateModule.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000100030005','2016-03-19 09:53:59',NULL,'--------删除操作','管理操作','/admin/identity/deleteModule.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('0002','2012-04-07 13:45:51','2016-03-19 10:09:50','假期管理','假期管理','#','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('00020001','2016-03-19 09:53:59','2016-03-19 09:53:59','----假期类型','假期类型管理','/admin/leave/mgrLeaveType.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000200010001','2016-03-19 09:53:59',NULL,'--------查询操作','查询操作','/admin/leave/selectLeaveType.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000200010002','2016-03-19 09:53:59','2016-03-19 09:53:59','--------增加操作','增加操作','/admin/leave/addLeaveType.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000200010003','2016-03-19 09:53:59',NULL,'--------修改操作','修改操作','/admin/leave/updateLeaveType.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000200010004','2016-03-19 09:53:59',NULL,'--------删除操作','删除操作','/admin/leave/deleteLeaveType.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000200010005','2016-03-19 09:53:59','2016-03-19 09:53:59','--------管理操作','管理操作','/admin/leave/mgrLeaveType.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('00020002','2016-03-19 09:53:59',NULL,'----假期明细','假期明细','/admin/leave/mgrLeaveItem.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000200020001','2016-03-19 09:53:59',NULL,'--------管理操作','管理操作','/admin/leave/mgrLeaveItem.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000200020002','2016-03-19 09:53:59',NULL,'--------查询操作','查询操作','/admin/leave/selectLeaveItem.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000200020006','2016-03-19 09:53:59',NULL,'--------审核操作','审核操作','/admin/leave/checkLeaveItem.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('00020003','2016-03-19 09:53:59','2016-03-19 09:53:59','----用户请假','用户请假','/admin/leave/selectUserLeave.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000200030001','2016-03-19 09:53:59',NULL,'--------查询操作','查询操作','/admin/leave/selectUserLeave.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000200030002','2016-03-19 09:53:59',NULL,'--------增加操作','增加操作','/admin/leave/addUserLeave.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000200030003','2016-03-19 09:53:59',NULL,'--------修改操作','修改操作','/admin/leave/updateUserLeave.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000200030004','2016-03-19 09:54:00',NULL,'--------删除操作','删除操作','/admin/leave/deleteUserLeave.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('00020004','2016-03-19 09:54:00','2016-03-19 09:54:00','----假期审批','假期审批','/admin/leave/selectAuditLeave.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000200040001','2016-03-19 09:54:00',NULL,'--------查看审批操作','查看审批操作','/admin/leave/selectAuditLeave.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000200040002','2016-03-19 09:54:00',NULL,'--------审批请假操作','审批请假操作','/admin/leave/audit.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('0003','2016-03-20 21:12:22',NULL,'流程管理','流程管理','#','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('00030001','2016-03-20 21:13:28',NULL,'----流程部署','流程部署','/admin/workflow/workflowDiagarm.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000300010001','2016-03-20 21:19:38',NULL,'--------跳转操作','跳转操作','/admin/workflow/workflowDiagarm.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000300010002','2016-03-20 21:20:07',NULL,'--------部署操作','部署操作','/admin/workflow/deploy.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('00030002','2016-03-20 21:52:58','2016-03-20 21:53:38','----部署管理','部署管理','/admin/workflow/selectDeployment.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000300020001','2016-03-20 21:54:06',NULL,'--------查询操作','查询操作','/admin/workflow/selectDeployment.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000300020002','2016-03-20 21:54:39',NULL,'--------删除操作','删除操作','/admin/workflow/deleteDeployment.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('0004','2016-06-19 09:27:23','2016-06-19 09:27:37','通迅录管理','通迅录管理','#','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('00040001','2016-06-19 09:29:47','2016-06-19 09:32:27','----联系组','联系组','/admin/addressbook/selectContactGroup.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000400010001','2016-06-19 09:32:14','2016-06-19 09:33:49','--------查询操作','查询操作','/admin/addressbook/selectContactGroup.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000400010002','2016-06-19 09:34:13',NULL,'--------添加操作','添加操作','/admin/addressbook/addContactGroup.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000400010003','2016-06-19 09:34:45',NULL,'--------修改操作','修改操作','/admin/addressbook/updateContactGroup.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000400010004','2016-06-19 09:35:22',NULL,'--------删除操作','删除操作','/admin/addressbook/deleteContactGroup.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('00040002','2016-06-19 09:30:53','2016-06-19 09:32:40','----联系人','联系人','/admin/addressbook/mgrContact.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000400020001','2016-06-19 09:36:02',NULL,'--------管理操作','管理操作','/admin/addressbook/mgrContact.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000400020002','2016-06-19 09:36:31',NULL,'--------查询操作','查询操作','/admin/addressbook/selectContact.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000400020003','2016-06-19 09:36:55',NULL,'--------添加操作','添加操作','/admin/addressbook/addContact.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000400020004','2016-06-19 09:37:15',NULL,'--------修改操作','修改操作','/admin/addressbook/updateContact.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000400020005','2016-06-19 09:37:38',NULL,'--------删除操作','删除操作','/admin/addressbook/deleteContact.jspx','admin',NULL);
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000400020006','2016-06-19 09:38:42','2016-06-19 09:39:46','--------导出操作','导出操作','/admin/addressbook/exportContact.jspx','admin','admin');
insert into `oa_id_module` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `URL`, `CREATER`, `MODIFIER`) values('000400020007','2016-06-19 09:39:24',NULL,'--------导入操作','导入操作','/admin/addressbook/importContact.jspx','admin',NULL);


# 角色表
insert into `oa_id_role` (`ID`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `CREATER`, `MODIFIER`) values('1','2016-03-19 09:53:58','2016-03-19 12:03:44','系统管理员','系统维护','admin','admin');
insert into `oa_id_role` (`ID`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `CREATER`, `MODIFIER`) values('2','2016-03-19 09:53:58','2016-03-19 10:10:02','普通用户','普通用户','admin','admin');
insert into `oa_id_role` (`ID`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `CREATER`, `MODIFIER`) values('3','2016-03-22 11:28:50',NULL,'假期审批','假期审批','admin',NULL);

# 用户角色中间表
insert into `oa_id_user_role` (`ROLE_ID`, `USER_ID`) values('1','admin');
insert into `oa_id_user_role` (`ROLE_ID`, `USER_ID`) values('2','admin');
insert into `oa_id_user_role` (`ROLE_ID`, `USER_ID`) values('2','leesiuwah');
insert into `oa_id_user_role` (`ROLE_ID`, `USER_ID`) values('2','limin');
insert into `oa_id_user_role` (`ROLE_ID`, `USER_ID`) values('3','limin');
insert into `oa_id_user_role` (`ROLE_ID`, `USER_ID`) values('2','liqin');
insert into `oa_id_user_role` (`ROLE_ID`, `USER_ID`) values('3','liqin');
insert into `oa_id_user_role` (`ROLE_ID`, `USER_ID`) values('3','lixiaohu');
insert into `oa_id_user_role` (`ROLE_ID`, `USER_ID`) values('2','liying');
insert into `oa_id_user_role` (`ROLE_ID`, `USER_ID`) values('2','zhangbo');
insert into `oa_id_user_role` (`ROLE_ID`, `USER_ID`) values('3','zhangbo');
insert into `oa_id_user_role` (`ROLE_ID`, `USER_ID`) values('2','zhangsan');
insert into `oa_id_user_role` (`ROLE_ID`, `USER_ID`) values('3','zhangsan');

# 权限表
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('1','2016-03-19 09:54:00','admin','00020002','000200020001','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('2','2016-03-19 09:54:00','admin','00020002','000200020002','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('3','2016-03-19 09:54:00','admin','00020002','000200020006','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('9','2016-03-19 09:54:00','admin','00020001','000200010001','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('10','2016-03-19 09:54:00','admin','00020001','000200010002','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('11','2016-03-19 09:54:00','admin','00020001','000200010003','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('12','2016-03-19 09:54:00','admin','00020001','000200010004','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('13','2016-03-19 09:54:00','admin','00020001','000200010005','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('14','2016-03-19 09:54:00','admin','00020003','000200030001','2');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('15','2016-03-19 09:54:00','admin','00020003','000200030002','2');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('16','2016-03-19 09:54:00','admin','00020003','000200030003','2');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('17','2016-03-19 09:54:00','admin','00020003','000200030004','2');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('18','2016-03-19 09:54:00','admin','00010002','000100020001','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('19','2016-03-19 09:54:00','admin','00010002','000100020002','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('20','2016-03-19 09:54:00','admin','00010002','000100020003','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('21','2016-03-19 09:54:00','admin','00010002','000100020004','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('22','2016-03-19 09:54:00','admin','00010002','000100020005','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('23','2016-03-19 09:54:00','admin','00010002','000100020006','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('24','2016-03-19 09:54:00','admin','00010002','000100020007','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('25','2016-03-19 09:54:01','admin','00010002','000100020008','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('26','2016-03-19 09:54:01','admin','00010002','000100020009','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('27','2016-03-19 09:54:01','admin','00010002','000100020010','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('28','2016-03-19 09:54:01','admin','00010003','000100030001','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('29','2016-03-19 09:54:01','admin','00010003','000100030002','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('30','2016-03-19 09:54:01','admin','00010003','000100030003','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('31','2016-03-19 09:54:01','admin','00010003','000100030004','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('32','2016-03-19 09:54:01','admin','00010003','000100030005','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('33','2016-03-19 09:54:01','admin','00020003','000200030001','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('34','2016-03-19 09:54:01','admin','00020003','000200030002','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('35','2016-03-19 09:54:01','admin','00020003','000200030003','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('36','2016-03-19 09:54:01','admin','00020003','000200030004','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('39','2016-03-19 09:54:01','admin','00020004','000200040001','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('40','2016-03-19 09:54:01','admin','00020004','000200040002','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('41','2016-03-20 12:13:12','admin','00010001','000100010001','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('42','2016-03-20 12:13:12','admin','00010001','000100010002','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('43','2016-03-20 12:13:12','admin','00010001','000100010003','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('44','2016-03-20 12:13:12','admin','00010001','000100010004','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('45','2016-03-20 12:13:12','admin','00010001','000100010005','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('46','2016-03-20 21:22:00','admin','00030001','000300010001','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('47','2016-03-20 21:22:00','admin','00030001','000300010002','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('48','2016-03-20 21:54:55','admin','00030002','000300020001','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('49','2016-03-20 21:54:55','admin','00030002','000300020002','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('50','2016-03-22 11:29:43','admin','00020004','000200040001','3');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('51','2016-03-22 11:29:43','admin','00020004','000200040002','3');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('52','2016-06-19 09:40:09','admin','00040001','000400010001','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('53','2016-06-19 09:40:09','admin','00040001','000400010002','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('54','2016-06-19 09:40:09','admin','00040001','000400010003','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('55','2016-06-19 09:40:09','admin','00040001','000400010004','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('56','2016-06-19 09:40:14','admin','00040002','000400020001','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('57','2016-06-19 09:40:14','admin','00040002','000400020002','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('58','2016-06-19 09:40:14','admin','00040002','000400020003','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('59','2016-06-19 09:40:14','admin','00040002','000400020004','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('60','2016-06-19 09:40:14','admin','00040002','000400020005','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('61','2016-06-19 09:40:14','admin','00040002','000400020006','1');
insert into `oa_id_popedom` (`ID`, `CREATE_DATE`, `CREATER`, `MODULE_CODE`, `OPERA_CODE`, `ROLE_ID`) values('62','2016-06-19 09:40:14','admin','00040002','000400020007','1');


# 假期类型表
insert into `oa_leave_type` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `CREATER`, `MODIFIER`) values('001','2016-03-19 09:54:01','2016-03-19 09:54:01','年假','年假不扣工资','admin','admin');
insert into `oa_leave_type` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `CREATER`, `MODIFIER`) values('002','2016-03-19 09:54:01','2016-03-21 20:45:21','病假','病假扣10%工资','admin','admin');
insert into `oa_leave_type` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `CREATER`, `MODIFIER`) values('003','2016-03-19 09:54:01','2016-03-19 09:54:01','休假','补休','admin','admin');
insert into `oa_leave_type` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `CREATER`, `MODIFIER`) values('004','2016-03-19 09:54:01','2016-03-19 09:54:01','产假','产假','admin','admin');
insert into `oa_leave_type` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `CREATER`, `MODIFIER`) values('005','2016-03-19 09:54:01','2016-03-19 09:54:01','陪产假','陪产假','admin','admin');
insert into `oa_leave_type` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `CREATER`, `MODIFIER`) values('006','2016-03-19 09:54:01','2016-03-19 09:54:01','婚假','婚假','admin','admin');
insert into `oa_leave_type` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `CREATER`, `MODIFIER`) values('007','2016-03-19 09:54:01','2016-03-19 09:54:01','探亲假','探亲假','admin','admin');
insert into `oa_leave_type` (`CODE`, `CREATE_DATE`, `MODIFY_DATE`, `NAME`, `REMARK`, `CREATER`, `MODIFIER`) values('008','2016-03-21 16:12:22','2016-03-19 09:54:01','事假','事假','admin','admin');

# 联系组表
insert into `oa_ab_group` (`ID`, `NAME`, `REMARK`) values('1','朋友','朋友');
insert into `oa_ab_group` (`ID`, `NAME`, `REMARK`) values('2','客户','客户');
insert into `oa_ab_group` (`ID`, `NAME`, `REMARK`) values('3','同事','同事');
insert into `oa_ab_group` (`ID`, `NAME`, `REMARK`) values('4','同学','同学');

UPDATE oa_id_user SET dept_id = 1, job_code = '0005' WHERE user_id = 'admin';