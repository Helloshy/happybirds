package cheanxin.state.impl;

import cheanxin.enums.Authority;
import cheanxin.service.LoanService;
import cheanxin.state.LoanState;

/**
 * 放弃补充材料后状态
 * Created by Jawinton on 2017/2/22.
 */
public class MaterialsAbortedLoanState extends LoanState {
    public static final int status = 15;

    public MaterialsAbortedLoanState(LoanService loanService) {
        super(loanService);
    }

    @Override
    public int getStatus() {
        return MaterialsAbortedLoanState.status;
    }

    @Override
    public Authority[] getReadAuthorities() {
        return new Authority[]{Authority.ROLE_LOAN_SECOND_READ};
    }
}
