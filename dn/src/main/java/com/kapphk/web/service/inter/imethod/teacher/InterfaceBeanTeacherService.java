package com.kapphk.web.service.inter.imethod.teacher;

import java.math.BigDecimal;
import java.util.List;

import com.kapphk.web.bean.course.BeanCourseCollection;
import com.kapphk.web.bean.teacher.BeanAccompanyLike;
import com.kapphk.web.bean.teacher.BeanTeacherCollection;
import com.kapphk.web.utils.Result;

public interface InterfaceBeanTeacherService {

	/**
	 * 陪伴师收藏列表
	 * @author dengwen 
	 * 2016-10-14下午2:10:30
	 */
	public Result getCollectionList(BeanCourseCollection bean, Integer page, Result rs)throws Exception;

	/**
	 * 取消陪伴师收藏
	 * @author dengwen 
	 * 2016-10-14下午3:56:42
	 */
	public Result deleteCollection(List<Long> ids, Result rs)throws Exception;

	/**
	 * 获取
	 * @author dengwen 
	 * 2016-10-17下午3:51:33
	 */
	public Result getTeacherList(String district, String level,
			String itemName, Integer page, Result rs)throws Exception;

	/**
	 * 获取动能讲师老师详情
	 * @author dengwen 
	 * 2016-10-17下午3:48:07
	 */
	public Result getTeacherDetail(Long id, Result rs)throws Exception;

	/**
	 * 获取动能讲师课程列表
	 * @author dengwen 
	 * 2016-10-17下午4:27:58
	 */
	public Result getCourseList(Long id, Integer page, Result rs)throws Exception;

	/**
	 * 获取陪伴师列表
	 * @author dengwen 
	 * 2016-10-18上午11:21:49
	 */
	public Result getAccompanyList(Long uid, BigDecimal longitude, BigDecimal latitude, Integer sex, String level, String itemName,
			String tagValue, String city, Integer page, Result rs)throws Exception;

	public Result getConditionList(Result rs)throws Exception;

	/**
	 * 获取陪伴师经历
	 * @author dengwen 
	 * 2016-10-18下午4:54:03
	 */
	public Result getUndergoList(Long uid, Long tid, Integer page, Result rs)throws Exception;


	/**
	 * 保存经历点赞
	 * @author dengwen 
	 * 2016-10-18下午5:40:17
	 */
	public Result saveAccompanyLike(BeanAccompanyLike bean, Result rs)throws Exception;

	/**
	 * 可预约时间
	 * @author dengwen 
	 * 2016-10-18下午5:56:00
	 */
	public Result getTeacherArrangeDetail(Long tid, Result rs)throws Exception;

	/**
	 * 陪伴师详情
	 * @author dengwen 
	 * 2016-10-19上午9:36:31
	 */
	public String getAccompanyDetail(Long id)throws Exception;

	/**
	 * 保存收藏老师
	 * @author dengwen 
	 * 2016-10-19上午9:51:18
	 */
	public Result saveCollection(BeanTeacherCollection bean, String type, Result rs)throws Exception;

	/**
	 * 经历详情
	 * @author dengwen 2016-10-18下午3:26:57
	 */
	public String getUndergoDetail(Long id);

}
