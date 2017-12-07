package com.kapphk.web.controller.web.shop;

import com.kapphk.web.bean.product.BeanAppProductOrder;
import com.kapphk.web.service.web.imethod.shop.BeanProductOrderService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**商品订单控制器
 * Created by shy on 2016/12/21.
 */

@RestController
@RequestMapping("/web/product/order/")
public class BeanProductOrderController {
    @Autowired
    private BeanProductOrderService service;

    /**
     * 查询列表
     */
    @RequestMapping("searchList.htm")
    public Result getList(@RequestParam(required = false) String deliveryName,
                          @RequestParam(required = false) String itemRemark,
                          @RequestParam(required = false) Long id,
                          @RequestParam(required = false) String orderNo,
                          @RequestParam(required = false) Long productId,
                          @RequestParam(required = false) Integer state
            , GridReq gridReq){
        Result rs = new Result();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("deliveryName", StringUtils.trim(deliveryName));
        param.put("itemRemark",StringUtils.trim(itemRemark));
        param.put("id",id);
        param.put("orderNo",StringUtils.trim(orderNo));
        param.put("productId",productId);
        param.put("state",state);
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
     * 更新物流
     * @param bean
     * @return
     */
    @RequestMapping("update.htm")
    public Result updateData(BeanAppProductOrder bean){
        Result rs = new Result();
        try {
            return service.update(bean,rs);
        } catch (Exception e) {
            e.printStackTrace();
            rs.setStatus(Contents.ERROR);
            rs.setMsg("出现错误");
            return rs;
        }
    }

    /**
     * 详情/评价
     */
    @RequestMapping("data.htm")
    public Result getData(BeanAppProductOrder bean){
        Result rs = new Result();
        try {
            return service.getData(rs,bean);
        } catch (Exception e) {
            e.printStackTrace();
            rs.setStatus(Contents.ERROR);
            rs.setMsg("出现错误");
            return rs;
        }
    }

    /**
     * 导出EXCLE
     * @param response
     */
    @RequestMapping("exportOrder.htm")
    public void exportExcel(@RequestParam(required = false)String orderNo,
                            @RequestParam(required = false)String itemRemark,
                            @RequestParam(required = false)String deliveryName,
                            @RequestParam(required = false)Integer state,
                            HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("orderNo",orderNo);
        param.put("itemRemark",itemRemark);
        param.put("deliveryName",deliveryName);
        param.put("state",state);
        service.exportExcel(param,request,response);
    }
}
