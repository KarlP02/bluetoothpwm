package Pages;

import Components.PrintInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WelcomePage implements ActionListener {
    BufferedReader reader;
    JFrame frame = new JFrame();
    JLabel welcomeLabel = new JLabel("Hello, ");
    JButton addButton = new JButton("add");
    JButton refreshButton = new JButton("refresh");

    WelcomePage() {
        welcomeLabel.setBounds(10, 10, 200, 35);
        welcomeLabel.setText("Hello!");

        addButton.setBounds(10, 50, 100, 25);
        addButton.addActionListener(this);
        refreshButton.setBounds(110, 50, 100 ,25);
        refreshButton.addActionListener(this);

        try {
            reader = new BufferedReader(new FileReader("asd123.txt"));
            String line;
            int i = 0;
            int j = 1;
            int height = 100;

            // does not work
            while ((line = reader.readLine()) != null) {
                if (i == 0) {
                    JLabel label = new JLabel("title:");
                    label.setBounds(10, height + j, 75, 25);
                    PrintInfo printInfo = new PrintInfo(height, j, line, frame);

                    frame.add(label);
                }
                if (i == 1) {
                    JLabel label = new JLabel("login:");
                    label.setBounds(10, height + j, 75, 25);
                    PrintInfo printInfo = new PrintInfo(height, j, line, frame);

                    frame.add(label);
                }
                if (i == 2) {
                    JLabel label = new JLabel("password:");
                    label.setBounds(10, height + j, 75, 25);
                    PrintInfo printInfo = new PrintInfo(height, j, line, frame);

                    frame.add(label);

                    i = -1;
                    j += 25;
                }
                i++;
                j += 25;
            }

            reader.close();
        } catch (IOException c) {
            throw new RuntimeException(c);
        }

        frame.add(welcomeLabel);
        frame.add(addButton);
        frame.add(refreshButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            AddPage addPage = new AddPage();
        }

        if (e.getSource() == refreshButton) {
            frame.dispose();
            WelcomePage welcomePage = new WelcomePage();
        }
    }
}
