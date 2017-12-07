package com.kapphk.system.web;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseController;
import com.kapphk.system.bean.Resource;
import com.kapphk.system.bean.SystemUser;
import com.kapphk.system.service.UserOrderService;
import com.kapphk.web.utils.Common;
import com.kapphk.web.utils.ValidateUtils;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报表模块控制器
 * Created by Shy on 2016/12/12
 * Since 1.0
 */

@Controller
@RequestMapping("chart")
public class ChartController extends BaseController {
    @Autowired
    private UserOrderService userOrderService;



    /**
     * 跳转到缴费记录列表
     * @param model
     * @return
     */
    @RequestMapping("/payment/listPage")
    public String listPage(Model model){
        List<Resource> findRes = findRes();
        model.addAttribute("res", findRes);
        return Common.BACKGROUND_PATH + "/b2b/chart/payment/list";
    }

    /**
     * 获取列表
     * @param queryParam 查询参数 开始时间/结束时间
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value="/payment/list",method= RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> list(
            @RequestParam(required=false)String queryParam,
            @RequestParam(defaultValue="1")Integer page,
            @RequestParam(defaultValue="20")Integer rows,
            @RequestParam(value = "beginDate",required = false)String beginDate,
            @RequestParam(value = "endDate",required = false)String endDate){
        Map<String,Object> map = new HashMap<String, Object>();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("queryParam", ValidateUtils.isBlank(queryParam)? null :queryParam);
        Date date1 = null;
        Date date2 = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (!ValidateUtils.isBlank(beginDate)){
            date1 = sdf.parse(beginDate);
            }
            if (!ValidateUtils.isBlank(endDate)) {
             date2 = sdf.parse(endDate);
            }
            //查询用户所在公司的客户信息
            SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
            param.put("companyId",systemUser.getCompanyId());
            param.put("beginDate",date1);
            param.put("endDate",date2);
            PageInfo<Map<String,Object>> pageInfo = userOrderService.queryPaymentByPage(param,page, rows,null);
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
     * 导出缴费报表
     * @author zoneyu 16-7-29
     */
    @RequestMapping("/payment/export")
    public void paymentExport(@RequestParam(defaultValue="1")Integer page,
                               @RequestParam(defaultValue="20")Integer rows,
                               @RequestParam(value = "beginDate",required = false)String beginDate,
                               @RequestParam(value = "endDate",required = false)String endDate,
            HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<String, Object>();
        Map<String,Object> param = new HashMap<String,Object>();
        Date date1 = null;
        Date date2 = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (!ValidateUtils.isBlank(beginDate)){
                date1 = sdf.parse(beginDate);
            }
            if (!ValidateUtils.isBlank(endDate)) {
                date2 = sdf.parse(endDate);
            }
            //查询用户所在公司的客户信息
            SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
            param.put("companyId",systemUser.getCompanyId());
            param.put("beginDate",date1);
            param.put("endDate",date2);
            userOrderService.exportPayment(param,null,null,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

   /* =================gasCount========================*/

    /**
     * 跳转到用气统计报表列表
     * @param model
     * @return
     */
    @RequestMapping("/gasCount/listPage")
    public String list2GasCount(Model model){
        List<Resource> findRes = findRes();
        model.addAttribute("res", findRes);
        return Common.BACKGROUND_PATH + "/b2b/chart/gasCount/list";
    }

    /**
     * 获取列表
     * @param queryParam
     * @param page
     * @param rows
     * @param beginDate
     * @param endDate
     * @return
     */
    @RequestMapping(value="/gasCount/list",method= RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> list2GasCount(
            @RequestParam(required=false)String queryParam,
            @RequestParam(defaultValue="1")Integer page,
            @RequestParam(defaultValue="20")Integer rows,
            @RequestParam(value = "beginDate",required = false)String beginDate,
            @RequestParam(value = "endDate",required = false)String endDate){
        Map<String,Object> map = new HashMap<String, Object>();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("queryParam", ValidateUtils.isBlank(queryParam)? null :queryParam);
        Date date1 = null;
        Date date2 = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (!ValidateUtils.isBlank(beginDate)){
                date1 = sdf.parse(beginDate);
            }
            if (!ValidateUtils.isBlank(endDate)) {
                date2 = sdf.parse(endDate);
            }
            //查询用户所在公司的客户信息
            SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
            param.put("companyId",systemUser.getCompanyId());
            param.put("beginDate",date1);
            param.put("endDate",date2);
            PageInfo<Map<String,Object>> pageInfo = userOrderService.queryGasCountByPage(param,page, rows, null);
            map.put("rows", pageInfo.getList());
            map.put("page", page);
            map.put("records", pageInfo.getTotal());
            map.put("total", pageInfo.getPages());
            if (ValidateUtils.isBlank(beginDate)&&ValidateUtils.isBlank(endDate)){
                map.put("queryTime","");
            }else if (ValidateUtils.isBlank(beginDate)&&!ValidateUtils.isBlank(endDate)){
                map.put("queryTime",endDate);
            }else if (!ValidateUtils.isBlank(beginDate)&&ValidateUtils.isBlank(endDate)){
                map.put("queryTime",beginDate);
            }else {
                map.put("queryTime",beginDate+"-"+endDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 导出用气统计报表
     *
     */
    @RequestMapping("/gasCount/export")
    public void gasCountExport(@RequestParam(defaultValue="1")Integer page,
                                   @RequestParam(defaultValue="20")Integer rows,
                                   @RequestParam(value = "beginDate",required = false)String beginDate,
                                   @RequestParam(value = "endDate",required = false)String endDate,
                                   HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        Date date1 = null;
        Date date2 = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (!ValidateUtils.isBlank(beginDate)) {
                date1 = sdf.parse(beginDate);
            }
            if (!ValidateUtils.isBlank(endDate)) {
                date2 = sdf.parse(endDate);
            }
            //查询用户所在公司的客户信息
            SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
            param.put("companyId", systemUser.getCompanyId());
            param.put("beginDate", date1);
            param.put("endDate", date2);
            userOrderService.exportGasCount(param,null,null, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*==================paymentCount==================*/


    /**
     * 跳转到缴费统计报表列表
     * @param model
     * @return
     */
    @RequestMapping("/paymentCount/listPage")
    public String list2PaymentCount(Model model){
        List<Resource> findRes = findRes();
        model.addAttribute("res", findRes);
        return Common.BACKGROUND_PATH + "/b2b/chart/paymentCount/list";
    }

    /**
     * 获取列表
     * @param queryParam
     * @param page
     * @param rows
     * @param beginDate
     * @param endDate
     * @return
     */
    @RequestMapping(value="/paymentCount/list",method= RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> list2PaymentCount(
            @RequestParam(required=false)String queryParam,
            @RequestParam(defaultValue="1")Integer page,
            @RequestParam(defaultValue="20")Integer rows,
            @RequestParam(value = "beginDate",required = false)String beginDate,
            @RequestParam(value = "endDate",required = false)String endDate){
        Map<String,Object> map = new HashMap<String, Object>();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("queryParam", ValidateUtils.isBlank(queryParam)? null :queryParam);
        Date date1 = null;
        Date date2 = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (!ValidateUtils.isBlank(beginDate)){
                date1 = sdf.parse(beginDate);
            }
            if (!ValidateUtils.isBlank(endDate)) {
                date2 = sdf.parse(endDate);
            }
            //查询用户所在公司的客户信息
            SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
            param.put("companyId",systemUser.getCompanyId());
            param.put("beginDate",date1);
            param.put("endDate",date2);
            PageInfo<Map<String,Object>> pageInfo = userOrderService.queryPaymentCountByPage(param,page, rows, null);
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
     * 导出小区用户数统计报表
     *
     */
    @RequestMapping("/paymentCount/export")
    public void paymentCountExport(@RequestParam(defaultValue="1")Integer page,
                                    @RequestParam(defaultValue="20")Integer rows,
                                    @RequestParam(value = "beginDate",required = false)String beginDate,
                                    @RequestParam(value = "endDate",required = false)String endDate,
                                    HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        Date date1 = null;
        Date date2 = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (!ValidateUtils.isBlank(beginDate)) {
                date1 = sdf.parse(beginDate);
            }
            if (!ValidateUtils.isBlank(endDate)) {
                date2 = sdf.parse(endDate);
            }
            //查询用户所在公司的客户信息
            SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
            param.put("companyId", systemUser.getCompanyId());
            param.put("beginDate", date1);
            param.put("endDate", date2);
            userOrderService.exportPaymentCount(param,null,null, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*=================cellUserCount=======================*/


    /**
     * 跳转到小区用户数统计报表列表
     * @param model
     * @return
     */
    @RequestMapping("/cellUserCount/listPage")
    public String list2CellUserCount(Model model){
        List<Resource> findRes = findRes();
        model.addAttribute("res", findRes);
        return Common.BACKGROUND_PATH + "/b2b/chart/cellUserCount/list";
    }

    /**
     * 获取列表
     * @param queryParam 查询参数 开始时间/结束时间
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value="/cellUserCount/list",method= RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> list2CellUserCount(
            @RequestParam(required=false)String queryParam,
            @RequestParam(defaultValue="1")Integer page,
            @RequestParam(defaultValue="20")Integer rows,
            @RequestParam(value = "beginDate",required = false)String beginDate,
            @RequestParam(value = "endDate",required = false)String endDate){
        Map<String,Object> map = new HashMap<String, Object>();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("queryParam", ValidateUtils.isBlank(queryParam)? null :queryParam);
        Date date1 = null;
        Date date2 = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (!ValidateUtils.isBlank(beginDate)){
                date1 = sdf.parse(beginDate);
            }
            if (!ValidateUtils.isBlank(endDate)) {
                date2 = sdf.parse(endDate);
            }
            //查询用户所在公司的客户信息
            SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
            param.put("companyId",systemUser.getCompanyId());
            param.put("beginDate",date1);
            param.put("endDate",date2);
            PageInfo<Map<String,Object>> pageInfo = userOrderService.queryCellUserCountByPage(param,page, rows, null);
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
     * 导出小区用户数统计报表
     *
     */
    @RequestMapping("/cellUserCount/export")
    public void cellUserCountExport(@RequestParam(defaultValue="1")Integer page,
                              @RequestParam(defaultValue="20")Integer rows,
                              @RequestParam(value = "beginDate",required = false)String beginDate,
                              @RequestParam(value = "endDate",required = false)String endDate,
                              HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        Date date1 = null;
        Date date2 = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (!ValidateUtils.isBlank(beginDate)) {
                date1 = sdf.parse(beginDate);
            }
            if (!ValidateUtils.isBlank(endDate)) {
                date2 = sdf.parse(endDate);
            }
            //查询用户所在公司的客户信息
            SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
            param.put("companyId", systemUser.getCompanyId());
            param.put("beginDate", date1);
            param.put("endDate", date2);
            userOrderService.exportCellUserCount(param,null,null, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
