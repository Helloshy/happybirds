package cheanxin.web;

import cheanxin.constant.LogicConstants;
import cheanxin.domain.Loan;
import cheanxin.domain.PostAuthority;
import cheanxin.domain.User;
import cheanxin.enums.LoanOperation;
import cheanxin.enums.ReleaseStatus;
import cheanxin.exceptions.ResourceNotFoundException;
import cheanxin.service.LoanLogService;
import cheanxin.service.LoanService;
import cheanxin.state.LoanState;
import cheanxin.state.impl.DraftUpdatingLoanState;
import cheanxin.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.TreeSet;

import static cheanxin.constant.LogicConstants.FINANCIAL_POST_ID;

/**
 * Created by Jawinton on 17/02/09.
 */
@RestController
@RequestMapping("/loans")
public class LoanController extends BaseController {
    @Autowired
    LoanService loanService;

    @Autowired
    LoanLogService loanLogService;

    /**
     * 新建贷款操作
     * @param unsavedLoan
     * @param errors
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Loan> save(
            @Valid @RequestBody Loan unsavedLoan,
            Errors errors,
            @AuthenticationPrincipal User user) {
        assertFieldError(errors);

        long now = System.currentTimeMillis() / 1000;
        unsavedLoan.setCreatorUsername(user.getUsername());
        unsavedLoan.setCreatedTime(now);
        unsavedLoan.setModifiedTime(now);
        unsavedLoan.setStatus(loanService.getLoanState(DraftUpdatingLoanState.status).getStatus());
        unsavedLoan.setVehiclePredictPrice(null);
        unsavedLoan.setReleaseStatus(ReleaseStatus.RELEASE_UNAVAILABLE.value());
        return new ResponseEntity<>(loanService.save(user, unsavedLoan, true), HttpStatus.CREATED);
    }

    /**
     * 贷款列表
     * @param cityId
     * @param sourceChannel
     * @param applicantName
     * @param applicantMobileNumber
     * @param createdTimeFrom
     * @param createdTimeTo
     * @param status
     * @param page
     * @param size
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Page<Loan> list(
            @RequestParam(value = "cityId", defaultValue = "0") long cityId,
            @RequestParam(value = "sourceChannel", defaultValue = "0") int sourceChannel,
            @RequestParam(value = "applicantName", defaultValue = "") String applicantName,
            @RequestParam(value = "applicantMobileNumber", defaultValue = "") String applicantMobileNumber,
            @RequestParam(value = "createdTimeFrom", defaultValue = "0") long createdTimeFrom,
            @RequestParam(value = "createdTimeTo", defaultValue = "0") long createdTimeTo,
            @RequestParam(value = "status", defaultValue = "") String status,
            @RequestParam(value = "releaseStatus", defaultValue = "-1") int releaseStatus,
            @RequestParam(value = "page", defaultValue = LogicConstants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = LogicConstants.DEFAULT_SIZE) int size,
            @AuthenticationPrincipal User user) {
        // 金融专员只看自己的贷款列表（即使该金融专员有其他角色）
        boolean isFilterSelf = false;
        for (PostAuthority postAuthority : user.getPostAuthorities()) {
            if (postAuthority.getPostId() == FINANCIAL_POST_ID) {
                isFilterSelf = true;
                break;
            }
        }
        String creatorUsername = isFilterSelf ? user.getUsername() : null;

        Set<Integer> statusSet = StringUtil.splitStrToIntSet(status);
        Set<Long> cityIds = new TreeSet<>();
        if (!isFilterSelf) {
            for (Integer statusValue : statusSet) {
                LoanState loanState = loanService.getLoanState(statusValue);
                Assert.notNull(loanState, "Unkown loan state.");
                Set<Long> authorityCityIds = getReadAuthorityCities(user, cityId, loanState.getReadAuthorities());
                if (authorityCityIds == null) {
                    cityIds = null;
                    break;
                }
                cityIds.addAll(authorityCityIds);
            }
        }
        return loanService.list(cityIds, creatorUsername, sourceChannel, applicantName, applicantMobileNumber, createdTimeFrom, createdTimeTo, statusSet, releaseStatus, page, size);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Loan> get(@PathVariable(value = "id") long id) {
        Loan loan = loanService.findOne(id);
        Assert.notNull(loan, "Loan not found.");
        return new ResponseEntity<>(loan, HttpStatus.OK);
    }

    /**
     * 贷款审核、字段更新、放弃、提交审核等操作
     * @param id
     * @param loanOperate
     * @param unsavedLoan
     * @param user
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Loan> patch(
            @PathVariable(value = "id") long id,
            @RequestParam(value = "loanOperate", defaultValue = "0") int loanOperate,
            @RequestBody Loan unsavedLoan,
            @AuthenticationPrincipal User user) throws Throwable {
        unsavedLoan.setId(id);
        unsavedLoan.setModifiedTime(System.currentTimeMillis() / 1000);

        LoanOperation loanOperation = LoanOperation.valueOf(loanOperate);
        Assert.notNull(loanOperation, "Loan operate error.");

        // 产品信息的贷款方式会影响贷款状态变更，因此需要获取产品信息
        Loan savedLoan = loanService.findOne(unsavedLoan.getId(), true);
        if (savedLoan == null || savedLoan.getStatus() == null) {
            throw new ResourceNotFoundException("Loan", "id", String.valueOf(id));
        }
        unsavedLoan.setReleaseStatus(savedLoan.getReleaseStatus());
        savedLoan = loanService.handle(user, loanOperation, unsavedLoan, savedLoan);

        return new ResponseEntity<>(savedLoan, HttpStatus.OK);
    }

    /**
     * 放款操作
     * @param id
     * @param unsavedLoan
     * @param user
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/{id}/release", method = RequestMethod.PATCH)
    public ResponseEntity<Loan> release(
            @PathVariable(value = "id") long id,
            @RequestBody Loan unsavedLoan,
            @AuthenticationPrincipal User user) throws Throwable {
        unsavedLoan.setId(id);
        unsavedLoan.setModifiedTime(System.currentTimeMillis() / 1000);

        Loan savedLoan = loanService.findOne(unsavedLoan.getId(), true);
        if (savedLoan == null || savedLoan.getStatus() == null) {
            throw new ResourceNotFoundException("Loan", "id", String.valueOf(id));
        }

        Assert.notNull(unsavedLoan.getReleaseCreatedTime(), "Release time cannot be null.");
        Assert.notNull(unsavedLoan.getReleaseFileIds(), "Release file ids cannot be null.");

        // 放款条件
        // 过户后放款：过户材料审核后
        // 抵押后放款：过户抵押材料审核后
        Assert.notNull(savedLoan.getReleaseStatus(), "Release status error.");
        ReleaseStatus targetReleaseStatus = null;
        if (savedLoan.getReleaseStatus() == ReleaseStatus.MORTGAGE_RELEASE_PENDING.value() || savedLoan.getReleaseStatus() == ReleaseStatus.MORTGAGE_RELEASED.value()) {
            targetReleaseStatus = ReleaseStatus.MORTGAGE_RELEASED;
        } else if (savedLoan.getReleaseStatus() == ReleaseStatus.TRANSFER_RELEASE_PENDING.value() || savedLoan.getReleaseStatus() == ReleaseStatus.TRANSFER_RELEASED.value()) {
            targetReleaseStatus = ReleaseStatus.TRANSFER_RELEASED;
        }
        Assert.notNull(targetReleaseStatus, "Release status error.");

        savedLoan.setReleaseStatus(targetReleaseStatus.value());
        savedLoan.setReleaseCreatedTime(unsavedLoan.getReleaseCreatedTime());
        savedLoan.setReleaseFileIds(unsavedLoan.getReleaseFileIds());
        savedLoan.setRemark("#release!#");

        return new ResponseEntity<>(loanService.save(user, savedLoan, true), HttpStatus.OK);
    }
}
