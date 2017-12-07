package com.kapphk.web.dao.mapper.course;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.course.BeanCourse;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键数据操作接口
 * @author zoneyu 2016-12-22
*/
public interface BeanCourseMapper extends BaseMapper<BeanCourse, Long> {
	
	/**
	 * 获取课程列表
	 * @author dengwen 
	 * 2016-9-26下午1:48:08
	 */
	public List<Map<String, Object>> searchCourseList(@Param("recordType")Integer recordType,@Param("type")String type);

	/**
	 * 首课程推荐
	 * @author zoneyu 16-10-10 
	 */
	public List<Map<String, Object>> findHomeData();

	/**
	 * 线下课程
	 * @author zoneyu 16-10-12
	 */
	public List<Map<String, Object>> getOfflineList(@Param("uid")Long uid,@Param("courseGroup")String courseGroup, @Param("isPublic")Integer isPublic,
			@Param("name")String name, @Param("page")Integer page, @Param("rows")Integer rows);

	/**
	 * 课程详情
	 * @author zoneyu 16-10-12
	 */
	public Map<String, Object> getCourseInfo(@Param("courseId")Long courseId,@Param("uid")Long uid);

	/**
	 * 主讲老师
	 * @author zoneyu 16-10-12
	 */
	public List<Map<String, Object>> getTeachersList(@Param("courseId")Long courseId);
	
	/**
	 * 报名联系人
	 * @author zoneyu 16-10-12
	 */
	public List<Map<String, Object>> getConnectList(@Param("courseId")Long courseId);

	/**
	 * 推荐课程
	 * @author zoneyu 16-10-12
	 */
	public List<Map<String,Object>> getPushInfo(@Param("courseId")Long courseId);

	/**
	 * 网络课程
	 * @author zoneyu 16-10-19
	 */
	public List<Map<String, Object>> getOnlineList(@Param("courseType")Integer courseType,  @Param("courseGroup")String courseGroup,  
			@Param("isPublic")Integer isPublic,@Param("name")String name,@Param("page")Integer page, @Param("rows")Integer rows);

	/**
	 * 线下课程详情
	 * @author zoneyu 16-10-20
	 */
	public Map<String, Object> getOnlineCourseDetail(@Param("courseId")Long courseId,@Param("uid")Long uid);

	/**
	 * 系统课程
	 * @author zoneyu 16-10-21
	 */
	public List<Map<String, Object>> getSystemList(@Param("name")String name, 
			@Param("page")Integer page, @Param("rows")Integer rows);

	/**
	 * 系统课程详情
	 * @author zoneyu 16-10-21
	 */
	public Map<String, Object> getSystemCourseDetail(@Param("courseId")Long courseId);

	/**
	 * 报名联系人
	 * @author zoneyu 16-10-21
	 */
	public List<Map<String, Object>> getContactList(@Param("courseId")Long courseId);

	/**
	 * 系统课程内容
	 * @author zoneyu 16-10-21
	 */
	public List<Map<String, Object>> getSystemCourseList(@Param("courseId")Long courseId);
	
	/**
	 * 动能财商
	 * @author zoneyu 16-10-21
	 */
	public List<Map<String, Object>> getFinancialListOffline(@Param("page")Integer page, @Param("rows")Integer rows);

	/**
	 * 网络课程
	 * @author zoneyu 16-10-21
	 */
	public List<Map<String, Object>> getFinancialListOnline(@Param("page")Integer page, @Param("rows")Integer rows);

	/**
	 * 系统课程
	 * @author zoneyu 16-10-21
	 */
	public List<Map<String, Object>> getFinancialListSystem(@Param("page")Integer page, @Param("rows")Integer rows);

	/**
	 * 更新课程状态
	 * @author dengwen 
	 * 2016-12-7上午9:53:23
	 */
	public int upstatusByCourseIds(@Param("ids")List<Long> ids);
}