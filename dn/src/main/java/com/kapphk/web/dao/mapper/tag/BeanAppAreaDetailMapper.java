package com.kapphk.web.dao.mapper.tag;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.tag.BeanAppAreaDetail;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-10-28
*/
public interface BeanAppAreaDetailMapper extends BaseMapper<BeanAppAreaDetail, Long> {

	/**
	 * 获取全部省份
	 * @author dengwen 
	 * 2016-11-3上午9:35:31
	 */
	List<String> findProvinceList(@Param("aid_checkbox")String aid_checkbox,@Param("aid")List<String> aid);
}