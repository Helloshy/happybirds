package cheanxin.constant;

import cheanxin.domain.OAuth2Client;

/**
 * 配置常量
 * Created by Jawinton on 16/12/24.
 */
public class ConfigConstants {
    // password secret
    public static final String PASSWORD_SECRET = "7EsF+0BCtNRW1hLtf39QLDQq5G+4Eh1/euW4azW7Qux";

    // security constants
    public static final String REALM = "cheanxin";
    public static final String RESOURCE_ID = "cheanxin_rest_resource";

    // authenticated clients
    public static final OAuth2Client[] O_AUTH2_CLIENTS = {
        new OAuth2Client(
                "cheanxin_client",
                "ampnvaUQrmQj7r9a6f94ltjCuzqY7jcvX",
                new String[]{"password", "refresh_token"},
                new String[]{"CLIENT"},
                new String[]{"read", "write"},
                7200,
                86400 * 30)
    };
}
