package com.kapphk.web.bean.system;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.kapphk.web.bean.BaseModel;


/**
 * 主键：user_opinion
 * @author zoneyu 2016-09-20
*/
public class BeanOpinion extends BaseModel {

    /**
     * 表字段：user_opinion.id 注释：主键id
     * @author zoneyu 2016-09-20
     */
    private Long id;
    /**
     * 表字段：user_opinion.uid 注释：用户id
     * @author zoneyu 2016-09-20
     */
    private Long uid;
    /**
     * 表字段：user_opinion.content 注释：反馈内容
     * @author zoneyu 2016-09-20
     */
    private String content;
    /**
     * 表字段：user_opinion.feedback 注释：回复内容
     * @author zoneyu 2016-09-20
     */
    private String feedback;
    private Integer recordType;
    private Date feedbackTime;
    
    public Integer getRecordType() {
		return recordType;
	}

	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
	public Date getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}
    
    
}