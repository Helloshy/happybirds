package com.airparking.apms.server;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.airparking.core.anno.Required;
import com.airparking.core.comm.utils.DateHelper;

/**
 * Created by ryan on 12/25/15.
 */
public class ServiceRequest {
    private String version;
    private String appVersion;
    private Double userLng;
    private Double userLat;
    private String token;
    @Required
    private String sign;
    @Required
    private Long timestamp;
    private String deviceModel;
    private String screen;
    private String deviceId;
    private String deviceIP;
    @Required
    private String appId;
    private String mobileSystem;
    private ClientType clientType;
    private NetworkType networkType;
    private Map<String, Object> parameters;

    private RequestPath path;
    private RequestMapping requestMapping;

    public enum ClientType {
        UNKNOWN(0, "unknown"),
        ANDROID(1, "android"),
        IOS(2, "ios"),
        WP(3, "wp"),
        HTML(4, "html");
        private int value;
        private String desc;

        ClientType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }

        public static ClientType getClientType(int value) {
            ClientType[] clientTypes = ClientType.values();
            for (ClientType type : clientTypes) {
                if (type.getValue() == value)
                    return type;
            }

            return null;
        }
    }

    public enum NetworkType {
        UNKNOWN(0, "unknown"),
        WIFI(1, "wifi"),
        GSM(2, "2g"),
        CDMA(3, "3g"),
        LTE(4, "4g");
        private int value;
        private String desc;

        NetworkType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }

        public static NetworkType getNetworkTypes(int value) {
            NetworkType[] networkTypes = NetworkType.values();
            for (NetworkType type : networkTypes) {
                if (type.getValue() == value)
                    return type;
            }

            return null;
        }
    }

    public ServiceRequest() {
    }

    public ServiceRequest(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Double getUserLng() {
        return userLng;
    }

    public void setUserLng(Double userLng) {
        this.userLng = userLng;
    }

    public Double getUserLat() {
        return userLat;
    }

    public void setUserLat(Double userLat) {
        this.userLat = userLat;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceIP() {
        return deviceIP;
    }

    public void setDeviceIP(String deviceIP) {
        this.deviceIP = deviceIP;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMobileSystem() {
        return mobileSystem;
    }

    public void setMobileSystem(String mobileSystem) {
        this.mobileSystem = mobileSystem;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public NetworkType getNetworkType() {
        return networkType;
    }

    public void setNetworkType(NetworkType networkType) {
        this.networkType = networkType;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public RequestPath getPath() {
        return path;
    }

    public void setPath(RequestPath path) {
        this.path = path;
    }

    public RequestMapping getRequestMapping() {
        return requestMapping;
    }

    public void setRequestMapping(RequestMapping requestMapping) {
        this.requestMapping = requestMapping;
    }

    public Integer getInt(String key) throws NumberFormatException {
        return parameters.containsKey(key) ? StringUtils.isEmpty(parameters.get(key)) ? null : Integer.parseInt(parameters.get(key).toString()) : null;
    }

    public Long getLong(String key) throws NumberFormatException {
        return parameters.containsKey(key) ? StringUtils.isEmpty(parameters.get(key)) ? null : Long.parseLong(parameters.get(key).toString()) : null;
    }

    public Double getDouble(String key) throws NumberFormatException {
        return parameters.containsKey(key) ? StringUtils.isEmpty(parameters.get(key)) ? null : Double.parseDouble(parameters.get(key).toString()) : null;
    }

    public String getString(String key) {
        return parameters.containsKey(key) ? StringUtils.isEmpty(parameters.get(key)) ? null : parameters.get(key).toString() : null;
    }

    public Date getDate(String key) throws ParseException {
        return parameters.containsKey(key) ? StringUtils.isEmpty(parameters.get(key)) ? null : DateHelper.parseDateTime(parameters.get(key).toString()) : null;
    }

    public boolean containKey(String key) {
        return parameters.containsKey(key);
    }
}
