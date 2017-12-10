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
 * 待过户（待补充过户材料，过户后放款）
 * Created by Jawinton on 2017/2/22.
 */
public class TransferUpdatingLoanState extends LoanState {
    private static GrantedAuthority neededAuthority = new SimpleGrantedAuthority(Authority.ROLE_LOAN_TRANSFER_UPDATE.name());
    public static final int status = 8;

    public TransferUpdatingLoanState(LoanService loanService) {
        super(neededAuthority, loanService);
    }

    @Override
    public int getStatus() {
        return TransferUpdatingLoanState.status;
    }

    /**
     * 只更新过户相关的字段，且不能为空
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     * @throws IllegalAccessException
     */
    @Override
    public Loan update(User user, Loan unsavedLoan, Loan savedLoan) throws IllegalAccessException {
        TransferUpdatingLoanState.checkNotNullFields(unsavedLoan, savedLoan);
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
        return super.updateLoanStatus(user, unsavedLoan, savedLoan, TransferReviewingLoanState.status);
    }

    @Override
    public Loan abort(User user, Loan unsavedLoan, Loan savedLoan) {
        super.checkAuthority(user, savedLoan);

        int targetStatus = super.getLoanService().isLoanLogExists(unsavedLoan.getId(), this, super.getLoanService().getLoanState(TransferAbortReviewingLoanState.status))
                ? TransferAbortedLoanState.status : TransferAbortReviewingLoanState.status;
        return super.updateLoanStatus(user, unsavedLoan, savedLoan, targetStatus);
    }

    public static void checkNotNullFields(Loan unsavedLoan, Loan savedLoan) {
        if (unsavedLoan.getTransferCardNumber() != null) savedLoan.setTransferCardNumber(unsavedLoan.getTransferCardNumber());
        if (unsavedLoan.getTransferCreatedTime() != null) savedLoan.setTransferCreatedTime(unsavedLoan.getTransferCreatedTime());
        if (unsavedLoan.getTransferFileIds() != null) savedLoan.setTransferFileIds(unsavedLoan.getTransferFileIds());
        if (unsavedLoan.getTransferGPSFileIds() != null) savedLoan.setTransferGPSFileIds(unsavedLoan.getTransferGPSFileIds());
        if (unsavedLoan.getTransferInsuranceFileIds() != null) savedLoan.setTransferInsuranceFileIds(unsavedLoan.getTransferInsuranceFileIds());

        Assert.notNull(savedLoan.getTransferCardNumber(), "Transfer car number is null.");
        Assert.notNull(savedLoan.getTransferCreatedTime(), "Transfer created time is null.");
        Assert.notNull(savedLoan.getTransferFileIds(), "Transfer file ids is null.");
        Assert.notNull(savedLoan.getTransferGPSFileIds(), "Transfer gps file ids is null.");
        Assert.notNull(savedLoan.getTransferInsuranceFileIds(), "Transfer insurance file ids is null.");
    }

    @Override
    public Authority[] getReadAuthorities() {
        return new Authority[]{Authority.ROLE_LOAN_TRANSFER_READ, Authority.ROLE_FIRST_REVIEWER};
    }
}
