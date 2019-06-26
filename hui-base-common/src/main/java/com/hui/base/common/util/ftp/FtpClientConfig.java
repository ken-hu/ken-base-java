package com.hui.base.common.util.ftp;

import lombok.Data;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <b><code>FtpClientConfig</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2018/12/9 12:01.
 *
 * @author HuWeihui
 */
@Component
@PropertySource("classpath:config/ftp.properties")
@ConfigurationProperties("ftp")
@Data
public class FtpClientConfig extends GenericObjectPoolConfig {
    //主机名
    private String host;

    //端口
    private int port = 21;

    //用户名
    private String username;

    //密码
    private String password;

    //ftp 连接超时时间 毫秒
    private int connectTimeOut = 5000;

    //字符编码
    private String controlEncoding = "utf-8";

    //缓冲区大小
    private int bufferSize = 1024;

    // 传输数据格式   2是 binary二进制数据
    private int fileType = FTPClient.BINARY_FILE_TYPE;

    //毫秒
    private int dataTimeout = 120000;

    //是否应该尝试使用与IPv4 EPSV
    private boolean useEPSVwithIPv4 = false;

    //是否启用被动模式
    private boolean passiveMode = true;

}
