package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UserLoginPage extends JFrame {

    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JTextField userName;
    private JPasswordField password;
    private JButton loginBtn;   //login

    public UserLoginPage(){
        setTitle("Login Page");
        JPanel panel = new JPanel();

        userNameLabel = new JLabel("UserName: ");
        userNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        userNameLabel.setBounds(58, 100, 99, 30);
        userNameLabel.setForeground(Color.CYAN);
        panel.add(userNameLabel);
        userName = new JTextField();
        userName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        userName.setBounds(200, 100, 228, 50);
        userName.setColumns(10);
        panel.add(userName);


        //Last Name Input
        passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        passwordLabel.setBounds(58, 200, 110, 30);
        passwordLabel.setForeground(Color.CYAN);
        panel.add(passwordLabel);
        password = new JPasswordField();
        password.setFont(new Font("Tahoma", Font.PLAIN, 20));
        password.setBounds(400, 200, 228, 50);
        password.setColumns(10);
        panel.add(password);


        loginBtn = new JButton("Login");
        panel.add(loginBtn);
        /**
         * button will check the account information in the database
         * if match => login successful
         * not match => error pop up
         */
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameField = userName.getText();
                String passwordField = password.getText();
                Connection connection;
                //String welcomeMessage = "" + fN;
                //SELECT * FROM User.Users WHERE UserName="carter565" && Passwd="dlx123";
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/User", "root", "dlx990330");
                    String query = "SELECT * FROM User.Users WHERE UserName=" + "\"" + usernameField +"\"" + "&& Passwd=" + "\"" + passwordField + "\"" + ";";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    System.out.println(resultSet);
                    if(!resultSet.next()){    //means that the query return nothing
                        JOptionPane.showMessageDialog(loginBtn, "Username or Password is not correct! Make sure you enter the right account information!");
                    }
                    else{   //login the system successful
                        MainFrame mainFrame = new MainFrame();
                        mainFrame.makeFrame(800, 800);
                    }

                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setBackground(Color.BLACK);
        add(panel);
        setBounds(450, 190, 1014, 600);
        setLayout(new GridBagLayout());
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
