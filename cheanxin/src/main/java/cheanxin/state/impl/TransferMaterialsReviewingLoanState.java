package cheanxin.state.impl;

import cheanxin.domain.Loan;
import cheanxin.domain.User;
import cheanxin.service.LoanService;
import org.springframework.util.Assert;

/**
 * 补充过户材料后待审核（过户后放款）
 * Created by Jawinton on 2017/2/22.
 */
public class TransferMaterialsReviewingLoanState extends TransferReviewingLoanState {
    public static final int status = 32;

    public TransferMaterialsReviewingLoanState(LoanService loanService) {
        super(loanService);
    }

    @Override
    public int getStatus() {
        return TransferMaterialsReviewingLoanState.status;
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

        return super.updateLoanStatus(user, unsavedLoan, savedLoan, TransferMaterialsAbortedLoanState.status);
    }
}
