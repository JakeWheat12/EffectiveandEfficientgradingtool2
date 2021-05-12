package com.company;

import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author Carter Du
 * Store users' accounts' information into the database
 * reference: https://github.com/patrickfav/bcrypt (for hashing the password)
 */
public class UserRegisterPage extends JFrame {
    private JTextField firstName;
    private JTextField lastName;
    private JTextField email;
    private JTextField userName;
    private JPasswordField passwordField;
    private JButton regBtn;
    private JPanel container;
    private JFrame frame;

    //@todo find a way to make this jdbc stuff not user specifc?
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "user";
    private static final String PASSWORD = "1k2k3k4k";

    /*TIM G*/
    //private static final String DB_URL = "jdbc:mysql://localhost:3306/e&e_gradingtool";
    //private static final String USER = "TimG";

    /*GENERIC*/
    //private static final String DB_URL = "";
    //private static final String USER = "";
    //private static final String PASSWORD = "";

    public UserRegisterPage(){
        container = new JPanel();
        frame = new JFrame();

        JLabel lblNewUserRegister = new JLabel("Sign-Up Page!");    //font
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 42));
        lblNewUserRegister.setBounds(362, 52, 325, 50);
        lblNewUserRegister.setForeground(Color.YELLOW);
        container.add(lblNewUserRegister);

        //First Name Input
        JLabel lblName = new JLabel("First Name");
        lblName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblName.setForeground(Color.CYAN);
        lblName.setBounds(58, 152, 99, 43);
        container.add(lblName);
        firstName = new JTextField();
        firstName.setFont(new Font("Tahoma", Font.PLAIN, 32));
        firstName.setBounds(214, 151, 228, 50);
        container.add(firstName);
        firstName.setColumns(10);

        //Last Name Input
        JLabel lblNewLabel = new JLabel("Last Name");
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel.setForeground(Color.CYAN);
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
        lblEmailAddress.setForeground(Color.CYAN);
        lblEmailAddress.setBounds(58, 324, 124, 36);
        container.add(lblEmailAddress);
        email = new JTextField();
        email.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 32));
        email.setBounds(214, 320, 300, 50);
        container.add(email);
        email.setColumns(10);

        //Username Input
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblUsername.setForeground(Color.CYAN);
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
        lblPassword.setForeground(Color.CYAN);
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
                String passwd = passwordField.getText();    //TODO: getText() is depriciated
                String hash = BCrypt.hashpw(passwd, BCrypt.gensalt(12));    //hash of the plain-text password

                Connection connection;
                String welcomeMessage = "" + fN;
                welcomeMessage += " \n";
                try {
                    connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                    if(fN.isEmpty() || lN.isEmpty() || mail.isEmpty() || mail.length()<=5 ||userN.isEmpty() ||
                        userN.length()<3 || passwd.isEmpty() || passwd.length()<=5){
                        JOptionPane.showMessageDialog(regBtn, "User information needs to be accurate. " +
                                "Make sure you follow the rules below!");
                    }
                    else{
//                        String query = "INSERT INTO ACCOUNTS VALUES('" + userN + "','" + hash + "','" + fN + "','" +
//                                    lN + "','" + mail + "')";
                        String query = "INSERT INTO User VALUES('" + userN + "','" + hash + "','" + fN + "','" +
                                lN + "','" + mail + "')";

                        Statement sta = connection.createStatement();
                        int returnCode = sta.executeUpdate(query);
                        System.out.println("return code: " + returnCode);
                        if (returnCode == 0)
                            JOptionPane.showMessageDialog(regBtn, "This is already exist");

                        else
                            JOptionPane.showMessageDialog(regBtn,
                                    "Welcome, " + welcomeMessage + "Your account is successfully created");
                        frame.dispose(); //close the register page after user registered
                        connection.close();
                    }
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        container.add(regBtn);
        JLabel rule1 = new JLabel("-- UserName's length should be greater than 3");
        JLabel rule2 = new JLabel("-- Password's length should be greater than 5");
        JLabel rule3 = new JLabel("-- Email's format should be standard");
        rule1.setBounds(380, 650, 800, 30);
        rule1.setForeground(Color.WHITE);
        rule2.setBounds(380, 670,800,30);
        rule2.setForeground(Color.WHITE);
        rule3.setBounds(380,690,800,30);
        rule3.setForeground(Color.WHITE);
        container.add(rule1);
        container.add(rule2);
        container.add(rule3);
        container.setVisible(true);
        container.setBorder(new EmptyBorder(5, 5, 5, 5));
        container.setLayout(null);
        container.setBackground(Color.BLACK);
        frame.setContentPane(container);


        frame.setBounds(450, 190, 1014, 800);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }
}
