package com.airparking.apms.server;

import com.airparking.core.comm.utils.JsonUtils;
import com.google.protobuf.GeneratedMessage;

import java.io.IOException;

/**
 * Created by ryan on 12/25/15.
 */
public class ServiceResponse {
    private Integer code;
    private String desc;
    private Object result;
    private long timestamp = System.currentTimeMillis();

    public ServiceResponse(ResponseCode responseCode) {
        this.code = responseCode.getValue();
        this.desc = responseCode.getDesc();
    }

    public ServiceResponse(ResponseCode responseCode, String desc) {
        this.code = responseCode.getValue();
        this.desc = desc;
    }

    public ServiceResponse(ResponseCode responseCode, Object result) {
        this.code = responseCode.getValue();
        this.desc = responseCode.getDesc();
        this.result = result;
    }

    public ServiceResponse(Exception cause) {
        this.code = ResponseCode.SERVER_ERROR.getValue();
        this.desc = ResponseCode.SERVER_ERROR.getDesc();
        this.result = cause.getMessage();
    }

    public ServiceResponse(Object result) {
        this.code = ResponseCode.SUCCESS.getValue();
        this.desc = ResponseCode.SUCCESS.getDesc();
        this.result = result;
    }

    public ServiceResponse(ResponseCode responseCode, ServiceException cause) {
        this.code = responseCode.getValue();
        this.desc = responseCode.getDesc();
        this.result = cause.getMessage();
    }

    public ServiceResponse(Integer code, String desc, Object result) {
        this.code = code;
        this.desc = desc;
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String toJson() throws IOException {
        try {
            if (this.result instanceof GeneratedMessage) {
                result = JsonUtils.toObject(JsonUtils.jsonProto((GeneratedMessage) result), result.getClass());
            }
            return JsonUtils.toJson(this);
        } catch (IOException e) {
            throw e;
        }
    }
}
