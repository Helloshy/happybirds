package com.kapphk.system.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseService;
import com.kapphk.yyt.bean.Company;

/**
 * 公司信息数据服务接口
 * @author Administrator
 *
 */
public interface CompanyService extends BaseService<Company,Long>{

    /**
     * 按条件进行分页查询
     * @param param 查询参数
     * @param page 页码
     * @param rows 每页显示条数
     * @param sort 排序
     * @return
     */
    public PageInfo<Map<String,Object>> queryCompanyByPage(Company Company,Integer page, Integer rows, String sort);
}
