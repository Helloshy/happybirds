package com.kapphk.web.bean.system;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：sys_message_push
 * @author zoneyu 2016-11-04
*/
public class BeanMessagePush extends BaseModel {

    /**
     * 表字段：sys_message_push.id 注释：主键id
     * @author zoneyu 2016-11-04
     */
    private Long id;
    /**
     * 表字段：sys_message_push.title 注释：标题
     * @author zoneyu 2016-11-04
     */
    private String title;
    /**
     * 表字段：sys_message_push.remark 注释：备注
     * @author zoneyu 2016-11-04
     */
    private String remark;
    /**
     * 表字段：sys_message_push.content 注释：内容
     * @author zoneyu 2016-11-04
     */
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}