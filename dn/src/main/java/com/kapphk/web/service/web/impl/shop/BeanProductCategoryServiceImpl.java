package com.kapphk.web.service.web.impl.shop;


import com.kapphk.web.bean.product.BeanAppProductCategory;
import com.kapphk.web.bean.tag.BeanAppArea;
import com.kapphk.web.dao.mapper.product.BeanAppProductCategoryMapper;
import com.kapphk.web.service.web.imethod.shop.BeanProductCategoryService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**商品分类业务处理
 * Created by shy on 2016/12/19.
 */

@Service
public class BeanProductCategoryServiceImpl implements BeanProductCategoryService {
    @Autowired
    private BeanAppProductCategoryMapper mapper;

    @Override
    public Result searchList(Result rs, BeanAppProductCategory bean, GridReq gridReq)throws Exception {
        DataUtils.trim(bean);
        List<Map<String,Object>> list = mapper.findList(bean,gridReq.getPage(),gridReq.getRows());
        int count = mapper.findCount(bean);
        rs = DataUtils.mergeData(count, list, rs);
        return rs;
    }

    @Override
    public Result saveData(Result rs, BeanAppProductCategory bean) throws Exception{
        if (null==bean){
            rs.setStatus(Contents.ERROR);
            rs.setMsg("参数有误");
            return rs;
        }
        if (null==bean.getId()){
            //添加
            BeanAppProductCategory pc1 = mapper.findEntityByName(StringUtils.trim(bean.getItemName()));
            if (pc1!=null){
                rs.setStatus(Contents.ERROR);
                rs.setMsg("分类已经存在");
                return rs;
            }
            bean.setCreateTime(new Date());
            mapper.insert(bean);
        }else {
            //修改
            BeanAppProductCategory pc = mapper.findById(bean.getId());
            if (!pc.getItemName().equals(StringUtils.trim(bean.getItemName()))){
                BeanAppProductCategory pc1 = mapper.findEntityByName(StringUtils.trim(bean.getItemName()));
                if (pc1!=null){
                    rs.setStatus(Contents.ERROR);
                    rs.setMsg("分类已经存在");
                    return rs;
                }
            }
                mapper.update(bean);
        }
        return rs;
    }

    @Override
    public Result delete(Result rs, List<Long> ids) throws Exception {
        rs.put("count",mapper.deletes(ids));
        return rs;
    }

    @Override
    public Result getData(Result rs, BeanAppProductCategory bean) throws Exception {
        rs.put("data",mapper.findById(bean.getId()));
        return rs;
    }

    @Override
    public  List<Map<String, Object>> findAll() {
        return mapper.findCategoryList();
    }
}
