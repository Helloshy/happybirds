package cheanxin.data;

import cheanxin.domain.LoanLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jawinton on 17/02/08.
 */
@Repository
public interface LoanLogRepository extends JpaRepository<LoanLog, Long> {
    List<LoanLog> findByLoanIdAndFromStatusAndToStatus(long loanId, int fromStatus, int toStatus);
    int countByLoanIdAndFromStatusAndToStatus(long loanId, int fromStatus, int toStatus);
    int countByLoanIdAndToStatus(long loanId, int toStatus);
}
