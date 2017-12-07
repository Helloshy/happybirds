package com.kapphk.system.service;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseService;
import com.kapphk.system.bean.User;

import java.util.Map;

/**
 * Created by shy on 2016/12/7.
 */


public interface CustomerService extends BaseService<User,Long>{

    /**
     * 按条件进行分页查询
     * @param param 查询参数
     * @param page 页码
     * @param rows 每页显示条数
     * @param sort 排序
     * @return
     */
    public PageInfo<Map<String,Object>> queryByPage(Map<String, Object> param, Integer page, Integer rows, String sort);
}
