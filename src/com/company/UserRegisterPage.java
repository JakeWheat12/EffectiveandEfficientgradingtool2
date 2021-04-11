package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class UserRegisterPage extends JFrame {
    private JTextField firstName;
    private JTextField lastName;
    private JTextField email;
    private JTextField userName;
    private JPasswordField passwordField;
    private JButton regBtn;
    private JPanel container;

    public UserRegisterPage(String title){
        container = new JPanel();

        JLabel lblNewUserRegister = new JLabel("Sign-Up Page!");    //font
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 42));
        lblNewUserRegister.setBounds(362, 52, 325, 50);
        container.add(lblNewUserRegister);

        //First Name Input
        JLabel lblName = new JLabel("First Name");
        lblName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblName.setBounds(58, 152, 99, 43);
        container.add(lblName);
        firstName = new JTextField();
        firstName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        firstName.setBounds(214, 151, 228, 50);
        container.add(firstName);
        firstName.setColumns(10);

        //Last Name Input
        JLabel lblNewLabel = new JLabel("Last Name");
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel.setBounds(58, 243, 110, 29);
        container.add(lblNewLabel);
        lastName = new JTextField();
        lastName.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lastName.setBounds(214, 235, 228, 50);
        container.add(lastName);
        lastName.setColumns(10);

        //Email Input
        JLabel lblEmailAddress = new JLabel("Email");
        lblEmailAddress.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblEmailAddress.setBounds(58, 324, 124, 36);
        container.add(lblEmailAddress);
        email = new JTextField();
        email.setFont(new Font("Tahoma", Font.PLAIN, 32));
        email.setBounds(214, 320, 228, 50);
        container.add(email);
        email.setColumns(10);

        //Username Input
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblUsername.setBounds(542, 159, 99, 29);
        container.add(lblUsername);
        userName = new JTextField();
        userName.setFont(new Font("Tahoma", Font.PLAIN, 32));
        userName.setBounds(707, 151, 228, 50);
        container.add(userName);
        userName.setColumns(10);

        //Password Input
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblPassword.setBounds(542, 245, 99, 24);
        container.add(lblPassword);
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(707, 235, 228, 50);
        container.add(passwordField);

        //register button
        regBtn = new JButton("Register Here");
        regBtn.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        regBtn.setBounds(399, 447, 259, 74);
        regBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fN = firstName.getText();
                String lN = lastName.getText();
                String mail = email.getText();
                String userN = userName.getText();
                String passwd = passwordField.getText();

                Connection connection;
                String welcomeMessage = "" + fN;
                welcomeMessage += " \n";
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/User", "root", "dlx990330");
                    String query = "INSERT INTO Users VALUES('" + fN + "','" + lN + "','" + mail + "','" +
                            passwd + "','" + userN + "')";

                    Statement sta = connection.createStatement();
                    int x = sta.executeUpdate(query);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(regBtn, "This is alredy exist");
                    } else {
                        JOptionPane.showMessageDialog(regBtn,
                                "Welcome, " + welcomeMessage + "Your account is sucessfully created");
                    }
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        container.add(regBtn);

        container.setVisible(true);
        container.setBorder(new EmptyBorder(5, 5, 5, 5));
        container.setLayout(null);
        setContentPane(container);

        setBounds(450, 190, 1014, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String[] args) {
        new UserRegisterPage("User Register");
    }
}
