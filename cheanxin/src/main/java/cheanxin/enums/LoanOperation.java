package cheanxin.enums;

/**
 * 贷款操作类型
 * Created by Jawinton on 17/1/3.
 */
public enum LoanOperation {
    /**
     * 更新
     */
    UPDATE(1, "update"),
    /**
     * 提交
     */
    SUBMIT(2, "submit"),
    /**
     * 通过
     */
    ACCEPT(3, "accept"),
    /**
     * 拒绝
     */
    REJECT(4, "reject"),
    /**
     * 放弃
     */
    ABORT(5, "abort");

    private final Integer value;
    private final String methodName;

    LoanOperation(Integer value, String desc) {
        this.value = value;
        this.methodName = desc;
    }

    public Integer value() {
        return this.value;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public static boolean contains(int value) {
        return valueOf(value) != null;
    }

    public static LoanOperation valueOf(int value) {
        LoanOperation loanOperations[] = LoanOperation.values();
        for (LoanOperation loanOperation : loanOperations) {
            if (loanOperation.value() == value)
                return loanOperation;
        }
        return null;
    }
}
