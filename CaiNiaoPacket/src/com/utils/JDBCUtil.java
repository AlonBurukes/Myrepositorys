package com.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * 建立连接 关闭连接
 *
 */
public class JDBCUtil {

    //获得数据源
    private static ComboPooledDataSource cpds = new ComboPooledDataSource();

    public  static DataSource getDataSource(){
        return cpds;
    }
    public static Connection getConnection(){
        try {
            return cpds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void closeConnection (Connection conn){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
