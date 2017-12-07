package com.kapphk.web.dao.mapper.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.user.BeanUserCollection;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-10-09
*/
public interface BeanUserCollectionMapper extends BaseMapper<BeanUserCollection, Long> {

	/**
	 * 获取资讯收藏列表
	 * @author dengwen 
	 * 2016-10-14下午4:10:42
	 */
	public List<Map<String, Object>> getCollectionList(@Param("uid")Long uid, @Param("page")int page, @Param("rows")Integer rows);
}