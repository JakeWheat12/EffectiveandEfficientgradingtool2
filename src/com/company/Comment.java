package com.company;

import java.util.ArrayList;

/**
 * Comment is an object that will store comments as String
 * @author Hyungsuk Kim
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
     * @return the resulting list of comments gathered from the database
     */
    //@todo implement JDBC connection
    public static ArrayList<Comment> query(){
        ArrayList<Comment> result = new ArrayList<>();

        for (int i=0; i<50; i++) {
            result.add(new Comment("test " + i));
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