package com.kapphk.web.dao.mapper.teacher;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.teacher.BeanTeacherLevel;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 级别的数据操作接口
 * @author zoneyu 2016-10-08
*/
public interface BeanTeacherLevelMapper extends BaseMapper<BeanTeacherLevel, String> {

	/**
	 * 导出
	 * @author dengwen 
	 * 2016-10-8上午10:59:31
	 */
	List<Map<String,Object>> findList();

	/**
	 * 获取全部级别
	 * @author dengwen 
	 * 2016-10-8上午10:59:24
	 */
	List<Map<String, Object>> findLevelList(@Param("recordType")Integer recordType);

	/**
	 * 获取老师级别
	 * @author dengwen 
	 * 2016-10-17下午3:42:32
	 */
	List<String> findTagIdList(@Param("recordType")int recordType);
}