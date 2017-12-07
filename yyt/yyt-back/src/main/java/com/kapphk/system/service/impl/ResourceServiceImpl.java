package com.kapphk.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.base.persistence.BaseServiceImpl;
import com.kapphk.system.bean.Resource;
import com.kapphk.system.bean.RoleResource;
import com.kapphk.system.mapper.ResourceMapper;
import com.kapphk.system.mapper.RoleResourceMapper;
import com.kapphk.system.service.ResourceService;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 菜单业务层
 * @author zoneyu 16-6-3
 */
@Service
public class ResourceServiceImpl extends
		BaseServiceImpl<Resource, Long> implements ResourceService {
	
	@Autowired
	private ResourceMapper mapper;
	@Autowired
	private RoleResourceMapper roleResourceMapper;
	
	public void init() {
		super.setMapper(mapper);
	}

	@Override
	public List<Resource> findResourceByRoleId(Long roleId){
		return mapper.findResourceByRoleId(roleId);
	}
	
	@Override
	public List<Resource> findUserResourcesByUserId(Long userId, List<Resource> list) {
		return null;
	}

	@Override
	public List<Map<String, Object>> searchResourceList(List<Map<String, Object>> list) {
		return null;
	}

	@Override
	public Result saveData(Resource bean, Result rs) {
		if(ValidateUtils.isBlank(bean.getId())){
			bean.setCreateTime(new Date());
			mapper.insert(bean) ;
		}else{
			mapper.update(bean) ;
		}
		return rs ;
	}
	

	@Override
	public Result delete(String ids, Result rs){
		List<Long> idss = DataUtils.string2List(ids) ;
		mapper.deletes(idss) ;
		return rs ;
	}

	@Override
	public Result saveRoleResOfBatch(Long roleId,String resourceIds, Result rs) {
		if(!StringUtils.isBlank(resourceIds)){
			
			String[] resourceId = resourceIds.split(",");
			List<RoleResource> list = new ArrayList<RoleResource>();
			for(int i=0;i<resourceId.length;i++){
				Map<String,Object> map = new HashMap<String, Object>();
				RoleResource roleResource = new RoleResource();
				map.put("roleId", roleId);
				map.put("resourceId", resourceId[i]);
				roleResource.setRoleId(roleId);
				roleResource.setResourceId(Long.parseLong(resourceId[i]));
				list.add(roleResource);
			}
			roleResourceMapper.inserts(list);
			
		}else {
			roleResourceMapper.deleteByRoleId(roleId);
		}
		return rs;
		
	}

	@Override
	public List<Resource> findByParentId(Long parentId) {
		return mapper.findByParentId(parentId);
	}
	

}
