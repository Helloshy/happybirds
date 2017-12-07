package com.kapphk.entity;

import java.util.Date;

import com.kapphk.system.bean.Role;

public class PermissionEntity extends Role{
	
	private Long id;
	
	private String roleName;
	
	private Date createTime;
	
	private String rolePage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRolePage() {
		return rolePage;
	}

	public void setRolePage(String rolePage) {
		this.rolePage = rolePage;
	}

}
