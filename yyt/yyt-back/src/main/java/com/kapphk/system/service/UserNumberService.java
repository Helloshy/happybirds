package com.kapphk.system.service;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseService;
import com.kapphk.web.utils.Result;
import com.kapphk.yyt.bean.UserNumber;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created by shy on 2016/12/7.
 */


public interface UserNumberService extends BaseService<UserNumber,Long>{

    /**
     * 按条件进行分页查询
     * @param param 查询参数
     * @param page 页码
     * @param rows 每页显示条数
     * @param sort 排序
     * @return
     */
    public PageInfo<Map<String,Object>> queryByPage(Map<String, Object> param, Integer page, Integer rows, String sort);


    public Result delete(String ids, Result rs) throws Exception ;

    Result updateData(UserNumber userNumber, Result result);

    /**
     * 分页查询用户收费管理信息
     * @param param
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    PageInfo<Map<String,Object>> queryChargeByPage(Map<String, Object> param, Integer page, Integer rows, String sort);

    /**
     * 确认缴费
     * @param id
     * @param payMethod
     * @param amount
     * @param createTime
     * @param operatorId
     * @return
     */
    Result confirmPayment(Long id, Integer payMethod, BigDecimal amount, Date createTime, Long operatorId,Result result);

    /**
     * 查询缴费记录
     * @param param
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    PageInfo<Map<String,Object>> queryRecordByPage(Map<String, Object> param, Integer page, Integer rows, String sort);

    /**
     *
     * @param id
     * @param amount
     * @param updateTime
     * @param operatorId
     * @param result
     * @return
     */
    Result updateForWriteOff(Long id, BigDecimal amount, Date updateTime, Long operatorId, Result result);
}
