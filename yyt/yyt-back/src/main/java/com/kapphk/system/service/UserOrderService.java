package com.kapphk.system.service;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseService;
import com.kapphk.yyt.bean.UserOrder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 用户燃气号缴费业务处理
 * Created by Shy on 2016/12/12
 * Since 1.0
 */

public interface UserOrderService extends BaseService<UserOrder,Long> {

    /**
     * 缴费报表
     * @param param
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    PageInfo<Map<String,Object>> queryPaymentByPage(Map<String, Object> param, Integer page, Integer rows, String sort);

    /**
     * 用气报表
     * @param param
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    PageInfo<Map<String,Object>> queryGasCountByPage(Map<String, Object> param, Integer page, Integer rows, String sort);

    /**
     * 缴费统计报表
     * @param param
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    PageInfo<Map<String,Object>> queryPaymentCountByPage(Map<String, Object> param, Integer page, Integer rows, String sort);


    /**
     *  小区用户数统计报表
     * @param param
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    PageInfo<Map<String,Object>> queryCellUserCountByPage(Map<String, Object> param, Integer page, Integer rows, String sort);

    /**
     * 导出数据--缴费报表
     * @param param
     * @param page
     * @param rows
     * @param request
     * @param response
     */
    void exportPayment(Map<String, Object> param, Integer page, Integer rows, HttpServletRequest request, HttpServletResponse response);

    /**
     * 导出数据--小区用户数统计
     * @param param
     * @param page
     * @param rows
     * @param request
     * @param response
     */
    void exportCellUserCount(Map<String, Object> param, Integer page, Integer rows, HttpServletRequest request, HttpServletResponse response);
    /**
     * 导出数据--缴费统计(不同营业网点不同的支付类型)
     * @param param
     * @param page
     * @param rows
     * @param request
     * @param response
     */
    void exportPaymentCount(Map<String, Object> param, Integer page, Integer rows, HttpServletRequest request, HttpServletResponse response);

    /**
     * 导出数据--用气统计(不同营业网点不同的居民类型)
     * @param param
     * @param page
     * @param rows
     * @param request
     * @param response
     */
    void exportGasCount(Map<String, Object> param, Integer page, Integer rows, HttpServletRequest request, HttpServletResponse response);
}
