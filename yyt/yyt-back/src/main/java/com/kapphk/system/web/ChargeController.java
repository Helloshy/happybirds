package com.kapphk.system.web;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseController;
import com.kapphk.system.bean.Resource;
import com.kapphk.system.bean.SystemUser;
import com.kapphk.system.service.UserNumberService;
import com.kapphk.web.utils.Common;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
import com.kapphk.yyt.bean.UserNumber;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**收费管理控制器
 * Created by shy on 2016/12/10.
 */

@Controller
@RequestMapping("charge")
public class ChargeController extends BaseController{

    @Autowired
    private UserNumberService userNumberService;

    /**
     * 跳转到收费管理记录列表
     * @param model
     * @return
     */
    @RequestMapping("/manage/listPage")
    public String listPage(Model model){
        List<Resource> findRes = findRes();
        model.addAttribute("res", findRes);
        return Common.BACKGROUND_PATH + "/b2b/charge/manage/list";
    }

    /**
     * 获取列表
     * @param queryParam 查询参数 客户编号/姓名/地址
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    @RequestMapping(value="/manage/list",method= RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> list(
            @RequestParam(required=false)String queryParam,
            @RequestParam(defaultValue="1")Integer page,
            @RequestParam(defaultValue="20")Integer rows,
            @RequestParam(defaultValue="update_time desc")String sort){
        Map<String,Object> map = new HashMap<String, Object>();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("queryParam", ValidateUtils.isBlank(queryParam)? null :queryParam);
        try {
            //查询用户所在公司的客户信息
            SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
            param.put("companyId",systemUser.getCompanyId());
            PageInfo<Map<String,Object>> pageInfo = userNumberService.queryChargeByPage(param,page, rows, sort);
            map.put("rows", pageInfo.getList());
            map.put("page", page);
            map.put("records", pageInfo.getTotal());
            map.put("total", pageInfo.getPages());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 跳转现金缴费页面
     * @param model
     * @return
     * */

    @RequestMapping(value="/manage/editPage",method = RequestMethod.GET)
    public String editPage2Adjust(Model model,String id){
        try {
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id",id);
            PageInfo<Map<String,Object>> pageInfo = userNumberService.queryChargeByPage(param,1,1,null);
            if(!ValidateUtils.isEmpty(pageInfo)&&null!=pageInfo.getList()&&pageInfo.getList().size()>0){
                Map<String,Object> resultMap = pageInfo.getList().get(0);
                model.addAttribute("resultMap",resultMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("mgs", Result.MSG.ERROR);
        }
        return Common.BACKGROUND_PATH + "/b2b/charge/manage/edit";
    }



     /* 跳转到确认缴费页面
     *
     * @param id
     * @return*/

    @RequestMapping(value="/manage/payment")
    public String  toPaymentPage(Model model, @RequestParam("id") Long id,
                                              @RequestParam("payMethod") Integer payMethod,
                                              @RequestParam("amount") BigDecimal amount ,
                                              @RequestParam("createTime") String createTime,
                                              @RequestParam("address") String address,
                                              @RequestParam("realName") String realName){
        Result result = new Result();
        try {
            UserNumber userNumber = userNumberService.findById(id);
            if (null!=userNumber && null!=amount&&amount.compareTo(new BigDecimal(0))==1){
                Map<String,Object> resultMap = new HashMap<String, Object>();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(createTime);
                resultMap.put("id",id);
                resultMap.put("payMethod",payMethod);
                resultMap.put("amount",amount);
                resultMap.put("createTime",date);
                resultMap.put("address",address);
                resultMap.put("realName",realName);
                model.addAttribute("resultMap",resultMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(Contents.ERROR);
            result.setMsg("系统错误");
        }
        model.addAttribute("result",result);
        return Common.BACKGROUND_PATH + "/b2b/charge/manage/payment";
    }


     /*
     * 确认缴费
     * @param
     * @return*/

    @RequestMapping(value="/manage/confirmPayment")
    public @ResponseBody Object confirmPayment(
            @RequestParam("id") Long id,
            @RequestParam("payMethod") Integer payMethod,
            @RequestParam("amount") BigDecimal amount ,
            @RequestParam("createTime") Date createTime){
        Result result = new Result();
        try {
            if (null != id && id>0 && null!=payMethod&&null!=amount&&amount.compareTo(new BigDecimal(0))==1&&null!=createTime ){
                SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
                /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(createTime);*/
                result = userNumberService.confirmPayment(id,payMethod,amount,createTime,systemUser.getId(),result);
            }else {
                result.setError(Result.MSG.PARAMS_ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(Contents.ERROR);
            result.setMsg("系统错误");
        }
        return result;
    }



    /*====================record=====================*/


    /**
     * 跳转到缴费记录列表
     * @param model
     * @return
     */
    @RequestMapping("/record/listPage")
    public String listPage2Record(Model model){
        List<Resource> findRes = findRes();
        model.addAttribute("res", findRes);
        return Common.BACKGROUND_PATH + "/b2b/charge/record/list";
    }

    /**
     * 获取列表
     * @param queryParam 查询参数 客户编号/姓名/地址
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    @RequestMapping(value="/record/list",method= RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> list2Record(
            @RequestParam(required=false)String queryParam,
            @RequestParam(defaultValue="1")Integer page,
            @RequestParam(defaultValue="20")Integer rows,
            @RequestParam(defaultValue="createTime desc")String sort){
        Map<String,Object> map = new HashMap<String, Object>();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("queryParam", ValidateUtils.isBlank(queryParam)? null :queryParam);
        try {
            //查询用户所在公司的客户信息
            SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
            param.put("companyId",systemUser.getCompanyId());
            PageInfo<Map<String,Object>> pageInfo = userNumberService.queryRecordByPage(param,page, rows, sort);
            map.put("rows", pageInfo.getList());
            map.put("page", page);
            map.put("records", pageInfo.getTotal());
            map.put("total", pageInfo.getPages());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

     /*====================wirteOff 冲销=====================*/



    /**
     * 跳转到缴费冲销列表
     * @param model
     * @return
     */
    @RequestMapping("/writeOff/listPage")
    public String listPage2WriteOff(Model model){
        List<Resource> findRes = findRes();
        model.addAttribute("res", findRes);
        return Common.BACKGROUND_PATH + "/b2b/charge/writeOff/list";
    }

    /**
     * 获取列表
     * @param queryParam 查询参数 客户编号/姓名/地址
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    @RequestMapping(value="/writeOff/list",method= RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> list2WriteOff(
            @RequestParam(required=false)String queryParam,
            @RequestParam(defaultValue="1")Integer page,
            @RequestParam(defaultValue="20")Integer rows,
            @RequestParam(defaultValue="createTime desc")String sort){
        Map<String,Object> map = new HashMap<String, Object>();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("queryParam", ValidateUtils.isBlank(queryParam)? null :queryParam);
        try {
            //查询用户所在公司的客户信息
            SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
            param.put("companyId",systemUser.getCompanyId());
            PageInfo<Map<String,Object>> pageInfo = userNumberService.queryChargeByPage(param,page, rows, sort);
            map.put("rows", pageInfo.getList());
            map.put("page", page);
            map.put("records", pageInfo.getTotal());
            map.put("total", pageInfo.getPages());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 跳转缴费冲销页面
     * @id    用户燃气编号id
     * @param model
     * @return
     * */

    @RequestMapping(value="/wirteOff/editPage",method = RequestMethod.GET)
    public String editPage2WriteOff(Model model,String id){
        try {
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id",id);
            PageInfo<Map<String,Object>> pageInfo = userNumberService.queryChargeByPage(param,1,1,null);
            if(!ValidateUtils.isEmpty(pageInfo)&&null!=pageInfo.getList()&&pageInfo.getList().size()>0){
                Map<String,Object> resultMap = pageInfo.getList().get(0);
                model.addAttribute("resultMap",resultMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("mgs", Result.MSG.ERROR);
        }
        return Common.BACKGROUND_PATH + "/b2b/charge/writeOff/edit";
    }


     /*
     * 冲销
     * @param
     * @return*/

    @RequestMapping(value="/writeOff/update")
    public @ResponseBody Object update(
            @RequestParam("id") Long id,
            @RequestParam("amount") BigDecimal amount ,
            @RequestParam("updateTime") String updateTime){
        Result result = new Result();
        try {
            if (null != id && id>0 &&null!=amount&&amount.compareTo(new BigDecimal(0))==1&&null!=updateTime ){
                SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(updateTime);
                result = userNumberService.updateForWriteOff(id,amount,date,systemUser.getId(),result);
            }else {
                result.setError(Result.MSG.PARAMS_ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(Contents.ERROR);
            result.setMsg("系统错误");
        }
        return result;
    }


    /*===============repair=========================*/

    /**
     * 跳转到票据补打列表
     * @param model
     * @return
     */
    @RequestMapping("/repair/listPage")
    public String listPage2Repair(Model model){
        List<Resource> findRes = findRes();
        model.addAttribute("res", findRes);
        return Common.BACKGROUND_PATH + "/b2b/charge/repair/list";
    }

    /**
     * 获取列表
     * @param queryParam 查询参数 客户编号/姓名/地址
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    @RequestMapping(value="/repair/list",method= RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> list2Repair(
            @RequestParam(required=false)String queryParam,
            @RequestParam(defaultValue="1")Integer page,
            @RequestParam(defaultValue="20")Integer rows,
            @RequestParam(defaultValue="createTime desc")String sort){
        Map<String,Object> map = new HashMap<String, Object>();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("queryParam", ValidateUtils.isBlank(queryParam)? null :queryParam);
        try {
            //查询用户所在公司的客户信息
            SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
            param.put("companyId",systemUser.getCompanyId());
            PageInfo<Map<String,Object>> pageInfo = userNumberService.queryRecordByPage(param,page, rows, sort);
            map.put("rows", pageInfo.getList());
            map.put("page", page);
            map.put("records", pageInfo.getTotal());
            map.put("total", pageInfo.getPages());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
