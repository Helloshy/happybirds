package com.kapphk.web.dao.mapper.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 用户数据操作接口
 * @author zoneyu 2016-10-18
*/
public interface BeanUserMapper extends BaseMapper<BeanUser, Long> {
	
	/**
	 * 省市区
	 * @author dengwen 
	 * 2016-9-21下午4:03:36
	 */
	public List<Map<String, Object>> findPcd(@Param("id")String id, @Param("type")Integer type);

	/**
	 * 查询列表
	 * @author dengwen 
	 * 2016-9-21下午4:13:16
	 */
	public List<Map<String, Object>> findList(@Param("staffPosition")String staffPosition,@Param("startTime")String startTime,
			@Param("endTime")String endTime,@Param("param")BeanUser bean,@Param("type")Integer type, 
			@Param("yqRealName")String yqRealName,@Param("page")int page, @Param("rows")int rows);

	/**
	 * 查询总数
	 * @author dengwen 
	 * 2016-9-21下午4:13:44
	 */
	public int findCount(@Param("staffPosition")String staffPosition,@Param("startTime")String startTime,@Param("endTime")String endTime,
			@Param("param")BeanUser bean, @Param("type")Integer type,@Param("yqRealName")String yqRealName);

	/**
	 * 暂停或使用
	 * @author dengwen 
	 * 2016-9-21下午5:46:32
	 */
	public void upstatus(@Param("ids")List<Long> ids, @Param("state")Integer state);

	/**
	 * 详情信息
	 * @author dengwen 
	 * 2016-9-22下午1:46:41
	 */
	public Map<String, Object> findDetailById(@Param("id")Long id);

	/**
	 * 审核
	 * @author dengwen 
	 * 2016-9-22下午6:13:17
	 */
	public void saveState(@Param("ids")List<Long> ids, @Param("state")Integer state,
			@Param("rejectReason")String rejectReason, @Param("dueDate")Date dueDate);

	/**
	 * 用户关系列表
	 * @author dengwen 
	 * 2016-9-23上午9:58:59
	 */
	public List<Map<String, Object>> findUserRelationList(@Param("param")BeanUser bean, @Param("page")int page, @Param("rows")int rows);

	/**
	 * 获取邀请人
	 * @author dengwen 
	 * 2016-9-23上午11:15:25
	 */
	public List<Map<String, Object>> searchUserList(@Param("staffPosition")String staffPosition);

	/**
	 * 省份
	 * @author dengwen 
	 * 2016-9-23上午11:19:22
	 */
	public List<Map<String, Object>> searchProvinceList();

	/**
	 * 更新密码
	 * @author zoneyu 
	 */		
	public void updatePwd(@Param("userName")String userName, @Param("newPwd")String newPwd);

	/**
	 * 获取个人信息
	 * @author dengwen 
	 * 2016-10-13下午3:06:56
	 */
	public Map<String, Object> findUserInfo(@Param("id")Long id);

	/**
	 * 动能家人
	 * @author dengwen 
	 * 2016-10-13下午5:09:48
	 */
	public Map<String, Object> getRelationList(@Param("id")Long id);

	/**
	 * 用户关系列表总数
	 * @author dengwen 
	 * 2016-10-22上午11:08:00
	 */
	public int findUserRelationCount(@Param("position")String position,@Param("userName")String userName, 
			@Param("realName")String realName, @Param("yqRealName")String yqRealName,@Param("khRealName")String khRealName,@Param("id")Long id);

	/**
	 * 用户关系列表
	 * @author dengwen 
	 * 2016-10-22上午11:08:33
	 */
	public List<Map<String, Object>> findUserRelationList(@Param("position")String position,
			@Param("userName")String userName, @Param("realName")String realName, @Param("yqRealName")String yqRealName,
			@Param("khRealName")String khRealName,@Param("id")Long id, @Param("page")int page, @Param("rows")int rows);

	/**
	 * 客服管理列表
	 * @author dengwen 
	 * 2016-10-22下午2:53:57
	 */
	public List<Map<String, Object>> findServiceList(@Param("province")String province,
			@Param("city")String city, @Param("userName")String userName,@Param("realName")String realName, 
			@Param("userId")Long userId,@Param("page")int page, @Param("rows")int rows);

	/**
	 * 客服管理列表总数
	 * @author dengwen 
	 * 2016-10-22下午2:55:09
	 */
	public int findServiceCount(@Param("province")String province,@Param("city")String city,
			@Param("userName")String userName,@Param("realName")String realName,@Param("userId")Long userId);

	/**
	 * 省
	 * @author zoneyu 16-11-2
	 */
	public List<Map<String, Object>> getProvince();

	/**
	 * 市
	 * @author zoneyu 16-11-2
	 */
	public List<Map<String, Object>> getCity(@Param("pid")String pid);

	/**
	 * 区
	 * @author zoneyu 16-11-2
	 */
	public List<Map<String, Object>> getDistrict(@Param("pid")String pid);

	/**
	 * 所有市
	 * @author zoneyu 16-11-7
	 */
	public List<Map<String, Object>> getCityAll();

	/**
	 * 所有区
	 * @author zoneyu 16-11-7
	 */
	public List<Map<String, Object>> getDistrictAll();

	/**
	 * 根据身份获取
	 * @author dengwen 
	 * 2016-11-4下午5:01:08
	 */
	public List<String> findByPositionList(@Param("list")List<String> list);

	/**
	  * 查询下级
	  * @author zoneyu 16-11-10
	  */
	public List<Map<String, Object>> searchchildList(@Param("uid")Long uid, @Param("grade")Integer grade,
			@Param("page")int page, @Param("rows")int rows);

	/**
	 * 获取员工身份
	 * @author dengwen 
	 * 2016-11-21下午2:49:21
	 */
	public List<Map<String, Object>> findByStaffPosition();

	/**
	 * 获取当月交易总金额
	 * @author dengwen 
	 * 2016-11-21下午4:28:45
	 */
	public Long findTotalAmount(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("list")List<String> list);

	/**
	  * 查询上三级
	  * @author zoneyu 16-11-23
	  */
	public List<Map<String, Object>> getThreeUid(@Param("uid")Long uid);

	/**
	 * 我的分佣
	 * @author dengwen 
	 * 2016-11-23上午10:05:18
	 */
	public Map<String, Object> findCentCommission(@Param("uid")Long uid);

	/**
	 * APP分佣明细
	 * @author dengwen 
	 * 2016-11-23上午11:53:47
	 */
	public List<Map<String, Object>> searchCentCommissionDetail(@Param("uid")Long uid,@Param("type")String type,
			@Param("page")int page, @Param("rows")int rows);

	/**
	 * 计算升级身份获得分佣
	 * @author dengwen 
	 * 2016-11-24下午6:23:43
	 */
	public Float findCommissionByuid(@Param("id")Long id);

	/**
	  * 支付控制
	  * @author zoenyu 16-12-10
	  */
	public Object getPaySet();

	/**
	 * 获取所在城市的存量客服
	 * @author dengwen 
	 * 2016-12-16下午2:22:41
	 */
	public List<Long> findStaffPositionByCity(@Param("city")String city,@Param("type")Integer type);

	public List<Map<String, Object>> getStaffPosition();

	/**
	 * 获取课程订单与商品订单
	 * @author dengwen 
	 * 2016-12-24下午2:09:01
	 */
	public List<Map<String, Object>> cancellationOfOrder(@Param("date")String date,@Param("type")Integer type);
}