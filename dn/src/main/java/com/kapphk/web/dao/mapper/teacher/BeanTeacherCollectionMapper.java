package com.kapphk.web.dao.mapper.teacher;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.teacher.BeanTeacherCollection;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-10-14
*/
public interface BeanTeacherCollectionMapper extends BaseMapper<BeanTeacherCollection, Long> {

	/**
	 * APP-陪伴师收藏列表
	 * @author dengwen 
	 * 2016-10-14下午2:17:40
	 */
	public List<Map<String, Object>> getCollectionList(@Param("uid")Long uid,@Param("page")int page,@Param("rows")int rows);
}