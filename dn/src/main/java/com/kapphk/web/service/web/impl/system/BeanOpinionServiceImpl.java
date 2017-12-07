package com.kapphk.web.service.web.impl.system;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.system.BeanOpinion;
import com.kapphk.web.dao.mapper.system.BeanOpinionMapper;
import com.kapphk.web.service.web.imethod.system.BeanOpinionService;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 意见反馈业务层
 * @author cgj 16-3-21
 */
@Service
public class BeanOpinionServiceImpl implements BeanOpinionService {

	@Autowired
	private BeanOpinionMapper mapper ;

	/**
	 * 查询
	 * @author cgj 2016-4-6
	 */
	public Result searchList(BeanOpinion bean, int page, int rows, Result rs)
			throws Exception {
		int count = mapper.findCount(bean);
		List<Map<String,Object>> list = mapper.findList(bean,(page-1)*rows, rows) ;
		return DataUtils.mergeData(count, list, rs) ;
	}

	/**
	 * 保存
	 * @author cgj 2016-4-6
	 */
	public Result saveOpinion(BeanOpinion Opinion, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(Opinion.getId())){
			Opinion.setFeedbackTime(new Date());
			mapper.update(Opinion) ;
		}else{
			mapper.insert(Opinion) ;
		}
		return rs ;
	}

	/**
	 * 修改
	 * @author cgj 2016-4-6
	 */
	public Result editOpinion(Result rs,Long id) throws Exception {
		return rs.put("data", mapper.findDetailById(id));
	}

	/**
	 * 删除
	 * @author cgj 2016-4-6
	 */
	public Result delete(Long id, Result rs) throws Exception {
		mapper.delete(id) ;
		return rs;
	}

}
