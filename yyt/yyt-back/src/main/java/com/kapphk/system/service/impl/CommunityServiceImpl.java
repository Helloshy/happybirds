package com.kapphk.system.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseServiceImpl;
import com.kapphk.entity.CommunityEntity;
import com.kapphk.system.bean.SystemUser;
import com.kapphk.system.mapper.SystemUserMapper;
import com.kapphk.system.service.CommunityService;
import com.kapphk.yyt.bean.Community;
import com.kapphk.yyt.mapper.CommunityMapper;

/**小区业务层
 * Created by shy on 2016/12/7.
 */

@Service
public class CommunityServiceImpl extends BaseServiceImpl<Community,Long> implements CommunityService{

    @Autowired
    private CommunityMapper mapper;
    
    @Autowired
    private SystemUserMapper systemUserMapper;

    @Override
    public void init() {
        super.setMapper(this.mapper);
    }

    @Override
    public List<Community> findByCompanyId(Long companyId) {
        Community community = new Community();
        community.setCompanyId(companyId);
        List<Community> communities = mapper.findAll(community);
        return communities;
    }

	@Override
	public PageInfo<CommunityEntity> findByPageByParam(Community community, Integer page, Integer rows, String sort) {
		PageHelper.startPage(page,rows);
        if (!StringUtils.isBlank(sort)){
            PageHelper.orderBy(sort);
        }
        List<CommunityEntity> resultMaps = mapper.findListByParam(community);
        PageInfo<CommunityEntity> pageInfo = new PageInfo<CommunityEntity>(resultMaps);
        return pageInfo;
	}

	
	@Override
	public List<SystemUser> getstaffByHallId(Long hallId) {
		
		return systemUserMapper.findHallUser(hallId);
	}
}
