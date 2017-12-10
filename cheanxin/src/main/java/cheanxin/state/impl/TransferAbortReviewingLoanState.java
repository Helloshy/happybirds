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
 * 无法过户待确认（过户后放款）
 * Created by Jawinton on 2017/2/22.
 */
public class TransferAbortReviewingLoanState extends LoanState {
    private static GrantedAuthority neededAuthority = new SimpleGrantedAuthority(Authority.ROLE_LOAN_TRANSFER_ABORT_REVIEW.name());
    public static final int status = 19;

    public TransferAbortReviewingLoanState(LoanService loanService) {
        super(neededAuthority, loanService);
    }

    @Override
    public int getStatus() {
        return TransferAbortReviewingLoanState.status;
    }

    @Override
    public Loan accept(User user, Loan unsavedLoan, Loan savedLoan) {
        Assert.notNull(unsavedLoan.getReviewRemark(), "Review remark can not be null.");

        return super.updateLoanStatus(user, unsavedLoan, savedLoan, TransferAbortedLoanState.status);
    }

    @Override
    public Loan reject(User user, Loan unsavedLoan, Loan savedLoan) {
        Assert.notNull(unsavedLoan.getReviewRemark(), "Review remark can not be null.");

        return super.updateLoanStatus(user, unsavedLoan, savedLoan, TransferUpdatingLoanState.status);
    }

    @Override
    public Authority[] getReadAuthorities() {
        return new Authority[]{Authority.ROLE_LOAN_TRANSFER_ABORT_READ};
    }
}
