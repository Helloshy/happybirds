package cheanxin.service;

import cheanxin.domain.LoanLog;

import java.util.List;

/**
 * 贷款操作日志service类
 * Created by Jawinton on 17/02/08.
 */
public interface LoanLogService {
    /**
     * 保存贷款操作日志
     * @param unsavedLoanLog
     * @return
     */
    LoanLog save(LoanLog unsavedLoanLog);

    /**
     * 判断贷款操作日志是否存在
     * @param loanId
     * @param fromStatus
     * @param toStatus
     * @return
     */
    boolean isExists(long loanId, int fromStatus, int toStatus);

    /**
     * 贷款日志列表
     * @param loanId
     * @param fromStatus
     * @param toStatus
     * @return
     */
    List<LoanLog> list(long loanId, int fromStatus, int toStatus);
}
