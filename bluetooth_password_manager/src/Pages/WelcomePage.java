package Pages;

import Components.Decryption;
import Components.PrintInfo;
import Components.PrintPW;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class WelcomePage implements ActionListener {
    BufferedReader reader;
    JFrame frame = new JFrame();
    JButton addButton = new JButton("add");
    JButton refreshButton = new JButton("refresh");
    JButton bluetoothButton = new JButton("sharing");
    Decryption decryption = new Decryption();


    WelcomePage() {
        addButton.setBounds(10, 10, 100, 25);
        addButton.setFocusable(false);
        addButton.addActionListener(this);
        refreshButton.setBounds(110, 10, 100 ,25);
        refreshButton.setFocusable(false);
        refreshButton.addActionListener(this);
        bluetoothButton.setBounds(1150, 10, 100 ,25);
        bluetoothButton.setFocusable(false);
        bluetoothButton.addActionListener(this);


        try {
            reader = new BufferedReader(new FileReader("logins.txt"));
            String line;
            int whichFile = 1;
            int i = 0;
            int j = 0;
            int k = 1;
            int height = 50;

            while ((line = reader.readLine()) != null) {
                if (i == 0) {
                    JLabel label = new JLabel("title:");
                    label.setBounds(10, height + j, 75, 25);
                    String decryptedString = decryption.Decryption(k, line, whichFile);
                    PrintInfo printInfo = new PrintInfo(height, j, decryptedString, frame);
                    k += 2;

                    frame.add(label);
                }
                if (i == 1) {
                    JLabel label = new JLabel("login:");
                    label.setBounds(10, height + j, 75, 25);
                    String decryptedString = decryption.Decryption(k, line, whichFile);
                    PrintInfo printInfo = new PrintInfo(height, j, decryptedString, frame);
                    k += 2;

                    frame.add(label);
                }
                if (i == 2) {
                    JLabel label = new JLabel("password:");
                    label.setBounds(10, height + j, 75, 25);
                    String decryptedString = decryption.Decryption(k, line, whichFile);
                    PrintPW printPW = new PrintPW(height, j, decryptedString, frame);
                    k += 2;

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

        frame.add(addButton);
        frame.add(refreshButton);
        frame.add(bluetoothButton);

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

        if (e.getSource() == bluetoothButton) {
            BluetoothPage bluetoothPage = new BluetoothPage();
        }

    }
}
