package com.kapphk.web.service.web.impl.course;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.course.BeanCourseType;
import com.kapphk.web.bean.course.BeanCourseTypeTag;
import com.kapphk.web.dao.mapper.course.BeanCourseTypeMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseTypeTagMapper;
import com.kapphk.web.service.web.imethod.course.BeanCourseTypeService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
@Service
public class BeanCourseTypeServiceImpl implements BeanCourseTypeService {

	@Autowired
	private BeanCourseTypeMapper mapper;
	
	@Autowired
	private BeanCourseTypeTagMapper typeTagMapper;
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, BeanCourseType bean, GridReq gridReq) throws Exception {
		DataUtils.trim(bean);
		List<Map<String,Object>> list = mapper.findList(bean,gridReq.getPage(),gridReq.getRows());
		int count = mapper.findCount(bean);
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	/**
	 * 保存
	 */
	@Override
	public Result saveData(Result rs, BeanCourseType bean,List<String> ctt) throws Exception {
		bean.setCourseTagType("课程标签");
		bean.setCourseGroupType("课程分类");
		bean.setCourseMonthType("课程有效期");
		
		BeanCourseType courseType = new BeanCourseType();
		courseType.setItemName(bean.getItemName());
		List<BeanCourseType> ctList = mapper.findAll(courseType);
		
		Date date = new Date();
		if(ValidateUtils.isBlank(bean.getId())){
			if(ValidateUtils.isEmptyForCollection(ctList)){
				bean.setCreateTime(date);
				mapper.insert(bean);
			}else{
				rs.setStatus(Contents.ERROR);
				rs.setMsg("课程系列名称已存在,请更改");
				return rs;
			}
		}else{
			if(ValidateUtils.isEmptyForCollection(ctList) || ctList.get(0).getId() - bean.getId() == 0){
				mapper.update(bean);
			}else{
				rs.setStatus(Contents.ERROR);
				rs.setMsg("课程系列名称已存在,请更改");
				return rs;
			}
		}
		
		//先删除之前数据在保存
		BeanCourseTypeTag typeTag = new BeanCourseTypeTag();
		typeTag.setCourseTypeId(bean.getId());
		typeTagMapper.deleteByEntity(typeTag);
		List<BeanCourseTypeTag> list = new ArrayList<BeanCourseTypeTag>();
		if(!ValidateUtils.isEmptyForCollection(ctt)){
			for (String str : ctt) {
				BeanCourseTypeTag tt = new BeanCourseTypeTag();
				tt.setCourseTypeId(bean.getId());
				tt.setCreateTime(date);
				tt.setTagIdentity(str);
				tt.setTagType("身份");
				list.add(tt);
			}
		}
		if(!ValidateUtils.isEmptyForCollection(list))typeTagMapper.inserts(list);
		return rs;
	}

	/**
	 * 详情
	 */
	@Override
	public Result getData(Result rs, BeanCourseType bean) throws Exception {
		rs.put("data", mapper.findDetailById(bean.getId()));
		return rs;
	}

	/**
	 * 删除
	 */
	@Override
	public Result delete(Result rs, List<Long> ids) throws Exception {
		rs.put("count", mapper.deletes(ids));
		return rs;
	}

	/**
	 * 获取全部课程系列名称o
	 */
	@Override
	public List<Map<String, Object>> searchCourseTypeList(Integer type) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", "");
		map.put("text", "全部");
		List<Map<String, Object>> list = mapper.searchCourseTypeList();
		if(!ValidateUtils.isBlank(type)){
			list.add(0, map);
		}
		return list;
	}
	
}
