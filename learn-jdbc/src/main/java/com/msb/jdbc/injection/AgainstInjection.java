package com.msb.jdbc.injection;

import com.msb.jdbc.entity.Account;
import com.msb.jdbc.util.ConnectMySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @Auther: Huanyu Lee
 * @Date: 2022/3/6  18:20
 * @Description: 防止SQL注入攻击
 * @version: 1.0
 */
public class AgainstInjection {
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    public static Account getAccount(String username, String password){
        Account account = null;
        try {
            String sql = "select * from account where username = ? and password = ?";
            preparedStatement = ConnectMySQL.init(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
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
            ConnectMySQL.close(preparedStatement,resultSet);
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
