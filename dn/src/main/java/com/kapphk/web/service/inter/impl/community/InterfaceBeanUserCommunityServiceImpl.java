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
import com.kapphk.web.bean.community.BeanUserCommunity;
import com.kapphk.web.bean.course.BeanCourseOrder;
import com.kapphk.web.dao.mapper.community.BeanCommunityMapper;
import com.kapphk.web.dao.mapper.community.BeanCommunityStaffMapper;
import com.kapphk.web.dao.mapper.community.BeanUserCommunityMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseOrderMapper;
import com.kapphk.web.service.BaseServiceImpl;
import com.kapphk.web.service.inter.imethod.community.InterfaceBeanUserCommunityService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 社区信息业务层
 * @author zoneyu  2016-11-2
 */
@Service("interfaceBeanUserCommunityService")
public class InterfaceBeanUserCommunityServiceImpl extends BaseServiceImpl<BeanUserCommunity, Long>
		implements InterfaceBeanUserCommunityService{

	@Autowired
	private BeanUserCommunityMapper mapper;
	
	//社区
	@Autowired
	private BeanCommunityMapper communityMapper;
	
	//社区
	@Autowired
	private BeanCourseOrderMapper courseOrderMapper ;
	
	//社区服务人员
	@Autowired
	private BeanCommunityStaffMapper communityStaffMapper;

	public void init() {
		super.setMapper(mapper);
	}
	
	/**
	 * 社区信息
	 * @author zoneyu 16-11-2
	 */
	public Result searchCommunityInfo(Long id, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(id)){
			Map<String,Object> map = communityMapper.searchCommunityInfo(id) ;
			if(!ValidateUtils.isEmptyForMap(map)){
				List<String> photoList = new ArrayList<String>() ;
				String photoPath1 = (String) map.get("photoPath1") ;
				String photoPath2 = (String) map.get("photoPath2") ;
				String photoPath3 = (String) map.get("photoPath3") ;
				if(!ValidateUtils.isBlank(photoPath1)) photoList.add(photoPath1) ;
				if(!ValidateUtils.isBlank(photoPath2)) photoList.add(photoPath2) ;
				if(!ValidateUtils.isBlank(photoPath3)) photoList.add(photoPath3) ;
				map.put("photos", photoList) ;
				map.remove("photoPath1") ;
				map.remove("photoPath2") ;
				map.remove("photoPath3") ;
			}
			rs.put("info", map) ;
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}

	/**
	 * 社区公告、动态
	 * @author zoneyu 16-11-2
	 */
	public Result searchNoticeOrDynamic(Long uid, Long id, Integer type, Integer page, Result rs) throws Exception {
		page = ValidateUtils.isBlank(page) ? PAGE : page ;
		if(!ValidateUtils.isBlank(id) && !ValidateUtils.isBlank(type)){
			List<Map<String,Object>> list = mapper.searchNoticeOrDynamic(uid,id,type,(page-1)*ROWS,ROWS) ;
			if(!ValidateUtils.isEmptyForCollection(list)){
				for(Map<String,Object> map : list){
					String logoPath = (String) map.get("logoPath") ;
					List<String> photoList = new ArrayList<String>() ;
					if(!ValidateUtils.isBlank(logoPath)){
						String[] photos = logoPath.split(",") ;
						for(String s : photos){
							photoList.add(s) ;
						}
					}
					map.put("logoPath", photoList) ;
				}
			}
			rs.put("info", list) ;
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}

	/**
	 * 社区个数
	 * @author zoneyu 16-11-2
	 */
	public Result getCommunityCounts(Long uid, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(uid)){
			Map<String,Object> map = new HashMap<String, Object>() ;
			int count = 0 ;
			Object id = -1 ;
			BeanCommunity bean = new BeanCommunity() ;
			bean.setUid(uid);
			List<BeanCommunity> communityList = communityMapper.findAll(bean) ;
			if(!ValidateUtils.isEmptyForCollection(communityList)){//不为空,说明是区长身份
				count = communityList.size() ;
				if(count == 1){
					id = communityList.get(0).getId() ;
				}else{
					id = null ;
				}
			}else{//服务人员或普通人员
				//查询是否为服务人员
				BeanCommunityStaff staff = new BeanCommunityStaff() ;
				staff.setUid(uid);
				List<BeanCommunityStaff> staffList = communityStaffMapper.findAll(staff) ;
				if(!ValidateUtils.isEmptyForCollection(staffList)){//说明是服务人员
					count = staffList.size() ;
					if(count == 1){
						id = staffList.get(0).getCommunityId() ;
					}
				}else{//普通人员
					Map<String,Object> mapInfo = communityMapper.getCourseInfo() ;
					if(!ValidateUtils.isEmptyForMap(mapInfo)){
						Long courseId = (Long) mapInfo.get("courseId") ;
						String itemName = (String) mapInfo.get("itemName") ;
						BeanCourseOrder order = new BeanCourseOrder() ;
						order.setCourseId(courseId);
						order.setUid(uid);
						order.setIsPay(1);
						int count3 = courseOrderMapper.findByPageCount(order) ;
						if(count3 == 0){
							map.put("courseId", courseId) ;
							rs.setStatus(21000) ;
							rs.setMsg("加入社区，您需要先购买《"+itemName+"》课程") ;
						}else{
							BeanUserCommunity uc = new BeanUserCommunity() ;
							uc.setUid(uid); ;
							List<BeanUserCommunity> list = mapper.findAll(uc) ;
							if(!ValidateUtils.isEmptyForCollection(list)){
								count = list.size() ;
								if(count == 1){
									id = list.get(0).getCommunityId() ;
								}
							}
							map.put("courseId", -1) ;
						}
					}else{
						rs.setStatus(MSG.ERROR.getStatus()) ;
						rs.setMsg("未设置进入社区需购买的课程") ;
					}
				}
			}
			map.put("counts", count) ;
			map.put("id", id) ;
			rs.put("info", map) ;
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}

	/**
	 * 加入社区
	 * @author zoneyu 16-11-5
	 */
	public Result saveUserCommunity(BeanUserCommunity bean, Result rs) throws Exception {
		Long uid = bean.getUid() ;
		Long communityId = bean.getCommunityId() ;
		if(!ValidateUtils.isBlank(communityId) && !ValidateUtils.isBlank(uid)){
			BeanCommunity bean2 = new BeanCommunity() ;
			bean2.setId(communityId);
			bean2.setUid(uid);
			int count = communityMapper.findByPageCount(bean2) ;
			if(count == 0){
				//判断是为服务人员
				BeanCommunityStaff staff = new BeanCommunityStaff() ;
				staff.setCommunityId(communityId);
				staff.setUid(uid);
				int count2 = communityStaffMapper.findByPageCount(staff) ;
				if(count2 == 0){
					//普通人员 ，判断是否满足进入社区条件，即是否购买了系统课程
					Map<String,Object> map = communityMapper.getCourseInfo() ;
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
							rs.setMsg("加入社区，您需要先购买《"+itemName+"》课程") ;
							rs.put("info", courseId) ;
						}else{
							//判断人数是否达上限500
							BeanUserCommunity b = new BeanUserCommunity() ;
							b.setCommunityId(bean.getCommunityId());
							int count4 = mapper.findByPageCount(b) ;
							if(count4 < 500){
								int count5 = mapper.findByPageCount(bean) ;
								if(count5 == 0){
									bean.setCreateTime(new Date());
									bean.setRecordType(2);//普通人员
									mapper.insert(bean) ;
								}else{
									rs.setStatus(MSG.ERROR.getStatus()) ;
									rs.setMsg("您已加入了该社区") ;
								}
							}else{
								rs.setStatus(MSG.ERROR.getStatus()) ;
								rs.setMsg("社区人数已达上限") ;
							}
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

}
