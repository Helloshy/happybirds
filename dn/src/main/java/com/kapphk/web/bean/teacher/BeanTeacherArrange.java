package com.kapphk.web.bean.teacher;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_teacher_arrange
 * @author zoneyu 2016-10-11
*/
public class BeanTeacherArrange extends BaseModel {

    /**
     * 表字段：app_teacher_arrange.id 注释：主键id
     * @author zoneyu 2016-10-11
     */
    private Long id;
    /**
     * 表字段：app_teacher_arrange.teacher_id 注释：名师id
     * @author zoneyu 2016-10-11
     */
    private Long teacherId;
    /**
     * 表字段：app_teacher_arrange.arrange_date 注释：预约时间
     * @author zoneyu 2016-10-11
     */
    private Date arrangeDate;
    
    private Date endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    @JSONField(format="yyyy-MM-dd")
    public Date getArrangeDate() {
        return arrangeDate;
    }

    public void setArrangeDate(Date arrangeDate) {
        this.arrangeDate = arrangeDate;
    }

    @JSONField(format="yyyy-MM-dd")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
    
    

}