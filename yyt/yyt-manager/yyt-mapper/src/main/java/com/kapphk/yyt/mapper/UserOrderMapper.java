package com.kapphk.yyt.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.base.mapper.BaseMapper;
import com.kapphk.entity.UserOrderEntity;
import com.kapphk.yyt.bean.UserOrder;

/**
 * 记录ID的数据操作接口
 * @author zoneyu 2016-12-06
*/
public interface UserOrderMapper extends BaseMapper<UserOrder, Long> {

	List<UserOrderEntity> findPage(@Param("param")UserOrder order);


	List<Map<String,Object>> queryPayment(@Param("param")Map<String,Object> param);

    List<Map<String,Object>> queryGasCount(@Param("param")Map<String,Object> param);

	List<Map<String,Object>> queryPaymentCount(@Param("param")Map<String,Object> param);

	List<Map<String,Object>> queryCellUserCount(@Param("param")Map<String,Object> param);
}