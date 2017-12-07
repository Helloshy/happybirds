package com.kapphk.yyt.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.base.mapper.BaseMapper;
import com.kapphk.entity.CompanyHallEntity;
import com.kapphk.yyt.bean.CompanyHall;

/**
 * ID的数据操作接口
 * 
 * @author zoneyu 2016-12-06
 */
public interface CompanyHallMapper extends BaseMapper<CompanyHall, Long> {

	List<CompanyHallEntity> nearbyPage(@Param("param")CompanyHall hall);

	List<Map<String,Object>> findListByParam(@Param("param")CompanyHall companyHall);

	List<CompanyHall> finListByUid(Long id);
}