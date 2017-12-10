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
 * 待过户（过户和抵押材料待补充，抵押后放款）
 * Created by Jawinton on 2017/2/22.
 */
public class MortgageUpdatingLoanState extends LoanState {
    private static GrantedAuthority neededAuthority = new SimpleGrantedAuthority(Authority.ROLE_LOAN_MORTGAGE_UPDATE.name());
    public static final int status = 25;

    public MortgageUpdatingLoanState(LoanService loanService) {
        super(neededAuthority, loanService);
    }

    @Override
    public int getStatus() {
        return MortgageUpdatingLoanState.status;
    }

    /**
     * 只更新过户和抵押相关的字段，且不能为空
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     * @throws IllegalAccessException
     */
    @Override
    public Loan update(User user, Loan unsavedLoan, Loan savedLoan) throws IllegalAccessException {
        TransferUpdatingLoanState.checkNotNullFields(unsavedLoan, savedLoan);
        MortgageUpdatingLoanState.checkNotNullFields(unsavedLoan, savedLoan);
        return super.updateLoan(user, unsavedLoan, savedLoan, false);
    }

    /**
     * 提交后变成放款待审核
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     */
    @Override
    public Loan submit(User user, Loan unsavedLoan, Loan savedLoan) {
        return super.updateLoanStatus(user, unsavedLoan, savedLoan, MortgageReviewingLoanState.status);
    }

    @Override
    public Loan abort(User user, Loan unsavedLoan, Loan savedLoan) {
        int targetStatus= super.getLoanService().isLoanLogExists(unsavedLoan.getId(), this, super.getLoanService().getLoanState(MortgageAbortReviewingLoanState.status))
                ? MortgageAbortedLoanState.status : MortgageAbortReviewingLoanState.status;

        return super.updateLoanStatus(user, unsavedLoan, savedLoan, targetStatus);
    }

    public static void checkNotNullFields(Loan unsavedLoan, Loan savedLoan) {
        if (unsavedLoan.getMortgageCreatedTime() != null) savedLoan.setMortgageCreatedTime(unsavedLoan.getMortgageCreatedTime());
        if (unsavedLoan.getMortgageFileIds() != null) savedLoan.setMortgageFileIds(unsavedLoan.getMortgageFileIds());

        Assert.notNull(savedLoan.getMortgageCreatedTime(), "Mortgage file ids is null.");
        Assert.notNull(savedLoan.getMortgageFileIds(), "Mortgage created time is null.");
    }

    @Override
    public Authority[] getReadAuthorities() {
        return new Authority[]{Authority.ROLE_LOAN_MORTGAGE_READ, Authority.ROLE_FIRST_REVIEWER};
    }
}
