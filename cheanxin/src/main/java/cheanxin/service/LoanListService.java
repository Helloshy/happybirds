package cheanxin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 获取贷款列表通用接口类
 * Created by Jawinton on 17/02/08.
 */
public abstract class LoanListService<T> {
    /**
     * 贷款列表接口
     * @param sourceCityIds
     * @param creatorUsername
     * @param sourceChannel
     * @param applicantName
     * @param applicantMobileNumber
     * @param createdTimeFrom
     * @param createdTimeTo
     * @param status
     * @param page
     * @param size
     * @return
     */
    abstract public Page<T> list(Set<Long> sourceCityIds, String creatorUsername, int sourceChannel, String applicantName, String applicantMobileNumber, long createdTimeFrom, long createdTimeTo, Set<Integer> status, int releaseStatus, int page, int size);

    protected class SearchLoan {
        private Set<Long> sourceCityIds;
        private String creatorUsername;
        private int sourceChannel;
        private String applicantName;
        private String applicantMobileNumber;
        private long createdTimeFrom;
        private long createdTimeTo;
        private Set<Integer> status;
        private int releaseStatus;

        public SearchLoan() {}

        public Set<Long> getSourceCityIds() {
            return sourceCityIds;
        }

        public void setSourceCityIds(Set<Long> sourceCityIds) {
            this.sourceCityIds = sourceCityIds;
        }

        public String getCreatorUsername() {
            return creatorUsername;
        }

        public void setCreatorUsername(String creatorUsername) {
            this.creatorUsername = creatorUsername;
        }

        public int getSourceChannel() {
            return sourceChannel;
        }

        public void setSourceChannel(int sourceChannel) {
            this.sourceChannel = sourceChannel;
        }

        public String getApplicantName() {
            return applicantName;
        }

        public void setApplicantName(String applicantName) {
            this.applicantName = applicantName;
        }

        public String getApplicantMobileNumber() {
            return applicantMobileNumber;
        }

        public void setApplicantMobileNumber(String applicantMobileNumber) {
            this.applicantMobileNumber = applicantMobileNumber;
        }

        public long getCreatedTimeFrom() {
            return createdTimeFrom;
        }

        public void setCreatedTimeFrom(long createdTimeFrom) {
            this.createdTimeFrom = createdTimeFrom;
        }

        public long getCreatedTimeTo() {
            return createdTimeTo;
        }

        public void setCreatedTimeTo(long createdTimeTo) {
            this.createdTimeTo = createdTimeTo;
        }

        public Set<Integer> getStatus() {
            return status;
        }

        public void setStatus(Set<Integer> status) {
            this.status = status;
        }

        public int getReleaseStatus() {
            return releaseStatus;
        }

        public void setReleaseStatus(int releaseStatus) {
            this.releaseStatus = releaseStatus;
        }
    }

    /**
     * Generate where clause dynamically.
     * @param sourceCityIds
     * @param creatorUsername
     * @param sourceChannel
     * @param applicantName
     * @param applicantMobileNumber
     * @param createdTimeFrom
     * @param createdTimeTo
     * @param status
     * @return Specification
     */
    public Specification<T> getWhereClause(Set<Long> sourceCityIds, String creatorUsername, int sourceChannel, String applicantName, String applicantMobileNumber, long createdTimeFrom, long createdTimeTo, Set<Integer> status, int releaseStatus) {
        SearchLoan searchLoan = new SearchLoan();
        searchLoan.setSourceCityIds(sourceCityIds);
        searchLoan.setCreatorUsername(creatorUsername);
        searchLoan.setSourceChannel(sourceChannel);
        searchLoan.setApplicantName(applicantName);
        searchLoan.setApplicantMobileNumber(applicantMobileNumber);
        searchLoan.setCreatedTimeFrom(createdTimeFrom);
        searchLoan.setCreatedTimeTo(createdTimeTo);
        searchLoan.setStatus(status);
        searchLoan.setReleaseStatus(releaseStatus);

        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                if (searchLoan.getSourceCityIds() != null && !searchLoan.getSourceCityIds().isEmpty()) {
                    predicate.add(root.get("sourceCityId").as(Long.class).in(searchLoan.getSourceCityIds()));
                }
                if (searchLoan.getCreatorUsername() != null && !searchLoan.getCreatorUsername().trim().isEmpty()) {
                    predicate.add(cb.equal(root.get("creatorUsername").as(String.class), searchLoan.getCreatorUsername()));
                }
                if (searchLoan.getCreatedTimeFrom() > 0) {
                    predicate.add(cb.ge(root.get("createdTime").as(Long.class), searchLoan.getCreatedTimeFrom()));
                }
                if (searchLoan.getCreatedTimeTo() > 0) {
                    predicate.add(cb.le(root.get("createdTime").as(Long.class), searchLoan.getCreatedTimeTo()));
                }
                if (searchLoan.getSourceChannel() > 0) {
                    predicate.add(cb.equal(root.get("sourceChannel").as(Integer.class), searchLoan.getSourceChannel()));
                }
                if (searchLoan.getApplicantName() != null && !searchLoan.getApplicantName().trim().isEmpty()) {
                    predicate.add(cb.like(root.get("applicantName").as(String.class), searchLoan.getApplicantName() + "%"));
                }
                if (searchLoan.getApplicantMobileNumber() != null && !searchLoan.getApplicantMobileNumber().trim().isEmpty()) {
                    predicate.add(cb.equal(root.get("applicantMobileNumber").as(String.class), searchLoan.getApplicantMobileNumber()));
                }
                if (searchLoan.getStatus() != null && !searchLoan.getStatus().isEmpty()) {
                    predicate.add(root.get("status").as(Integer.class).in(searchLoan.getStatus()));
                }
                if (searchLoan.getReleaseStatus() >= 0) {
                    predicate.add(cb.equal(root.get("releaseStatus").as(Integer.class), searchLoan.getReleaseStatus()));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }
}
