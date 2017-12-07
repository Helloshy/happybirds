package com.kapphk.web.dao.mapper.product;

import com.kapphk.web.bean.product.BeanAppProductPhoto;
import com.kapphk.web.dao.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-12-19
*/
public interface BeanAppProductPhotoMapper extends BaseMapper<BeanAppProductPhoto, Long> {

    Integer deletesByProductId(List<Long> ids);

    List<Map<String,Object>> findByProductId(@Param("productId") Long productId);
}