package com.kapphk.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.base.persistence.BaseServiceImpl;
import com.kapphk.system.bean.SysLog;
import com.kapphk.system.mapper.SysLogMapper;
import com.kapphk.system.service.SysLogService;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;



/**
 * 日志业务层
 * @author EXIA
 */
@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLog, Long> implements SysLogService {

	@Autowired
	private SysLogMapper mapper;
	
	@Override
	public void init() {
		super.setMapper(mapper);
	}
	
	/**
	 * 查询
	 */
	public Result getList(SysLog bean, String userName, int page, int rows,
			Result rs) throws Exception {
		int count = mapper.findPageCount(userName) ;
		List<Map<String,Object>> list = mapper.findPageList(userName, (page-1)*rows, rows) ;
		rs = DataUtils.mergeData(count, list, rs) ;
		return rs ;
	}
	
	
	/**
	 * 保存
	 */
	public Result saveData(SysLog bean, Result rs) throws Exception {
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
	 */
	public Result delete(String ids, Result rs) throws Exception {
		List<Long> idss = DataUtils.string2List(ids) ;
		mapper.deletes(idss) ;
		return rs ;
	}

}
