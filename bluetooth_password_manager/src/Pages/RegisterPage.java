package Pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterPage implements ActionListener {
    BufferedWriter writer;
    JFrame frame = new JFrame();
    JButton registerButton = new JButton("register");
    JButton backButton = new JButton("back");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JPasswordField userPasswordAgainField = new JPasswordField();
    JLabel userIDLabel = new JLabel("username:");
    JLabel userPasswordLabel = new JLabel("password:");
    JLabel userPasswordAgainLabel = new JLabel("password:");
    JLabel messageLabel = new JLabel("");

    RegisterPage() {
        userIDLabel.setBounds(50, 75, 75, 25);
        userPasswordLabel.setBounds(50, 125, 75, 25);
        userPasswordAgainLabel.setBounds(50, 175, 75, 25);

        userIDField.setBounds(150, 75, 200, 25);
        userPasswordField.setBounds(150, 125, 200, 25);
        userPasswordAgainField.setBounds(150, 175, 200, 25);

        registerButton.setBounds(150, 225, 100, 25);
        registerButton.addActionListener(this);
        backButton.setBounds(250, 225, 100 ,25);
        backButton.addActionListener(this);

        messageLabel.setBounds(150, 250, 350, 35);

        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(userPasswordAgainLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(userPasswordAgainField);
        frame.add(registerButton);
        frame.add(backButton);
        frame.add(messageLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 350);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String userID = userIDField.getText();
            String password1 = String.valueOf(userPasswordField.getPassword());
            String password2 = String.valueOf(userPasswordAgainField.getPassword());

            if (userID.length() >= 4 && userID.length() <= 20) {
                if (password1.length() >= 12 && password1.length() <= 50) {
                    if (password1.equals(password2)) {
                        try {
                            writer = new BufferedWriter(new FileWriter("123asd.txt"));
                            writer.write(userID);
                            writer.write("\n" + password2);
                            writer.close();
                            messageLabel.setForeground(Color.green);
                            messageLabel.setText("account made successfully");
                        } catch (IOException c) {
                            throw new RuntimeException(c);
                        }
                    } else {
                        messageLabel.setForeground(Color.red);
                        messageLabel.setText("passwords do not match");
                    }
                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("password must be between 12 and 50 characters long");
                }
            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("username must be between 4 and 20 characters long");
            }
        }

        if (e.getSource() == backButton) {
            frame.dispose();
            LoginPage loginPage = new LoginPage();
        }
    }
}
