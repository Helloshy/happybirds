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
 * 待签约
 * Created by Jawinton on 2017/2/22.
 */
public class ContractReviewingLoanState extends LoanState {
    private static GrantedAuthority neededAuthority = new SimpleGrantedAuthority(Authority.ROLE_LOAN_CONTRACT_REVIEW.name());
    public static final int status = 6;

    public ContractReviewingLoanState(LoanService loanService) {
        super(neededAuthority, loanService);
    }

    @Override
    public int getStatus() {
        return ContractReviewingLoanState.status;
    }

    /**
     * 只可更新贷款合同相关字段
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     * @throws IllegalAccessException
     */
    @Override
    public Loan update(User user, Loan unsavedLoan, Loan savedLoan) throws IllegalAccessException {
        if (unsavedLoan.getContractNumber() != null) savedLoan.setContractNumber(unsavedLoan.getContractNumber());
        if (unsavedLoan.getContractFileIds() != null) savedLoan.setContractFileIds(unsavedLoan.getContractFileIds());
        if (unsavedLoan.getContractCreatedTime() != null) savedLoan.setContractCreatedTime(unsavedLoan.getContractCreatedTime());

        Assert.notNull(savedLoan.getContractNumber(), "Contract number is empty.");
        Assert.notNull(savedLoan.getContractFileIds(), "Contract file ids is empty.");
        Assert.notNull(savedLoan.getContractCreatedTime(), "Contract created time is empty.");

        return super.updateLoan(user, unsavedLoan, savedLoan, false);
    }

    /**
     * 签约后跳转到待过户
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     */
    @Override
    public Loan accept(User user, Loan unsavedLoan, Loan savedLoan) {
        Assert.notNull(savedLoan.getContractNumber(), "Contract number is empty.");
        Assert.notNull(savedLoan.getContractFileIds(), "Contract file ids is empty.");
        Assert.notNull(savedLoan.getContractCreatedTime(), "Contract created time is empty.");

        return super.updateLoanStatus(user, unsavedLoan, savedLoan, ContractAcceptReviewingLoanState.status);
    }

    /**
     * 第一次放弃签约跳转到放弃合同签约待确认
     * 第二次放弃签约贷款结束
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     */
    @Override
    public Loan abort(User user, Loan unsavedLoan, Loan savedLoan) {
        int targetStatus = super.getLoanService().isLoanLogExists(unsavedLoan.getId(), this, super.getLoanService().getLoanState(ContractAbortReviewingLoanState.status))
                ? ContractAbortedLoanState.status : ContractAbortReviewingLoanState.status;
        return super.updateLoanStatus(user, unsavedLoan, savedLoan, targetStatus);
    }

    @Override
    public Authority[] getReadAuthorities() {
        return new Authority[]{Authority.ROLE_LOAN_CONTRACT_READ};
    }
}
