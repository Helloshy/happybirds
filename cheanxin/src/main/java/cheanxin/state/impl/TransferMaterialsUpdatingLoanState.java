package cheanxin.state.impl;

import cheanxin.domain.Loan;
import cheanxin.domain.User;
import cheanxin.service.LoanService;

/**
 * 过户材料退回待补充（过户后放款）
 * Created by Jawinton on 2017/2/22.
 */
public class TransferMaterialsUpdatingLoanState extends TransferUpdatingLoanState {
    public static final int status = 21;

    public TransferMaterialsUpdatingLoanState(LoanService loanService) {
        super(loanService);
    }

    @Override
    public int getStatus() {
        return TransferMaterialsUpdatingLoanState.status;
    }

    /**
     * 提交后变成补充材料后放款待审核
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     */
    @Override
    public Loan submit(User user, Loan unsavedLoan, Loan savedLoan) {
        return super.updateLoanStatus(user, unsavedLoan, savedLoan, TransferMaterialsReviewingLoanState.status);
    }
}
