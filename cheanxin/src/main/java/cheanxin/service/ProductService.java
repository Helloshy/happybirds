package cheanxin.service;

import cheanxin.domain.Product;
import cheanxin.domain.User;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 产品service类
 * Created by Jawinton on 17/01/07.
 */
public interface ProductService {
    /**
     * 保存产品
     * @param unsavedProduct
     * @return
     */
    Product save(Product unsavedProduct);

    /**
     * 删除产品
     * @param id
     */
    void remove(long id);

    /**
     * 获取产品详情
     * @param id
     * @return
     */
    Product findOne(long id);

    /**
     * 分页获取产品信息列表
     * @param cityIds 城市id列表
     * @param productTemplateId 产品模板id
     * @param name 产品名称
     * @param status 产品状态
     * @param page
     * @param size
     * @return
     */
    Page<Product> list(Set<Long> cityIds, long productTemplateId, String name, int status, int page, int size);

    /**
     * 通过产品id列表获取产品详情列表
     * @param productIds
     * @return
     */
    List<Product> list(Collection<Long> productIds);

    /**
     * 获取城市下的产品列表
     * @param cityId
     * @param status
     * @param enabled
     * @return
     */
    List<Product> list(long cityId, int status, boolean enabled);

    /**
     * 审核产品
     * @param user
     * @param fromProduct
     * @param toProduct
     * @return
     */
    Product review(User user, Product fromProduct, Product toProduct);

    /**
     * 判断产品是否是产品是否有子产品
     * 模板产品才有子产品的概念
     * @param productTemplateId
     * @return
     */
    boolean hasChildProducts(long productTemplateId);
}
