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
 * 待复审
 * Created by Jawinton on 2017/2/22.
 */
public class SecondReviewingLoanState extends LoanState {
    private static GrantedAuthority neededAuthority = new SimpleGrantedAuthority(Authority.ROLE_LOAN_SECOND_REVIEW.name());
    public static final int status = 4;

    public SecondReviewingLoanState(LoanService loanService) {
        super(neededAuthority, loanService);
    }

    @Override
    public int getStatus() {
        return SecondReviewingLoanState.status;
    }

    /**
     * 只更新审批贷款成数和审批贷款金额两个字段
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     * @throws IllegalAccessException
     */
    @Override
    public Loan update(User user, Loan unsavedLoan, Loan savedLoan) throws IllegalAccessException {
        if (unsavedLoan.getReviewLoanRate() != null) savedLoan.setReviewLoanRate(unsavedLoan.getReviewLoanRate());
        if (unsavedLoan.getReviewLoanTerms() != null) savedLoan.setReviewLoanTerms(unsavedLoan.getReviewLoanTerms());
        if (unsavedLoan.getReviewLoanPrice() != null) savedLoan.setReviewLoanPrice(unsavedLoan.getReviewLoanPrice());

        Assert.notNull(savedLoan.getReviewLoanRate(), "Review loan rate is null.");
        Assert.notNull(savedLoan.getReviewLoanTerms(), "Review loan terms is null.");
        Assert.notNull(savedLoan.getReviewLoanPrice(), "Review loan price is null.");

        int maxLoanPrice = (int) Math.floor(savedLoan.getVehiclePredictPrice() * savedLoan.getReviewLoanRate()  *  0.1f);
        savedLoan.setReviewLoanPrice(Math.min(maxLoanPrice, savedLoan.getApplicantLoanPrice()));

        return super.updateLoan(user, unsavedLoan, savedLoan, false);
    }

    /**
     * 复审通过跳转到待签约
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     */
    @Override
    public Loan accept(User user, Loan unsavedLoan, Loan savedLoan) {
        Assert.notNull(savedLoan.getReviewLoanRate(), "Review loan rate is null.");
        Assert.notNull(savedLoan.getReviewLoanTerms(), "Review loan terms is null.");
        Assert.notNull(savedLoan.getReviewLoanPrice(), "Review loan price is null.");
        Assert.isTrue(savedLoan.getReviewLoanRate() <= savedLoan.getProductMaxAvailableRate(), "Review loan rate cannot be greater than product max available rate.");
        Assert.isTrue(savedLoan.getReviewLoanRate() >= savedLoan.getProductMinAvailableRate(), "Review loan rate cannot be less than product min available rate.");

        int maxLoanPrice = (int) Math.floor(savedLoan.getVehiclePredictPrice() * savedLoan.getReviewLoanRate()  *  0.1f);
        int minLoanPrice = (int) Math.floor(savedLoan.getVehiclePredictPrice() * savedLoan.getProductMinAvailableRate()  *  0.1f);
        Assert.isTrue(savedLoan.getReviewLoanPrice() <= maxLoanPrice, "Review loan price is too large.");
        Assert.isTrue(savedLoan.getReviewLoanPrice() >= minLoanPrice, "Review loan price is too small.");

        return super.updateLoanStatus(user, unsavedLoan, savedLoan, ContractReviewingLoanState.status);
    }

    /**
     * 待复审被拒绝跳转到材料待补充
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     */
    @Override
    public Loan reject(User user, Loan unsavedLoan, Loan savedLoan) {
        Assert.notNull(unsavedLoan.getReviewRemark(), "Review remark can not be null.");

        return super.updateLoanStatus(user, unsavedLoan, savedLoan, MaterialsUpdatingLoanState.status);
    }

    @Override
    public Authority[] getReadAuthorities() {
        return new Authority[]{Authority.ROLE_LOAN_SECOND_READ, Authority.ROLE_APPRAISER};
    }
}