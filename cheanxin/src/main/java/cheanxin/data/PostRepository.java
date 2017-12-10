package cheanxin.data;

import cheanxin.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jawinton on 16/12/14.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    Page<Post> findByNameIgnoreCaseContainingAndEnabled(String name, boolean enabled, Pageable pageable);
    Page<Post> findByNameIgnoreCaseContaining(String name, Pageable pageable);
    Page<Post> findByEnabled(boolean enabled, Pageable pageable);
    List<Post> findByEnabled(boolean enabled);
}
