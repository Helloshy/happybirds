package com.kapphk.web.service.inter.impl.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.tag.BeanUserTag;
import com.kapphk.web.dao.mapper.tag.BeanUserTagMapper;
import com.kapphk.web.service.BaseServiceImpl;
import com.kapphk.web.service.inter.imethod.user.InterfaceBeanUserTagService;
import com.kapphk.web.utils.Result;

/**
 * 用户标签业务层(接口)
 * @author zoneyu 16-9-30
 */
@Service("interfaceBeanUserTagService")
public class InterfaceBeanUserTagServiceImpl extends BaseServiceImpl<BeanUserTag, String> implements
		InterfaceBeanUserTagService {

	@Autowired
	private BeanUserTagMapper mapper ;
	
	public void init() {
		super.setMapper(mapper) ;
	}

	/**
	 * 标签集合
	 * @author zoneyu 16-9-30
	 */
	public Result getTagList(String tagType,Result rs) throws Exception {
		List<Map<String,Object>> list = mapper.getTagList(tagType) ;
		rs.put("info", list) ;
		return rs ;
	}
	
}
