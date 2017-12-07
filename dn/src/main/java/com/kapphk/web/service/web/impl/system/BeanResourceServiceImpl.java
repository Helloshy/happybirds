package com.kapphk.web.service.web.impl.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.system.BeanResource;
import com.kapphk.web.bean.system.BeanStandardResource;
import com.kapphk.web.dao.mapper.system.BeanResourceMapper;
import com.kapphk.web.service.web.imethod.system.BeanResourceService;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 菜单业务层
 * @author dengwen 
 * 2016-9-16下午1:41:26
 */
@Service
public class BeanResourceServiceImpl implements BeanResourceService {
	
	@Autowired
	private BeanResourceMapper mapper;
	
	/**
	 * 查询菜单目录
	 */
	public List<BeanStandardResource> findUserResourcesByUserId(Long userId,List<BeanStandardResource> list) {
		List<BeanResource> resourcelist = new ArrayList<BeanResource>();
		Map<String, Object> map = mapper.findRoleByUserId(userId);
		if("ROLE_SUPER_ADMIN".equals(map.get("roleValue"))){
			resourcelist = mapper.findResourcesById(null);
		}else{
			List<Long> ids = mapper.findResourceByRoleId((Long)map.get("id"));
			resourcelist = mapper.findResourcesById(ids);
		}
		
		for (BeanResource r : resourcelist) {
			if(r.getParentId() == -1){
				BeanStandardResource s1 = new BeanStandardResource();
				s1.setId(r.getId());
				s1.setText(r.getResourceName());
				s1.setUrl(r.getUrl());
				s1.setState("closed");
				List<BeanStandardResource> list2 = new ArrayList<BeanStandardResource>();
				for (BeanResource r1 : resourcelist) {
					if(r1.getParentId() - r.getId() == 0){
						BeanStandardResource s2 = new BeanStandardResource();
						s2.setId(r1.getId());
						s2.setText(r1.getResourceName());
						s2.setUrl(r1.getUrl());
						
						List<BeanStandardResource> list3 = new ArrayList<BeanStandardResource>();
						for(BeanResource r3 : resourcelist){
							if(r3.getParentId() - r1.getId() == 0){
								BeanStandardResource s3 = new BeanStandardResource();
								s3.setId(r3.getId());
								s3.setText(r3.getResourceName());
								s3.setUrl(r3.getUrl());
								list3.add(s3) ;
							}
						}
						if(!ValidateUtils.isEmptyForCollection(list3)){
							s2.setChildren(list3);
							s1.setState("closed");
						}
						list2.add(s2) ;
					}
				}
				s1.setChildren(list2);
				if(!ValidateUtils.isEmptyForCollection(list2))
				list.add(s1);
			}
		}
		return list ;
	}

	/**
	 * 初始化菜单下拉框
	 */
	@Override
	public List<Map<String, Object>> searchResourceList(
			List<Map<String, Object>> list) {
		List<BeanResource> resourceList = mapper.findAll(new BeanResource()) ;
		Collections.reverse(resourceList) ;
		for (BeanResource r : resourceList) {
			if(r.getParentId() == -1){
				Map<String,Object> map1 = new HashMap<String, Object>() ;
				map1.put("id", r.getId()) ;
				map1.put("text", r.getResourceName()) ;
				List<Map<String,Object>> list2 = new ArrayList<Map<String,Object>>();
				for (BeanResource r2 : resourceList) {
					if(r2.getParentId() - r.getId() == 0){
						Map<String,Object> map2 = new HashMap<String, Object>() ;
						map2.put("id", r2.getId()) ;
						map2.put("text", r2.getResourceName()) ;
						List<Map<String,Object>> list3 = new ArrayList<Map<String,Object>>();
						for(BeanResource r3 : resourceList){
							if(r3.getParentId() - r2.getId() == 0){
								Map<String,Object> map3 = new HashMap<String, Object>() ;
								map3.put("id", r3.getId()) ;
								map3.put("text", r3.getResourceName()) ;
								list3.add(map3) ;
							}
						}
						if(!ValidateUtils.isEmptyForCollection(list3)){
							map2.put("children", list3);
						}
						list2.add(map2) ;
					}
				}
				map1.put("children", list2);
				list.add(map1);
			}
		}
		return list ;
	}
}