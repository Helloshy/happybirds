package com.kapphk.yyt.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.base.mapper.BaseMapper;
import com.kapphk.yyt.bean.Company;

/**
 * 公司的数据操作接口
 * @author zoneyu 2016-12-06
*/
public interface CompanyMapper extends BaseMapper<Company, Long> {

	List<Map<String, Object>> findListByParam(@Param("param")Company company);
}