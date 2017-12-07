package com.kapphk.web.service.web.impl.shop;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.product.BeanAppProductInventory;
import com.kapphk.web.bean.product.BeanAppProductOrder;
import com.kapphk.web.dao.mapper.product.BeanAppProductInventoryMapper;
import com.kapphk.web.dao.mapper.product.BeanAppProductOrderMapper;
import com.kapphk.web.service.web.imethod.shop.BeanProductInventoryService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.JXLExportUtils;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

/**商品库存业务处理
 * Created by shy on 2016/12/20.
 */

@Service
public class BeanProductInventoryServiceImpl implements BeanProductInventoryService {
    @Autowired
    private BeanAppProductInventoryMapper mapper;

    @Autowired
    private BeanAppProductOrderMapper orderMapper;
    
    @Override
    public Result searchList(Result rs, Map<String, Object> param, GridReq gridReq) {
        List<Map<String,Object>> list = mapper.findListByParam(param,gridReq.getPage(),gridReq.getRows());
        int count = mapper.findListByParamCount(param);
        rs = DataUtils.mergeData(count, list, rs);
        return rs;
    }

    @Override
    public Result saveData(Result rs, BeanAppProductInventory bean) throws Exception {
        DataUtils.trim(bean);
        bean.setCreateTime(new Date());
        if (ValidateUtils.isBlank(bean.getId())){
            /*List<BeanAppProductInventory> pis = new ArrayList<BeanAppProductInventory>();
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("productId",bean.getProductId());
            //查询该该商品所有的库存
            List<Map<String, Object>> maps =  mapper.findListByParam(param,null,null);
            Map<String,Map<String,Object>> tempMap = new HashMap<String,Map<String,Object>>();

            for (Map<String,Object> map:maps) {
                tempMap.put(((String) map.get("colorSize")).toUpperCase(),map);
            }
            //添加
            String [] colors = bean.getColor().split(",");
            String [] sizes = bean.getSize().split(",");
            for (String color:colors) {
                color = color.toUpperCase();
                for (String size:sizes) {
                    BeanAppProductInventory pi = new BeanAppProductInventory();
                    size = size.toUpperCase();
                    pi.setProductId(bean.getProductId());
                    pi.setCreateTime(new Date());
                    pi.setColor(color);
                    pi.setSize(size);
                    pi.setPrice(bean.getPrice());
                    pi.setStockQty(bean.getStockQty());
                    Map<String, Object> tempMap2 = tempMap.get(StringUtils.trim(color+size));
                    if (null == tempMap2){
                        //不存在 则添加
                        pis.add(pi);
                        mapper.insert(pi);
                    }else {
                        //存在则更新
                        pi.setId((Long)tempMap2.get("id"));
                        pi.setCreateTime((Date) tempMap2.get("createTime"));
                        mapper.update(pi);
                    }
                }
            }*/
        	if(!ValidateUtils.isBlank(bean.getColor())){
        		bean.setColorName("颜色");
        	}else{
        		bean.setColor("");
        		bean.setColorName("");
        	}
        	if(!ValidateUtils.isBlank(bean.getSize())){
        		bean.setSizeName("尺码");
        	}else{
        		bean.setSize("");
        		bean.setSizeName("");
        	}
        	BeanAppProductInventory inventory = new BeanAppProductInventory();
        	inventory.setProductId(bean.getProductId());
        	inventory.setColor(bean.getColor());
        	inventory.setSize(bean.getSize());
        	int count = mapper.findByPageCount(inventory);
        	if(count == 0){
        		mapper.insert(bean);
        	}else{
        		rs.setStatus(Contents.ERROR);
        		rs.setMsg("该属性搭配已存");
        	}
        }else {
            //修改
            mapper.update(bean);
        }
        return rs;
    }

    @Override
    public Result delete(Result rs, Long ids) throws Exception {
    	BeanAppProductInventory inventory = mapper.findById(ids);
    	BeanAppProductOrder order = new BeanAppProductOrder();
    	order.setProductId(inventory.getProductId());
    	order.setColor(inventory.getColor());
    	order.setSize(inventory.getSize());
    	int count = orderMapper.findByPageCount(order);
    	if(count == 0){
    		rs.put("count",mapper.delete(ids));
    	}else{
    		rs.setStatus(Contents.ERROR);
    		rs.setMsg("该商品对应的属性搭配已存在订单，不可删除");
    	}
        return rs;
    }

    @Override
    public void exportExcelProductInventory(Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) {
        List<Map<String,Object>> list = mapper.findListByParam(param,null,null);
        JXLExportUtils export = new JXLExportUtils("商城产品销售信息");
        export.addExportType(Map.class);
        try {
            export.addExportProperty("商品分类", "itemName");
            export.addExportProperty("商品描述", "itemRemark");
            export.addExportProperty("属性搭配", "colorSize");
            export.addExportProperty("单价/元", "price");
            export.addExportProperty("库存", "stockQty");
            export.addExportProperty("添加时间", "createTime");

            export.writerHead(response);
            export.writerTitle(response);
            export.writerData(list);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Result getData(Result rs, Long id) {
        rs.put("data",mapper.findDetailById(id));
        return rs;
    }
}
