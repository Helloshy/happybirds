package com.kapphk.web.controller.web.shop;

import com.kapphk.web.bean.product.BeanAppProductDelivery;
import com.kapphk.web.service.web.imethod.shop.BeanProductDeliveryService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;

/**运费处理控制器
 * Created by shy on 2016/12/23.
 */

@RestController
@RequestMapping("/web/product/delivery/")
public class BeanProductDeliveryController {

    @Autowired
    private BeanProductDeliveryService service;

    /**
     * 查询列表
     */
    @RequestMapping("searchList.htm")
    public Result getList(@RequestParam(required = false) String cityId, GridReq gridReq){
        Result rs = new Result();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("cityId",cityId);
        try {
            return service.searchList(rs,param,gridReq);
        } catch (Exception e) {
            e.printStackTrace();
            rs.setStatus(Contents.ERROR);
            rs.setMsg("出现错误");
            return rs;
        }
    }


    /**
     * 保存/修改
     */
    @RequestMapping("save.htm")
    public Result saveData(BeanAppProductDelivery bean,
                           @RequestParam(value = "ids") String [] ids,
                           @RequestParam(value = "oldId",required = false) String [] oldId){
        Result rs = new Result();
        try {
            return service.saveData(rs,bean,ids,oldId);
        } catch (Exception e) {
            e.printStackTrace();
            rs.setStatus(Contents.ERROR);
            rs.setMsg("出现错误");
            return rs;
        }
    }



    @RequestMapping("data.htm")
    public Result getData(BeanAppProductDelivery bean,String [] ids){
        Result rs = new Result();
        try {
            return service.getData(rs,bean,Arrays.asList(ids));
        } catch (Exception e) {
            e.printStackTrace();
            rs.setStatus(Contents.ERROR);
            rs.setMsg("出现错误");
            return rs;
        }
    }



    @RequestMapping("delete.htm")
    public Result delete(@RequestParam("ids") String ids){
        Result rs = new Result();
        String [] arr = ids.split(",");
        Long [] idsArr = new Long [arr.length];
        for (int i =0;i<arr.length;i++) {
            idsArr[i]=Long.parseLong(arr[i]);
        }
        try {
            return service.delete(rs, Arrays.asList(idsArr));
        } catch (Exception e) {
            e.printStackTrace();
            rs.setStatus(Contents.ERROR);
            rs.setMsg("出现错误");
            return rs;
        }
    }


    @RequestMapping("getProvinceAndCityList.htm")
    public List<Map<String,Object>> getList(){
        List<Map<String,Object>> resultMap = service.findAll();
        return resultMap;
    }


    /**
     * 初始化省份城市下拉框
     * @author zoneyu 15-12-17
     */
    @RequestMapping("searchCitysList.htm")
    public List<Map<String,Object>> searchResourceList(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>() ;
        try {
            list = service.searchCitysList(list) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list ;
    }
}
