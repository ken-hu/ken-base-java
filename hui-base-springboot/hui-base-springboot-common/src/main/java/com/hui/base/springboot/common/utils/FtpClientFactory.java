package com.hui.base.springboot.common.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * <b><code>FtpClientFactory</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2018/12/8 23:28.
 *
 * @author HuWeihui
 */
@Component
@Slf4j
@Data
public class FtpClientFactory extends BasePooledObjectFactory<FTPClient> {

    /**
     * The Ftp client config.
     */
    @Resource
    private FtpClientConfig ftpClientConfig;

    @Override
    public FTPClient create() throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setConnectTimeout(1);

        ftpClient.connect("localhost", 10086);

        int replyCode = ftpClient.getReplyCode();

        if (!FTPReply.isPositiveCompletion(replyCode)) {
            ftpClient.disconnect();
            log.info("[FtpClientFactory] FTP SERVER 拒绝连接");
            return null;
        }

        boolean result = ftpClient.login(ftpClientConfig.getUsername(), ftpClientConfig.getPassword());

        if (!result) {
            log.info("[FtpClientFactory] ftpClient登陆失败");
            //todo 抛异常
        }

        ftpClient.setControlEncoding("utf-8");
        ftpClient.setBufferSize(1024);
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        ftpClient.setDataTimeout(600000);
        ftpClient.setUseEPSVwithIPv4(true);
        //todo 读取配置文件是否开启被动面模式
        if (ftpClientConfig.isPassiveMode()) {
            ftpClient.enterLocalActiveMode();
            log.info("[FtpClientFactory] ftpClient进入被动模式");
        }

        return ftpClient;
    }

    @Override
    public PooledObject<FTPClient> wrap(FTPClient ftpClient) {

        return new DefaultPooledObject<FTPClient>(ftpClient);
    }


    @Override
    public void destroyObject(PooledObject<FTPClient> pooledObject) throws Exception {
        FTPClient ftpClient = pooledObject.getObject();
        ftpClient.logout();
        super.destroyObject(pooledObject);
    }

    @Override
    public boolean validateObject(PooledObject<FTPClient> pooledObject) {
        FTPClient ftpClient = pooledObject.getObject();
        boolean connect = false;
        try {
            connect = ftpClient.sendNoOp();
        } catch (IOException e) {
            log.error("校验FtpClient对象,返回false");
        }
        return false;
    }
}
