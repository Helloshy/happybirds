package cheanxin.image;

import cheanxin.Env;
import org.apache.log4j.Logger;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by 向麒 on 2017/3/15.
 */
public class FileManager implements FileManagerConfig {
    private static final long serialVersionUID = 1L;

    private static Logger logger  = Logger.getLogger(FileManager.class);

    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;
    private static StorageServer  storageServer;
    private static StorageClient  storageClient;


    static { // Initialize Fast DFS Client configurations

        try {
            String classPath = new File(FileManager.class.getResource("/").getFile()).getCanonicalPath();

            // TODO: 2017/4/19
            String fdfsClientConfigFilePath = classPath + File.separator + Env.ENV + File.separator + CLIENT_CONFIG_FILE;

            logger.info("Fast DFS configuration file path:" + fdfsClientConfigFilePath);
            ClientGlobal.init(fdfsClientConfigFilePath);

            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();

            storageClient = new StorageClient(trackerServer, storageServer);

        } catch (Exception e) {
//            LoggerUtils.error(logger,  e);

        }
    }



    public static String upload(FastDFSFile file) throws IOException, MyException {
        NameValuePair[] meta_list = new NameValuePair[3];
        meta_list[0] = new NameValuePair("width", "120");
        meta_list[1] = new NameValuePair("heigth", "120");
        meta_list[2] = new NameValuePair("author", "xiangqi");

        String[] uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);

        if (uploadResults == null) {
            throw new InternalError("File upload failed.");
        }

        String groupName 		= uploadResults[0];
        String remoteFileName   = uploadResults[1];

        String fileAbsolutePath = groupName + SEPARATOR + remoteFileName;

        return fileAbsolutePath;

    }

    public static FileInfo getFile(String groupName, String remoteFileName) {
        try {
            return storageClient.get_file_info(groupName, remoteFileName);
        } catch (IOException e) {
            logger.error("IO Exception: Get File from Fast DFS failed", e);
        } catch (Exception e) {
            logger.error("Non IO Exception: Get File from Fast DFS failed", e);
        }
        return null;
    }

    public static void deleteFile(String groupName, String remoteFileName) throws Exception {
        storageClient.delete_file(groupName, remoteFileName);
    }

    public static StorageServer[] getStoreStorages(String groupName) throws IOException {
        return trackerClient.getStoreStorages(trackerServer, groupName);
    }

    public static ServerInfo[] getFetchStorages(String groupName, String remoteFileName) throws IOException {
        return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
    }
}
