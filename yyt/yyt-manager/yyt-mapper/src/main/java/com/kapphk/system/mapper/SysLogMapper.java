package com.kapphk.system.mapper;

import com.kapphk.base.mapper.BaseMapper;
import com.kapphk.system.bean.SysLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 数据操作接口
 * @author EXIA
*/
public interface SysLogMapper extends BaseMapper<SysLog, Long> {

	/**
	 * 总记录数
	 * @author zoneyu 16-6-12
	 */
	public int findPageCount(@Param(value = "userName") String userName);

	/**
	 * 数据集合
	 * @author zoneyu 16-6-12
	 */
	public List<Map<String, Object>> findPageList(@Param(value = "userName") String userName,
                                                  @Param(value = "page") int page, @Param(value = "rows") int rows);
	
	
}