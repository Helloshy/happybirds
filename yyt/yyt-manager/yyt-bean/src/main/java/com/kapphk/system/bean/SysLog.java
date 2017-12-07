package com.kapphk.system.bean;

import com.kapphk.base.bean.BaseModel;
import java.util.Date;

/**
 * ：sys_log
 * @author zoneyu 2016-12-06
*/
public class SysLog extends BaseModel {

    /**
     * 表字段：sys_log.id 注释：
     * @author zoneyu 2016-12-06
     */
    private Long id;
    /**
     * 表字段：sys_log.sid 注释：系统用户ID
     * @author zoneyu 2016-12-06
     */
    private Long sid;
    /**
     * 表字段：sys_log.user_name 注释：操作人
     * @author zoneyu 2016-12-06
     */
    private String userName;
    /**
     * 表字段：sys_log.ip_address 注释：登录ip
     * @author zoneyu 2016-12-06
     */
    private String ipAddress;
    /**
     * 表字段：sys_log.operation_type 注释：登录类型
     * @author zoneyu 2016-12-06
     */
    private String operationType;
    /**
     * 表字段：sys_log.event_name 注释：登录事件
     * @author zoneyu 2016-12-06
     */
    private String eventName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}