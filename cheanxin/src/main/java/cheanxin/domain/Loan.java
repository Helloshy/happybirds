package cheanxin.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Created by Jawinton on 17/02/08.
 */
@Entity
@Table(indexes = {@Index(name = "idx_creator_username", columnList = "creatorUsername"),
        @Index(name = "idx_created_time", columnList = "createdTime"),
        @Index(name = "idx_source_city_id", columnList = "sourceCityId"),
        @Index(name = "idx_applicant_name", columnList = "applicantName")})
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '自增id'")
    private Long id;

    //@NotNull
    @Min(0)
    @Max(Integer.MAX_VALUE)
    @Column(columnDefinition = "INT(10) UNSIGNED DEFAULT NULL COMMENT '车辆成交价格'")
    private Integer vehicleDealPrice;

    @Min(0)
    @Max(Integer.MAX_VALUE)
    @Column(columnDefinition = "INT(10) UNSIGNED DEFAULT NULL COMMENT '车辆评估价格'")
    private Integer vehiclePredictPrice;

    //@NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED DEFAULT NULL COMMENT '产品id'")
    private Long productId;

    @Transient
    private String productName;

    @Transient
    private String productType;

    @Transient
    private Integer productLoanPolicy;

    @Transient
    private Integer productMinAvailableRate;

    @Transient
    private Integer productMaxAvailableRate;

    @Min(0)
    @Max(10)
    @Column(columnDefinition = "DECIMAL(5,4) UNSIGNED COMMENT '贷款月利率'")
    private Float productLoanMonthlyInterestRate;

    @Transient
    private String[] productAvailableTerms;

    //@NotNull
    @Min(1)
    @Max(10)
    @Column(columnDefinition = "TINYINT(2) UNSIGNED DEFAULT NULL COMMENT '客户想要的贷款比率'")
    private Integer applicantLoanRate;

    @Min(0)
    @Max(Integer.MAX_VALUE)
    @Column(columnDefinition = "INT(10) UNSIGNED DEFAULT NULL COMMENT '客户想要的贷款金额'")
    private Integer applicantLoanPrice;

    //@NotNull
    @Min(1)
    @Max(360)
    @Column(columnDefinition = "SMALLINT(3) UNSIGNED DEFAULT NULL COMMENT '客户想要的贷款期数'")
    private Integer loanTerms;

    //@NotNull
    @Min(1)
    @Max(10)
    @Column(columnDefinition = "TINYINT(2) UNSIGNED DEFAULT NULL COMMENT '审批核准的最高贷款比率'")
    private Integer reviewLoanRate;

    @Min(0)
    @Max(Integer.MAX_VALUE)
    @Column(columnDefinition = "INT(10) UNSIGNED DEFAULT NULL COMMENT '审批的贷款金额'")
    private Integer reviewLoanPrice;

    //@NotNull
    @Min(1)
    @Max(360)
    @Column(columnDefinition = "SMALLINT(3) UNSIGNED DEFAULT NULL COMMENT '审批的贷款期数'")
    private Integer reviewLoanTerms;

    @Size(max = 255)
    @Column(columnDefinition = "VARCHAR(255) DEFAULT NULL COMMENT '备注'")
    private String remark;

    //@NotNull
    @Size(max = 30)
    @Column(columnDefinition = "VARCHAR(30) DEFAULT NULL COMMENT '跟单金融专员'")
    private String sourceFinancialCommissioner;

    //@NotNull
    @Size(max = 30)
    @Column(columnDefinition = "VARCHAR(30) DEFAULT NULL COMMENT '收单员'")
    private String sourceReceiver;

    //@NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED DEFAULT NULL COMMENT '城市id'")
    private Long sourceCityId;

    //@NotNull
    @Min(0)
    @Max(8)
    @Column(columnDefinition = "TINYINT(2) UNSIGNED DEFAULT NULL COMMENT '来源渠道'")
    private Integer sourceChannel;

    //@NotNull
    @Size(max = 30)
    @Column(columnDefinition = "VARCHAR(30) DEFAULT NULL COMMENT '来源联系人姓名'")
    private String sourcePersonName;

    //@NotNull
    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT NULL COMMENT '来源联系人电话'")
    private String sourcePersonTel;

    //@NotNull
    @Size(max = 30)
    @Column(columnDefinition = "VARCHAR(30) DEFAULT NULL COMMENT '贷款申请人姓名'")
    private String applicantName;

    //@NotNull
    @Min(0)
    @Max(8)
    @Column(columnDefinition = "TINYINT(1) UNSIGNED DEFAULT NULL COMMENT '婚姻状态'")
    private Integer applicantMarriage;

    //@NotNull
    @Min(0)
    @Max(8)
    @Column(columnDefinition = "TINYINT(2) UNSIGNED DEFAULT NULL COMMENT '证件类型'")
    private Integer applicantCertificateType;

    //@NotNull
    @Size(min = 6, max = 20)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT NULL COMMENT '证件号码'")
    private String applicantCertificateNumber;

    @Min(0)
    @Max(4)
    @Column(columnDefinition = "TINYINT(1) UNSIGNED DEFAULT NULL COMMENT '性别'")
    private Integer applicantGender;

    @Size(min = 4, max = 10)
    @Column(columnDefinition = "CHAR(10) DEFAULT NULL COMMENT '出生年月'")
    private String applicantBirthYearMonth;

    @Size(max = 30)
    @Column(columnDefinition = "VARCHAR(30) DEFAULT NULL COMMENT '学历'")
    private String applicantQualification;

    //@NotNull
    @Size(min = 11, max = 11)
    @Column(columnDefinition = "CHAR(11) DEFAULT NULL COMMENT '申请人手机号'")
    @Pattern(regexp = "^1[34578][0-9]{9}$")
    private String applicantMobileNumber;

    @Column(columnDefinition = "INT(10) UNSIGNED DEFAULT NULL COMMENT '申请人户籍所在城市'")
    private Long applicantCensusCityId;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) DEFAULT NULL COMMENT '现居住地'")
    private String applicantAddress;

    //@NotNull
    @Min(0)
    @Column(columnDefinition = "INT(10) DEFAULT NULL COMMENT '月收入'")
    private Long applicantIncomePerMonth;

    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT NULL COMMENT '申请人电话'")
    private String applicantTelephone;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) DEFAULT NULL COMMENT '工作单位名称'")
    private String applicantEmployerName;

    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT NULL COMMENT '单位电话'")
    private String applicantEmployerTelephone;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) DEFAULT NULL COMMENT '单位所在地'")
    private String applicantEmployerAddress;

    @Size(max = 50)
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '工作单位性质(国企、私企、外企等等)'")
    private String applicantEmployerType;

    @Size(max = 100)
    @Column(columnDefinition = "VARCHAR(100) DEFAULT NULL COMMENT '工作单位所属行业'")
    private String applicantEmployerIndustry;

    @Size(max = 50)
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '申请人工作岗位'")
    private String applicantPost;

    @Size(max = 50)
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '申请人职业'")
    private String applicantOccupation;

    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT NULL COMMENT '申请人工作年限'")
    private String applicantWorkYears;

    @Size(max = 50)
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '申请人职务'")
    private String applicantPosition;

    @Size(max = 50)
    @Column(columnDefinition = "VARCHAR(50) DEFAULT NULL COMMENT '申请人职称'")
    private String applicantJobTitle;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) DEFAULT NULL COMMENT '邮寄地址'")
    private String applicantPostAddress;

    @Size(min = 6, max = 6)
    @Column(columnDefinition = "CHAR(6) DEFAULT NULL COMMENT '邮编'")
    private String applicantPostCode;

    @Size(min = 2, max = 30)
    @Column(columnDefinition = "VARCHAR(30) DEFAULT NULL COMMENT '紧急联系人1'")
    private String applicantFirstEmergencyContact;

    @Size(max = 10)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT NULL COMMENT '紧急联系人1与申请人的关系'")
    private String applicantFirstEmergencyContactRelationship;

    @Pattern(regexp = "^1[34578][0-9]{9}$")
    @Size(min = 11, max = 11)
    @Column(columnDefinition = "CHAR(11) DEFAULT NULL COMMENT '紧急联系人1手机号码'")
    private String applicantFirstEmergencyContactMobileNumber;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) DEFAULT NULL COMMENT '紧急联系人1地址'")
    private String applicantFirstEmergencyContactAddress;

    @Size(min = 2, max = 30)
    @Column(columnDefinition = "VARCHAR(30) DEFAULT NULL COMMENT '紧急联系人2'")
    private String applicantSecondEmergencyContact;

    @Size(max = 10)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT NULL COMMENT '紧急联系人1与申请人的关系'")
    private String applicantSecondEmergencyContactRelationship;

    @Pattern(regexp = "^1[34578][0-9]{9}$")
    @Size(min = 11, max = 11)
    @Column(columnDefinition = "CHAR(11) DEFAULT NULL COMMENT '紧急联系人2手机号码'")
    private String applicantSecondEmergencyContactMobileNumber;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) DEFAULT NULL COMMENT '紧急联系人2地址'")
    private String applicantSecondEmergencyContactAddress;

    //@NotNull
    @Size(max = 1000)
    @Column(columnDefinition = "VARCHAR(1000) DEFAULT NULL COMMENT '申请图片列表'")
    private String applicationPicUrl;

    //@NotNull
    @Size(max = 100)
    @Column(columnDefinition = "VARCHAR(100) DEFAULT NULL COMMENT '证件图片'")
    private String applicantCertificateFileIds;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) DEFAULT NULL COMMENT '学历证明图片'")
    private String applicantQualificationFileIds;

    @Size(max = 500)
    @Column(columnDefinition = "VARCHAR(500) DEFAULT NULL COMMENT '收入证明图片'")
    private String applicantIncomeFileIds;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) DEFAULT NULL COMMENT '房产图片'")
    private String applicantEstateFileIds;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) DEFAULT NULL COMMENT '车辆图片'")
    private String applicantVehicleFileIds;

    @Size(max = 500)
    @Column(columnDefinition = "VARCHAR(500) DEFAULT NULL COMMENT '其他图片'")
    private String applicantOtherFileIds;

    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(200) DEFAULT NULL COMMENT '共同申请人姓名'")
    private String coApplicantName;

    @Size(max = 10)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT NULL COMMENT '共同申请人与申请人的关系'")
    private String coApplicantRelationship;

    @Column(columnDefinition = "INT(10) UNSIGNED DEFAULT NULL COMMENT '共同申请人户籍所在城市'")
    private Long coApplicantCensusCityId;

    @Size(min = 6, max = 20)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT NULL COMMENT '共同申请人证件号码'")
    private String coApplicantCertificateNumber;

    @Size(min = 11, max = 11)
    @Column(columnDefinition = "CHAR(11) DEFAULT NULL COMMENT '共同申请人手机号'")
    @Pattern(regexp = "^1[34578][0-9]{9}$")
    private String coApplicantMobileNumber;

    @Size(max = 30)
    @Column(columnDefinition = "VARCHAR(30) DEFAULT NULL COMMENT '共同申请人学历'")
    private String coApplicantQualification;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) DEFAULT NULL COMMENT '共同申请人现居住地'")
    private String coApplicantAddress;

    @Min(0)
    @Column(columnDefinition = "INT(10) DEFAULT NULL COMMENT '共同申请人月收入'")
    private Long coApplicantIncomePerMonth;

    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT NULL COMMENT '共同申请人电话'")
    private String coApplicantTelephone;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) DEFAULT NULL COMMENT '共同申请人工作单位名称'")
    private String coApplicantEmployerName;

    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT NULL COMMENT '共同申请人单位电话'")
    private String coApplicantEmployerTelephone;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) DEFAULT NULL COMMENT '共同申请人单位所在地'")
    private String coApplicantEmployerAddress;

    @Size(min = 5, max = 100)
    @Column(columnDefinition = "VARCHAR(100) DEFAULT NULL COMMENT '共同申请人证件图片'")
    private String coApplicantCertificateFileIds;

    @Size(max = 500)
    @Column(columnDefinition = "VARCHAR(500) DEFAULT NULL COMMENT '共同申请人收入证明图片'")
    private String coApplicantIncomeFileIds;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) DEFAULT NULL COMMENT '共同申请人房产图片'")
    private String coApplicantEstateFileIds;

    @Size(max = 500)
    @Column(columnDefinition = "VARCHAR(500) DEFAULT NULL COMMENT '共同申请人其他图片'")
    private String coApplicantOtherFileIds;

    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT NULL COMMENT '担保人姓名'")
    private String guarantorName;

    @Column(columnDefinition = "INT(10) UNSIGNED DEFAULT NULL COMMENT '担保人户籍所在城市'")
    private Long guarantorCensusCityId;

    @Size(max = 10)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT NULL COMMENT '担保人与申请人的关系'")
    private String guarantorRelationship;

    @Size(min = 6, max = 20)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT NULL COMMENT '担保人证件号码'")
    private String guarantorCertificateNumber;

    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT NULL COMMENT '担保人房产权属'")
    private String guarantorRealEstateOwnType;

    @Min(0)
    @Column(columnDefinition = "INT(10) DEFAULT NULL COMMENT '担保人月收入'")
    private Long guarantorIncomePerMonth;

    @Size(min = 11, max = 11)
    @Column(columnDefinition = "CHAR(11) DEFAULT NULL COMMENT '担保人手机号'")
    @Pattern(regexp = "^1[34578][0-9]{9}$")
    private String guarantorMobileNumber;
    
    @Size(min = 5, max = 100)
    @Column(columnDefinition = "VARCHAR(100) DEFAULT NULL COMMENT '证件图片'")
    private String guarantorCertificateFileIds;

    @Size(max = 500)
    @Column(columnDefinition = "VARCHAR(500) DEFAULT NULL COMMENT '收入证明图片'")
    private String guarantorIncomeFileIds;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) DEFAULT NULL COMMENT '房产图片'")
    private String guarantorEstateFileIds;

    @Size(max = 500)
    @Column(columnDefinition = "VARCHAR(500) DEFAULT NULL COMMENT '其他图片'")
    private String guarantorOtherFileIds;

    //@NotNull
    @Size(min = 17, max = 17)
    @Column(columnDefinition = "CHAR(17) DEFAULT NULL COMMENT '车辆vin码'")
    private String vehicleVin;

    //@NotNull
    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT NULL COMMENT '厂家'")
    private String vehicleManufacturers;

    //@NotNull
    @Min(0)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '品牌ID'")
    private Long vehicleBrand;

    //@NotNull
    @Min(0)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '车系ID'")
    private Long vehicleSeries;

    @Min(0)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '车型ID'")
    private Long vehicleType;

    @Size(max = 150)
    @Column(columnDefinition = "VARCHAR(100) DEFAULT NULL COMMENT '车型'")
    private String vehicleDesc;

    @Size(min = 4, max = 10)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT NULL COMMENT '生产年月'")
    private String vehicleProductionYearMonth;

    @Size(min = 4, max = 10)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT NULL COMMENT '首次登记年月'")
    private String vehicleRegistrationYearMonth;

    //@NotNull
    @Digits(integer = 3, fraction = 4)
    @Column(columnDefinition = "DECIMAL(7,4) UNSIGNED DEFAULT NULL COMMENT '公里数'")
    private Float vehicleKilometers;

    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT NULL COMMENT '使用性质'")
    private String vehicleUtilityType;

    @Size(max = 10)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT NULL COMMENT '排放标准'")
    private String vehicleEmission;

    //@NotNull
    @Size(min = 5, max = 100)
    @Column(columnDefinition = "VARCHAR(100) DEFAULT NULL COMMENT '车辆登记证书图片'")
    private String vehicleRegistrationCertificateFileIds;

    //@NotNull
    @Size(min = 5, max = 100)
    @Column(columnDefinition = "VARCHAR(100) DEFAULT NULL COMMENT '机动车行驶证图片'")
    private String vehicleLicenseFileIds;

    //@NotNull
    @Size(min = 5, max = 2000)
    @Column(columnDefinition = "VARCHAR(2000) DEFAULT NULL COMMENT '车辆图片'")
    private String vehicleFileIds;

    //@NotNull
    @Min(0)
    @Max(64)
    @Column(columnDefinition = "TINYINT(2) UNSIGNED COMMENT '贷款状态'")
    private Integer status;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    private Long createdTime;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '修改时间'")
    private Long modifiedTime;

    @Size(min = 3, max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '创建贷款草稿用户'")
    private String creatorUsername;

    @Transient
    private String reviewRemark;

    @Size(max = 255)
    @Column(columnDefinition = "VARCHAR(255) COMMENT '补充材料重新申请理由'")
    private String materialsRemark;

    @Size(max = 500)
    @Column(columnDefinition = "VARCHAR(500) COMMENT '补充材料照片'")
    private String materialsFileIds;

    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '合同编号'")
    private String contractNumber;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '合同签约日期'")
    private Long contractCreatedTime;

    @Size(max = 500)
    @Column(columnDefinition = "VARCHAR(500) COMMENT '合同照片'")
    private String contractFileIds;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '过户日期'")
    private Long transferCreatedTime;

    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '过户后车牌号'")
    private String transferCardNumber;

    @Size(max = 500)
    @Column(columnDefinition = "VARCHAR(500) COMMENT '过户材料'")
    private String transferFileIds;

    @Size(max = 500)
    @Column(columnDefinition = "VARCHAR(500) COMMENT '过户GPS安装材料'")
    private String transferGPSFileIds;

    @Size(max = 500)
    @Column(columnDefinition = "VARCHAR(500) COMMENT '过户保险合同'")
    private String transferInsuranceFileIds;

    @Min(0)
    @Max(8)
    @Column(columnDefinition = "TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '放款状态'")
    private Integer releaseStatus;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '放款日期'")
    private Long releaseCreatedTime;

    @Size(max = 500)
    @Column(columnDefinition = "VARCHAR(500) COMMENT '放款材料'")
    private String releaseFileIds;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '抵押日期'")
    private Long mortgageCreatedTime;

    @Size(max = 500)
    @Column(columnDefinition = "VARCHAR(500) COMMENT '抵押材料'")
    private String mortgageFileIds;

    public Loan() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVehicleDealPrice() {
        return vehicleDealPrice;
    }

    public void setVehicleDealPrice(Integer vehicleDealPrice) {
        this.vehicleDealPrice = vehicleDealPrice;
    }

    public Integer getVehiclePredictPrice() {
        return vehiclePredictPrice;
    }

    public void setVehiclePredictPrice(Integer vehiclePredictPrice) {
        this.vehiclePredictPrice = vehiclePredictPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getProductMinAvailableRate() {
        return productMinAvailableRate;
    }

    public void setProductMinAvailableRate(Integer productMinAvailableRate) {
        this.productMinAvailableRate = productMinAvailableRate;
    }

    public Integer getProductMaxAvailableRate() {
        return productMaxAvailableRate;
    }

    public void setProductMaxAvailableRate(Integer productMaxAvailableRate) {
        this.productMaxAvailableRate = productMaxAvailableRate;
    }

    public Integer getApplicantLoanRate() {
        return applicantLoanRate;
    }

    public void setApplicantLoanRate(Integer applicantLoanRate) {
        this.applicantLoanRate = applicantLoanRate;
    }

    public Integer getApplicantLoanPrice() {
        return applicantLoanPrice;
    }

    public void setApplicantLoanPrice(Integer applicantLoanPrice) {
        this.applicantLoanPrice = applicantLoanPrice;
    }

    public Integer getReviewLoanRate() {
        return reviewLoanRate;
    }

    public void setReviewLoanRate(Integer reviewLoanRate) {
        this.reviewLoanRate = reviewLoanRate;
    }

    public Integer getReviewLoanPrice() {
        return reviewLoanPrice;
    }

    public void setReviewLoanPrice(Integer reviewLoanPrice) {
        this.reviewLoanPrice = reviewLoanPrice;
    }

    public Float getProductLoanMonthlyInterestRate() {
        return productLoanMonthlyInterestRate;
    }

    public void setProductLoanMonthlyInterestRate(Float productLoanMonthlyInterestRate) {
        this.productLoanMonthlyInterestRate = productLoanMonthlyInterestRate;
    }

    public String[] getProductAvailableTerms() {
        return productAvailableTerms;
    }

    public void setProductAvailableTerms(String[] productAvailableTerms) {
        this.productAvailableTerms = productAvailableTerms;
    }

    public Integer getLoanTerms() {
        return loanTerms;
    }

    public void setLoanTerms(Integer loanTerms) {
        this.loanTerms = loanTerms;
    }

    public Integer getReviewLoanTerms() {
        return reviewLoanTerms;
    }

    public void setReviewLoanTerms(Integer reviewLoanTerms) {
        this.reviewLoanTerms = reviewLoanTerms;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSourceFinancialCommissioner() {
        return sourceFinancialCommissioner;
    }

    public void setSourceFinancialCommissioner(String sourceFinancialCommissioner) {
        this.sourceFinancialCommissioner = sourceFinancialCommissioner;
    }

    public String getSourceReceiver() {
        return sourceReceiver;
    }

    public void setSourceReceiver(String sourceReceiver) {
        this.sourceReceiver = sourceReceiver;
    }

    public Long getSourceCityId() {
        return sourceCityId;
    }

    public void setSourceCityId(Long sourceCityId) {
        this.sourceCityId = sourceCityId;
    }

    public Integer getSourceChannel() {
        return sourceChannel;
    }

    public void setSourceChannel(Integer sourceChannel) {
        this.sourceChannel = sourceChannel;
    }

    public String getSourcePersonName() {
        return sourcePersonName;
    }

    public void setSourcePersonName(String sourcePersonName) {
        this.sourcePersonName = sourcePersonName;
    }

    public String getSourcePersonTel() {
        return sourcePersonTel;
    }

    public void setSourcePersonTel(String sourcePersonTel) {
        this.sourcePersonTel = sourcePersonTel;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public Integer getApplicantMarriage() {
        return applicantMarriage;
    }

    public void setApplicantMarriage(Integer applicantMarriage) {
        this.applicantMarriage = applicantMarriage;
    }

    public Integer getApplicantCertificateType() {
        return applicantCertificateType;
    }

    public void setApplicantCertificateType(Integer applicantCertificateType) {
        this.applicantCertificateType = applicantCertificateType;
    }

    public String getApplicantCertificateNumber() {
        return applicantCertificateNumber;
    }

    public void setApplicantCertificateNumber(String applicantCertificateNumber) {
        this.applicantCertificateNumber = applicantCertificateNumber;
    }

    public Integer getApplicantGender() {
        return applicantGender;
    }

    public void setApplicantGender(Integer applicantGender) {
        this.applicantGender = applicantGender;
    }

    public String getApplicantBirthYearMonth() {
        return applicantBirthYearMonth;
    }

    public void setApplicantBirthYearMonth(String applicantBirthYearMonth) {
        this.applicantBirthYearMonth = applicantBirthYearMonth;
    }

    public String getApplicantQualification() {
        return applicantQualification;
    }

    public void setApplicantQualification(String applicantQualification) {
        this.applicantQualification = applicantQualification;
    }

    public String getApplicantMobileNumber() {
        return applicantMobileNumber;
    }

    public void setApplicantMobileNumber(String applicantMobileNumber) {
        this.applicantMobileNumber = applicantMobileNumber;
    }

    public Long getApplicantCensusCityId() {
        return applicantCensusCityId;
    }

    public void setApplicantCensusCityId(Long applicantCensusCityId) {
        this.applicantCensusCityId = applicantCensusCityId;
    }

    public String getApplicantAddress() {
        return applicantAddress;
    }

    public void setApplicantAddress(String applicantAddress) {
        this.applicantAddress = applicantAddress;
    }

    public Long getApplicantIncomePerMonth() {
        return applicantIncomePerMonth;
    }

    public void setApplicantIncomePerMonth(Long applicantIncomePerMonth) {
        this.applicantIncomePerMonth = applicantIncomePerMonth;
    }

    public String getApplicantTelephone() {
        return applicantTelephone;
    }

    public void setApplicantTelephone(String applicantTelephone) {
        this.applicantTelephone = applicantTelephone;
    }

    public String getApplicantEmployerName() {
        return applicantEmployerName;
    }

    public void setApplicantEmployerName(String applicantEmployerName) {
        this.applicantEmployerName = applicantEmployerName;
    }

    public String getApplicantEmployerTelephone() {
        return applicantEmployerTelephone;
    }

    public void setApplicantEmployerTelephone(String applicantEmployerTelephone) {
        this.applicantEmployerTelephone = applicantEmployerTelephone;
    }

    public String getApplicantEmployerAddress() {
        return applicantEmployerAddress;
    }

    public void setApplicantEmployerAddress(String applicantEmployerAddress) {
        this.applicantEmployerAddress = applicantEmployerAddress;
    }

    public String getApplicantEmployerType() {
        return applicantEmployerType;
    }

    public void setApplicantEmployerType(String applicantEmployerType) {
        this.applicantEmployerType = applicantEmployerType;
    }

    public String getApplicantEmployerIndustry() {
        return applicantEmployerIndustry;
    }

    public void setApplicantEmployerIndustry(String applicantEmployerIndustry) {
        this.applicantEmployerIndustry = applicantEmployerIndustry;
    }

    public String getApplicantPost() {
        return applicantPost;
    }

    public void setApplicantPost(String applicantPost) {
        this.applicantPost = applicantPost;
    }

    public String getApplicantOccupation() {
        return applicantOccupation;
    }

    public void setApplicantOccupation(String applicantOccupation) {
        this.applicantOccupation = applicantOccupation;
    }

    public String getApplicantWorkYears() {
        return applicantWorkYears;
    }

    public void setApplicantWorkYears(String applicantWorkYears) {
        this.applicantWorkYears = applicantWorkYears;
    }

    public String getApplicantPosition() {
        return applicantPosition;
    }

    public void setApplicantPosition(String applicantPosition) {
        this.applicantPosition = applicantPosition;
    }

    public String getApplicantJobTitle() {
        return applicantJobTitle;
    }

    public void setApplicantJobTitle(String applicantJobTitle) {
        this.applicantJobTitle = applicantJobTitle;
    }

    public String getApplicantPostAddress() {
        return applicantPostAddress;
    }

    public void setApplicantPostAddress(String applicantPostAddress) {
        this.applicantPostAddress = applicantPostAddress;
    }

    public String getApplicantPostCode() {
        return applicantPostCode;
    }

    public void setApplicantPostCode(String applicantPostCode) {
        this.applicantPostCode = applicantPostCode;
    }

    public String getApplicantFirstEmergencyContact() {
        return applicantFirstEmergencyContact;
    }

    public void setApplicantFirstEmergencyContact(String applicantFirstEmergencyContact) {
        this.applicantFirstEmergencyContact = applicantFirstEmergencyContact;
    }

    public String getApplicantFirstEmergencyContactRelationship() {
        return applicantFirstEmergencyContactRelationship;
    }

    public void setApplicantFirstEmergencyContactRelationship(String applicantFirstEmergencyContactRelationship) {
        this.applicantFirstEmergencyContactRelationship = applicantFirstEmergencyContactRelationship;
    }

    public String getApplicantFirstEmergencyContactMobileNumber() {
        return applicantFirstEmergencyContactMobileNumber;
    }

    public void setApplicantFirstEmergencyContactMobileNumber(String applicantFirstEmergencyContactMobileNumber) {
        this.applicantFirstEmergencyContactMobileNumber = applicantFirstEmergencyContactMobileNumber;
    }

    public String getApplicantFirstEmergencyContactAddress() {
        return applicantFirstEmergencyContactAddress;
    }

    public void setApplicantFirstEmergencyContactAddress(String applicantFirstEmergencyContactAddress) {
        this.applicantFirstEmergencyContactAddress = applicantFirstEmergencyContactAddress;
    }

    public String getApplicantSecondEmergencyContact() {
        return applicantSecondEmergencyContact;
    }

    public void setApplicantSecondEmergencyContact(String applicantSecondEmergencyContact) {
        this.applicantSecondEmergencyContact = applicantSecondEmergencyContact;
    }

    public String getApplicantSecondEmergencyContactRelationship() {
        return applicantSecondEmergencyContactRelationship;
    }

    public void setApplicantSecondEmergencyContactRelationship(String applicantSecondEmergencyContactRelationship) {
        this.applicantSecondEmergencyContactRelationship = applicantSecondEmergencyContactRelationship;
    }

    public String getApplicantSecondEmergencyContactMobileNumber() {
        return applicantSecondEmergencyContactMobileNumber;
    }

    public void setApplicantSecondEmergencyContactMobileNumber(String applicantSecondEmergencyContactMobileNumber) {
        this.applicantSecondEmergencyContactMobileNumber = applicantSecondEmergencyContactMobileNumber;
    }

    public String getApplicantSecondEmergencyContactAddress() {
        return applicantSecondEmergencyContactAddress;
    }

    public void setApplicantSecondEmergencyContactAddress(String applicantSecondEmergencyContactAddress) {
        this.applicantSecondEmergencyContactAddress = applicantSecondEmergencyContactAddress;
    }

    public String getApplicationPicUrl() {
        return applicationPicUrl;
    }

    public void setApplicationPicUrl(String applicationPicUrl) {
        this.applicationPicUrl = applicationPicUrl;
    }

    public String getApplicantCertificateFileIds() {
        return applicantCertificateFileIds;
    }

    public void setApplicantCertificateFileIds(String applicantCertificateFileIds) {
        this.applicantCertificateFileIds = applicantCertificateFileIds;
    }

    public String getApplicantQualificationFileIds() {
        return applicantQualificationFileIds;
    }

    public void setApplicantQualificationFileIds(String applicantQualificationFileIds) {
        this.applicantQualificationFileIds = applicantQualificationFileIds;
    }

    public String getApplicantIncomeFileIds() {
        return applicantIncomeFileIds;
    }

    public void setApplicantIncomeFileIds(String applicantIncomeFileIds) {
        this.applicantIncomeFileIds = applicantIncomeFileIds;
    }

    public String getApplicantEstateFileIds() {
        return applicantEstateFileIds;
    }

    public void setApplicantEstateFileIds(String applicantEstateFileIds) {
        this.applicantEstateFileIds = applicantEstateFileIds;
    }

    public String getApplicantVehicleFileIds() {
        return applicantVehicleFileIds;
    }

    public void setApplicantVehicleFileIds(String applicantVehicleFileIds) {
        this.applicantVehicleFileIds = applicantVehicleFileIds;
    }

    public String getApplicantOtherFileIds() {
        return applicantOtherFileIds;
    }

    public void setApplicantOtherFileIds(String applicantOtherFileIds) {
        this.applicantOtherFileIds = applicantOtherFileIds;
    }

    public String getCoApplicantName() {
        return coApplicantName;
    }

    public void setCoApplicantName(String coApplicantName) {
        this.coApplicantName = coApplicantName;
    }

    public String getCoApplicantRelationship() {
        return coApplicantRelationship;
    }

    public void setCoApplicantRelationship(String coApplicantRelationship) {
        this.coApplicantRelationship = coApplicantRelationship;
    }

    public Long getCoApplicantCensusCityId() {
        return coApplicantCensusCityId;
    }

    public void setCoApplicantCensusCityId(Long coApplicantCensusCityId) {
        this.coApplicantCensusCityId = coApplicantCensusCityId;
    }

    public String getCoApplicantCertificateNumber() {
        return coApplicantCertificateNumber;
    }

    public void setCoApplicantCertificateNumber(String coApplicantCertificateNumber) {
        this.coApplicantCertificateNumber = coApplicantCertificateNumber;
    }

    public String getCoApplicantMobileNumber() {
        return coApplicantMobileNumber;
    }

    public void setCoApplicantMobileNumber(String coApplicantMobileNumber) {
        this.coApplicantMobileNumber = coApplicantMobileNumber;
    }

    public String getCoApplicantQualification() {
        return coApplicantQualification;
    }

    public void setCoApplicantQualification(String coApplicantQualification) {
        this.coApplicantQualification = coApplicantQualification;
    }

    public String getCoApplicantAddress() {
        return coApplicantAddress;
    }

    public void setCoApplicantAddress(String coApplicantAddress) {
        this.coApplicantAddress = coApplicantAddress;
    }

    public Long getCoApplicantIncomePerMonth() {
        return coApplicantIncomePerMonth;
    }

    public void setCoApplicantIncomePerMonth(Long coApplicantIncomePerMonth) {
        this.coApplicantIncomePerMonth = coApplicantIncomePerMonth;
    }

    public String getCoApplicantTelephone() {
        return coApplicantTelephone;
    }

    public void setCoApplicantTelephone(String coApplicantTelephone) {
        this.coApplicantTelephone = coApplicantTelephone;
    }

    public String getCoApplicantEmployerName() {
        return coApplicantEmployerName;
    }

    public void setCoApplicantEmployerName(String coApplicantEmployerName) {
        this.coApplicantEmployerName = coApplicantEmployerName;
    }

    public String getCoApplicantEmployerTelephone() {
        return coApplicantEmployerTelephone;
    }

    public void setCoApplicantEmployerTelephone(String coApplicantEmployerTelephone) {
        this.coApplicantEmployerTelephone = coApplicantEmployerTelephone;
    }

    public String getCoApplicantEmployerAddress() {
        return coApplicantEmployerAddress;
    }

    public void setCoApplicantEmployerAddress(String coApplicantEmployerAddress) {
        this.coApplicantEmployerAddress = coApplicantEmployerAddress;
    }

    public String getCoApplicantCertificateFileIds() {
        return coApplicantCertificateFileIds;
    }

    public void setCoApplicantCertificateFileIds(String coApplicantCertificateFileIds) {
        this.coApplicantCertificateFileIds = coApplicantCertificateFileIds;
    }

    public String getCoApplicantIncomeFileIds() {
        return coApplicantIncomeFileIds;
    }

    public void setCoApplicantIncomeFileIds(String coApplicantIncomeFileIds) {
        this.coApplicantIncomeFileIds = coApplicantIncomeFileIds;
    }

    public String getCoApplicantEstateFileIds() {
        return coApplicantEstateFileIds;
    }

    public void setCoApplicantEstateFileIds(String coApplicantEstateFileIds) {
        this.coApplicantEstateFileIds = coApplicantEstateFileIds;
    }

    public String getCoApplicantOtherFileIds() {
        return coApplicantOtherFileIds;
    }

    public void setCoApplicantOtherFileIds(String coApplicantOtherFileIds) {
        this.coApplicantOtherFileIds = coApplicantOtherFileIds;
    }

    public String getGuarantorName() {
        return guarantorName;
    }

    public void setGuarantorName(String guarantorName) {
        this.guarantorName = guarantorName;
    }

    public Long getGuarantorCensusCityId() {
        return guarantorCensusCityId;
    }

    public void setGuarantorCensusCityId(Long guarantorCensusCityId) {
        this.guarantorCensusCityId = guarantorCensusCityId;
    }

    public String getGuarantorRelationship() {
        return guarantorRelationship;
    }

    public void setGuarantorRelationship(String guarantorRelationship) {
        this.guarantorRelationship = guarantorRelationship;
    }

    public String getGuarantorCertificateNumber() {
        return guarantorCertificateNumber;
    }

    public void setGuarantorCertificateNumber(String guarantorCertificateNumber) {
        this.guarantorCertificateNumber = guarantorCertificateNumber;
    }

    public String getGuarantorRealEstateOwnType() {
        return guarantorRealEstateOwnType;
    }

    public void setGuarantorRealEstateOwnType(String guarantorRealEstateOwnType) {
        this.guarantorRealEstateOwnType = guarantorRealEstateOwnType;
    }

    public Long getGuarantorIncomePerMonth() {
        return guarantorIncomePerMonth;
    }

    public void setGuarantorIncomePerMonth(Long guarantorIncomePerMonth) {
        this.guarantorIncomePerMonth = guarantorIncomePerMonth;
    }

    public String getGuarantorMobileNumber() {
        return guarantorMobileNumber;
    }

    public void setGuarantorMobileNumber(String guarantorMobileNumber) {
        this.guarantorMobileNumber = guarantorMobileNumber;
    }

    public String getGuarantorCertificateFileIds() {
        return guarantorCertificateFileIds;
    }

    public void setGuarantorCertificateFileIds(String guarantorCertificateFileIds) {
        this.guarantorCertificateFileIds = guarantorCertificateFileIds;
    }

    public String getGuarantorIncomeFileIds() {
        return guarantorIncomeFileIds;
    }

    public void setGuarantorIncomeFileIds(String guarantorIncomeFileIds) {
        this.guarantorIncomeFileIds = guarantorIncomeFileIds;
    }

    public String getGuarantorEstateFileIds() {
        return guarantorEstateFileIds;
    }

    public void setGuarantorEstateFileIds(String guarantorEstateFileIds) {
        this.guarantorEstateFileIds = guarantorEstateFileIds;
    }

    public String getGuarantorOtherFileIds() {
        return guarantorOtherFileIds;
    }

    public void setGuarantorOtherFileIds(String guarantorOtherFileIds) {
        this.guarantorOtherFileIds = guarantorOtherFileIds;
    }

    public String getVehicleVin() {
        return vehicleVin;
    }

    public void setVehicleVin(String vehicleVin) {
        this.vehicleVin = vehicleVin;
    }

    public String getVehicleManufacturers() {
        return vehicleManufacturers;
    }

    public void setVehicleManufacturers(String vehicleManufacturers) {
        this.vehicleManufacturers = vehicleManufacturers;
    }

    public Long getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(Long vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public Long getVehicleSeries() {
        return vehicleSeries;
    }

    public void setVehicleSeries(Long vehicleSeries) {
        this.vehicleSeries = vehicleSeries;
    }

    public Long getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Long vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleDesc() {
        return vehicleDesc;
    }

    public void setVehicleDesc(String vehicleDesc) {
        this.vehicleDesc = vehicleDesc;
    }

    public String getVehicleProductionYearMonth() {
        return vehicleProductionYearMonth;
    }

    public void setVehicleProductionYearMonth(String vehicleProductionYearMonth) {
        this.vehicleProductionYearMonth = vehicleProductionYearMonth;
    }

    public String getVehicleRegistrationYearMonth() {
        return vehicleRegistrationYearMonth;
    }

    public void setVehicleRegistrationYearMonth(String vehicleRegistrationYearMonth) {
        this.vehicleRegistrationYearMonth = vehicleRegistrationYearMonth;
    }

    public Float getVehicleKilometers() {
        return vehicleKilometers;
    }

    public void setVehicleKilometers(Float vehicleKilometers) {
        this.vehicleKilometers = vehicleKilometers;
    }

    public String getVehicleUtilityType() {
        return vehicleUtilityType;
    }

    public void setVehicleUtilityType(String vehicleUtilityType) {
        this.vehicleUtilityType = vehicleUtilityType;
    }

    public String getVehicleEmission() {
        return vehicleEmission;
    }

    public void setVehicleEmission(String vehicleEmission) {
        this.vehicleEmission = vehicleEmission;
    }

    public String getVehicleRegistrationCertificateFileIds() {
        return vehicleRegistrationCertificateFileIds;
    }

    public void setVehicleRegistrationCertificateFileIds(String vehicleRegistrationCertificateFileIds) {
        this.vehicleRegistrationCertificateFileIds = vehicleRegistrationCertificateFileIds;
    }

    public String getVehicleLicenseFileIds() {
        return vehicleLicenseFileIds;
    }

    public void setVehicleLicenseFileIds(String vehicleLicenseFileIds) {
        this.vehicleLicenseFileIds = vehicleLicenseFileIds;
    }

    public String getVehicleFileIds() {
        return vehicleFileIds;
    }

    public void setVehicleFileIds(String vehicleFileIds) {
        this.vehicleFileIds = vehicleFileIds;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Long getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    public String getReviewRemark() {
        return reviewRemark;
    }

    public void setReviewRemark(String reviewRemark) {
        this.reviewRemark = reviewRemark;
    }

    public String getMaterialsRemark() {
        return materialsRemark;
    }

    public void setMaterialsRemark(String materialsRemark) {
        this.materialsRemark = materialsRemark;
    }

    public String getMaterialsFileIds() {
        return materialsFileIds;
    }

    public void setMaterialsFileIds(String materialsFileIds) {
        this.materialsFileIds = materialsFileIds;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Long getContractCreatedTime() {
        return contractCreatedTime;
    }

    public void setContractCreatedTime(Long contractCreatedTime) {
        this.contractCreatedTime = contractCreatedTime;
    }

    public String getContractFileIds() {
        return contractFileIds;
    }

    public void setContractFileIds(String contractFileIds) {
        this.contractFileIds = contractFileIds;
    }

    public Long getTransferCreatedTime() {
        return transferCreatedTime;
    }

    public void setTransferCreatedTime(Long transferCreatedTime) {
        this.transferCreatedTime = transferCreatedTime;
    }

    public String getTransferCardNumber() {
        return transferCardNumber;
    }

    public void setTransferCardNumber(String transferCardNumber) {
        this.transferCardNumber = transferCardNumber;
    }

    public String getTransferFileIds() {
        return transferFileIds;
    }

    public void setTransferFileIds(String transferFileIds) {
        this.transferFileIds = transferFileIds;
    }

    public String getTransferGPSFileIds() {
        return transferGPSFileIds;
    }

    public void setTransferGPSFileIds(String transferGPSFileIds) {
        this.transferGPSFileIds = transferGPSFileIds;
    }

    public String getTransferInsuranceFileIds() {
        return transferInsuranceFileIds;
    }

    public void setTransferInsuranceFileIds(String transferInsuranceFileIds) {
        this.transferInsuranceFileIds = transferInsuranceFileIds;
    }

    public Integer getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(Integer releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

    public Long getReleaseCreatedTime() {
        return releaseCreatedTime;
    }

    public void setReleaseCreatedTime(Long releaseCreatedTime) {
        this.releaseCreatedTime = releaseCreatedTime;
    }

    public String getReleaseFileIds() {
        return releaseFileIds;
    }

    public void setReleaseFileIds(String releaseFileIds) {
        this.releaseFileIds = releaseFileIds;
    }

    public Long getMortgageCreatedTime() {
        return mortgageCreatedTime;
    }

    public void setMortgageCreatedTime(Long mortgageCreatedTime) {
        this.mortgageCreatedTime = mortgageCreatedTime;
    }

    public String getMortgageFileIds() {
        return mortgageFileIds;
    }

    public void setMortgageFileIds(String mortgageFileIds) {
        this.mortgageFileIds = mortgageFileIds;
    }

    public Integer getProductLoanPolicy() {
        return productLoanPolicy;
    }

    public void setProductLoanPolicy(Integer productLoanPolicy) {
        this.productLoanPolicy = productLoanPolicy;
    }
}