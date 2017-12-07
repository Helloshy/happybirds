package com.kapphk.web.bean.system;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：sys_setting
 * @author zoneyu 2016-10-28
*/
public class BeanSetting extends BaseModel {

    /**
     * 表字段：sys_setting.id 注释：主键id
     * @author zoneyu 2016-10-28
     */
    private String id;
    /**
     * 表字段：sys_setting.item_name 注释：参数名称
     * @author zoneyu 2016-10-28
     */
    private String itemName;
    /**
     * 表字段：sys_setting.value 注释：参数值
     * @author zoneyu 2016-10-28
     */
    private String value;
    /**
     * 表字段：sys_setting.remark 注释：描述
     * @author zoneyu 2016-10-28
     */
    private String remark;
    /**
     * 表字段：sys_setting.item_type 注释：参数类型
     * @author zoneyu 2016-10-28
     */
    private String itemType;
    private String value1;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}
    
}