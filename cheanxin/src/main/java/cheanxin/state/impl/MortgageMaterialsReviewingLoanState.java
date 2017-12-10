package cheanxin.state.impl;

import cheanxin.domain.Loan;
import cheanxin.domain.User;
import cheanxin.service.LoanService;
import org.springframework.util.Assert;

/**
 * 补充材料后放款待审核（抵押后放款）
 * Created by Jawinton on 2017/2/22.
 */
public class MortgageMaterialsReviewingLoanState extends MortgageReviewingLoanState {
    public static final int status = 33;

    public MortgageMaterialsReviewingLoanState(LoanService loanService) {
        super(loanService);
    }

    @Override
    public int getStatus() {
        return MortgageMaterialsReviewingLoanState.status;
    }

    /**
     * 补充材料后放款审核被拒绝直接结束
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     */
    @Override
    public Loan reject(User user, Loan unsavedLoan, Loan savedLoan) {
        Assert.notNull(unsavedLoan.getReviewRemark(), "Review remark can not be null.");

        return super.updateLoanStatus(user, unsavedLoan, savedLoan, MortgageMaterialsAbortedLoanState.status);
    }
}
