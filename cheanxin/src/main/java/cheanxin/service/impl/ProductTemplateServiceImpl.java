package cheanxin.service.impl;

import cheanxin.data.ProductTemplateRepository;
import cheanxin.domain.ProductTemplate;
import cheanxin.service.ProductTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Jawinton on 17/01/07.
 */
@Service
public class ProductTemplateServiceImpl implements ProductTemplateService {
    @Autowired
    ProductTemplateRepository productTemplateRepository;

    @Override
    public ProductTemplate save(ProductTemplate unsavedProductTemplate) {
        return productTemplateRepository.save(unsavedProductTemplate);
    }

    @Override
    public void remove(long id) {
        productTemplateRepository.delete(id);
    }

    @Override
    public ProductTemplate findOne(long id) {
        return productTemplateRepository.findOne(id);
    }

    @Override
    public Page<ProductTemplate> list(String name, int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        if (name == null || name.isEmpty()) {
            return productTemplateRepository.findAll(pageable);
        }
        return productTemplateRepository.findByNameIgnoreCaseLike("%" + name + "%", pageable);
    }
}
