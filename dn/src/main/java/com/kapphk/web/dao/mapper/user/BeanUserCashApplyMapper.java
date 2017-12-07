package com.kapphk.web.dao.mapper.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.user.BeanUserCashApply;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-10-28
*/
public interface BeanUserCashApplyMapper extends BaseMapper<BeanUserCashApply, Long> {

	/**
	 * 更新状态
	 * @author dengwen 
	 * 2016-10-28上午11:19:56
	 */
	int upstatus(@Param("ids")List<Long> ids, @Param("state")Integer state);

	/**
	 * 查询列表
	 * @author dengwen 
	 * 2016-10-28上午11:28:38
	 */
	List<Map<String, Object>> findList(@Param("param")BeanUserCashApply bean, @Param("userName")String userName,
			@Param("realName")String realName,@Param("startTime")String startTime, @Param("endTime")String endTime,
			 @Param("page")int page, @Param("rows")int rows);

	/**
	 * 查询列表总数
	 * @author dengwen 
	 * 2016-10-28上午11:28:54
	 */
	int findCount(@Param("param")BeanUserCashApply bean, @Param("userName")String userName,
			@Param("realName")String realName,@Param("startTime")String startTime, @Param("endTime")String endTime);
}