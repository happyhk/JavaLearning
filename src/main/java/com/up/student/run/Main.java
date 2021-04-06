package main.java.com.up.student.run;

import main.java.com.up.student.util.DBUtil;
import main.java.com.up.student.view.LoginView;

/**
 * Created by CodingJiu on 2021/4/6 21:36
 */
public class Main {
    public static void initDB() {
        DBUtil dbUtil = DBUtil.getDBUtil();

        //检查数据库是否初始化
        if (dbUtil.exeute("select 1 from  admin")) {
            return;
        }

        //初始化数据库
        //admin表
        dbUtil.exeute("create table if not exists admin(id int primary key," +
                "name varchar(32)," +
                "username varchar(32)," +
                "password varchar(32))");
        dbUtil.exeute("insert into admin(id, name, username, password) values(1, 'admin', 'test', 'test')");

        //student
        dbUtil.exeute("create table if not exists student(" +
                "id int primary key," +
                "sno varchar(16)," +
                "name varchar(32)," +
                "sex varchar(8)," +
                "department varchar(32)," +
                "hometown varchar(64)," +
                "mark varchar(32)," +
                "email varchar(32)," +
                "tel varchar(16))");
    }

    public static void main(String[] args) {
        initDB();
        new LoginView();
        DBUtil.getDBUtil().close();
    }
}
