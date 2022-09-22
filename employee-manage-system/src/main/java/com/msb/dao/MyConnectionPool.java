package com.msb.dao;

import com.msb.util.PropertiesUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;

/**
 * 连接池
 * @author HuanyuLee
 * @date 2022/3/9
 */
public class MyConnectionPool {
    private static final String DRIVER;
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;
    private static final int INIT_SIZE;
    private static final int MAX_SIZE;

    private static final Logger logger;
    private static final LinkedList<Connection> POOL;

    static {
        logger = Logger.getLogger(MyConnectionPool.class);
        PropertiesUtil pu = new PropertiesUtil("/jdbc.properties");
        DRIVER = pu.getProperties("driver");
        URL = pu.getProperties("url");
        USER = pu.getProperties("user");
        PASSWORD = pu.getProperties("password");
        INIT_SIZE = Integer.parseInt(pu.getProperties("initSize"));
        MAX_SIZE = Integer.parseInt(pu.getProperties("maxSize"));
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            logger.fatal("找不到数据库驱动类 " + DRIVER,e);
        }
        POOL = new LinkedList<>();
        for (int i = 0; i < INIT_SIZE; i++) {
            final Connection connection = init();
            if (connection != null) {
                POOL.add(connection);
                logger.info("初始化连接 " + connection.hashCode() + " 放入连接池......");
            }
        }
    }

    private static Connection init(){
        try {
            return DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            logger.fatal("初始化连接异常 ",e);
        }
        return null;
    }

    public static Connection getConnection(){
        Connection connection;
        if (POOL.size() > 0){
            connection = POOL.removeFirst();
            logger.info("连接池中还有连接: " + connection.hashCode());
        }else {
            connection = init();
            assert connection != null;
            logger.info("连接池空，创建新连接: " + connection.hashCode());
        }
        return connection;
    }
    
    public static void returnConnection(Connection connection){
        if (connection != null){
            try {
                if (!connection.isClosed()){
                    if (POOL.size() < MAX_SIZE){
                        try {
                            // 调整事务状态
                            connection.setAutoCommit(true);
                            logger.debug("设置连接 " + connection.hashCode() +" 自动提交为true！");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        POOL.addLast(connection);
                        logger.info("连接池未满，归还: " + connection.hashCode());
                    }else {
                        try {
                            connection.close();
                            logger.info("连接池已满，关闭连接: " + connection.hashCode());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    logger.info("连接 " + connection.hashCode() +" 已关闭，无需归还！");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            logger.warn("传入的连接为 null，不可归还！");
        }
    }

    public static void closeStatement(PreparedStatement preparedStatement){
        if (preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void closeResultSet(ResultSet resultSet){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
