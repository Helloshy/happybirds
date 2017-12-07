package com.kapphk.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kapphk.base.mapper.BaseMapper;
import com.kapphk.entity.EmployeeEntity;
import com.kapphk.system.bean.SystemUser;
import com.kapphk.yyt.bean.Company;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-12-06
*/
public interface SystemUserMapper extends  BaseMapper<SystemUser, Long> {

    public SystemUser findByUsername(String username);

	public List<EmployeeEntity> queryEmployeeByPage(@Param("param")SystemUser systemUser);

	/**
	 * 根据营业厅id获取工作人员
	 * @param hallId
	 * @return
	 */
	public List<SystemUser> findHallUser(Long hallId);
	
	/**
	 * 更新用户状态
	 * @param ids
	 * @return
	 */
	public int updateState(@Param(value = "ids")List<Long> ids);
}