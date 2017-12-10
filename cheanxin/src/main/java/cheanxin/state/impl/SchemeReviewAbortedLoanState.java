package cheanxin.state.impl;

import cheanxin.enums.Authority;
import cheanxin.service.LoanService;
import cheanxin.state.LoanState;

/**
 * 方案调整后第二次复审被拒绝后结束状态
 * Created by Jawinton on 2017/2/22.
 */
public class SchemeReviewAbortedLoanState extends LoanState {
    public static final int status = 13;

    public SchemeReviewAbortedLoanState(LoanService loanService) {
        super(loanService);
    }

    @Override
    public int getStatus() {
        return SchemeReviewAbortedLoanState.status;
    }

    @Override
    public Authority[] getReadAuthorities() {
        return new Authority[]{Authority.ROLE_LOAN_SECOND_READ};
    }
}
