package com.company;

import java.sql.*;
import java.util.ArrayList;

/**
 * Comment is an object that will store comments as String
 * @author Hyungsuk Kim, Carter Du
 */
public class Comment {
    private String text;

    public Comment() {
        text = "";
    }

    public Comment(String txt) {
        text = txt;
    }

    public String getText() {
        return text;
    }

    /**
     * A method to query the database for comments that match the criteria
     * Generate random comments from database
     * @return the resulting list of comments gathered from the database
     */
    public static ArrayList<Comment> generateRandom(){
        ArrayList<Comment> result = new ArrayList<>();
//        for (int i=0; i<50; i++) {
//            result.add(new Comment("test " + (int)(Math.random()*100 +1)));
//        }
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Test", "root", "dlx990330");
            //assuming good comment 1, bad comment 0
            String query = "SELECT * FROM Test.Text ORDER BY RAND() LIMIT 20";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String content = resultSet.getString("Content");
                result.add(new Comment(content));
                System.out.println(new Comment(content));
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * A method to query the database for comments that match the criteria
     * Generate NEGATIVE comments from database
     * @return the resulting list of comments gathered from the database
     */
    public static ArrayList<Comment> generateNegative(){
        ArrayList<Comment> result = new ArrayList<>();
//        for (int i=0; i<50; i++) {
//            result.add(new Comment("test " + (int)(Math.random()*100 +1)));
//        }
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Test", "root", "dlx990330");
            //assuming good comment 1, bad comment 0
            String query = "SELECT * FROM Test.Text WHERE CATEGORY=0 ORDER BY RAND() LIMIT 15";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String content = resultSet.getString("Content");
                result.add(new Comment(content));
                System.out.println(new Comment(content));
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * A method to query the database for comments that match the criteria
     * Generate POSITIVE comments from database
     * @return the resulting list of comments gathered from the database
     */
    public static ArrayList<Comment> generatePositive(){
        ArrayList<Comment> result = new ArrayList<>();
//        for (int i=0; i<50; i++) {
//            result.add(new Comment("test " + (int)(Math.random()*100 +1)));
//        }
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Test", "root", "dlx990330");
            //assuming good comment 1, bad comment 0
            String query = "SELECT * FROM Test.Text WHERE CATEGORY=1 ORDER BY RAND() LIMIT 15";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String content = resultSet.getString("Content");
                result.add(new Comment(content));
                System.out.println(new Comment(content));
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


    /**
     * A method for adding new comments to the database
     * @param com the comment to be added to the database
     */
    public void insert(Comment com){
        //@todo implement method
    }

    public String toString() {
        return this.text;
    }
}