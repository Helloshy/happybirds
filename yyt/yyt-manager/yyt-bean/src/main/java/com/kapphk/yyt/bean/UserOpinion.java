package com.kapphk.yyt.bean;

import com.kapphk.base.bean.BaseModel;

/**
 * 主键：user_opinion
 * @author zoneyu 2016-12-09
*/
public class UserOpinion extends BaseModel {

    /**
     * 表字段：user_opinion.id 注释：主键id
     * @author zoneyu 2016-12-09
     */
    private Long id;
    /**
     * 表字段：user_opinion.company_id 注释：公司id
     * @author zoneyu 2016-12-09
     */
    private Long companyId;
    /**
     * 表字段：user_opinion.uid 注释：用户id
     * @author zoneyu 2016-12-09
     */
    private Long uid;
    /**
     * 表字段：user_opinion.content 注释：反馈内容
     * @author zoneyu 2016-12-09
     */
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}