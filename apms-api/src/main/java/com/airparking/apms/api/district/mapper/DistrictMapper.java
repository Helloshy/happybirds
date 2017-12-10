package com.airparking.apms.api.district.mapper;

import com.airparking.core.base.mapper.AbstractMapper;
import com.airparking.apms.api.district.entity.District;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by ryan on 2016-01-28.
 */
public interface DistrictMapper extends AbstractMapper<District, Long> {
    @ResultType(String.class)
    @Select("select d2.city_cde " +
            " from ap_park p" +
            " left join ap_district d1 on d1.id = p.district_id" +
            " left join ap_district d2 on d1.parent_id = d2.id" +
            " where p.id = #{parkId}")
    String getCityCdeByParkId(Long parkId);

	List<Map<String, Object>> listAllMap();

    @ResultType(District.class)
    @Select("select id, short_name as shortName, city_cde as cityCde, longitude, latitude from ap_district where level = #{level} and enabled is true and is_deleted is false")
    List<District> enabledCities(int level);

    @ResultType(Map.class)
    @Select("select id as cityid, short_name as cityname from ap_district where level = #{level} and enabled is true and is_deleted is false ")
    List<Map> listAllEnabled(int level);
}