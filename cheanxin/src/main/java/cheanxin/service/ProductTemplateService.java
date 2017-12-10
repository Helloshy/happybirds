package cheanxin.service;

import cheanxin.domain.Product;
import cheanxin.domain.ProductTemplate;
import cheanxin.domain.User;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 产品模版service类
 * Created by Jawinton on 17/01/07.
 */
public interface ProductTemplateService {
    /**
     * 保存产品模版
     * @param unsavedProductTemplate
     * @return
     */
    ProductTemplate save(ProductTemplate unsavedProductTemplate);

    /**
     * 删除产品模版
     * @param id
     */
    void remove(long id);

    /**
     * 获取产品模版详情
     * @param id
     * @return
     */
    ProductTemplate findOne(long id);

    /**
     * 分页获取产品模版列表
     * @param name 产品名称
     * @param page
     * @param size
     * @return
     */
    Page<ProductTemplate> list(String name, int page, int size);
}
