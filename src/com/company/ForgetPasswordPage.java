package com.company;

import javax.swing.*;
import java.awt.*;

public class ForgetPasswordPage extends JFrame {
    private JLabel email;
    private JLabel verifyCode;
    private JTextArea emailArea;
    private JPasswordField code;
    private JButton sendMailButton;
    private JButton verifyButton;

    public ForgetPasswordPage(){
        setTitle("Forget Password Page");
        JPanel panel = new JPanel();

        //enter email label
        email = new JLabel("enter registered email: ");
        email.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(email);
        //enter email field
        emailArea = new JTextArea();
        emailArea.setColumns(10);
        panel.add(emailArea);
        //send mail button
        sendMailButton = new JButton("SEND");
        panel.add(sendMailButton);

        //code verification label
        verifyCode = new JLabel("enter the verification-code: ");
        verifyCode.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(verifyCode);
        //code enter field
        code = new JPasswordField();
        code.setColumns(10);
        panel.add(code);
        //verify button
        verifyButton = new JButton("VERIFY");
        panel.add(verifyButton);


        add(panel);
        setResizable(false);
        setVisible(true);
        setSize(300, 300);
    }

    public static void main(String[] args) {
        new ForgetPasswordPage();
    }
}
