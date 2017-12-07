package com.kapphk.web.controller.web.shop;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.product.BeanAppProductInventory;
import com.kapphk.web.service.web.imethod.shop.BeanProductInventoryService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;

/**商品库存控制器
 * Created by shy on 2016/12/19.
 */

@RestController
@RequestMapping("/web/product/inventory/")
public class BeanProductInventoryController {

    @Autowired
    private BeanProductInventoryService service;

    /**
     * 查询列表
     */
    @RequestMapping("searchList.htm")
    public Result getList(@RequestParam(required = false) String itemName,
                          @RequestParam(required = false) Long id,
                          @RequestParam(required = false) Long productId
                          , GridReq gridReq){
        Result rs = new Result();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("itemName",itemName);
        param.put("id",id);
        param.put("productId",productId);
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
    public Result saveData(BeanAppProductInventory bean){
        Result rs = new Result();
        try {
            return service.saveData(rs,bean);
        } catch (Exception e) {
            e.printStackTrace();
            rs.setStatus(Contents.ERROR);
            rs.setMsg("出现错误");
            return rs;
        }
    }

    /**
     *详情
     */
    @RequestMapping("data.htm")
    public Result getData(@RequestParam(required = false) Long  id){
        Result rs = new Result();
        try {
            return service.getData(rs,id);
        } catch (Exception e) {
            e.printStackTrace();
            rs.setStatus(Contents.ERROR);
            rs.setMsg("出现错误");
            return rs;
        }
    }

    @RequestMapping("delete.htm")
    public Result delete(Long ids){
        Result rs = new Result();
        try {
            return service.delete(rs, ids);
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
            @RequestParam(required = false) Long productId,
            @RequestParam(required = false) Integer state,
            HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("state",state);
        param.put("productId",productId);
        service.exportExcelProductInventory(param,request,response);
    }
}
