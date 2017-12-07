package com.kapphk.system.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseService;
import com.kapphk.entity.CommunityEntity;
import com.kapphk.system.bean.SystemUser;
import com.kapphk.yyt.bean.Community;

/**小区业务层
 * Created by shy on 2016/12/7.
 */


public interface CommunityService extends BaseService<Community,Long>{
	
    List<Community> findByCompanyId(Long companyId);

	PageInfo<CommunityEntity> findByPageByParam(Community community, Integer page, Integer rows, String sort);

	/**
	 * 获取抄表人员
	 * @param hallId
	 * @return
	 */
	List<SystemUser> getstaffByHallId(Long hallId);
}
