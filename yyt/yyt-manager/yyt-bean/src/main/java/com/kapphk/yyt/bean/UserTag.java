package com.kapphk.yyt.bean;

import java.math.BigDecimal;

import com.kapphk.base.bean.BaseModel;

/**
 * 主键：user_tag
 * @author zoneyu 2016-12-05
*/
public class UserTag extends BaseModel {

    /**
     * 表字段：user_tag.id 注释：主键id
     * @author zoneyu 2016-12-05
     */
    private String id;
    /**
     * 表字段：user_tag.tag_type 注释：标签类型
     * @author zoneyu 2016-12-05
     */
    private String tagType;
    /**
     * 表字段：user_tag.state 注释：状态: 0 不可用; 1 可用
     * @author zoneyu 2016-12-05
     */
    private Integer state;
    /**
     * 表字段：user_tag.row_seq 注释：排序
     * @author zoneyu 2016-12-05
     */
    private Integer rowSeq;
    /**
     * 表字段：user_tag.logo_path 注释：图标
     * @author zoneyu 2016-12-05
     */
    private String logoPath;
    /**
     * 表字段：user_tag.value_from 注释：开始值
     * @author zoneyu 2016-12-05
     */
    private BigDecimal valueFrom;
    /**
     * 表字段：user_tag.value_to 注释：结束值
     * @author zoneyu 2016-12-05
     */
    private BigDecimal valueTo;
    /**
     * 表字段：user_tag.sort 注释：排序
     * @author zoneyu 2016-12-05
     */
    private Long sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getRowSeq() {
        return rowSeq;
    }

    public void setRowSeq(Integer rowSeq) {
        this.rowSeq = rowSeq;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public BigDecimal getValueFrom() {
        return valueFrom;
    }

    public void setValueFrom(BigDecimal valueFrom) {
        this.valueFrom = valueFrom;
    }

    public BigDecimal getValueTo() {
        return valueTo;
    }

    public void setValueTo(BigDecimal valueTo) {
        this.valueTo = valueTo;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }
}