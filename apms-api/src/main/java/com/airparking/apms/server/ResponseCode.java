package com.airparking.apms.server;

/**
 * Created by ryan on 12/25/15.
 */
public enum ResponseCode {
    SUCCESS(200, "success"),
    NO_CHANGE(304, "no any changes"),

    REQUEST_ERROR(400, "request error"),

    NOT_AUTHORIZED(401, "not authorized"),
    LOGIN_FAILED(4011, "登录失败!"),
    NOT_SUPPORT_VERSION(4012, "not support this version"),
    LOGIN_EXPIRED(4014, "登陆过期！"),
    ERROR_ACCOUNT(4017, "用户名或者密码错误！"),

    MISS_REQUIRED(402, "miss required parameters"),
    MISS_APPID(4021, "miss appId"),
    MISS_SIGN(4022, "miss request sign"),
    MISS_TIMESTAMP(4023, "miss request timestamp"),

    SIGN_FAILED(403, "invalid sign"),

    SERVER_ERROR(500, "internal server error"),
    
    UPDATE_ORDER_ERROR(600, "updating order data error"),
    ;
    private int value;
    private String desc;

    ResponseCode(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
