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
 * 放弃抵押待确认（抵押后放款）
 * Created by Jawinton on 2017/2/22.
 */
public class MortgageAbortReviewingLoanState extends LoanState {
    private static GrantedAuthority neededAuthority = new SimpleGrantedAuthority(Authority.ROLE_LOAN_MORTGAGE_ABORT_REVIEW.name());
    public static final int status = 26;

    public MortgageAbortReviewingLoanState(LoanService loanService) {
        super(neededAuthority, loanService);
    }

    @Override
    public int getStatus() {
        return MortgageAbortReviewingLoanState.status;
    }

    @Override
    public Loan accept(User user, Loan unsavedLoan, Loan savedLoan) {
        Assert.notNull(unsavedLoan.getReviewRemark(), "Review remark can not be null.");

        return super.updateLoanStatus(user, unsavedLoan, savedLoan, MortgageAbortedLoanState.status);
    }

    @Override
    public Loan reject(User user, Loan unsavedLoan, Loan savedLoan) {
        Assert.notNull(unsavedLoan.getReviewRemark(), "Review remark can not be null.");

        return super.updateLoanStatus(user, unsavedLoan, savedLoan, MortgageUpdatingLoanState.status);
    }

    @Override
    public Authority[] getReadAuthorities() {
        return new Authority[]{Authority.ROLE_LOAN_MORTGAGE_READ};
    }
}
