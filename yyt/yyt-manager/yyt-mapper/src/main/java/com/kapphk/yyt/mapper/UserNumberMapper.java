package com.kapphk.yyt.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.kapphk.base.mapper.BaseMapper;
import com.kapphk.entity.UserNumberEntity;
import com.kapphk.yyt.bean.UserNumber;

/**
 * 用户燃气编号的数据操作接口
 * 
 * @author zoneyu 2016-12-06
 */
public interface UserNumberMapper extends BaseMapper<UserNumber, Long> {

	public List<Map<String, Object>> findListByParam(@Param("param") Map<String, Object> param);

	public Integer updateUserNumberState(@Param("ids") List<Long> ids, @Param("status") Integer status);

	void bindingUser(String uid, String unid);

	List<UserNumber> queryUnit(@Param("param") UserNumber userNumber);

	public List<UserNumberEntity> queryByUid(@Param("unid")Long unid, @Param("uid")Long uid, 
			@Param("communityId")Long communityId);

	List<Map<String, Object>> findChargeListByParam(@Param("param") Map<String, Object> param);

    /**
     * 查询缴费记录
     * @param param
     * @return
     */
    List<Map<String,Object>> findRecordListByParam(@Param("param") Map<String, Object> param);

    /**
     * 更新账户余额
     * @param userNumber
     * @return
     */
    Integer updateBlanceAmount(UserNumber userNumber);

    /**
     * 查询所在公司所有的燃气用户信息
     * @param param
     * @return
     */
    List<Map<String,Object>> findAllByCompanyId(@Param("param") Map<String, Object> param);

	
}