package com.company;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Database {

//    static final String JDBC_DRIVER = "";

    // @todo change these to your database settings. I will change my database when I can
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "user";
    private static final String PASSWORD = "1k2k3k4k";

    public static ArrayList<Comment> Refresh_database () {
        Connection connection = null;
        Statement s = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            s = connection.createStatement();
            rs = s.executeQuery("SELECT commentText FROM comment ORDER BY RAND() LIMIT 25;");

            while (rs.next()) {
                list.add(rs.getString("commentText"));
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
                se.printStackTrace();
            } catch (Exception e) {
                //Handle errors for Class.forName
                e.printStackTrace();
            }

            // temp code
            return new ArrayList<>();
        }
    }
    //The following methods attempt to connect to the database using JDBC and run a query given different criteria
    //The username and password fields are the username and password needed to connect to the database using JDBC when not using windows authorization
    public static ArrayList getCommentListByCategory(String category){
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            s = conn.createStatement();
            rs = s.executeQuery("SELECT TOP 25 commentText, lineNumber FROM comment WHERE schoolSubject = " + category + " ORDER BY popularity;");
            while(rs.next()) {
                list.add(rs.getString("commentText"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(conn!=null)
                    conn.close();
                if(s!=null)
                    s.close();
                if(rs!=null)
                    rs.close();
            }
            catch(SQLException e){
                e.printStackTrace();
                return null;
            }
        }

        return list;
    }
    /** A method to retrieve comments of a certain positivity value and category form the database
     *
     * @return ArrayList, a container for the comments that result from the query
     */
    public static ArrayList getCommentListByPositivity(int positivity){
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            s = conn.createStatement();
            rs = s.executeQuery("SELECT TOP 25 commentText, lineNumber FROM comment WHERE positivity = " +  positivity + " ORDER BY popularity;");

            while(rs.next()) {
                list.add(rs.getString("commentText"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(conn!=null)
                    conn.close();
                if(s!=null)
                    s.close();
                if(rs!=null)
                    rs.close();
            }
            catch(SQLException e){
                e.printStackTrace();
                return null;
            }
        }

        return list;
    }
    public static ArrayList getCommentListByBoth(String category, int positivity){
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            s = conn.createStatement();
            rs = s.executeQuery("SELECT TOP 25 commentText, lineNumber FROM comment WHERE positivity = " +  positivity + " AND schoolSubject = " + category + " ORDER BY popularity;");

            while(rs.next()) {
                list.add(rs.getString("commentText"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(conn!=null)
                    conn.close();
                if(s!=null)
                    s.close();
                if(rs!=null)
                    rs.close();
            }
            catch(SQLException e){
                e.printStackTrace();
                return null;
            }
        }

        return list;
    }
 /*@todo make a way to upload user comments to the database in the GUI
    public static void submitComment(Comment comment){
        Connection conn = null;
        Statement s = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            s = conn.createStatement();
            //@todo make sure the sql insert statement is compatible with the database
            s.executeQuery("INSERT INTO comment" +
                    "VALUES ('"+comment.getText()+"', '"+comment.getCategory()+"', 0, GETDATE(), '"+ USER +"', "+comment.getPositivity()+", 0)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(conn!=null)
                    conn.close();
                if(s!=null)
                    s.close();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
  */
}


