package cheanxin.state.impl;

import cheanxin.domain.Loan;
import cheanxin.domain.User;
import cheanxin.enums.Authority;
import cheanxin.enums.LoanPolicy;
import cheanxin.service.LoanService;
import cheanxin.state.LoanState;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * 同意签约并上传签约材料之后待审核状态
 * Created by Jawinton on 2017/04/13.
 */
public class ContractAcceptReviewingLoanState extends LoanState {
    private static GrantedAuthority neededAuthority = new SimpleGrantedAuthority(Authority.ROLE_LOAN_CONTRACT_ACCEPT_REVIEW.name());
    public static final int status = 37;

    public ContractAcceptReviewingLoanState(LoanService loanService) {
        super(neededAuthority, loanService);
    }

    @Override
    public int getStatus() {
        return ContractAcceptReviewingLoanState.status;
    }

    @Override
    public Loan accept(User user, Loan unsavedLoan, Loan savedLoan) {
        LoanPolicy loanPolicy = LoanPolicy.get(savedLoan.getProductLoanPolicy());
        if (loanPolicy == null) {
            throw new InternalError("Unknown loan policy");
        }
        int targetStatus;
        if (LoanPolicy.AFTER_TRANSFER == loanPolicy) {
            targetStatus = TransferUpdatingLoanState.status;
        } else if (LoanPolicy.AFTER_MORTGAGE == loanPolicy) {
            targetStatus = MortgageUpdatingLoanState.status;
        } else {
            throw new InternalError("Unknown loan policy");
        }

        return super.updateLoanStatus(user, unsavedLoan, savedLoan, targetStatus);
    }

    @Override
    public Loan reject(User user, Loan unsavedLoan, Loan savedLoan) {
        return super.updateLoanStatus(user, unsavedLoan, savedLoan, ContractReviewingLoanState.status);
    }

    @Override
    public Authority[] getReadAuthorities() {
        return new Authority[]{Authority.ROLE_LOAN_CONTRACT_ACCEPT_READ};
    }
}
