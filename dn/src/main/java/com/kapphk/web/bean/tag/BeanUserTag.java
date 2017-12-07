package com.kapphk.web.bean.tag;

import com.kapphk.web.bean.BaseModel;

public class BeanUserTag extends BaseModel {
	private String id;
	private String tagType;
	private Integer rowSeq;
	private String logoPath;
	private String oldId;
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
	public String getOldId() {
		return oldId;
	}
	public void setOldId(String oldId) {
		this.oldId = oldId;
	}
	
}
