package com.kapphk.system.bean;

import com.kapphk.base.bean.BaseModel;
import java.util.Date;

/**
 * 主键：sys_guide
 * @author zoneyu 2016-12-06
*/
public class Guide extends BaseModel {

    /**
     * 表字段：sys_guide.id 注释：主键id
     * @author zoneyu 2016-12-06
     */
    private Long id;
    /**
     * 表字段：sys_guide.logo_path 注释：图片路径
     * @author zoneyu 2016-12-06
     */
    private String logoPath;
    /**
     * 表字段：sys_guide.record_type 注释：类型 1:首页
     * @author zoneyu 2016-12-06
     */
    private Integer recordType;
    /**
     * 表字段：sys_guide.content 注释：内容
     * @author zoneyu 2016-12-06
     */
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}