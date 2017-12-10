package cheanxin.service.impl;

import cheanxin.data.PostTypeRepository;
import cheanxin.data.ProductLogRepository;
import cheanxin.domain.PostType;
import cheanxin.domain.ProductLog;
import cheanxin.service.PostTypeService;
import cheanxin.service.ProductLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jawinton on 16/12/21.
 */
@Service
public class ProductLogServiceImpl implements ProductLogService {
    @Autowired
    ProductLogRepository productLogRepository;

    @Override
    public ProductLog save(ProductLog productLog) {
        return productLogRepository.save(productLog);
    }
}
