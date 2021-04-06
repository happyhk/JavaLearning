package main.java.com.up.student.util;

import main.java.com.up.student.AppConstants;

import java.sql.*;

/**
 * Created by CodingJiu on 2021/4/6 11:57
 */

public class DBUtil {

    private static DBUtil db;
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public DBUtil() {

    }

    public static DBUtil getDBUtil() {
        if(db == null) {
            db = new DBUtil();
        }
        return db;
    }

    public int executeUpdate(String sql) {
        int result = -1;
        if(getConn() == null) {
            return result;
        }

        try {
            ps = conn.prepareStatement(sql);
            result = ps.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int executeUpdate(String sql, Object[] obj) {
        int result = -1;
        if (getConn() == null) {
            return result;
        }
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            result = ps.executeUpdate();
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public ResultSet executeQuery(String sql) {
        if(getConn() == null) {
            return null;
        }
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet executeQuery(String sql, Object[] obj) {
        if(getConn() == null) {
            return null;
        }
        try {
            ps = conn.prepareStatement(sql);
            for(int i = 0; i < obj.length; i++) {
                ps.setObject(i+1, obj[i]);
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    private Connection getConn() {
        try {
            if(conn == null || conn.isClosed()) {
                Class.forName(AppConstants.JDBC_DRIVER);
                conn = DriverManager.getConnection(AppConstants.JDBC_URL, AppConstants.JDBC_USERNAME,
                        AppConstants.JDBC_PASSWORD);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("jdbc driver is not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void close() {
        try {
            if(rs != null) {
                rs.close();
            }
            if(ps != null) {
                ps.close();
            }

            if(conn != null) {
                conn.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean exeute(String sql) {
        if (getConn() == null) {
            return false;
        }
        try {
            Statement statement = conn.createStatement();
            statement.execute(sql);
            statement.close();
            return true;
        } catch (SQLException e) {
//			e.printStackTrace();
            return false;
        }
    }
}
