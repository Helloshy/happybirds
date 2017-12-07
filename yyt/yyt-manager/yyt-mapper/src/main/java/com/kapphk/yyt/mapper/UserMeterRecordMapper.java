package com.kapphk.yyt.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.base.mapper.BaseMapper;
import com.kapphk.yyt.bean.UserMeterRecord;

/**
 * 记录ID的数据操作接口
 * @author zoneyu 2016-12-06
*/
public interface UserMeterRecordMapper extends BaseMapper<UserMeterRecord, Long> {
	
	List<Map<String, Object>> findListByParam(@Param("param")Map<String, Object> param);


	List<Map<String,Object>> findMeterRecordsByParam(@Param("param")Map<String, Object> param);

	List<Map<String,Object>> findMeterRecordsByQueryParam(@Param("param")Map<String, Object> param);

	/**
	 *
	 *查询用户审核抄表记录
	 */
	List<Map<String,Object>> findMeterAuditRecordsByParam(@Param("param")Map<String, Object> param);

    Integer updateData(UserMeterRecord userMeterRecord);

	Integer updateMeter(UserMeterRecord userMeterRecord);


	List<Map<String, Object>> findMeterRecordChart(@Param("param")Map<String, Object> param);

    Integer updateMany(@Param("ids") List<Long> ids, @Param("state") Integer state);
}