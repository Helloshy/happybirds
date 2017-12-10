package cheanxin.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Jawinton on 16/12/30.
 * 产品操作日志
 */
@Entity
@Table(indexes = {@Index(name = "idx_product_id", columnList = "productId"), @Index(name = "idx_operator_username", columnList = "operatorUsername")})
public class ProductLog {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '自增id'")
    // 自增id
    private Long id;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '产品id'")
    // 产品id
    private Long productId;

    @NotNull
    @Column(columnDefinition = "VARCHAR(20) COMMENT '操作人username'")
    private String operatorUsername;

    @NotNull
    @Min(0)
    @Max(8)
    @Column(columnDefinition = "TINYINT(1) UNSIGNED COMMENT '操作类型'")
    // 操作类型
    private Integer operatorType;

    @Size(max = 255)
    @Column(columnDefinition = "VARCHAR(255) COMMENT '备注'")
    // 备注
    private String remark;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    // 创建时间
    private Long createdTime;

    public ProductLog() {}

    public ProductLog(Long productId, String operatorUsername, Integer operatorType, String remark, Long createdTime) {
        this.productId = productId;
        this.operatorUsername = operatorUsername;
        this.operatorType = operatorType;
        this.remark = remark;
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "ProductLog{" +
                "id=" + id +
                ", productId=" + productId +
                ", operatorUsername='" + operatorUsername + '\'' +
                ", operatorType=" + operatorType +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getOperatorUsername() {
        return operatorUsername;
    }

    public void setOperatorUsername(String operatorUsername) {
        this.operatorUsername = operatorUsername;
    }

    public Integer getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
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
