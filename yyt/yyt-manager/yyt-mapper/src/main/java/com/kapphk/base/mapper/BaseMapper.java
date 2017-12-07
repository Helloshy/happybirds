package com.kapphk.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * dao操作接口，用于继�?
 * @author exuan 2015-4-28
 */
public interface BaseMapper<M, T> {
	/**
	 * 分页查找数据
	 * @author exuan 2015-4-28
	 */
	public List<M> findByPage(@Param(value = "param") M m,
			@Param(value = "offset") int offset,
			@Param(value = "pageSize") int pageSize);
	
	/**
	 * 分页查找数据
	 * @author exuan 2015-4-28
	 */
	public List<M> findList(@Param(value = "param") M m);
	/**
	 * 分页查找总条�?
	 * @author exuan 2015-4-28
	 */
	public int findByPageCount(@Param(value = "param") M m);
	
	/**
	 * 根据id查询数据
	 * @author exuan 2015-4-28
	 */
	public M findById(T id);
	
	/**
	 * 加载id为传入id的数�?
	 * @author exuan 2015-4-28
	 */
	public List<M> load(List<T> ids);
	
	/**
	 * 查询�?有数�?
	 * @author exuan 2015-4-28
	 */
	public List<M> all();
	
	/**
	 * 查询总条�?
	 * @author exuan 2015-4-28
	 */
	public int count();
	
	/**
	 * 更新数据
	 * @author exuan 2015-4-28
	 */
	public int update(M m);
	
	/**
	 * 添加数据
	 * @author exuan 2015-4-28
	 */
	public int insert(M m);
	
	/**
	 * 添加多条数据（直接添加到数据�? 不做非空判断�?
	 * @author exuan 2015-4-28
	 */
	public int inserts(List<M> list);
	
	/**
	 * 删除单条数据
	 * @author exuan 2015-4-28
	 */
	public int delete(T id);
	
	/**
	 * 删除多条数据
	 * @author exuan 2015-4-28
	 */
	public int deletes(@Param(value = "ids")List<T> ids);
	
	/**
	 * 通过实体中的条件删除数据
	 * @author exuan 2015-4-28
	 */
	public int deleteByEntity(@Param(value = "param") M m);
	
	/**
	 * 通过实体中的条件查询�?�?
	 * @author exuan 2015-4-28
	 */
	public List<M> findAll(@Param(value = "param") M m);
	
	/**
	 * 通过实体条件查询唯一实体
	 * @author exuan 2015-4-28
	 */
	public M findEntityByCondition(@Param(value = "param") M m);
	
}
