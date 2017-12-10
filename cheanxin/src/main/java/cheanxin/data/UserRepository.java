package cheanxin.data;

import cheanxin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jawinton on 16/12/14.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserDao, JpaSpecificationExecutor<User> {
    User findByUsernameIgnoringCase(String username);
    User findByMobileNumber(String mobileNumber);
    List<User> findAllByDeptIdAndEnabled(long deptId, boolean enabled);
    List<User> findAllByEnabled(boolean enabled);
}
