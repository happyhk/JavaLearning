package main.java.com.up.student.dao;

import main.java.com.up.student.DAO;
import main.java.com.up.student.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by CodingJiu on 2021/4/6 14:57
 */
public class BaseDAO {
    protected  final DBUtil db = DBUtil.getDBUtil();
    protected ResultSet rs;
    private static BaseDAO baseDAO;

    public BaseDAO() {
        init();
    }

    private void init() {

    }

    public static synchronized BaseDAO getAbilityDAO(DAO dao) {
        switch(dao) {
            case AdminDAO:
                if(baseDAO == null || baseDAO.getClass() != AdminDAO.class) {
                    baseDAO = StudentDAO.getInstance();
                }
                break;
            default:
                break;
        }
        return baseDAO;
    }

    protected void destroy() {
        try {
            if(rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
}
