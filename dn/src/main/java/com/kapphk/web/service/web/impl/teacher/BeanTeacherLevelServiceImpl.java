package com.kapphk.web.service.web.impl.teacher;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.teacher.BeanTeacherLevel;
import com.kapphk.web.dao.mapper.teacher.BeanTeacherLevelMapper;
import com.kapphk.web.service.web.imethod.teacher.BeanTeacherLevelService;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.ExcelUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
@Service
public class BeanTeacherLevelServiceImpl implements BeanTeacherLevelService {

	@Autowired
	private BeanTeacherLevelMapper mapper;
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, BeanTeacherLevel bean, GridReq gridReq) throws Exception {
		List<BeanTeacherLevel> list = mapper.findByPage(bean,gridReq.getPage(),gridReq.getRows());
		int count = mapper.findByPageCount(bean);
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	/**
	 * 保存
	 */
	@Override
	public Result saveData(Result rs, BeanTeacherLevel bean) throws Exception {
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
	public Result getData(Result rs, BeanTeacherLevel bean) throws Exception {
		rs.put("data", mapper.findById(bean.getId()));
		return rs;
	}

	/**
	 * 删除
	 */
	@Override
	public Result delete(Result rs, List<Long> ids) {
		//rs.put("count", mapper.deletes(ids));
		return rs;
	}

	
	@Override
	public void exportExcel(HttpServletResponse response) {
		String[] particular = {"级别","提成比例","更新时间"};
		String[] property = {"id","rate","createTime"};
		ExcelUtils.downExcelList("讲学提成表单.xlsx", particular, property, mapper.findList(), response);
	}

	/**
	 * 获取级别列表
	 */
	@Override
	public List<Map<String, Object>> searchLevelList(Integer recordType,
			String type) {
		List<Map<String, Object>> list = mapper.findLevelList(recordType);
		if(!ValidateUtils.isBlank(type)){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id", "");
			map.put("text", "全部");
			list.add(0, map);
		}
		return list;
	}

}
