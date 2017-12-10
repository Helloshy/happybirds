package cheanxin.state.impl;

import cheanxin.service.LoanService;

/**
 * 预审退回，和草稿箱状态类似
 * Created by Jawinton on 2017/3/1.
 */
public class FirstReviewRejectedLoanState extends DraftUpdatingLoanState {
    public static final int status = 9;

    public FirstReviewRejectedLoanState(LoanService loanService) {
        super(loanService);
    }

    @Override
    public int getStatus() {
        return FirstReviewRejectedLoanState.status;
    }
}
