package cheanxin.state.impl;

import cheanxin.domain.Loan;
import cheanxin.domain.User;
import cheanxin.enums.Authority;
import cheanxin.enums.ReleaseStatus;
import cheanxin.service.LoanService;
import cheanxin.state.LoanState;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.Assert;

/**
 * 过户抵押材料待审核（抵押后放款）
 * Created by Jawinton on 2017/2/22.
 */
public class MortgageReviewingLoanState extends LoanState {
    private static GrantedAuthority neededAuthority = new SimpleGrantedAuthority(Authority.ROLE_LOAN_MORTGAGE_RELEASE_REVIEW.name());
    public static final int status = 29;

    public MortgageReviewingLoanState(LoanService loanService) {
        super(neededAuthority, loanService);
    }

    @Override
    public int getStatus() {
        return MortgageReviewingLoanState.status;
    }

    /**
     * 审核通过跳转到待放款
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     */
    @Override
    public Loan accept(User user, Loan unsavedLoan, Loan savedLoan) throws IllegalAccessException {
        // 更新放款状态
        savedLoan.setReleaseStatus(ReleaseStatus.MORTGAGE_RELEASE_PENDING.value());
        super.updateLoan(user, unsavedLoan, savedLoan, false);

        return super.updateLoanStatus(user, unsavedLoan, savedLoan, MortgageRepaymentLoanState.status);
    }

    /**
     * 审核被拒绝跳转到材料待补充
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     */
    @Override
    public Loan reject(User user, Loan unsavedLoan, Loan savedLoan) {
        Assert.notNull(unsavedLoan.getReviewRemark(), "Review remark can not be null.");

        return super.updateLoanStatus(user, unsavedLoan, savedLoan, MortgageMaterialsUpdatingLoanState.status);
    }

    @Override
    public Authority[] getReadAuthorities() {
        return new Authority[]{Authority.ROLE_LOAN_MORTGAGE_RELEASE_REVIEW_READ, Authority.ROLE_FIRST_REVIEWER};
    }
}
