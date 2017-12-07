package com.kapphk.web.service.inter.imethod.user;

import javax.servlet.http.HttpServletRequest;

import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.service.BaseService;
import com.kapphk.web.utils.Result;

public interface InterfaceBeanUserService extends BaseService<BeanUser, Long> {

	/**
	  * 登录
	  * @author zoneyu 16-9-30
	  */
	public Result sign(BeanUser user, HttpServletRequest request, Result rs) throws Exception ;

	/**
	  * 忘记密码
	  * @author zoneyu 16-9-30
	  */
	public Result resetPassword(String userName, String pwd, String newPwd, String code, Integer type, Result rs) throws Exception ;

	/**
	  * 获取短信验证码
	  * @author zoneyu 16-9-30
	  */
	public Result insertCodeAndGetCode(String phone, Integer type, HttpServletRequest request, Result rs) throws Exception ;

	 /**
	  * 注册
	  * @author zoneyu 16-9-30
	  */
	public Result register(BeanUser user, String code, String dnPosition, String personalPosition, Result rs) throws Exception ;

	/**
	  * 获取蓝币跟红币
	  * @author zoneyu 16-10-11
	  */
	public Result getCurency(Long uid, Result rs) throws Exception ;

	/**
	 * 获取个人信息
	 * @author dengwen 
	 * 2016-10-13下午3:02:32
	 */
	public Result getUserInfo(Long id ,String date, Result rs) throws Exception;

	/**
	 * 更新个人信息
	 * @author dengwen 
	 * 2016-10-13下午3:53:07
	 */
	public Result updateUser(BeanUser bean, String grPosition,String dnPosition, Result rs) throws Exception;

	/**
	 * 获取动能家人
	 * @author dengwen 
	 * 2016-10-13下午5:05:32
	 */
	public Result getRelationList(Long id, Result rs) throws Exception;

	/**
	 * 更新动能贵人
	 * @author dengwen 
	 * 2016-10-13下午6:13:55
	 */
	public Result updateRelation(Long id, String inviteCode, Result rs) throws Exception;

	/**
	  * 省市区
	  * @author zoneyu 16-11-2
	  */
	public Result getAddress(Integer type, String pid, Result rs) throws Exception;

	/**
	  * 省市区(全部数据)
	  * @author zoneyu 16-11-7
	  */
	public Result getAddressList(Result rs) throws Exception;

	/**
	  * 注册--分享后的注册
	  * @author zoneyu 16-11-9
	  */
	public Result registerWithCode(BeanUser user, String code, Result rs) throws Exception;

	 /**
	  * 查询下级
	  * @author zoneyu 16-11-10
	  */
	public Result searchchildList(Long uid, Integer grade,Integer page, Result rs) throws Exception;

	/**
	 * 我的分佣
	 * @author dengwen 
	 * 2016-11-23上午9:55:53
	 */
	public Result searchCentCommission(Long uid, Result rs) throws Exception;

	/**
	 * 分佣明细
	 * @author dengwen 
	 * 2016-11-23上午11:41:57
	 */
	public Result searchCentCommissionDetail(Long uid, String type, Integer page, Result rs) throws Exception;

	/**
	 * 转账
	 * @author dengwen 
	 * 2016-11-24上午9:35:46
	 */
	public Result saveTransferAccounts(String userName, Long currency,
			Long uid, String pwd, Result rs) throws Exception;

	/**
	 * 分享注册
	 * @author dengwen 
	 * 2016-11-29下午2:27:00
	 */
	public String registerShare(Long id) throws Exception;

	/**
	  * 获取设置内容
	  * @author zoenyu 16-12-2
	  */
	public Result getSetting(String itemName, Result rs) throws Exception;

	/**
	  * 评测结果
	  * @author zoenyu 16-12-6
	  */
	public Result getResult(String answers, Result rs) throws Exception;

	/**
	  * 推荐课程
	  * @author zoenyu 16-12-6
	  */
	public Result getCourse(String result, Result rs) throws Exception;

	/**
	  * 支付控制
	  * @author zoenyu 16-12-10
	  */
	public Result getPaySet(Result rs) throws Exception;

	/**
	 * 获取版本号
	 * @author dengwen 
	 * 2016-12-12下午3:29:41
	 */
	public Result getVersion(Result rs) throws Exception;

	public Result usp(Result rs) throws Exception;

}
