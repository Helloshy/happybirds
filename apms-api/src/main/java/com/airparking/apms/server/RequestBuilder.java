package com.airparking.apms.server;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import org.springframework.util.StringUtils;

import com.airparking.core.anno.Path;
import com.airparking.core.anno.Required;
import com.airparking.apms.server.handler.AbstractController;
import com.airparking.apms.server.spring.SpringContext;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

/**
 * Created by ryan on 12/31/15.
 */
public class RequestBuilder {
    public static Cache cache = CacheBuilder.newBuilder().maximumSize(512).build();

    private Map<String, Object> parameters;

    private String requestUri;

    public RequestBuilder() {
    }

    public RequestBuilder(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public static RequestBuilder getInstance() {
        return new RequestBuilder();
    }

    public static RequestBuilder getInstance(Map<String, Object> parameters) {
        return new RequestBuilder(parameters);
    }

    public RequestBuilder addParameters(Map<String, Object> params) {
        this.parameters = params;
        return this;
    }

    public RequestBuilder addRequestUri(String requestUri) {
        this.requestUri = requestUri;
        return this;
    }

    /**
     * build request and verify required fields
     * @return
     * @throws ServiceException
     */
    public ServiceRequest build() throws ServiceException {
        ServiceRequest serviceRequest = new ServiceRequest();

        serviceRequest.setVersion(this.getString("version"));
        serviceRequest.setAppVersion(this.getString("appVersion"));
        serviceRequest.setUserLat(this.getDouble("userLat"));
        serviceRequest.setUserLng(this.getDouble("userLng"));
        serviceRequest.setToken(this.getString("token"));
        serviceRequest.setSign(this.getString("sign"));
        serviceRequest.setTimestamp(this.getLong("timestamp"));
        serviceRequest.setDeviceModel(this.getString("deviceModel"));
        serviceRequest.setScreen(this.getString("screen"));
        serviceRequest.setDeviceId(this.getString("deviceId"));
        serviceRequest.setDeviceIP(this.getString("deviceIP"));
        serviceRequest.setAppId(this.getString("appId"));
        serviceRequest.setMobileSystem(this.getString("mobileSystem"));
        serviceRequest.setClientType(ServiceRequest.ClientType.getClientType(this.getInt("clientType", 0)));
        serviceRequest.setNetworkType(ServiceRequest.NetworkType.getNetworkTypes(this.getInt("networkType", 0)));

        RequestPath path = RequestPath.fromUrl(requestUri);

        RequestMapping requestMapping = new RequestMapping();
        AbstractController service = (AbstractController) SpringContext.getContext().getBean(path.getService());
        if (service == null) {
            throw new ServiceException(ResponseCode.REQUEST_ERROR);
        }
        requestMapping.setService(service);
        Method method = getMethod(path.getInterfaceMapping(), path.getService(), service);
        requestMapping.setMethod(method);

        serviceRequest.setRequestMapping(requestMapping);
        serviceRequest.setParameters(parameters);

        String verify = verify(serviceRequest);
        if (verify != null)
            throw new ServiceException(verify, ResponseCode.MISS_REQUIRED);

        return serviceRequest;
    }

    private Method getMethod(final String path, final String serviceName, final AbstractController service) {
        try {
            String key = serviceName + "/" +path;

            final Method method = (Method) cache.get(key, new Callable() {
                @Override
                public Object call() throws Exception {
                    try {
                        Method method1 = service.getClass().getMethod(path, ServiceRequest.class);
                        if (method1 != null)
                            return method1;
                    } catch (NoSuchMethodException e) {
                    }

                    Class cls = service.getClass();
                    Method[] methods = cls.getMethods();
                    for (Method m : methods) {
                        Path anno = m.getAnnotation(Path.class);
                        if (anno != null && path.equals(anno.value())) {
                            return m;
                        }
                    }

                    return null;
                }
            });

            return method;
        } catch (ExecutionException e) {
            throw new ServiceException(e);
        } catch (CacheLoader.InvalidCacheLoadException ie) {
            throw new ServiceException("The resources are not existed.", ResponseCode.REQUEST_ERROR);
        }
    }

    private String verify(ServiceRequest serviceRequest) throws ServiceException {
        Field[] fields = serviceRequest.getClass().getDeclaredFields();

        for (Field field : fields) {
            Required anno = field.getAnnotation(Required.class);
            if (anno != null) {
                field.setAccessible(true);
                Object value = null;
                try {
                    value = field.get(serviceRequest);
                } catch (IllegalAccessException e) {
                    throw new ServiceException(e.getMessage(), ResponseCode.SERVER_ERROR);
                }
                if (value == null) {
                    return field.getName();
                }
            }
        }
        return null;
    }



    public Integer getInt(String key) throws NumberFormatException {
        return parameters.containsKey(key) ? StringUtils.isEmpty(parameters.get(key)) ? null : Integer.parseInt(parameters.get(key).toString()) : null;
    }

    public Integer getInt(String key, int defaultValue) throws NumberFormatException {
        return parameters.containsKey(key) ? StringUtils.isEmpty(parameters.get(key)) ? defaultValue : Integer.parseInt(parameters.get(key).toString()) : defaultValue;
    }

    public Long getLong(String key) throws NumberFormatException {
        return parameters.containsKey(key) ? StringUtils.isEmpty(parameters.get(key)) ? null : Long.parseLong(parameters.get(key).toString()) : null;
    }

    public Long getLong(String key, long defaultValue) throws NumberFormatException {
        return parameters.containsKey(key) ? StringUtils.isEmpty(parameters.get(key)) ? defaultValue : Long.parseLong(parameters.get(key).toString()) : defaultValue;
    }

    public Double getDouble(String key) throws NumberFormatException {
        return parameters.containsKey(key) ? StringUtils.isEmpty(parameters.get(key)) ? null : Double.parseDouble(parameters.get(key).toString()) : null;
    }

    public Double getDouble(String key, double defaultValue) throws NumberFormatException {
        return parameters.containsKey(key) ? StringUtils.isEmpty(parameters.get(key)) ? defaultValue : Double.parseDouble(parameters.get(key).toString()) : defaultValue;
    }

    public String getString(String key) {
        return parameters.containsKey(key) ? StringUtils.isEmpty(parameters.get(key)) ? null : parameters.get(key).toString() : null;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}
