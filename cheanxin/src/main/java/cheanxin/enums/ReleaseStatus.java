package cheanxin.enums;

/**
 * 贷款放款状态
 * Created by Jawinton on 17/04/27.
 */
public enum ReleaseStatus {
    RELEASE_UNAVAILABLE(0, "未达到放款状态"),
    TRANSFER_RELEASE_PENDING(1, "过户待放款"),
    MORTGAGE_RELEASE_PENDING(2, "抵押待放款"),
    TRANSFER_RELEASED(3, "过户已放款"),
    MORTGAGE_RELEASED(4, "抵押已放款");

    private final Integer value;
    private final String desc;

    ReleaseStatus(Integer value, String desc) {
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
        ReleaseStatus releaseStatuses[] = ReleaseStatus.values();
        int values[] = new int[releaseStatuses.length];
        for (int i = 0; i < releaseStatuses.length; i++) {
            values[i] = releaseStatuses[i].value();
        }
        return values;
    }

    public static boolean contains(int value) {
        return ReleaseStatus.valueOf(value) != null;
    }
    
    public static ReleaseStatus valueOf(int value) {
        ReleaseStatus releaseStatuses[] = ReleaseStatus.values();
        for (ReleaseStatus releaseStatus : releaseStatuses) {
            if (releaseStatus.value() == value)
                return releaseStatus;
        }
        return null;
    }
}
