package cheanxin.state.impl;

import cheanxin.enums.Authority;
import cheanxin.service.LoanService;
import cheanxin.state.LoanState;

/**
 * 第二次过户材料审核不通过（过户后放款，结束）
 * Created by Jawinton on 2017/2/22.
 */
public class TransferMaterialsAbortedLoanState extends LoanState {
    public static final int status = 34;

    public TransferMaterialsAbortedLoanState(LoanService loanService) {
        super(loanService);
    }

    @Override
    public int getStatus() {
        return TransferMaterialsAbortedLoanState.status;
    }

    @Override
    public Authority[] getReadAuthorities() {
        return new Authority[]{Authority.ROLE_LOAN_RELEASE_READ};
    }
}
