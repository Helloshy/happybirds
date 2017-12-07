package com.kapphk.yyt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kapphk.base.mapper.BaseMapper;
import com.kapphk.yyt.bean.AddrCity;

/**
 * 的数据操作接口
 * @author zoneyu 2016-12-06
*/
public interface AddrCityMapper extends  BaseMapper<AddrCity, String> {

	List<AddrCity> findListByCityName(@Param("cityName")String cityName);
}