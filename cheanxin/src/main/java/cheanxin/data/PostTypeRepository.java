package cheanxin.data;

import cheanxin.domain.PostType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jawinton on 16/12/14.
 */
@Repository
public interface PostTypeRepository extends JpaRepository<PostType, Long> {
    List<PostType> findAllByEnabledOrderBySortIndexAsc(Boolean enabled);
}
