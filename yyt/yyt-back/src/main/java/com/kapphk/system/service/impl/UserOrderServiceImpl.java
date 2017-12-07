package com.kapphk.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseServiceImpl;
import com.kapphk.system.service.UserOrderService;
import com.kapphk.yyt.bean.UserOrder;
import com.kapphk.yyt.mapper.UserOrderMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kapphk.system.web.JXLExportUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Shy on 2016/12/12
 * Since 1.0
 */
@Service
public class UserOrderServiceImpl extends BaseServiceImpl<UserOrder,Long> implements UserOrderService {
    @Autowired
    private UserOrderMapper mapper;

    @Override
    public void init() {
        super.setMapper(this.mapper);
    }


    @Override
    public PageInfo<Map<String, Object>> queryPaymentByPage(Map<String, Object> param, Integer page, Integer rows, String sort) {

        PageHelper.startPage(page,rows);
        if (!StringUtils.isBlank(sort)){
            PageHelper.orderBy(sort);
        }
        List<Map<String,Object>> resultMaps = mapper.queryPayment(param);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(resultMaps);
        return pageInfo;
    }

    @Override
    public PageInfo<Map<String, Object>> queryGasCountByPage(Map<String, Object> param, Integer page, Integer rows, String sort) {
        PageHelper.startPage(page,rows);
        if (!StringUtils.isBlank(sort)){
            PageHelper.orderBy(sort);
        }
        List<Map<String,Object>> resultMaps = mapper.queryGasCount(param);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(resultMaps);
        return pageInfo;
    }

    @Override
    public PageInfo<Map<String, Object>> queryPaymentCountByPage(Map<String, Object> param, Integer page, Integer rows, String sort) {
        PageHelper.startPage(page,rows);
        if (!StringUtils.isBlank(sort)){
            PageHelper.orderBy(sort);
        }
        List<Map<String,Object>> resultMaps = mapper.queryPaymentCount(param);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(resultMaps);
        return pageInfo;
    }

    @Override
    public PageInfo<Map<String, Object>> queryCellUserCountByPage(Map<String, Object> param, Integer page, Integer rows, String sort) {
        PageHelper.startPage(page,rows);
        if (!StringUtils.isBlank(sort)){
            PageHelper.orderBy(sort);
        }
        List<Map<String,Object>> resultMaps = mapper.queryCellUserCount(param);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(resultMaps);
        return pageInfo;
    }

    @Override
    public void exportPayment(Map<String, Object> param, Integer page, Integer rows, HttpServletRequest request, HttpServletResponse response) {
        List<Map<String,Object>> list = mapper.queryPayment(param);
        JXLExportUtils export = new JXLExportUtils("缴费报表");
        export.addExportType(Map.class);
        try {
            export.addExportProperty("所属公司", "companyName");
            export.addExportProperty("营业网点", "hallName");
            export.addExportProperty("缴费总额", "totalPayment");
            export.addExportProperty("更新时间", "createTime");

            export.writerHead(response);

            export.writerTitle(response);

            export.writerData(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportCellUserCount(Map<String, Object> param, Integer page, Integer rows, HttpServletRequest request, HttpServletResponse response) {
        List<Map<String,Object>> list = mapper.queryCellUserCount(param);
        JXLExportUtils export = new JXLExportUtils("小区用户数统计");
        export.addExportType(Map.class);
        try {
            export.addExportProperty("所属公司", "companyName");
            export.addExportProperty("小区名称", "itemName");
            export.addExportProperty("开栓用户数", "openCount");
            export.addExportProperty("闭栓用户数", "closeCount");
            export.addExportProperty("更新时间", "updateTime");

            export.writerHead(response);

            export.writerTitle(response);

            export.writerData(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportPaymentCount(Map<String, Object> param, Integer page, Integer rows, HttpServletRequest request, HttpServletResponse response) {
        List<Map<String,Object>> list = mapper.queryPaymentCount(param);
        JXLExportUtils export = new JXLExportUtils("缴费统计报表");
        export.addExportType(Map.class);
        try {
            export.addExportProperty("所属公司", "companyName");
            export.addExportProperty("营业网点", "hallName");
            export.addExportProperty("缴费类型", "payMethod");
            export.addExportProperty("缴费金额", "totalPayment");
            export.addExportProperty("更新时间", "createTime");

            export.writerHead(response);

            export.writerTitle(response);

            export.writerData(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportGasCount(Map<String, Object> param, Integer page, Integer rows, HttpServletRequest request, HttpServletResponse response) {
        List<Map<String,Object>> list = mapper.queryGasCount(param);
        JXLExportUtils export = new JXLExportUtils("用气统计报表");
        export.addExportType(Map.class);
        try {
            export.addExportProperty("所属公司", "company_name");
            export.addExportProperty("营业网点", "hall_name");
            export.addExportProperty("用户类型", "record_tag");
            export.addExportProperty("用户数", "totalUser");
            export.addExportProperty("总消费金额", "totalPrice");
            export.addExportProperty("总用气量", "totalgas");

            export.writerHead(response);

            export.writerTitle(response);

            export.writerData(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
