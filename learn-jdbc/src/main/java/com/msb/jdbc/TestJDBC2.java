package com.msb.jdbc;

import com.msb.jdbc.entity.Emp;
import com.msb.jdbc.util.ConnectMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Huanyu Lee
 * @Date: 2022/3/6  11:26
 * @Description: 通过反射加载驱动、处理异常、重构代码
 * @version: 2.0
 */
public class TestJDBC2 {
    private static Statement statement = null;
    private static ResultSet resultSet = null;


    public static void add() throws SQLException {
        String sql = "insert into dept values(50,'教学部','北京')";
        statement.executeUpdate(sql);
    }

    public static void delete() throws SQLException {
        String sql = "delete from dept where deptno = 60";
        statement.executeUpdate(sql);
    }

    public static void update() throws SQLException {
        String sql = "update dept set dname = '总部',loc = '河南' where deptno = 50";
        statement.executeUpdate(sql);
    }

    public static List<Emp> query() throws SQLException {
        String sql = "select * from emp";
        resultSet = statement.executeQuery(sql);
        List<Emp> list = new ArrayList<>();
        while (resultSet.next()){
            final int empno = resultSet.getInt("empno");
            final String ename = resultSet.getString("ename");
            final String job = resultSet.getString("job");
            final int mgr = resultSet.getInt("mgr");
            final Date hiredate = resultSet.getDate("hiredate");
            final double sal = resultSet.getDouble("sal");
            final double comm = resultSet.getDouble("comm");
            final int deptno = resultSet.getInt("deptno");
            Emp emp = new Emp(empno,ename,job,mgr,hiredate,sal,comm,deptno);
            list.add(emp);      // 保存查询的结果，供其他类使用
        }
        return list;
    }

    public static void main(String[] args){
        try {
            statement = ConnectMySQL.init();
            add();
            update();
            delete();
            List<Emp> list = query();
            // 遍历集合
            for (Emp emp : list){
                System.out.println(emp);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectMySQL.close(statement,resultSet);
        }
    }
}
