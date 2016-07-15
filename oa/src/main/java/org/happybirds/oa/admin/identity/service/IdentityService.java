package cn.itcast.oa.admin.identity.service;

import java.util.List;

import cn.itcast.oa.admin.identity.entity.Job;

/**
 * 权限模块业务处理接口
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午5:31:44
 * @version 1.0
 */
public interface IdentityService {

	List<Job> getJobs();

}
