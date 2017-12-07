package com.kapphk.web.service.web.imethod.course;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.kapphk.web.bean.course.BeanCourse;
import com.kapphk.web.bean.course.BeanCourseOffline;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;

public interface BeanCourseOfflineService {
	/**
	 * 查询列表
	 */
	public Result searchList(Result rs, String itemName, Long courseTypeId, Integer isRecommend,
			Integer state, BigDecimal amount, GridReq gridReq)throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs,BeanCourse course,BeanCourseOffline offline,MultipartFile file,HttpServletRequest request) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanCourse bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<Long> ids) throws Exception;

	/**
	 * 省市区
	 * @author dengwen 
	 * 2016-9-26下午1:57:50
	 */
	public List<Map<String, Object>> searchPcd(String id, Integer type);

	/**
	 * 更新首页推荐
	 * @author dengwen 
	 * 2016-9-26下午4:46:37
	 */
	public Result saveIsRecommend(Result rs, BeanCourseOffline offline) throws Exception;

	/**
	 * 批量更新课程状态
	 * @author dengwen 
	 * 2016-9-26下午5:32:15
	 */
	public Result upState(Result rs, List<Long> asList, Integer state) throws Exception;

	/**
	 * 导出
	 * @author zoneyu 16-12-23
	 */
	public void export(String itemName, Long courseTypeId, Integer isRecommend, Integer state, BigDecimal amount,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

}
