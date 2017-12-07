package com.kapphk.web.dao.mapper.community;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.community.BeanCommunityStaff;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-10-31
*/
public interface BeanCommunityStaffMapper extends BaseMapper<BeanCommunityStaff, Long> {

	/**
	 * 获取全部用户
	 * @author dengwen 
	 * 2016-11-1下午2:16:12
	 */
	List<Map<String, Object>> findUserList(@Param("userName")String userName);

	/**
	 * 查询列表
	 * @author dengwen 
	 * 2016-11-1下午2:32:04
	 */
	List<Map<String, Object>> findList(@Param("tagValue")String tagValue, @Param("itemName")String itemName,
			@Param("managerPhone")String managerPhone, @Param("realName")String realName,
			@Param("page")int page, @Param("rows")int rows);

	/**
	 * 查询列表总数
	 * @author dengwen 
	 * 2016-11-1下午2:32:16
	 */
	int findCount(@Param("tagValue")String tagValue, @Param("itemName")String itemName,
			@Param("managerPhone")String managerPhone, @Param("realName")String realName);

	/**
	 * 详情
	 * @author dengwen 
	 * 2016-11-1下午2:53:47
	 */
	Map<String, Object> findDetailById(@Param("id")Long id);
}