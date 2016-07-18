package org.happybirds.oa.admin.leave.service.impl;

import javax.annotation.Resource;

import org.happybirds.oa.admin.leave.dao.LeaveAuditDao;
import org.happybirds.oa.admin.leave.dao.LeaveItemDao;
import org.happybirds.oa.admin.leave.dao.LeaveTypeDao;
import org.happybirds.oa.admin.leave.service.LeaveService;



/**
 * 假期模块业务处理接口实现类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午5:34:23
 * @version 1.0
 */
public class LeaveServiceImpl implements LeaveService {
	/**
	 * 注入dao接口实体
	 * 
	 * 
	 */
	@Resource
	private LeaveAuditDao leaveAuditDao;
	@Resource
	private LeaveItemDao leaveItemDao;
	@Resource
	private LeaveTypeDao leaveTypeDao;
	
}
