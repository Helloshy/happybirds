package cheanxin.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Jawinton on 16/12/30.
 * 贷款操作日志
 */
@Entity
@Table(indexes = {@Index(name = "idx_loan_id_from_status_to_status", columnList = "loanId, fromStatus, toStatus"), @Index(name = "idx_operator_username", columnList = "operatorUsername")})
public class LoanLog {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '自增id'")
    private Long id;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '贷款id'")
    private Long loanId;

    @NotNull
    @Column(columnDefinition = "VARCHAR(20) COMMENT '操作人username'")
    private String operatorUsername;

    @NotNull
    @Min(0)
    @Max(99)
    @Column(columnDefinition = "TINYINT(2) UNSIGNED COMMENT '原贷款状态'")
    private Integer fromStatus;

    @NotNull
    @Min(0)
    @Max(99)
    @Column(columnDefinition = "TINYINT(2) UNSIGNED COMMENT '目标贷款状态'")
    private Integer toStatus;

    @Size(max = 255)
    @Column(columnDefinition = "VARCHAR(255) COMMENT '备注'")
    private String remark;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    private Long createdTime;

    public LoanLog() {}

    public LoanLog(Long loanId, String operatorUsername, Integer fromStatus, Integer toStatus, String remark, Long createdTime) {
        this.loanId = loanId;
        this.operatorUsername = operatorUsername;
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
        this.remark = remark;
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "LoanLog{" +
                "loanId=" + loanId +
                ", id=" + id +
                ", operatorUsername='" + operatorUsername + '\'' +
                ", fromStatus=" + fromStatus +
                ", toStatus=" + toStatus +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public String getOperatorUsername() {
        return operatorUsername;
    }

    public void setOperatorUsername(String operatorUsername) {
        this.operatorUsername = operatorUsername;
    }

    public Integer getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(Integer fromStatus) {
        this.fromStatus = fromStatus;
    }

    public Integer getToStatus() {
        return toStatus;
    }

    public void setToStatus(Integer toStatus) {
        this.toStatus = toStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }
}
