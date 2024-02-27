package Pages;

import Components.Decryption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginPage implements ActionListener
{
    BufferedReader reader;
    JFrame frame = new JFrame();
    JButton loginButton = new JButton("login");
    JButton registerPageButton = new JButton("register");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("username:");
    JLabel userPasswordLabel = new JLabel("password:");
    JLabel messageLabel = new JLabel("");
    Decryption decryption = new Decryption();

    public LoginPage() {
        userIDLabel.setBounds(50, 100, 75, 25);
        userPasswordLabel.setBounds(50, 150, 75, 25);

        userIDField.setBounds(150, 100, 200, 25);
        userPasswordField.setBounds(150, 150, 200, 25);

        loginButton.setBounds(150, 200, 100, 25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        registerPageButton.setBounds(250, 200, 100, 25);
        registerPageButton.setFocusable(false);
        registerPageButton.addActionListener(this);

        messageLabel.setBounds(150, 225, 250 ,35);

        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(registerPageButton);
        frame.add(messageLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 350);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());
            String fileID;
            String filePassword;
            String decryptedID = "";
            String decryptedPassword = "";

            try {
                reader = new BufferedReader(new FileReader("mainLogin.txt"));
                String line;
                int i = 0;
                int k = 1;
                while ((line = reader.readLine()) != null) {
                    if (i == 0) {
                        fileID = line;
                        decryptedID = decryption.Decryption(k, fileID);
                        k += 2;
                    }
                    if (i == 1) {
                        filePassword = line;
                        decryptedPassword = decryption.Decryption(k, filePassword);
                    }
                    i++;
                }
                reader.close();
            } catch (IOException c) {
                throw new RuntimeException(c);
            }

            if (userID.equals(decryptedID)) {
                if (password.equals(decryptedPassword)) {
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login successful");
                    frame.dispose();
                    WelcomePage welcomePage = new WelcomePage();
                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Login failed");
                }
            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Login failed");
            }
        }

        if (e.getSource() == registerPageButton) {
            frame.dispose();
            RegisterPage registerPage = new RegisterPage();
        }
    }
}
