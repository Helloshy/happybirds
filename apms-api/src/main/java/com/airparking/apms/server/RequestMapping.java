package com.airparking.apms.server;

import com.airparking.apms.server.handler.AbstractController;

import java.lang.reflect.Method;

/**
 * Created by ryan on 2/17/16.
 */
public class RequestMapping {
    private AbstractController service;
    private Method method;

    public AbstractController getService() {
        return service;
    }

    public void setService(AbstractController service) {
        this.service = service;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

}
