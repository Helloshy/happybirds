package com.kapphk.yyt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kapphk.base.mapper.BaseMapper;
import com.kapphk.entity.CommunityEntity;
import com.kapphk.yyt.bean.Community;

/**
 * 小区的数据操作接口
 * @author zoneyu 2016-12-06
*/
public interface CommunityMapper extends BaseMapper<Community, Long> {

	
	List<Community> findListByItemName(@Param("itemName")String itemName);


	List<Community> nearbyList(@Param("param")Community community);


	List<CommunityEntity> findListByParam(@Param("param")Community community);
}