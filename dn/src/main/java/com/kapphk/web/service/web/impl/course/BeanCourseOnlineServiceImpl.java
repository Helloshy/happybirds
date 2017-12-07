package com.kapphk.web.service.web.impl.course;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kapphk.web.bean.course.BeanCourse;
import com.kapphk.web.bean.course.BeanCourseOnline;
import com.kapphk.web.bean.course.BeanCourseTag;
import com.kapphk.web.bean.teacher.BeanTeacher;
import com.kapphk.web.dao.mapper.course.BeanCourseMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseOnlineMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseOrderMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseSystemMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseTagMapper;
import com.kapphk.web.dao.mapper.teacher.BeanTeacherMapper;
import com.kapphk.web.service.web.imethod.course.BeanCourseOnlineService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.ExcelUtils;
import com.kapphk.web.utils.FileUploadUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
@Service
public class BeanCourseOnlineServiceImpl implements BeanCourseOnlineService {

	@Autowired
	private BeanCourseOnlineMapper mapper;
	
	@Autowired
	private BeanCourseMapper courseMapper;
	
	@Autowired
	private BeanTeacherMapper teacherMapper;
	
	@Autowired
	private BeanCourseTagMapper tagMapper;
	
	@Autowired
	private BeanCourseSystemMapper systemMapper;
	
	@Autowired
	private BeanCourseOrderMapper orderMapper;
	
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, String courseGroup, String teacherName,
			BigDecimal amount, String itemName, Long teacherId, GridReq gridReq) throws Exception {
		List<Map<String,Object>> list = mapper.findList(courseGroup,teacherName,amount,itemName,teacherId,gridReq.getPage(),gridReq.getRows());
		int count = mapper.findCount(courseGroup,teacherName,amount,itemName,teacherId);
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	/**
	 * 保存
	 */
	@Override
	public Result saveData(Result rs,BeanCourseOnline online,BeanCourse course,MultipartFile file,MultipartFile file1,List<String> ct,
			HttpServletRequest request) throws Exception {
		
		if(null != file && !file.isEmpty()){
			try {
				byte[] bytes = file.getBytes();
				String logoPath = FileUploadUtils.uploadFile(bytes,"upload/public", "2", request);
				course.setLogoPath(logoPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(null != file1 && !file1.isEmpty()){
			try {
				byte[] bytes = file1.getBytes();
				String logoPath = FileUploadUtils.uploadFile(bytes,"upload/public", "2", request);
				online.setTeacherLogoPath(logoPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Date date = new Date();
		BeanTeacher teacher = teacherMapper.findById(online.getTeacherId());
		if(ValidateUtils.isBlank(online.getTeacherLogoPath())) online.setTeacherLogoPath(teacher.getLogoPath());
		if(ValidateUtils.isBlank(online.getTeacherName())) online.setTeacherName(teacher.getItemName());
		if(ValidateUtils.isBlank(online.getTeacherRemark())) online.setTeacherRemark(teacher.getItemRemark());
		
		if(ValidateUtils.isBlank(online.getId())){
			course.setCreateTime(date);
			course.setState(1);
			course.setRecordType(2);
			courseMapper.insert(course);
			online.setId(course.getId());
			mapper.insert(online);
		}else{
			courseMapper.update(course);
			mapper.update(online);
		}
		
		//展示对象
		BeanCourseTag courseTag = new BeanCourseTag();
		courseTag.setCourseId(course.getId());
		tagMapper.deleteByEntity(courseTag);
		List<BeanCourseTag> list = new ArrayList<BeanCourseTag>();
		if(!ValidateUtils.isEmptyForCollection(ct)){
			for (String str : ct) {
				BeanCourseTag tag = new BeanCourseTag();
				tag.setCourseId(course.getId());
				tag.setCreateTime(date);
				tag.setTagIdentity(str);
				tag.setTagType("身份");
				list.add(tag);
			}
		}
		if(!ValidateUtils.isEmptyForCollection(list)) tagMapper.inserts(list);
		
		return rs;
	}

	/**
	 * 详情
	 */
	@Override
	public Result getData(Result rs, BeanCourseOnline bean) throws Exception {
		rs.put("data", mapper.findDetailById(bean.getId()));
		return rs;
	}

	/**
	 * 删除
	 */
	@Override
	public Result delete(Result rs, List<Long> ids) throws Exception {
		//验证该课程是否生成订单
		if(orderMapper.verifyByCourseIds(ids) > 0){
			rs.setStatus(Contents.ERROR);
			rs.setMsg("该课程已生成订单，不可删除");
		}else{
			rs.put("count",courseMapper.upstatusByCourseIds(ids));
			List<Long> list = systemMapper.findByCourseId(ids);
			if(!ValidateUtils.isEmptyForCollection(list)) courseMapper.upstatusByCourseIds(list);
		}
		return rs;
	}

	@Override
	public void exportExcel(String courseGroup, String teacherName,
			BigDecimal amount, String itemName, Long teacherId,HttpServletResponse response) {
		List<Map<String,Object>> list = mapper.findList(courseGroup,teacherName,amount,itemName,teacherId,0,9999999);
		String[] particular = new String[]{"主讲老师","网络课程名称","课程简介","课程介绍","课程分类","费用","视频网址","播放次数","展示对象","更新时间"};
		String[] property = new String[]{"teacherName","itemName","itemRemark","courseContent","courseGroup","amount","vedioLink","vedioPalys","ct","createTime"};
		System.out.println(ExcelUtils.downExcelList("网络课程表单.xlsx", particular, property, list, response));
		
	}

}
