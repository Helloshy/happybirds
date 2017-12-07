package com.kapphk.web.bean.course;

import java.math.BigDecimal;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键ID：app_course_use
 * @author zoneyu 2016-11-25
*/
public class BeanCourseUse extends BaseModel {

    /**
     * 表字段：app_course_use.id 注释：主键ID
     * @author zoneyu 2016-11-25
     */
    private Long id;
    /**
     * 表字段：app_course_use.uid 注释：用户id
     * @author zoneyu 2016-11-25
     */
    private Long uid;
    /**
     * 表字段：app_course_use.use_month 注释：月份
     * @author zoneyu 2016-11-25
     */
    private String useMonth;
    /**
     * 表字段：app_course_use.unuse_course_name 注释：当月未消耗课程
     * @author zoneyu 2016-11-25
     */
    private String unuseCourseName;
    /**
     * 表字段：app_course_use.use_course_name 注释：当月已消耗课程
     * @author zoneyu 2016-11-25
     */
    private String useCourseName;
    /**
     * 表字段：app_course_use.use_counts 注释：当月已消耗量
     * @author zoneyu 2016-11-25
     */
    private Integer useCounts;
    /**
     * 表字段：app_course_use.use_amount 注释：当月已消总金额
     * @author zoneyu 2016-11-25
     */
    private BigDecimal useAmount;
    /**
     * 表字段：app_course_use.unuse_counts 注释：当月未消耗量
     * @author zoneyu 2016-11-25
     */
    private Integer unuseCounts;
    /**
     * 表字段：app_course_use.unuse_amount 注释：当月未消耗总金额
     * @author zoneyu 2016-11-25
     */
    private BigDecimal unuseAmount;

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

    public String getUseMonth() {
        return useMonth;
    }

    public void setUseMonth(String useMonth) {
        this.useMonth = useMonth;
    }

    public String getUnuseCourseName() {
        return unuseCourseName;
    }

    public void setUnuseCourseName(String unuseCourseName) {
        this.unuseCourseName = unuseCourseName;
    }

    public String getUseCourseName() {
        return useCourseName;
    }

    public void setUseCourseName(String useCourseName) {
        this.useCourseName = useCourseName;
    }

    public Integer getUseCounts() {
        return useCounts;
    }

    public void setUseCounts(Integer useCounts) {
        this.useCounts = useCounts;
    }

    public BigDecimal getUseAmount() {
        return useAmount;
    }

    public void setUseAmount(BigDecimal useAmount) {
        this.useAmount = useAmount;
    }

    public Integer getUnuseCounts() {
        return unuseCounts;
    }

    public void setUnuseCounts(Integer unuseCounts) {
        this.unuseCounts = unuseCounts;
    }

    public BigDecimal getUnuseAmount() {
        return unuseAmount;
    }

    public void setUnuseAmount(BigDecimal unuseAmount) {
        this.unuseAmount = unuseAmount;
    }
}