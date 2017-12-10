package cheanxin.state.impl;

import cheanxin.domain.Loan;
import cheanxin.domain.User;
import cheanxin.enums.Authority;
import cheanxin.service.LoanService;
import cheanxin.state.LoanState;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.Assert;

/**
 * 抵押材料待审核（过户后放款）
 * Created by Jawinton on 2017/2/22.
 */
public class TransferMortgageReviewingLoanState extends LoanState {
    private static GrantedAuthority neededAuthority = new SimpleGrantedAuthority(Authority.ROLE_LOAN_RELEASE_MORTGAGE_REVIEW.name());
    public static final int status = 38;

    public TransferMortgageReviewingLoanState(LoanService loanService) {
        super(neededAuthority, loanService);
    }

    @Override
    public int getStatus() {
        return TransferMortgageReviewingLoanState.status;
    }

    /**
     * 审核通过跳转到待还款
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     */
    @Override
    public Loan accept(User user, Loan unsavedLoan, Loan savedLoan) {
        return super.updateLoanStatus(user, unsavedLoan, savedLoan, TransferRepaymentLoanState.status);
    }

    /**
     * 审核被拒绝跳转到抵押材料待补充
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     */
    @Override
    public Loan reject(User user, Loan unsavedLoan, Loan savedLoan) {
        Assert.notNull(unsavedLoan.getReviewRemark(), "Review remark can not be null.");

        return super.updateLoanStatus(user, unsavedLoan, savedLoan, TransferMortgageUpdatingLoanState.status);
    }

    @Override
    public Authority[] getReadAuthorities() {
        return new Authority[]{Authority.ROLE_LOAN_RELEASE_MORTGAGE_READ, Authority.ROLE_FIRST_REVIEWER};
    }
}
