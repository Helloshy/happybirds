package com.kapphk.web.service.web.impl.system;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.system.BeanPublicHelp;
import com.kapphk.web.dao.mapper.system.BeanPublicHelpMapper;
import com.kapphk.web.service.web.imethod.system.BeanPublicHelpService;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 详情业务层
 * @author zoneyu 16-3-21
 */
@Service
public class BeanPublicHelpServiceImpl implements BeanPublicHelpService {

	@Autowired
	private BeanPublicHelpMapper mapper ;

	/**
	 * 查询
	 * @author zoneyu 2016-4-4
	 */
	public Result searchList(BeanPublicHelp PublicHelp, int page, int rows, Result rs)
			throws Exception {
		int count = mapper.findByPageCount(PublicHelp) ;
		List<BeanPublicHelp> list = mapper.findByPage(PublicHelp, (page-1)*rows, rows) ;
		return DataUtils.mergeData(count, list, rs) ;
	}

	/**
	 * 保存
	 * @author zoneyu 2016-4-4
	 */
	public Result savePublicHelp(BeanPublicHelp PublicHelp, Result rs) throws Exception {
		PublicHelp.setCreateTime(new Date()) ;
		if(!ValidateUtils.isBlank(PublicHelp.getId())){
			mapper.update(PublicHelp) ;
		}else{
			mapper.insert(PublicHelp) ;
		}
		return rs ;
	}

	/**
	 * 修改
	 * @author zoneyu 2016-4-4
	 */
	public Result editPublicHelp(Result rs,Long id) throws Exception {
		return rs.put("data", mapper.findById(id));
	}

	/**
	 * 删除
	 * @author zoneyu 2016-4-4
	 */
	public Result delete(String ids, Result rs) throws Exception {
		mapper.deletes(DataUtils.string2List(ids)) ;
		return rs;
	}

}
