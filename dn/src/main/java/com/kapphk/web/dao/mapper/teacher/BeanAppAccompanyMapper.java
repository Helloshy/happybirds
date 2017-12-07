package com.kapphk.web.dao.mapper.teacher;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.teacher.BeanAppAccompany;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-10-10
*/
public interface BeanAppAccompanyMapper extends BaseMapper<BeanAppAccompany, Long> {

	/**
	 * 查询列表
	 * @author dengwen 
	 * 2016-10-10下午2:44:27
	 */
	List<Map<String, Object>> findList(@Param("teacherId")Long teacherId, @Param("page")int page, @Param("rows")int rows);

	/**
	 * 查询列表总数
	 * @author dengwen 
	 * 2016-10-10下午2:44:39
	 */
	int findCount(@Param("teacherId")Long teacherId);

	/**
	 * 陪伴师
	 * @author dengwen 
	 * 2016-10-11上午11:06:13
	 */
	List<Map<String, Object>> searchAccompanyList();

	/**
	 * 获取陪伴经历
	 * @author dengwen 
	 * 2016-10-18下午5:08:32
	 */
	List<Map<String, Object>> findUndergoList(@Param("tid")Long tid,@Param("uid")Long uid, @Param("page")int page, @Param("rows")int rows);
}