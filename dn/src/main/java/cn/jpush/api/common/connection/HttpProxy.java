package cn.jpush.api.common.connection;

import java.net.InetSocketAddress;
import java.net.Proxy;

import cn.jpush.api.common.ServiceHelper;
import cn.jpush.api.utils.Preconditions;

public class HttpProxy {

    private String host;
    private int port;
    private String username;
    private String password;
    
    private boolean authenticationNeeded = false;
    
    public HttpProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    public HttpProxy(String host, int port, String username, String password) {
        this(host, port);
        
        Preconditions.checkArgument(! (null == username), "username should not be null");
        Preconditions.checkArgument(! (null == password), "password should not be null");
        
        this.username = username;
        this.password = password;
        authenticationNeeded = true;
        
    }
    
    
    public Proxy getNetProxy() {
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
    }
    
    public boolean isAuthenticationNeeded() {
        return this.authenticationNeeded;
    }
    
    public String getProxyAuthorization() {
        return ServiceHelper.getBasicAuthorization(username, password);
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
}