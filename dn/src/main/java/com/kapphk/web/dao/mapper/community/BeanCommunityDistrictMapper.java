package com.kapphk.web.dao.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.community.BeanCommunityDistrict;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-10-31
*/
public interface BeanCommunityDistrictMapper extends BaseMapper<BeanCommunityDistrict, String> {

	/**
	 * 根据省份获取服务社区
	 * @author dengwen 
	 * 2016-11-3上午9:45:03
	 */
	List<Long> findCommunityId(@Param("proList")List<String> proList, @Param("pid")List<String> pid);
}