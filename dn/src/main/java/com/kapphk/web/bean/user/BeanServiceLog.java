package com.kapphk.web.bean.user;

import java.util.Date;

import com.kapphk.web.bean.BaseModel;

/**
 * ：app_service_log
 * @author zoneyu 2016-11-04
*/
public class BeanServiceLog extends BaseModel {

    /**
     * 表字段：app_service_log.id 注释：id
     * @author zoneyu 2016-11-04
     */
    private Long id;
    /**
     * 表字段：app_service_log.service_id 注释：客服id
     * @author zoneyu 2016-11-04
     */
    private Long serviceId;
    /**
     * 表字段：app_service_log.uid 注释：用户id
     * @author zoneyu 2016-11-04
     */
    private Long uid;
    /**
     * 表字段：app_service_log.log_content 注释：回访内容
     * @author zoneyu 2016-11-04
     */
    private String logContent;
    /**
     * 表字段：app_service_log.log_date 注释：回访时间
     * @author zoneyu 2016-11-04
     */
    private Date logDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }
}