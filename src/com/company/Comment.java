package com.company;

import java.sql.*;
import java.util.ArrayList;

/**
 * Comment is an object that will store comments as String
 * @author Hyungsuk Kim
 */
public class Comment {
    private String text, category;
    private int positivity;

    public Comment() {
        this.text = "";
    }
    public Comment(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {this.text = text;}

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {this.category = category;}

    public int getPositivity(){ return positivity;}
    public void setPositivity(int positivity){ this.positivity = positivity;}


    /**
     * A method to query the database for comments that match the criteria
     * @return the resulting list of comments gathered from the database
     */
    //@todo implement JDBC connection
    public static ArrayList<Comment> query(){
        return Database.Refresh_database();
    }
    public static ArrayList<Comment> query(String category, String positivity){
       /* ArrayList<Comment> result = new ArrayList<>();
        for (int i=0; i<50; i++) {
            result.add(new Comment("test " + (int)(Math.random()*100 +1)));
        }
        return result;
        */
        //@todo will only have this code when {@code Databse.Refresh_database} is implemented
        if(category == "" && positivity == "")
         return Database.Refresh_database();
        else if(category == "")
            return Database.getCommentListByPositivity(Integer.parseInt(positivity));
        else if(positivity == "")
            return Database.getCommentListByCategory(category);
        else
            return Database.getCommentListByBoth(category, Integer.parseInt(positivity));

    }

    /**
     * A method for adding new comments to the database
     * @param com the comment to be added to the database
     */
    public void insert(Comment com){
        //@todo implement method
    }

    /**
     * A method to update the popularity of a given comment in the database
     * @param increase the amount of times the given comment has been used during the grading session
     */
    public void updatePopularity(int increase){
        //@todo implement method
    }

    /** A method to return content of Comment
     *
     * @return
     */
    @Override
    public String toString() {
        return this.text;
    }

}