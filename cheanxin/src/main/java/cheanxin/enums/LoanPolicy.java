package cheanxin.enums;

/**
 * Created by Jawinton on 17/1/3.
 */
public enum LoanPolicy {
    AFTER_TRANSFER(0, "过户后放款"),
    AFTER_MORTGAGE(1, "抵押后放款");

    private final Integer value;
    private final String desc;

    LoanPolicy(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer value() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    public static LoanPolicy get(Integer value) {
        if (value == null) {
            return null;
        }
        for (LoanPolicy loanPolicy : LoanPolicy.values()) {
            if (loanPolicy.value() == value) {
                return loanPolicy;
            }
        }
        return null;
    }

    public static boolean contains(int value) {
        LoanPolicy loanPolicies[] = LoanPolicy.values();
        for (LoanPolicy loanPolicy : loanPolicies) {
            if (loanPolicy.value() == value)
                return true;
        }
        return false;
    }
}
