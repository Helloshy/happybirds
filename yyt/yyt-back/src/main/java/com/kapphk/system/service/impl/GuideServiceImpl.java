package com.kapphk.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.base.persistence.BaseServiceImpl;
import com.kapphk.system.bean.Guide;
import com.kapphk.system.mapper.GuideMapper;
import com.kapphk.system.service.GuideService;

import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 推送消息业务层
 * @author Exia
 */
@Service
public class GuideServiceImpl extends BaseServiceImpl<Guide, Long>
		implements GuideService {

	@Autowired
	private GuideMapper mapper ;
	
	public void init() {
		super.setMapper(mapper) ;
	}

	/**
	 * 查询
	 */
	public Result searchList(Guide guide, int page, int rows, Result rs)
			throws Exception {
		int count = mapper.findByPageCount(guide) ;
		List<Guide> list = mapper.findByPage(guide, (page-1)*rows, rows) ;
		return DataUtils.mergeData(count, list, rs) ;
	}

	/**
	 * 保存
	 */
	public Result saveGuide(Guide guide, Result rs) throws Exception {
		guide.setCreateTime(new Date()) ;
		if(!ValidateUtils.isBlank(guide.getId())){
			mapper.update(guide) ;
		}else{
			mapper.insert(guide) ;
		}
		return rs ;
	}

	/**
	 * 修改
	 */
	public Guide editGuide(Long id) throws Exception {
		return mapper.findById(id);
	}

	/**
	 * 删除
	 */
	public Result delete(String ids, Result rs) throws Exception {
		mapper.deletes(DataUtils.string2List(ids)) ;
		return rs;
	}

	@Override
	public List<Guide> findList(Guide guide) {
		List<Guide> findList = mapper.findList(guide);
		return findList;
	}

}
