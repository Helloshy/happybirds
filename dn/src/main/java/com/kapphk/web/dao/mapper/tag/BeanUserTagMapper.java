package com.kapphk.web.dao.mapper.tag;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.tag.BeanUserTag;
import com.kapphk.web.dao.mapper.BaseMapper;

public interface BeanUserTagMapper extends BaseMapper<BeanUserTag, String> {

	/**
	 * 批量删除
	 * @author dengwen 
	 * 2016-9-29上午9:26:57
	 */
	public int dele(@Param("ids")List<String> ids, @Param("param")BeanUserTag bean);

	/**
	 * 获取标签列表
	 * @author dengwen 
	 * 2016-9-30上午11:49:39
	 */
	public List<Map<String, Object>> findTagTypeList(@Param("param")BeanUserTag bean);

	/**
	 * 标签集合
	 * @param zoneyu 16-9-30
	 */
	public List<Map<String, Object>> getTagList(@Param("tagType")String tagType);

	public List<String> findTagIdList(@Param("tagType")String tagType);

}
