package cheanxin.state.impl;

import cheanxin.domain.Loan;
import cheanxin.domain.User;
import cheanxin.service.LoanService;
import cheanxin.state.LoanState;
import org.springframework.util.Assert;

/**
 * 方案调整后待复审状态
 * Created by Jawinton on 2017/2/22.
 */
public class SchemeReviewingLoanState extends SecondReviewingLoanState {
    public static final int status = 12;

    public SchemeReviewingLoanState(LoanService loanService) {
        super(loanService);
    }

    @Override
    public int getStatus() {
        return SchemeReviewingLoanState.status;
    }

    /**
     * 方案调整后复审被拒绝直接结束贷款
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     */
    @Override
    public Loan reject(User user, Loan unsavedLoan, Loan savedLoan) {
        Assert.notNull(unsavedLoan.getReviewRemark(), "Review remark can not be null.");

        return super.updateLoanStatus(user, unsavedLoan, savedLoan, SchemeReviewAbortedLoanState.status);
    }
}
