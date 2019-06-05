package com.hui.base.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * <b><code>HDFSTest</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2018/11/6 23:38.
 *
 * @author Hu Weihui
 */
public class HDFSTest {
    /**
     * The File system.
     *
     * @since hui_project 1.0.0
     */
    private FileSystem fileSystem = null;
    /**
     * The Configuration.
     *
     * @since hui_project 1.0.0
     */
    private Configuration configuration = null;

    /**
     * 初始化.
     *
     * @throws Exception the exception
     * @since hui_project 1.0.0
     */
    @Before
    public void before() throws Exception {
        configuration = new Configuration();
        // 获取一个fileSystem对象，这就相当于建立连接了
        fileSystem = FileSystem.get(configuration);
    }


    /**
     * 创建文件夹.
     *
     * @throws IOException the io exception
     * @since hui_project 1.0.0
     */
    @Test
    public void testMakeDir() throws IOException {
        fileSystem.mkdirs(new Path("D:\\test\\test"));
    }

    /**
     * 创建文件，参数二true代表存在即覆盖.
     *
     * @throws IOException the io exception
     * @since hui_project 1.0.0
     */
    @Test
    public void testCreateFile() throws IOException {
        fileSystem.create(new Path("D:\\test\\test\\demo.txt"), true);
    }

    /**
     * 创建新文件 .
     * 不同于create是 先执行 exists方法查看文件是否存在，不存在才创建
     * @throws IOException the io exception
     * @since hui_project 1.0.0
     */
    @Test
    public void testCreateNewFile() throws IOException {
        fileSystem.createNewFile(new Path("D:\\test\\test\\demo.txt"));
    }

    /**
     * 读取文件并打印
     *
     * @throws IOException the io exception
     * @since hui_project 1.0.0
     */
    @Test
    public void testReadFile() throws IOException {
        FSDataInputStream fsDataInputStream = fileSystem.open(new Path("D:\\test\\test.txt"));
        IOUtils.copyBytes(fsDataInputStream, System.out, configuration);
    }

    /**
     * 文件是否存在.
     *
     * @throws IOException the io exception
     * @since hui_project 1.0.0
     */
    @Test
    public void testExist() throws IOException {
        boolean exists = fileSystem.exists(new Path("D:\\test"));
        System.out.println(exists);
    }

    /**
     * 下载文件从指定目录.
     *
     * @throws IOException the io exception
     * @since hui_project 1.0.0
     */
    @Test
    public void downLoadFile() throws IOException {
        fileSystem.copyFromLocalFile(new Path("D:\\test\\distance-final.txt"), new Path("D:\\test\\test\\"));
    }

    /**
     * 上传文件到指定目录.
     *
     * @throws IOException the io exception
     * @since hui_project 1.0.0
     */
    @Test
    public void uploadFile() throws IOException {
        fileSystem.copyFromLocalFile(new Path("D:\\test\\demo.txt"), new Path("D:"));
    }

    /**
     * 删除文件或文件夹.
     * 参数二的true代表 递归删除
     * @throws IOException the io exception
     * @since hui_project 1.0.0
     */
    @Test
    public void deleteFile() throws IOException {
        fileSystem.delete(new Path("D:/test/test"), true);
    }

    /**
     * 追加内容.
     *
     * @throws IOException the io exception
     * @since hui_project 1.0.0
     */
    @Test
    public void testAppendContent() throws IOException {
        configuration.set("dfs.support.append", "true");
        FSDataOutputStream fsDataOutputStream = fileSystem.append(new Path("D:/test/test/demo.txt"));
        fsDataOutputStream.write(new String("test something ").getBytes());
    }

    /**
     * 重命名文件或文件夹.
     *
     * @throws IOException the io exception
     * @since hui_project 1.0.0
     */
    @Test
    public void testRename() throws IOException {
        fileSystem.rename(new Path("D:/test/test/demo.txt"), new Path("D:/test/test/demo1.txt"));
    }

    /**
     * 列出指定文件夹的文件以及文件夹信息.
     *
     * @throws IOException the io exception
     * @since hui_project 1.0.0
     */
    @Test
    public void testListStatus() throws IOException {
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path("D:/test"));
        for (FileStatus fileStatus : fileStatuses) {
            System.out.println(fileStatus.getPath().toString());
        }
    }

    /**
     * 列出指定路径所有文件信息.
     * listFiles第二个参数 true 递归查找 会把子文件夹的文件信息也查找出来
     *
     * @throws IOException the io exception
     * @since hui_project 1.0.0
     */
    @Test
    public void testListFile() throws IOException {
        RemoteIterator<LocatedFileStatus> fileStatusRemoteIterator = fileSystem.listFiles(new Path("D:/test"), true);
        while (fileStatusRemoteIterator.hasNext()) {
            LocatedFileStatus next = fileStatusRemoteIterator.next();
            System.out.println(next.getPath());
        }
    }
}
