package cheanxin.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Jawinton on 16/12/30.
 * 产品
 */
@Entity
public class ProductTemplate {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '自增id'")
    // 自增id
    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(columnDefinition = "VARCHAR(50) COMMENT '产品名称'")
    // 产品名称
    private String name;

    @NotNull
    @Min(0)
    @Max(16)
    @Column(columnDefinition = "TINYINT(2) UNSIGNED COMMENT '产品类型'")
    // 产品类型
    private Integer productType;

    @NotNull
    @Min(0)
    @Max(16)
    @Column(columnDefinition = "TINYINT(2) UNSIGNED COMMENT '还款类型'")
    // 还款类型
    private Integer repaymentMethod;

    @NotNull
    @Min(1)
    @Max(10)
    @Column(columnDefinition = "TINYINT(1) UNSIGNED COMMENT '最低可贷成数'")
    // 最低可贷成数
    private Integer minAvailableRate;

    @NotNull
    @Min(1)
    @Max(10)
    @Column(columnDefinition = "TINYINT(1) UNSIGNED COMMENT '最高可贷成数'")
    // 最高可贷成数
    private Integer maxAvailableRate;

    @NotNull
    @NotEmpty
    @Column(columnDefinition = "VARCHAR(100) COMMENT '可贷期数'")
//    @Pattern(regexp = "([0-9]+,)*[0-9]+")
    // 可贷期数
    private String availableTerms;

    @NotNull
    @Min(0)
    @Max(16)
    @Column(columnDefinition = "TINYINT(2) UNSIGNED COMMENT '贷款政策'")
    // 贷款政策
    private Integer loanPolicy;

    @NotNull
    @Min(0)
    @Max(10)
    @Column(columnDefinition = "DECIMAL(5,4) UNSIGNED COMMENT '贷款月利率'")
    // 贷款月利率
    private Float loanMonthlyInterestRate;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '产品最长可用车辆年限'")
    @Max(1000)
    // 产品最长可用车辆年限
    private Integer maxAvailableVehicleYear;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '修改时间'")
    // 修改时间
    private Long modifiedTime;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    // 创建时间
    private Long createdTime;

    @Column(columnDefinition = "VARCHAR(20) COMMENT '创建人'")
    // 创建人
    private String creatorUsername;

    public ProductTemplate() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getRepaymentMethod() {
        return repaymentMethod;
    }

    public void setRepaymentMethod(Integer repaymentMethod) {
        this.repaymentMethod = repaymentMethod;
    }

    public Integer getMinAvailableRate() {
        return minAvailableRate;
    }

    public void setMinAvailableRate(Integer minAvailableRate) {
        this.minAvailableRate = minAvailableRate;
    }

    public Integer getMaxAvailableRate() {
        return maxAvailableRate;
    }

    public void setMaxAvailableRate(Integer maxAvailableRate) {
        this.maxAvailableRate = maxAvailableRate;
    }

    public String getAvailableTerms() {
        return availableTerms;
    }

    public void setAvailableTerms(String availableTerms) {
        this.availableTerms = availableTerms;
    }

    public Integer getLoanPolicy() {
        return loanPolicy;
    }

    public void setLoanPolicy(Integer loanPolicy) {
        this.loanPolicy = loanPolicy;
    }

    public Float getLoanMonthlyInterestRate() {
        return loanMonthlyInterestRate;
    }

    public void setLoanMonthlyInterestRate(Float loanMonthlyInterestRate) {
        this.loanMonthlyInterestRate = loanMonthlyInterestRate;
    }

    public Long getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    public Integer getMaxAvailableVehicleYear() {
        return maxAvailableVehicleYear;
    }

    public void setMaxAvailableVehicleYear(Integer maxAvailableVehicleYear) {
        this.maxAvailableVehicleYear = maxAvailableVehicleYear;
    }
}
