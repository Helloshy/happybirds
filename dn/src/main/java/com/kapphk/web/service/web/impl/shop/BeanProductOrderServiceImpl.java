package com.kapphk.web.service.web.impl.shop;

import com.kapphk.web.bean.product.BeanAppProductOrder;
import com.kapphk.web.dao.mapper.product.BeanAppProductOrderMapper;
import com.kapphk.web.service.web.imethod.shop.BeanProductOrderService;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.JXLExportUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 商品订单业务处理
 * Created by shy on 2016/12/21.
 */

@Service
public class BeanProductOrderServiceImpl implements BeanProductOrderService {
    @Autowired
    private BeanAppProductOrderMapper mapper;

    @Override
    public Result searchList(Result rs, Map<String, Object> param, GridReq gridReq) throws Exception {
        List<Map<String,Object>> list = mapper.findListByParamExcel(param,gridReq.getPage(),gridReq.getRows());
        int count = mapper.findListByParamExcelCount(param);
        rs = DataUtils.mergeData(count, list, rs);
        return rs;
    }
    @Override
    public Result update(BeanAppProductOrder bean, Result rs) throws Exception {
        //状态设为待收货
        bean.setState(2);
        mapper.updateByOrderNo(bean);
        return rs;
    }

    /**
     * 详情
     */
    @Override
    public Result getData(Result rs, BeanAppProductOrder bean) throws Exception {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("orderNo",bean.getOrderNo());
        rs.put("data", mapper.findListByParamExcel(param,null,null));
        return rs;
    }

    @Override
    public void exportExcel(Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) {
        List<Map<String,Object>> list = mapper.findListByParamExcel(param,null,null);
        JXLExportUtils export = new JXLExportUtils("订单信息");
        export.addExportType(Map.class);
        try {
            export.addExportProperty("订单号", "orderNo");
            export.addExportProperty("订单状态", "state");
            export.addExportProperty("商品描述", "itemRemark");
            export.addExportProperty("商品单价/元", "price");
            export.addExportProperty("颜色", "color");
            export.addExportProperty("尺寸", "size");
            export.addExportProperty("购买数量", "qty");
            export.addExportProperty("欧币抵扣", "discount");
            export.addExportProperty("支付金额/元", "payAmount");
            export.addExportProperty("支付方式", "payMethod");
            export.addExportProperty("支付时间", "payTime");
            export.addExportProperty("配送方式", "deliveryMethod");
            export.addExportProperty("购买账号", "userName");
            export.addExportProperty("收货人", "deliveryName");
            export.addExportProperty("物流单号", "deliveryNo");

            export.writerHead(response);

            export.writerTitle(response);

            export.writerData(list);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public Result searchListForSellChart(Result rs, Map<String, Object> param, GridReq gridReq)throws Exception {

        List<Map<String,Object>> list = mapper.findSellChartByParam(param,gridReq.getPage(),gridReq.getRows());
        int count = mapper.findSellChartByParamCount(param);
        rs = DataUtils.mergeData(count, list, rs);
        return rs;

    }

    @Override
    public void exportExcelSellChart(Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) {
        List<Map<String,Object>> list = mapper.findSellChartByParam(param,null,null);
        JXLExportUtils export = new JXLExportUtils("商城销售信息");
        export.addExportType(Map.class);
        try {
            export.addExportProperty("商品描述", "itemRemark");
            export.addExportProperty("销售数量", "totalProductQty");
            export.addExportProperty("商品单价/元", "price");
            export.addExportProperty("estimatePayAmount", "理论销售金额");
            export.addExportProperty("使用红币数", "totalDiscountRed");
            export.addExportProperty("使用蓝币数", "totalDiscountBlue");
            export.addExportProperty("实际收入现金/元", "totalPayAmount");
            export.addExportProperty("更新时间", "create_time");

            export.writerHead(response);
            export.writerTitle(response);
            export.writerData(list);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Result searchListForProductSellChart(Result rs, Map<String, Object> param, GridReq gridReq) {

        List<Map<String,Object>> list = mapper.findProductSellChartByParam(param,gridReq.getPage(),gridReq.getRows());
        int count = mapper.findProductSellChartByParamCount(param);
        rs = DataUtils.mergeData(count, list, rs);
        return rs;
    }

    @Override
    public void exportExcelProductSellChart(Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) {
        List<Map<String,Object>> list = mapper.findProductSellChartByParam(param,null,null);
        JXLExportUtils export = new JXLExportUtils("商城产品销售信息");
        export.addExportType(Map.class);
        try {
            export.addExportProperty("用户账号", "userName");
            export.addExportProperty("真实姓名", "realName");
            export.addExportProperty("身份", "userIdentity");
            export.addExportProperty("商品描述", "productName");
            export.addExportProperty("购买总数", "totalProductQty");
            export.addExportProperty("更新时间", "createTime");

            export.writerHead(response);
            export.writerTitle(response);
            export.writerData(list);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public Result searchListForProductInventoryChart(Result rs, Map<String, Object> param, GridReq gridReq) {
        List<Map<String,Object>> list = mapper.findProductInventoryChartByParam(param,gridReq.getPage(),gridReq.getRows());
        int count = mapper.findProductInventoryChartByParamCount(param);
        rs = DataUtils.mergeData(count, list, rs);
        return rs;
    }

    @Override
    public void exportExcelProductInventoryChart(Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) {
        List<Map<String,Object>> list = mapper.findProductInventoryChartByParam(param,null,null);
        JXLExportUtils export = new JXLExportUtils("商城产品销售信息");
        export.addExportType(Map.class);
        try {
            export.addExportProperty("统计月份", "ym");
            export.addExportProperty("商品描述", "item_name");
            export.addExportProperty("属性搭配", "property");
            export.addExportProperty("单价/元", "price");
            export.addExportProperty("上月结存", "last_month_qty");
            export.addExportProperty("本月上架", "this_month_qty");
            export.addExportProperty("本月销售数量", "month_sales_qty");
            export.addExportProperty("理论销售金额/元", "estimatePayAmount");
            export.addExportProperty("使用红币数", "month_discount_red");
            export.addExportProperty("使用蓝币数", "month_discount_blue");
            export.addExportProperty("实际收入现金", "month_pay_amount");
            export.addExportProperty("本月库存量", "current_month_qty");
            export.addExportProperty("时间", "update_time");

            export.writerHead(response);
            export.writerTitle(response);
            export.writerData(list);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
