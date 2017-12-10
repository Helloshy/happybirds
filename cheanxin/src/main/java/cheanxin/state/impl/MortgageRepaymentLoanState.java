package cheanxin.state.impl;

import cheanxin.enums.Authority;
import cheanxin.service.LoanService;
import cheanxin.state.LoanState;

/**
 * 待还款（抵押后放款）
 * Created by Jawinton on 2017/2/22.
 */
public class MortgageRepaymentLoanState extends LoanState {
    public static final int status = 36;

    public MortgageRepaymentLoanState(LoanService loanService) {
        super(loanService);
    }

    @Override
    public int getStatus() {
        return MortgageRepaymentLoanState.status;
    }

    @Override
    public Authority[] getReadAuthorities() {
        return new Authority[]{Authority.ROLE_LOAN_MORTGAGE_RELEASE_READ, Authority.ROLE_FINANCE};
    }
}
