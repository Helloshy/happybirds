package com.kapphk.web.dao.mapper.teacher;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.teacher.BeanTeacherOrder;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-10-08
*/
public interface BeanTeacherOrderMapper extends BaseMapper<BeanTeacherOrder, Long> {

	/**
	 * 更新状态
	 * @author dengwen 
	 * 2016-10-9上午11:57:56
	 */
	void upstate(@Param("ids")List<Long> ids, @Param("param")BeanTeacherOrder bean);

	/**
	 *获取列表
	 * @author dengwen 
	 * 2016-10-9下午1:53:11
	 */
	List<Map<String, Object>> findList(@Param("param")BeanTeacherOrder bean, @Param("itemName")String itemName,
			@Param("type")Integer type,@Param("realName")String realName,@Param("page")int page, @Param("rows")int rows);

	/**
	 * 列表总数
	 * @author dengwen 
	 * 2016-10-9下午1:53:27
	 */
	int findCount(@Param("param")BeanTeacherOrder bean, @Param("itemName")String itemName,@Param("type")Integer type,@Param("realName")String realName);

	/**
	 * 详情
	 * @author dengwen 
	 * 2016-10-9下午1:53:36
	 */
	Map<String, Object> findDetailById(@Param("id")Long id);

	/**
	 * 导出数据
	 * @author dengwen 
	 * 2016-10-9下午4:49:09
	 */
	List<Map<String, Object>> findExcel(@Param("type")String type);

	/**
	 * APP获取订单列表
	 * @author dengwen 
	 * 2016-10-20下午2:43:37
	 */
	List<Map<String, Object>> findTeacherOrderList(@Param("param")BeanTeacherOrder bean, @Param("page")int page, @Param("rows")int rows);

	/**
	 * 订单详情
	 * @author dengwen 
	 * 2016-10-20下午4:53:25
	 */
	Map<String, Object> findOrderDetail(@Param("id")Long id);

	/**
	 * 预约详情
	 * @author zoneyu 16-11-22
	 */
	public Object searchTeacherOrderDetail(@Param("id")Long id);

	int upTeacherOrder(@Param("type")Integer type, @Param("date")String date);
}