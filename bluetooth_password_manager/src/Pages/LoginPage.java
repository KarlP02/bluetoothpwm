package Pages;

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

    public LoginPage() {
        userIDLabel.setBounds(50, 100, 75, 25);
        userPasswordLabel.setBounds(50, 150, 75, 25);

        userIDField.setBounds(125, 100, 200, 25);
        userPasswordField.setBounds(125, 150, 200, 25);

        loginButton.setBounds(125, 200, 100, 25);
        loginButton.addActionListener(this);
        registerPageButton.setBounds(225, 200, 100, 25);
        registerPageButton.addActionListener(this);

        messageLabel.setBounds(125, 250, 250 ,35);

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());
            String fileID = "";
            String filePassword = "";

            try {
                reader = new BufferedReader(new FileReader("123asd.txt"));
                String line;
                while ((line = reader.readLine()) != null) {
                    for (int i = 0; i < 2; i++) {
                        if (i == 0) {
                            fileID = line;
                        }
                        if (i == 1) {
                            filePassword = line;
                        }
                    }
                }
                reader.close();
            } catch (IOException c) {
                throw new RuntimeException(c);
            }

            if (userID.equals(fileID)) {
                if (password.equals(filePassword)) {
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login successful");
                    frame.dispose();
                    WelcomePage welcomePage = new WelcomePage(userID);
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
