package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Carter Du
 * The main page
 */
public class WelcomePage extends JFrame {
    private JButton signUpBtn;
    private JButton loginBtn;
    private JLabel label;
    private static int WIDTH = 600;
    private static int HEIGHT = 600;

    public WelcomePage(String title){
        setTitle(title);
        setSize(WIDTH, HEIGHT);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        //open the userRegisterPage
        signUpBtn = new JButton("Sign Up");
        signUpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserRegisterPage();
            }
        });

        //open the userLoginPage
        loginBtn = new JButton("Login");
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserLoginPage();
            }
        });

        panel.add(loginBtn);
        panel.add(signUpBtn);
        panel.setBackground(Color.BLACK);

        label = new JLabel("COMMENT GENERATOR");
        //label.setBounds(300, 300, 500, 50);
        label.setBounds(220,220,200,100);
        JTextField textField = new JTextField("Comment Generator");
        label.setFont(new Font("Serif Bold Italic", Font.PLAIN, 20));

        label.setForeground(Color.CYAN);
        label.setText(textField.getText());
        add(label);


        add(panel);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
