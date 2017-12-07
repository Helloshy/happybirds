package com.kapphk.web.controller.inter.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.jpush.api.utils.Jgpush;

import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.controller.BaseController;
import com.kapphk.web.service.inter.imethod.user.InterfaceBeanUserService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 用户控制层
 * @author zoneyu 16-9-30
 */
@RestController
@RequestMapping("/user/")
public class InterfaceBeanUserController extends BaseController<BeanUser, Long> {
	
	@Autowired
	private InterfaceBeanUserService service ;
	 
	 /**
	  * 登录
	  * @author zoneyu 16-9-30
	  */
	 @RequestMapping("sign.do")
	 public Result sign(BeanUser user,HttpServletRequest request){
		 Result rs = new Result() ;
		 try {
			 rs = service.sign(user,request,rs) ;
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus()) ;
			 rs.setMsg("出现错误") ;
		 }
		 return rs ;
	 }
	 
	 /**
	  * 忘记密码
	  * @author zoneyu 16-9-30
	  */
	 @RequestMapping("resetPassword.do")
	 public Result resetPassword(String userName,String pwd,String newPwd,String code,Integer type){
		 Result rs = new Result() ;
		 try {
			 rs = service.resetPassword(userName,pwd,newPwd,code,type,rs) ;
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus()) ;
			 rs.setMsg("出现错误") ;
		 }
		 return rs ;
	 }
	 
	 /**
	  * 获取短信验证码
	  * @author zoneyu 16-9-30
	  */
	 @RequestMapping("insertCodeAndGetCode.do")
	 public Result insertCodeAndGetCode(String phone,Integer type,HttpServletRequest request){
		 Result rs = new Result() ;
		 try {
			 rs = service.insertCodeAndGetCode(phone,type,request,rs) ;
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus()) ;
			 rs.setMsg("出现错误") ;
		 }
		 return rs ;
	 }
	 
	 /**
	  * 注册
	  * @author zoneyu 16-9-30
	  */
	 @RequestMapping("register.do")
	 public Result register(BeanUser user,String code,String dnPosition,String personalPosition){
		 Result rs = new Result() ;
		 try {
			 rs = service.register(user,code,dnPosition,personalPosition,rs) ;
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus()) ;
			 rs.setMsg("出现错误") ;
		 }
		 return rs ;
	 }
	 
	 /**
	  * 获取蓝币跟红币
	  * @author zoneyu 16-10-11
	  */
	 @RequestMapping("getCurency.do")
	 public Result getCurency(Long uid){
		 Result rs = new Result() ;
		 try {
			 rs = service.getCurency(uid,rs) ;
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus()) ;
			 rs.setMsg("出现错误") ;
		 }
		 return rs ;
	 }
	 
	 /**
	  * 获取个人信息
	  * @author dengwen 
	  * 2016-10-13下午3:02:12
	  */
	 @RequestMapping("getUserInfo.do")
	 public Result getUserInfo(Long id,String date){
		 Result rs = new Result() ;
		 try {
			 rs = service.getUserInfo(id,date,rs) ;
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus()) ;
			 rs.setMsg("出现错误") ;
		 }
		 return rs ;
	 }
	 
	 /**
	  * 更新个人信息
	  * @author dengwen 
	  * 2016-10-13下午3:02:12
	  */
	 @RequestMapping("updateUser.do")
	 public Result updateUser(BeanUser bean,String grPosition,String dnPosition){
		 Result rs = new Result() ;
		 try {
			 rs = service.updateUser(bean,grPosition,dnPosition,rs) ;
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus()) ;
			 rs.setMsg("出现错误") ;
		 }
		 return rs ;
	 }
	 
	 /**
	  * 获取动能家人
	  * @author dengwen 
	  * 2016-10-13下午4:59:24
	  */
	 @RequestMapping("getRelationList.do")
	 public Result getRelationList(Long id){
		 Result rs = new Result() ;
		 try {
			 rs = service.getRelationList(id,rs) ;
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus()) ;
			 rs.setMsg("出现错误") ;
		 }
		 return rs ;
	 }
	 
	 /**
	  * 更新动能贵人
	  * @author dengwen 
	  * 2016-10-13下午4:59:24
	  */
	 @RequestMapping("updateRelation.do")
	 public Result updateRelation(Long id,String inviteCode){
		 Result rs = new Result() ;
		 try {
			 rs = service.updateRelation(id,inviteCode,rs) ;
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus()) ;
			 rs.setMsg("出现错误") ;
		 }
		 return rs ;
	 }
	 
	 /**
	  * 省市区
	  * @author zoneyu 16-11-2
	  */
	 @RequestMapping("getAddress.do")
	 public Result getAddress(Integer type,String pid){
		 Result rs = new Result() ;
		 try {
			 rs = service.getAddress(type,pid,rs) ;
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus()) ;
			 rs.setMsg("出现错误") ;
		 }
		 return rs ;
	 }
	 
	 /**
	  * 省市区(全部数据)
	  * @author zoneyu 16-11-7
	  */
	 @RequestMapping("getAddressList.do")
	 public Result getAddressList(){
		 Result rs = new Result() ;
		 try {
			 rs = service.getAddressList(rs) ;
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus()) ;
			 rs.setMsg("出现错误") ;
		 }
		 return rs ;
	 }
	 
	 /**
	  * 查询下级
	  * @author zoneyu 16-11-10
	  */
	 @RequestMapping("searchChildList.do")
	 public Result searchchildList(Long uid,Integer grade,Integer page){
		 Result rs = new Result() ;
		 try {
			 rs = service.searchchildList(uid,grade,page,rs) ;
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus()) ;
			 rs.setMsg("出现错误") ;
		 }
		 return rs ;
	 }
	 
	 /**
	  * 我的分佣
	  * @author dengwen
	  */
	 @RequestMapping("searchCentCommission.do")
	 public Result searchCentCommission(Long uid){
		 Result rs = new Result() ;
		 try {
			 rs = service.searchCentCommission(uid,rs) ;
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus()) ;
			 rs.setMsg("出现错误") ;
		 }
		 return rs ;
	 }
	 
	 /**
	  * 分佣明细
	  * @author dengwen
	  */
	 @RequestMapping("searchCentCommissionDetail.do")
	 public Result searchCentCommissionDetail(Long uid,String type, Integer page){
		 Result rs = new Result();
		 try {
			 rs = service.searchCentCommissionDetail(uid,type,page,rs) ;
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus()) ;
			 rs.setMsg("出现错误") ;
		 }
		 return rs ;
	 }
	 
	 /**
	  * 转账
	  * @author dengwen
	  */
	 @RequestMapping("saveTransferAccounts.do")
	 public Result saveTransferAccounts(String userName,Long currency, Long uid, String pwd){
		 Result rs = new Result();
		 try {
			 rs = service.saveTransferAccounts(userName,currency,uid,pwd,rs) ;
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus());
			 rs.setMsg("出现错误") ;
		 }
		 return rs ;
	 }
	
	 /**
	  * 分享注册
	  * @author dengwen 
	  * 2016-11-29下午2:26:43
	  */
	 @RequestMapping("registerShare.do")
	 public ModelAndView registerShare(Long id,HttpServletRequest request){
			try {
				request.setAttribute("inviteCode", service.registerShare(id));
			} catch (Exception e) {
				e.printStackTrace();
			}
		return new ModelAndView("/dongneng/html/share-1.jsp") ;
	}
	 
	 /**
	  * 获取设置内容
	  * @author zoenyu 16-12-2
	  */
	 @RequestMapping("getSetting.do")
	 public Result getSetting(String itemName){
		 Result rs = new Result();
		 try {
			 rs = service.getSetting(itemName,rs) ;
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus());
			 rs.setMsg("出现错误") ;
		 }
		 return rs ;
	 }
	 
	 /**
	  * 评测结果
	  * @author zoenyu 16-12-6
	  */
	 @RequestMapping("getResult.do")
	 public Result getResult(String answers){
		 Result rs = new Result();
		 try {
			 rs = service.getResult(answers,rs) ;
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus());
			 rs.setMsg("出现错误") ;
		 }
		 return rs ;
	 }
	 
	 /**
	  * 推荐课程
	  * @author zoenyu 16-12-6
	  */
	 @RequestMapping("getCourse.do")
	 public Result getCourse(String result){
		 Result rs = new Result();
		 try {
			 rs = service.getCourse(result,rs) ;
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus());
			 rs.setMsg("出现错误") ;
		 }
		 return rs ;
	 }
	 
	 /**
	  * 支付控制
	  * @author zoenyu 16-12-10
	  */
	 @RequestMapping("getPaySet.do")
	 public Result getPaySet(){
		 Result rs = new Result();
		 try {
			 rs = service.getPaySet(rs) ;
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus());
			 rs.setMsg("出现错误") ;
		 }
		 return rs ;
	 }
	 
	 /**
	  * 获取版本号
	  * @author zoneyu 16-9-21
	  */
	 @RequestMapping("getVersion.do")
	 public Result getVersion(){
		 Result rs = new Result() ;
		 try {
			 rs = service.getVersion(rs) ;
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus()) ;
			 rs.setMsg("出现错误") ;
		 }
		 return rs; 
	 }
	 
	 /**
	  * 推送验证
	  * @author dengwen 
	  * 2016-12-12下午6:24:33
	  */
	 @RequestMapping("push.do")
	 public Result push(String serialNumber,Long uid){
		 Result rs = new Result() ;
		 try {
			if(!ValidateUtils.isBlank(serialNumber) && !ValidateUtils.isBlank(uid)){
				Map<String, Object> map = new HashMap<String,Object>() ;
			    map.put("serialNumber", serialNumber);
			    map.put("type", "2");
				String[] ids = new String[]{"alias"+uid};
			    Jgpush.sendMessage_simple("",ids,map);
			}else{
				rs.setStatus(MSG.ERROR.getStatus()) ;
				 rs.setMsg("参数有误") ;
			}
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus()) ;
			 rs.setMsg("出现错误") ;
		 }
		 return rs; 
	 }
	 
	 /**
	  * 更新身份
	  * @author dengwen 
	  * 2016-12-16下午4:39:40
	  */
	 @RequestMapping("usp.do")
	 public Result usp(){
		 Result rs = new Result() ;
		 try {
			 rs = service.usp(rs);
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus()) ;
			 rs.setMsg("出现错误") ;
		 }
		 return rs; 
	 }
	 
}
