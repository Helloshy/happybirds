package cheanxin.service.impl;

import cheanxin.data.LoanRepository;
import cheanxin.domain.Loan;
import cheanxin.domain.LoanLog;
import cheanxin.domain.Product;
import cheanxin.domain.User;
import cheanxin.enums.LoanOperation;
import cheanxin.enums.ProductType;
import cheanxin.exceptions.ResourceNotFoundException;
import cheanxin.service.LoanLogService;
import cheanxin.service.LoanService;
import cheanxin.service.ProductService;
import cheanxin.state.LoanState;
import cheanxin.state.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Jawinton on 17/02/08.
 */
@Service
public class LoanServiceImpl extends LoanService {
    @Autowired
    LoanRepository loanRepository;

    @Autowired
    LoanLogService loanLogService;

    @Autowired
    private ProductService productService;

    private Map<Integer, LoanState> loanStateMap = new HashMap<>();

    {
        loanStateMap.put(FirstReviewAbortedLoanState.status, new FirstReviewAbortedLoanState(this));
        loanStateMap.put(DraftUpdatingLoanState.status, new DraftUpdatingLoanState(this));
        loanStateMap.put(FirstReviewingLoanState.status, new FirstReviewingLoanState(this));
        loanStateMap.put(PriceUpdatingLoanState.status, new PriceUpdatingLoanState(this));
        loanStateMap.put(SecondReviewingLoanState.status, new SecondReviewingLoanState(this));
        loanStateMap.put(MaterialsUpdatingLoanState.status, new MaterialsUpdatingLoanState(this));
        loanStateMap.put(ContractReviewingLoanState.status, new ContractReviewingLoanState(this));
        loanStateMap.put(TransferUpdatingLoanState.status, new TransferUpdatingLoanState(this));
        loanStateMap.put(FirstReviewRejectedLoanState.status, new FirstReviewRejectedLoanState(this));
        loanStateMap.put(MaterialsReviewingLoanState.status, new MaterialsReviewingLoanState(this));
        loanStateMap.put(MaterialsReviewAbortedLoanState.status, new MaterialsReviewAbortedLoanState(this));
        loanStateMap.put(SchemeReviewingLoanState.status, new SchemeReviewingLoanState(this));
        loanStateMap.put(SchemeReviewAbortedLoanState.status, new SchemeReviewAbortedLoanState(this));
        loanStateMap.put(MaterialsAbortedLoanState.status, new MaterialsAbortedLoanState(this));
        loanStateMap.put(ContractAbortedLoanState.status, new ContractAbortedLoanState(this));
        loanStateMap.put(ContractAbortReviewingLoanState.status, new ContractAbortReviewingLoanState(this));
        loanStateMap.put(TransferAbortReviewingLoanState.status, new TransferAbortReviewingLoanState(this));
        loanStateMap.put(TransferAbortedLoanState.status, new TransferAbortedLoanState(this));
        loanStateMap.put(TransferMaterialsUpdatingLoanState.status, new TransferMaterialsUpdatingLoanState(this));
        loanStateMap.put(TransferReviewingLoanState.status, new TransferReviewingLoanState(this));
        loanStateMap.put(TransferMortgageUpdatingLoanState.status, new TransferMortgageUpdatingLoanState(this));
        loanStateMap.put(MortgageUpdatingLoanState.status, new MortgageUpdatingLoanState(this));
        loanStateMap.put(MortgageAbortReviewingLoanState.status, new MortgageAbortReviewingLoanState(this));
        loanStateMap.put(MortgageAbortedLoanState.status, new MortgageAbortedLoanState(this));
        loanStateMap.put(MortgageMaterialsUpdatingLoanState.status, new MortgageMaterialsUpdatingLoanState(this));
        loanStateMap.put(MortgageReviewingLoanState.status, new MortgageReviewingLoanState(this));
        loanStateMap.put(TransferRepaymentLoanState.status, new TransferRepaymentLoanState(this));
        loanStateMap.put(TransferMaterialsReviewingLoanState.status, new TransferMaterialsReviewingLoanState(this));
        loanStateMap.put(MortgageMaterialsReviewingLoanState.status, new MortgageMaterialsReviewingLoanState(this));
        loanStateMap.put(TransferMaterialsAbortedLoanState.status, new TransferMaterialsAbortedLoanState(this));
        loanStateMap.put(MortgageMaterialsAbortedLoanState.status, new MortgageMaterialsAbortedLoanState(this));
        loanStateMap.put(MortgageRepaymentLoanState.status, new MortgageRepaymentLoanState(this));
        loanStateMap.put(ContractAcceptReviewingLoanState.status, new ContractAcceptReviewingLoanState(this));
        loanStateMap.put(TransferMortgageReviewingLoanState.status, new TransferMortgageReviewingLoanState(this));
    }

    /**
     * 该方法已经添加了@Transactional注解，且该方法会调用save和updateStatus等方法，因此save和updateStatus方法不需要添加@Transactional
     * @param user 用来做权限判断和记录操作日志
     * @param loanOperation 一共五种操作类型 ref:LoanOperation
     * @param unsavedLoan
     * @param savedLoan
     * @return
     * @throws Throwable
     */
    @Override
    @Transactional
    public Loan handle(User user, LoanOperation loanOperation, Loan unsavedLoan, Loan savedLoan) throws Throwable {
        LoanState loanState = loanStateMap.get(savedLoan.getStatus());
        if (loanState == null) {
            throw new ResourceNotFoundException("Loan", "id", String.valueOf(unsavedLoan.getId()));
        }
        unsavedLoan.setStatus(savedLoan.getStatus());

        String methodName = loanOperation.getMethodName();
        Method method = loanState.getClass().getMethod(methodName, User.class, Loan.class, Loan.class);
        try {
            return (Loan) method.invoke(loanState, user, unsavedLoan, savedLoan);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    @Override
    public boolean isLoanLogExists(long id, LoanState fromLoanState, LoanState toLoanState) {
        return loanLogService.isExists(id, fromLoanState.getStatus(), toLoanState.getStatus());
    }

    /**
     * @param unsavedLoan
     * @param isLog 是否记录贷款操作日志
     * @return
     */
    @Override
    @Transactional
    public Loan save(User user, Loan unsavedLoan, boolean isLog) {
        Loan savedLoan = loanRepository.save(unsavedLoan);
        if (savedLoan == null) {
            throw new InternalError("Save loan error.");
        }

        if (isLog && unsavedLoan.getStatus() != null) {
            LoanLog loanLog = new LoanLog(
                    savedLoan.getId(),
                    user.getUsername(),
                    unsavedLoan.getStatus(),
                    unsavedLoan.getStatus(),
                    savedLoan.getRemark(),
                    savedLoan.getModifiedTime());
            loanLogService.save(loanLog);
        }

        return savedLoan;
    }

    @Override
    public Loan findOne(long id) {
        return findOne(id, true);
    }

    @Override
    public Loan findOne(long id, boolean hasProduct) {
        Loan loan = loanRepository.findOne(id);
        if (loan == null || loan.getProductId() == null) {
            return loan;
        }

        // product name and product type
        if (hasProduct) {
            Product product = productService.findOne(loan.getProductId());
            if (product != null) {
                loan.setProductName(product.getName());
                ProductType productType = ProductType.valueOf(product.getProductType());
                String productTypeStr = productType == null ? null : productType.getDesc();
                loan.setProductType(productTypeStr);
                loan.setProductLoanPolicy(product.getLoanPolicy());
                loan.setProductMinAvailableRate(product.getMinAvailableRate());
                loan.setProductMaxAvailableRate(product.getMaxAvailableRate());
                loan.setProductAvailableTerms(product.getAvailableTerms().split(","));
                loan.setProductLoanMonthlyInterestRate(product.getLoanMonthlyInterestRate());
            }
        }

        return loan;
    }

    @Override
    public Page<Loan> list(Set<Long> sourceCityIds, String creatorUsername, int sourceChannel, String applicantName, String applicantMobileNumber, long createdTimeFrom, long createdTimeTo, Set<Integer> status, int released, int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        Specification<Loan> specification = this.getWhereClause(sourceCityIds, creatorUsername, sourceChannel, applicantName, applicantMobileNumber, createdTimeFrom, createdTimeTo, status, released);
        Page<Loan> loanPage = loanRepository.findAll(specification, pageable);
        if (loanPage == null) {
            return null;
        }

        // product name
        Map<Long, Product> productMap = new HashMap<>();
        for (Loan loan : loanPage) {
            if (loan == null || loan.getProductId() == null) {
                continue;
            }
            productMap.put(loan.getProductId(), null);
        }
        List<Product> productList = productService.list(productMap.keySet());
        if (productList != null) {
            for (Product product : productList) {
                if (product == null || product.getId() == null) {
                    continue;
                }
                productMap.put(product.getId(), product);
            }
            for (Loan loan : loanPage) {
                if (loan == null || loan.getProductId() == null) {
                    continue;
                }
                Product product = productMap.get(loan.getProductId());
                if (product == null) {
                    continue;
                }
                loan.setProductName(product.getName());
                ProductType productType = ProductType.valueOf(product.getProductType());
                String productTypeStr = productType == null ? null : productType.getDesc();
                loan.setProductType(productTypeStr);
            }
        }
        
        return loanPage;
    }

    /**
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @param isLog
     * @return
     */
    @Override
    @Transactional
    public Loan updateStatus(User user, Loan unsavedLoan, Loan savedLoan, boolean isLog) {
        if (savedLoan == null || savedLoan.getStatus() == null) {
            return null;
        }

        savedLoan.setModifiedTime(unsavedLoan.getModifiedTime());
        savedLoan = loanRepository.save(savedLoan);
        if (savedLoan == null) {
            throw new InternalError("Save loan error.");
        }

        // save loan operation log.
        savedLoan.setReviewRemark(unsavedLoan.getReviewRemark());
        if (isLog && savedLoan.getStatus() != null) {
            LoanLog loanLog = new LoanLog(
                    savedLoan.getId(),
                    user.getUsername(),
                    unsavedLoan.getStatus(),
                    savedLoan.getStatus(),
                    savedLoan.getReviewRemark(),
                    savedLoan.getModifiedTime());
            loanLogService.save(loanLog);
        }

        return savedLoan;
    }

    @Override
    public LoanState getLoanState(int status) {
        return loanStateMap.get(status);
    }
}
