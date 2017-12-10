package cheanxin.state.impl;

import cheanxin.enums.Authority;
import cheanxin.service.LoanService;
import cheanxin.state.LoanState;

/**
 * 第二次放弃签约后贷款结束状态
 * Created by Jawinton on 2017/2/22.
 */
public class ContractAbortedLoanState extends LoanState {
    public static final int status = 16;

    public ContractAbortedLoanState(LoanService loanService) {
        super(loanService);
    }

    @Override
    public int getStatus() {
        return ContractAbortedLoanState.status;
    }

    @Override
    public Authority[] getReadAuthorities() {
        return new Authority[]{Authority.ROLE_LOAN_CONTRACT_READ};
    }
}
