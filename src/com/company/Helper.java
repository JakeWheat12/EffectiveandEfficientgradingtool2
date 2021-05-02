package com.company;

import javax.swing.*;
import java.util.ArrayList;

/**
 * A class cosisiting of helper methods
 */

public class Helper {

    /**
     * @Author Hyungsuk Kim
     * converts DefaultListModel to ArrayList<String>
     *
     * @param model DefaultListModel from SelectedCommentField JList
     * @return ArrayList of Strings
     */
    public static ArrayList<String> toArrayList(DefaultListModel model) {
        ArrayList<String> result = new ArrayList<>();

        for (int i=0; i<model.size(); i++) {
            result.add(model.get(i).toString());
        }
        return result;
    }
}
