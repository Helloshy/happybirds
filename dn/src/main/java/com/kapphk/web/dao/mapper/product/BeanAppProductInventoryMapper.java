package com.kapphk.web.dao.mapper.product;

import com.kapphk.web.bean.product.BeanAppProductInventory;
import com.kapphk.web.dao.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-12-20
*/
public interface BeanAppProductInventoryMapper extends BaseMapper<BeanAppProductInventory, Long> {

    /**
     * 条件查询列表
     * @param page
     * @param rows
     * @return
     */
    List<Map<String, Object>> findListByParam(@Param("param") Map<String,Object>param, @Param("page")Integer page, @Param("rows")Integer rows);

    /**
     *  条件查询总记录数
     * @param param
     * @return
     */
    Integer findListByParamCount(@Param("param") Map<String, Object> param);

    Integer saveOrUpdate(BeanAppProductInventory bean);
    Integer batchSaveOrUpdate(List<BeanAppProductInventory> pis);

    Integer deletesByProductId(List<Long> ids);

    Integer offShelves(@Param("ids") List<Long> ids);

	Map<String, Object> findDetailById(@Param("id")Long id);
}