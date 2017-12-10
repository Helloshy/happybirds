package cheanxin.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Created by Jawinton on 16/12/30.
 * 产品
 */
@Entity
@Table(indexes = { @Index(name = "idx_product_template_id", columnList = "productTemplateId"), @Index(name = "idx_city_id", columnList = "cityId") })
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '自增id'")
    // 自增id
    private Long id;

    @NotNull
    @Min(0)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '产品模板id，如果productTemplateId为0表示自身为产品模板'")
    private Long productTemplateId;

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

    @NotNull
    @Min(0)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '城市id'")
    // 城市id
    private Long cityId;

    @NotNull
    @Min(0)
    @Max(8)
    @Column(columnDefinition = "TINYINT(1) UNSIGNED COMMENT '产品状态'")
    private Integer status;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '修改时间'")
    // 修改时间
    private Long modifiedTime;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    // 创建时间
    private Long createdTime;

    @Column(columnDefinition = "VARCHAR(20) COMMENT '创建人'")
    // 创建人
    private String creatorUsername;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '产品最长可用车辆年限'")
    @Max(1000)
    // 产品最长可用车辆年限
    private Integer maxAvailableVehicleYear;

    @NotNull
    @Column(columnDefinition = "TINYINT(1) UNSIGNED DEFAULT TRUE COMMENT '是否启用'")
    private Boolean enabled;

    @Transient
    private String remark;

    public Product() {}

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productTemplateId=" + productTemplateId +
                ", name='" + name + '\'' +
                ", productType=" + productType +
                ", repaymentMethod=" + repaymentMethod +
                ", minAvailableRate=" + minAvailableRate +
                ", maxAvailableRate=" + maxAvailableRate +
                ", availableTerms='" + availableTerms + '\'' +
                ", loanPolicy=" + loanPolicy +
                ", loanMonthlyInterestRate=" + loanMonthlyInterestRate +
                ", cityId=" + cityId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductTemplateId() {
        return productTemplateId;
    }

    public void setProductTemplateId(Long productTemplateId) {
        this.productTemplateId = productTemplateId;
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

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getMaxAvailableVehicleYear() {
        return maxAvailableVehicleYear;
    }

    public void setMaxAvailableVehicleYear(Integer maxAvailableVehicleYear) {
        this.maxAvailableVehicleYear = maxAvailableVehicleYear;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
