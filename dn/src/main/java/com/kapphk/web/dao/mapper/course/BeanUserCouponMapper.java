package com.kapphk.web.dao.mapper.course;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.course.BeanUserCoupon;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-09-22
*/
public interface BeanUserCouponMapper extends BaseMapper<BeanUserCoupon, Long> {

	/**
	 * 查询列表
	 * @author dengwen 
	 * 2016-9-22上午11:03:42
	 */
	public List<Map<String, Object>> findList(@Param("param")BeanUserCoupon bean, @Param("page")int page, @Param("rows")int rows);

	/**
	 * 查询总数
	 * @author dengwen 
	 * 2016-9-22上午11:03:59
	 */
	public int findCount(@Param("param")BeanUserCoupon bean);

	/**
	 * 详情信息
	 * @author dengwen 
	 * 2016-9-22上午11:06:57
	 */
	public Map<String, Object> findDetailById(@Param("id")Long id);

	/**
	 * 课程抵扣券
	 * @author zoneyu 16-10-13
	 */
	public List<Map<String, Object>> getUnUseCoupon(@Param("courseTypeId")Long courseTypeId, @Param("uid")Long uid);

	/**
	 * APP-获取抵扣卷列表
	 * @author dengwen 
	 * 2016-10-14上午11:07:46
	 */
	public List<Map<String, Object>> findCouponInfo(@Param("param")BeanUserCoupon bean, @Param("page")int page, @Param("rows")int rows);

	/**
	 * 验证是否有可用抵扣卷
	 * @author dengwen 
	 * 2016-11-18上午10:21:39
	 */
	public List<BeanUserCoupon> findByCourseId(@Param("uid")Long uid, @Param("courseId")Long courseId, @Param("startTime")String startTime);

}