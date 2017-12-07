package org.happybirds.oa.admin.leave.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.happybirds.oa.admin.leave.dao.LeaveAuditDao;
import org.happybirds.oa.admin.leave.dao.LeaveItemDao;
import org.happybirds.oa.admin.leave.dao.LeaveTypeDao;
import org.happybirds.oa.admin.leave.entity.LeaveItem;
import org.happybirds.oa.admin.leave.service.LeaveService;
import org.happybirds.oa.core.common.web.PageModel;
import org.happybirds.oa.core.exception.OAException;



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
	
	/**
	     * TODO   分页条件查询当前用户请假明细
	     * @param userId
	     * @return
	     * @Since 2016年8月7日
	     * @Version 1.0
    	     */
    	public  List<LeaveItem> getLeaveItemByPage(LeaveItem leaveItem, PageModel pageModel){
	   try {
	       List<LeaveItem> leaveItems = leaveItemDao.getLeaveItemByPage(leaveItem,pageModel);
	       return leaveItems;
            } catch (Exception e) {
                e.printStackTrace();
                throw new OAException("分页查询请假明细出现异常");
            }             
    	}
	
	
}
