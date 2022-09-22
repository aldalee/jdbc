package com.msb.jdbc.batch;

import com.msb.jdbc.util.ConnectMySQL;

import java.sql.*;
import java.util.LinkedList;

/**
 * @Auther: Huanyu Lee
 * @Date: 2022/3/6  22:53
 * @Description: 批处理 设置回滚点
 * @version: 2.0
 */
public class TestBatch {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/mydb?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useServerPrepStmts=true&cachePrepStmts=true&&rewriteBatchedStatements=true";
    private static final String user = "root";
    public static final String password = "root";

    /*
    批处理添加1000条数据
    return 数组中的元素代表执行结果的代号
    SUCCESS_NO_INFO = -2
    EXECUTE_FAILED = -3
    需要设置批处理开启 &rewriteBatchedStatements=true
     */
    public static void addBatch() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        LinkedList<Savepoint> savepoints = new LinkedList<>();
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
            conn.setAutoCommit(false);  // 手动设置事务
            String sql = "insert into dept values(default,?,?)";
            preparedStatement = ConnectMySQL.init(sql);
            for (int i = 0; i < 100000; i++) {
                preparedStatement.setString(1,"Te");
                preparedStatement.setString(2,"Beijing");
                preparedStatement.addBatch();   // 将修改放在一个批次中
                if (i % 1000 == 0){
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();     // 清除批处理中数据
                    final Savepoint savepoint = conn.setSavepoint();
                    savepoints.addLast(savepoint);
                }
                // 数据在 100001条插入的时候出现异常
                if(i == 1001){
                    int x =1/0;
                }
            }
            preparedStatement.executeBatch();
            preparedStatement.clearBatch();
        } catch (Exception e) {
            // 回滚事务
            if (conn != null) {
                try {
                    final Savepoint sp = savepoints.getLast();  // 选择回滚点
                    if (sp != null){
                        conn.rollback(sp);  // 回滚
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.commit();  // 提交事务
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
        addBatch();
    }
}
