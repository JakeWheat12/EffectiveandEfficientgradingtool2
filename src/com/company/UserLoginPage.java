package com.company;

import org.mindrot.jbcrypt.BCrypt;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class UserLoginPage extends JFrame {

    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JTextField userName;
    private JPasswordField password;
    private JButton loginBtn;   //login
    private JLabel forgetPassword;
    private JFrame frame;


    public String globalUsername = "";   //record the current user

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

    public UserLoginPage() {
        setTitle("Login Page");
        JPanel panel = new JPanel();
        frame = new JFrame();

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
                    connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
                    String query = "SELECT UserPassword FROM User WHERE UserName=" + "\"" + usernameField + "\"" + ";";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);

                    boolean validUsername = resultSet.next();   //check for query
                    if(validUsername && BCrypt.checkpw(passwordField, resultSet.getString("UserPassword"))){
                        frame.dispose();    //close the current login window
                        globalUsername = usernameField; //store the current username globally
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

        forgetPassword = new JLabel("forget password");
        forgetPassword.setForeground(Color.YELLOW);
        panel.add(forgetPassword);
        /**
         * allow user jump to forgetPassword page
         */
        forgetPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ForgetPasswordPage();
            }
        });

        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setBackground(Color.BLACK);
        frame.add(panel);
        frame.setBounds(450, 190, 800, 300);
        frame.setLayout(new GridBagLayout());
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        new UserLoginPage();
    }
}
