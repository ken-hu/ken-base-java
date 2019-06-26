package com.hui.base.common.util.database;

import lombok.extern.slf4j.Slf4j;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * <b><code>PostGreSQLUtil</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2018/12/18 14:26.
 *
 * @author Hu weihui
 */
@Slf4j
public class PostGreSQLUtil {
    /**
     * 用文件导入数据到PG.(可以指定列名)
     *
     * @param connection the connection
     * @param filePath   the file path
     * @param tableName  the table name
     * @author : Hu weihui
     * @since hui_project v1
     */
    public static void importFromFile(Connection connection, String filePath, String tableName, String columnInfo) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            CopyManager copyManager = new CopyManager((BaseConnection) connection.getMetaData().getConnection());
            String sql = "";
            if (StringUtils.isEmpty(columnInfo)) {
                sql = String.format("COPY %s FROM STDIN WITH CSV HEADER ENCODING 'UTF8", tableName);
            } else {
                sql = String.format("COPY %s (%s) FROM STDIN WITH CSV HEADER ENCODING 'UTF8", tableName, columnInfo);
            }
            copyManager.copyIn(sql, fileInputStream);
        } catch (FileNotFoundException e) {
            log.error("[PostgreImportDataUtil]-copyFromFile Can not to find :{}", filePath);
        } catch (IOException e) {
            log.error("[PostgreImportDataUtil]-copyFromFile fail to get FileInputStream");
        } catch (SQLException e) {
            log.error("[PostgreImportDataUtil]-copyFromFile BAD SQL,{}", e);
        }
    }

    /**
     * 导出到文件.(tableName 或者 querySQL)
     *
     * @param connection   the connection
     * @param filePath     the file path
     * @param tableOrQuery the table or query
     * @throws SQLException the sql exception
     * @throws IOException  the io exception
     * @author : Hu weihui
     * @since hui_project v1
     */
    public static void copyToFile(Connection connection, String filePath, String tableOrQuery) throws SQLException, IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            String sql = "";
            if (tableOrQuery.trim().startsWith("select")){
                sql = String.format("COPY (%s) TO STDOUT ",tableOrQuery);
            }else {
                sql = String.format("COPY %s TO STDOUT ",tableOrQuery);
            }
            CopyManager copyManager = new CopyManager((BaseConnection) connection.getMetaData().getConnection());
            copyManager.copyOut(sql, fileOutputStream);
        }
    }
}
