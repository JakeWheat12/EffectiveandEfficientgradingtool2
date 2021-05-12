package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestJmenu {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();

        JMenuBar bar = new JMenuBar();

        JMenu menu = new JMenu("Option");
        JMenuItem item1 = new JMenuItem("Print hello");
        JMenuItem item2 = new JMenuItem("Print world");

        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(":hello");
            }
        });

        menu.add(item1);
        menu.add(item2);

        bar.add(menu);

        jFrame.setJMenuBar(bar);
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
