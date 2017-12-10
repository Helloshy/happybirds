package cheanxin.state.impl;

import cheanxin.enums.Authority;
import cheanxin.service.LoanService;
import cheanxin.state.LoanState;

/**
 * 第二次过户抵押材料审核不通过（抵押后放款，结束）
 * Created by Jawinton on 2017/2/22.
 */
public class MortgageMaterialsAbortedLoanState extends LoanState {
    public static final int status = 35;

    public MortgageMaterialsAbortedLoanState(LoanService loanService) {
        super(loanService);
    }

    @Override
    public int getStatus() {
        return MortgageMaterialsAbortedLoanState.status;
    }

    @Override
    public Authority[] getReadAuthorities() {
        return new Authority[]{Authority.ROLE_LOAN_MORTGAGE_READ};
    }
}
