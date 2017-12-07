package com.kapphk.web.service.inter.impl.teacher;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.course.BeanCourseCollection;
import com.kapphk.web.bean.tag.BeanUserTag;
import com.kapphk.web.bean.teacher.BeanAccompanyLike;
import com.kapphk.web.bean.teacher.BeanTeacher;
import com.kapphk.web.bean.teacher.BeanTeacherArrange;
import com.kapphk.web.bean.teacher.BeanTeacherCollection;
import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.dao.mapper.course.BeanCourseTeacherMapper;
import com.kapphk.web.dao.mapper.tag.BeanUserTagMapper;
import com.kapphk.web.dao.mapper.teacher.BeanAccompanyLikeMapper;
import com.kapphk.web.dao.mapper.teacher.BeanAppAccompanyMapper;
import com.kapphk.web.dao.mapper.teacher.BeanTeacherArrangeMapper;
import com.kapphk.web.dao.mapper.teacher.BeanTeacherCollectionMapper;
import com.kapphk.web.dao.mapper.teacher.BeanTeacherMapper;
import com.kapphk.web.dao.mapper.user.BeanUserMapper;
import com.kapphk.web.service.inter.imethod.teacher.InterfaceBeanTeacherService;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.web.utils.ValidateUtils;
/**
 * 老师业务层
 * @author dengwen 
 * 2016-10-14下午2:08:20
 */
@Service
public class InterfaceBeanTeacherServiceImpl implements InterfaceBeanTeacherService {

	@Autowired
	private BeanTeacherMapper mapper;
	
	@Autowired
	private BeanTeacherCollectionMapper teacherCollectionMapper;
	
	@Autowired
	private BeanCourseTeacherMapper courseTeacherMapper;
	
	@Autowired
	private BeanUserTagMapper userTagMapper;
	
	@Autowired
	private BeanAppAccompanyMapper accompanyMapper;
	
	@Autowired
	private BeanAccompanyLikeMapper likeMapper;
	
	@Autowired
	private BeanTeacherArrangeMapper arrangeMapper;
	
	@Autowired
	private BeanUserMapper userMapper;
	
	/**
	 * 获取陪伴师收藏列表
	 */
	@Override
	public Result getCollectionList(BeanCourseCollection bean, Integer page, Result rs)
			throws Exception {
		if(!ValidateUtils.isBlank(bean.getUid())){
			page = ValidateUtils.isBlank(page) ? 1 : page ;
			List<Map<String,Object>> list = teacherCollectionMapper.getCollectionList(bean.getUid(),(page-1)*GridReq.ROWS,GridReq.ROWS);
			rs.put("info", list);
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 取消陪伴师收藏
	 */
	@Override
	public Result deleteCollection(List<Long> ids, Result rs) throws Exception {
		if(!ValidateUtils.isEmptyForCollection(ids)){
			rs.put("count", teacherCollectionMapper.deletes(ids));
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 获取动能名师列表
	 */
	@Override
	public Result getTeacherList(String district, String level,
			String itemName, Integer page, Result rs) throws Exception {
		page = ValidateUtils.isBlank(page) ? 1 : page;
		List<Map<String,Object>> list = mapper.findAPPTeacherList(district,level,itemName,(page-1)*GridReq.ROWS,GridReq.ROWS);
		rs.put("info", list);
		return rs;
	}

	/**
	 * 获取老师详情
	 */
	@Override
	public Result getTeacherDetail(Long id, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(id)){
			BeanTeacher teacher = mapper.findById(id);
			teacher.setItemContent(null);
			rs.put("info",teacher);
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 获取动能讲师课程列表
	 */
	@Override
	public Result getCourseList(Long id, Integer page, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(id)){
			page = ValidateUtils.isBlank(page) ? 1 : page;
			rs.put("info", courseTeacherMapper.findCourseList(id,(page-1)*7,7));
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 获取陪伴师列表
	 */
	@Override
	public Result getAccompanyList(Long uid, BigDecimal longitude, BigDecimal latitude, Integer sex, 
			String level, String itemName, String tagValue, String city, Integer page, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(city)){
			page = ValidateUtils.isBlank(page) ? 1 : page;
			if(!ValidateUtils.isBlank(uid)){
				BeanUser user = userMapper.findById(uid);
				longitude = user.getLongitude();
				latitude = user.getLatitude();
			}
			rs.put("info", mapper.findAccompanyList(uid,longitude,latitude,sex,level,itemName,tagValue,city,(page-1)*7,7));
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	@Override
	public Result getConditionList(Result rs) throws Exception {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", "");
		map.put("text", "不限");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("id", "预备级");
		map.put("text", "预备级");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("id", "12");
		map.put("text", "1-2星级");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("id", "34");
		map.put("text", "3-4星级");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("id", "56");
		map.put("text", "5-6星级");
		list.add(map);
		rs.put("levelList", list);
		BeanUserTag bean = new BeanUserTag();
		bean.setTagType("学科特长");
		List<Map<String, Object>> disciplineList = userTagMapper.findTagTypeList(bean);
		map = new HashMap<String, Object>();
		map.put("text", "全部");
		map.put("id", "");
		disciplineList.add(0, map);
		rs.put("disciplineList", disciplineList);
		return rs;
	}

	/**
	 * 获取陪伴师经历列表
	 */
	@Override
	public Result getUndergoList(Long uid, Long tid, Integer page, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(tid)){
			page = ValidateUtils.isBlank(page) ? 1 : page;
			List<Map<String, Object>> list = accompanyMapper.findUndergoList(tid,uid,(page-1)*7,7);
			for (Map<String, Object> map : list) {
				map.put("photos", ValidateUtils.isBlank(map.get("photos")) ? new String[]{} : ((String)map.get("photos")).split(","));
			}
			rs.put("info", list);
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 保存经历点赞
	 */
	@Override
	public Result saveAccompanyLike(BeanAccompanyLike bean, Result rs)
			throws Exception {
		if(!ValidateUtils.isBlank(bean.getAccompanyId()) && !ValidateUtils.isBlank(bean.getUid())){
			List<BeanAccompanyLike> list = likeMapper.findAll(bean);
			if(ValidateUtils.isEmptyForCollection(list)){
				bean.setCreateTime(new Date());
				likeMapper.insert(bean);
			}else{
				rs.setStatus(MSG.ERROR.getStatus());
				rs.setMsg("不可重复点赞");
			}
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 可预约时间
	 */
	@Override
	public Result getTeacherArrangeDetail(Long tid, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(tid)){
			BeanTeacherArrange arrange = new BeanTeacherArrange();
			arrange.setTeacherId(tid);
			List<BeanTeacherArrange> list = arrangeMapper.findAll(arrange);
			if(ValidateUtils.isEmptyForCollection(list)){
				arrange.setState(1);
				rs.put("info", arrange);
			}else{
				rs.put("info", list.get(0));
			}
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 陪伴师详情
	 */
	@Override
	public String getAccompanyDetail(Long id) throws Exception {
		return mapper.findById(id).getItemContent();
	}

	/**
	 * 保存收藏老师
	 */
	@Override
	public Result saveCollection(BeanTeacherCollection bean, String type, Result rs)throws Exception {
		if(!ValidateUtils.isBlank(bean.getUid()) && !ValidateUtils.isBlank(bean.getTeacherId()) && !ValidateUtils.isBlank(type) 
				&& !ValidateUtils.isBlank(bean.getDataType()) && !ValidateUtils.isBlank(bean.getCollectionType())){
			if("1".equals(type)){
				if(ValidateUtils.isEmptyForCollection(teacherCollectionMapper.findAll(bean))){
					bean.setCreateTime(new Date());
					teacherCollectionMapper.insert(bean);
				}else{
					rs.setStatus(MSG.ERROR.getStatus());
					rs.setMsg(bean.getDataType() - 1 == 0 ? "不可重复收藏" : "不可重复点赞");
				}
			}else{
				teacherCollectionMapper.deleteByEntity(bean);
			}
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 经历详情
	 * @author dengwen 2016-10-18下午3:26:57
	 */
	@Override
	public String getUndergoDetail(Long id) {
		return accompanyMapper.findById(id).getRemark();
	}
}
