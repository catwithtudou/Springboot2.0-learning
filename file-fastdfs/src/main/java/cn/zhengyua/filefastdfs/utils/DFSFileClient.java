package cn.zhengyua.filefastdfs.utils;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import javax.sound.midi.Track;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * DFSFile客户端封装类
 *
 * @author tudou
 * @date 2020/1/69:58
 */


public class DFSFileClient {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(DFSFileClient.class);

    /*
     * 读取配置文件
     */
    static {
        try {
            String filePath = new ClassPathResource("fdfs_client_conf").getFile().getAbsolutePath();
            ClientGlobal.init(filePath);
        } catch (Exception e) {
            logger.error("FastDFS Client Init Fail", e);
        }
    }

    /**
     * 获取客户端
     */
    private static StorageClient getStorageClient() throws IOException {
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient;
    }

    /**
     * 获取Tracker连接
     */
    private static TrackerServer getTrackerServer() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerServer;
    }

    /**
     * 上传类
     * 使⽤ FastDFS 提供的客户端 storageClient 来进⾏⽂件上传，最后将上传结果返回。
     *
     * @param file:文件
     * @return String[]
     */
    public static String[] upload(DFSFile file) {
        logger.info("FileName:" + file.getName() + "; FileLength:" + file.getContent().length);

        //存储文件基本属性
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author", file.getAuthor());

        long start = System.currentTimeMillis();
        String[] uploadResult = null;
        StorageClient storageClient = null;

        try {
            storageClient = getStorageClient();
            uploadResult = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
        } catch (IOException e) {
            logger.error("IO Exception In uploading the file:" + file.getName(), e);
        } catch (Exception e) {
            logger.error("Exception In uploading the file:" + file.getName());
        }
        long end = System.currentTimeMillis();
        logger.info("upload_file used time :" + (end - start) + "ms");

        if (uploadResult == null && storageClient != null) {
            logger.error("upload the file failed,error code:" + storageClient.getErrorCode());
        }

        logger.info("upload the file successfully In groupName:" + uploadResult[0] + ",remoteFileName: " +
                uploadResult[1]);
        return uploadResult;
    }

    /**
     * 从Storage集群中获取FileInfo
     *
     * @param groupName:集群组数
     * @param remoteFileName:文件名
     * @return FileInfo
     */
    public static FileInfo getFile(String groupName, String remoteFileName) {
        try {
            StorageClient storageClient = getStorageClient();
            return storageClient.get_file_info(groupName, remoteFileName);
        } catch (IOException e) {
            logger.error("IO Exception:Get The File From DFS Failed", e);
        } catch (Exception e) {
            logger.error("Exception:Get The File From DFS Failed", e);
        }
        return null;
    }

    /**
     * 从Storage集群中获取FileInputStream
     *
     * @param groupName:集群组数
     * @param remoteFileName:文件名
     * @return FileInfo
     */
    public static InputStream downFile(String groupName, String remoteFileName) {
        try {
            StorageClient storageClient = getStorageClient();
            byte[] file = storageClient.download_file(groupName, remoteFileName);
            InputStream inputStream = new ByteArrayInputStream(file);
            return inputStream;
        } catch (IOException e) {
            logger.error("IO Exception:Get The File From DFS Failed", e);
        } catch (Exception e) {
            logger.error("Exception:Get The File From DFS Failed", e);
        }
        return null;
    }


    /**
     * 删除文件
     */
    public static void deleteFile(String groupName, String remoteFileName) throws Exception {
        StorageClient storageClient = getStorageClient();
        logger.info("delete the file:" + storageClient.delete_file(groupName, remoteFileName) + " successfully!");
    }

    /**
     * 获取storage端集群
     */
    public static StorageServer[] getStoreStorageS(String groupName) throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getStoreStorages(trackerServer, groupName);
    }

    /**
     * 获取文件关联的storage端集群Info
     */
    public static ServerInfo[] getFetchStorageS(String groupName, String remoteFileName) throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
    }

    /**
     * 获取Tracker端的Url
     */
    public static String getTrackerUrl() throws IOException {
        return "http://" + getTrackerServer().getInetSocketAddress().getHostString() + ":" + ClientGlobal.getG_tracker_http_port() + "/";
    }


//    private volatile  static StorageClient storageClient;
//
//    private static StorageClient reGetStorageClient() throws IOException{
//        TrackerServer trackerServer = getTrackerServer();
//        if (storageClient==null){
//            synchronized (StorageClient.class){
//                if(storageClient==null){
//                    storageClient = new StorageClient(trackerServer,null);
//                }
//            }
//        }
//        return storageClient;
//    }


}
