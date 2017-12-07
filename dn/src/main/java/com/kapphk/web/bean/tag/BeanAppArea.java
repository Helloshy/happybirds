package com.kapphk.web.bean.tag;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_area
 * @author zoneyu 2016-10-28
*/
public class BeanAppArea extends BaseModel {

    /**
     * 表字段：app_area.id 注释：主键id
     * @author zoneyu 2016-10-28
     */
    private String id;
    
    private String oldId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getOldId() {
		return oldId;
	}

	public void setOldId(String oldId) {
		this.oldId = oldId;
	}
    
}