package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ResetPasswordPage extends JFrame {
    private JLabel newPassword;
    private JLabel confirmPassword;
    private JPasswordField newPasswd;
    private JPasswordField confirmPasswd;
    private JButton resetBtn;

    public ResetPasswordPage(){
        setTitle("Reset Password Page");
        JPanel panel = new JPanel();

        newPassword = new JLabel("New Password: ");
        newPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        newPassword.setBounds(58, 100, 99, 30);
        newPassword.setForeground(Color.CYAN);
        panel.add(newPassword);
        newPasswd = new JPasswordField();
        newPasswd.setFont(new Font("Tahoma", Font.PLAIN, 20));
        newPasswd.setBounds(200, 100, 228, 50);
        newPasswd.setColumns(10);
        panel.add(newPasswd);


        //Last Name Input
        confirmPassword = new JLabel("Confirmed Password: ");
        confirmPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        confirmPassword.setBounds(58, 200, 110, 30);
        confirmPassword.setForeground(Color.CYAN);
        panel.add(confirmPassword);
        confirmPasswd = new JPasswordField();
        confirmPasswd.setFont(new Font("Tahoma", Font.PLAIN, 20));
        confirmPasswd.setBounds(400, 200, 228, 50);
        confirmPasswd.setColumns(10);
        panel.add(confirmPasswd);


        resetBtn = new JButton("Reset");
        panel.add(resetBtn);

        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setBackground(Color.BLACK);
        add(panel);
        setBounds(450, 190, 800, 300);
        setLayout(new GridBagLayout());
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        new ResetPasswordPage();
    }
}
