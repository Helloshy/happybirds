package cheanxin.service;

import cheanxin.domain.ProductLog;

/**
 * 产品操作日志service类
 * Created by Jawinton on 16/12/21.
 */
public interface ProductLogService {
    /**
     * 添加产品操作日志
     * @param productLog
     * @return
     */
    ProductLog save(ProductLog productLog);
}
