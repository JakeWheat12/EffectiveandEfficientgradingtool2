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
     * @param model DefaultListModel from SelectedCommentField JList
     * @return ArrayList of String
     */
    public static ArrayList<String> toArrayList(DefaultListModel model) {
        ArrayList<String> result = new ArrayList<>();

        for (int i=0; i<model.size(); i++) {
            result.add(model.get(i).toString());
        }
        return result;
    }

//    /**
//     * @Author Hyungsuk Kim
//     * converts Comments to String
//     * @param list ArrayList of Comments
//     * @return ArrayList of String
//     */
//    public static ArrayList<String> ConvertComment(ArrayList<Comment> list) {
//        ArrayList<String> result = new ArrayList<>();
//
//        for (Comment cmnt : list) {
//            result.add(cmnt.getText());
//        }
//        return result;
//    }

    /**
     *
     * @param list
     * @return
     */
    public static void AddToDefaultList(ArrayList<Comment> list, DefaultListModel<Comment> dList) {
        dList.clear();
        for (Comment cmnt : list) {
            dList.addElement(cmnt);
        }
    }

    /**
     * @Author Hyungsuk Kim
     *
     * removes "\n" {new lines} from
     *
     * @param list an ArayList to be modified
     * @return a new ArrayList that contains Strings without "\n"
     */
    public static ArrayList<String> RemoveNewLine(ArrayList<String> list) {
        ArrayList<String> result = new ArrayList<>();
        for (String str : list) {
            result.add(str.replace("\n",""));
        }
        return result;
    }
}
