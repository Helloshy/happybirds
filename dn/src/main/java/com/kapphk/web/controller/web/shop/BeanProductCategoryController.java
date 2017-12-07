package com.kapphk.web.controller.web.shop;

import com.kapphk.web.bean.product.BeanAppProductCategory;
import com.kapphk.web.service.web.imethod.shop.BeanProductCategoryService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**商品分类控制器
 * Created by shy on 2016/12/19.
 */

@RestController
@RequestMapping("/web/product/category/")
public class BeanProductCategoryController {
    @Autowired
    private BeanProductCategoryService service;

    /**
     * 查询列表
     */
    @RequestMapping("searchList.htm")
    public Result getList(BeanAppProductCategory bean, GridReq gridReq){
        Result rs = new Result();
        try {
            return service.searchList(rs,bean,gridReq);
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
    public Result saveData(BeanAppProductCategory bean){
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



    @RequestMapping("data.htm")
    public Result getData(BeanAppProductCategory bean){
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



    @RequestMapping("delete.htm")
    public Result delete(@RequestParam("ids") Long[] ids){
        Result rs = new Result();
        try {
            return service.delete(rs, Arrays.asList(ids));
        } catch (Exception e) {
            e.printStackTrace();
            rs.setStatus(Contents.ERROR);
            rs.setMsg("出现错误");
            return rs;
        }
    }


    @RequestMapping("getCategoryList.htm")
    public List<Map<String,Object>> getList(){
            List<Map<String,Object>> resultMap = service.findAll();
            return resultMap;
        }

}
