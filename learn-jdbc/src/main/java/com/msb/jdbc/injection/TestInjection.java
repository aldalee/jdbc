package com.msb.jdbc.injection;

import com.msb.jdbc.entity.Account;
import com.msb.jdbc.util.ConnectMySQL;

import java.sql.*;
import java.util.Scanner;

/**
 * @Auther: Huanyu Lee
 * @Date: 2022/3/6  16:32
 * @Description: SQL注入攻击
 * @version: 1.0
 */
public class TestInjection {
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    public static Account getAccount(String username, String password){     // asdf 和 asdf'or'a'='a 登录成功
        Account account = null;
        try {
            statement = ConnectMySQL.init();
            String sql = "select * from account where username = '" + username + "' and password = '" + password +"'";
            System.out.println("sql = " + sql);
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                final int aid = resultSet.getInt("aid");
                final String user = resultSet.getString("username");
                final int pwd = resultSet.getInt("password");
                final double money = resultSet.getDouble("money");
                account = new Account(aid,user,pwd,money);
                System.out.println(account);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectMySQL.close(statement,resultSet);
        }
        return account;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入用户名：");
        final String username = sc.next();
        System.out.print("请输入密码：");
        final String password = sc.next();
        final Account account = getAccount(username, password);
        System.out.println(account == null ? "登录失败！": "登录成功！");
        sc.close();
    }
}
