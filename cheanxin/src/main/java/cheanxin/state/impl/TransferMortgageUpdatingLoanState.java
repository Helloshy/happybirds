package cheanxin.state.impl;

import cheanxin.domain.Loan;
import cheanxin.domain.User;
import cheanxin.enums.Authority;
import cheanxin.service.LoanService;
import cheanxin.state.LoanState;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * 抵押材料待补充（过户后放款）
 * Created by Jawinton on 2017/2/22.
 */
public class TransferMortgageUpdatingLoanState extends LoanState {
    private static GrantedAuthority neededAuthority = new SimpleGrantedAuthority(Authority.ROLE_LOAN_RELEASE_MORTGAGE_UPDATE.name());
    public static final int status = 24;

    public TransferMortgageUpdatingLoanState(LoanService loanService) {
        super(neededAuthority, loanService);
    }

    @Override
    public int getStatus() {
        return TransferMortgageUpdatingLoanState.status;
    }

    /**
     * 只更新抵押相关的字段，且不能为空
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     * @throws IllegalAccessException
     */
    @Override
    public Loan update(User user, Loan unsavedLoan, Loan savedLoan) throws IllegalAccessException {
        MortgageUpdatingLoanState.checkNotNullFields(unsavedLoan, savedLoan);
        return super.updateLoan(user, unsavedLoan, savedLoan, false);
    }

    /**
     * 提交后变成待还款
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     */
    @Override
    public Loan submit(User user, Loan unsavedLoan, Loan savedLoan) {
        return super.updateLoanStatus(user, unsavedLoan, savedLoan, TransferMortgageReviewingLoanState.status);
    }

    @Override
    public Authority[] getReadAuthorities() {
        Authority[] result = new Authority[]{Authority.ROLE_LOAN_TRANSFER_READ, Authority.ROLE_FINANCE};
        return result;
    }
}
