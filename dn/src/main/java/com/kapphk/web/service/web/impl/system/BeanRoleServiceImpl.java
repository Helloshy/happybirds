package com.kapphk.web.service.web.impl.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.system.BeanRole;
import com.kapphk.web.bean.system.BeanRoleResource;
import com.kapphk.web.dao.mapper.system.BeanResourceMapper;
import com.kapphk.web.dao.mapper.system.BeanRoleMapper;
import com.kapphk.web.dao.mapper.system.BeanRoleResourceMapper;
import com.kapphk.web.service.web.imethod.system.BeanRoleService;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 角色业务层
 * @author dengwen 2016-9-13下午2:23:38
 */
@Service
public class BeanRoleServiceImpl implements BeanRoleService{

	@Autowired
	private BeanRoleMapper mapper;

	@Autowired
	private BeanRoleResourceMapper roleResourceMapper;

	@Autowired
	private BeanResourceMapper resourceMapper;

	/**
	 * 查询
	 */
	public Result getList(BeanRole bean, GridReq grid, Result rs)throws Exception {
		int count = mapper.findByPageCount(bean);
		List<BeanRole> list = mapper.findByPage(bean, grid.getPage(),grid.getRows());
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	/**
	 * 详情
	 */
	public Result getData(Long id, Result rs) throws Exception {
		Map<String, Object> map = mapper.findData(id);
		rs.put("info", map);
		return rs;
	}

	/**
	 * 保存
	 */
	public Result saveData(BeanRole bean, List<Long> resourceId, Result rs)
			throws Exception {
		BeanRole r = new BeanRole();
		r.setRoleName(bean.getRoleName());
		List<BeanRole> list = mapper.findByPage(r, 0, 1);
		List<BeanRoleResource> sourceList = new ArrayList<BeanRoleResource>();
		if (ValidateUtils.isBlank(bean.getId())) {
			if (!ValidateUtils.isEmptyForCollection(list)) {
				rs.setStatus(MSG.ERROR.getStatus());
				rs.setMsg("角色名称已经存在");
				return rs;
			} else {
				bean.setCreateTime(new Date());
				mapper.insert(bean);
			}
		} else {
			if (!ValidateUtils.isEmptyForCollection(list)) {
				BeanRole role = list.get(0);
				if (role.getId() - bean.getId() != 0) {
					rs.setStatus(MSG.ERROR.getStatus());
					rs.setMsg("角色名称已经存在");
					return rs;
				}
			}
			mapper.update(bean);

		}
		// 先删除
		BeanRoleResource s = new BeanRoleResource();
		s.setRoleId(bean.getId());
		roleResourceMapper.deleteByEntity(s);
		// 添加授权
		if (!ValidateUtils.isEmptyForCollection(resourceId)) {
			List<Long> rid = resourceMapper.findResourceAll(resourceMapper
					.findResourceAll(resourceId));
			for (Long id : rid) {
				BeanRoleResource source = new BeanRoleResource();
				source.setResourceId(id);
				source.setRoleId(bean.getId());
				sourceList.add(source);
			}
		}
		if (!ValidateUtils.isEmptyForCollection(sourceList))
			roleResourceMapper.inserts(sourceList);
		return rs;
	}

	/**
	 * 删除
	 */
	public Result delete(List<Long> ids, Result rs) throws Exception {
		if(!ValidateUtils.isEmptyForCollection(ids)){
			rs.put("count", mapper.deletes(ids));		
		}
		return rs;
	}

	/**
	 * 初始化角色
	 */
	public List<Map<String, Object>> searchGrant(List<Map<String, Object>> list)
			throws Exception {
		List<BeanRole> list2 = mapper.all();
		if (!ValidateUtils.isEmptyForCollection(list2)) {
			for (BeanRole r : list2) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", r.getId());
				map.put("name", r.getRoleName());
				list.add(map);
			}
		}
		return list;
	}

}
