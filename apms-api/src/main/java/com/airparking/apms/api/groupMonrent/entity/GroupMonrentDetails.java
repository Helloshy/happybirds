package com.airparking.apms.api.groupMonrent.entity;

import com.airparking.core.base.entity.AbstractEntity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/14.
 */
public class GroupMonrentDetails implements Serializable {
    private Long id;
    private String spaceNo;
    private String mrrid;
    private Long groupMonrentId;

    public String getSpaceNo() {
        return spaceNo;
    }

    public void setSpaceNo(String spaceNo) {
        this.spaceNo = spaceNo;
    }

    public String getMrrid() {
        return mrrid;
    }

    public void setMrrid(String mrrid) {
        this.mrrid = mrrid;
    }

    public Long getGroupMonrentId() {
        return groupMonrentId;
    }

    public void setGroupMonrentId(Long groupMonrentId) {
        this.groupMonrentId = groupMonrentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
