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
 * 草稿箱
 * Created by Jawinton on 2017/2/22.
 */
public class DraftUpdatingLoanState extends LoanState {
    private static GrantedAuthority neededAuthority = new SimpleGrantedAuthority(Authority.ROLE_LOAN_DRAFT_UPDATE.name());
    public static final int status = 1;

    public DraftUpdatingLoanState(LoanService loanService) {
        super(neededAuthority, loanService);
    }

    @Override
    public int getStatus() {
        return DraftUpdatingLoanState.status;
    }

    @Override
    public Loan update(User user, Loan unsavedLoan, Loan savedLoan) throws IllegalAccessException {
        unsavedLoan.setVehiclePredictPrice(null);
        return super.updateLoan(user, unsavedLoan, savedLoan, true);
    }

    /**
     * 提交的时候要判断非空字段
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     */
    @Override
    public Loan submit(User user, Loan unsavedLoan, Loan savedLoan) {
        Assert.notNull(savedLoan.getProductId(), "Product cannot be null.");
        Assert.notNull(savedLoan.getLoanTerms(), "Loan terms cannot be null.");
        Assert.notNull(savedLoan.getProductLoanMonthlyInterestRate(), "Loan monthly interest rate.");
        Assert.notNull(savedLoan.getVehicleDealPrice(), "Vehicle deal price cannot be null.");
        Assert.notNull(savedLoan.getApplicantLoanRate(), "Applicant loan rate cannot be null.");
        Assert.notNull(savedLoan.getProductMinAvailableRate(), "Product min available rate cannot be null.");
        Assert.notNull(savedLoan.getProductMaxAvailableRate(), "Product max available rate cannot be null.");
        Assert.isTrue(savedLoan.getApplicantLoanRate() <= savedLoan.getProductMaxAvailableRate(), "Applicant loan rate cannot be greater than product max available rate.");
        Assert.isTrue(savedLoan.getApplicantLoanRate() >= savedLoan.getProductMinAvailableRate(), "Applicant loan rate cannot be less than product min available rate.");

        return super.updateLoanStatus(user, unsavedLoan, savedLoan, FirstReviewingLoanState.status);
    }

    @Override
    public Authority[] getReadAuthorities() {
        return new Authority[]{Authority.ROLE_LOAN_DRAFT_READ};
    }
}