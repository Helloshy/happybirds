package com.kapphk.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kapphk.base.mapper.BaseMapper;
import com.kapphk.entity.MessageEntity;
import com.kapphk.system.bean.Message;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-12-06
*/
public interface MessageMapper extends  BaseMapper<Message, Long> {

	List<MessageEntity> findListByUid(@Param("title")String title,@Param("uid")Long uid,@Param("isRead")Integer isRead);

}