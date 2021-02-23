package com.company;

import java.util.PriorityQueue;

/**
 * to write: description
 *
 * @author Timothy Grashaw
 */
public class Comment implements Comparable<Comment>{
    private String text;
    private String category;
    private int positivity;

    public Comment(){
        // temporary initialization
        text = "";
        category =  "";
        this.positivity = -1;
    }
    public Comment(String txt, String ctgry, int pstvty){
        this.text = txt;
        this.category = ctgry;
        this.positivity = pstvty;
    }

    public String getText() {
        return text;
    }

    public String getCategory() {
        return category;
    }

    public int getIsPostive() {
        return positivity;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setIsPostive(int isPostive) {
        this.positivity = isPostive;
    }

    /**
     * A method to querry the database for comments that match the criteria
     * @param subject the subject of comments that are desired
     * @param positivity the positivity rating of the desired comments
     * @return the resulting list of comments gathered from the querry
     */
    // this method is static for testing purpose
    public static PriorityQueue<Comment> querry(String subject, int positivity){
        PriorityQueue<Comment> list = new PriorityQueue<Comment>();
        //@todo remove these example comments and add connectivity with the database to get actual comments
        list.add(new Comment("Test0", "Computer Science", 0));
        list.add(new Comment("Test1", "Computer Science", 1));
        list.add(new Comment("Test2", "Computer Science", 2));
        return list;
    }

    //@todo implement this
    public int compareTo(Comment cmnt) {
        return this.getText().compareTo(cmnt.getText());
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


}
