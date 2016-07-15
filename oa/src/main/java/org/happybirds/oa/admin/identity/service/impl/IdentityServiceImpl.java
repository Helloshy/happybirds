package cn.itcast.oa.admin.identity.service.impl;

import java.util.List;

import cn.itcast.oa.admin.identity.dao.JobDao;
import cn.itcast.oa.admin.identity.entity.Job;
import cn.itcast.oa.admin.identity.service.IdentityService;

/**
 * 权限模块业务处理接口实现类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午5:32:15
 * @version 1.0
 */
public class IdentityServiceImpl implements IdentityService {
	
	private JobDao jobDao;

	@Override
	public List<Job> getJobs() {
		return jobDao.find("from Job");
	}

	public void setJobDao(JobDao jobDao) {
		this.jobDao = jobDao;
	}
}
