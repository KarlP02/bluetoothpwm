package Pages;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage implements ActionListener {
    JFrame frame = new JFrame();
    JLabel welcomeLabel = new JLabel("Hello, ");
    JButton addButton = new JButton("add");

    WelcomePage(String userID) {
        welcomeLabel.setBounds(10, 10, 200, 35);
        welcomeLabel.setText("Hello, " + userID + "!");

        addButton.setBounds(10, 50, 100, 25);
        addButton.addActionListener(this);

        frame.add(welcomeLabel);
        frame.add(addButton);

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
    }
}
