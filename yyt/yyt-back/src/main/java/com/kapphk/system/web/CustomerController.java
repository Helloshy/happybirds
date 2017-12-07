package com.kapphk.system.web;

import com.github.pagehelper.PageInfo;
import com.kapphk.anotation.SystemLog;
import com.kapphk.base.persistence.BaseController;
import com.kapphk.system.bean.Resource;
import com.kapphk.system.bean.SystemUser;
import com.kapphk.system.service.CommunityService;
import com.kapphk.system.service.CompanyService;
import com.kapphk.system.service.CustomerService;
import com.kapphk.system.service.UserNumberService;
import com.kapphk.web.utils.Common;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
import com.kapphk.yyt.bean.Community;
import com.kapphk.yyt.bean.Company;
import com.kapphk.yyt.bean.UserNumber;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**客户管理控制器
 * Created by shy on 2016/12/7.
 */

@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController{

    @Autowired
    private UserNumberService  userNumberService;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private CompanyService companyService;


    /**
     * 跳转到客户信息列表
     * @param model
     * @return
     */
    @RequestMapping("/list/listPage")
    public String listPage(Model model){
        List<Resource> findRes = findRes();
        model.addAttribute("res", findRes);
        return Common.BACKGROUND_PATH + "/b2b/customer/list";
    }

    /**
     * 获取列表
     * @param queryParam 查询参数 客户编号/姓名/地址
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    @RequestMapping(value="/list/list",method= RequestMethod.GET)
    public @ResponseBody Map<String,Object> list(
            @RequestParam(required=false)String queryParam,
            @RequestParam(defaultValue="1")Integer page,
            @RequestParam(defaultValue="20")Integer rows,
            @RequestParam(defaultValue = "1")Integer state,
            @RequestParam(defaultValue="update_time desc")String sort){
        Map<String,Object> map = new HashMap<String, Object>();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("queryParam", ValidateUtils.isBlank(queryParam)? null :queryParam);
        param.put("state",state);
        try {
            //查询用户所在公司的客户信息
            SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
            param.put("companyId",systemUser.getCompanyId());
            PageInfo<Map<String,Object>> pageInfo = userNumberService.queryByPage(param,page, rows, sort);
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
     * 跳转到编辑页面
     * @param model
     * @return
     */
    @RequestMapping(value="/list/editPage",method = RequestMethod.GET)
    public String editPage(Model model,String id){
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("state",1);
            param.put("id",id);
            PageInfo<Map<String,Object>> pageInfo = userNumberService.queryByPage(param,1,1,null);
            if(!ValidateUtils.isEmpty(pageInfo)&&null!=pageInfo.getList()&&pageInfo.getList().size()>0){
                Map<String,Object> resultMap = pageInfo.getList().get(0);
                Long companyId = (Long) resultMap.get("company_id");
                //UserNumber userNumber =  userNumberService.findById(Long.parseLong(id));
                //查询用户所在公司的所有小区
                List<Community> communities = communityService.findByCompanyId(companyId);
                model.addAttribute("resultMap",resultMap);
                model.addAttribute("communities",communities);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Common.BACKGROUND_PATH + "/b2b/customer/edit";
    }

    /**
     * 批量删除(逻辑删除)
     * @param ids
     * @return
     */
    @SystemLog(description="客户列表信息-删除")
    @RequestMapping(value="/list/delete",method=RequestMethod.POST)
    public @ResponseBody
    Result delete(String ids){
        Result result = new Result();
        try {
            result = userNumberService.delete(ids, result);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(Contents.ERROR);
            result.setMsg("系统错误");
        }
        return result;
    }

    /**
     * 更新
     * @param
     * @return
     */
    @RequestMapping(value="/list/update",method=RequestMethod.POST)
    public @ResponseBody Object updateData(UserNumber userNumber){
        Result result = new Result();
        try {
           result =  userNumberService.updateData(userNumber,result);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(Contents.ERROR);
            result.setMsg("系统错误");
        }
        return result;
    }

    /**
     * 跳转到新增页面
     * @return
     */

    @RequestMapping(value="/list/addPage",method=RequestMethod.GET)
    public String addPage(Model model){
        //查询用户所在公司名以及所负责的小区
        try {
            SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
            Long companyId = systemUser.getCompanyId();
            List<Community> communities = communityService.findByCompanyId(companyId);
            Company company = companyService.findById(companyId);
            model.addAttribute("communities",communities);
            model.addAttribute("company",company);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", Result.MSG.ERROR);
        }
        return Common.BACKGROUND_PATH + "/b2b/customer/add";
    }


    /**
     * 跳转到点火信息列表
     * @param model
     * @return
     */
    @RequestMapping("/fire/listPage")
    public String listPage2Fire(Model model){
        List<Resource> findRes = findRes();
        model.addAttribute("res", findRes);
        return Common.BACKGROUND_PATH + "/b2b/customer/fire/list";
    }


    /**
     * 跳转到编辑页面
     * @param model
     * @return
     */
    @RequestMapping(value="/fire/editPage",method = RequestMethod.GET)
    public String editPage2Fire(Model model,String id){
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("state",1);
            PageInfo<Map<String,Object>> pageInfo = userNumberService.queryByPage(param,1,1,null);
            if(!ValidateUtils.isEmpty(pageInfo)&&null!=pageInfo.getList()&&pageInfo.getList().size()>0){
                Map<String,Object> resultMap = pageInfo.getList().get(0);
                Long companyId = (Long) resultMap.get("company_id");
                //UserNumber userNumber =  userNumberService.findById(Long.parseLong(id));
                //查询用户所在公司的所有小区
                List<Community> communities = communityService.findByCompanyId(companyId);
                model.addAttribute("resultMap",resultMap);
                model.addAttribute("communities",communities);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Common.BACKGROUND_PATH + "/b2b/customer/fire/edit";
    }





    /**
     * 跳转到过户信息列表
     * @param model
     * @return
     */
    @RequestMapping("/exchange/listPage")
    public String listPage2Exchange(Model model){
        List<Resource> findRes = findRes();
        model.addAttribute("res", findRes);
        return Common.BACKGROUND_PATH + "/b2b/customer/exchange/list";
    }


    /**
     * 跳转到编辑页面
     * @param model
     * @return
     */
    @RequestMapping(value="exchange/editPage",method = RequestMethod.GET)
    public String editPage2Exchange(Model model,String id){
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("state",1);
            PageInfo<Map<String,Object>> pageInfo = userNumberService.queryByPage(param,1,1,null);
            if(!ValidateUtils.isEmpty(pageInfo)&&null!=pageInfo.getList()&&pageInfo.getList().size()>0){
                Map<String,Object> resultMap = pageInfo.getList().get(0);
                Long companyId = (Long) resultMap.get("company_id");
                //UserNumber userNumber =  userNumberService.findById(Long.parseLong(id));
                //查询用户所在公司的所有小区
                List<Community> communities = communityService.findByCompanyId(companyId);
                model.addAttribute("resultMap",resultMap);
                model.addAttribute("communities",communities);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Common.BACKGROUND_PATH + "/b2b/customer/exchange/edit";
    }

}
