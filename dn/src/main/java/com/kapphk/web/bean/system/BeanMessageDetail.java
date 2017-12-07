package com.kapphk.web.bean.system;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：sys_message_detail
 * @author zoneyu 2016-11-04
*/
public class BeanMessageDetail extends BaseModel {

    /**
     * 表字段：sys_message_detail.id 注释：主键id
     * @author zoneyu 2016-11-04
     */
    private Long id;
    /**
     * 表字段：sys_message_detail.message_id 注释：消息id
     * @author zoneyu 2016-11-04
     */
    private Long messageId;
    /**
     * 表字段：sys_message_detail.uid 注释：接受用户id
     * @author zoneyu 2016-11-04
     */
    private Long uid;
    /**
     * 表字段：sys_message_detail.is_read 注释：0:未读 1:已读
     * @author zoneyu 2016-11-04
     */
    private Integer isRead;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
}