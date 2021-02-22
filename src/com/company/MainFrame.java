package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public MainFrame(String title) {
        super(title);

        setLayout(new BorderLayout());

        final JTextArea textArea = new JTextArea();
        JButton button = new JButton("button");

        Container c = getContentPane();

        c.add(textArea, BorderLayout.CENTER);
        c.add(button, BorderLayout.SOUTH);

        button.addActionListener(new ActionListener() {
            //Button clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append("Hi\n");
            }
        });
    }
}
