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
import com.kapphk.system.bean.Role;
import com.kapphk.system.bean.RoleResource;
import com.kapphk.system.mapper.RoleMapper;
import com.kapphk.system.mapper.RoleResourceMapper;
import com.kapphk.system.service.RoleService;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.DateUtils;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 角色业务层
 * @author zoneyu 16-6-3
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

	@Autowired
	private RoleMapper mapper;
	
	@Override
	public void init() {
		super.setMapper(mapper);
	}
	
	@Autowired
	private RoleResourceMapper roleResourceMapper ;
	
	@Override
	public List<Role> findRoleByUserId(Long userId){
		return mapper.findRoleByUserId(userId);
	}
	
	/**
	 * 查询
	 * @author zoneyu 16-6-3
	 */
	public Result getList(Role bean, int page, int rows,
			Result rs) throws Exception {
		int count = mapper.findByPageCount(bean) ;
		List<Role> list = mapper.findByPage(bean, (page-1)*rows, rows) ;
		rs = DataUtils.mergeData(count, list, rs) ;
		return rs ;
	}

	/**
	 * 修改
	 * @author zoneyu 16-6-3
	 */
	public Result getData(Long id, Result rs) throws Exception {
		rs.put("info", mapper.findById(id)) ;
		return rs ;
	}

	/**
	 * 保存
	 * @author zoneyu 16-6-3
	 */
	public Result saveData(Role bean, Result rs) throws Exception {
		if(StringUtils.isBlank(bean.getRoleName())){
			rs.setError(MSG.CUS_ERROR);
			rs.setMsg("角色名不能空！");
			return rs;
		}
		if(ValidateUtils.isBlank(bean.getId())){
			bean.setCreateTime(new Date());
			mapper.insert(bean) ;
		}else{
			mapper.update(bean) ;
		}
		return rs ;
	}

	/**
	 * 删除
	 * @author zoneyu 16-6-3
	 */
	public Result delete(String ids, Result rs) throws Exception {
		List<Long> idss = DataUtils.string2List(ids) ;
		mapper.deletes(idss) ;
		return rs ;
	}
	
	/**
	 * 保存角色授权
	 * @author cgj 15-12-17
	 */
	public Result saveGrant(Long id, String Resource, Result rs)
			throws Exception {
		//删除历史
		RoleResource m = new RoleResource() ;
		m.setRoleId(id) ;
		roleResourceMapper.deleteByEntity(m) ;
		
		List<RoleResource> list = new ArrayList<RoleResource>() ;
		if(!ValidateUtils.isBlank(Resource)){
			String[] Resources = Resource.split(",") ;
			for(String mid : Resources){
				RoleResource me = new RoleResource() ;
				me.setResourceId(Long.valueOf(mid)) ;
				me.setRoleId(id) ;
				list.add(me) ;
			}
		}
		if(!ValidateUtils.isEmptyForCollection(list)) roleResourceMapper.inserts(list) ;
		return rs ;
	}

	/**
	 * 查询角色授权
	 * @author cgj 15-12-17
	 */
	public List<Map<String,Object>> searchGrant(Long id,List<Map<String,Object>> list) throws Exception {
		if(!ValidateUtils.isBlank(id)){
			RoleResource m = new RoleResource() ;
			m.setRoleId(id) ;
			List<RoleResource> ResourceList = roleResourceMapper.findAll(m) ;
			if(!ValidateUtils.isEmptyForCollection(ResourceList)){
				for(RoleResource b : ResourceList){
					Map<String,Object> map = new HashMap<String, Object>() ;
					map.put("id", b.getResourceId()) ;
					list.add(map) ;
				}
			}
		}
		return list ;
	}

	@Override
	public List<Role> findAll(Role role) {
		List<Role> list = mapper.findAll(role);
		return list;
	}

}
