package com.kapphk.web.dao.mapper.teacher;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.teacher.BeanTeacherComment;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-10-08
*/
public interface BeanTeacherCommentMapper extends BaseMapper<BeanTeacherComment, Long> {

	List<Map<String, Object>> findList(@Param("param")BeanTeacherComment bean, @Param("page")int page,
			@Param("rows")int rows);

	List<Map<String, Object>> getCommentList(@Param("tid")Long tid, @Param("page")int page, @Param("rows")int rows);
}