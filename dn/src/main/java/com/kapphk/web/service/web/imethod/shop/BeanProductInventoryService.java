package com.kapphk.web.service.web.imethod.shop;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kapphk.web.bean.product.BeanAppProductInventory;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;

/**
 * Created by shy on 2016/12/20.
 */


public interface BeanProductInventoryService {

    /**
     * 条件查询列表
     * @param rs
     * @param param
     * @param gridReq
     * @return
     */
    Result searchList(Result rs, Map<String, Object> param, GridReq gridReq) throws Exception;

    void exportExcelProductInventory(Map<String, Object> param, HttpServletRequest request, HttpServletResponse response);

    Result saveData(Result rs, BeanAppProductInventory bean) throws Exception;

    Result delete(Result rs, Long ids) throws Exception;

    Result getData(Result rs, Long id);
}
