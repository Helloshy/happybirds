package cheanxin.service.impl;

import cheanxin.data.ProductRepository;
import cheanxin.domain.Product;
import cheanxin.domain.ProductLog;
import cheanxin.domain.User;
import cheanxin.enums.ProductStatusTransfer;
import cheanxin.exceptions.UnauthorizedException;
import cheanxin.service.ProductLogService;
import cheanxin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Jawinton on 17/01/07.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductLogService productLogService;

    @Override
    public Product save(Product unsavedProduct) {
        return productRepository.save(unsavedProduct);
    }

    @Override
    public void remove(long id) {
        productRepository.delete(id);
    }

    @Override
    public Product findOne(long id) {
        return productRepository.findOne(id);
    }

    @Override
    public Page<Product> list(Set<Long> cityIds, long productTemplateId, String name, int status, int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        SearchProduct searchProduct = new SearchProduct();
        searchProduct.setName(name);
        searchProduct.setProductTemplateId(productTemplateId);
        searchProduct.setCityIds(cityIds);
        searchProduct.setStatus(status);
        Specification<Product> specification = this.getWhereClause(searchProduct);
        return productRepository.findAll(specification, pageable);
    }

    @Override
    public List<Product> list(Collection<Long> productIds) {
        if (productIds == null || productIds.isEmpty()) {
            return new ArrayList<>();
        }
        return productRepository.findAllByIdIn(productIds);
    }

    @Override
    public List<Product> list(long cityId, int status, boolean enabled) {
        return productRepository.findAllByCityIdAndStatusAndEnabled(cityId, status, enabled);
    }

    @Override
    @Transactional
    public Product review(User user, Product fromProduct, Product toProduct) {
        if (user == null || fromProduct == null || fromProduct.getStatus() == null || toProduct == null || toProduct.getStatus() == null) {
            return null;
        }

        ProductStatusTransfer productStatusTransfer = ProductStatusTransfer.checkAuthority(user, fromProduct.getStatus().intValue(), toProduct.getStatus().intValue());

        // save from status before save product.
        fromProduct.setStatus(productStatusTransfer.getToStatus().value());
        Product savedProduct = save(fromProduct);

        // save product operation log.
        ProductLog productLog = new ProductLog(
                savedProduct.getId(),
                user.getUsername(),
                productStatusTransfer.getValue(),
                toProduct.getRemark(),
                System.currentTimeMillis() / 1000);
        productLogService.save(productLog);

        return savedProduct;
    }

    @Override
    public boolean hasChildProducts(long productTemplateId) {
        return productRepository.findByProductTemplateId(productTemplateId, new PageRequest(0, 1)).hasContent();
    }

    private class SearchProduct {
        private Set<Long> cityIds;
        private long productTemplateId;
        private String name;
        private int status;

        public SearchProduct() {}


        public Set<Long> getCityIds() {
            return cityIds;
        }

        public void setCityIds(Set<Long> cityIds) {
            this.cityIds = cityIds;
        }

        public long getProductTemplateId() {
            return productTemplateId;
        }

        public void setProductTemplateId(long productTemplateId) {
            this.productTemplateId = productTemplateId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    /**
     * generate where clause dynamically.
     * @param searchProduct
     * @return
     */
    private Specification<Product> getWhereClause(final SearchProduct searchProduct){
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                if (searchProduct.getProductTemplateId() >= 0L) {
                    predicate.add(cb.equal(root.get("productTemplateId").as(Long.class), searchProduct.getProductTemplateId()));
                } else if (searchProduct.getProductTemplateId() == -1L) {
                    predicate.add(cb.gt(root.get("productTemplateId").as(Long.class), 0L));
                }
                if (searchProduct.getCityIds() != null && !searchProduct.getCityIds().isEmpty()) {
                    predicate.add(root.get("cityId").as(Long.class).in(searchProduct.getCityIds()));
                }
                if (searchProduct.getName() != null && !searchProduct.getName().trim().isEmpty()) {
                    predicate.add(cb.like(root.get("name").as(String.class), "%" + searchProduct.getName() + "%"));
                }
                if (searchProduct.getStatus() >= 0) {
                    predicate.add(cb.equal(root.get("status").as(Integer.class), searchProduct.getStatus()));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }
}
