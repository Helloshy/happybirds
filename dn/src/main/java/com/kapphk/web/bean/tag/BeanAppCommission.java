package com.kapphk.web.bean.tag;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_commission
 * @author zoneyu 2016-10-28
*/
public class BeanAppCommission extends BaseModel {

    /**
     * 表字段：app_commission.id 注释：主键id
     * @author zoneyu 2016-10-28
     */
    private Long id;
    /**
     * 表字段：app_commission.tag_id 注释：个人身份
     * @author zoneyu 2016-10-28
     */
    private String tagId;
    /**
     * 表字段：app_commission.tag_type 注释：标签类型
     * @author zoneyu 2016-10-28
     */
    private String tagType;
    /**
     * 表字段：app_commission.level_one 注释：一级分佣比例
     * @author zoneyu 2016-10-28
     */
    private BigDecimal levelOne;
    /**
     * 表字段：app_commission.level_two 注释：二级分佣比例
     * @author zoneyu 2016-10-28
     */
    private BigDecimal levelTwo;
    /**
     * 表字段：app_commission.level_three 注释：三级分佣比例
     * @author zoneyu 2016-10-28
     */
    private BigDecimal levelThree;
    /**
     * 表字段：app_commission.level_one_upgrade 注释：一级客户身份升级获得返佣比例
     * @author zoneyu 2016-10-28
     */
    private BigDecimal levelOneUpgrade;
    /**
     * 表字段：app_commission.modify_time 注释：修改时间
     * @author zoneyu 2016-10-28
     */
    private Date modifyTime;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public BigDecimal getLevelOne() {
        return levelOne;
    }

    public void setLevelOne(BigDecimal levelOne) {
        this.levelOne = levelOne;
    }

    public BigDecimal getLevelTwo() {
        return levelTwo;
    }

    public void setLevelTwo(BigDecimal levelTwo) {
        this.levelTwo = levelTwo;
    }

    public BigDecimal getLevelThree() {
        return levelThree;
    }

    public void setLevelThree(BigDecimal levelThree) {
        this.levelThree = levelThree;
    }

    public BigDecimal getLevelOneUpgrade() {
        return levelOneUpgrade;
    }

    public void setLevelOneUpgrade(BigDecimal levelOneUpgrade) {
        this.levelOneUpgrade = levelOneUpgrade;
    }

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
    
}