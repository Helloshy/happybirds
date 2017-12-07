package com.kapphk.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseServiceImpl;
import com.kapphk.system.bean.User;
import com.kapphk.system.service.UserNumberService;
import com.kapphk.web.utils.Common;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.Result;
import com.kapphk.yyt.bean.UserNumber;
import com.kapphk.yyt.bean.UserNumberMap;
import com.kapphk.yyt.bean.UserOrder;
import com.kapphk.yyt.mapper.UserNumberMapMapper;
import com.kapphk.yyt.mapper.UserNumberMapper;
import com.kapphk.yyt.mapper.UserOrderMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by shy on 2016/12/7.
 */

@Service
public class UserNumberServiceImpl extends BaseServiceImpl<UserNumber,Long> implements UserNumberService {

    @Autowired
    private UserNumberMapper mapper;
    @Autowired
    private UserOrderMapper userOrderMapper;
    @Autowired
    private UserNumberMapMapper userNumberMapMapper;

    @Override
    public void init() {
        super.setMapper(this.mapper);
    }

    @Override
    public PageInfo<Map<String, Object>> queryByPage(Map<String, Object> param, Integer page, Integer rows, String sort) {
        PageHelper.startPage(page,rows);
        if (!StringUtils.isBlank(sort)){
            PageHelper.orderBy(sort);
        }
        List<Map<String,Object>> resultMaps = mapper.findListByParam(param);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(resultMaps);
        return pageInfo;
    }

    @Override
    public Result delete(String ids, Result rs) throws Exception {
        mapper.updateUserNumberState(DataUtils.string2List(ids),0) ;
        return rs;
    }

    @Override
    public Result updateData(UserNumber userNumber, Result result) {
        if (null == userNumber||userNumber.getId()==null){
            result.setError(Result.MSG.PARAMS_ERROR);
            return result;
        }
        mapper.update(userNumber);
        return result;
    }

    /**
     * 分页查询用户收费管理信息
     * @param param
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    @Override
    public PageInfo<Map<String, Object>> queryChargeByPage(Map<String, Object> param, Integer page, Integer rows, String sort) {
        PageHelper.startPage(page,rows);
        if (!StringUtils.isBlank(sort)){
            PageHelper.orderBy(sort);
        }
        List<Map<String,Object>> resultMaps = mapper.findChargeListByParam(param);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(resultMaps);
        return pageInfo;
    }

    @Override
    public Result confirmPayment(Long id, Integer payMethod, BigDecimal amount, Date createTime, Long operatorId,Result result) {

        if (null != id && id>0 && null!=payMethod&&null!=amount&&amount.compareTo(new BigDecimal(0))==1&&null!=createTime&&null!=operatorId ){
            UserNumber userNumberByQuery = findById(id);
            if (null!=userNumberByQuery){
                UserNumber userNumber  = new UserNumber();
                userNumber.setId(id);
                userNumber.setUpdateBy(operatorId);
                userNumber.setUpdateTime(new Date());
                //计算新的账户余额
                BigDecimal newBalanceAmount = userNumberByQuery.getBalanceAmount().add(amount);
                userNumber.setBalanceAmount(newBalanceAmount);
                Integer updateCount = mapper.update(userNumber);
                if (updateCount>0){
                    //查询用户id
                    UserNumberMap userNumberMap = userNumberMapMapper.findByUnid(id);
                    if (null!=userNumberMap){
                        //产生订单记录
                        UserOrder userOrder = new UserOrder();
                        userOrder.setCompanyId(userNumberByQuery.getCompanyId());
                        userOrder.setUnid(id);
                        // orderNO 生成策略
                        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
                        String orderNo = sdf.format(createTime) + Common.createRandom(true, 3);
                        userOrder.setOrderNo(orderNo);
                        userOrder.setAmount(amount);
                        userOrder.setIsPay(1);
                        userOrder.setBalanceAmount(newBalanceAmount);
                        userOrder.setSid(operatorId);
                        userOrder.setUid(userNumberMap.getUid());
                        userOrder.setPayMethod(payMethod);
                        //备注默认为空字符串
                        userOrder.setRemark("empty");
                        userOrder.setCreateTime(createTime);
                        userOrderMapper.insert(userOrder);
                        //TODO 打印小票

                    }
                }
            }
        }
        else {
            result.setError(Result.MSG.PARAMS_ERROR);
            result.setMsg("参数有误");
        }
        return result;
    }


    @Override
    public PageInfo<Map<String, Object>> queryRecordByPage(Map<String, Object> param, Integer page, Integer rows, String sort) {
        PageHelper.startPage(page,rows);
        if (!StringUtils.isBlank(sort)){
            PageHelper.orderBy(sort);
        }
        List<Map<String,Object>> resultMaps = mapper.findRecordListByParam(param);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(resultMaps);
        return pageInfo;
    }

    @Override
    public Result updateForWriteOff(Long id, BigDecimal amount, Date updateTime, Long operatorId, Result result) {
        if (null == id ||null == amount || null == updateTime || null ==id){
            result.setError(Result.MSG.CUS_ERROR);
            result.setMsg("参数有误");
            return result;
        }else {
           amount =  amount.abs();
            UserNumber userNumber = mapper.findById(id);
            if (null == userNumber){
                result.setError(Result.MSG.CUS_ERROR);
                result.setMsg("参数有误");
                return result;
            }else{
                userNumber.getBalanceAmount();

                //设置新的账户余额
                userNumber.setBalanceAmount(userNumber.getBalanceAmount().add(amount));
                userNumber.setUpdateBy(operatorId);
                userNumber.setUpdateTime(updateTime);
                mapper.updateBlanceAmount(userNumber);
            }
        }


        return result;
    }
}
