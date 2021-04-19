package com.company;

import javax.swing.*;
import java.util.ArrayList;

/**
 * A class cosisiting of helper methods
 *
 * @Author Hyungsuk Kim
 */

public class Helper {

    // converts DefaultListModel to ArrayList<String>
    public static ArrayList<String> toArrayList(DefaultListModel model) {
        ArrayList<String> result = new ArrayList<>();

        for (int i=0; i<model.size(); i++) {
            result.add(model.get(i).toString());
        }
        return result;
    }
}
