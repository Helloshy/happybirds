package com.kapphk.yyt.bean;

import com.kapphk.base.bean.BaseModel;

/**
 * ：user_number_map
 * @author zoneyu 2016-12-06
*/
public class UserNumberMap extends BaseModel {

    /**
     * 表字段：user_number_map.id 注释：id
     * @author zoneyu 2016-12-06
     */
    private Long id;
    /**
     * 表字段：user_number_map.uid 注释：用户id
     * @author zoneyu 2016-12-06
     */
    private Long uid;
    /**
     * 表字段：user_number_map.unid 注释：用户燃气编号
     * @author zoneyu 2016-12-06
     */
    private Long unid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getUnid() {
        return unid;
    }

    public void setUnid(Long unid) {
        this.unid = unid;
    }
}