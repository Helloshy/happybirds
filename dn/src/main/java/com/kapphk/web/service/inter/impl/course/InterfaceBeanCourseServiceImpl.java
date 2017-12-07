package com.kapphk.web.service.inter.impl.course;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.redis.dao.JedisDao;
import com.kapphk.web.bean.course.BeanAppJoin;
import com.kapphk.web.bean.course.BeanCourse;
import com.kapphk.web.bean.course.BeanCourseCollection;
import com.kapphk.web.bean.course.BeanCourseOffline;
import com.kapphk.web.bean.course.BeanCourseOnline;
import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.dao.mapper.course.BeanAppJoinMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseCollectionMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseOfflineMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseOnlineMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseTypeMapper;
import com.kapphk.web.dao.mapper.course.BeanUserCouponMapper;
import com.kapphk.web.dao.mapper.user.BeanUserMapper;
import com.kapphk.web.service.BaseServiceImpl;
import com.kapphk.web.service.inter.imethod.course.InterfaceBeanCourseService;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.web.utils.ValidateUtils;


/**
 * 课程业务层(接口)
 * @author zoneyu 16-10-11
 */
@Service("interfaceBeanCourseService")
public class InterfaceBeanCourseServiceImpl extends BaseServiceImpl<BeanCourse, Long> implements
		InterfaceBeanCourseService {

	@Autowired
	private BeanCourseMapper mapper ;
	
	//课程收藏
	@Autowired
	private BeanCourseCollectionMapper courseCollectionMapper ;
	
	//线下课程
	@Autowired
	private BeanCourseOfflineMapper courseOfflineMapper ;
	
	//网络课程
	@Autowired
	private BeanCourseOnlineMapper courseOnlineMapper ;
	
	//抵扣券
	@Autowired
	private BeanUserCouponMapper userCouponMapper ;
	
	//用户
	@Autowired
	private BeanUserMapper userMapper ;
	
	//加盟申请
	@Autowired
	private BeanAppJoinMapper joinMapper ;
	
	@Autowired
	private BeanCourseTypeMapper courseTypeMapper;
	
	//redis缓存dao
	@Resource(name="jedisClient")
	private JedisDao jedisDao ;
	
	public void init() {
		super.setMapper(mapper) ;
	}

	/**
	 * 课程列表
	 * @author zoneyu 16-10-11
	 */
	public Result getCourseModuleList(Integer courseType, String courseGroup, Integer isPublic,
			Integer recordType, String name, Long uid, Integer page, Result rs) throws Exception {
		page = ValidateUtils.isBlank(page) ? 1 : page ;
		if(ValidateUtils.isBlank(recordType) && ValidateUtils.isBlank(name)){
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}else{
			List<Map<String,Object>> list = null ;
			if(!ValidateUtils.isBlank(uid)){
				BeanUser user = userMapper.findById(uid) ;
				Integer isPermissions = user.getIsPermissions() ;
				rs.put("isPermissions", isPermissions) ;
			}else{
				rs.put("isPermissions", 0) ;
			}
			if(recordType == 1){//线下课程
				list = mapper.getOfflineList(uid,courseGroup,isPublic,name,(page-1)*ROWS,ROWS) ;
				rs.put("offlineInfo", list);
			}else if(recordType == 2){//网络课程
				list = mapper.getOnlineList(courseType,courseGroup,isPublic,name,(page-1)*ROWS,ROWS) ;
				rs.put("onlineInfo", list);
			}else if(recordType == 3){//系统课程
				list = mapper.getSystemList(name,(page-1)*ROWS,ROWS) ;
				rs.put("systemInfo", list);
			}
			
		}
		return rs ;
	}

	/**
	 * 课程详情
	 * @author zoneyu 16-10-12
	 */
	public Result getCourseDetail(String typeName, Long uid, Long courseId, Result rs) throws Exception {
		if(ValidateUtils.isBlank(typeName) && ValidateUtils.isBlank(courseId)){
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}else{
			Map<String,Object> dataMap = new HashMap<String, Object>() ;
			
			//课程详情
			Map<String,Object> courseMap = mapper.getCourseInfo(courseId,uid) ;
			if(!ValidateUtils.isBlank(uid)){
				BeanUser user = userMapper.findById(uid) ;
				courseMap.put("position", user.getPosition()) ;
			}else{
				courseMap.put("position", "") ;
			}
			dataMap.put("courses", courseMap) ;
			
			//主讲老师
			List<Map<String,Object>> teacherList = mapper.getTeachersList(courseId) ;
			dataMap.put("teachers", teacherList) ;
			
			//报名联系人
			List<Map<String,Object>> connectList = mapper.getConnectList(courseId) ;
			dataMap.put("connects", connectList) ;
			
			//推荐课程
			List<Map<String,Object>> pushList = mapper.getPushInfo(courseId) ;
			dataMap.put("pushes", pushList) ;
			rs.put("info", dataMap) ;
		}
		return rs ;
	}

	/**
	 * 课程收藏
	 * @author zoneyu 16-10-12
	 */
	public Result saveCollection(BeanCourseCollection bean, Integer type, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(bean.getCourseId()) && !ValidateUtils.isBlank(bean.getUid()) 
				&& !ValidateUtils.isBlank(type)){
			int count = courseCollectionMapper.findByPageCount(bean) ;
			if(type == 0){//取消收藏
				courseCollectionMapper.deleteByEntity(bean) ;
			}else if(type == 1){//收藏
				if(count == 0){
					bean.setCollectionType(1);
					bean.setCreateTime(new Date());
					courseCollectionMapper.insert(bean) ;
				}else{
					rs.setStatus(MSG.ERROR.getStatus()) ;
					rs.setMsg("您已经收藏过了") ;
				}
			}
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}

	/**
	 * 课程介绍&报名须知
	 * @author zoneyu 16-10-12
	 */
	public String getIntroduction(Long id,Integer typeName) throws Exception {
		BeanCourseOffline bean = courseOfflineMapper.findById(id) ;
		String memo = "" ;
		if(!ValidateUtils.isBlank(bean)){
			if(typeName == 1){
				memo = bean.getCourseContent() ;
			}else if(typeName == 2){
				memo = bean.getCourseRemark() ;
			}
		}
		return memo ;
	}

	/**
	 * 课程抵扣券
	 * @author zoneyu 16-10-13
	 */
	public Result getUnUseCoupon(Long courseTypeId, Long uid, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(courseTypeId) && !ValidateUtils.isBlank(uid)){
			List<Map<String,Object>> list = userCouponMapper.getUnUseCoupon(courseTypeId,uid) ;
			rs.put("info", list) ;
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}

	/**
	 * 获取课程收藏列表
	 */
	@Override
	public Result getCollectionList(BeanCourseCollection bean, Integer page, Result rs)
			throws Exception {
		if(!ValidateUtils.isBlank(bean.getUid())){
			page = ValidateUtils.isBlank(page) ? 1 : page ;
			List<Map<String,Object>> list = courseCollectionMapper.getCollectionList(bean.getUid(),(page-1)*GridReq.ROWS,GridReq.ROWS);
			for (Map<String, Object> map : list) {
				Integer isPublic = (Integer) map.get("isPublic");
				map.put("amount", isPublic - 1 == 0 ? "公益课" : "￥"+map.get("amount"));
			}
			rs.put("info", list);
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 取消课程收藏
	 */
	@Override
	public Result deleteCollection(List<Long> ids, Result rs) throws Exception {
		if(!ValidateUtils.isEmptyForCollection(ids)){
			rs.put("count", courseCollectionMapper.deletes(ids));
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 线下课程详情
	 * @author zoneyu 16-10-20
	 */
	public Result doGetOnlineCourseDetail(Long courseId, Long uid, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(courseId)){
			Map<String,Object> map = mapper.getOnlineCourseDetail(courseId,uid) ;
			if(!ValidateUtils.isEmptyForMap(map)){
				Object plys = map.get("vedioPalys") ;
				Integer vedioPlays = ValidateUtils.isBlank(plys) ? 1 : ((Integer.valueOf(String.valueOf(plys))+1)) ;
				map.put("vedioPalys",vedioPlays);
				map.put("introductionLink", "/course/getOnlineCourseIntroduction.do?courseId="+courseId) ;
			}else{
				map = new HashMap<String, Object>() ;
			}
			//播放次数加一
			BeanCourseOnline course = courseOnlineMapper.findById(courseId) ;
			Integer plays = ValidateUtils.isBlank(course.getVedioPalys()) ? 0 : course.getVedioPalys() ;
			course.setVedioPalys(plays+1);
			courseOnlineMapper.update(course) ;
			rs.put("info", map) ;
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 线下课程简介
	 * @author zoneyu 16-10-20
	 */
	public String getOnlineCourseIntroduction(Long courseId) throws Exception {
		return courseOnlineMapper.findById(courseId).getCourseContent();
	}

	/**
	 * 动能财商
	 * @author zoneyu 16-10-21
	 */
	public Result getFinancialList(Integer type, Integer page, Result rs) throws Exception {
		page = ValidateUtils.isBlank(page) ? 1 : page ;
		List<Map<String,Object>> list = null ;
		if(!ValidateUtils.isBlank(type)){
			if(type == 1){//线下课程
				list = mapper.getFinancialListOffline((page-1)*ROWS,ROWS) ;
			}else if(type == 2){//网络课程
				list = mapper.getFinancialListOnline((page-1)*ROWS,ROWS) ;
			}else if(type == 3){//系统课程
				list = mapper.getFinancialListSystem((page-1)*ROWS,ROWS) ;
			}
			rs.put("info", list) ;
		}
		return rs ;
	}

	/**
	 * 系统课程详情
	 * @author zoneyu 16-10-21
	 */
	public Result getSystemCourseDetail(Long courseId, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(courseId)){
			Map<String,Object> dataMap = new HashMap<String, Object>() ;
			//系统课程详情
			Map<String,Object> map = mapper.getSystemCourseDetail(courseId) ;
			dataMap.put("courseInfo", map) ;
			
			//系统课程内容
			List<Map<String,Object>> infolist = mapper.getSystemCourseList(courseId) ;
			dataMap.put("infoList", infolist) ;
			
			//报名联系人
			List<Map<String,Object>> contactList = mapper.getContactList(courseId) ;
			dataMap.put("contactList", contactList) ;
			
			rs.put("info", dataMap) ;
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 加盟申请
	 * @author zoneyu 16-10-22
	 */
	public Result saveJoin(BeanAppJoin bean, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(bean.getNickname()) && !ValidateUtils.isBlank(bean.getPhone())){
			bean.setCreateTime(new Date());
			joinMapper.insert(bean) ;
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 更新播放次数
	 */
	@Override
	public Result saveVedioPalys(Long id, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(id)){
			BeanCourseOnline online = courseOnlineMapper.findById(id);
			online.setVedioPalys(online.getVedioPalys()+1);
			courseOnlineMapper.update(online);
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	@Override
	public Map<String, Object> shareCourseDetail(Long courseId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		BeanCourse course = mapper.findById(courseId);
		BeanCourseOffline offline = courseOfflineMapper.findById(courseId);
		map.put("logoPath", course.getLogoPath());
		map.put("itemName", course.getItemName());
		map.put("amount", course.getIsPublic() - 1 == 0 ? "公益课" : course.getAmount().intValue());
		map.put("registerDate", offline.getRegisterDate());
		map.put("address", offline.getProvince()+offline.getCity()+offline.getDistrict()+offline.getAddress());
		map.put("courseGroup", courseTypeMapper.findById(offline.getCourseTypeId()).getCourseGroup());
		map.put("id", offline.getId());
		//课程详情
		dataMap.put("courses", map);
		
		//主讲老师
		dataMap.put("teachers", mapper.getTeachersList(courseId)) ;
		
		//报名联系人
		dataMap.put("connects", mapper.getConnectList(courseId)) ;
		
		//推荐课程
		dataMap.put("pushes", mapper.getPushInfo(courseId)) ;
		return dataMap;
	}

}
