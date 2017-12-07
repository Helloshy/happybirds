package com.kapphk.web.service.web.imethod.shop;

import com.kapphk.web.bean.product.BeanAppProduct;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**商城管理---商品业务控制层
 * Created by shy on 2016/12/19.
 */


public interface BeanProductService {

    /**
     *
     * 列表数据
     */
    Result searchList(Result rs, Map<String,Object> param, GridReq gridReq) throws Exception;

    /**
     * 详情
     * @param rs
     * @param param
     * @return
     */
    Result getData(Result rs, Map<String,Object> param);

    /**
     *
     * @param rs
     * @param ids
     * @return
     */
    Result delete(Result rs, List<Long> ids) throws Exception;

    /**
     * 保存
     * @param bean
     * @param request
     * @param file
     * @param imgs
     * @param rs
     * @return
     */
    Result saveData(BeanAppProduct bean, HttpServletRequest request, MultipartFile file, String[] imgs, Result rs);

    Result saveData(BeanAppProduct bean, HttpServletRequest request, MultipartFile file, MultipartFile photo1, MultipartFile photo2, MultipartFile photo3, MultipartFile photo4, MultipartFile photo5, Result rs,List<Long> photos) throws Exception;
    /*
    下架商品
     */
    Result offShelves(Result rs, List<Long> longs);
}
