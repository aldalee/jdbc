package com.msb.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 基本SQL语句抽象
 * @author HuanyuLee
 * @date 2022/3/9  9:21
 */
public abstract class BaseDao {
    /**
     * 向表中增加、删除、修改一条数据
     * @param sql SQL语句
     * @param args 传入的参数
     * @return int
     */
    public int baseUpdate(String sql, Object ... args){
        Connection connection = MyConnectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        int rows = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            // 设置参数
            for (int i = 1; i <= args.length; i++) {
                preparedStatement.setObject(i,args[i-1]);
            }
            rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MyConnectionPool.closeStatement(preparedStatement);
            MyConnectionPool.returnConnection(connection);
        }
        return rows;
    }

    /**
     * 查询表中数据
     * @param clazz 实体类的字节码文件
     * @param sql SQL语句
     * @param args 传入的参数
     * @return java.util.List
     */
    public List baseQuery(Class clazz, String sql, Object ... args){
        Connection connection = MyConnectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List list = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            // 设置参数
            for (int i = 1; i <= args.length; i++) {
                preparedStatement.setObject(i,args[i-1]);
            }
            resultSet = preparedStatement.executeQuery();
            list = new ArrayList();
            // 根据字节码获取所有 的属性
            final Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                // 设置属性可以访问
                field.setAccessible(true);
            }
            while (resultSet.next()){
                // 默认在通过反射调用对象的空参构造方法
                final Object obj = clazz.newInstance();
                for (Field field : fields){
                    final String name = field.getName();
                    final Object data = resultSet.getObject(name);
                    field.set(obj,data);
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyConnectionPool.closeResultSet(resultSet);
            MyConnectionPool.closeStatement(preparedStatement);
            MyConnectionPool.returnConnection(connection);
        }

        return list;
    }
}
