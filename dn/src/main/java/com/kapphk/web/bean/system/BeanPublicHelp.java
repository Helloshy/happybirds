package com.kapphk.web.bean.system;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：sys_public_help
 * @author zoneyu 2016-05-23
*/
public class BeanPublicHelp extends BaseModel {

    /**
     * 表字段：sys_public_help.id 注释：主键id
     * @author zoneyu 2016-05-23
     */
    private Long id;
    /**
     * 表字段：sys_public_help.title 注释：标题
     * @author zoneyu 2016-05-23
     */
    private String title;
    /**
     * 表字段：sys_public_help.memo 注释：内容
     * @author zoneyu 2016-05-23
     */
    private String memo;
    private Integer msgType;

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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}
    
}