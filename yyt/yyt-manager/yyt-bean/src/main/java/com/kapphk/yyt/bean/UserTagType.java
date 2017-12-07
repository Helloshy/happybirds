package com.kapphk.yyt.bean;

import com.kapphk.base.bean.BaseModel;

/**
 * 主键：user_tag_type
 * @author zoneyu 2016-12-05
*/
public class UserTagType extends BaseModel {

    /**
     * 表字段：user_tag_type.id 注释：主键id
     * @author zoneyu 2016-12-05
     */
    private String id;
    /**
     * 表字段：user_tag_type.state 注释：状态: 0 不可用; 1 可用
     * @author zoneyu 2016-12-05
     */
    private Integer state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}