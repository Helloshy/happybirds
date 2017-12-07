package com.kapphk.system.bean;

import com.kapphk.base.bean.BaseModel;
import java.util.Date;

/**
 * 主键：sys_message
 * @author zoneyu 2016-12-06
*/
public class Message extends BaseModel {

    /**
     * 表字段：sys_message.id 注释：主键
     * @author zoneyu 2016-12-06
     */
    private Long id;
    /**
     * 表字段：sys_message.title 注释：推送标题
     * @author zoneyu 2016-12-06
     */
    private String title;
    /**
     * 表字段：sys_message.msg_type 注释：1：缴费成功  2：欠费推送   3：停气消息推送
     * @author zoneyu 2016-12-06
     */
    private Byte msgType;
    /**
     * 表字段：sys_message.content 注释：推送内容
     * @author zoneyu 2016-12-06
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

    public Byte getMsgType() {
        return msgType;
    }

    public void setMsgType(Byte msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}