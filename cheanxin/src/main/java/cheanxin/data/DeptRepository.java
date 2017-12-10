package cheanxin.data;

import cheanxin.domain.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Jawinton on 16/12/14.
 */
@Repository
public interface DeptRepository extends JpaRepository<Dept, Long> {
    List<Dept> findAllByLevelAndEnabled(int level, boolean enabled);
    List<Dept> findAllByParentDeptIdAndEnabled(long parentDeptId, boolean enabled);
    List<Dept> findAllByEnabled(boolean enabled);
    List<Dept> findAllByIdIn(Collection<Long> deptIds);
}
