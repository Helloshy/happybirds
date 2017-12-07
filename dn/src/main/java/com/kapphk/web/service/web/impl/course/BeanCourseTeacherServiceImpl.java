package com.kapphk.web.service.web.impl.course;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kapphk.web.bean.course.BeanCourseTeacher;
import com.kapphk.web.bean.teacher.BeanTeacher;
import com.kapphk.web.dao.mapper.course.BeanCourseTeacherMapper;
import com.kapphk.web.dao.mapper.teacher.BeanTeacherMapper;
import com.kapphk.web.service.web.imethod.course.BeanCourseTeacherService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.FileUploadUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
@Service
public class BeanCourseTeacherServiceImpl implements BeanCourseTeacherService {

	@Autowired
	private BeanCourseTeacherMapper mapper;
	
	@Autowired
	private BeanTeacherMapper teacherMapper;
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, BeanCourseTeacher bean, GridReq gridReq) throws Exception {
		List<Map<String,Object>> list = mapper.findList(bean,gridReq.getPage(),gridReq.getRows());
		int count = mapper.findCount(bean);
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	/**
	 * 保存
	 */
	@Override
	public Result saveData(Result rs, BeanCourseTeacher bean,MultipartFile file,HttpServletRequest request) throws Exception {
		
		BeanCourseTeacher courseTeacher = new BeanCourseTeacher();
		courseTeacher.setCourseId(bean.getCourseId());
		courseTeacher.setTeacherId(bean.getTeacherId());
		List<BeanCourseTeacher> list = mapper.findAll(courseTeacher);
		if(!ValidateUtils.isEmptyForCollection(list) && (ValidateUtils.isBlank(bean.getId()) || list.get(0).getId() - bean.getId() != 0)){
			rs.setStatus(Contents.ERROR);
			rs.setMsg("该主讲老师已存在，请更改");
			return rs;
		}
		
		if(null != file && !file.isEmpty()){
			try {
				byte[] bytes = file.getBytes();
				String logoPath = FileUploadUtils.uploadFile(bytes,"upload/public", "2", request);
				bean.setLogoPath(logoPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		BeanTeacher teacher = teacherMapper.findById(bean.getTeacherId());
		if(ValidateUtils.isBlank(bean.getUserName())) bean.setUserName(teacher.getItemName());
		if(ValidateUtils.isBlank(bean.getLogoPath())) bean.setLogoPath(teacher.getLogoPath());
		if(ValidateUtils.isBlank(bean.getRemark())) bean.setRemark(teacher.getItemRemark());
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
	public Result getData(Result rs, BeanCourseTeacher bean) throws Exception {
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

}
