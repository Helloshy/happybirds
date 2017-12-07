package com.kapphk.web.controller.web.shop;

import com.kapphk.web.service.web.imethod.shop.BeanProductOrderService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**商城销售报表控制器
 * Created by shy on 2016/12/24.
 */

@RestController
@RequestMapping("/web/product/inventoryChart/")
public class BeanProductInventoryChartController {


    @Autowired
    private BeanProductOrderService service;

    /**
     * 查询列表
     */
    @RequestMapping("searchList.htm")
    public Result getList(
                          @RequestParam(required = false) String itemRemark,
                          @RequestParam(required = false) String startTime,
                          @RequestParam(required = false) String endTime,
                          @RequestParam(required = false) Integer state
            , GridReq gridReq){
        Result rs = new Result();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("itemRemark",itemRemark);
        param.put("startTime",startTime);
        param.put("endTime",endTime);
        param.put("state",state);
        try {
            return service.searchListForProductInventoryChart(rs,param,gridReq);
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
    @RequestMapping("exportExcel.htm")
    public void exportExcel(
            @RequestParam(required = false) String itemRemark,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime,
            @RequestParam(required = false) Integer state,
                            HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("itemRemark",itemRemark);
        param.put("startTime",startTime);
        param.put("endTime",endTime);
        param.put("state",state);
        service.exportExcelProductInventoryChart(param,request,response);
    }
}
