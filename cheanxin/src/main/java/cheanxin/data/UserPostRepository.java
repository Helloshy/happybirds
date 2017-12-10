package cheanxin.data;

import cheanxin.domain.PostAuthority;
import cheanxin.domain.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Jawinton on 16/12/21.
 */
@Repository
public interface UserPostRepository extends JpaRepository<UserPost, Long> {
    List<UserPost> findByUsernameAndEnabled(String username, boolean enabled);
    List<UserPost> findByUsernameInAndEnabled(Collection<String> usernames, boolean enabled);
    List<UserPost> findByUsernameInAndPostIdAndEnabled(Collection<String> usernames, long postId, boolean enabled);
}
