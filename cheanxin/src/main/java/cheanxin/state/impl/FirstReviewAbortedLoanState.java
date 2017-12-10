package cheanxin.state.impl;

import cheanxin.enums.Authority;
import cheanxin.service.LoanService;
import cheanxin.state.LoanState;

/**
 * 预审直接拒绝后贷款结束状态
 * Created by Jawinton on 2017/2/22.
 */
public class FirstReviewAbortedLoanState extends LoanState {
    public static final int status = 0;

    public FirstReviewAbortedLoanState(LoanService loanService) {
        super(loanService);
    }

    @Override
    public int getStatus() {
        return FirstReviewAbortedLoanState.status;
    }

    @Override
    public Authority[] getReadAuthorities() {
        return new Authority[]{Authority.ROLE_LOAN_FIRST_READ};
    }
}
