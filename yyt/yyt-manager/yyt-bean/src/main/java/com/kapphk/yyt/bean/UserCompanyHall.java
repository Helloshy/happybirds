package com.kapphk.yyt.bean;

import com.kapphk.base.bean.BaseModel;

/**
 * ID：user_company_hall
 * @author zoneyu 2016-12-06
*/
public class UserCompanyHall extends BaseModel {

    /**
     * 表字段：user_company_hall.id 注释：ID
     * @author zoneyu 2016-12-06
     */
    private Long id;
    /**
     * 表字段：user_company_hall.sid 注释：员工ID
     * @author zoneyu 2016-12-06
     */
    private Long sid;
    /**
     * 表字段：user_company_hall.company_hall_id 注释：营业厅ID
     * @author zoneyu 2016-12-06
     */
    private Long companyHallId;

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

    public Long getCompanyHallId() {
        return companyHallId;
    }

    public void setCompanyHallId(Long companyHallId) {
        this.companyHallId = companyHallId;
    }
}