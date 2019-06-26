package com.hui.base.common.util.ftp;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

/**
 * <b><code>FtpUtil</code></b>
 * <p/>
 * Description: 并发量不大。简单工具类抓文件。支持多线程并发巨大的工具根据项目情况后补。
 * <p/>
 * <b>Creation Time:</b> 2018/12/5 11:59.
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
     * @param ftpClient  the ftp client
     * @param sourcePath the file path
     * @param fileName   the file name
     * @param destPath   the dest path
     * @return the boolean
     * @author : Hu weihui
     * @since nile -cmszbs-szcst 0.1.0
     */
    public static boolean downLoadFile(FTPClient ftpClient, String sourcePath, String fileName, String destPath) {
        try {
            if (!ftpClient.changeWorkingDirectory(sourcePath)) {
                log.info("[FtpUtil] {} 该目录不存在", sourcePath);
                return false;
            }

            ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据

            String[] fileArry = ftpClient.listNames();
            if (fileArry == null || fileArry.length == 0) {
                log.info("[FtpUtil] {} 目录下为空", sourcePath);
                return false;
            }

            FTPFile[] ftpFiles = ftpClient.listFiles();

            for (FTPFile ftpFile : ftpFiles) {
                if (ftpFile.getName().equals(fileName)) {
                    String destFilePath = destPath + "/"+fileName;
                    log.info(destFilePath);

                    File file = new File(destFilePath);
                    try(FileOutputStream fileOutputStream = new FileOutputStream(file)){
                        ftpClient.retrieveFile(ftpFile.getName(), fileOutputStream);
                        break;
                    }

                }
            }
        } catch (IOException e) {
            log.debug("[FtpUtil] download fail ,{}", e);
            closeConnect(ftpClient);
            return false;
        }
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
     * @since nile -cmszbs-szcst 0.1.0
     */
    public static boolean batchDownloadFile(FTPClient ftpClient, String sourcePath, String destPath) {
        try {
            ftpClient.enterLocalPassiveMode();
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
            closeConnect(ftpClient);
            return false;
        }
        return true;
    }


    /**
     * 上传单个文件到指定目录.
     *
     * @param ftpClient  the ftp client
     * @param sourcePath the source file path
     * @param fileName   the source file name
     * @param destPath   the dest path
     * @author : Hu weihui
     * @since nile -cmszbs-szcst 0.1.0
     */
    public static boolean uploadFile(FTPClient ftpClient, String sourcePath, String fileName, String destPath) {
        String sourceFilePath = sourcePath + "/" + fileName;
        File file = new File(sourceFilePath);
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ftpClient.setBufferSize(BUFFER_SIZE);
            ftpClient.setControlEncoding(CHARSET_UTF8);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();

            if (!ftpClient.changeWorkingDirectory(destPath)) {
                log.debug("[FtpUtil] uploadFile : {}目录不存在，请创建一下", destPath);
            }
            ftpClient.storeFile(new String(fileName.getBytes(localCharset), serverCharset), fileInputStream);
        } catch (FileNotFoundException e) {
            log.debug("[FtpUtil] uploadFile : not found local file ::: {}", sourceFilePath);
            closeConnect(ftpClient);
        } catch (IOException e) {
            log.debug("[FtpUtil] uploadFile : fileOutputStream error  ::: {}", e);
            closeConnect(ftpClient);
        }
        return true;
    }


    /**
     * 目录A所有文件上传到FTP目录B.
     *
     * @param ftpClient  the ftp client
     * @param sourcePath the source path
     * @param destPath   the dest path
     * @author : Hu weihui
     * @since nile -cmszbs-szcst 0.1.0
     */
    public static void batchUploadFile(FTPClient ftpClient, String sourcePath, String destPath) {
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
                try(FileInputStream fileInputStream = new FileInputStream(sourceFile)){
                    ftpClient.storeFile(new String(destPath.getBytes(localCharset), serverCharset), fileInputStream);
                }
            }
        } catch (IOException e) {
            log.error("[FtpUtil] batchUploadFile : fileIntputStream close fail");
            closeConnect(ftpClient);
        }
    }

    /**
     * 批量删除FTP目录下的所有文件.
     *
     * @param ftpClient the ftp client
     * @param destPath  the dest path
     * @author : Hu weihui
     * @since nile -cmszbs-szcst 0.1.0
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
            log.debug("[FtpUtil]-deleteFiles 删除文件失败，{}", e);
        }
    }


    /**
     * 批量移动FTP服务器目录A的所有文件到目录B下.
     *
     * @param ftpClient  the ftp client
     * @param sourcePath the source path
     * @param destPath   the dest path
     * @author : Hu weihui
     * @since nile -cmszbs-szcst 0.1.0
     */
    public static void batchMoveFileToDir(FTPClient ftpClient, String sourcePath, String destPath) {
        try {
            if (!ftpClient.changeWorkingDirectory(sourcePath)) {
                log.info("[FtpUtil] {} 该目录不存在", sourcePath);
                return;
            }
            String[] ftpFiles = ftpClient.listNames();
            if (null == ftpFiles || ftpFiles.length == 0) {
                log.debug("[FtpUtil]-monveFileToDir {} 目录下没有文件", destPath);
                return;
            }
            //批量移动文件到目标目录
            for (String ftpFile : ftpFiles) {
                String ftpFileName = new String(ftpFile.getBytes(serverCharset), localCharset);
                String srcFilePath = sourcePath + "/" + ftpFileName;
                String destFilePath = destPath + "/" + ftpFileName;
                ftpClient.rename(srcFilePath, destFilePath);
            }
        } catch (IOException e) {
            log.error("[FtpUtil]-monveFileToDir  查询文件夹{}失败", sourcePath, e);
            closeConnect(ftpClient);
        }
    }


    /**
     * 移动FTP服务器目录单个到目录B下.
     *
     * @param ftpClient  the ftp client
     * @param sourcePath the source path
     * @param destPath   the dest path
     * @author : Hu weihui
     * @since nile -cmszbs-szcst 0.1.0
     */
    public static void moveFileToDir(FTPClient ftpClient, String sourcePath, String fileName, String destPath) {
        try {
            if (!ftpClient.changeWorkingDirectory(sourcePath)) {
                log.info("[FtpUtil] {} 该目录不存在", sourcePath);
                return;
            }
            String[] ftpFiles = ftpClient.listNames();
            if (null == ftpFiles || ftpFiles.length == 0) {
                log.debug("[FtpUtil]-monveFileToDir {} 目录下没有文件", destPath);
                return;
            }
            //批量移动文件到目标目录
            for (String ftpFile : ftpFiles) {
                String ftpFileName = new String(ftpFile.getBytes(serverCharset), localCharset);
                if (ftpFileName.equals(fileName)) {
                    String srcFilePath = sourcePath + "/" + ftpFileName;
                    String destFilePath = destPath + "/" + ftpFileName;
                    ftpClient.rename(srcFilePath, destFilePath);
                }
            }
        } catch (IOException e) {
            log.error("[FtpUtil]-monveFileToDir  查询文件夹{}失败", sourcePath, e);
            closeConnect(ftpClient);
        }
    }

    /**
     * 登录Ftp服务器获取ftpclient.
     *
     * @param host     the host
     * @param port     the port
     * @param userName the user name
     * @param password the password
     * @return the ftp client
     * @author : Hu weihui
     * @since nile -cmszbs-szcst 0.1.0
     */
    public static FTPClient login(String host, Integer port, String userName, String password) {
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
     * @since nile -cmszbs-szcst 0.1.0
     */
    public static void closeConnect(FTPClient ftpClient) {
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
