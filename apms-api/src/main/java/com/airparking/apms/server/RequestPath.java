package com.airparking.apms.server;

/**
 * Created by ryan on 12/31/15.
 */
public class RequestPath {

    private String project;

    private String service;

    private String interfaceMapping;

    private String pattern;

    public String getInterfaceMapping() {
        return interfaceMapping;
    }

    public void setInterfaceMapping(String interfaceMapping) {
        this.interfaceMapping = interfaceMapping;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public static RequestPath fromUrl(String uri) throws ServiceException {
        if (uri != null) {
            if (uri.contains("?"))
                uri = uri.substring(0, uri.indexOf("?"));
            String[] components = uri.split("/");

            int length = components.length;

            RequestPath path = new RequestPath();
            try {
                path.setProject(components[1]);
                path.setService(components[2]);

                StringBuilder interfaceMapping = new StringBuilder(components[3]);
                for (int i = 4; i < length; i++) {
                    interfaceMapping.append("/");
                    interfaceMapping.append(components[i]);
                }
                path.setInterfaceMapping(interfaceMapping.toString());
                return path;
            } catch (Exception e) {
                throw new ServiceException("The resources are not existed.", ResponseCode.REQUEST_ERROR);
            }
        } else {
            throw new ServiceException("Invalid request. Please check your url.");
        }
    }
}
