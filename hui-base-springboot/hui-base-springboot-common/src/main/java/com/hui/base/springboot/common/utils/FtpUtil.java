package com.hui.base.springboot.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

/**
 * <b><code>FtpUtil</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2018/12/18 14:22.
 *
 * @author Hu weihui
 */
@Slf4j
public class FtpUtil {
    /**
     * 本地字符编码
     **/
    private static String localCharset = "GBK";

    /**
     * FTP协议里面，规定文件名编码为iso-8859-1
     **/
    private static String serverCharset = "ISO-8859-1";

    /**
     * UTF-8字符编码
     **/
    private static final String CHARSET_UTF8 = "UTF-8";

    /**
     * OPTS UTF8字符串常量
     **/
    private static final String OPTS_UTF8 = "OPTS UTF8";

    /**
     * 设置缓冲区大小4M
     **/
    private static final int BUFFER_SIZE = 1024 * 1024 * 4;


    /**
     * 下载单个文件.
     *
     * @param ftpClient the ftp client
     * @param filePath  the file path
     * @param fileName  the file name
     * @param destPath  the dest path
     * @return the boolean
     * @author : Hu weihui
     * @since hui_project v1
     */
    public static boolean downLoadFile(FTPClient ftpClient, String filePath, String fileName, String destPath) {
        try {
            if (!ftpClient.changeWorkingDirectory(filePath)) {
                log.info("[FtpUtil] {} 该目录不存在", filePath);
                return false;
            }

            ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据

            String[] fileArry = ftpClient.listNames();
            if (fileArry == null || fileArry.length == 0) {
                log.info("[FtpUtil] {} 目录下为空", filePath);
                return false;
            }

            FTPFile[] ftpFiles = ftpClient.listFiles();

            for (FTPFile ftpFile : ftpFiles) {
                if (ftpFile.getName().equals(fileName)) {
                    String destFilePath = destPath + fileName;
                    log.info(destFilePath);

                    File file = new File(destFilePath);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    ftpClient.retrieveFile(ftpFile.getName(), fileOutputStream);
                    fileOutputStream.close();
                    break;
                }
            }
        } catch (IOException e) {
            log.debug("[FtpUtil] download fail ,{}", e);
            return false;
        }
        closeConnect(ftpClient);
        return true;
    }


    /**
     * 批量下载文件.
     *
     * @param ftpClient  the ftp client
     * @param sourcePath the source path
     * @param destPath   the dest path
     * @return the boolean
     * @author : Hu weihui
     * @since hui_project v1
     */
    public static boolean batchDownloadFile(FTPClient ftpClient, String sourcePath, String destPath) {
        try {
            if (!ftpClient.changeWorkingDirectory(sourcePath)) {
                log.info("[FtpUtil] {} 该目录不存在", sourcePath);
                return false;
            }

            String[] fileArry = ftpClient.listNames();
            if (fileArry == null || fileArry.length == 0) {
                log.info("[FtpUtil] {} 目录下为空", sourcePath);
                return false;
            }
            for (String fileName : fileArry) {
                String ftpName = new String(fileName.getBytes(serverCharset), localCharset);
                File file = new File(destPath + '/' + ftpName);
                FileOutputStream outputStream = new FileOutputStream(file);
                ftpClient.retrieveFile(fileName, outputStream);
                outputStream.close();
            }
        } catch (IOException e) {
            log.debug("[FtpUtil]-batchDownloadFile download fail ,{}", e);
            return false;
        } finally {
            closeConnect(ftpClient);
        }

        return true;
    }


    /**
     * 上传单个文件到指定目录.
     *
     * @param ftpClient      the ftp client
     * @param sourceFilePath the source file path
     * @param destPath       the dest path
     * @author : Hu weihui
     * @since hui_project v1
     */
    public static void uploadFile(FTPClient ftpClient, String sourceFilePath, String destPath) {
        File file = new File(sourceFilePath);
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ftpClient.setBufferSize(BUFFER_SIZE);
            ftpClient.setControlEncoding(CHARSET_UTF8);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();

            if (!ftpClient.changeWorkingDirectory(destPath)) {
                log.debug("[FtpUtil] uploadFile : 目录不存在，请创建一下");
            }

            ftpClient.storeFile(new String(destPath.getBytes(localCharset), serverCharset), fileInputStream);
        } catch (FileNotFoundException e) {
            log.debug("[FtpUtil] uploadFile : not found local file ::: {}", sourceFilePath);
        } catch (IOException e) {
            log.debug("[FtpUtil] uploadFile : fileOutputStream error  ::: {}", e);
        } finally {
            closeConnect(ftpClient);
        }
    }


    /**
     * 目录A所有文件上传到FTP目录B.
     *
     * @param ftpClient  the ftp client
     * @param sourcePath the source path
     * @param destPath   the dest path
     * @author : Hu weihui
     * @since hui_project v1
     */
    public static void batchUploadFile(FTPClient ftpClient, String sourcePath, String destPath) {
        FileInputStream fileInputStream = null;

        try {
            ftpClient.setBufferSize(BUFFER_SIZE);
            ftpClient.setControlEncoding(CHARSET_UTF8);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

            if (!ftpClient.changeWorkingDirectory(destPath)) {
                log.debug("[FtpUtil] uploadFile : 目录不存在，请创建一下");
            }

            //读取源路径
            File file = new File(sourcePath);
            //获取下面所有文件（不递归）
            File[] files = file.listFiles();
            for (File sourceFile : files) {
                fileInputStream = new FileInputStream(sourceFile);
                ftpClient.storeFile(new String(destPath.getBytes(localCharset), serverCharset), fileInputStream);
                fileInputStream.close();
            }
        } catch (IOException e) {
            log.error("[FtpUtil] batchUploadFile : fileIntputStream close fail");
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                closeConnect(ftpClient);
            } catch (IOException e) {
                log.error("[FtpUtil] batchUploadFile : fileIntputStream close fail");
            }
        }
    }

    /**
     * 批量删除FTP目录下的所有文件.
     *
     * @param ftpClient the ftp client
     * @param destPath  the dest path
     * @author : Hu weihui
     * @since hui_project v1
     */
    public static void deleteFiles(FTPClient ftpClient, String destPath) {

        //设置被动模式
        ftpClient.enterLocalPassiveMode();

        try {
            String[] fileNames = ftpClient.listNames(destPath);

            if (null == fileNames || fileNames.length == 0) {
                log.debug("[FtpUtil]-deleteFiles {} 目录下没有文件", destPath);
            }
            for (String ftpFile : fileNames) {
                ftpClient.deleteFile(ftpFile);
            }
        } catch (IOException e) {
            log.debug("[FtpUtil]-deleteFiles 查询文件失败，{}", e);
        }
    }


    /**
     * 批量移动FTP服务器目录A的所有文件到目录B下.
     *
     * @param ftpClient  the ftp client
     * @param sourcePath the source path
     * @param destPath   the dest path
     * @author : Hu weihui
     * @since hui_project v1
     */
    public static void monveFileToDir(FTPClient ftpClient, String sourcePath, String destPath) {
        try {
            FTPFile[] ftpFiles = ftpClient.listFiles(sourcePath);
            if (null == ftpFiles || ftpFiles.length == 0) {
                log.debug("[FtpUtil]-monveFileToDir {} 目录下没有文件", destPath);
                return;
            }
            //批量移动文件到目标目录
            for (FTPFile ftpFile : ftpFiles) {
                String srcFilePath = sourcePath + "/" + ftpFile.getName();
                String destFilePath = destPath +"/"+ ftpFile.getName();
                ftpClient.rename(srcFilePath, destFilePath);
            }
        } catch (IOException e) {
            log.error("[FtpUtil]-monveFileToDir {} 查询文件夹{}失败", sourcePath);
        }
    }


    /**
     * 登录Ftp服务器.
     *
     * @param host     the host
     * @param port     the port
     * @param userName the user name
     * @param password the password
     * @return the ftp client
     * @author : Hu weihui
     * @since hui_project v1
     */
    public static FTPClient login(String host,Integer port,String userName,String password) {
        try {
            FTPClient ftpClient = new FTPClient();
            ftpClient.connect(host, port);
            ftpClient.login(userName, password);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            int replyCode = ftpClient.getReplyCode();
            if (FTPReply.isPositivePreliminary(replyCode)) {
                closeConnect(ftpClient);
                log.info("[FtpUtil] ftp connect fail ");
            }
            setEncode(ftpClient);
            return ftpClient;
        } catch (Exception e) {
            log.info("[FtpUtil] ftp login fail ");
            throw new RuntimeException("[FtpUtil] FTPClient 获取为空 ");
        }
    }


    /**
     * 关闭连接.
     *
     * @author : Hu weihui
     * @since hui_project v1
     */
    private static void closeConnect(FTPClient ftpClient) {
        if (ftpClient != null && ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                log.info("[FtpUtil] close ftp connecte fail {} ", e);
            }
        }
    }


    /**
     * 设置编码.
     *
     * @throws IOException the io exception
     * @author : Hu weihui
     * @since nile -szcst 0.1.0
     */
    private static void setEncode(FTPClient ftpClient) {
        try {
            if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(OPTS_UTF8, "ON"))) {
                localCharset = CHARSET_UTF8;
            }
        } catch (IOException e) {
            log.info("[FtpUtil] cannot to set encode UTF-8");
        }

    }
}
