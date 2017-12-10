package com.airparking.apms.comm;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by ryan on 1/12/16.
 */
public class GeneratorUtils {
    public static String generateUserName(String mobile) {
        StringBuffer userName = new StringBuffer();

        userName.append("ap");
        userName.append(mobile.substring(3));
        userName.append("_");
        userName.append(RandomStringUtils.randomNumeric(2));

        return userName.toString();
    }
}
