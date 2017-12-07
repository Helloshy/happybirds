package com.kapphk.web.service.web.impl.shop;


import com.kapphk.web.bean.product.BeanAppProductCategory;
import com.kapphk.web.bean.product.BeanAppProductDelivery;
import com.kapphk.web.bean.system.BeanCity;
import com.kapphk.web.dao.mapper.product.BeanAppProductCategoryMapper;
import com.kapphk.web.dao.mapper.product.BeanAppProductDeliveryMapper;
import com.kapphk.web.service.web.imethod.shop.BeanProductCategoryService;
import com.kapphk.web.service.web.imethod.shop.BeanProductDeliveryService;
import com.kapphk.web.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**商品分类业务处理
 * Created by shy on 2016/12/19.
 */

@Service
public class BeanProductDeliveryServiceImpl implements BeanProductDeliveryService {
    @Autowired
    private BeanAppProductDeliveryMapper mapper;

    @Override
    public Result searchList(Result rs, Map<String,Object> param, GridReq gridReq)throws Exception {
        List<Map<String,Object>> list = mapper.findList(param,gridReq.getPage(),gridReq.getRows());
        int count = mapper.findCount(param);
        rs = DataUtils.mergeData(count, list, rs);
        return rs;
    }

    @Override
    public Result saveData(Result rs, BeanAppProductDelivery bean,String  [] id,String [] ids) throws Exception{
        if (null==bean||null==id){
            rs.setStatus(Contents.ERROR);
            rs.setMsg("参数有误");
            return rs;
        }
        if (ValidateUtils.isEmpty(ids)){
            //添加
            //删除已经存在的
            mapper.deleteByCityId(Arrays.asList(id));
            insertMany(bean, id);
        }else {
            //修改
            Long [] idsArr = new Long [ids.length];
            for (int i =0;i<ids.length;i++) {
                idsArr[i]=Long.parseLong(ids[i]);
            }
            mapper.deletes(Arrays.asList(idsArr));
            insertMany(bean, id);
        }
        return rs;
    }

    private void insertMany(BeanAppProductDelivery bean, String[] id) {
        List<BeanAppProductDelivery> deliverys = new ArrayList<BeanAppProductDelivery>();
        for (String cityId:id) {
            BeanAppProductDelivery delivery =  new BeanAppProductDelivery();
            delivery.setPrice(bean.getPrice());
            delivery.setCreateTime(new Date());
            delivery.setCityId(cityId);
            delivery.setProvinceId(mapper.findProvinceId(cityId));
            deliverys.add(delivery);
        }
        mapper.inserts(deliverys);
    }

    @Override
    public Result delete(Result rs, List<Long> ids) throws Exception {
        rs.put("count",mapper.deletes(ids));
        return rs;
    }

    @Override
    public Result getData(Result rs, BeanAppProductDelivery bean,List<String> ids) throws Exception {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("ids",ids);
        rs.put("data",mapper.findList(param,null,null));
        return rs;
    }

    @Override
    public List<Map<String, Object>> findAll() {
        List<Map<String, Object>> resultMap = mapper.findProvinceAndCitys();
        return resultMap;
    }

    @Override
    public List<Map<String, Object>> searchCitysList(List<Map<String, Object>> list) {
        List<Map<String,Object>> cities = mapper.findProvinceAndCitys();
        Collections.reverse(cities);
        for (Map<String,Object> map:cities) {
            Map<String,Object> map1 = new HashMap<String, Object>() ;
            //添加省份
            map1.put("id",map.get("provinceId"));
            map1.put("text",map.get("provinceId"));
            //添加城市
            List<Map<String,Object>> list2 = new ArrayList<Map<String,Object>>();
            String [] cityArr = ((String)map.get("cityIds")).split(",");
            for (String cityId:cityArr) {
            Map<String,Object> map2 = new HashMap<String, Object>() ;
                map2.put("id",cityId);
                map2.put("text",cityId);
                list2.add(map2);
            }
            map1.put("children",list2);
            list.add(map1);
        }
        return list;
    }
}
