package com.tomcatservlet.jdbcutil;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {
    private static DataSource dataSource;
    static {
        Properties druidConfig = new Properties();
        InputStream ril = JdbcUtil.class.getClassLoader().getResourceAsStream("druid-config.properties");
        try {
            druidConfig.load(ril);
            dataSource = DruidDataSourceFactory.createDataSource(druidConfig);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ril.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void close(Connection conn, Statement st, ResultSet rs) throws SQLException {
        if(rs != null){
            rs.close();
        }
        if(st != null){
            st.close();
        }
        if(conn != null){
            conn.close();
        }
    }

    public static void close(Connection conn, Statement st) throws SQLException {
        close(conn,st,null);
    }

}
