package cheanxin.data;

import cheanxin.domain.ProductTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by Jawinton on 17/04/13.
 */
@Repository
public interface ProductTemplateRepository extends JpaRepository<ProductTemplate, Long>, JpaSpecificationExecutor<ProductTemplate> {
    Page<ProductTemplate> findByNameIgnoreCaseLike(String name, Pageable pageable);
}
