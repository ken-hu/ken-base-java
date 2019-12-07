package com.hui.base.springboot.common.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.impl.GenericObjectPool;

/**
 * <b><code>FtpClientPool</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2018/12/8 23:29.
 *
 * @author HuWeihui
 */
@Slf4j
@Getter
public class FtpClientPool {

    private GenericObjectPool<FTPClient> pool;

    private FtpClientFactory clientFactory;

    public FtpClientPool(FtpClientFactory clientFactory) {
        this.clientFactory = clientFactory;
        pool = new GenericObjectPool<FTPClient>(clientFactory,clientFactory.getFtpClientConfig());
    }

    public FTPClient borrowObject() throws Exception {
        FTPClient  client =    pool.borrowObject();
        return client ;
    }

    public void returnObject(FTPClient ftpClient) {
        if(ftpClient!=null){
            pool.returnObject(ftpClient);
        }
    }
}
