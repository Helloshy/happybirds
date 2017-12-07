package org.happybirds.oa.admin.leave.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.happybirds.oa.admin.AdminConstant;
import org.happybirds.oa.admin.leave.dao.LeaveItemDao;
import org.happybirds.oa.admin.leave.entity.LeaveItem;
import org.happybirds.oa.admin.leave.entity.LeaveType;
import org.happybirds.oa.core.common.web.PageModel;
import org.happybirds.oa.core.dao.impl.HibernateDaoImpl;
import org.happybirds.oa.core.exception.OAException;



/**
 * 假期明细数据访问接口实现类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午5:27:14
 * @version 1.0
 */
public class LeaveItemDaoImpl extends HibernateDaoImpl implements LeaveItemDao {
    
    /**
     * TODO   分页条件查询当前用户请假明细
     * @param userId
     * @return
     * @Since 2016年8月7日
     * @Version 1.0
     */
    public List<LeaveItem> getLeaveItemByPage(LeaveItem leaveItem, PageModel pageModel){
        try {
            StringBuilder sb = new StringBuilder();
            List<Object> parames = new ArrayList<Object>();
            sb.append("from LeaveItem");
            
            if(leaveItem!=null&&leaveItem.getCreater()!=null&&leaveItem.getCreater().getUserId()!=null){
                parames.add(leaveItem.getCreater().getUserId());
                sb.append(" where creater.id=?");
            }
            if(leaveItem!=null){
                LeaveType leaveType = leaveItem.getLeaveType();
                if(leaveType!=null){
                    sb.append("  and leaveType.code=?");
                    parames.add(leaveType.getCode());
                }
            }
            return this.findByPage(sb.toString(), pageModel, parames);
         } catch (Exception e) {
             e.printStackTrace();
             throw new OAException("分页查询请假明细出现异常");
         }
    }
}
