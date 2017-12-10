package cheanxin.state.impl;

import cheanxin.domain.Loan;
import cheanxin.domain.User;
import cheanxin.service.LoanService;

/**
 * 抵押材料退回待补充（抵押后放款）
 * Created by Jawinton on 2017/2/22.
 */
public class MortgageMaterialsUpdatingLoanState extends MortgageUpdatingLoanState {
    public static final int status = 28;

    public MortgageMaterialsUpdatingLoanState(LoanService loanService) {
        super(loanService);
    }

    @Override
    public int getStatus() {
        return MortgageMaterialsUpdatingLoanState.status;
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
        return super.updateLoanStatus(user, unsavedLoan, savedLoan, MortgageMaterialsReviewingLoanState.status);
    }
}
