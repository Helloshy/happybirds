package com.kapphk.web.service.web.imethod.course;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.kapphk.web.bean.course.BeanCourseContact;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
/**
 * 报名联系人业务层
 * @author dengwen
 * 2016-09-27 10:02:20
 */
public interface BeanCourseContactService {

	/**
	 * 查询列表
	 */
	public Result searchList(Result rs,BeanCourseContact bean,GridReq gridReq) throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs, BeanCourseContact bean,MultipartFile file,HttpServletRequest request) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanCourseContact bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<Long> ids) throws Exception;

	/**
	 * 查询教务人员列表
	 * @author dengwen 
	 * 2016-9-27上午10:48:18
	 */
	public List<Map<String, Object>> searchContactList();

}
