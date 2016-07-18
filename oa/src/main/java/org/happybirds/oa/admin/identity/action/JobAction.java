package org.happybirds.oa.admin.identity.action;

import java.util.List;

import org.happybirds.oa.admin.identity.entity.Job;
import org.happybirds.oa.admin.identity.service.IdentityService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * JobAction
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午5:38:00
 * @version 1.0
 */
public class JobAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4101440155621349762L;
	private IdentityService identityService;
	
	@Override
	public String execute() throws Exception {
		
		List<Job> jobs = identityService.getJobs();
		System.out.println(jobs);
		
		return NONE;
	}

	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}
}
