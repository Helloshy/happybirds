package com.kapphk.web.dao.mapper.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.product.BeanAppProductCategory;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-12-19
*/
public interface BeanAppProductCategoryMapper extends BaseMapper<BeanAppProductCategory, Long> {

	/**
	 * 分类列表
	 * @author dengwen 
	 * 2016-12-19下午4:37:08
	 */
	public List<Map<String,Object>> getList();

    /**
     * 查询列表
     * @param bean
     * @param page
     * @param rows
     * @return
     */
    List<Map<String,Object>> findList(@Param("param") BeanAppProductCategory bean,@Param("page") int page, @Param("rows") int rows);

    /**
     * 查询总记录数
     * @param bean
     * @return
     */
    Integer findCount(@Param("param")BeanAppProductCategory bean);

    List<Map<String,Object>> findCategoryList();

    BeanAppProductCategory findEntityByName(@Param("name") String name);
}