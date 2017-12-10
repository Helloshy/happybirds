package cheanxin.state.impl;

import cheanxin.enums.Authority;
import cheanxin.service.LoanService;
import cheanxin.state.LoanState;

/**
 * 第二次复审（补充材料后复审）被拒绝后结束状态
 * Created by Jawinton on 2017/2/22.
 */
public class MaterialsReviewAbortedLoanState extends LoanState {
    public static final int status = 11;

    public MaterialsReviewAbortedLoanState(LoanService loanService) {
        super(loanService);
    }

    @Override
    public int getStatus() {
        return MaterialsReviewAbortedLoanState.status;
    }

    @Override
    public Authority[] getReadAuthorities() {
        return new Authority[]{Authority.ROLE_LOAN_SECOND_READ};
    }
}
