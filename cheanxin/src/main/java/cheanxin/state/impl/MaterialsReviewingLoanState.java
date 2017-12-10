package cheanxin.state.impl;

import cheanxin.domain.Loan;
import cheanxin.domain.User;
import cheanxin.service.LoanService;
import cheanxin.state.LoanState;
import org.springframework.util.Assert;

/**
 * 第二次待复审（材料补充后待复审）
 * Created by Jawinton on 2017/2/22.
 */
public class MaterialsReviewingLoanState extends SecondReviewingLoanState {
    public static final int status = 10;

    public MaterialsReviewingLoanState(LoanService loanService) {
        super(loanService);
    }

    @Override
    public int getStatus() {
        return MaterialsReviewingLoanState.status;
    }

    /**
     * 如果用户补充材料后复审被拒绝，贷款结束。
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     */
    @Override
    public Loan reject(User user, Loan unsavedLoan, Loan savedLoan) {
        Assert.notNull(unsavedLoan.getReviewRemark(), "Review remark can not be null.");

        return super.updateLoanStatus(user, unsavedLoan, savedLoan, MaterialsReviewAbortedLoanState.status);
    }
}
