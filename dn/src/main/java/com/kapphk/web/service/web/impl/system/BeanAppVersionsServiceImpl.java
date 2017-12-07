package com.kapphk.web.service.web.impl.system;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.system.BeanaAppVersions;
import com.kapphk.web.dao.mapper.system.BeanaAppVersionsMapper;
import com.kapphk.web.service.web.imethod.system.BeanAppVersionsService;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

@Service
public class BeanAppVersionsServiceImpl implements BeanAppVersionsService {

	@Autowired
	private BeanaAppVersionsMapper mapper;
	
	@Override
	public Result searchList(int page, int rows, Result rs) throws Exception {
		int count = mapper.findByPageCount(new BeanaAppVersions()) ;
		List<BeanaAppVersions> list = mapper.findByPage(new BeanaAppVersions(), (page-1)*rows, rows) ;
		return DataUtils.mergeData(count, list, rs) ;
	}

	@Override
	public Result saveData(BeanaAppVersions bean, Result rs) throws Exception {
		bean.setCreateTime(new Date());
		mapper.insert(bean);
		return rs;
	}

	@Override
	public Result delete(Result rs, List<Long> ids) throws Exception {
		if(!ValidateUtils.isEmptyForCollection(ids))
			rs.put("count", mapper.deletes(ids));
		return rs;
	}

}
