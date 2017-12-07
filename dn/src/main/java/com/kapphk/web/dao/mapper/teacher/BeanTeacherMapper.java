package com.kapphk.web.dao.mapper.teacher;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.teacher.BeanTeacher;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键数据操作接口
 * @author zoneyu 2016-12-22
*/
public interface BeanTeacherMapper extends BaseMapper<BeanTeacher, Long> {
	
	/**
	 * 获取列表
	 * @author dengwen 
	 * 2016-10-8上午11:12:22
	 */
	List<Map<String, Object>> findList(@Param("param")BeanTeacher bean, @Param("district")String district, @Param("page")int page, @Param("rows")int rows);

	/**
	 * 获取列表总数
	 * @author dengwen 
	 * 2016-10-8上午11:13:53
	 */
	int findCount(@Param("param")BeanTeacher bean, @Param("district")String district);

	/**
	 * 详情
	 * @author dengwen 
	 * 2016-10-8上午11:46:02
	 */
	Map<String, Object> findDetailById(@Param("id")Long id);

	/**
	 * 获取全部动能名师
	 * @author dengwen 
	 * 2016-10-8下午2:10:58
	 */
	List<Map<String, Object>> findTeacherList(@Param("recordType")Integer recordType);

	/**
	 * 陪伴师推荐
	 * @author zoneyu 16-10-10
	 */
	List<Map<String, Object>> findHomeData(@Param("longitude")BigDecimal longitude, 
			@Param("latitude")BigDecimal latitude, @Param("city")String city,@Param("uid")Long uid);

	/**
	 * 获取APP动能名师列表
	 * @author dengwen 
	 * 2016-10-17下午3:56:12
	 */
	List<Map<String, Object>> findAPPTeacherList(@Param("district")String district, @Param("level")String level, 
			@Param("itemName")String itemName, @Param("page")int page, @Param("rows")Integer rows);

	/**
	 * 获取陪伴师列表
	 * @author dengwen 
	 * 2016-10-18上午11:25:58
	 */
	List<Map<String, Object>> findAccompanyList(@Param("uid")Long uid,@Param("longitude")BigDecimal longitude,  
			@Param("latitude")BigDecimal latitude,@Param("sex")Integer sex,@Param("level")String level,@Param("itemName")String itemName, 
			 @Param("tagValue")String tagValue,@Param("city")String city, @Param("page")Integer page, @Param("rows")Integer rows);
}