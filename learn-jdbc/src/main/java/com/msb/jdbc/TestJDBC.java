package com.msb.jdbc;

import java.sql.*;

/**
 * @Auther: Huanyu Lee
 * @Date: 2022/3/6  10:32
 * @Description: 向Dept表增加数据
 * @version: 1.0
 */
public class TestJDBC {
    public static void main(String[] args) throws SQLException {
        // 加载驱动 Driver
        Driver driver = new com.mysql.cj.jdbc.Driver();
        // 注册驱动 DriverManager
        DriverManager.registerDriver(driver);
        /*
        获得连接 Connection
        url: 统一资源定位符，定位要连接的数据库
            1. 协议 jdbc:mysql
            2. IP 127.0.0.1 / localhost
            3. 端口号 3306
            4. 数据库名称 mydb
            5. 参数
        user: root
        password: root
         */
        final Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC", "root", "root");
        // 获得语句对象 Statement
        final Statement statement = conn.createStatement();
        /*
        执行SQL语句返回结果
        insert delete update 操作调用 statement.executeUpdate() 返回int值，代表数据库多少行数据发生了变化
        select 操作调用 statement.executeQuery()
         */
        String sql = "insert into dept values(default,'教学部','北京')";
        final int rows = statement.executeUpdate(sql);
        System.out.println("rows = " + rows);
        // 释放资源    顺序: 先得后关
        statement.close();
        conn.close();
    }
}
