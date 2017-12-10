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
 * 材料待补充（复审拒绝）
 * Created by Jawinton on 2017/2/22.
 */
public class MaterialsUpdatingLoanState extends LoanState {
    private static GrantedAuthority neededAuthority = new SimpleGrantedAuthority(Authority.ROLE_LOAN_MATERIALS_UPDATE.name());
    public static final int status = 5;

    public MaterialsUpdatingLoanState(LoanService loanService) {
        super(neededAuthority, loanService);
    }

    @Override
    public int getStatus() {
        return status;
    }

    /**
     * 只可修改贷款中用以添加补充材料的字段
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     * @throws IllegalAccessException
     */
    @Override
    public Loan update(User user, Loan unsavedLoan, Loan savedLoan) throws IllegalAccessException {
        if (unsavedLoan.getMaterialsRemark() != null) savedLoan.setMaterialsRemark(unsavedLoan.getMaterialsRemark());
        if (unsavedLoan.getMaterialsFileIds() != null) savedLoan.setMaterialsFileIds(unsavedLoan.getMaterialsFileIds());

        Assert.notNull(savedLoan.getMaterialsRemark(), "Materials remark is empty.");
        Assert.notNull(savedLoan.getMaterialsFileIds(), "Materials file ids is empty.");

        return super.updateLoan(user, unsavedLoan, savedLoan, false);
    }

    /**
     * 提交后转到材料补充后待复审
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     */
    @Override
    public Loan submit(User user, Loan unsavedLoan, Loan savedLoan) {
        Assert.notNull(savedLoan.getMaterialsRemark(), "Materials remark is empty.");
        Assert.notNull(savedLoan.getMaterialsFileIds(), "Materials file ids is empty.");

        return super.updateLoanStatus(user, unsavedLoan, savedLoan, MaterialsReviewingLoanState.status);
    }

    @Override
    public Loan abort(User user, Loan unsavedLoan, Loan savedLoan) {
        return super.updateLoanStatus(user, unsavedLoan, savedLoan, MaterialsAbortedLoanState.status);
    }

    @Override
    public Authority[] getReadAuthorities() {
        return new Authority[]{Authority.ROLE_LOAN_SECOND_READ};
    }
}
