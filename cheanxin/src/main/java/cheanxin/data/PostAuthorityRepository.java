package cheanxin.data;

import cheanxin.domain.PostAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Jawinton on 16/12/21.
 */
@Repository
public interface PostAuthorityRepository extends JpaRepository<PostAuthority, Long> {
    List<PostAuthority> findByPostIdIn(Collection<Long> postIds);
}
