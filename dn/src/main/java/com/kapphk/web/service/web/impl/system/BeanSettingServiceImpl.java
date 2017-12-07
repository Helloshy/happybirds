package com.kapphk.web.service.web.impl.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.course.BeanCourse;
import com.kapphk.web.bean.system.BeanSetting;
import com.kapphk.web.dao.mapper.course.BeanCourseMapper;
import com.kapphk.web.dao.mapper.system.BeanSettingMapper;
import com.kapphk.web.service.web.imethod.system.BeanSettingService;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
@Service
public class BeanSettingServiceImpl implements BeanSettingService {

	@Autowired
	private BeanSettingMapper mapper;
	
	@Autowired
	private BeanCourseMapper courseMapper;
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, BeanSetting bean, GridReq gridReq) throws Exception {
		if("manager_commission".equals(bean.getItemType())){
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", "area_general_manager");
			map.put("itemName", "区域总经理");
			map.put("value", mapper.findById("area_general_manager").getValue());
			map.put("value1", mapper.findById("area_general_manager_indirect").getValue());
			map.put("createTime", mapper.findById("area_general_manager").getCreateTime());
			list.add(map);
			map = new HashMap<String,Object>();
			map.put("id", "area_manager");
			map.put("itemName", "区域经理");
			map.put("value", mapper.findById("area_manager").getValue());
			map.put("value1", mapper.findById("area_manager_indirect").getValue());
			map.put("createTime", mapper.findById("area_manager").getCreateTime());
			list.add(map);
			rs = DataUtils.mergeData(2, list, rs);
		}else if("community_condition".equals(bean.getItemType())){
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			BeanCourse course = courseMapper.findById(Long.valueOf(mapper.findById("community_condition").getValue()));
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", "community_condition");
			map.put("remark", "加入社区需要购买的系统课程设置");
			map.put("itemName", course.getItemName());
			map.put("amount", course.getAmount());
			map.put("createTime", mapper.findById("community_condition").getCreateTime());
			list.add(map);
			rs = DataUtils.mergeData(1, list, rs);
		}else{
			List<BeanSetting> list = mapper.findByPage(bean, gridReq.getPage(),gridReq.getRows());
			int count = mapper.findByPageCount(bean);
			rs = DataUtils.mergeData(count, list, rs);
		}
		return rs;
	}

	/**
	 * 保存
	 */
	@Override
	public Result saveData(Result rs, BeanSetting bean) throws Exception {
		bean.setCreateTime(new Date());
		if(ValidateUtils.isBlank(bean.getId())){
			mapper.insert(bean);
		}else{
			BeanSetting setting = new BeanSetting();
			if("area_general_manager".equals(bean.getId())){
				setting.setId("area_general_manager_indirect");
				setting.setValue(bean.getValue1());
				mapper.update(setting);
			}else if("area_manager".equals(bean.getId())){
				setting.setId("area_manager_indirect");
				setting.setValue(bean.getValue1());
				mapper.update(setting);
			}
			mapper.update(bean);
		}
		return rs;
	}

	/**
	 * 详情
	 */
	@Override
	public Result getData(Result rs, BeanSetting bean) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		if("area_general_manager".equals(bean.getId())){
			map.put("id", "area_general_manager");
			map.put("itemName", "区域总经理");
			map.put("value", mapper.findById("area_general_manager").getValue());
			map.put("value1", mapper.findById("area_general_manager_indirect").getValue());
			rs.put("data", map);
		}else if("area_manager".equals(bean.getId())){
			map.put("id", "area_manager");
			map.put("itemName", "区域经理");
			map.put("value", mapper.findById("area_manager").getValue());
			map.put("value1", mapper.findById("area_manager_indirect").getValue());
			rs.put("data", map);
		}else if("community_condition".equals(bean.getId())){
			BeanCourse course = courseMapper.findById(Long.valueOf(mapper.findById("community_condition").getValue()));
			map.put("id", "community_condition");
			map.put("remark", "加入社区需要购买的系统课程设置");
			map.put("value", mapper.findById("community_condition").getValue());
			map.put("amount", course.getAmount());
			rs.put("data", map);
		}else{
			rs.put("data", mapper.findById(bean.getId()));
		}
		return rs;
	}

	/**
	 * 删除
	 */
	@Override
	public Result delete(Result rs, List<Long> ids) throws Exception {
		return rs;
	}

}
