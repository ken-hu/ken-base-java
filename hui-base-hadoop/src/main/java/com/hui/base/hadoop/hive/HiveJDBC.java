package com.hui.base.hadoop.hive;

import java.sql.*;

/**
 * <b><code>HiveJDBC</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2018/11/19 22:08.
 *
 * @author Hu Weihui
 */
public class HiveJDBC {

    /**
     * The constant driverName.
     *
     * @since hui_project 1.0.0
     */
    private static final String driverName ="org.apache.hive.jdbc.HiveDriver";
    /**
     * The constant url.
     *
     * @since hui_project 1.0.0
     */
    private static final String url="jdbc:hive2://192.168.31.60:10000/default";

    /**
     * application.
     *
     * @param args the input arguments
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     * @since hui_project 1.0.0
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName(driverName);
        Connection connection = DriverManager.getConnection(url, "root", "123456");
        Statement statement = connection.createStatement();
        statement.execute("CREATE DATABASE d_huweihui");
        connection.close();
    }


    /**
     * 创建数据库.
     *
     * @param statement the statement
     * @throws SQLException the sql exception
     * @since hui_project 1.0.0
     */
    public void createDataBases(Statement statement) throws SQLException {
        String sql = "CREATE DATABASE d_huweihui";

        statement.execute(sql);
    }

    /**
     * 查看数据库.
     *
     * @param statement the statement
     * @throws SQLException the sql exception
     * @since hui_project 1.0.0
     */
    public void showDataBases(Statement statement) throws SQLException {
        String sql = "show databases";

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
        }
    }

}
