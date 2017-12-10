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
 * 主动放弃签约后待审核状态（防止用户误操作放弃签约）
 * Created by Jawinton on 2017/3/16.
 */
public class ContractAbortReviewingLoanState extends LoanState {
    private static GrantedAuthority neededAuthority = new SimpleGrantedAuthority(Authority.ROLE_LOAN_CONTRACT_ABORT_REVIEW.name());
    public static final int status = 17;

    public ContractAbortReviewingLoanState(LoanService loanService) {
        super(neededAuthority, loanService);
    }

    @Override
    public int getStatus() {
        return ContractAbortReviewingLoanState.status;
    }

    /**
     * 只更新用以调整方案的字段，且不能为空
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     * @throws IllegalAccessException
     */
    @Override
    public Loan update(User user, Loan unsavedLoan, Loan savedLoan) throws IllegalAccessException {
        if (unsavedLoan.getApplicantLoanRate() != null) savedLoan.setApplicantLoanRate(unsavedLoan.getApplicantLoanRate());
        if (unsavedLoan.getLoanTerms() != null) savedLoan.setLoanTerms(unsavedLoan.getLoanTerms());
        if (unsavedLoan.getProductLoanMonthlyInterestRate() != null) savedLoan.setProductLoanMonthlyInterestRate(unsavedLoan.getProductLoanMonthlyInterestRate());
        if (unsavedLoan.getApplicantLoanPrice() != null) savedLoan.setApplicantLoanPrice(unsavedLoan.getApplicantLoanPrice());

        Assert.notNull(savedLoan.getApplicantLoanRate(), "Loan rate is null.");
        Assert.notNull(savedLoan.getLoanTerms(), "Loan terms is null.");
        Assert.notNull(savedLoan.getProductLoanMonthlyInterestRate(), "Loan monthly interest rate is null.");
        Assert.notNull(savedLoan.getApplicantLoanPrice(), "Loan first payment is null.");
        return super.updateLoan(user, unsavedLoan, savedLoan, false);
    }

    /**
     * 提交后变成调整方案后待审核
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     */
    @Override
    public Loan submit(User user, Loan unsavedLoan, Loan savedLoan) {
        return super.updateLoanStatus(user, unsavedLoan, savedLoan, SchemeReviewingLoanState.status);
    }

    @Override
    public Loan accept(User user, Loan unsavedLoan, Loan savedLoan) {
        return super.updateLoanStatus(user, unsavedLoan, savedLoan, ContractAbortedLoanState.status);
    }

    @Override
    public Loan reject(User user, Loan unsavedLoan, Loan savedLoan) {
        return super.updateLoanStatus(user, unsavedLoan, savedLoan, ContractReviewingLoanState.status);
    }

    @Override
    public Authority[] getReadAuthorities() {
        return new Authority[]{Authority.ROLE_LOAN_CONTRACT_ABORT_READ};
    }
}
