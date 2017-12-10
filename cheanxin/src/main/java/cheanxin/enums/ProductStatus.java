package cheanxin.enums;

/**
 * 产品状态
 * Created by Jawinton on 17/1/3.
 */
public enum ProductStatus {
    PENDING_REVIEW(0, "待审核"),
    ACCEPTED(1, "审核通过"),
    REJECTED(2, "审核拒绝");

    private final Integer value;
    private final String desc;

    ProductStatus(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer value() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    public static int[] getAllValues() {
        ProductStatus productStatuses[] = ProductStatus.values();
        int values[] = new int[productStatuses.length];
        for (int i = 0; i < productStatuses.length; i++) {
            values[i] = productStatuses[i].value();
        }
        return values;
    }

    public static boolean contains(int value) {
        return ProductStatus.valueOf(value) != null;
    }
    
    public static ProductStatus valueOf(int value) {
        ProductStatus productStatuses[] = ProductStatus.values();
        for (ProductStatus productStatus : productStatuses) {
            if (productStatus.value() == value)
                return productStatus;
        }
        return null;
    }
}
