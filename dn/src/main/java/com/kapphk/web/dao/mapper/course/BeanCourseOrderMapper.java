package com.kapphk.web.dao.mapper.course;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.course.BeanCourseOrder;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键数据操作接口
 * @author zoneyu 2016-11-17
*/
public interface BeanCourseOrderMapper extends BaseMapper<BeanCourseOrder, Long> {
	
	/**
	 * 是否享受复训价
	 * @author zoneyu 16-10-14
	 */
	public Map<String, Object> checkInfo(@Param("param")BeanCourseOrder bean);

	/**
	 * 支付成功获取订单信息
	 * @author zoneyu 16-10-17
	 */
	public Map<String, Object> getOrderInfo(@Param("orderNo")String orderNo);

	/**
	 * 课程信息
	 * @author zoneyu 16-10-18
	 */
	public Map<String, Object> getCourseInfo(@Param("param")BeanCourseOrder bean);

	/**
	 * 学员信息--父母课程
	 * @author zoneyu 16-10-18
	 */
	public Map<String, Object> getStudentInfoByParent(@Param("id")Long id);

	/**
	 * 学员信息--孩子课程
	 * @author zoneyu 16-10-18
	 */
	public Map<String, Object> getStudentInfoByChild(@Param("id")Long id);

	/**
	 * 赞赏记录
	 * @author zoneyu 16-10-20
	 */
	public List<Map<String, Object>> getAdmirationList(@Param("courseId")Long courseId, 
			@Param("page")Integer page, @Param("rows")Integer rows);

	/**
	 * 获取订单列表
	 * @author dengwen 
	 * 2016-10-24下午4:19:54
	 */
	public List<Map<String, Object>> findList(@Param("uid")Long uid,@Param("list")List<Long> list,@Param("payStatus")Integer payStatus,
			@Param("isUse")Integer isUse, @Param("orderNo")String orderNo, @Param("itemName")String itemName,
			@Param("realName")String realName,@Param("startTime")String startTime, @Param("endTime")String endTime,
			@Param("type")String type, @Param("page")int page, @Param("rows")int rows,@Param("isPay")Integer isPay);

	/**
	 * 获取订单总数
	 * @author dengwen 
	 * 2016-10-24下午4:21:46
	 */
	public int findCount(@Param("uid")Long uid,@Param("list")List<Long> list,
			@Param("payStatus")Integer payStatus,@Param("isUse")Integer isUse, 
			@Param("orderNo")String orderNo, @Param("itemName")String itemName,@Param("realName")String realName,
			@Param("startTime")String startTime, @Param("endTime")String endTime,@Param("type")String type,@Param("isPay")Integer isPay);

	/**
	 * 获取课程列表
	 * @author dengwen 
	 * 2016-10-25上午11:05:18
	 */
	public List<Map<String, Object>> findCourseList(@Param("type")String type);

	/**
	 * 课程详情
	 * @author dengwen 
	 * 2016-10-25上午11:21:41
	 */
	public Map<String, Object> findCourseDetail(@Param("id")Long id);

	/**
	 * 获取用户详情
	 * @author dengwen 
	 * 2016-10-25下午1:46:17
	 */
	public Map<String, Object> findUserDetail(@Param("userName")String userName);

	/**
	 * 课程订单--线下课程
	 * @author zoneyu 16-10-24
	 */
	public List<Map<String, Object>> getOfflineOrderList(@Param("uid")Long uid, @Param("status")Integer status, 
			@Param("page")Integer page, @Param("rows")Integer rows);

	/**
	 * 课程订单--网络课程
	 * @author zoneyu 16-10-26
	 */
	public List<Map<String, Object>> getOnlineOrderList(@Param("uid")Long uid, 
			@Param("page")Integer page, @Param("rows")Integer rows);

	/**
	 * 购买课程人数列表
	 * @author dengwen 
	 * 2016-11-7下午2:58:38
	 */
	public List<Map<String, Object>> findPurchaseList(@Param("courseId")Long courseId,@Param("page")int page, @Param("rows")int rows);
	
	/**
	 * 获取个人管理的客户数据
	 * @author dengwen 
	 * 2016-11-11上午10:28:19
	 */
	public List<Map<String, Object>> getClientList(@Param("uid")Long uid, @Param("param")String param, 
			@Param("staffPosition")String staffPosition,@Param("page")int page, @Param("rows")Integer rows);

	/**
	 * 获取客户课程订单列表
	 * @author dengwen 
	 * 2016-11-11下午2:12:39
	 */
	public List<Map<String, Object>> getCourseOrderList(@Param("uid")Long uid,@Param("staffPosition")String staffPosition,
			@Param("page")int page, @Param("rows")Integer rows);

	/**
	 * 获取订单详情
	 * @author dengwen 
	 * 2016-11-12上午10:18:25
	 */
	public Map<String, Object> getCourseOrderDetail(@Param("id")Long id,@Param("staffPosition")String staffPosition);

	/**
	 * 课程信息
	 * @author zoneyu 16-11-25
	 */
	public Map<String, Object> findCourseInfo(@Param("courseId")Long courseId);

	/**
	 * 订单详情
	 * @author dengwen 
	 * 2016-12-6下午2:25:41
	 */
	public Map<String, Object>  findCourseOrderDetail(@Param("id")Long id);

	/**
	 * 
	 * @author dengwen 
	 * 2016-12-7上午10:42:48
	 */
	public int verifyByCourseIds(@Param("ids")List<Long> ids);

	/**
	 * 更新课程订单状态
	 * @author dengwen 
	 * 2016-12-24下午2:43:50
	 */
	public void updateIsPayByIds(@Param("ids")List<Long> ids);
}