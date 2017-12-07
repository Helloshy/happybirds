package com.kapphk.web.bean.teacher;

import java.math.BigDecimal;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_teacher_comment
 * @author zoneyu 2016-10-08
*/
public class BeanTeacherComment extends BaseModel {

    /**
     * 表字段：app_teacher_comment.id 注释：主键id
     * @author zoneyu 2016-10-08
     */
    private Long id;
    /**
     * 表字段：app_teacher_comment.teacher_id 注释：名师id
     * @author zoneyu 2016-10-08
     */
    private Long teacherId;
    /**
     * 表字段：app_teacher_comment.uid 注释：用户id
     * @author zoneyu 2016-10-08
     */
    private Long uid;
    /**
     * 表字段：app_teacher_comment.order_id 注释：预约订单号
     * @author zoneyu 2016-10-08
     */
    private Long orderId;
    /**
     * 表字段：app_teacher_comment.star 注释：评论星级
     * @author zoneyu 2016-10-08
     */
    private BigDecimal star;
    /**
     * 表字段：app_teacher_comment.content 注释：评论内容
     * @author zoneyu 2016-10-08
     */
    private String content;

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

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getStar() {
        return star;
    }

    public void setStar(BigDecimal star) {
        this.star = star;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}