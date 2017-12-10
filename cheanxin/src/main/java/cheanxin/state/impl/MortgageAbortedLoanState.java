package cheanxin.state.impl;

import cheanxin.enums.Authority;
import cheanxin.service.LoanService;
import cheanxin.state.LoanState;

/**
 * 无法处理抵押放弃贷款（抵押后放款，结束）
 * Created by Jawinton on 2017/2/22.
 */
public class MortgageAbortedLoanState extends LoanState {
    public static final int status = 27;

    public MortgageAbortedLoanState(LoanService loanService) {
        super(loanService);
    }

    @Override
    public int getStatus() {
        return MortgageAbortedLoanState.status;
    }

    @Override
    public Authority[] getReadAuthorities() {
        return new Authority[]{Authority.ROLE_LOAN_MORTGAGE_READ};
    }
}
