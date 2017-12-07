package com.kapphk.web.dao.mapper.product;

import com.kapphk.web.bean.product.BeanAppProductDelivery;
import com.kapphk.web.bean.system.BeanCity;
import com.kapphk.web.dao.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-12-19
*/
public interface BeanAppProductDeliveryMapper extends BaseMapper<BeanAppProductDelivery, Long> {
    
    
    
    List<Map<String,Object>> findList(@Param("param") Map<String,Object> param,
                                             @Param("page") Integer page,
                                             @Param("rows") Integer rows) throws Exception;

    Integer findCount(@Param("param") Map<String,Object> param) throws Exception;

    /**
     * 查询省份级别下的所有城市
     * @return
     */
    List<Map<String,Object>> findProvinceAndCitys();

    List<BeanCity> findCitys();

    String findProvinceId(@RequestParam("cityId") String cityId);

    Integer deleteByCityId(@Param("ids") List<String> ids);
}