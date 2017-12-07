package com.kapphk.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseServiceImpl;
import com.kapphk.system.bean.User;
import com.kapphk.system.mapper.UserMapper;
import com.kapphk.system.service.CustomerService;
import com.kapphk.web.utils.ValidateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Security;
import java.util.List;
import java.util.Map;

/**客户信息业务层
 * Created by shy on 2016/12/7.
 */


public class CustomerServiceImpl extends BaseServiceImpl<User,Long> implements CustomerService {

    @Autowired
    private UserMapper mapper;

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
}
