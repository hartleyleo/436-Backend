package com.example.demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static Connection conn = null;
    public static Connection getConnection() throws SQLException{
        if(conn!=null){
            return conn;
        }
        else{
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/shop_app?useSSL=false";
            String user = "root";
            String password = "Groupstudy174#";
            try{
                Class.forName(driver);
                conn=DriverManager.getConnection(url, user, password);
            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
            }
            return conn;
        }
    }
}
