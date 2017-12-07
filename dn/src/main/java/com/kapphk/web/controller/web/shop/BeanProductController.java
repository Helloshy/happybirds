package com.kapphk.web.controller.web.shop;

import com.kapphk.web.bean.product.BeanAppProduct;
import com.kapphk.web.bean.tag.BeanAppArea;
import com.kapphk.web.service.web.imethod.shop.BeanProductService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**商品相关控制器
 * Created by shy on 2016/12/19.
 */

@RestController
@RequestMapping("/web/product/manage/")
public class BeanProductController {

    @Autowired
    private BeanProductService service;

    /**
     * 查询列表
     */
    @RequestMapping("searchList.htm")
    public Result getList(@RequestParam(required = false) String itemName, GridReq gridReq){
        Result rs = new Result();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("itemName", StringUtils.trim(itemName));
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
     *  保存
     */
    @RequestMapping("save.htm")
    public Result saveData(BeanAppProduct bean, HttpServletRequest request,
                           MultipartFile file,
                           MultipartFile photo1,
                           MultipartFile photo2,
                           MultipartFile photo3,
                           MultipartFile photo4,
                           MultipartFile photo5,
                           @RequestParam(required = false)Long photoId1,
                           @RequestParam(required = false)Long photoId2,
                           @RequestParam(required = false)Long photoId3,
                           @RequestParam(required = false)Long photoId4,
                           @RequestParam(required = false)Long photoId5){
        Result rs = new Result();
        List<Long> photoIds =new ArrayList<Long>();
        photoIds.add(photoId1);
        photoIds.add(photoId2);
        photoIds.add(photoId3);
        photoIds.add(photoId4);
        photoIds.add(photoId5);
        try {
            return service.saveData(bean,request,file,photo1,photo2,photo3, photo4,photo5,rs,photoIds);
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
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("id",id);
        try {
           return service.getData(rs,param);
        } catch (Exception e) {
            e.printStackTrace();
            rs.setStatus(Contents.ERROR);
            rs.setMsg("出现错误");
            return rs;
        }
    }


    /**
     *删除
     */
    @RequestMapping("delete.htm")
    public Result delete(@RequestParam("ids") Long[] ids){
        Result rs = new Result();
        try {
            return service.delete(rs,Arrays.asList(ids));
        } catch (Exception e) {
            e.printStackTrace();
            rs.setStatus(Contents.ERROR);
            rs.setMsg("出现错误");
            return rs;
        }
    }

    /**
     *下架
     */
    @RequestMapping("offShelves.htm")
    public Result offShelves(@RequestParam("ids") Long[] ids){
        Result rs = new Result();
        try {
            return service.offShelves(rs,Arrays.asList(ids));
        } catch (Exception e) {
            e.printStackTrace();
            rs.setStatus(Contents.ERROR);
            rs.setMsg("出现错误");
            return rs;
        }
    }


}
