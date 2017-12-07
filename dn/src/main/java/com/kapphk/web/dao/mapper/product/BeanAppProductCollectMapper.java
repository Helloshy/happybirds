package com.kapphk.web.dao.mapper.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.product.BeanAppProductCollect;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-12-19
*/
public interface BeanAppProductCollectMapper extends BaseMapper<BeanAppProductCollect, Long> {

	/**
     * 获取收藏列表
     * @author dengwen 
     * 2016-12-21上午9:48:01
     */
	public List<Map<String,Object>> getProductCollectList(@Param("uid")Long uid, @Param("page")int page, @Param("rows")int rows);
}