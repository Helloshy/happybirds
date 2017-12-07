package org.happybirds.oa.admin.leave.service;

import java.util.List;

import org.happybirds.oa.admin.leave.entity.LeaveItem;
import org.happybirds.oa.core.common.web.PageModel;

/**
 * 假期模块业务处理接口
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午5:33:45
 * @version 1.0
 */
public interface LeaveService {
   /**
    * TODO   分页条件查询假期用户假期明细
    * @param leaveItem
    * @param pageModel
    * @return
    * @Since 2016年8月7日
    * @Version 1.0
    */
    List<LeaveItem> getLeaveItemByPage(LeaveItem leaveItem, PageModel pageModel);

}
