package com.kapphk.web.dao.mapper.system;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.system.BeanMessagePush;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-11-04
*/
public interface BeanMessagePushMapper extends BaseMapper<BeanMessagePush, Long> {

	/**
	 * 获取系统通知列表
	 * @author dengwen 
	 * 2016-11-12下午3:41:28
	 */
	List<Map<String, Object>> getMessagePushList(@Param("uid")Long uid, @Param("page")int page, @Param("rows")Integer rows);

	/**
	 * 消息红点
	 * @author zoneyu 16-12-3
	 */
	int getAmount(@Param("date")Date date);
}