package org.happybirds.oa.admin.identity.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * 职位实体
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午3:22:28
 * @version 1.0
 */
@Entity @Table(name="OA_ID_JOB")
public class Job implements Serializable {
	
	private static final long serialVersionUID = -7138188065684767111L;
	/**
	 * CODE	VARCHAR(100)	
	 * 代码	PK主键(0001...0002)四位为模块;
	 * (00010001..)八位为操作
	 */
	@Id @Column(name="CODE", length=100)
	private String code;
	/** NAME VARCHAR2(50) 名称	*/
	@Column(name="NAME", length=50)
	private String name;
	/** REMARK	VARCHAR2(300) 职位说明 */
	@Column(name="REMARK", length=300)
	private String remark;
	
	/** setter and getter method */
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}