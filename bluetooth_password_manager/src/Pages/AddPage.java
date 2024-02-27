package Pages;

import Components.Cryption;
import Components.Encryption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class AddPage implements ActionListener {
    BufferedWriter writer;
    JFrame frame = new JFrame();
    JButton addButton = new JButton("add");
    JButton clearButton = new JButton("clear");
    JTextField titleField = new JTextField();
    JTextField loginField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JLabel titleLabel = new JLabel("title:");
    JLabel loginLabel = new JLabel("login:");
    JLabel passwordLabel = new JLabel("password:");
    JLabel messageLabel = new JLabel("");

    AddPage() {
        titleLabel.setBounds(50, 75, 75, 25);
        loginLabel.setBounds(50, 125, 75, 25);
        passwordLabel.setBounds(50, 175, 75, 25);

        titleField.setBounds(150, 75, 200, 25);
        loginField.setBounds(150, 125, 200, 25);
        passwordField.setBounds(150, 175, 200, 25);

        addButton.setBounds(150, 225, 100, 25);
        addButton.setFocusable(false);
        addButton.addActionListener(this);
        clearButton.setBounds(250, 225, 100 ,25);
        clearButton.setFocusable(false);
        clearButton.addActionListener(this);

        messageLabel.setBounds(150, 250, 350, 35);

        frame.add(titleLabel);
        frame.add(loginLabel);
        frame.add(passwordLabel);
        frame.add(titleField);
        frame.add(loginField);
        frame.add(passwordField);
        frame.add(addButton);
        frame.add(clearButton);
        frame.add(messageLabel);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 350);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String title = titleField.getText();
            String login = loginField.getText();
            String password = String.valueOf(passwordField.getPassword());

            if (!title.isEmpty()) {
                if (!login.isEmpty()) {
                    if (!password.isEmpty()) {
                        try {
                            Encryption encryptedTitle = new Encryption(title);
                            Encryption encryptedLogin = new Encryption(login);
                            Encryption encryptedPassword = new Encryption(password);

                            messageLabel.setForeground(Color.green);
                            messageLabel.setText("account added successfully");
                        } catch (Exception c) {
                            throw new RuntimeException(c);
                        }
                    } else {
                        messageLabel.setForeground(Color.red);
                        messageLabel.setText("password field is empty");
                    }
                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("login field is empty");
                }
            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("title field is empty");
            }
        }

        if (e.getSource() == clearButton) {
            titleField.setText("");
            loginField.setText("");
            passwordField.setText("");
        }
    }
}
