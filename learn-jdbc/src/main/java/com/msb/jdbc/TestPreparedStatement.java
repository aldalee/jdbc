package com.msb.jdbc;

import com.msb.jdbc.entity.Emp;
import com.msb.jdbc.util.ConnectMySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Huanyu Lee
 * @Date: 2022/3/6  22:56
 * @Description: PreparedStatement实现CURD
 * @version: 1.0
 */
public class TestPreparedStatement {
    private static PreparedStatement preparedStatement = null;

    public static void add() throws SQLException, ClassNotFoundException {
        String sql = "insert into emp values(default,?,?,?,?,?,?,?)";
        preparedStatement = ConnectMySQL.init(sql);
        preparedStatement.setString(1,"Mark");
        preparedStatement.setString(2,"Manager");
        preparedStatement.setInt(3,7839);
        preparedStatement.setDate(4,new Date(System.currentTimeMillis()));
        preparedStatement.setDouble(5,3000.12);
        preparedStatement.setDouble(6,0.0);
        preparedStatement.setDouble(7,30);
        preparedStatement.executeUpdate();
    }
    public static void delete() throws SQLException, ClassNotFoundException {
        String sql = "delete from emp where empno = ?";
        preparedStatement = ConnectMySQL.init(sql);
        preparedStatement.setInt(1,7935);
        preparedStatement.executeUpdate();
    }
    public static void update() throws SQLException, ClassNotFoundException {
        String sql = "update emp set ename = ? where empno = ?";
        preparedStatement = ConnectMySQL.init(sql);
        preparedStatement.setString(1,"Mark");
        preparedStatement.setInt(2,7934);
        preparedStatement.executeUpdate();
    }
    public static List<Emp> query() throws SQLException, ClassNotFoundException {
        String sql = "select * from emp where ename like ? or ?";
        preparedStatement = ConnectMySQL.init(sql);
        preparedStatement.setString(1,"%a%");
        preparedStatement.setString(2,"%m%");
        final ResultSet resultSet = preparedStatement.executeQuery();
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

    public static void main(String[] args) {
        try {
//            add();
//            delete();
            update();
//            final List<Emp> list = query();
//            list.forEach(System.out::println);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectMySQL.close(preparedStatement);
        }
    }
}
