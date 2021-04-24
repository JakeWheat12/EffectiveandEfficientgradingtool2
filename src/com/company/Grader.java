package com.company;

/**
 * @todo: description
 *
 * @Author Hyungsuk Kim (we should add our names only if we touch this file?
 *          I don't know how author really works)
 */

// @todo discuss basic overall Class design
public class Grader {

    private String name; // name of Grader
    private Comment cmnt; // Comment variable
    /**
     * Constructor instantiates a Grader with its name
     * @param name
     */
    public Grader(String name){
        this.name = name; // need to talk to the database to confirm if the user is rightful
        this.cmnt = new Comment();
    }

    /**
     * Getter for name
     * @return name
     */
    public String getName() {
        return name;
    }

}