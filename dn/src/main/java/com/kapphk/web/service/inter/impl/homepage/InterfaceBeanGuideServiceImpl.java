package com.kapphk.web.service.inter.impl.homepage;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.redis.dao.JedisDao;
import com.kapphk.web.bean.homepage.BeanGuide;
import com.kapphk.web.dao.mapper.homepage.BeanGuideMapper;
import com.kapphk.web.dao.mapper.tag.BeanUserTagMapper;
import com.kapphk.web.dao.mapper.teacher.BeanTeacherLevelMapper;
import com.kapphk.web.service.BaseServiceImpl;
import com.kapphk.web.service.inter.imethod.homepage.InterfaceBeanGuideService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 轮播图业务层(接口)
 * @author zoneyu 16-10-8
 */
@Service("interfaceBeanGuideService")
public class InterfaceBeanGuideServiceImpl extends BaseServiceImpl<BeanGuide, Long> implements
		InterfaceBeanGuideService {

	@Autowired
	private BeanGuideMapper mapper ;
	
	@Autowired
	private BeanUserTagMapper tagMapper;
	
	@Autowired
	private BeanTeacherLevelMapper levelMapper;
	
	@Resource(name="jedisClient")
	private JedisDao jedisDao ;
	
	public void init() {
		super.setMapper(mapper) ;
	}

	/**
	 * 首页轮播图数据
	 * @author zoneyu 16-10-10
	 */
	public Result getHomeGuideList(Result rs) throws Exception {
		Object obj = jedisDao.hashGetEntity("banner", "index") ;
		if(!ValidateUtils.isBlank(obj)){
			rs.put("info", obj) ;
		}else{
			List<Map<String,Object>> list = mapper.getHomeGuideList() ;
			if(!ValidateUtils.isEmptyForCollection(list)){
				for(Map<String,Object> map : list){
					String itemLink = (String) map.get("itemLink") ;
					Long id = (Long) map.get("id") ;
					if(ValidateUtils.isBlank(itemLink)){
						String url = "/guide/getGuideDetail.do?id="+id ;
						map.put("url", url) ;
					}else{
						map.put("url", "") ;
					}
				}
				jedisDao.hashSaveEntity("banner", "index", list);
			}
			rs.put("info", list) ;
		}
		return rs ;
	}

	/**
	 * 轮播图详情
	 * @author zoneyu 16-10-10
	 */
	public String getGuideDetail(Long id) throws Exception {
		return mapper.findById(id).getContent();
	}

	/**O
	 * 动能名师轮播图数据
	 */
	@Override
	public Result getTeacherGuideList(BeanGuide bean, Result rs)
			throws Exception {
		if(!ValidateUtils.isBlank(bean.getRecordType())){
			List<BeanGuide> guideList = null ;
			if(bean.getRecordType() == 3){//动能集团
				guideList = mapper.findAllSort(bean) ;
			}else{
				guideList = mapper.findAll(bean);
			}
			if(!ValidateUtils.isEmptyForCollection(guideList)){
				for(BeanGuide guide : guideList){
					String itemLink = guide.getItemLink() ;
					if(ValidateUtils.isBlank(itemLink)){
						guide.setContent("/guide/getGuideDetail.do?id="+guide.getId());
					}else{
						guide.setContent("");
					}
				}
			}
			rs.put("info", guideList);
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

	@Override
	public Result getDistrictList(Result rs) throws Exception {
		List<String> districtList = tagMapper.findTagIdList("区域管理");
		List<String> levelList = levelMapper.findTagIdList(1);
		districtList.add(0, "全部");
		levelList.add(0, "全部");
		rs.put("districtList", districtList);
		rs.put("levelList", levelList);
		return rs;
	}

}
