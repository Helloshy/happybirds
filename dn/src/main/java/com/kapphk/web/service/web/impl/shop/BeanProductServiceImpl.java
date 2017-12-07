package com.kapphk.web.service.web.impl.shop;

import com.kapphk.web.bean.product.BeanAppProduct;
import com.kapphk.web.bean.product.BeanAppProductPhoto;
import com.kapphk.web.dao.mapper.product.BeanAppProductInventoryMapper;
import com.kapphk.web.dao.mapper.product.BeanAppProductMapper;
import com.kapphk.web.dao.mapper.product.BeanAppProductPhotoMapper;
import com.kapphk.web.service.web.imethod.shop.BeanProductService;
import com.kapphk.web.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**商品业务处理
 * Created by shy on 2016/12/19.
 */

@Service
public class BeanProductServiceImpl implements BeanProductService {

    @Autowired
    private BeanAppProductMapper mapper;
    @Autowired
    private BeanAppProductPhotoMapper beanAppProductPhotoMapper;
    @Autowired
    private BeanAppProductInventoryMapper beanAppProductInventoryMapper;


    @Override
    public Result searchList(Result rs, Map<String,Object> param, GridReq gridReq) throws Exception {
        List<Map<String,Object>> list = mapper.findListByParam(param,gridReq.getPage(),gridReq.getRows());
        int count = mapper.findListByParamCount(param);
        rs = DataUtils.mergeData(count, list, rs);
        return rs;
    }

    @Override
    public Result saveData(BeanAppProduct bean, HttpServletRequest request, MultipartFile file, String[] imgs, Result rs) {
        DataUtils.trim(bean);
        //上传图片
        if(!file.isEmpty()){
            try {
                byte[] bytes = file.getBytes();
                String logPath = FileUploadUtils.uploadFile(bytes,"upload/public", "2", request);
                bean.setLogoPath(logPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(ValidateUtils.isBlank(bean.getId())){
            bean.setCreateTime(new Date());
            Long id = mapper.insertOverride(bean);
            List<BeanAppProductPhoto> photos = new ArrayList<BeanAppProductPhoto>();
            BeanAppProductPhoto photo = null;
            for (String img:imgs) {
                photo = new BeanAppProductPhoto();
                photo.setProductId(id);
                photo.setLogoPath(img);
                photo.setCreateTime(new Date());
                photos.add(photo);
            }
            beanAppProductPhotoMapper.inserts(photos);
        }else{
            //更新
            mapper.update(bean);
        }
        return rs;
    }

    @Override
    public Result getData(Result rs, Map<String,Object> param) {
         Map<String,Object> map= mapper.findListByParam(param,null,null).get(0);
        Long id = (Long)map.get("id");
        List<Map<String,Object>> photos = beanAppProductPhotoMapper.findByProductId(id);
        map.put("photos",photos);
        rs.put("data",map);
        return rs;
    }

    @Override
    public Result delete(Result rs, List<Long> ids) throws Exception {
        //删除轮播图
        beanAppProductPhotoMapper.deletesByProductId(ids);
        //删除库存
        beanAppProductInventoryMapper.deletesByProductId(ids);
        //删除产品
        mapper.deletes(ids);
        return rs;
    }

    @Override
    public Result saveData(BeanAppProduct bean,
                           HttpServletRequest request,
                           MultipartFile file,
                           MultipartFile photo1,
                           MultipartFile photo2,
                           MultipartFile photo3,
                           MultipartFile photo4,
                           MultipartFile photo5, Result rs,
                           List<Long> photoIds) throws Exception {
        DataUtils.trim(bean);

        if (null == bean.getId()){
            BeanAppProductPhoto pto1 = null;
            BeanAppProductPhoto pto2 = null;
            BeanAppProductPhoto pto3 = null;
            BeanAppProductPhoto pto4 = null;
            BeanAppProductPhoto pto5 = null;
            //新增
            //上传图片
            if ( !file.isEmpty()){
            String logPath = uploadImg(file,request);
            bean.setLogoPath(logPath);
            }
            bean.setCreateTime(new Date());
             mapper.insertOverride(bean);
            Long id = bean.getId();
            List<BeanAppProductPhoto> ptos =null;
            //上传轮播图
            if (!photo1.isEmpty()){
                String logPath = uploadImg(photo2,request);
                pto1 = new BeanAppProductPhoto();
                pto1.setLogoPath(logPath);
                pto1.setCreateTime(new Date());
                pto1.setProductId(id);
                if(ptos==null){
                    ptos = new ArrayList<BeanAppProductPhoto>();
                }
                ptos.add(pto1);
            }
            if (!photo2.isEmpty()){
                String logPath = uploadImg(photo2,request);
                pto2 = new BeanAppProductPhoto();
                pto2.setLogoPath(logPath);
                pto2.setCreateTime(new Date());
                pto2.setProductId(id);
                if(ptos==null){
                    ptos = new ArrayList<BeanAppProductPhoto>();
                }
                ptos.add(pto2);
            }
            if (!photo3.isEmpty()){
                String logPath = uploadImg(photo3,request);
                pto3 = new BeanAppProductPhoto();
                pto3.setLogoPath(logPath);
                pto3.setCreateTime(new Date());
                pto3.setProductId(id);
                if(ptos==null){
                    ptos = new ArrayList<BeanAppProductPhoto>();
                }
                ptos.add(pto3);
            }
            if (!photo4.isEmpty()){
                String logPath = uploadImg(photo4,request);
                pto4 = new BeanAppProductPhoto();
                pto4.setLogoPath(logPath);
                pto4.setCreateTime(new Date());
                pto4.setProductId(id);
                if(ptos==null){
                    ptos = new ArrayList<BeanAppProductPhoto>();
                }
                ptos.add(pto4);
            }
            if (!photo5.isEmpty()){
                String logPath = uploadImg(photo5,request);
                pto5 = new BeanAppProductPhoto();
                pto5.setLogoPath(logPath);
                pto5.setCreateTime(new Date());
                pto5.setProductId(id);
                if(ptos==null){
                    ptos = new ArrayList<BeanAppProductPhoto>();
                }
                ptos.add(pto5);
            }
            if(null!=ptos&&ptos.size()>0){
            beanAppProductPhotoMapper.inserts(ptos);
            }
        }
        else{
            //修改
            if ( !file.isEmpty()){
                String logPath = uploadImg(file,request);
                bean.setLogoPath(logPath);
            }
            mapper.update(bean);
            //上传轮播图
            if (!photo1.isEmpty()){
                String logPath = uploadImg(photo1,request);
                BeanAppProductPhoto pto = new BeanAppProductPhoto();
                pto.setLogoPath(logPath);
                if (null == photoIds.get(0)){
                    //新增
                    pto.setProductId(bean.getId());
                    pto.setCreateTime(new Date());
                    beanAppProductPhotoMapper.insert(pto);
                }else{
                pto.setId(photoIds.get(0));
                beanAppProductPhotoMapper.update(pto);
                }
            }
            if (!photo2.isEmpty()){
                String logPath = uploadImg(photo2,request);
                BeanAppProductPhoto pto = new BeanAppProductPhoto();
                pto.setLogoPath(logPath);
                if (null == photoIds.get(1)){
                    //新增
                    pto.setProductId(bean.getId());
                    pto.setCreateTime(new Date());
                    beanAppProductPhotoMapper.insert(pto);
                }else{
                    pto.setId(photoIds.get(1));
                    beanAppProductPhotoMapper.update(pto);
                }
            }
            if (!photo3.isEmpty()){
                String logPath = uploadImg(photo3,request);
                BeanAppProductPhoto pto = new BeanAppProductPhoto();
                pto.setLogoPath(logPath);
                if (null == photoIds.get(2)){
                    //新增
                    pto.setProductId(bean.getId());
                    pto.setCreateTime(new Date());
                    beanAppProductPhotoMapper.insert(pto);
                }else{
                    pto.setId(photoIds.get(2));
                    beanAppProductPhotoMapper.update(pto);
                }
            }
            if (!photo4.isEmpty()){
                String logPath = uploadImg(photo4,request);
                BeanAppProductPhoto pto = new BeanAppProductPhoto();
                pto.setLogoPath(logPath);
                if (null == photoIds.get(3)){
                    //新增
                    pto.setProductId(bean.getId());
                    pto.setCreateTime(new Date());
                    beanAppProductPhotoMapper.insert(pto);
                }else{
                    pto.setId(photoIds.get(3));
                    beanAppProductPhotoMapper.update(pto);
                }
            }
            if (!photo5.isEmpty()){
                String logPath = uploadImg(photo5,request);
                BeanAppProductPhoto pto = new BeanAppProductPhoto();
                pto.setLogoPath(logPath);
                if (null == photoIds.get(4)){
                    //新增
                    pto.setProductId(bean.getId());
                    pto.setCreateTime(new Date());
                    beanAppProductPhotoMapper.insert(pto);
                }else{
                    pto.setId(photoIds.get(4));
                    beanAppProductPhotoMapper.update(pto);
                }
            }
        }
        return rs;
    }

    private String uploadImg(MultipartFile file,HttpServletRequest request) throws Exception {
        if(!file.isEmpty()){
            byte[] bytes = file.getBytes();
            String logPath = FileUploadUtils.uploadFile(bytes,"upload/public", "2", request);
            return logPath;
        }
        return null;
    }

    @Override
    public Result offShelves(Result rs, List<Long> ids) {
        beanAppProductInventoryMapper.offShelves(ids);
        return rs;
    }
}
