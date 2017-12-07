package com.kapphk.web.service.web.impl.teacher;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.teacher.BeanAppAccompany;
import com.kapphk.web.dao.mapper.teacher.BeanAppAccompanyMapper;
import com.kapphk.web.dao.mapper.teacher.BeanTeacherMapper;
import com.kapphk.web.dao.mapper.user.BeanUserMapper;
import com.kapphk.web.service.web.imethod.teacher.BeanAppAccompanyService;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
@Service
public class BeanAppAccompanyServiceImpl implements BeanAppAccompanyService {

	@Autowired
	private BeanAppAccompanyMapper mapper;
	
	@Autowired
	private BeanTeacherMapper teacherMapper;
	
	@Autowired
	private BeanUserMapper userMapper;
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, Long teacherId, GridReq gridReq) throws Exception {
		List<Map<String,Object>> list = mapper.findList(teacherId,gridReq.getPage(),gridReq.getRows());
		int count = mapper.findCount(teacherId);
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	/**
	 * 保存
	 */
	@Override
	public Result saveData(Result rs, BeanAppAccompany bean, String[] imgs) throws Exception {
		if(!ValidateUtils.isempty(imgs)){
			if(imgs.length == 3){
				bean.setLogoPath3(imgs[2]);
				bean.setLogoPath2(imgs[1]);
				bean.setLogoPath(imgs[0]);
			}else if(imgs.length == 2){
				bean.setLogoPath2(imgs[1]);
				bean.setLogoPath(imgs[0]);
			}else{
				bean.setLogoPath(imgs[0]);
			}
		}
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
	public Result getData(Result rs, BeanAppAccompany bean) throws Exception {
		bean = mapper.findById(bean.getId());
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", bean.getId());
		map.put("remark", bean.getRemark());
		map.put("teacherId", bean.getTeacherId());
		map.put("userName", userMapper.findById(teacherMapper.findById(bean.getTeacherId()).getUid()).getUserName());
		String logoPaths = "";
		if(!ValidateUtils.isBlank(bean.getLogoPath()))logoPaths += "," + bean.getLogoPath();
		if(!ValidateUtils.isBlank(bean.getLogoPath2()))logoPaths += "," + bean.getLogoPath2();
		if(!ValidateUtils.isBlank(bean.getLogoPath3()))logoPaths += "," + bean.getLogoPath3();
		map.put("logoPaths", ValidateUtils.isBlank(logoPaths) ? "" : logoPaths.substring(1));
		rs.put("data", map);
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

	@Override
	public Result getUserName(Result rs, Long id) throws Exception {
		rs.put("data", userMapper.findById(teacherMapper.findById(id).getUid()));
		return rs;
	}

}
