package cheanxin.state;

import cheanxin.domain.Loan;
import cheanxin.domain.PostAuthority;
import cheanxin.domain.User;
import cheanxin.enums.Authority;
import cheanxin.exceptions.ForbiddenException;
import cheanxin.exceptions.UnauthorizedException;
import cheanxin.service.LoanService;
import cheanxin.util.ReflectUtil;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 贷款状态基类
 * Created by Jawinton on 2017/2/22.
 */
public abstract class LoanState {
    /**
     * 操作当前状态的贷款需要什么权限
     */
    private GrantedAuthority neededAuthority;
    /**
     * 贷款service实例
     */
    private LoanService loanService;

    /**
     * 构造方法
     * @param neededAuthority
     * @param loanService
     */
    public LoanState(GrantedAuthority neededAuthority, LoanService loanService) {
        this.neededAuthority = neededAuthority;
        this.loanService = loanService;
    }

    public LoanState(LoanService loanService) {
        this.loanService = loanService;
    }

    /**
     * 所有贷款状态必须有个可替代的数字
     * @return
     */
    public abstract int getStatus();

    /**
     * 更新贷款操作
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     * @throws IllegalAccessException
     */
    public Loan update(User user, Loan unsavedLoan, Loan savedLoan) throws IllegalAccessException {
        throw new UnauthorizedException("Undefined loan operation.");
    }

    /**
     * 提交贷款（审批/签约）
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     */
    public Loan submit(User user, Loan unsavedLoan, Loan savedLoan) {
        throw new UnauthorizedException("Undefined loan operation.");
    }

    /**
     * 审核通过
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     */
    public Loan accept(User user, Loan unsavedLoan, Loan savedLoan) throws IllegalAccessException {
        throw new UnauthorizedException("Undefined loan operation.");
    }

    /**
     * 审核拒绝
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     */
    public Loan reject(User user, Loan unsavedLoan, Loan savedLoan) {
        throw new UnauthorizedException("Undefined loan operation.");
    }

    /**
     * 直接放弃贷款
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     */
    public Loan abort(User user, Loan unsavedLoan, Loan savedLoan) {
        throw new UnauthorizedException("Undefined loan operation.");
    }

    protected LoanService getLoanService() {
        return loanService;
    }

    /**
     * 全局权限校验
     * @param user
     */
    protected void checkAuthority(User user, Loan loan) {
        boolean hasAuthority = false;
        Collection<? extends PostAuthority> userPostAuthorities = user.getPostAuthorities();
        String operatorUsername = loan == null ? null : loan.getCreatorUsername();
        long cityId = loan == null ? -1 : loan.getSourceCityId();
        for (PostAuthority postAuthority : userPostAuthorities) {
            if (postAuthority == null || postAuthority.getAuthority() == null) {
                continue;
            }
            if (!postAuthority.getAuthority().equals(neededAuthority.getAuthority())) {
                continue;
            }

            if (postAuthority.getSelfCheck() && !user.getUsername().equals(operatorUsername)) {
                continue;
            }
            if (postAuthority.getCityCheck() && user.getCityIds() != null) {
                if (cityId < 0 || !user.getCityIds().contains(cityId)) {
                    continue;
                }
            }
            hasAuthority = true;
            break;
        }
        if (!hasAuthority) {
            throw new ForbiddenException("Authorities error.");
        }
    }

    /**
     * 更新贷款操作
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @param isMergeNotNullAttribute
     * @return
     * @throws IllegalAccessException
     */
    protected Loan updateLoan(User user, Loan unsavedLoan, Loan savedLoan, boolean isMergeNotNullAttribute) throws IllegalAccessException {
        checkAuthority(user, savedLoan);
        if (isMergeNotNullAttribute) {
            ReflectUtil.mergeObject(unsavedLoan, savedLoan);
        }
        savedLoan.setStatus(this.getStatus());
        savedLoan.setModifiedTime(unsavedLoan.getModifiedTime());
        return loanService.save(user, savedLoan, false);
    }

    /**
     * 通用更新贷款状态操作
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @param status
     * @return
     */
    protected Loan updateLoanStatus(User user, Loan unsavedLoan, Loan savedLoan, int status) {
        checkAuthority(user, savedLoan);
        LoanState targetState = getLoanService().getLoanState(status);
        savedLoan.setReviewRemark(unsavedLoan.getReviewRemark());
        unsavedLoan.setStatus(savedLoan.getStatus());
        savedLoan.setStatus(targetState.getStatus());
        return loanService.updateStatus(user, unsavedLoan, savedLoan, true);
    }

    public abstract Authority[] getReadAuthorities();
}
