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
    private static int WIDTH = 800;
    private static int HEIGHT = 800;

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

        label = new JLabel("COMMENT GENERATOR");
        label.setBounds(300, 300, 500, 50);
        JTextField textField = new JTextField("Comment Generator");
        textField.setForeground(Color.CYAN);
        label.setFont(new Font("Tahoma", Font.PLAIN, 30));
        label.setText(textField.getText());
        add(label);

        add(panel);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
