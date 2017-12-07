package com.kapphk.web.dao.mapper.course;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.course.BeanCourseOffline;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-10-13
*/
public interface BeanCourseOfflineMapper extends BaseMapper<BeanCourseOffline, Long> {
	
	/**
	 * 省市区
	 * @author dengwen 
	 * 2016-10-13下午2:02:27
	 */
	public List<Map<String, Object>> findPcd(@Param("id")String id, @Param("type")Integer type);

	/**
	 * 查询列表
	 * @author dengwen 
	 * 2016-9-26下午2:25:53
	 */
	public List<Map<String, Object>> findList(@Param("itemName")String itemName,@Param("courseTypeId")Long courseTypeId,
			@Param("isRecommend")Integer isRecommend, @Param("state")Integer state, 
			@Param("amount")BigDecimal amount, @Param("page")int page,@Param("rows")int rows);

	/**
	 * 查询总数
	 * @author dengwen 
	 * 2016-9-26下午2:27:37
	 */
	public int findCount(@Param("itemName")String itemName,@Param("courseTypeId")Long courseTypeId,
			@Param("isRecommend")Integer isRecommend, @Param("state")Integer state, 
			@Param("amount")BigDecimal amount);

	/**
	 * 详情
	 * @author dengwen 
	 * 2016-9-26下午2:55:16
	 */
	public Map<String, Object> findDetailById(@Param("id")Long id);

	/**
	 * 更新课程状态
	 * @author dengwen 
	 * 2016-9-26下午5:33:19
	 */
	public void upState(@Param("ids")List<Long> ids, @Param("state")Integer state);

	/**
	 * 动能留学课程列表
	 * @author dengwen 
	 * 2016-10-17下午6:08:48
	 */
	public List<Map<String, Object>> findCourseOfflineList(@Param("page")int page, @Param("rows")Integer rows);

	/**
	 * 推荐课程
	 * @author zoneyu 16-12-6
	 */
	public List<Map<String,Object>> getCOurse(@Param("names")List<String> names);

	/**
	 * 导出数据
	 * @author zoneyu 16-12-23
	 */
	public List<Map<String, Object>> findExportList(@Param("itemName")String itemName,@Param("courseTypeId")Long courseTypeId,
			@Param("isRecommend")Integer isRecommend, @Param("state")Integer state, 
			@Param("amount")BigDecimal amount);
}