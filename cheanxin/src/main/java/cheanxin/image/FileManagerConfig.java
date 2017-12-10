package cheanxin.image;

import java.io.Serializable;

/**
 * Created by 向麒 on 2017/3/15.
 */
public interface FileManagerConfig  extends Serializable {
    public static final String FILE_DEFAULT_WIDTH 	= "120";
    public static final String FILE_DEFAULT_HEIGHT 	= "120";
    public static final String FILE_DEFAULT_AUTHOR 	= "xiangqi";

    public static final String PROTOCOL = "http://";
    public static final String SEPARATOR = "/";

    public static final String TRACKER_NGNIX_PORT 	= "8888";

    public static final String CLIENT_CONFIG_FILE   = "fdfs_client.properties";
}
