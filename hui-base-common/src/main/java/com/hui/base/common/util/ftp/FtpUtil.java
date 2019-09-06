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
     * @param ftpClient the ftp client
     * @param srcPath   the file path
     * @param fileName  the file name
     * @param destPath  the dest path
     * @return the boolean
     * @author : Hu weihui
     * @since hui-project
     */
    public static void download(FTPClient ftpClient, String srcPath, String fileName, String destPath) {
        try {
            FTPFile[] ftpFiles = ftpClient.listFiles();
            enterDir(ftpClient, srcPath);
            checkDirExist(destPath);

            for (FTPFile ftpFile : ftpFiles) {
                if (ftpFile.getName().equals(fileName)) {
                    String destFilePath = destPath + "/" + fileName;
                    File file = new File(destFilePath);
                    try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                        ftpClient.retrieveFile(ftpFile.getName(), fileOutputStream);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            log.error("ftp download file fail !!!", e);
            throw new RuntimeException("ftp download file fail !!!");
        }
    }


    /**
     * 批量下载文件.
     *
     * @param ftpClient the ftp client
     * @param srcPath   the source path
     * @param destPath  the dest path
     * @return the boolean
     * @author : Hu weihui
     * @since hui-project
     */
    public static void batchDownload(FTPClient ftpClient, String srcPath, String destPath) {
        try {
            enterDir(ftpClient, srcPath);
            checkDirExist(destPath);

            FTPFile[] ftpFiles = ftpClient.listFiles();
            for (FTPFile ftpFile : ftpFiles) {
                File file = new File(destPath + File.separator + ftpFile.getName());
                try (FileOutputStream outputStream = new FileOutputStream(file);
                ) {
                    ftpClient.retrieveFile(ftpFile.getName(), outputStream);
                }
            }
        } catch (IOException e) {
            log.error("ftp batch download files fail !!!", e);
            throw new RuntimeException("ftp batch download files fail !!!");
        }
    }


    /**
     * 上传单个文件到指定目录.
     *
     * @param ftpClient the ftp client
     * @param srcPath   the source file path
     * @param fileName  the source file name
     * @param destPath  the dest path
     * @author : Hu weihui
     * @since hui-project
     */
    public static void upload(FTPClient ftpClient, String srcPath, String fileName, String destPath) {
        String sourceFilePath = srcPath + File.separator + fileName;
        File file = new File(sourceFilePath);
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ftpClient.setBufferSize(BUFFER_SIZE);
            ftpClient.setControlEncoding(CHARSET_UTF8);
            enterDir(ftpClient, destPath);
            checkDirExist(srcPath);
            ftpClient.storeFile(new String(fileName.getBytes(localCharset), serverCharset), fileInputStream);
        } catch (IOException e) {
            log.error("ftp upload file fail !!!", e);
            throw new RuntimeException("ftp upload file fail !!!");
        }
    }


    /**
     * 目录A所有文件上传到FTP目录B.
     *
     * @param ftpClient the ftp client
     * @param srcPath   the source path
     * @param destPath  the dest path
     * @author : Hu weihui
     * @since hui-project
     */
    public static void batchUpload(FTPClient ftpClient, String srcPath, String destPath) {
        try {
            ftpClient.setBufferSize(BUFFER_SIZE);
            ftpClient.setControlEncoding(CHARSET_UTF8);
            enterDir(ftpClient, destPath);
            checkDirExist(srcPath);
            //读取源路径
            File file = new File(srcPath);
            //获取下面所有文件（不递归）
            File[] files = file.listFiles();
            for (File sourceFile : files) {
                try (FileInputStream fileInputStream = new FileInputStream(sourceFile)) {
                    ftpClient.storeFile(new String(destPath.getBytes(localCharset), serverCharset), fileInputStream);
                }
            }
        } catch (IOException e) {
            log.error("ftp batch upload files fail !!!", e);
            throw new RuntimeException("ftp batch upload files fail !!!");
        }
    }

    /**
     * 批量删除FTP目录下的所有文件.
     *
     * @param ftpClient the ftp client
     * @param destPath  the dest path
     * @author : Hu weihui
     * @since hui-project
     */
    public static void batchDelete(FTPClient ftpClient, String destPath) {
        try {
            enterDir(ftpClient,destPath);

            String[] fileNames = ftpClient.listNames(destPath);

            for (String fileName : fileNames) {
                String destFilePath = destPath + File.separator + fileName;
                ftpClient.deleteFile(destFilePath);
            }

        } catch (IOException e) {
            log.debug("ftp batch delete files fail !!!", e);
            throw new RuntimeException("ftp batch delete files fail !!!");
        }
    }

    /**
     * 删除FTP文件
     * @param ftpClient
     * @param destPath
     * @param fileName
     */
    public static void delete(FTPClient ftpClient,String destPath,String fileName){
        try {
            enterDir(ftpClient,destPath);

            String destFilePath = destPath + File.separator + fileName;

            ftpClient.deleteFile(destFilePath);

        } catch (IOException e) {
            log.error("ftp delete file fail !!!", e);
            throw new RuntimeException("ftp delete file fail !!!");
        }
    }

    /**
     * 批量移动FTP服务器目录A的所有文件到目录B下.
     *
     * @param ftpClient  the ftp client
     * @param srcPath the source path
     * @param destPath   the dest path
     * @author : Hu weihui
     * @since hui-project
     */
    public static void batchMove(FTPClient ftpClient, String srcPath, String destPath) {
        try {
            enterDir(ftpClient,destPath);
            String[] ftpFiles = ftpClient.listNames();
            //批量移动文件到目标目录
            for (String ftpFile : ftpFiles) {
                String ftpFileName = new String(ftpFile.getBytes(serverCharset), localCharset);
                String srcFilePath = srcPath + File.separator + ftpFileName;
                String destFilePath = destPath + File.separator + ftpFileName;
                ftpClient.rename(srcFilePath, destFilePath);
            }
        } catch (IOException e) {
            log.error("ftp batch move files from {} to {} fail !!!", srcPath,destPath, e);
            throw new RuntimeException("ftp batch move file fail !!!");
        }
    }


    /**
     * 移动FTP服务器目录单个到目录B下.
     *
     * @param ftpClient  the ftp client
     * @param srcPath the source path
     * @param destPath   the dest path
     * @author : Hu weihui
     * @since hui-project
     */
    public static void move(FTPClient ftpClient, String srcPath, String fileName, String destPath) {
        try {
            String[] ftpFiles = ftpClient.listNames();
            //批量移动文件到目标目录
            for (String ftpFile : ftpFiles) {
                String ftpFileName = new String(ftpFile.getBytes(serverCharset), localCharset);
                if (ftpFileName.equals(fileName)) {
                    String srcFilePath = srcPath + File.separator+ ftpFileName;
                    String destFilePath = destPath + File.separator + ftpFileName;
                    ftpClient.rename(srcFilePath, destFilePath);
                }
            }
        } catch (IOException e) {
            log.error("ftp move files fail !!!", srcPath, e);
            throw new RuntimeException("ftp move files fail !!!");
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
     * @since hui-project
     */
    public static FTPClient login(String host, Integer port, String userName, String password) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(host, port);
            ftpClient.login(userName, password);
            int replyCode = ftpClient.getReplyCode();
            if (FTPReply.isPositivePreliminary(replyCode)) {
                log.error("FTP connect fail  ");
                close(ftpClient);
                throw new RuntimeException("FTP connect fail");
            }
            // 设置文件下载为二进制模式
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            //设置编码
            setEncode(ftpClient);
            ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
        } catch (IOException e) {
            log.error("FTP login fail ", e);
            throw new RuntimeException("FTP login fail");
        }
        return ftpClient;
    }


    /**
     * 关闭连接.
     *
     * @author : Hu weihui
     * @since hui-project 0.1.0
     */
    public static void close(FTPClient ftpClient) {
        if (ftpClient != null && ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                log.error("ftp client close fail !!!", e);
            }
        }
    }


    /**
     * 检查文件夹是否存在并创建
     *
     * @param destPath
     * @throws IOException
     */
    private static void checkDirExist(String destPath) throws IOException {
        File file = new File(destPath);
        if (!file.exists()) {
            boolean success = file.mkdirs();
            if (!success) {
                throw new IOException("cannot to make dir for local");
            }
        }
    }

    /**
     * FTP 进入文件夹
     *
     * @param ftpClient
     * @param dirPath
     * @throws IOException
     */
    private static void enterDir(FTPClient ftpClient, String dirPath) throws IOException {
        if (!ftpClient.changeWorkingDirectory(dirPath)) {
            log.error("{} is not exist", dirPath);
            throw new RuntimeException("FTP download file fail !!!");
        }
    }

    /**
     * 设置编码.
     *
     * @throws IOException the io exception
     * @author : Hu weihui
     * @since nile -szcst 0.1.0
     */
    private static void setEncode(FTPClient ftpClient) throws IOException {
        final String ON = "ON";
        if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(OPTS_UTF8, ON))) {
            localCharset = CHARSET_UTF8;
        }
    }
}
