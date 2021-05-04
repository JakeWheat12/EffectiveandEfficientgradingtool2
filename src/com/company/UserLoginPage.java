package com.company;

import org.mindrot.jbcrypt.BCrypt;
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

    public UserLoginPage() {
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
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "1k2k3k4k");
                    String query = "SELECT UserPassword FROM user.user WHERE UserName=" + "\"" + usernameField + "\"" + ";";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    System.out.println(resultSet);

                    boolean validUsername = resultSet.next();   //check for query
                    if(validUsername && BCrypt.checkpw(passwordField, resultSet.getString("UserPassword"))){
                        MainFrame mainFrame = new MainFrame();
                        mainFrame.makeFrame(1000, 800);
                    }
                    else
                    JOptionPane.showMessageDialog(loginBtn, "Username or Password is not correct! Make sure you enter the right account information!");
                } catch (Exception exception) {
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
}
