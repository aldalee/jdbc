package com.msb.jdbc.transaction;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Auther: Huanyu Lee
 * @Date: 2022/3/8  20:06
 * @Description: 事务
 * 在逻辑上一组不可分割的操作,由多个sql语句组成,多个sql语句要么全都执行成功,要么都不执行。
 * 原子性 一致性  隔离性 持久性
 * @version: 1.0
 */
public class TestTransaction {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/mydb?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useServerPrepStmts=true&cachePrepStmts=true&&rewriteBatchedStatements=true";
    private static final String user = "root";
    public static final String password = "root";

    /**
    * @param: account表
     * Tom 向 Jack转账 100
     *
     * JDBC 默认是自动提交事务的
     * 每条DML都是默认提交事务的,多个preparedStatement.executeUpdate();都会提交一次事务
     * 通过Connection对象控制connection.setAutoCommit(false);来手动控制事务
     * 无论是否发生回滚,事务最终都要提交 建议放在finally之中进行提交
     * 如果转账的过程中出现异常,那么我就要执行回滚,回滚操作应该方法catch语句块中
    */
    public static void transferAccounts(){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
            conn.setAutoCommit(false);  // 设置事务手动提交
            String sql = "update account set money = money - ? where aid = ?";
            preparedStatement = conn.prepareStatement(sql);
            // 转出
            preparedStatement.setDouble(1,100);
            preparedStatement.setInt(2,1);
            preparedStatement.executeUpdate();

            /*
            模拟转账过程中程序出现异常
             */
            int error = 1/0;

            // 转入
            preparedStatement.setDouble(1,-100);
            preparedStatement.setInt(2,2);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            // 回滚事务
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            // 提交事务
            if (conn != null) {
                try {
                    conn.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
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
    }

    public static void main(String[] args) {
        transferAccounts();
    }
}
