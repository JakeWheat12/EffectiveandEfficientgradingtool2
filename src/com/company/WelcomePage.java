package com.company;

import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Carter Du
 * The welcome page allow user to sign up or register
 */
public class WelcomePage extends JFrame {
    private JButton signUpBtn;
    private JButton loginBtn;
    private JLabel label;
    private static int WIDTH = 600;
    private static int HEIGHT = 600;
    private JLabel dane;    //picture of great dane
    private JLabel ualbany; //picture of school logo

    public WelcomePage(String title){
        setTitle(title);
        setSize(WIDTH, HEIGHT);

        JPanel panel = new JPanel();
        //if want to say the layout => layoutConstraint"inserts 0 0 0 0, debug"
        panel.setLayout(new MigLayout("insets n", "[grow]", "[]80[][grow]"));


        JPanel logoPanel = new JPanel();   //panel for adding logo
        logoPanel.setLayout(new FlowLayout());
        logoPanel.setOpaque(false); //get ride of white background

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

        panel.add(loginBtn, "cell 0 0, alignx center");
        panel.add(signUpBtn, "cell 0 0, alignx center");
        panel.setBackground(Color.BLACK);

        //product name label
        label = new JLabel("COMMENT GENERATOR");
        label.setBounds(220,220,200,100);
        JTextField textField = new JTextField("Comment Generator");
        label.setFont(new Font("Serif Bold Italic", Font.BOLD, 20));

        label.setForeground(Color.CYAN);
        label.setText(textField.getText());
        panel.add(label, "cell 0 1, alignx center");

        try {

            BufferedImage daneLogo = ImageIO.read(new File("src/com/company/pictures/GreatDane.jpg"));
            Image daneImage = daneLogo.getScaledInstance(100, 100, Image.SCALE_DEFAULT);  //resizing the image
            dane = new JLabel(new ImageIcon(daneImage));

            BufferedImage ualbanyLogo = ImageIO.read(new File("src/com/company/pictures/ualbanyLOGO.jpeg"));
            Image albanyImage = ualbanyLogo.getScaledInstance(100, 100, Image.SCALE_REPLICATE); //resizing the image
            ualbany = new JLabel(new ImageIcon(albanyImage));

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        logoPanel.add(dane);
        logoPanel.add(ualbany);

        panel.add(logoPanel, "cell 0 2, align center");


        add(panel);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



}
