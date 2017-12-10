package cheanxin.data;

import cheanxin.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Jawinton on 16/12/14.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Page<Product> findByProductTemplateId(long productTemplateId, Pageable pageable);
    List<Product> findAllByIdIn(Collection<Long> productIds);
    List<Product> findAllByCityIdAndStatusAndEnabled(long cityId, int status, boolean enabled);
}
