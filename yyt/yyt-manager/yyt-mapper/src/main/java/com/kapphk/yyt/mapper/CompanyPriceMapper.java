package com.kapphk.yyt.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.base.mapper.BaseMapper;
import com.kapphk.yyt.bean.CompanyPrice;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-12-06
*/
public interface CompanyPriceMapper extends BaseMapper<CompanyPrice, Long> {

	List<Map<String, Object>> findListByParam(@Param("param")CompanyPrice companyPrice);


    /*
     * 录入当期表字时最新的价格
     * @param param
     * @return
     */
    CompanyPrice findLastPrice(@Param("param") Map<String, Object> param);
}