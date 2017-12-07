package com.kapphk.web.service.inter.impl.homepage;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.redis.dao.JedisDao;
import com.kapphk.web.bean.course.BeanCourseCollection;
import com.kapphk.web.bean.homepage.BeanAppNews;
import com.kapphk.web.bean.user.BeanUserCollection;
import com.kapphk.web.dao.mapper.course.BeanCourseMapper;
import com.kapphk.web.dao.mapper.homepage.BeanAppNewsMapper;
import com.kapphk.web.dao.mapper.teacher.BeanTeacherMapper;
import com.kapphk.web.dao.mapper.user.BeanUserCollectionMapper;
import com.kapphk.web.service.BaseServiceImpl;
import com.kapphk.web.service.inter.imethod.homepage.InterfaceBeanAppNewsService;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 资讯业务层(接口)
 * @author zoneyu 16-10-8
 */
@Service("interfaceBeanAppNewsService")
public class InterfaceBeanAppNewsServiceImpl extends BaseServiceImpl<BeanAppNews, Long> implements
		InterfaceBeanAppNewsService {

	@Autowired
	private BeanAppNewsMapper mapper ;
	
	//收藏
	@Autowired
	private BeanUserCollectionMapper userCollectionMapper ;
	
	//课程
	@Autowired
	private BeanCourseMapper courseMapper ;
	
	//陪伴师
	@Autowired
	private BeanTeacherMapper teacherMapper ;
	
	@Resource(name="jedisClient")
	private JedisDao jedisDao ;
	
	public void init() {
		super.setMapper(mapper) ;
	}

	/**
	 * 资讯列表
	 * @author zoneyu 16-10-9
	 */
	public Result getNewsList(String newsTag, Integer page ,Long uid, Integer recordType, Result rs) throws Exception {
		page = ValidateUtils.isBlank(page) ? 1 : page ;
		if(!ValidateUtils.isBlank(recordType)){
			if(3-recordType == 0 && ValidateUtils.isBlank(newsTag)){
				rs.setStatus(MSG.ERROR.getStatus()) ;
				rs.setMsg("参数错误") ;
			}else{
				List<Map<String,Object>> list = mapper.getNewsList(newsTag,uid,recordType,(page-1)*ROWS,ROWS) ;
				rs.put("info", list) ;
			}
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}

	/**
	 * 资讯详情
	 * @author zoneyu 16-10-9
	 */
	public String upNewsDetail(Long id) throws Exception {
		BeanAppNews bean = mapper.findById(id);
		bean.setCounts(bean.getCounts()+1);
		mapper.update(bean);
		String memo = "" ;
		if(!ValidateUtils.isBlank(bean)){
			memo = bean.getContent() ;
		}
		return memo ;
	}

	/**
	 * 资讯功能键
	 * @author zoneyu 16-10-9
	 */
	public Result saveOperater(BeanUserCollection bean,Integer type,Result rs) throws Exception {
		if(!ValidateUtils.isBlank(bean.getUid()) && !ValidateUtils.isBlank(bean.getNewsId())
				&& !ValidateUtils.isBlank(bean.getRecordType()) && !ValidateUtils.isBlank(type)){
			if(type == 1){//收藏
				bean.setDataType(1);
				int count = userCollectionMapper.findByPageCount(bean) ;
				if(count == 0){
					bean.setCreateTime(new Date());
					userCollectionMapper.insert(bean) ;
				}else{
					rs.setStatus(MSG.ERROR.getStatus()) ;
					rs.setMsg("您已经收藏过了") ;
				}
			}else if(type == 0){//取消收藏
				bean.setDataType(1);
				userCollectionMapper.deleteByEntity(bean) ;
			}else if(type == 2){//取消点赞
				bean.setDataType(2);
				userCollectionMapper.deleteByEntity(bean) ;
			}else if(type == 3){//点赞
				bean.setDataType(2);
				int count = userCollectionMapper.findByPageCount(bean) ;
				if(count == 0){
					bean.setCreateTime(new Date());
					userCollectionMapper.insert(bean) ;
				}else{
					rs.setStatus(MSG.ERROR.getStatus()) ;
					rs.setMsg("您已经点过赞了") ;
				}
			}
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}

	/**
	 * 首页资讯、课程推荐、陪伴师推荐
	 * @author zoneyu 16-10-10
	 */
	public Result getHomeData(BigDecimal longitude, BigDecimal latitude, String city, Long uid, Result rs) throws Exception {
		//首页动能资讯
		List<Map<String,Object>> newsList = mapper.findHomeData(uid) ;
		Map<String,Object> dataMap = new HashMap<String, Object>() ;
		dataMap.put("news", newsList) ;
		
		//首课程推荐
		Object obj = jedisDao.hashGetEntity("news", "index") ;
		if(!ValidateUtils.isBlank(obj)){
			dataMap.put("courses", obj) ;
		}else{
			List<Map<String,Object>> courseList = courseMapper.findHomeData() ;
			dataMap.put("courses", courseList) ;
			jedisDao.hashSaveEntity("news", "index", courseList);
		}
		
		//陪伴师推荐
		List<Map<String,Object>> teacherList = teacherMapper.findHomeData(longitude,latitude,city,uid) ;
		dataMap.put("teachers", teacherList) ;
		
		rs.put("info", dataMap) ;
		return rs ;
	}

	 /**
	 * 欧巴资讯
	 * @author zoneyu 16-10-11
	 */
	public Result getList(String recordType, Integer page, Long uid, Result rs) throws Exception {
		page = ValidateUtils.isBlank(page) ? 1 : page ;
		if(!ValidateUtils.isBlank(recordType)){
			List<Map<String,Object>> list = mapper.getList(recordType,uid,(page-1)*ROWS,ROWS) ;
			rs.put("info", list) ;
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}
	
	/**
	 * 获取资讯收藏列表
	 */
	@Override
	public Result getCollectionList(BeanCourseCollection bean, Integer page, Result rs)
			throws Exception {
		if(!ValidateUtils.isBlank(bean.getUid())){
			page = ValidateUtils.isBlank(page) ? 1 : page ;
			List<Map<String,Object>> list = userCollectionMapper.getCollectionList(bean.getUid(),(page-1)*GridReq.ROWS,GridReq.ROWS);
			rs.put("info", list);
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 取消资讯收藏
	 */
	@Override
	public Result deleteCollection(List<Long> ids, Result rs) throws Exception {
		if(!ValidateUtils.isEmptyForCollection(ids)){
			rs.put("count", userCollectionMapper.deletes(ids));
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 公司公告列表
	 */
	@Override
	public Result getNoticeList(BeanAppNews bean, Integer page, Result rs)
			throws Exception {
		if(!ValidateUtils.isBlank(bean.getNewsTag())){
			page = ValidateUtils.isBlank(page) ? 1 : page ;
			rs.put("info", mapper.getNoticeList(bean.getNewsTag(), (page-1)*GridReq.ROWS, GridReq.ROWS));
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 公司公告详情
	 */
	@Override
	public BeanAppNews getNoticeDetail(Long id) {
		return mapper.findById(id);
	}

	/**
	 * 资讯赞赏记录
	 * @author dengwen 16-11-15
	 */
	public Result getRecord(Long id, Long uid, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(id) && !ValidateUtils.isBlank(uid)){
			List<Map<String,Object>> list = mapper.getRecord(id,uid) ;
			rs.put("info", list) ;
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs ;
	}

	/**
	 * 动能集团所有的详情
	 * @author dengwen 16-12-9
	 */
	public String getDN(String title) throws Exception {
		String memo = "" ;
		if(!ValidateUtils.isBlank(title)){
			BeanAppNews bean = new BeanAppNews() ;
			bean.setTitle(title);
			bean.setRecordType(4);
			List<BeanAppNews> list = mapper.findByPage(bean, 0, 1);
			if(!ValidateUtils.isEmptyForCollection(list)) memo = list.get(0).getContent() ;
		}
		return memo ;
	}

	/**
	 * 动能集团所有的详情链接
	 * @author zoneyu 16-12-13
	 */
	public Result getDN(String title, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(title)){
			BeanAppNews bean = new BeanAppNews() ;
			bean.setTitle(title);
			bean.setRecordType(4);
			List<BeanAppNews> list = mapper.findByPage(bean, 0, 1);
			if(!ValidateUtils.isEmptyForCollection(list)){
				bean = list.get(0) ;
				if(!ValidateUtils.isBlank(bean.getLink())){
					rs.put("info", bean.getLink()) ;
				}else{
					rs.put("info", "/news/getLinkInfo.do?id="+bean.getId()) ;
				}
			}
		}
		return rs ;
	}

	/**
	 * 动能集团所有的详情
	 * @author zoneyu 16-12-13
	 */
	public String getLinkInfo(Long id) throws Exception {
		BeanAppNews news = mapper.findById(id) ;
		String memo = "" ;
		if(!ValidateUtils.isBlank(news)){
			memo = news.getContent() ;
		}
		return memo ;
	}

}
