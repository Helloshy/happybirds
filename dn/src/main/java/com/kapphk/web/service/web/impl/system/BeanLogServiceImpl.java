package com.kapphk.web.service.web.impl.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.system.BeanLog;
import com.kapphk.web.dao.mapper.system.BeanLogMapper;
import com.kapphk.web.service.web.imethod.system.BeanLogService;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;

/**
 * 日志业务层
 * @author zoneyu 16-6-12
 */
@Service
public class BeanLogServiceImpl implements BeanLogService {

	@Autowired
	private BeanLogMapper mapper;
	
	/**
	 * 查询
	 * @author zoneyu 16-6-12
	 */
	public Result getList(BeanLog bean,GridReq gridReq, Result rs) throws Exception {
		int count = mapper.findByPageCount(bean);
		List<BeanLog> list = mapper.findByPage(bean, gridReq.getPage(), gridReq.getRows());
		rs = DataUtils.mergeData(count, list, rs) ;
		return rs ;
	}

	/**
	 * 删除
	 */
	@Override
	public Result delete(List<Long> asList, Result rs) throws Exception {
		return rs.put("count", mapper.deletes(asList));
	}

}
