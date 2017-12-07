package com.kapphk.web.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.system.BeanPublicHelp;
import com.kapphk.web.dao.mapper.system.BeanPublicHelpMapper;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 详情(接口)
 * @author zoneyu 16-8-25
 */
@Service("publicHelpService")
public class PublicHelpServiceImpl extends BaseServiceImpl<BeanPublicHelp, Long> 
				implements PublicHelpService{

	@Autowired
	private BeanPublicHelpMapper mapper ;
	public void init() {
		super.setMapper(mapper) ;
	}

	/**
	 * 查询详情
	 * @author cgj 15-12-29
	 */
	public String findPublicDetail(BeanPublicHelp help) throws Exception {
		String memo = "" ;
		if(!ValidateUtils.isBlank(help.getTitle())){
			List<BeanPublicHelp> list = mapper.findAll(help) ;
			if(!ValidateUtils.isEmptyForCollection(list)){
				memo = list.get(0).getMemo() ;
			}
		}
		return memo ;
	}

	/**
	 * 查询常见问题列表
	 * @author cgj 16-3-20
	 */
	public Result searchList(BeanPublicHelp help,Result rs) throws Exception {
		List<BeanPublicHelp> list = mapper.findAll(help) ;
		Collections.reverse(list) ;
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>() ;
		if(!ValidateUtils.isEmptyForCollection(list)){
			for(BeanPublicHelp p : list){
				Map<String,Object> m = new HashMap<String, Object>() ;
				m.put("id", p.getId()) ;
				m.put("title", p.getTitle()) ;
				dataList.add(m) ;
			}
		}
		rs.put("info", dataList) ;
		return rs ;
	}

	/**
	 * 动能社区介绍
	 * @author exuan 16-10-31
	 */
	public Result searchDNList(BeanPublicHelp bean, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(bean.getMsgType())){
			List<BeanPublicHelp> list = mapper.findAll(bean) ;
			List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>() ;
			if(!ValidateUtils.isEmptyForCollection(list)){
				Collections.reverse(list);
				for(BeanPublicHelp help : list){
					Map<String,Object> map = new HashMap<String, Object>() ;
					map.put("title", help.getTitle()) ;
					dataList.add(map) ;
				}
			}
			rs.put("info", dataList) ;
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}
}
