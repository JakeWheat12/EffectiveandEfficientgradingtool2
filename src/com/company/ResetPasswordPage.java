package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ResetPasswordPage extends JFrame {
    private JLabel newPassword;
    private JLabel confirmPassword;
    private JPasswordField newPasswd;
    private JPasswordField confirmPasswd;
    private JButton resetBtn;
    private String username;

    public ResetPasswordPage(String username){
        this.username = username;
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
        /**
         * if newPassword equals confirmed password
         * then update the the corresponding user's password
         */
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection;
                try{
                    if(Integer.valueOf(newPasswd.getText()) == Integer.valueOf(confirmPasswd.getText())){
                        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Test", "root", "dlx990330");
                        /**
                         * SQL update syntax reference:
                         * UPDATE table_name
                         * SET column1 = value1, column2 = value2, ...
                         * WHERE condition;
                         */
                        String updateQuery = "UPDATE Test.User\n" +
                                " SET column1 = value1, column2 = value2, ...\n" +
                                " WHERE condition;";
                        Statement sta = connection.createStatement();
                        int returnCode = sta.executeUpdate(updateQuery);
                        System.out.println("return code: " + returnCode);
//                        if (returnCode == 0)
//                            JOptionPane.showMessageDialog(regBtn, "This is already exist");
//
//                        else
//                            JOptionPane.showMessageDialog(regBtn,
//                                    "Welcome, " + welcomeMessage + "Your account is successfully created");
                        connection.close();
                    }
                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });

        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setBackground(Color.BLACK);
        add(panel);
        setBounds(450, 190, 800, 300);
        setLayout(new GridBagLayout());
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

//    public static void main(String[] args) {
//        new ResetPasswordPage();
//    }
}
