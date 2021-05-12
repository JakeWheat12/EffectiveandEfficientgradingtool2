package com.company;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Database {

//    static final String JDBC_DRIVER = "";

    //@todo find a way to make this jdbc stuff not user specifc?
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "user";
    private static final String PASSWORD = "1k2k3k4k";

    /*TIM G*/
    //private static final String DB_URL = "jdbc:mysql://localhost:3306/e&e_gradingtool";
    //private static final String USER = "TimG";

    /*GENERIC*/
    //private static final String DB_URL = "";
    //private static final String USER = "";
    //private static final String PASSWORD = "";


    public static ArrayList<Comment> Refresh_database() {
        Connection connection = null;
        Statement s = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            s = connection.createStatement();
            rs = s.executeQuery("SELECT Comment_text FROM comment ORDER BY RAND() LIMIT 25;");

            while (rs.next()) {
                list.add(new Comment(rs.getString("Comment_text")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
                if (s != null)
                    s.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException se) {
                //Handle errors for JDBC
                //JOptionPane.showMessageDialog(null, "SQL Error","Warning", JOptionPane.ERROR_MESSAGE);
                se.printStackTrace();
            } catch (Exception e) {
                //Handle errors for Class.forName
                e.printStackTrace();
            }

            return list;
        }
    }

    //The following methods attempt to connect to the database using JDBC and run a query given different criteria
    //The username and password fields are the username and password needed to connect to the database using JDBC when not using windows authorization
    public static ArrayList getCommentListByCategory(String category) {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            s = conn.createStatement();
            rs = s.executeQuery("SELECT Comment_text FROM comment WHERE category like '" + category + "' ORDER BY popularity LIMIT 25;");
            while (rs.next()) {
                list.add(new Comment(rs.getString("Comment_text")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
                if (s != null)
                    s.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        }
    }

    /**
     * A method to retrieve comments of a certain positivity value and category form the database
     *
     * @return ArrayList, a container for the comments that result from the query
     */
    public static ArrayList getCommentListByPositivity(int positivity) {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            s = conn.createStatement();
            rs = s.executeQuery("SELECT Comment_text FROM comment WHERE positivity = " + positivity + " ORDER BY popularity LIMIT 50;");
            while (rs.next()) {
                list.add(new Comment(rs.getString("Comment_text")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
                if (s != null)
                    s.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        }
    }

    //@todo fix this method. Currently returns an empty set.
    public static ArrayList getCommentListByBoth(String category, int positivity) {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            s = conn.createStatement();
            rs = s.executeQuery("SELECT Comment_text FROM comment WHERE positivity = " + positivity + " AND category like '" + category + "' ORDER BY popularity LIMIT 50;");
            while (rs.next()) {
                list.add(new Comment(rs.getString("Comment_text")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
                if (s != null)
                    s.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        }
    }

}