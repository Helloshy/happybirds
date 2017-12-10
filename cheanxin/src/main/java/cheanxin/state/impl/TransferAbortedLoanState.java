package cheanxin.state.impl;

import cheanxin.enums.Authority;
import cheanxin.service.LoanService;
import cheanxin.state.LoanState;

/**
 * 无法过户放弃贷款（过户后放款，结束）
 * Created by Jawinton on 2017/2/22.
 */
public class TransferAbortedLoanState extends LoanState {
    public static final int status = 20;

    public TransferAbortedLoanState(LoanService loanService) {
        super(loanService);
    }

    @Override
    public int getStatus() {
        return TransferAbortedLoanState.status;
    }

    @Override
    public Authority[] getReadAuthorities() {
        return new Authority[]{Authority.ROLE_LOAN_TRANSFER_ABORT_READ};
    }
}
