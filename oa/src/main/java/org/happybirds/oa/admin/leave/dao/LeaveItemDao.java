package org.happybirds.oa.admin.leave.dao;

import java.util.List;

import org.happybirds.oa.admin.leave.entity.LeaveItem;
import org.happybirds.oa.core.common.web.PageModel;
import org.happybirds.oa.core.dao.HibernateDao;


/**
 * 假期明细数据访问接口
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午5:25:22
 * @version 1.0
 */
public interface LeaveItemDao extends HibernateDao {
    
    /**
     * TODO   分页条件查询当前用户请假明细
     * @param pageModel 
     * @param userId
     * @return
     * @Since 2016年8月7日
     * @Version 1.0
     */
    List<LeaveItem> getLeaveItemByPage(LeaveItem leaveItem, PageModel pageModel);

}
