package cheanxin.enums;

/**
 * 还款方式
 * Created by Jawinton on 17/1/3.
 */
public enum RepaymentMethod {
    EQUALITY_CORPUS_AND_INTEREST(0, "等额本息");

    private final Integer value;
    private final String desc;

    RepaymentMethod(Integer value, String desc) {
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
        RepaymentMethod repaymentMethods[] = RepaymentMethod.values();
        int values[] = new int[repaymentMethods.length];
        for (int i = 0; i < repaymentMethods.length; i++) {
            values[i] = repaymentMethods[i].value();
        }
        return values;
    }

    public static boolean contains(int value) {
        RepaymentMethod repaymentMethods[] = RepaymentMethod.values();
        for (RepaymentMethod repaymentMethod : repaymentMethods) {
            if (repaymentMethod.value() == value)
                return true;
        }
        return false;
    }
}
