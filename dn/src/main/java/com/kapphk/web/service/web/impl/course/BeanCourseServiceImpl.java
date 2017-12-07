package com.kapphk.web.service.web.impl.course;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.course.BeanCourse;
import com.kapphk.web.dao.mapper.course.BeanCourseMapper;
import com.kapphk.web.service.web.imethod.course.BeanCourseService;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
@Service
public class BeanCourseServiceImpl implements BeanCourseService {

	@Autowired
	private BeanCourseMapper mapper;
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, BeanCourse bean, GridReq gridReq) throws Exception {
		DataUtils.trim(bean);
		/*List<Map<String,Object>> list = mapper.findList(bean,gridReq.getPage(),gridReq.getRows());
		int count = mapper.findCount();
		rs = DataUtils.mergeData(count, list, rs);*/
		return rs;
	}

	/**
	 * 保存
	 */
	@Override
	public Result saveData(Result rs, BeanCourse bean) throws Exception {
		DataUtils.trim(bean);
		if(ValidateUtils.isBlank(bean.getId())){
			bean.setCreateTime(new Date());
			mapper.insert(bean);
		}else{
			mapper.update(bean);
		}
		return rs;
	}

	/**
	 * 详情
	 */
	@Override
	public Result getData(Result rs, BeanCourse bean) throws Exception {
		rs.put("data", mapper.findById(bean.getId()));
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
	 * 获取课程列表
	 */
	@Override
	public List<Map<String, Object>> searchCourseList(Integer recordType,String type) {
		return mapper.searchCourseList(recordType,type);
	}

}
