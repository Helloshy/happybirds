package com.airparking.apms.api.carModel.mapper;

import com.airparking.core.base.mapper.AbstractMapper;
import com.airparking.apms.api.carModel.entity.CarModel;

import java.lang.Long;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

/**
 * Created by ryan on 2016-08-05.
 */
public interface CarModelMapper extends AbstractMapper<CarModel, Long> {
  CarModel findByTypeAndParkId(@Param("type") Byte type, @Param("parkId") Long parkId);
  
  @ResultType(List.class)
  @Select("SELECT cm.id,cm.name,cm.type,cm.park_id parkId,cm.code,cm.is_disabled isDisabled,"
      + "cm.is_deleted isDeleted FROM car_model cm WHERE (cm.park_id=#{parkId} AND cm.is_send = 0) OR ((cm.type=0 OR cm.type=1 ) "
      + "and cm.park_id=#{parkId}) ")
  List<CarModel> findAllWithoutCode(Long parkId);
  
  @ResultType(Byte.class)
  @Select("SELECT type FROM car_model WHERE park_id = #{parkId} ORDER BY type DESC LIMIT 1")
  Byte findMaxType(Long parkId);
  
  @ResultType(CarModel.class)
  @Select("SELECT * FROM car_model WHERE code = #{code} ")
  CarModel findByCode(String code);
  
  @ResultType(CarModel.class)
  @Select("SELECT * FROM car_model WHERE id = #{id} and park_id = #{parkId} and is_disabled = 0 and is_deleted = 0")
  CarModel findByIdAndParkId(@Param("id") Long id, @Param("parkId") Long parkId);
}