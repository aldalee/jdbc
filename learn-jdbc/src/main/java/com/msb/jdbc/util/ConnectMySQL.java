package com.msb.jdbc.util;

import java.sql.*;

/**
 * @Auther: Huanyu Lee
 * @Date: 2022/3/6  16:45
 * @Description: 连接MySQL、关闭资源
 * @version: 1.0
 */
public class ConnectMySQL {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/mydb?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&rewriteBatchedStatements=true";
    private static final String user = "root";
    private static final String password = "root";
    private static Connection conn = null;

    public static Statement init() throws ClassNotFoundException, SQLException {
        // 通过反射加载驱动 Driver 并自动注册驱动 DriverManager
        Class.forName(driver);
        conn = DriverManager.getConnection(url, user, password);
        return conn.createStatement();
    }
    public static PreparedStatement init(String sql) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        conn = DriverManager.getConnection(url, user, password);
        return conn.prepareStatement(sql);
    }
    public static void close(Statement statement){
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(Statement statement,ResultSet resultSet){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close(statement);
    }
}
