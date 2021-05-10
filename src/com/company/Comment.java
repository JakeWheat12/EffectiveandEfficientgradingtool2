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
    public static ArrayList<Comment> query(int v){
        ArrayList<Comment> result = new ArrayList<>();
//        for (int i=0; i<50; i++) {
//            result.add(new Comment("test " + (int)(Math.random()*100 +1)));
//        }
        if (v == 1) {
            result.add(new Comment("Well commented"));
            result.add(new Comment("Could use more comments"));
            result.add(new Comment("No comment use at all"));
            result.add(new Comment("Great job using comments"));
            result.add(new Comment("Well documented"));
            result.add(new Comment("All functions implemented correctly"));
            result.add(new Comment("incorrect use of functions"));
            result.add(new Comment("Somewhat implemented functions"));
            result.add(new Comment("Good variable naming"));
            result.add(new Comment("Okay variable naming"));
            result.add(new Comment("Poor variable naming"));
            result.add(new Comment("Solid testing"));
            result.add(new Comment("Testing attempted"));
            result.add(new Comment("One or two testing"));
            result.add(new Comment("No testing"));
            result.add(new Comment("Confusing"));
            result.add(new Comment("Lacks conclusion"));
            result.add(new Comment("Missing major part of assignment"));
            result.add(new Comment("Great introduction to topic"));
            result.add(new Comment("Topic is too broad"));
            result.add(new Comment("All requirements met"));
        }
        else {
            result.add(new Comment("Excellent job conveying ideas"));
            result.add(new Comment("Great use of interface"));
            result.add(new Comment("Poor organization of ideas"));
            result.add(new Comment("Can't understand the point here"));
            result.add(new Comment("Interesting choice of wording"));
            result.add(new Comment("Lacks the core elements"));
            result.add(new Comment("Solid use of parameters"));
            result.add(new Comment("Unnecessary use of parameters"));
            result.add(new Comment("Variable should be private"));
            result.add(new Comment("Variable should be protected"));
            result.add(new Comment("Lack of details"));
            result.add(new Comment("Needs more details"));
            result.add(new Comment("Confusing use of spacing"));
            result.add(new Comment("Good use of white spacing"));
            result.add(new Comment("Nicely documented"));
            result.add(new Comment("Needs way more description"));
            result.add(new Comment("Could be descriptive"));
            result.add(new Comment("Creative use of loops"));
            result.add(new Comment("Unnecessary loops"));
            result.add(new Comment("Unused imports"));

        }
        return result;

        //@todo will only have this code when {@code Databse.Refresh_database} is implemented
        // return Database.Refresh_database();
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