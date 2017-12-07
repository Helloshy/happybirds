package com.kapphk.web.service.inter.imethod.course;

import java.util.List;
import java.util.Map;

import com.kapphk.web.bean.course.BeanAppJoin;
import com.kapphk.web.bean.course.BeanCourse;
import com.kapphk.web.bean.course.BeanCourseCollection;
import com.kapphk.web.service.BaseService;
import com.kapphk.web.utils.Result;

public interface InterfaceBeanCourseService extends BaseService<BeanCourse, Long> {

	/**
	 * 课程列表
	 * @author zoneyu 16-10-11
	 */
	public Result getCourseModuleList(Integer courseType, String courseGroup, Integer isPublic,Integer recordType, String name, Long uid, Integer page, Result rs) throws Exception ;

	/**
	 * 课程详情
	 * @author zoneyu 16-10-12
	 */
	public Result getCourseDetail(String typeName, Long uid, Long courseId, Result rs) throws Exception ;

	/**
	 * 课程收藏
	 * @author zoneyu 16-10-12
	 */
	public Result saveCollection(BeanCourseCollection bean, Integer type, Result rs) throws Exception ;

	/**
	 * 课程介绍&报名须知
	 * @author zoneyu 16-10-12
	 */
	public String getIntroduction(Long id,Integer typeName) throws Exception ;

	/**
	 * 课程抵扣券
	 * @author zoneyu 16-10-13
	 */
	public Result getUnUseCoupon(Long courseTypeId, Long uid, Result rs) throws Exception ;

	/**
	 * 获取课程收藏列表
	 * @author dengwen 
	 * 2016-10-14上午11:22:15
	 */
	public Result getCollectionList(BeanCourseCollection bean, Integer page, Result rs) throws Exception;

	/**
	 * 取消课程收藏
	 * @author dengwen 
	 * 2016-10-14下午3:53:58
	 */
	public Result deleteCollection(List<Long> ids, Result rs) throws Exception;

	/**
	 * 线下课程详情
	 * @author zoneyu 16-10-20
	 */
	public Result doGetOnlineCourseDetail(Long courseId, Long uid, Result rs) throws Exception;

	/**
	 * 线下课程简介
	 * @author zoneyu 16-10-20
	 */
	public String getOnlineCourseIntroduction(Long courseId) throws Exception;

	/**
	 * 动能财商
	 * @author zoneyu 16-10-21
	 */
	public Result getFinancialList(Integer type, Integer page, Result rs) throws Exception;

	/**
	 * 系统课程详情
	 * @author zoneyu 16-10-21
	 */
	public Result getSystemCourseDetail(Long courseId, Result rs) throws Exception;

	/**
	 * 加盟申请
	 * @author zoneyu 16-10-22
	 */
	public Result saveJoin(BeanAppJoin bean, Result rs) throws Exception;

	public Result saveVedioPalys(Long id, Result rs) throws Exception;

	public Map<String,Object> shareCourseDetail(Long courseId);

}
