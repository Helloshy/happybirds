package com.airparking.apms.server.handler;

import org.apache.commons.lang3.StringUtils;

import com.airparking.apms.server.ResponseCode;
import com.airparking.apms.server.ServiceException;
import com.airparking.apms.server.ServiceRequest;


/**
 * Created by ryan on 1/8/16.
 */
public class AbstractController {

    public boolean versionSupport(String appVersion) {
        return true;
    }

//    public boolean isAuthorized(String token) throws Exception {
//        if (StringUtils.isEmpty(token)) {
//            throw new ServiceException("miss token!", ResponseCode.MISS_REQUIRED);
//        }
//        return true;
//    }

    public String validRequiredParam(ServiceRequest request, String[] fields) {
        if (fields == null)
            return null;

        for (String field : fields) {
            if (!request.containKey(field) || StringUtils.isEmpty(request.getString(field)))
                return "Miss required parameter " + field;
        }

        return null;
    }
}
