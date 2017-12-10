package cheanxin.enums;

/**
 * 产品类型
 * Created by Jawinton on 17/1/3.
 */
public enum ProductType {
    NEW_CAR_LOAN(0, "新车贷款"),
    USED_CAR_LOAN(1, "二手车贷款");

    private final Integer value;
    private final String desc;

    private ProductType(Integer value, String desc) {
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
        ProductType productTypes[] = ProductType.values();
        int values[] = new int[productTypes.length];
        for (int i = 0; i < productTypes.length; i++) {
            values[i] = productTypes[i].value();
        }
        return values;
    }

    public static boolean contains(int value) {
        ProductType productTypes[] = ProductType.values();
        for (ProductType productType : productTypes) {
            if (productType.value() == value)
                return true;
        }
        return false;
    }

    public static ProductType valueOf(int value) {
        ProductType productTypes[] = ProductType.values();
        for (ProductType productType : productTypes) {
            if (productType.value() == value)
                return productType;
        }
        return null;
    }
}
