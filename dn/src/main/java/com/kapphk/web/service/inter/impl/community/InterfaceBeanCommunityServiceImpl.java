package com.kapphk.web.service.inter.impl.community;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.community.BeanCommunity;
import com.kapphk.web.bean.community.BeanCommunityStaff;
import com.kapphk.web.bean.course.BeanCourseOrder;
import com.kapphk.web.dao.mapper.community.BeanCommunityMapper;
import com.kapphk.web.dao.mapper.community.BeanCommunityStaffMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseOrderMapper;
import com.kapphk.web.service.BaseServiceImpl;
import com.kapphk.web.service.inter.imethod.community.InterfaceBeanCommunityService;
import com.kapphk.web.utils.DateUtils;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 社区业务层
 * @author zoneyu  16-11-3
 */
@Service("interfaceBeanCommunityService")
public class InterfaceBeanCommunityServiceImpl extends BaseServiceImpl<BeanCommunity, Long>
		implements InterfaceBeanCommunityService{

	@Autowired
	private BeanCommunityMapper mapper;
	
	//社区人员
	@Autowired
	private BeanCommunityStaffMapper communityStaffMapper;
	
	//订单
	@Autowired
	private BeanCourseOrderMapper courseOrderMapper ;

	public void init() {
		super.setMapper(mapper);
	}

	/**
	 * 社区列表
	 * @author zoneyu 16-11-3
	 */
	public Result searchCommunityList(Long uid ,String date, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(uid)){
			Date dateTime = null ;
			if(ValidateUtils.isBlank(date)){
				dateTime = new Date() ;
			}else{
				dateTime = DateUtils.parseDate("yyyy-MM-dd HH:mm:ss", date) ;
			}
			List<Map<String,Object>> list = mapper.searchCommunityList(uid,dateTime) ;
			rs.put("info", list) ;
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}

	/**
	 * 校验是否能进入社区
	 * @author zoneyu 16-11-4
	 */
	public Result checkUserInfo(Long uid, Long communityId, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(uid) && !ValidateUtils.isBlank(communityId)){
			//判断是否为区长
			BeanCommunity bean = new BeanCommunity() ;
			bean.setId(communityId);
			bean.setUid(uid);
			int count = mapper.findByPageCount(bean) ;
			if(count == 0){
				//判断是为服务人员
				BeanCommunityStaff staff = new BeanCommunityStaff() ;
				staff.setCommunityId(communityId);
				staff.setUid(uid);
				int count2 = communityStaffMapper.findByPageCount(staff) ;
				if(count2 == 0){
					//普通人员 ，判断是否满足进入社区条件，即是否购买了系统课程
					Map<String,Object> map = mapper.getCourseInfo() ;
					if(!ValidateUtils.isEmptyForMap(map)){
						Long courseId = (Long) map.get("courseId") ;
						String itemName = (String) map.get("itemName") ;
						BeanCourseOrder order = new BeanCourseOrder() ;
						order.setCourseId(courseId);
						order.setUid(uid);
						order.setIsPay(1);
						int count3 = courseOrderMapper.findByPageCount(order) ;
						if(count3 == 0){
							rs.setStatus(21000) ;
							rs.setMsg("您未购买"+itemName+"课程") ;
							rs.put("info", courseId) ;
						}
					}else{
						rs.setStatus(MSG.ERROR.getStatus()) ;
						rs.setMsg("未设置进入社区需购买的课程") ;
					}
				}
			}
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}

	/**
	 * 保存社区信息
	 * @author zoneyu 16-11-4
	 */
	public Result saveCommunityInfo(BeanCommunity bean, Result rs) throws Exception {
		mapper.update(bean) ;
		return rs ;
	}

	/**
	 * 社区成员
	 * @author zoneyu 16-11-4
	 */
	public Result searchCommunityMembers(BeanCommunity bean, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(bean.getId())){
			Map<String,Object> dataMap = new HashMap<String, Object>() ;
			//区长信息
			Map<String,Object> mainMap = mapper.getMainInfo(bean.getId()) ;
			dataMap.put("main", mainMap) ;
			
			//服务人员
			List<Map<String,Object>> serviceList = mapper.getServiceInfo(bean.getId()) ;
			dataMap.put("services", serviceList) ;

			//普通成员
			List<Map<String,Object>> memberList = mapper.getMemberInfo(bean.getId()) ;
			dataMap.put("member", memberList) ;
			rs.put("info", dataMap) ;
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}

	/**
	 * 未加入的社区
	 * @author zoneyu 16-11-5
	 */
	public Result searchCommunityListByProvince(String province , Long uid , Integer page, Result rs) throws Exception {
		if(ValidateUtils.isBlank(province)){
			province = "广东省" ;
		}
		page = ValidateUtils.isBlank(page) ? PAGE : page ;
		List<Map<String,Object>> list = mapper.searchCommunityListByProvince(province,(page-1)*ROWS,ROWS) ;
		if(!ValidateUtils.isEmptyForCollection(list)){
			for(Map<String,Object> map : list){
				String photoPath1 = (String) map.get("photoPath1") ;
				String photoPath2 = (String) map.get("photoPath2") ;
				String photoPath3 = (String) map.get("photoPath3") ;
				List<String> photoList = new ArrayList<String>() ;
				if(!ValidateUtils.isBlank(photoPath1)) photoList.add(photoPath1) ;
				if(!ValidateUtils.isBlank(photoPath2)) photoList.add(photoPath2) ;
				if(!ValidateUtils.isBlank(photoPath3)) photoList.add(photoPath3) ;
				map.put("logoPaths", photoList) ;
				map.remove("photoPath1") ;
				map.remove("photoPath2") ;
				map.remove("photoPath3") ;
			}
		}
		//普通人员 ，判断是否满足进入社区条件，即是否购买了系统课程
		Map<String,Object> map = mapper.getCourseInfo() ;
		int state = 1 ;
		if(!ValidateUtils.isEmptyForMap(map)){
			Long courseId = (Long) map.get("courseId") ;
			String itemName = (String) map.get("itemName") ;
			BeanCourseOrder order = new BeanCourseOrder() ;
			order.setCourseId(courseId);
			order.setUid(uid);
			order.setIsPay(1);
			int count3 = courseOrderMapper.findByPageCount(order) ;
			if(count3 == 0){
				rs.setMsg("您未购买"+itemName+"课程") ;
				state = 0 ;
			}
			rs.put("courseId", courseId) ;
			rs.put("state", state) ;
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("未设置进入社区需购买的课程") ;
			return rs ;
		}
		rs.put("info", list) ;
		return rs ;
	}

	/**
	 * 社区红点，社区详情
	 * @author zoneyu 16-12-24
	 */
	public Result getPoint(Long communityId, Long uid, String date ,Integer position, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(position)){
			Date dateTime = null ;
			if(ValidateUtils.isBlank(date)){
				dateTime = new Date() ;
			}else{
				dateTime = DateUtils.parseDate("yyyy-MM-dd HH:mm:ss", date) ;
			}
			if(position == 1){//社区
				if(!ValidateUtils.isBlank(uid)){
					rs.put("info", mapper.getCommunityPoint(uid,dateTime)) ;
				}else{
					rs.setStatus(MSG.ERROR.getStatus()) ;
					rs.setMsg("参数错误") ;
				}
			}else if(position == 2){//社区详情
				if(!ValidateUtils.isBlank(communityId)){
					rs.put("info", mapper.getPoint(communityId,dateTime)) ;
				}else{
					rs.setStatus(MSG.ERROR.getStatus()) ;
					rs.setMsg("参数错误") ;
				}
			}
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}

}
