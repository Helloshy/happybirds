package com.kapphk.web.dao.mapper.community;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.community.BeanCommunity;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键数据操作接口
 * @author zoneyu 2016-11-04
*/
public interface BeanCommunityMapper extends BaseMapper<BeanCommunity, Long> {
	
	/**
	 * 查询列表
	 * @author dengwen 
	 * 2016-10-31下午4:27:19
	 */
	public List<Map<String, Object>> findList(@Param("province")String province, @Param("itemName")String itemName,
			@Param("userName")String userName, @Param("realName")String realName, @Param("page")int page, @Param("rows")int rows);

	/**
	 * 查询列表总数
	 * @author dengwen 
	 * 2016-10-31下午4:28:15
	 */
	public Integer findCount(@Param("province")String province, @Param("itemName")String itemName,
			@Param("userName")String userName, @Param("realName")String realName);

	/**
	 * 详情
	 * @author dengwen 
	 * 2016-11-1上午10:33:41
	 */
	public Map<String, Object> findDetailById(@Param("id")Long id);

	/**
	 * 获取全部小组
	 * @author dengwen 
	 * 2016-11-1下午2:01:26
	 */
	public List<Map<String, Object>> findCommunityList();

	/**
	 * 社区信息
	 * @author zoneyu 16-11-2
	 */
	public Map<String, Object> searchCommunityInfo(@Param("id")Long id) throws Exception ;

	/**
	 * 社区列表
	 * @author zoneyu 16-11-3
	 */
	public List<Map<String, Object>> searchCommunityList(@Param("uid")Long uid,@Param("dateTime")Date dateTime);

	/**
	 * 进入社区必须购买的课程
	 * @author zoneyu 16-11-4
	 */
	public Map<String, Object> getCourseInfo();

	/**
	 * 区长信息
	 * @author zoneyu 16-11-4
	 */
	public Map<String, Object> getMainInfo(@Param("id")Long id);

	/**
	 * 服务人员
	 * @author zoneyu 16-11-4
	 */
	public List<Map<String, Object>> getServiceInfo(@Param("id")Long id);

	/**
	 * 普通人员
	 * @author zoneyu 16-11-4
	 */
	public List<Map<String, Object>> getMemberInfo(@Param("id")Long id);

	/**
	 * 未加入的社区
	 * @author zoneyu 16-11-5
	 */
	public List<Map<String, Object>> searchCommunityListByProvince(@Param("province")String province, 
			@Param("page")Integer page, @Param("rows")Integer rows);

	/**
	 * 获取客户属于地区的社区
	 * @author dengwen 
	 * 2016-12-8下午7:49:35
	 */
	public Long findCommunityByUid(@Param("city")String city);

	/**
	 * 判断该用户是否是管理人员
	 * @author dengwen 
	 * 2016-12-8下午8:07:52
	 */
	public Integer findCountByUid(@Param("uid")Long uid);

	/**
	 * 获取一级客户数
	 * @author dengwen 
	 * 2016-12-9下午6:35:23
	 */
	public List<Long> findUsersById(@Param("uid")Long uid);

	/**
	 * 社区红点，社区详情
	 * @author zoneyu 16-12-24
	 */
	public List<Map<String, Object>> getPoint(@Param("communityId")Long communityId, @Param("dateTime")Date dateTime);

	/**
	 * 社区红点
	 * @author zoneyu 16-12-24
	 */
	public Object getCommunityPoint(@Param("uid")Long uid,@Param("dateTime")Date dateTime);
}