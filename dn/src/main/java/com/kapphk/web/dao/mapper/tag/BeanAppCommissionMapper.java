package com.kapphk.web.dao.mapper.tag;

import java.util.List;
import java.util.Map;

import com.kapphk.web.bean.tag.BeanAppCommission;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-10-28
*/
public interface BeanAppCommissionMapper extends BaseMapper<BeanAppCommission, Long> {

	/**
	 * 导出数据
	 * @author dengwen 
	 * 2016-10-28下午3:22:09
	 */
	List<Map<String, Object>> exportExcel();
}