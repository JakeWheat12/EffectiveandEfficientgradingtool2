package com.company;

import javax.swing.*;
import java.sql.*;

public class Database {

//    static final String JDBC_DRIVER = "";

    // @todo change these to your database settings. I will change my database when I can
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "user";
    private static final String PASSWORD = "1k2k3k4k";

    public static void Refresh_database (DefaultListModel<Comment> list) {
        String comment;

        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

        }
        catch(SQLException se){
        //Handle errors for JDBC
        se.printStackTrace();
        }
        catch(Exception e){
        //Handle errors for Class.forName
        e.printStackTrace();
        }

    }
}
