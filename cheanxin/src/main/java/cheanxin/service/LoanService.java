package cheanxin.service;

import cheanxin.domain.Loan;
import cheanxin.domain.User;
import cheanxin.enums.LoanOperation;
import cheanxin.state.LoanState;

import java.lang.reflect.InvocationTargetException;

/**
 * 贷款service类
 * Created by Jawinton on 17/02/08.
 */
public abstract class LoanService extends LoanListService<Loan> {
    /**
     * 保存贷款
     * @param unsavedLoan
     * @param isLog 是否记录贷款操作日志
     * @return
     */
    public abstract Loan save(User user, Loan unsavedLoan, boolean isLog);

    /**
     * 获取贷款详情
     * @param id
     * @return
     */
    public abstract Loan findOne(long id);

    /**
     * 获取贷款详情，如果hasProduct为True，添加product信息
     * @param id
     * @param hasProduct
     * @return
     */
    public abstract Loan findOne(long id, boolean hasProduct);

    /**
     * 更新贷款状态，一般贷款状态转变会调用该接口
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @param isLog
     * @return
     */
    public abstract Loan updateStatus(User user, Loan unsavedLoan, Loan savedLoan, boolean isLog);

    /**
     * 判断贷款操作日志是否存在
     * @param id
     * @param fromLoanState
     * @param toLoanState
     * @return
     */
    public abstract boolean isLoanLogExists(long id, LoanState fromLoanState, LoanState toLoanState);

    /**
     * 操作贷款，同样的操作，当贷款处在不同的状态时，会有不同的处理逻辑和结果
     * @param user 用来做权限判断和记录操作日志
     * @param loanOperation 一共五种操作类型 ref:LoanOperation
     * @param unsavedLoan
     * @param savedLoan
     * @return
     * @throws Throwable
     */
    public abstract Loan handle(User user, LoanOperation loanOperation, Loan unsavedLoan, Loan savedLoan) throws Throwable;

    /**
     * 获取贷款状态
     * @param status int 贷款状态的数字表示
     * @return LoanState
     */
    public abstract LoanState getLoanState(int status);
}
