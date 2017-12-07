package com.kapphk.web.dao.mapper.community;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.community.BeanContentComment;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-11-02
*/
public interface BeanContentCommentMapper extends BaseMapper<BeanContentComment, Long> {

	/**
	 * 评论列表
	 * @author dengwen 
	 * 2016-11-2下午2:54:33
	 */
	List<Map<String,Object>> fineCommentList(@Param("contentId")Long contentId);
}