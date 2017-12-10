package cheanxin.state.impl;

import cheanxin.enums.Authority;
import cheanxin.service.LoanService;
import cheanxin.state.LoanState;

/**
 * 待还款（过户后放款）
 * Created by Jawinton on 2017/2/22.
 */
public class TransferRepaymentLoanState extends LoanState {
    public static final int status = 31;

    public TransferRepaymentLoanState(LoanService loanService) {
        super(loanService);
    }

    @Override
    public int getStatus() {
        return TransferRepaymentLoanState.status;
    }

    @Override
    public Authority[] getReadAuthorities() {
        return new Authority[]{Authority.ROLE_LOAN_RELEASE_MORTGAGE_READ, Authority.ROLE_FIRST_REVIEWER};
    }
}
