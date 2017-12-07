package com.kapphk.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.base.persistence.BaseServiceImpl;
import com.kapphk.system.bean.Setting;
import com.kapphk.system.mapper.SettingMapper;
import com.kapphk.system.service.SettingService;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;



/**
 * setting业务层
 */
@Service
public class SettingServiceImpl extends BaseServiceImpl<Setting, String> implements SettingService {

	@Autowired
	private SettingMapper mapper;
	
	@Override
	public void init() {
		super.setMapper(mapper);
	}
	
	/**
	 * 查询
	 * @author zoneyu 16-6-3
	 */
	public Result getList(Setting bean, int page, int rows,
			Result rs) throws Exception {
		int count = mapper.findByPageCount(bean) ;
		List<Setting> list = mapper.findByPage(bean, (page-1)*rows, rows) ;
		rs = DataUtils.mergeData(count, list, rs) ;
		return rs ;
	}

	/**
	 * 保存
	 * @author zoneyu 16-6-3
	 */
	public Result saveData(Setting bean, Result rs) throws Exception {
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
		List<String> idss = DataUtils.string2ListString(ids) ;
		mapper.deletes(idss) ;
		return rs ;
	}
	

}
